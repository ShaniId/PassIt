package com.example.passit1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ToKnowTheClient extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String isWorking;
    String jobDescription;
    String isDoingSport;
    String isStayingAlone;
    String favoriteSport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_know_the_client);
        findViewById(R.id.yesWork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWorking = "yes";
            }
        });
        findViewById(R.id.noWork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWorking = "no";
            }
        });
        findViewById(R.id.yesSport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDoingSport = "yes";
            }
        });
        findViewById(R.id.noSport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDoingSport = "no";
            }
        });
        findViewById(R.id.yesHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStayingAlone = "yes";
            }
        });
        findViewById(R.id.noHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStayingAlone = "no";
            }
        });

        //spinner for 3 questions
        Spinner s =(Spinner) findViewById(R.id.spinner1);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ToKnowTheClient.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                jobDescription = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner s1 =(Spinner) findViewById(R.id.spinner2);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ToKnowTheClient.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                favoriteSport = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Link to ToKnowTheClientMenu page
        findViewById(R.id.Next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Map<String, Object> userActivities = new HashMap<>();
                userActivities.put("isWorking", isWorking);
                userActivities.put("isDoingSport", isDoingSport);
                userActivities.put("jobDescription", jobDescription);
                userActivities.put("isStayingAlone", isStayingAlone);
                userActivities.put("favoriteSport", favoriteSport);
                db.collection("userActivities").add(userActivities);
                Intent intent = new Intent(ToKnowTheClient.this, ToKnowTheClientMenu.class);
                startActivity(intent);
            }
        });

    }
}