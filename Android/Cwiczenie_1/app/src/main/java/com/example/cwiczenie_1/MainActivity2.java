package com.example.cwiczenie_1;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        View mojeOkno2 = (View) findViewById(R.id.mojeOkno2);
        mojeOkno2.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                finish();
                return false;
            }
        });

    }
}