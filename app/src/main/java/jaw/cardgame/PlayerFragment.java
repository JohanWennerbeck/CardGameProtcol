package jaw.cardgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PlayerFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    String new_player_name;
    private static final String STATE_NEW_PLAYER_NAME = "newPlayerName";

    Button add_new_player;
    EditText editText_new_player_name;

    public PlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player, container, false);
        add_new_player = v.findViewById(R.id.add_new_player_button);
        add_new_player.setOnClickListener(this);
        editText_new_player_name = v.findViewById(R.id.new_player_name);
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(STATE_NEW_PLAYER_NAME, new_player_name);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new_player_button: {
                new_player_name = editText_new_player_name.getText().toString();
                Player player = new Player(new_player_name);
                player.save(mContext, new_player_name);
                editText_new_player_name.setText(null);
            }
        }
    }
}
