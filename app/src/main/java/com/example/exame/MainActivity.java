package com.example.exame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.exame.Entidade.Cachorro;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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









    }
}