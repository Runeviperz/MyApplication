package runeviperz.com.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Achievements extends AppCompatActivity implements View.OnClickListener {

    Button bAchievementsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        bAchievementsBack = (Button) findViewById(R.id.bAchievementsBack);

        bAchievementsBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bAchievementsBack:
                finish();
                break;
        }
    }
}
