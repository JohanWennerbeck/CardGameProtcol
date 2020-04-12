package jaw.cardgame;

import android.graphics.Color;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.support.v4.app.Fragment;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import jaw.cardgame.util.PlayerConverterJson;
import jaw.cardgame.util.StorageUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectPlayersFragment extends Fragment {

    public SelectPlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(100, 0, 100, 0);
                if(i > 0){
                    lp.addRule(RelativeLayout.BELOW, allPlayerButtons.get(i-1).getId());
                } else {
                    lp.addRule(RelativeLayout.BELOW, R.id.select_player_textview);
                }
                ll.addView(playerButton, lp);

                allPlayerButtons.add(playerButton);
            }

        }

        Button myButton = new Button(v.getContext());
        myButton.setText(R.string.start_new_game);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMarginEnd(50);
        lp.setMarginStart(50);
        lp.addRule(RelativeLayout.BELOW, allPlayerButtons.get(allPlayerButtons.size()-1).getId());
        ll.addView(myButton, lp);
        if(allNames.size() < 3 || notEnoughPlayers){
            notEnoughPlayers();
            return;
        }
    }

    private void notEnoughPlayers() {
    }
}
