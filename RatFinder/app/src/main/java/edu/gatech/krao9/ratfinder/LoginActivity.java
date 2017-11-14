package edu.gatech.krao9.ratfinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashMap;

import models.Client;

public class LoginActivity extends AppCompatActivity {
    private HashMap validLogins = new HashMap<>();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        final Button loginButton = (Button) findViewById(R.id.LoginButton1);
        Button cancelButton = (Button) findViewById(R.id.CancelButton);
        final TextView feedback = (TextView) findViewById(R.id.feedback);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    startActivity(new Intent(LoginActivity.this, HomeScreen.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
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
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, HomeScreen.class));
        }
    }

    /**
     * check user login creditals
     */
    public void checkLogin() {
        String username = ((EditText) findViewById(R.id.Username)).getText().toString();
        String password = ((EditText) findViewById(R.id.Password)).getText().toString();

        Client client = new Client();
        client.checkLogin(username, password);
//        final TextView feedback = (TextView) findViewById(R.id.feedback);
//        mAuth.signInWithEmailAndPassword(username, password)
//            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d("LOG IN", "signInWithEmail:success");
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        startActivity(new Intent(LoginActivity.this, HomeScreen.class));
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w("LOGIN", "signInWithEmail:failure", task.getException());
//                        Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show();
//                        feedback.setText("FAIL!");
//                    }
//
//                    // ...
//                }
//            });


    }
}
