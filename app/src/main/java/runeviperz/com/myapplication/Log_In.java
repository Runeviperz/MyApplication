package runeviperz.com.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;


public class Log_In extends ActionBarActivity implements View.OnClickListener {

    Button bLogin, bRegister, bReset;
    EditText etUsername, etPassword;
    UserLocalStore userLocalStore;
    TextView tvLogin;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bRegister = (Button) findViewById(R.id.bRegister);
        bReset = (Button) findViewById(R.id.bReset);
        tvLogin = (TextView) findViewById(R.id.tvLogin);

        bLogin.setOnClickListener(this);
        bRegister.setOnClickListener(this);
        bReset.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bLogin:
                String username = etUsername.getText().toString().toLowerCase();
                String password = etPassword.getText().toString();

                // User registeredUser = userLocalStore.getRegisteredUser();

                User toLogin = new User(username, password);

                // for local use
//                if (registeredUser.username.equals("")) {
//                    Toast.makeText(Log_In.this, "Please register first", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//
//                if (registeredUser.equals(toLogin)) {
//                    startActivity(new Intent(this, Logged_In.class));
//                } else {
//                    Toast.makeText(Log_In.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
//                }

                authenticate(toLogin);

                break;
            case R.id.bRegister:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.tvLogin:
                count++;
                if (count == 5) {
                    Toast.makeText(Log_In.this, "Cleared UserStore enabled", Toast.LENGTH_SHORT).show();
                    bReset.setEnabled(true);
                }
                break;
            case R.id.bReset:
                userLocalStore.clearUserData();
                Toast.makeText(Log_In.this, "Cleared UserStore", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // for online use
    private void authenticate(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchUserDataAsyncTask(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Log_In.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        startActivity(new Intent(this, Logged_In.class));
    }
}
