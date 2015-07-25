package runeviperz.com.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Achievements extends AppCompatActivity implements View.OnClickListener {

    Button bAchievementsBack;
    UserLocalStore userLocalStore;
    TextView tvWin10Games, tvWin20Games, tvWin50Games, tvWin100Games, tvWin250Games;
    TextView tvWinInExactly1, tvWinInExactly69, tvWinInExactly100, tvWinInExactly420;
    TextView tvWinWithOver500, tvWinWithOver700, tvWinWithOver800, tvWinWithOver900, tvWinWithOver1000, tvWinWithOver9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        tvWin10Games = (TextView) findViewById(R.id.tvWin10Games);
        tvWin20Games = (TextView) findViewById(R.id.tvWin20Games);
        tvWin50Games = (TextView) findViewById(R.id.tvWin50Games);
        tvWin100Games = (TextView) findViewById(R.id.tvWin100Games);
        tvWin250Games = (TextView) findViewById(R.id.tvWin250Games);
        tvWinInExactly1 = (TextView) findViewById(R.id.tvWinInExactly1);
        tvWinInExactly69 = (TextView) findViewById(R.id.tvWinInExactly69);
        tvWinInExactly100 = (TextView) findViewById(R.id.tvWinInExactly100);
        tvWinInExactly420 = (TextView) findViewById(R.id.tvWinInExactly420);
        tvWinWithOver500 = (TextView) findViewById(R.id.tvWinWithOver500);
        tvWinWithOver700 = (TextView) findViewById(R.id.tvWinWithOver700);
        tvWinWithOver800 = (TextView) findViewById(R.id.tvWinWithOver800);
        tvWinWithOver900 = (TextView) findViewById(R.id.tvWinWithOver900);
        tvWinWithOver1000 = (TextView) findViewById(R.id.tvWinWithOver1000);
        tvWinWithOver9000 = (TextView) findViewById(R.id.tvWinWithOver9000);

        bAchievementsBack = (Button) findViewById(R.id.bAchievementsBack);
        userLocalStore = new UserLocalStore(this);

        bAchievementsBack.setOnClickListener(this);

        checkMostRolls(userLocalStore.getMostRolls());
        checkGamesWon(userLocalStore.getTotalGames());
        checkWinInExactly(userLocalStore.getLeastRolls(), userLocalStore.getMostRolls());

        tvWin10Games.setText(booleanToUnicode(userLocalStore.getWon10Games()));
        tvWin20Games.setText(booleanToUnicode(userLocalStore.getWon20Games()));
        tvWin50Games.setText(booleanToUnicode(userLocalStore.getWon50Games()));
        tvWin100Games.setText(booleanToUnicode(userLocalStore.getWon100Games()));
        tvWin250Games.setText(booleanToUnicode(userLocalStore.getWon250Games()));
        tvWinInExactly1.setText(booleanToUnicode(userLocalStore.getWonInExactly1()));
        tvWinInExactly69.setText(booleanToUnicode(userLocalStore.getWonInExactly69()));
        tvWinInExactly100.setText(booleanToUnicode(userLocalStore.getWonInExactly100()));
        tvWinInExactly420.setText(booleanToUnicode(userLocalStore.getWonInExactly420()));
        tvWinWithOver500.setText(booleanToUnicode(userLocalStore.getWonInOver500()));
        tvWinWithOver700.setText(booleanToUnicode(userLocalStore.getWonInOver700()));
        tvWinWithOver800.setText(booleanToUnicode(userLocalStore.getWonInOver800()));
        tvWinWithOver900.setText(booleanToUnicode(userLocalStore.getWonInOver900()));
        tvWinWithOver1000.setText(booleanToUnicode(userLocalStore.getWonInOver1000()));
        tvWinWithOver9000.setText(booleanToUnicode(userLocalStore.getWonInOver9000()));

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bAchievementsBack:
                finish();
                break;
        }
    }

    private void checkMostRolls(int mostRolls) {
        if (mostRolls >= 9000) {
            userLocalStore.setWonInOver9000();
            userLocalStore.setWonInOver1000();
            userLocalStore.setWonInOver900();
            userLocalStore.setWonInOver800();
            userLocalStore.setWonInOver700();
            userLocalStore.setWonInOver500();
        } else if (mostRolls >= 1000) {
            userLocalStore.setWonInOver1000();
            userLocalStore.setWonInOver900();
            userLocalStore.setWonInOver800();
            userLocalStore.setWonInOver700();
            userLocalStore.setWonInOver500();
        } else if (mostRolls >= 900) {
            userLocalStore.setWonInOver900();
            userLocalStore.setWonInOver800();
            userLocalStore.setWonInOver700();
            userLocalStore.setWonInOver500();
        } else if (mostRolls >= 800) {
            userLocalStore.setWonInOver800();
            userLocalStore.setWonInOver700();
            userLocalStore.setWonInOver500();
        } else if (mostRolls >= 700) {
            userLocalStore.setWonInOver700();
            userLocalStore.setWonInOver500();
        } else if (mostRolls >= 500) {
            userLocalStore.setWonInOver500();
        }
    }

    private void checkGamesWon(int won) {
        if (won >= 250) {
            userLocalStore.setWon250Games();
            userLocalStore.setWon100Games();
            userLocalStore.setWon50Games();
            userLocalStore.setWon20Games();
            userLocalStore.setWon10Games();
        } else if (won >= 100) {
            userLocalStore.setWon100Games();
            userLocalStore.setWon50Games();
            userLocalStore.setWon20Games();
            userLocalStore.setWon10Games();
        } else if (won >= 50) {
            userLocalStore.setWon50Games();
            userLocalStore.setWon20Games();
            userLocalStore.setWon10Games();
        } else if (won >= 20) {
            userLocalStore.setWon20Games();
            userLocalStore.setWon10Games();
        } else if (won >= 10) {
            userLocalStore.setWon10Games();
        }
    }

    private void checkWinInExactly(int leastRolls, int mostRolls) {
        if (leastRolls == 1 || mostRolls == 1) {
            userLocalStore.setWonInExactly1();
        }
        if (leastRolls == 69 || mostRolls == 69) {
            userLocalStore.setWonInExactly69();
        }
        if (leastRolls == 100 || mostRolls == 100) {
            userLocalStore.setWonInExactly100();
        }
        if (leastRolls == 420 || mostRolls == 420) {
            userLocalStore.setWonInExactly420();
        }
    }

    private String booleanToUnicode(boolean string) {
        if (string) {
            return "\u2713";
        } else {
            return "\u2717";
        }
    }
}
