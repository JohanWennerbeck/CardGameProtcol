package jaw.cardgame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_main);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.scoreFragmentContainer);
        if (fragment == null) {
            fragment = new ScoreFragment();
            manager.beginTransaction()
                    .add(R.id.scoreFragmentContainer, fragment)
                    .commit();
        }
    }
}
