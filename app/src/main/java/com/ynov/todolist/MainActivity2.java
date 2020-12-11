package com.ynov.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.ynov.todolist.R;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button button2;
    Button button3;
    Button button4;
    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button2 = findViewById(R.id.button2);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        setUpListViewListener();

    }

    private void setUpListViewListener() {

    }

    public void addItem(View view){
        EditText input = findViewById(R.id.editText);
        String itemsText = input.getText().toString();

        if(!(itemsText.equals(" "))){
            itemsAdapter.add(itemsText);
            input.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter text..", Toast.LENGTH_LONG).show();
        }
    }
}