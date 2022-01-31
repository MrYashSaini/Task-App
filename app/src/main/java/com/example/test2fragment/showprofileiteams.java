package com.example.test2fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class showprofileiteams extends AppCompatActivity {
    private static final String file_name = "namesave.txt";
    EditText entrybox;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showprofileiteams);
        entrybox = findViewById(R.id.nameentry);
        save = findViewById(R.id.namesave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = entrybox.getText().toString();
                FileOutputStream fos = null;
                try{
                    fos = openFileOutput(file_name,MODE_PRIVATE);
                    fos.write(text.getBytes());
                    entrybox.getText().clear();
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
            }
        });


    }
}