package com.ynov.todolist;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myactivity";

    FloatingActionButton buttonNewTodo;
    ListView listView;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;



    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        buttonNewTodo = findViewById(R.id.buttonNewTodo);

        /** Floating Button pour changer d'activitée, pointage vers sur MainActivity2  */
        buttonNewTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newTodo = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(newTodo);
            }
        }); // END setOnClickListener

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference text = database.getReference().child("todo");

        ArrayAdapter<String> tableau =
                new ArrayAdapter<String>(listView.getContext(),
                        R.layout.text_field, R.id.textView,
                        arrayList);

        listView.setAdapter(tableau);

        text.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Todo todoClass = (Todo) dataSnapshot.getValue(Todo.class);
                 String todoString = String.valueOf(todoClass);

                arrayAdapter.add(todoString);
                Log.w(TAG,"onChildAdded - " + todoString);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


        /*
        // Read from the database
        text.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

                ArrayAdapter<String> tableau =
                        new ArrayAdapter<String>(listView.getContext(),
                                R.layout.text_field, R.id.textView,
                                arrayList);



                listView.setAdapter(tableau);

                Log.d(TAG, "onDataChange - Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
                // Failed to read value
        }); // END addValueEventListener*/

        Log.w(TAG,"onCreate");
    } // END onCreate



} // END MainActivity

        /*
        items = new ArrayList<>();
        //itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(bundle);
        setUpListViewListener();
}

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show();

                items.remove(i);
                //itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void addList(View view){
        EditText textNewList = findViewById(R.id.textNewList);
        String itemsText = textNewList.getText().toString();

        if(!(itemsText.equals(" "))){
            //itemsAdapter.add(itemsText);
            textNewList.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter text..", Toast.LENGTH_LONG).show();
        }
    }







}


























/*    Button ButtonNewList;
    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    /*ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        ButtonNewList = findViewById(R.id.ButtonNewList);


        ButtonNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addList(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        setUpListViewListener();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "clicked item: " + i + " " + itemsAdapter.getItem(i).toString(), Toast.LENGTH_SHORT).show();
                Intent MainActivity2 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(MainActivity2);
            }
        });


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }


    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show();

                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void addList(View view){
        EditText textNewList = findViewById(R.id.textNewList);
        String itemsText = textNewList.getText().toString();

        if(!(itemsText.equals(" "))){
            itemsAdapter.add(itemsText);
            textNewList.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter text..", Toast.LENGTH_LONG).show();
        }
    }
}

*/