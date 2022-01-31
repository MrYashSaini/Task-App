package com.example.test2fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editDailyActivity extends AppCompatActivity {
    Button editbutton;
    EditText editbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_daily);
        editbox = findViewById(R.id.editentrybox);
        editbutton = findViewById(R.id.editdailybtn);

        Intent intent = getIntent();
        String id_Index = intent.getStringExtra("User_Value");

        int p = Integer.parseInt(id_Index);
        databasemenu db = new databasemenu(editDailyActivity.this);
        Cursor res = db.getdata();
        String numar[]=new String[res.getCount()+10];
        int i=0;
        while(res.moveToNext()) {
            numar[i]=res.getString(0);
            i++;
        }
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idnum = numar[p];
                String task = editbox.getText().toString();
                db.updateuserdata(idnum,task);
                }
        });
         }
}