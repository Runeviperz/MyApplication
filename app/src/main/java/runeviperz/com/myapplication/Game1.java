package runeviperz.com.myapplication;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import org.w3c.dom.Text;

import java.util.Random;


public class Game1 extends AppCompatActivity implements View.OnTouchListener {
    int randnum;
    TextView tvHighscore, tvCurrentRoll, tvTotalRolls;
    TextView tvMostRolls, tvLeastRolls;
    Button bRoll, bRestart, bGame1Back, bResetAll;
    UserLocalStore userLocalStore;
    Handler mHandler;
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
//        bResetAll = (Button) findViewById(R.id.bResetAll);

        userLocalStore = new UserLocalStore(this);

        rolls = userLocalStore.getTempTotalRolls();

        bRoll.setOnTouchListener(this);
        bRestart.setOnTouchListener(this);
        bGame1Back.setOnTouchListener(this);
//        bResetAll.setOnClickListener(this);

        tvHighscore.setText("" + userLocalStore.getHighScore());
        tvTotalRolls.setText("" + rolls);
        tvLeastRolls.setText("" + userLocalStore.getLeastRolls());
        tvMostRolls.setText("" + userLocalStore.getMostRolls());

        if (userLocalStore.getHighScore() == 100) {
            bRoll.setEnabled(false);
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(v.getId()){
            case R.id.bRoll:
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    roll();
                    if (randnum == 100) {
                        break;
                    }
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 500);
                    break;
                } else if (MotionEvent.ACTION_UP == event.getAction()) {
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    break;
                }
                break;
            case R.id.bRestart:
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    restart();
                }
                break;
            case R.id.bGame1Back:
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    finish();
                }
                break;
        }
        return true;
    }

//    @Override
//    public void onClick(View view) {
//        switch(view.getId()) {
//            case R.id.bRoll:
//                roll();
//                break;
//            case R.id.bRestart:
//                restart();
//                break;
//            case R.id.bGame1Back:
//                finish();
//                break;
//        }
//    }

    Runnable mAction = new Runnable() {
        @Override public void run() {
            roll();
            if (!(randnum == 100)) {
                mHandler.postDelayed(this, 100);
            }
        }
    };

    private void roll() {
        rolls++;
        tvTotalRolls.setText(""+rolls);
        userLocalStore.setTempTotalRolls(rolls);
        Random random = new Random();
        randnum = random.nextInt(100)+1;
        tvCurrentRoll.setText(""+randnum);
        if (randnum > userLocalStore.getHighScore()) {
            userLocalStore.setHighScore(randnum);
            tvHighscore.setText(""+userLocalStore.getHighScore());
            if (randnum == 100) {
                bRoll.setEnabled(false);
                setMostRolls();
                setLeastRolls();
                userLocalStore.updateGameRolls(rolls);
                userLocalStore.updateTotalGames();
                tvLeastRolls.setText(""+userLocalStore.getLeastRolls());
                tvMostRolls.setText(""+userLocalStore.getMostRolls());
                Toast.makeText(Game1.this, "You've won!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void setMostRolls() {
        if (rolls > userLocalStore.getMostRolls()) {
            userLocalStore.setMostRolls(rolls);
        }
    }
    private void setLeastRolls() {
        if (userLocalStore.getLeastRolls() == 0) {
            userLocalStore.setLeastRolls(rolls);
        } else if (rolls < userLocalStore.getLeastRolls()) {
            userLocalStore.setLeastRolls(rolls);
        }
    }
    private void restart() {
        tvCurrentRoll.setText("0");
        tvTotalRolls.setText("0");
        userLocalStore.setHighScore(0);
        userLocalStore.setTempTotalRolls(0);
        rolls = userLocalStore.getTempTotalRolls();
        tvHighscore.setText(""+userLocalStore.getHighScore());
        bRoll.setEnabled(true);
    }

}
