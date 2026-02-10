package com.example.cwiczenie_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intencja1 = new Intent(this,MainActivity2.class);
        Button przycisk1 = (Button)findViewById(R.id.button);
        przycisk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intencja1);
            }
        });
    }

    public void uruchomDwa(View view){
        final Intent intencja2 = new Intent(this, MainActivity2.class);
        startActivity(intencja2);
    }

    public void uruchomTrzy(View view){
        final  Intent intencja3 = new Intent(this,MainActivity3.class);
        startActivity(intencja3);
    }

}