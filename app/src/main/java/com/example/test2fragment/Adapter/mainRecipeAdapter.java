package com.example.test2fragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test2fragment.EditGoalActivity;
import com.example.test2fragment.Models.mainRecipeModel;
import com.example.test2fragment.R;
import com.example.test2fragment.goaldatabasemenu;
import com.example.test2fragment.profiledatabase;

import java.util.ArrayList;
public class mainRecipeAdapter extends RecyclerView.Adapter<mainRecipeAdapter.viewHolder2> {
    ArrayList<mainRecipeModel> list;
    Context context;

    public mainRecipeAdapter(ArrayList<mainRecipeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.goalsamplerecyleview,parent,false);
        return new viewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder2 holder, int position) {
        goaldatabasemenu db = new goaldatabasemenu(context);
        mainRecipeModel model = list.get(position);
        holder.imageView.setImageResource(model.getPic());
        holder.imageView2.setImageResource(model.getPic2());
        holder.imageView3.setImageResource(model.getPic3());
        holder.textView.setText(model.getText());
        int p = position;
// delete data from goal database using position
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// get id in array
                Cursor res = db.getdata();
                String numar[]=new String[res.getCount()+10];
                int i=0;
                while(res.moveToNext()) {
                    numar[i]=res.getString(0);
                    i++;
                }
// get position and remove data
                String idTXT = numar[p];
                db.deleteuserdata(idTXT);
                Toast.makeText(v.getContext(), "Delete Goal", Toast.LENGTH_SHORT).show();

            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_value = String.valueOf(p);

                Intent intent = new Intent(context, EditGoalActivity.class);
                intent.putExtra("User_Value",user_value);
                context.startActivity(intent);
            }
        });
        holder.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profiledatabase dbs = new profiledatabase(v.getContext());
                Cursor res = db.getdata();
                String AcTask = null;

                int i=0;
                while(res.moveToNext()) {
                    if(i==p){
                        AcTask=res.getString(1);
                    }
                    i++;
                }
                String id = "1";
                int ids;
                Cursor gac = dbs.getdata();
                ids = gac.getCount()+1;
                id = String.valueOf(ids);
                String typepfs ="4";
                dbs.insertuserdata(id,AcTask,typepfs);

                Cursor goalres = db.getdata();
                String numar[]=new String[goalres.getCount()+10];
                int j=0;
                while(goalres.moveToNext()) {
                    numar[j]=goalres.getString(0);
                    j++;
                }
// get position and remove data
                String idTXT = numar[p];
                db.deleteuserdata(idTXT);
                Toast.makeText(v.getContext(), "Delete Goal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public void updateData(ArrayList<mainRecipeModel>mainRecipeModels){
        list.clear();
        list.addAll(mainRecipeModels);
        notifyDataSetChanged();
    }

    public class viewHolder2 extends RecyclerView.ViewHolder{
        ImageView imageView,imageView2,imageView3;
        TextView textView;

            public viewHolder2(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.editmainimageview);
                imageView2 = itemView.findViewById(R.id.deletemainimageview);
                imageView3 = itemView.findViewById(R.id.addhistory);
                textView = itemView.findViewById(R.id.maintaskview);
            }
    }
}
