package com.example.cwiczenie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Lista2 extends Activity implements AdapterView.OnItemClickListener {

    ListView lista;
    String[] lista_poz = {"Linia 1", "Linia 2","Linia 3", "Linia 4","Linia 5", "Linia 6","Linia 7", "Linia 8","Linia 9", "Linia 10","Linia 11", "Linia 12","Linia 13", "Linia 14"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista2);

        lista= (ListView) findViewById(R.id.Lista_Multichoice);
        lista.setOnItemClickListener(this);

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,lista_poz);
        lista.setAdapter(adap);

    }

    protected void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(),"Lista2",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String napis = new String("");
        SparseBooleanArray zaznaczone = lista.getCheckedItemPositions();


        for (int i =0; i< zaznaczone.size()+1;i++) {
            if (zaznaczone.valueAt(i)) {
                int indeks = zaznaczone.keyAt(i);
                napis += (" " + String.valueOf(indeks+1));
            }
        }
        Toast.makeText(getApplicationContext(),"Wybrałeś: "+ napis,Toast.LENGTH_SHORT).show();
    }



}