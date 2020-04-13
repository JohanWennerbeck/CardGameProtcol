package jaw.cardgame;

import android.content.Context;
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
public class NewOrLoadFragment extends Fragment {

    public NewOrLoadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_or_load_game, container, false);
        initButtons(v);

        return v;
    }

    private void initButtons(View v) {
        Button loadButton = v.findViewById(R.id.load_button);
        Button newGameButton = v.findViewById(R.id.new_game_button);

        loadButton.setOnClickListener((View view) -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("newGame", false);
            TrebelloScoreFragment trebelloScoreFragment= new TrebelloScoreFragment();
            trebelloScoreFragment.setArguments(bundle);
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, trebelloScoreFragment)
                    .addToBackStack(null)
                    .commit();
        });

        newGameButton.setOnClickListener((View view) -> {
            SelectPlayersFragment selectPlayersFragment= new SelectPlayersFragment();
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, selectPlayersFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
