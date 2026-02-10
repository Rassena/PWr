package com.example.cwiczenie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static android.graphics.Color.WHITE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] lista = {"WHITE","RED","GREEN","YELLOW"};
    String[] p ={"WHITE","RED","GREEN","YELLOW"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intencja1 = new Intent(this,Lista1.class);
        Button przycisk1 = (Button)findViewById(R.id.button);
        przycisk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intencja1);
            }
        });

        Spinner opcje = (Spinner) findViewById(R.id.spinner);
        if(opcje !=null){
            opcje.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,lista);
            opcje.setAdapter(adapter);
        }

        /*
        String dane = "";
        String akcja = "android.intent.action.MAIN";
        Intent iii= new Intent(akcja,Uri.parse(dane));
        startActivityForResult(iii,1);


         */
    }

    @Override
    protected void onActivityResult(int reqID,int resC,Intent ii) {
        super.onActivityResult(reqID, resC, ii);
        if(resC== Activity.RESULT_OK && reqID == 1) {
            String dane = ii.getDataString();
        }
    }


    public void OpenAdd(View v){
        Intent i = new Intent(getApplicationContext(),Add.class);
        startActivityForResult(i, 1);
    }

    /*
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1 && resultCode==RESULT_OK){
            String reciveMessange = data.getStringExtra("Data");
        }
    }

     */

    public void uruchomDwa(View view){
        final Intent intencja2 = new Intent(this, Lista1.class);
        startActivity(intencja2);
    }

    public void uruchomtrzy(View view){
        final Intent intencja2 = new Intent(this, Lista2.class);
        startActivity(intencja2);
    }

    public void uruchomcztery(View view){
        final Intent intencja2 = new Intent(this, Grid1.class);
        startActivity(intencja2);
    }

    public void uruchompiec(View view){
        final Intent intencja2 = new Intent(this, Lista3.class);
        startActivity(intencja2);
    }

    public void uruchomadd(View view){
        final Intent intencja2 = new Intent(this, Add.class);
        startActivity(intencja2);
    }

    public void uruchomOpen(View view){
        final Intent intencja2 = new Intent(this, OpenItem.class);
        startActivity(intencja2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Wybrałeś: "+ p[position],Toast.LENGTH_LONG).show();
        if(position==0)
            bialeTlo();
        else
            if(position==1)
                czerwoneTlo();
            else
                if (position==2)
                    zieloneTlo();
                else
                    zolteTlo();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void bialeTlo(){
        this.getWindow().getDecorView().setBackgroundColor(WHITE);
    }

    public void zolteTlo(){
        this.getWindow().getDecorView().setBackgroundColor(YELLOW);
    }

    public void zieloneTlo(){
        this.getWindow().getDecorView().setBackgroundColor(GREEN);
    }

    public void czerwoneTlo(){
        this.getWindow().getDecorView().setBackgroundColor(RED);
    }

}