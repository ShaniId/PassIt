package com.example.passit1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PersonalDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseFirestore db;
    private static final String TAG = PersonalDetailsActivity.class.getSimpleName();

    EditText editTextEmail, editTextPassword1, editTextPassword2, editFullName;
    String gender = "";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name

        //Link to Welcome Back page
        findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PersonalDetailsActivity.this, ToKnowTheClient.class);
                startActivity(intent);
            }
        });

        editFullName = (EditText) findViewById(R.id.FullName);
        editTextEmail = (EditText) findViewById(R.id.EmailAddress);
        editTextPassword1 = (EditText) findViewById(R.id.Password);
        editTextPassword2 = (EditText) findViewById(R.id.ConfirmPassword);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.Login).setOnClickListener(this);
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBoxFemale:
                if (checked)
                   gender = "Female";
            else
                gender = "";
                break;
            case R.id.checkBoxMale:
                if (checked)
                gender = "Male";
            else
                gender = "";
                break;
        }
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

        if (password1.isEmpty() || password2.isEmpty()){
            editTextPassword1.setError("Password is required");
            editTextPassword1.requestFocus();
            return;
        }
        if (!password1.equals(password2)){
            editTextPassword1.setError("Password and confirmed password arn't equal");
            editTextPassword1.requestFocus();
        }
        if (password1.length() < 6){
            editTextPassword1.setError("Minimum length of password should be 6");
            editTextPassword1.requestFocus();
            return;
        }

        Map<String, Object> user = new HashMap<>();
        user.put("FullName", fullName);
        user.put("Email", email);
        user.put("Password", password1);
        user.put("Gender", gender);

        mAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "User Registered Successful", Toast.LENGTH_SHORT).show();
                    // Add a new document with a generated ID
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });

                }
                else{
                    Toast.makeText(getApplicationContext(), "Email already exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        registerUser();
    }
}