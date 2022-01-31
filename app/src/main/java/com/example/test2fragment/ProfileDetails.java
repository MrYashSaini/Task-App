package com.example.test2fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test2fragment.Adapter.profileAdapter;
import com.example.test2fragment.Models.profileModel;

import java.util.ArrayList;

public class ProfileDetails extends Fragment {
    RecyclerView recyclerView;
    TextView textview;
    Button back;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileDetails() {
    }

    public static ProfileDetails newInstance(String param1, String param2) {
        ProfileDetails fragment = new ProfileDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_details, container, false);
        profiledatabase db = new profiledatabase(getContext());
        Bundle bundle = this.getArguments();
        String data = bundle.getString("typepf");
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
        back = v.findViewById(R.id.backtoprofile);
        textview = v.findViewById(R.id.detailtv);

        recyclerView = v.findViewById(R.id.skillreview2);
        ArrayList<profileModel> list = new ArrayList<>();

        profileAdapter adapter = new profileAdapter(list,getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add this
            }
        });
// Get data from database
        Cursor res = db.getdata();
        if(data.equals("skill")){
            textview.setText("Skills");
            while(res.moveToNext()) {
                String ty = res.getString(2);
                if (ty.equals("1")) {
                    list.add(new profileModel(R.drawable.substract, res.getString(1)));
                }
        }
        }
        else if(data.equals("str")){
            textview.setText("Strength");
            while(res.moveToNext()) {
                String ty = res.getString(2);
                if (ty.equals("2")) {
                    list.add(new profileModel(R.drawable.substract, res.getString(1)));
                }
            }
        }
        else if(data.equals("week")){
            textview.setText("Weakness");
            while(res.moveToNext()) {
                String ty = res.getString(2);
                if (ty.equals("3")) {
                    list.add(new profileModel(R.drawable.substract, res.getString(1)));
                }
            }
        }
        else{
            textview.setText("Archived Goal");
            while(res.moveToNext()) {
                String ty = res.getString(2);
                if (ty.equals("4")) {
                    list.add(new profileModel(R.drawable.substract, res.getString(1)));
                }
            }
        }

        return v;
    }
}