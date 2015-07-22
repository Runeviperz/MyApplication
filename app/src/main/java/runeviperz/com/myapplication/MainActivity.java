package runeviperz.com.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bBegin, bReset;
    TextView tvTitle;
    int count = 0;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bBegin = (Button) findViewById(R.id.bBegin);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        bReset = (Button) findViewById(R.id.bReset);

        bBegin.setOnClickListener(this);
        tvTitle.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

//        public static boolean hostAvailabilityCheck() {
//            try (Socket s = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT)) {
//                return true;
//            } catch (IOException ex) {
//        /* ignore */
//            }
//            return false;
//        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bBegin:
                if (!userLocalStore.getUsername().isEmpty()) {
                Toast.makeText(MainActivity.this, "Welcome back " + userLocalStore.getName(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Logged_In.class));
                } else {
                    startActivity(new Intent(this, Log_In.class));
                }
                break;
            case R.id.tvTitle:
                count++;
                if (count == 5) {
                    count = 0;
                    Toast.makeText(MainActivity.this, "ooh easter egg!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
