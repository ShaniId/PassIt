package com.example.passit1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class WelcomeBack extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText Email, Password;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_back);
        mAuth = FirebaseAuth.getInstance();

        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.WelcomeBackPassword);
//        signIn(Email.getText().toString(),Password.getText().toString());
        findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                signIn(Email.getText().toString(),Password.getText().toString());
//               Intent intent = new Intent(MainActivity.this, PersonalDetailsActivity.class);
//               startActivity(intent);
            }
        });

    }
    public void signIn(String email, String password) {
        Log.d(TAG, "email & pass : " + email + " " + password);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d(TAG, user.getEmail());
//                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
//                    updateUI(null);
                    // ...
                }

                // ...
            }
        });
    }

}