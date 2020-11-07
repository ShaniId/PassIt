package com.example.passit1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class PersonalDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail, editTextPassword1, editTextPassword2, editFullName;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        editFullName = (EditText) findViewById(R.id.FullName);
        editTextEmail = (EditText) findViewById(R.id.EmailAddress);
        editTextPassword1 = (EditText) findViewById(R.id.Password);
        editTextPassword2 = (EditText) findViewById(R.id.ConfirmPassword);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.Login).setOnClickListener(this);
    }

    private void registerUser(){
        String fullName = editFullName.getText().toString().trim();
        String password1 = editTextPassword1.getText().toString().trim();
        String password2 = editTextPassword1.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password1.isEmpty()){
            editTextPassword1.setError("Password is required");
            editTextPassword1.requestFocus();
            return;
        }
        if (password1.length() < 6){
            editTextPassword1.setError("Minimum length of password should be 6");
            editTextPassword1.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "User Registered Successfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        registerUser();
    }
}