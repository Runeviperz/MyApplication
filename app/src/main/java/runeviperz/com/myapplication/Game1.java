package runeviperz.com.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import org.w3c.dom.Text;

import java.util.Random;


public class Game1 extends ActionBarActivity implements View.OnClickListener {
    TextView tvHighscore, tvCurrentRoll, tvTotalRolls, tvLeastRolls, tvMostRolls;
    Button bRoll, bRestart, bGame1Back, bResetAll;
    UserLocalStore userLocalStore;
    int rolls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        tvTotalRolls = (TextView) findViewById(R.id.tvTotalRolls);
        tvHighscore = (TextView) findViewById(R.id.tvHighscore);
        tvMostRolls = (TextView) findViewById(R.id.tvMostRolls);
        tvLeastRolls = (TextView) findViewById(R.id.tvLeastRolls);
        tvCurrentRoll = (TextView) findViewById(R.id.tvCurrentRoll);
        bRoll = (Button) findViewById(R.id.bRoll);
        bRestart = (Button) findViewById(R.id.bRestart);
        bGame1Back = (Button) findViewById(R.id.bGame1Back);
        bResetAll = (Button) findViewById(R.id.bResetAll);

        userLocalStore = new UserLocalStore(this);

        rolls = userLocalStore.getTotalRolls();

        bRoll.setOnClickListener(this);
        bRestart.setOnClickListener(this);
        bGame1Back.setOnClickListener(this);
        bResetAll.setOnClickListener(this);

        tvHighscore.setText("" + userLocalStore.getHighScore());
        tvTotalRolls.setText("" + rolls);
        tvLeastRolls.setText("" + userLocalStore.getLeastRolls());
        tvMostRolls.setText("" + userLocalStore.getMostRolls());

        if (userLocalStore.getHighScore() == 100) {
            bRoll.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bRoll:
                rolls++;
                tvTotalRolls.setText(""+rolls);
                userLocalStore.setTotalRolls(rolls);
                Random random = new Random();
                int randnum = random.nextInt(101);
                tvCurrentRoll.setText(""+randnum);
                if (randnum > userLocalStore.getHighScore()) {
                    userLocalStore.setHighScore(randnum);
                    tvHighscore.setText(""+userLocalStore.getHighScore());
                    if (randnum == 100) {
                        bRoll.setEnabled(false);
                        if (rolls > userLocalStore.getMostRolls()) {
                            userLocalStore.setMostRolls(rolls);
                        }
                        if (userLocalStore.getLeastRolls() == 0) {
                            userLocalStore.setLeastRolls(rolls);
                        } else if (rolls < userLocalStore.getLeastRolls()) {
                            userLocalStore.setLeastRolls(rolls);
                        }
                        tvLeastRolls.setText(""+userLocalStore.getLeastRolls());
                        tvMostRolls.setText(""+userLocalStore.getMostRolls());
                        Toast.makeText(Game1.this, "You've won!", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.bRestart:
                restart();
                break;
            case R.id.bGame1Back:
                finish();
                break;
            case R.id.bResetAll:
                // Insert alertdialog to confirm
                showAlert();
                break;
        }
    }
    private void restart() {
        tvCurrentRoll.setText("0");
        tvTotalRolls.setText("0");
        userLocalStore.setHighScore(0);
        userLocalStore.setTotalRolls(0);
        rolls = 0;
        tvHighscore.setText(""+userLocalStore.getHighScore());
        bRoll.setEnabled(true);
    }

    private void showAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Are you sure you want to reset");
        alertDialog.setMessage("You will reset all scores, including lowest rolls and highest rolls.");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
                userLocalStore.resetAllScores();
                tvLeastRolls.setText(""+userLocalStore.getLeastRolls());
                tvMostRolls.setText(""+userLocalStore.getMostRolls());
                restart();
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
