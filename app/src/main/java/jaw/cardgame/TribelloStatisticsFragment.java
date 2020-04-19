package jaw.cardgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import jaw.cardgame.util.PlayerConverterJson;
import jaw.cardgame.util.StorageUtil;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TribelloStatisticsFragment extends Fragment {

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.tribello_statistics, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @SuppressLint("DefaultLocale")
    private void initView(View v) {
        RelativeLayout rl = v.findViewById(R.id.tribello_statistics_fragment);
        ArrayList<String> allNames;
        JsonElement element = null;
        try {
            element = StorageUtil.load(v.getContext().getApplicationContext(), "All_players");
        } catch (IllegalStateException e){
            StorageUtil.resetData(v.getContext().getApplicationContext(), "All_players");
        } catch (FileNotFoundException ignored){
        }

        if(!(element == null || !element.isJsonArray())){

            JsonArray array = element.getAsJsonArray();
            allNames = PlayerConverterJson.getInstance().toObjectString(array);
            for (int i = 0; i < allNames.size(); i++) {
                String playerName = allNames.get(i);
                Player player = new Player();
                player.load(mContext, playerName);

                TextView name = new TextView(v.getContext());
                name.setText(player.getName());
                name.setId(100+i);
                name.setTypeface(null, Typeface.BOLD);
                name.setGravity(Gravity.CENTER);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                lp.setMargins(0,40,0,0);
                lp.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER);

                if (i > 0) {
                    lp.addRule(RelativeLayout.BELOW, 600+i-1);
                } else {
                    lp.addRule(RelativeLayout.BELOW, rl.getId());
                }
                rl.addView(name, lp);

                TextView firstPlacements = new TextView(v.getContext());
                firstPlacements.setText(String.format("First placements: %d", player.getTribelloFirst()));
                firstPlacements.setId(200+i);
                firstPlacements.setGravity(Gravity.CENTER);
                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp2.addRule(RelativeLayout.BELOW, name.getId());
                lp2.setMargins(0,10,0,0);
                rl.addView(firstPlacements, lp2);

                TextView secondPlacements = new TextView(v.getContext());
                secondPlacements.setText(String.format("Second placements: %d", player.getTribelloSecond()));
                secondPlacements.setId(300+i);
                secondPlacements.setGravity(Gravity.CENTER);
                RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp3.addRule(RelativeLayout.BELOW, firstPlacements.getId());
                rl.addView(secondPlacements, lp3);

                TextView thirdPlacements = new TextView(v.getContext());
                thirdPlacements.setText(String.format("Third placements: %d", player.getTribelloThird()));
                thirdPlacements.setId(400+i);
                thirdPlacements.setGravity(Gravity.CENTER);
                RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp4.addRule(RelativeLayout.BELOW, secondPlacements.getId());
                rl.addView(thirdPlacements, lp4);

                TextView highscore = new TextView(v.getContext());
                highscore.setText(String.format("Highest score: %d", player.getTribelloHighScore()));
                highscore.setId(500+i);
                highscore.setGravity(Gravity.CENTER);
                RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp5.addRule(RelativeLayout.BELOW, thirdPlacements.getId());
                rl.addView(highscore, lp5);

                TextView jumboscore = new TextView(v.getContext());
                jumboscore.setText(String.format("Lowest score: %d",player.getTribelloJumboScore()));
                jumboscore.setId(600+i);
                jumboscore.setGravity(Gravity.CENTER);
                RelativeLayout.LayoutParams lp6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp6.addRule(RelativeLayout.BELOW, highscore.getId());
                rl.addView(jumboscore, lp6);
            }
        }
    }
}
