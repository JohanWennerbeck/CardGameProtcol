package jaw.cardgame;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.support.v4.app.Fragment;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import jaw.cardgame.util.PlayerConverterJson;
import jaw.cardgame.util.StorageUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectPlayersFragment extends Fragment implements View.OnClickListener{
    ArrayList<String> selectedPlayers;

    public SelectPlayersFragment() {
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
        boolean notEnoughPlayers = false;
        RelativeLayout ll = v.findViewById(R.id.select_player_fragment);
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
                RelativeLayout.LayoutParams lpButton = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lpButton.setMargins(20, 0, 20, 0);
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
                    selectedCheckImage.setImageResource(R.drawable.ic_check_black_24dp);
                    selectedPlayers.add(playerButton.getText().toString());
                });

                playerButton.setOnLongClickListener((View view2) -> {
                    selectedCheckImage.setImageResource(android.R.color.transparent);
                    selectedPlayers.remove(playerButton.getText().toString());
                    return true;
                });
                
                ll.addView(selectedCheckImage, lpImage);
                ll.addView(playerButton, lpButton);
                allPlayerButtons.add(playerButton);
            }
        }

        if(allNames.size() < 3 || notEnoughPlayers){
            notEnoughPlayers(v.getContext().getApplicationContext());
        } else {
            Button myButton = new Button(v.getContext());
            myButton.setText(R.string.start_new_game);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.setMarginEnd(50);
            lp.setMarginStart(50);
            lp.addRule(RelativeLayout.BELOW, allPlayerButtons.get(allPlayerButtons.size()-1).getId());
            ll.addView(myButton, lp);
        }
    }

    private void notEnoughPlayers(Context context) {
        CharSequence text = "Not enough players is created to play a game of trebello";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void onClick(View v) {

    }
}
