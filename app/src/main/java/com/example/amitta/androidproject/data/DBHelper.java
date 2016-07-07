package com.example.amitta.androidproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Amitta on 6/7/2559.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GPA_PSU_TRANG.db"; //ชื่อฐานข้อมูลทั้งก้อน
    public static final int DATABASE_VERSION = 1; //กำหนด version เพื่อมีการ Update ภายหลัง

    public static final String SUBJECT_TABLE_NAME = "subject";
    public static final String SUBJECT_COLUMN_CODE = "code_id";
    public static final String SUBJECT_COLUMN_ID = "subject_column_id";
    public static final String SUBJECT_COLUMN_NAME = "name";
    public static final String SUBJECT_COLUMN_CREDIT = "credit";
    public static final String SUBJECT_COLUMN_GRAD = "grade";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);} //เป็นการเขียนเพื่อรองรับในกรณีที่มี parameter หลายตัว

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SUBJECT_TABLE_NAME  + "( " + SUBJECT_COLUMN_CODE + " integer primary key autoincrement," + //กำหนดการรับค่าของแต่ละ Column
                SUBJECT_COLUMN_ID  + " text," +
                SUBJECT_COLUMN_NAME+ " text," +
                SUBJECT_COLUMN_CREDIT + " integer," +
                SUBJECT_COLUMN_GRAD + " double,"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS subject");
        onCreate(db);
    }

    public boolean insertSubject(String subject, String code_id, String subject_column_id, String name, String credit, String grade) { //เก็บค่าที่รับมา
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("code_id", code_id);
        contentValues.put("name", name);
        contentValues.put("subject_column_id", subject_column_id);
        contentValues.put("credit", credit);
        contentValues.put("grade", grade);
        db.insert("subject", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from subject where id= " + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SUBJECT_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(Integer id, String code_id, String subject_column_id, String name, String credit, String grade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("code_id", code_id);
        contentValues.put("name", name);
        contentValues.put("subject_column_id", subject_column_id);
        contentValues.put("credit", credit);
        contentValues.put("grade", grade);
        db.update("subject", contentValues, "id = ?", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("subject", "id=?", new String[]{Integer.toString(id)});
    }


    public ArrayList<String> getAllContacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from subject", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(SUBJECT_COLUMN_NAME)));
            res.moveToNext();

        }
        return array_list;
    }

    public ArrayList<HashMap<String, String>> getAllContactsList() {

        ArrayList<HashMap<String, String>> ContactList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;

        SQLiteDatabase db = this.getReadableDatabase();

        String strSQL = "SELECT * FROM " + SUBJECT_TABLE_NAME;
        Cursor cursor = db.rawQuery(strSQL, null);

        if (cursor.moveToFirst()) {
            do {
                map = new HashMap<String, String>();
                map.put("ID",
                        cursor.getString(cursor.getColumnIndex(SUBJECT_COLUMN_ID)));
                map.put("Name",
                        cursor.getString(cursor.getColumnIndex(SUBJECT_COLUMN_NAME)));
                ContactList.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ContactList;
    }
}
