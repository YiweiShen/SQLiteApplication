package com.example.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {
    DBHelper DB;
    List<Student> studentList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        DB = new DBHelper(this);
        Cursor res = DB.getdata();

        studentList = new ArrayList<>();
        listView = findViewById(R.id.ListView);

        while(res.moveToNext()){
            Student student = new Student(res.getString(0),res.getString(1),res.getString(2));
            studentList.add(student);
        }
        StudentAdapter studentAdapter = new StudentAdapter(this, R.layout.layout_student_info, studentList);
        listView.setAdapter(studentAdapter);
    }
}