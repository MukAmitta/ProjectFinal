package com.example.amitta.androidproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MemberForm extends AppCompatActivity {

    private Button   btnSave     = null;
    private EditText edtName     = null;
    private Spinner  spnFaculty  = null;
    private Spinner  spnYear     = null;
    private Spinner  spnTerm     = null;
    private EditText edtPassword = null;
    private EditText edtPassword1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("สมัครสมาชิก");
        setSupportActionBar(toolbar);

        edtName = (EditText) findViewById(R.id.edtName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword1 = (EditText) findViewById(R.id.edtPassword1);

        //ส่วนที่เก็บค่าของสาขา
        spnFaculty = (Spinner) findViewById(R.id.spnFaculty);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Faculty_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFaculty.setAdapter(adapter);

        //ส่วนที่เก็บค่าของชั้นปี
        spnYear = (Spinner) findViewById(R.id.spnYear);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.Year_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnYear.setAdapter(adapter);

        //ส่วนที่เก็บค่าของเทอม
        spnTerm= (Spinner) findViewById(R.id.spnTerm);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.Term_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTerm.setAdapter(adapter);

        //ส่วนที่เกี่ยวกับปุ่มบันทึก
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String Faculty = spnFaculty.getSelectedItem().toString();
                String Year = spnYear.getSelectedItem().toString();
                String Term = spnTerm.getSelectedItem().toString();
                String Name = edtName.getText().toString(); Name = Name.trim(); //ตัดข้างหน้ากับข้างหลังของชื่อ ยกเว้นตรงกลาง หรือ อาจจะเขียนสั้นๆ Name.trim().equals
                String Password = edtPassword.getText().toString();
                String Password1 = edtPassword1.getText().toString();

                //ตรวจสอบการป้อนค่าชื่อ - นามสกุล
                if (Name == null || Name.equals("") ) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MemberForm.this).create();
                    alertDialog.setMessage(getResources().getString(R.string.check_name));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ตกลง",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    //alertDialog.setTitle("");
                                }
                            });
                    alertDialog.show();
                }

                //ตรวจสอบการป้อนค่าของสาขา
                else if (Faculty == null || Faculty.equals("สาขา")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MemberForm.this).create();
                    alertDialog.setMessage(getResources().getString(R.string.check_faculty));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ตกลง",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                //ตรวจสอบการป้อนค่าของชั้นปี
                else if (Year == null || Year.equals("ชั้นปี")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MemberForm.this).create();
                    alertDialog.setMessage(getResources().getString(R.string.check_year));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ตกลง",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                //ตรวจสอบการป้อนค่าของเทอม
                else if (Term == null || Term.equals("ภาคการศึกษา/เทอม")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MemberForm.this).create();
                    alertDialog.setMessage(getResources().getString(R.string.check_term));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ตกลง",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                //ตรวจสอบการป้อนรหัสผ่าน
                else if ( Password == null || Password.length() < 4) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MemberForm.this).create();
                    alertDialog.setMessage(getResources().getString(R.string.check_password));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ตกลง",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    //alertDialog.setTitle("");
                                }
                            });
                    alertDialog.show();
                }

                //ตรวจสอบการป้อนรหัสผ่านอีกครั้ง
                else if ( Password1 == null || !Password1.equals(Password) || Password.length() < 4 ) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MemberForm.this).create();
                    alertDialog.setMessage(getResources().getString(R.string.check_password1));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ตกลง",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.show();
                }

                //หากไม่เข้ากรณีใด ให้บันทึกได้
                else {
                    Intent i = new Intent(getApplicationContext(), Member.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

        //ส่วนที่เกียวกับปุ่มกลับ
        public void onBacktoMainMenu (View view){
        finish();
        }
    }



