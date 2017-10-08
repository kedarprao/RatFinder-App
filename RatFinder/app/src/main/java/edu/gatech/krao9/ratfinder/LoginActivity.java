package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private HashMap validLogins = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = (Button) findViewById(R.id.LoginButton1);
        Button cancelButton = (Button) findViewById(R.id.CancelButton);
        final TextView feedback = (TextView) findViewById(R.id.feedback);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkLogin()) {
                    feedback.setText("LoginActivity Successful");
                    startActivity(new Intent(LoginActivity.this, HomeScreen.class));
                } else {
                    feedback.setText("LoginActivity Failed. Please enter a valid username and password.");
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, StartupActivity.class));
            }
        });
    }
    public boolean checkLogin() {
        String username = ((EditText) findViewById(R.id.Username)).getText().toString();
        String password = ((EditText) findViewById(R.id.Password)).getText().toString();
        if (RegistrationActivity.getValidLogins().get(username.hashCode()) != null) {
            if (RegistrationActivity.getValidLogins().get(username.hashCode()).equals(password)) {
                return true;
            }
        }
        return false;
    }


}
