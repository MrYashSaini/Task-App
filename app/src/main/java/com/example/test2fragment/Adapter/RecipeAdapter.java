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

import com.example.test2fragment.Models.RecipeModel;
import com.example.test2fragment.R;
import com.example.test2fragment.databasemenu;
import com.example.test2fragment.editDailyActivity;

import java.util.ArrayList;

    public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.viewHolder> {
        ArrayList<RecipeModel> list;
        Context context;

        public RecipeAdapter(ArrayList<RecipeModel> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.sampleofrecyleview ,parent,false);
            return new viewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull viewHolder holder, int position) {
            RecipeModel model  = list.get(position);
            databasemenu db = new databasemenu(context);

            holder.imageView2.setImageResource(model.getPic2());
            holder.imageView.setImageResource(model.getPic());
            holder.textView.setText(model.getText());
            int p =position;
// delete task in daily work
            holder.imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

// get id in array
                    Cursor res = db.getdata();
                    String numar[]=new String[res.getCount()+1];
                    int i=0;
                    while(res.moveToNext()) {
                        numar[i]=res.getString(0);
                        i++;
                    }
// get position and remove data
                    String idTXT = numar[p];
                    db.deleteuserdata(idTXT);
                    Toast.makeText(v.getContext(), "Delete Task", Toast.LENGTH_SHORT).show();

                }
            });
// edit user  daily work
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String user_value = String.valueOf(p);

                    Intent intent = new Intent(context, editDailyActivity.class);
                    intent.putExtra("User_Value",user_value);
                    context.startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class viewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            ImageView imageView2;
            TextView textView;
            public viewHolder(@NonNull View itemView) {
                super(itemView);
                imageView2 = itemView.findViewById(R.id.deleteimageView);
                imageView = itemView.findViewById(R.id.editimageView);
                textView = itemView.findViewById(R.id.taskview);
            }
        }
    }

