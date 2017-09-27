package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import java.util.Arrays;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
//    private HashMap validLogins = new HashMap<>();
    CallbackManager callbackManager = new CallbackManager.Factory().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = (Button) findViewById(R.id.LoginButton1);
        Button cancelButton = (Button) findViewById(R.id.CancelButton);
        final TextView feedback = (TextView) findViewById(R.id.feedback);
        final LoginButton loginFB = (LoginButton) findViewById(R.id.login_button);

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        feedback.setText("LoginActivity Successful");
                        startActivity(new Intent(LoginActivity.this, HomeScreen.class));
                    }

                    @Override
                    public void onCancel() {
                        feedback.setText("Facebook Login Cancelled. Please enter a valid username and password.");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        feedback.setText("LoginActivity Failed. Please enter a valid username and password.");
                    }
                });

        // Callback registration
        loginFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                feedback.setText("LoginActivity Successful");
                startActivity(new Intent(LoginActivity.this, HomeScreen.class));
            }

            @Override
            public void onCancel() {
                feedback.setText("Facebook Login Cancelled. Please enter a valid username and password.");
            }

            @Override
            public void onError(FacebookException exception) {
                feedback.setText("LoginActivity Failed. Please enter a valid username and password.");
            }
        });

        loginFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
