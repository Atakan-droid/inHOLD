package com.buruqo.inventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//Created by Varol KALA and Atakan GÖÇER
public class AddActivity extends AppCompatActivity {

    EditText title_input,author_input,item_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input =findViewById(R.id.title_input);
        author_input =findViewById(R.id.author_input);
        item_input =findViewById(R.id.item_input);
        add_button=findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabase myDB=new MyDatabase(AddActivity.this);
                myDB.addItem(title_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        Integer.valueOf(item_input.getText().toString().trim()));
                Intent intent=new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }
}