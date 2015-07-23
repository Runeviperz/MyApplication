package runeviperz.com.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Logged_In extends AppCompatActivity implements View.OnClickListener {

    Button bLogout, bGame1;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged__in);

        bLogout = (Button) findViewById(R.id.bLogout);
        bGame1 = (Button) findViewById(R.id.bGame1);

        userLocalStore = new UserLocalStore(this);

        bLogout.setOnClickListener(this);
        bGame1.setOnClickListener(this);
    }

//    @Override
//    public void onBackPressed() {
//        finish();
//        startActivity(new Intent(this, MainActivity.class));
//    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bLogout:
                finish();
                userLocalStore.logOut();
                startActivity(new Intent(this, Log_In.class));
                break;
            case R.id.bGame1:
                startActivity(new Intent(this, Game1.class));
                break;
        }
    }
}
