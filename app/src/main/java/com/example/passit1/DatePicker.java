package com.example.passit1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DatePicker extends AppCompatActivity {

    private static final String TAG = DatePicker.class.getSimpleName();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button mDatePickerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        mDatePickerBtn = findViewById(R.id.btnDatePicker);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A DATE");
        MaterialDatePicker materialDatePicker = builder.build();

        mDatePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long,Long> selection) {
                Long startDate = selection.first;
                Long endDate = selection.second;
                Date s_date=new Date(startDate);
                Date e_date=new Date(endDate);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                String dateText = df2.format(s_date);
                String dateText2 = df2.format(e_date);
                Map<String, Object> userdate = new HashMap<>();
                userdate.put("start_date", dateText);
                userdate.put("end_date", dateText2);
                db.collection("dates").add(userdate);
                Log.d(TAG, "stat date: " + dateText);
                Log.d(TAG, "end date: " + dateText2);

            }
        });

        findViewById(R.id.btn_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DatePicker.this, ToKnowTheClient.class);
                startActivity(intent);
            }
        });

    }
}