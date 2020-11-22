package com.example.passit1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ToKnowTheClientMenu extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String sport;
    String cooking;
    String travel;
    String business;
    String courses;
    String technology;
    String music;
    String diy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_know_the_client_menu);
        CheckBox sport_checkbox = (CheckBox) findViewById(R.id.checkBoxSport);
        CheckBox cooking_checkbox = (CheckBox) findViewById(R.id.checkBoxCooking);
        CheckBox travel_checkbox = (CheckBox) findViewById(R.id.checkBoxTravel);
        CheckBox business_checkbox = (CheckBox) findViewById(R.id.checkBoxBusinesses);
        CheckBox courses_checkbox = (CheckBox) findViewById(R.id.checkBoxCourses);
        CheckBox technology_checkbox = (CheckBox) findViewById(R.id.checkBoxTechnology);
        CheckBox music_checkbox = (CheckBox) findViewById(R.id.checkBoxMusic);
        CheckBox diy_checkbox = (CheckBox) findViewById(R.id.checkBoxDIY);

        //Link to Day 1 page
        findViewById(R.id.Next123).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (sport_checkbox.isChecked()) {
                    sport="yes";
                }else{
                    sport="no";
                }
                if (cooking_checkbox.isChecked()) {
                    cooking="yes";
                }else{
                    cooking="no";
                }
                if (travel_checkbox.isChecked()) {
                    travel="yes";
                }else{
                    travel="no";
                }
                if (business_checkbox.isChecked()) {
                    business="yes";
                }else{
                    business="no";
                }
                if (courses_checkbox.isChecked()) {
                    courses="yes";
                }else{
                    courses="no";
                }
                if (technology_checkbox.isChecked()) {
                    technology="yes";
                }else{
                    technology="no";
                }
                if (music_checkbox.isChecked()) {
                    music="yes";
                }else{
                    music="no";
                }
                if (diy_checkbox.isChecked()) {
                    diy="yes";
                }else{
                    diy="no";
                }
                Map<String, Object> userIntrest = new HashMap<>();
                userIntrest.put("Sport", sport);
                userIntrest.put("Cooking", cooking);
                userIntrest.put("Travel", travel);
                userIntrest.put("Businesses", business);
                userIntrest.put("Courses", courses);
                userIntrest.put("Technology", technology);
                userIntrest.put("Music", music);
                userIntrest.put("DIY", diy);
                db.collection("userIntrest").add(userIntrest);
                Intent intent = new Intent(ToKnowTheClientMenu.this, Day_1.class);
                startActivity(intent);
            }
        });

    }
}