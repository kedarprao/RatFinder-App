package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.HashMap;

public class loginPage extends AppCompatActivity {
    private HashMap validLogins = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final Button loginButton = (Button) findViewById(R.id.LoginButton1);
        Button cancelButton = (Button) findViewById(R.id.CancelButton);
        final TextView feedback = (TextView) findViewById(R.id.feedback);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkLogin()) {
                    feedback.setText("Login Successfull");
                } else {
                    feedback.setText("Login Failed. Please enter a valid username and password.");
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginPage.this, LoginActivity.class));
            }
        });
    }

    public void setAdminLogins() {
        String admin = "admin";
        String adminPW = "password";
        validLogins.put(admin.hashCode(), adminPW);
    }

    public boolean checkLogin() {
        setAdminLogins();
        String username = ((EditText) findViewById(R.id.Username)).getText().toString();
        String password = ((EditText) findViewById(R.id.Password)).getText().toString();
        if (validLogins.get(username.hashCode()) != null) {
            if (validLogins.get(username.hashCode()).equals(password)) {
                return true;
            }
        }
        return false;
    }


}
