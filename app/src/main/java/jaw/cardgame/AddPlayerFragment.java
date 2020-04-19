package jaw.cardgame;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddPlayerFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private String new_player_name;
    private static final String STATE_NEW_PLAYER_NAME = "newPlayerName";

    private EditText editText_new_player_name;

    public AddPlayerFragment() {
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
        Button add_new_player = v.findViewById(R.id.add_new_player_button);
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
    public void onClick(View v) {
        if (v.getId() == R.id.add_new_player_button) {
            new_player_name = editText_new_player_name.getText().toString();
            if (!new_player_name.equals("")) {
                Player player = new Player(new_player_name);
                boolean uniqueName = player.saveNewPlayer(mContext, new_player_name);
                if (!uniqueName) {
                    CharSequence text = "Player with that name already exists";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    editText_new_player_name.setText(null);
                }
            } else {
                CharSequence text = "Can't enter empty name";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(mContext, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }
}
