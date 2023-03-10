package com.example.test2fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String file_name = "namesave.txt";
    Button dailytask,maintask,profile;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailytask = findViewById(R.id.dailytask);
        maintask = findViewById(R.id.maintask);
        profile = findViewById(R.id.profile);
        linearLayout = findViewById(R.id.fortest);

        DailyTaskFragment dailyTaskFragment = new DailyTaskFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fortest,dailyTaskFragment);
        transaction.commit();

//set daily task fragment on main activity
        dailytask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyTaskFragment dailyTaskFragment = new DailyTaskFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fortest,dailyTaskFragment);
                transaction.commit();
                getSupportActionBar().setTitle("Daily Task");
            }
        });
//set main task fragment on main activity
        maintask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainTaskFragment maintaskfragment = new MainTaskFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fortest,maintaskfragment);
                transaction.commit();
                getSupportActionBar().setTitle("Goals");
            }
        });
//set profile fragment on main activity
        profile.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                getSupportActionBar().setTitle("Profile");

                FileInputStream fis = null;
                String namestore = null;
                try {
                    fis = openFileInput(file_name);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;
                    while((text = br.readLine())!= null){
                        sb.append(text).append("\n");
                    }
                    namestore = sb.toString();
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                    namestore = "Add Name";
                } catch (IOException e) {
                    e.printStackTrace();
                    namestore = "Add Name";
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
                String finalNamestore = namestore;
                Bundle bundle = new Bundle();
                bundle.putString("name", finalNamestore);
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fortest, profileFragment);
                transaction.commit();
            }
        });
    }
}