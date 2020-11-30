package com.buruqo.inventoryapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Created by Varol KALA and Atakan GÖÇER
public class UpdateActivity extends AppCompatActivity {

    EditText title_input2, author_input2, number_input2;

    Button update_button,delete_button;

    String id, title, author, number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input2 = findViewById(R.id.title_input2);
        author_input2 = findViewById(R.id.author_input2);
        number_input2 = findViewById(R.id.number_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
                MyDatabase myDB =new MyDatabase(UpdateActivity.this);

                title=title_input2.getText().toString().trim();
                author=author_input2.getText().toString().trim();
                number=number_input2.getText().toString().trim();

                myDB.updateData(id,title,author,number);
                Intent intent=new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {

        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("number"))
        {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            number = getIntent().getStringExtra("number");

            title_input2.setText(title);
            author_input2.setText(author);
            number_input2.setText(number);

        }else
        {

            Toast.makeText(this, "Veri Yok", Toast.LENGTH_SHORT).show();

        }
    }

    void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Silinecek olan " + title + " ?");
        builder.setMessage("Silmek istediginize emin misiniz " + title + " ?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                MyDatabase myDB = new MyDatabase(UpdateActivity.this);

                myDB.deleteOneRow(id);
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