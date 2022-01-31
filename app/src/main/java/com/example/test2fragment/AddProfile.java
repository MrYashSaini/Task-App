package com.example.test2fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddProfile extends AppCompatActivity {
    private static final String file_name = "profileidnumber.txt";

    Button addtask;
    TextView entrybox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        addtask = findViewById(R.id.addprofilebtn);
        entrybox = findViewById(R.id.profileentrybox);
        profiledatabase db = new profiledatabase(this);
        Intent intent = getIntent();
        String typepf = intent.getStringExtra("typepf");
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = "1";
                int idnum = 0;

                FileInputStream fis = null;
                try {
                    fis = openFileInput(file_name);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;
                    while((text = br.readLine())!= null){
                        sb.append(text);
                    }
                    id = sb.toString();
                    idnum = Integer.parseInt(id)+1;
                    id  = String.valueOf(idnum);
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(fis!=null)
                        try{
                            fis.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                }
// write the next id number
                FileOutputStream fos = null;
                try{
                    fos = openFileOutput(file_name,MODE_PRIVATE);
                    fos.write(id.getBytes());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                finally {
                    if(fos!= null){
                        try{
                            fos.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }

                String task = entrybox.getText().toString();
                db.insertuserdata(id,task,typepf);
                Toast.makeText(AddProfile.this, typepf+id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}