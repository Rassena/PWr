package com.example.cwiczenie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

public class Grid1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid1);

        GridView gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(new MyAdapter(this));

    }

    protected void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(),"Grid1",Toast.LENGTH_SHORT).show();
    }
}