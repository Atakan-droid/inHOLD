package com.buruqo.inventoryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//Created by Varol KALA and Atakan GÖÇER
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

   private Context context;
   Activity activity;
   

   private  ArrayList item_id, item_title, item_author, item_number;


    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList item_id,
                  ArrayList item_title,
                  ArrayList item_author,
                  ArrayList item_number){
        this.activity = activity;

        this.context =context;
        this.item_id=item_id;
        this.item_title =item_title;
        this.item_author=item_author;
        this.item_number=item_number;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {



        holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_title_txt.setText(String.valueOf(item_title.get(position)));
        holder.item_author_txt.setText(String.valueOf(item_author.get(position)));
        holder.item_number_txt.setText(String.valueOf(item_number.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(item_id.get(position)));
                intent.putExtra("title", String.valueOf(item_title.get(position)));
                intent.putExtra("author", String.valueOf(item_author.get(position)));
                intent.putExtra("number", String.valueOf(item_number.get(position)));
               activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_id_txt, item_title_txt, item_author_txt, item_number_txt;

        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id_txt=itemView.findViewById(R.id.item_id_txt);
            item_title_txt=itemView.findViewById(R.id.item_title_txt);
            item_author_txt=itemView.findViewById(R.id.item_author_txt);
            item_number_txt=itemView.findViewById(R.id.item_number_txt);
            mainLayout =itemView.findViewById(R.id.mainLayout);


        }
    }
}
