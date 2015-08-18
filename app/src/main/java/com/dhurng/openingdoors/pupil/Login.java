package com.dhurng.openingdoors.pupil;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvregisterLink;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvregisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvregisterLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();


                User user = new User(username, password);

                authenticate(user);

                //user logged in and store it
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);

                break;

            case R.id.tvRegisterLink:
                //start activity when clicked
                startActivity(new Intent(this, Register.class));

                break;
        }
    }

    private void authenticate(User user) {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if(returnedUser == null) {
                    showErrorMessage();
                }else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");

        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, MainActivity.class));
    }
}
