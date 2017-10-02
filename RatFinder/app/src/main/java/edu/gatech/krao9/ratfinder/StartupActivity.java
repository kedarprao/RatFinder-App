package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        Button toLoginActivity = (Button) findViewById(R.id.LoginButton);
        Button toRegistrationActivity = (Button) findViewById(R.id.RegisterButton);

        toLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartupActivity.this, LoginActivity.class));
            }
        });
        toRegistrationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartupActivity.this, RegistrationActivity.class));
            }
        });
    }
}
