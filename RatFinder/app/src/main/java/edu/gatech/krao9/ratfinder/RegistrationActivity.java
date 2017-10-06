package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {
    private static HashMap validLogins = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        final Button registerButton = (Button) findViewById(R.id.registerButton);
        Button cancelButton = (Button) findViewById(R.id.CancelButton);
        final TextView feedback = (TextView) findViewById(R.id.feedback);

        setAdminLogins();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
                feedback.setText("Sucessfully registered!");
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, StartupActivity.class));
            }
        });
    }

    public void setAdminLogins() {
        String admin = "admin";
        String adminPW = "password";
        validLogins.put(admin.hashCode(), adminPW);
    }

    public void registerNewUser() {
        String username = ((EditText) findViewById(R.id.Username)).getText().toString();
        String password = ((EditText) findViewById(R.id.Password)).getText().toString();
        validLogins.put(username.hashCode(), password);
    }

    public static HashMap getValidLogins() {
        return validLogins;
    }

}
