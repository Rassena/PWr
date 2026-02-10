package com.example.cwiczenie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String[] lista = {"BLACK","WHITE","RED","GREEN","YELLOW"};
    String[] p ={"BLACK","WHITE","RED","GREEN","YELLOW"};

    ArrayList<Lista.ListElement> ItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadData();

        Spinner opcje = (Spinner) findViewById(R.id.spinner);
        if(opcje !=null){
            opcje.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,lista);
            opcje.setAdapter(adapter);
        }


    }

    public void uruchomlista_copy(View view){
        final Intent intencja = new Intent(this, Lista.class);
        startActivity(intencja);
    }

    public void uruchomlista(View view){
        final Intent intencja = new Intent(this, test_activity.class);
        startActivity(intencja);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Wybrałeś: "+ p[position],Toast.LENGTH_LONG).show();
        switch (p[position]){
            case "BLACK":
                czarneTlo();
                break;
            case "WHITE":
                bialeTlo();
                break;
            case "YELLOW":
                zolteTlo();
                break;
            case "GREEN":
                zieloneTlo();
                break;
            case "RED":
                czerwoneTlo();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void czarneTlo(){
        this.getWindow().getDecorView().setBackgroundColor(BLACK);
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


    public void uruchomAdd(View view){
        final Intent intencja = new Intent(this, Add.class);
        startActivityForResult(intencja,1);

    }

    private void SaveList()
    {
        SharedPreferences sharedPreferences = getSharedPreferences( "shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ItemList);
        editor.putString("List",json);
        editor.apply();
    }
    private void LoadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences( "shared preferences", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("List" ,null);
        Type type = new TypeToken<ArrayList<Lista.ListElement>>() {}.getType();
        ItemList = gson.fromJson(json,type);
        if(ItemList == null)
        {
            ItemList = new ArrayList<>();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Lista.ListElement Element = new Lista.ListElement();
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Element.name = data.getStringExtra("name");
                Element.number = data.getStringExtra("number");
                Element.age = data.getStringExtra("age");
                Element.BlueProgress = data.getIntExtra("blueprogress", 0);
                Element.RedProgress = data.getIntExtra("redprogress", 0);
                Element.GreenProgress = data.getIntExtra("greenprogress", 0);
                Element.rating = data.getFloatExtra("rating", 0);
                int test = data.getIntExtra("gender", 0);
                switch (test) {
                    case R.id.radioButtonWomen:
                        Element.gender = 0;
                        break;
                    case R.id.radioButtonMen:
                        Element.gender = 1;
                        break;
                    case R.id.radioButtonAgender:
                        Element.gender = 2;
                        break;
                    case R.id.radioButtonTrap:
                        Element.gender = 3;
                        break;
                    case R.id.radioButtonReverseTrap:
                        Element.gender = 4;
                        break;
                    case R.id.radioButtonNonBinary:
                        Element.gender = 5;
                        break;
                }
                ItemList.add(Element);
                SaveList();
            }
        }
    }
}