package jaw.cardgame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tribello_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        switch (Objects.requireNonNull(getIntent().getAction())) {
            case "LoadOrNew":
                if (fragment == null) {
                    fragment = new NewOrLoadFragment();

                    manager.beginTransaction()
                            .add(R.id.fragmentContainer, fragment)
                            .commit();
                }
                break;
            case "Statistics":
                if (fragment == null) {
                    fragment = new TribelloStatisticsFragment();
                    manager.beginTransaction()
                            .add(R.id.fragmentContainer, fragment)
                            .commit();
                }
                break;
            case "AddPlayers":
                if (fragment == null) {
                    fragment = new AddPlayerFragment();
                    manager.beginTransaction()
                            .add(R.id.fragmentContainer, fragment)
                            .commit();
                }
                break;
            case "DeletePlayers":
                if (fragment == null) {
                    fragment = new DeletePlayerFragment();
                    manager.beginTransaction()
                            .add(R.id.fragmentContainer, fragment)
                            .commit();
                }
                break;
        }

    }


}
