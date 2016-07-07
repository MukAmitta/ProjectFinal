package com.example.amitta.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Member extends AppCompatActivity {

    private Button btnCalGPA = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("สมาชิก");
        setSupportActionBar(toolbar);

        //กดปุ่มคำนวณ GAP ไปแสดงหน้ากรอกข้อมูลเกรด
        btnCalGPA = (Button)findViewById(R.id.btnCalGPA);
        btnCalGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Calculator.class);
                startActivity(i);
            }
        });
    }

}
