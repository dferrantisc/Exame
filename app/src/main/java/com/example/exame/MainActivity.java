package com.example.exame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exame.Entidade.Cachorro;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference DBRefe;

    private List<Cachorro> dogList = new ArrayList<>();
    private ArrayAdapter<Cachorro> dogArrayAdapter;

    EditText Nome;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nome = (EditText)findViewById(R.id.txtNome);
        list = (ListView) findViewById(R.id.listView);

        FirebaseApp.initializeApp(MainActivity.this);
        database = FirebaseDatabase.getInstance();
        DBRefe = database.getReference();


        DBRefe.child("Cachorro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dogList.clear();
                for (DataSnapshot objSnapShot:snapshot.getChildren()){
                    Cachorro dog = objSnapShot.getValue(Cachorro.class);
                    dogList.add(dog);
                }
                dogArrayAdapter = new ArrayAdapter<Cachorro>(MainActivity.this,
                        android.R.layout.simple_list_item_1, dogList);
                list.setAdapter(dogArrayAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void salvar (View view){
        Cachorro dog = new Cachorro();

        dog.setIdDog(UUID.randomUUID().toString());
        dog.setNomeDog(Nome.getText().toString());

        DBRefe.child("Cachorro").child(dog.getIdDog()).setValue(dog);

        Toast.makeText(MainActivity.this, "Cachorro cadastrado", Toast.LENGTH_LONG).show();

    }
}