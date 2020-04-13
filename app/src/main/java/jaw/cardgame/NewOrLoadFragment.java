package jaw.cardgame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;


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
