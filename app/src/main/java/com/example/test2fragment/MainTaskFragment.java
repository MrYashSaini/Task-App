package com.example.test2fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.test2fragment.Adapter.mainRecipeAdapter;
import com.example.test2fragment.Models.mainRecipeModel;

import java.util.ArrayList;

public class MainTaskFragment extends Fragment {
    RecyclerView recyclerView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MainTaskFragment() {
    }

    public static MainTaskFragment newInstance(String param1, String param2) {
        MainTaskFragment fragment = new MainTaskFragment();
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
        View v = inflater.inflate(R.layout.fragment_main_task, container, false);
        Button button = v.findViewById(R.id.button3);
        goaldatabasemenu db = new goaldatabasemenu(getContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddMainTask.class);
                startActivity(intent);
            }
        });
        recyclerView = v.findViewById(R.id.mainrecyclerview);
        ArrayList<mainRecipeModel> list = new ArrayList<>();

        mainRecipeAdapter adapter = new mainRecipeAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
// Get data from database
        Cursor res = db.getdata();
        while(res.moveToNext()){
            list.add(new mainRecipeModel(R.drawable.pen,R.drawable.dustbin,R.drawable.plus,res.getString(1)));
        }
        return v;
    }

}