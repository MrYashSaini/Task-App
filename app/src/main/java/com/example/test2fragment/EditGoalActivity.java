package com.example.test2fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class EditGoalActivity extends AppCompatActivity {
    Button editbutton;
    EditText editbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goal);
        editbox = findViewById(R.id.editgoalentrybox);
        editbutton = findViewById(R.id.editgoalbtn);
        getSupportActionBar().setTitle("Edit Goal");
        Intent intent = getIntent();
        String id_Index = intent.getStringExtra("User_Value");

        int p = Integer.parseInt(id_Index);
        goaldatabasemenu db = new goaldatabasemenu(EditGoalActivity.this);

        Cursor res = db.getdata();
        String numar[]=new String[res.getCount()];
        int i=0;
        while(res.moveToNext()) {
            numar[i]=res.getString(0);
            i++;
        }
// update database using id
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idnum = numar[p];
                String task = editbox.getText().toString();
                db.updateuserdata(idnum,task);
                editbox.getText().clear();
                }
        });
    }
}