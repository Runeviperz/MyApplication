package runeviperz.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister, bCancel;
    EditText etName, etUsername, etPassword, etAge;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bRegister = (Button) findViewById(R.id.bRegister);
        bCancel = (Button) findViewById(R.id.bCancel);
        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etAge = (EditText) findViewById(R.id.etAge);

        bRegister.setOnClickListener(this);
        bCancel.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bRegister:
                String name = etName.getText().toString();
                String username = etUsername.getText().toString().toLowerCase();
                String password = etPassword.getText().toString();

                if (name.equals("") || username.equals("") || password.equals("") || etAge.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "Fields missing", Toast.LENGTH_SHORT).show();
                    break;
                }

                int age = Integer.parseInt(etAge.getText().toString());

                User registeredData = new User(name, age, username, password);

                // userLocalStore.storeUserData(registeredData);

                // Toast.makeText(Register.this, "Success!", Toast.LENGTH_SHORT).show();

                // sends data to server
                registerUser(registeredData);
                // finish();
                break;
            case R.id.bCancel:
                finish();
                break;
        }
    }

    // sends data to server
    private void registerUser(User user) {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                finish();
                startActivity(new Intent(Register.this, Log_In.class));
            }
        });
    }
}
