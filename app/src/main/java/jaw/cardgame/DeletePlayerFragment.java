package jaw.cardgame;

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
public class DeletePlayerFragment extends Fragment{
    private ArrayList<String> selectedPlayers;
    private ArrayList<String> allNames;
    public DeletePlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selectedPlayers = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_select_players, container, false);
        initButtons(v);

        return v;
    }

    private void initButtons(View v) {
        RelativeLayout rl = v.findViewById(R.id.select_player_fragment);
        allNames = new ArrayList<>();
        ArrayList<Button> allPlayerButtons = new ArrayList<>();

        JsonElement element = null;
        try {
            element = StorageUtil.load(v.getContext().getApplicationContext(), "All_players");
        } catch (IllegalStateException e){
            StorageUtil.resetData(v.getContext().getApplicationContext(), "All_players");
        } catch (FileNotFoundException ignored){
        }

        if (element != null && element.isJsonArray()) {

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

        Button deletePlayersButton = new Button(v.getContext());
        deletePlayersButton.setText(R.string.delete_selected_players);
        deletePlayersButton.setBackgroundResource(R.drawable.button_corner);
        deletePlayersButton.setTypeface(null, Typeface.BOLD);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(50,20,50,20);
        lp.addRule(RelativeLayout.BELOW, allPlayerButtons.get(allPlayerButtons.size()-1).getId());

        deletePlayersButton.setOnClickListener((View view) -> {
            deletePlayers(selectedPlayers);
            CharSequence text = "Players are deleted";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(view.getContext(), text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            DeletePlayerFragment deletePlayerFragment= new DeletePlayerFragment();
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, deletePlayerFragment)
                    .addToBackStack(null)
                    .commit();
        });

        rl.addView(deletePlayersButton, lp);

    }

    private void deletePlayers(ArrayList<String> selectedPlayers) {
        for (String name : selectedPlayers){
            allNames.remove(name);
        }
        JsonArray newArray = PlayerConverterJson.getInstance().toJson(allNames);
        StorageUtil.save(Objects.requireNonNull(getContext()), "All_players", newArray);
    }
}
