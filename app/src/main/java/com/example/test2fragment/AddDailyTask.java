package com.example.test2fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test2fragment.Models.RecipeModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class    AddDailyTask extends AppCompatActivity {
    private static final String file_name = "idnumber.txt";
    Button addtask;
    EditText entrybox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_daily_task);
        getSupportActionBar().setTitle("Add Work");
        addtask = findViewById(R.id.adddailytaskbutton);
        entrybox = findViewById(R.id.dailytaskentrybox);
        databasemenu db = new databasemenu(this);
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entrybox.getText().toString()!=null){
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

                    Toast.makeText(AddDailyTask.this, "id incremnet"+id, Toast.LENGTH_SHORT).show();
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
                db.insertuserdata(id,task);
                entrybox.getText().clear();

            }
            }
        });
    }
}