package jaw.cardgame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trebello_main);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.trebelloFragmentContainer);
        if(getIntent().getAction() == "Game"){
            if (fragment == null) {
                fragment = new TrebelloScoreFragment();
                manager.beginTransaction()
                        .add(R.id.trebelloFragmentContainer, fragment)
                        .commit();
            }
        } else if (getIntent().getAction() == "Statistics"){
            if (fragment == null) {
                fragment = new TrebelloStatisticsFragment();
                manager.beginTransaction()
                        .add(R.id.trebelloFragmentContainer, fragment)
                        .commit();
            }
        } else if (getIntent().getAction() == "EditPlayers"){
            if (fragment == null) {
                fragment = new PlayerFragment();
                manager.beginTransaction()
                        .add(R.id.trebelloFragmentContainer, fragment)
                        .commit();
            }
        }

    }


}
