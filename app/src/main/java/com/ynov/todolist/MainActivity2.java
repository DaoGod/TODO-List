package com.ynov.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "myactivity";

    Button buttonReturn;
    Button buttonAddTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonReturn = findViewById(R.id.buttonReturn);
        buttonAddTodo = findViewById(R.id.buttonAddTodo);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent MainActivity = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(MainActivity);
            }
        });

        buttonAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addList(view);
                Log.w(TAG, "setOnClickListener");

            }
        });
    }


public void addList(View view){

        EditText textNewList = findViewById(R.id.textNewList);
        String itemsText = textNewList.getText().toString();

        if(!(itemsText.equals(""))){

            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference text = database.getReference("todo");

            text.setValue(itemsText);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter text..", Toast.LENGTH_LONG).show();
        }
    }
}