package jaw.cardgame;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

import jaw.cardgame.util.PlayerConverterJson;
import jaw.cardgame.util.StorageUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectPlayersFragment extends Fragment{
    private ArrayList<String> selectedPlayers;

    public SelectPlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundleArguments = getArguments();
        assert bundleArguments != null;
        String gameOption = bundleArguments.getString("Game_option");
        
        // Inflate the layout for this fragment
        selectedPlayers = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_select_players, container, false);
        initButtons(v, gameOption);

        return v;
    }

    private void initButtons(View v, String gameOption) {
        boolean notEnoughPlayers = false;
        RelativeLayout rl = v.findViewById(R.id.select_player_fragment);
        ArrayList<String> allNames = new ArrayList<>();
        ArrayList<Button> allPlayerButtons = new ArrayList<>();

        JsonElement element = null;
        try {
            element = StorageUtil.load(v.getContext().getApplicationContext(), "All_players");
        } catch (IllegalStateException e){
            StorageUtil.resetData(v.getContext().getApplicationContext(), "All_players");
        } catch (FileNotFoundException ignored){
        }

        if(element == null || !element.isJsonArray()){
            //Not enough players
            notEnoughPlayers = true;
        } else {

            JsonArray array = element.getAsJsonArray();
            allNames = PlayerConverterJson.getInstance().toObjectString(array);
            for (int i = 0; i < allNames.size(); i++) {
                String playerName = allNames.get(i);
                Button playerButton = new Button(v.getContext());
                playerButton.setText(playerName);
                playerButton.setId(100 + i);
                playerButton.setBackgroundResource(R.drawable.button_corner_selectable);
                RelativeLayout.LayoutParams lpButton = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lpButton.setMargins(20, 10, 20, 0);
                lpButton.addRule(RelativeLayout.CENTER_HORIZONTAL);
                if(i > 0){
                    lpButton.addRule(RelativeLayout.BELOW, allPlayerButtons.get(i-1).getId());
                } else {
                    lpButton.addRule(RelativeLayout.BELOW, R.id.select_player_textview);
                }

                ImageView selectedCheckImage = new ImageView(v.getContext());
                RelativeLayout.LayoutParams lpImage = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                selectedCheckImage.setImageResource(android.R.color.transparent);
                selectedCheckImage.setId(200 + i);
                lpImage.addRule(RelativeLayout.ALIGN_TOP, playerButton.getId());
                lpImage.addRule(RelativeLayout.ALIGN_BOTTOM, playerButton.getId());
                lpImage.addRule(RelativeLayout.END_OF, playerButton.getId());
                playerButton.setOnClickListener((View view) -> {
                    if(!selectedPlayers.contains(playerButton.getText().toString())){
                        selectedCheckImage.setImageResource(R.drawable.ic_check_black_24dp);
                        selectedPlayers.add(playerButton.getText().toString());
                        playerButton.setSelected(true);
                    } else {
                        selectedPlayers.remove(playerButton.getText().toString());
                        selectedCheckImage.setImageResource(android.R.color.transparent);
                        playerButton.setSelected(false);
                    }
                });
                
                rl.addView(selectedCheckImage, lpImage);
                rl.addView(playerButton, lpButton);
                allPlayerButtons.add(playerButton);
            }
        }

        if(allNames.size() < 3 || notEnoughPlayers){
            notEnoughPlayers(v.getContext().getApplicationContext());
        } else {
            Button startGameButton = new Button(v.getContext());
            startGameButton.setText(R.string.start_new_game);
            startGameButton.setBackgroundResource(R.drawable.button_corner);
            startGameButton.setTypeface(null, Typeface.BOLD);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(50,20,50,20);
            lp.addRule(RelativeLayout.BELOW, allPlayerButtons.get(allPlayerButtons.size()-1).getId());
            if(gameOption.equals("Tribello")) {
                startGameButton.setOnClickListener((View view) -> {
                    if (selectedPlayers.size() == 3) {
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("playerNames", selectedPlayers);
                        bundle.putBoolean("newGame", true);
                        TribelloScoreFragment tribelloScoreFragment = new TribelloScoreFragment();
                        tribelloScoreFragment.setArguments(bundle);
                        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, tribelloScoreFragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        CharSequence text = "Select 3 players to play a game of Tribello";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(view.getContext(), text, duration);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }

                });
            }
            rl.addView(startGameButton, lp);
        }
    }

    private void notEnoughPlayers(Context context) {
        CharSequence text = "Not enough players is created to play a game of Tribello";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
