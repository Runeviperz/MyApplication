package runeviperz.com.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Stats extends AppCompatActivity implements View.OnClickListener {

    TextView tvTotalGames, tvLeastRolls, tvMostRolls, tvAverageRolls, tvAchievementsUnlocked;
    Button bStatsBack;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        tvTotalGames = (TextView) findViewById(R.id.tvTotalGames);
        tvLeastRolls = (TextView) findViewById(R.id.tvLeastRolls);
        tvMostRolls = (TextView) findViewById(R.id.tvMostRolls);
        tvAverageRolls = (TextView) findViewById(R.id.tvAverageRolls);
        tvAchievementsUnlocked = (TextView) findViewById(R.id.tvAchievementsUnlocked);
        bStatsBack = (Button) findViewById(R.id.bStatsBack);

        userLocalStore = new UserLocalStore(this);

        tvTotalGames.setText(""+userLocalStore.getTotalGames());
        tvLeastRolls.setText(""+userLocalStore.getLeastRolls());
        tvMostRolls.setText(""+userLocalStore.getMostRolls());
        tvAverageRolls.setText(calculateAverageRolls());
        tvAchievementsUnlocked.setText("");

        bStatsBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bStatsBack:
                finish();
                startActivity(new Intent(this, Logged_In.class));
                break;
        }
    }

    private String calculateAverageRolls() {
        int totalRolls = userLocalStore.getTotalGameRolls();
        int totalGames = userLocalStore.getTotalGames();
        if (totalGames == 0) {
            return "0";
        } else {
            double avg = (double)totalRolls/(double)totalGames;
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(avg);
        }
    }
}
