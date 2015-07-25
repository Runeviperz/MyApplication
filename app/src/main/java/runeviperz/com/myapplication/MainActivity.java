package runeviperz.com.myapplication;
// Hi
import android.app.AlertDialog;
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

    String version = "1.26";
    Button bBegin, bReset;
    TextView tvTitle;
    int count = 0;
    User user;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bBegin = (Button) findViewById(R.id.bBegin);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        bReset = (Button) findViewById(R.id.bReset);
        user = new User("appversion", version);

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
                authenticate(user);
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

    // Authenticate user
    private void authenticate(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchUserDataAsyncTask(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    Toast.makeText(MainActivity.this, "There is a newer version available. \nOr I can't connect to the database :/", Toast.LENGTH_SHORT).show();
                    logUserIn(returnedUser);
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void logUserIn(User returnedUser) {
        startActivity(new Intent(this, Logged_In.class));
    }
}
