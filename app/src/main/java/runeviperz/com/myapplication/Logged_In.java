package runeviperz.com.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Logged_In extends ActionBarActivity implements View.OnClickListener {

    Button bBack, bGame1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged__in);

        bBack = (Button) findViewById(R.id.bBack);
        bGame1 = (Button) findViewById(R.id.bGame1);

        bBack.setOnClickListener(this);
        bGame1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bBack:
                finish();
                break;
            case R.id.bGame1:
                startActivity(new Intent(this, Game1.class));
                break;
        }
    }
}
