package runeviperz.com.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Game1 extends ActionBarActivity implements View.OnClickListener {
    TextView tvHighscore, tvCurrentRoll, tvTotalRolls;
    Button bRoll, bReset, bGame1Back;
    UserLocalStore userLocalStore;
    int rolls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        tvTotalRolls = (TextView) findViewById(R.id.tvTotalRolls);
        tvHighscore = (TextView) findViewById(R.id.tvHighscore);
        tvCurrentRoll = (TextView) findViewById(R.id.tvCurrentRoll);
        bRoll = (Button) findViewById(R.id.bRoll);
        bReset = (Button) findViewById(R.id.bReset);
        bGame1Back = (Button) findViewById(R.id.bGame1Back);
        userLocalStore = new UserLocalStore(this);
        rolls = userLocalStore.getTotalRolls();

        bRoll.setOnClickListener(this);
        bReset.setOnClickListener(this);
        bGame1Back.setOnClickListener(this);

        tvHighscore.setText("" + userLocalStore.getHighScore());
        tvTotalRolls.setText(""+rolls);
        if (userLocalStore.getHighScore() == 100) {
            bRoll.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bRoll:
                Random random = new Random();
                int randnum = random.nextInt(101);
                tvCurrentRoll.setText(""+randnum);
                if (randnum > userLocalStore.getHighScore()) {
                    userLocalStore.setHighScore(randnum);
                    tvHighscore.setText(""+userLocalStore.getHighScore());
                    if (randnum == 100) {
                        bRoll.setEnabled(false);
                        Toast.makeText(Game1.this, "You've won!", Toast.LENGTH_LONG).show();
                    }
                }
                rolls++;
                tvTotalRolls.setText(""+rolls);
                break;
            case R.id.bReset:
                tvCurrentRoll.setText("0");
                tvTotalRolls.setText("0");
                userLocalStore.setHighScore(0);
                userLocalStore.setTotalRolls(0);
                rolls = 0;
                tvHighscore.setText(""+userLocalStore.getHighScore());
                bRoll.setEnabled(true);
                break;
            case R.id.bGame1Back:
                userLocalStore.setTotalRolls(rolls);
                finish();
                break;
        }
    }
}
