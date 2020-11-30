package com.buruqo.inventoryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
//Created by Varol KALA and Atakan GÖÇER
public class MainActivity extends AppCompatActivity {
    
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabase myDB;

    ArrayList<String> item_id, item_title, item_author, item_number;


    CustomAdapter customAdapter;
    

    @Override
    //create button
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerView=findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });
        myDB =new MyDatabase(MainActivity.this);

        item_id = new ArrayList<>();
        item_title = new ArrayList<>();
        item_author = new ArrayList<>();
        item_number = new ArrayList<>();

        storeDataInArrays();

        customAdapter=new CustomAdapter(MainActivity.this,this, item_id, item_title, item_author, item_number);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();

        }
    }

    void storeDataInArrays(){
       //Save datas with arrays
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount()==0){

            Toast.makeText(this,"Veri Yok" , Toast.LENGTH_SHORT).show();

        }else{
            while(cursor.moveToNext()){

                item_id.add(cursor.getString(0));
                item_title.add(cursor.getString(1));
                item_author.add(cursor.getString(2));
                item_number.add(cursor.getString(3));

            }

        }
    }

    @Override
    //Menu create
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //Deleting All
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all);
        {
            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hepsini Sil");
        builder.setMessage("Silmek istediginize emin misiniz ? ");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                MyDatabase myDB =new MyDatabase(MainActivity.this);
                myDB.deleteAllData();
                Intent intent=new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });
        builder.setNegativeButton("Hayýr", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



            }
        });
        builder.create().show();


    }
}