package runeviperz.com.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Stats extends AppCompatActivity implements View.OnClickListener {

    TextView tvTotalGames, tvLeastRolls, tvMostRolls, tvAverageRolls, tvAchievementsUnlocked;
    Button bStatsBack, bResetAll;
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
        bResetAll = (Button) findViewById(R.id.bResetAll);

        userLocalStore = new UserLocalStore(this);

        tvTotalGames.setText(""+userLocalStore.getTotalGames());
        tvLeastRolls.setText(""+userLocalStore.getLeastRolls());
        tvMostRolls.setText(""+userLocalStore.getMostRolls());
        tvAverageRolls.setText(calculateAverageRolls());
        tvAchievementsUnlocked.setText("");

        bResetAll.setOnClickListener(this);
        bStatsBack.setOnClickListener(this);

        updateNumbers();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bStatsBack:
                finish();
                startActivity(new Intent(this, Logged_In.class));
                break;
            case R.id.bResetAll:
                showAlert();
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

    private void updateNumbers() {
        tvTotalGames.setText(""+userLocalStore.getTotalGames());
        tvLeastRolls.setText(""+userLocalStore.getLeastRolls());
        tvMostRolls.setText(""+userLocalStore.getMostRolls());
        tvAverageRolls.setText(calculateAverageRolls());
        tvAchievementsUnlocked.setText("");
    }

        private void showAlert() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Are you sure you want to reset");
            alertDialog.setMessage("You will reset all scores, including ABSOLUTELY EVERYTHING");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // continue with delete
                    userLocalStore.resetAllScores();
                    updateNumbers();
                }
            });
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing

                }
            });
        alertDialog.show();
    }
}
