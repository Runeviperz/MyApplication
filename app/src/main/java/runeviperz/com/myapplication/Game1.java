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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import org.w3c.dom.Text;

import java.util.Random;


public class Game1 extends AppCompatActivity implements View.OnClickListener {
    int randnum;
    TextView tvHighscore, tvCurrentRoll, tvTotalRolls;
    TextView tvMostRolls, tvLeastRolls, tvRNGcoins;
    EditText etBetAmount, etBetRolls;
    Button bRoll, bRestart, bGame1Back, bLockIn;
    UserLocalStore userLocalStore;
    Handler mHandler;
    boolean isRolling;
    int rolls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        tvTotalRolls = (TextView) findViewById(R.id.tvTotalRolls);
        tvCurrentRoll = (TextView) findViewById(R.id.tvCurrentRoll);
        bRoll = (Button) findViewById(R.id.bRoll);
        bRestart = (Button) findViewById(R.id.bRestart);
        bGame1Back = (Button) findViewById(R.id.bGame1Back);
        etBetAmount = (EditText) findViewById(R.id.etBetAmount);
        etBetRolls = (EditText) findViewById(R.id.etBetRolls);
        tvRNGcoins = (TextView) findViewById(R.id.tvRNGcoins);

        userLocalStore = new UserLocalStore(this);

        rolls = userLocalStore.getTempTotalRolls();

        bRoll.setOnClickListener(this);
        bRestart.setOnClickListener(this);
        bGame1Back.setOnClickListener(this);
        etBetAmount.setOnClickListener(this);
        etBetRolls.setOnClickListener(this);

        userLocalStore.checkFirstLaunch();

        tvTotalRolls.setText("" + rolls);
        updateCoinsText();

        isRolling = false;

        if (userLocalStore.getHighScore() == 100) {
            bRoll.setEnabled(false);
        }


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bRoll:
                if (checkBetAmount()) {
                    isRolling = true;
                    etBetAmount.setEnabled(false);
                    etBetRolls.setEnabled(false);
                    bRoll.setEnabled(false);
                    bRestart.setEnabled(false);
                    bGame1Back.setEnabled(false);
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 0);
                }
                break;
            case R.id.bRestart:
                etBetAmount.setEnabled(true);
                etBetRolls.setEnabled(true);
                restart();
                break;
            case R.id.bGame1Back:
                finish();
                break;
        }
    }
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        switch(v.getId()){
//            case R.id.bRoll:
//                if (MotionEvent.ACTION_DOWN == event.getAction()) {
//                    roll();
//                    if (randnum == 100) {
//                        break;
//                    }
//                    mHandler = new Handler();
//                    mHandler.postDelayed(mAction, 500);
//                    break;
//                } else if (MotionEvent.ACTION_UP == event.getAction()) {
//                    mHandler.removeCallbacks(mAction);
//                    mHandler = null;
//                    break;
//                }
//                break;
//            case R.id.bRestart:
//                if (MotionEvent.ACTION_DOWN == event.getAction()) {
//                    restart();
//                }
//                break;
//            case R.id.bGame1Back:
//                if (MotionEvent.ACTION_DOWN == event.getAction()) {
//                    finish();
//                }
//                break;
//        }
//        return true;
//    }

    Runnable mAction = new Runnable() {
        @Override public void run() {
            roll();
            if (!(randnum == 100)) {
                mHandler.postDelayed(this, 50);
            } else {
                bRestart.setEnabled(true);
                bGame1Back.setEnabled(true);
                isRolling = false;
            }
        }
    };

    @Override
    public void onBackPressed() {

    }

    private void roll() {
        rolls++;
        tvTotalRolls.setText(""+rolls);
        userLocalStore.setTempTotalRolls(rolls);
        Random random = new Random();
        randnum = random.nextInt(100)+1;
        tvCurrentRoll.setText(""+randnum);
        if (randnum > userLocalStore.getHighScore()) {
            userLocalStore.setHighScore(randnum);
            if (randnum == 100) {
                bRoll.setEnabled(false);
                if (rolls == 1) {
                    userLocalStore.setWonInExactly1();
                } else if (rolls == 69) {
                    userLocalStore.setWonInExactly69();
                } else if (rolls == 100) {
                    userLocalStore.setWonInExactly100();
                } else if (rolls == 420) {
                    userLocalStore.setWonInExactly420();
                }
                setMostRolls();
                setLeastRolls();
                userLocalStore.updateGameRolls(rolls);
                userLocalStore.updateTotalGames();
                int winnings = calculateBet(etBetAmount.getText().toString(), etBetRolls.getText().toString());
                Toast.makeText(Game1.this, "You've won "+winnings+" coins!", Toast.LENGTH_LONG).show();
                userLocalStore.setTotalCash(userLocalStore.getTotalCash() + winnings);
                updateCoinsText();
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
        bRoll.setEnabled(true);
    }
    public boolean checkBetAmount() {
        try {
            int betAmount = Integer.parseInt(etBetAmount.getText().toString());
            if (userLocalStore.getTotalCash() - betAmount >= 0) {
                userLocalStore.setTotalCash(userLocalStore.getTotalCash() - betAmount);
                Toast.makeText(Game1.this, "Subtracted " + etBetAmount.getText().toString(), Toast.LENGTH_LONG).show();
                updateCoinsText();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    public int calculateBet(String betAmountString, String betRollsString) {
        try {
            int betAmount = Integer.parseInt(betAmountString);
            int betRolls = Integer.parseInt(betRollsString);
            if (Math.abs(rolls-betRolls) >= 50) {
                return 0;
            } else {
                int diff = Math.abs(rolls-betRolls);
                double scale = 50 - diff;
                double multiplier = 0.0045*scale*scale - 0.025*scale;
                double total = multiplier*betAmount;
                return (int)total;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public void updateCoinsText() {
        tvRNGcoins.setText(""+userLocalStore.getTotalCash());
    }

}
