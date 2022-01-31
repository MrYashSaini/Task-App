package com.example.test2fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;




public class ProfileFragment extends Fragment {
    private static final String file_name = "namesave.txt";
    TextView nametextview;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView button = v.findViewById(R.id.addskill);
        ImageView button2 = v.findViewById(R.id.addstrength);
        ImageView button3 = v.findViewById(R.id.addweeknes);
        TextView textView1 = v.findViewById(R.id.showskill);
        TextView textView2 = v.findViewById(R.id.showstrength);
        TextView textView3 = v.findViewById(R.id.showweeknes);
        TextView textView4 = v.findViewById(R.id.showarchievedgoals);
        nametextview = v.findViewById(R.id.nametextView);
        Bundle bundle = this.getArguments();
        String nameget = bundle.getString("name");
        try {
            nametextview.setText(nameget);
        }
        catch (Exception e){
            nametextview.setText("Add Name");
        }


        nametextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),showprofileiteams.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddProfile.class);
                intent.putExtra("typepf","1");
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddProfile.class);
                intent.putExtra("typepf","2");
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddProfile.class);
                intent.putExtra("typepf","3");
                startActivity(intent);
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("typepf","skill");
                ProfileDetails profileFragment = new ProfileDetails();
                profileFragment.setArguments(bundle);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fortest, profileFragment);
                transaction.commit();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("typepf","str");
                ProfileDetails profileFragment = new ProfileDetails();
                profileFragment.setArguments(bundle);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fortest, profileFragment);
                transaction.commit();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("typepf","week");
                ProfileDetails profileFragment = new ProfileDetails();
                profileFragment.setArguments(bundle);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fortest, profileFragment);
                transaction.commit();
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("typepf","ca");
                ProfileDetails profileFragment = new ProfileDetails();
                profileFragment.setArguments(bundle);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fortest, profileFragment);
                transaction.commit();
            }
        });

        return v;
    }
}