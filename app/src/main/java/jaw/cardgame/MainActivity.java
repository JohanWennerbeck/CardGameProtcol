package jaw.cardgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button trebelloGame = findViewById(R.id.trebelloButton);
        trebelloGame.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
            intent.setAction("Game");
            startActivity(intent);
        });

        Button trebelloStatistics = findViewById(R.id.statistics);
        trebelloStatistics.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
            intent.setAction("Statistics");
            startActivity(intent);
        });

        Button editPlayers = findViewById(R.id.edit_players);
        editPlayers.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
            intent.setAction("EditPlayers");
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
