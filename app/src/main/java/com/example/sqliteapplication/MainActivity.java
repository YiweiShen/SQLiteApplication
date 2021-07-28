package com.example.sqliteapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, update, delete,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = name.getText().toString();
                String contactTxt = contact.getText().toString();
                String dobTxt = dob.getText().toString();

                Boolean checkinsertData = DB.insertuserdata(nameTxt,contactTxt,dobTxt);

                if(checkinsertData){
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "New Entry NOT Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = name.getText().toString();
                String contactTxt = contact.getText().toString();
                String dobTxt = dob.getText().toString();

                Boolean checkupdate = DB.updateuserdata(nameTxt,contactTxt,dobTxt);

                if(checkupdate){
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Entry not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = name.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdata(nameTxt);

                if(checkdeletedata){
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Entry not Deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Cursor res = DB.getdata();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Nothing existed!", Toast.LENGTH_SHORT).show();

                }
                else {

                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Name: "+res.getString(0)+"\n");
                        buffer.append("Content: "+res.getString(1)+"\n");
                        buffer.append("Date of Birth: "+res.getString(2)+"\n\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Results");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });


    }
}
