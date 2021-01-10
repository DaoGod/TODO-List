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
        DatabaseReference text = database.getReference("todolist");

       /* ArrayAdapter<String> tableau =
                new ArrayAdapter<String>(listView.getContext(),
                        R.layout.text_field, R.id.textView,
                        arrayList);

        listView.setAdapter(tableau);*/

        text.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                String value = snapshot.getValue(Todo.class).toString();
                Todo todoClass = (Todo) snapshot.getValue(Todo.class);
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


        });*/

        /** Lecture de la firebase*/
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference text = database.getReference("todolist");
        // Read from the database
        text.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
              /*if (dataSnapshot.exists ())
              {
                  HashMap <String, Object> data
              });
            for (clé de chaîne: dataMap.keySet ()) {objet Data = dataMap.get (clé);
            try {
                HashMap <String, Object> userData = (HashMap <String, Object>) data;
            }
            utilisateur mUser = nouvel utilisateur ( (Chaîne) userData.get ("nom"), (int) (long) userData.get ("âge"));
            addTextToView (mUser.getName () + "-" + Integer.toString (mUser.getAge ()) );
            }
            catch (ClassCastException cce)
            {
            // Si l'objet ne peut pas être transtypé en HashMap, cela signifie qu'il est de type String.
            // try {String mString = String.valueOf (dataMap.get (clé)); addTextToView ( mString);} catch (ClassCastException cce2) {}}}}}
            // @Override public void onCancelled (@NonNull DatabaseError databaseError) {}});
                // }*/
                String value = dataSnapshot.getValue(String.class);

                ArrayAdapter<String> tableau =
                        new ArrayAdapter<String>(listView.getContext(),
                                R.layout.text_field, R.id.textView,
                                arrayList);



                listView.setAdapter(tableau);

                Log.d(TAG, "onDataChange - Value is: " + value);
            }
            /** Si n'arrive pas à lire la firebase*/
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("APPX", "Failed to read value.", error.toException());
            }
            // Failed to read value
        }); // END addValueEventListener*/

        Log.w(TAG,"onCreate");
    } // END onCreate



} // END MainActivity
