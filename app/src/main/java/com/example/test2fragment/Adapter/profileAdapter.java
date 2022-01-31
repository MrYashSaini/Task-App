package com.example.test2fragment.Adapter;

import android.content.Context;
import android.database.Cursor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test2fragment.Models.profileModel;
import com.example.test2fragment.R;
import com.example.test2fragment.profiledatabase;


import java.util.ArrayList;

public class profileAdapter extends RecyclerView.Adapter<profileAdapter.viewHolder3> {
    ArrayList<profileModel> list;
    Context context;

    public profileAdapter(ArrayList<profileModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profilesample,parent,false);
        return new viewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder3 holder, int position) {
        profiledatabase db = new profiledatabase(context);
        profileModel model = list.get(position);
        holder.imageView.setImageResource(model.getPic());
        holder.textView.setText(model.getText());
        int p = position;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getdata();
                String idnum[] = new String[res.getCount()+1];
                int i=0;
                while(res.moveToNext()) {
                    idnum[i]=res.getString(0);
                    i++;
                }
                String idTXT = idnum[p];
                db.deleteuserdata(idTXT);
                Toast.makeText(v.getContext(), "Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder3 extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public viewHolder3(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.deleteprofiledetele);
            textView = itemView.findViewById(R.id.profiledetel);
        }
    }

}
