package com.example.amitta.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Calculator extends AppCompatActivity {

    private Spinner spnyear = null;
    private Spinner spnterm = null;
    private Button btncalculator = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("คำนวณ GPA สะสม");
        setSupportActionBar(toolbar);

        //เก็บค่าในส่วนของชั้นปี หน้าคำวนณ GPA
        spnyear = (Spinner) findViewById(R.id.spnyear);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.year_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnyear.setAdapter(adapter);

        //เก็บค่าในส่วนของภาคเรียน หน้าคำวนณ GPA
        spnterm = (Spinner) findViewById(R.id.spnterm);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.term_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnterm.setAdapter(adapter);

        //ส่วนที่กดไปหน้า Add วิชา
        btncalculator = (Button)findViewById(R.id.btncalculator);
        btncalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_subject.class);
                startActivity(i);
                finish();
            }
        });





    }
}
