package com.example.cwiczenie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OpenItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_item);
    }


    public void powrot(View view) {
        onBackPressed();
    }


    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Podgląd", Toast.LENGTH_SHORT).show();
    }

    public void dial(View view){
        TextView txt =(TextView) findViewById(R.id.textView8);
        Intent iii = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+txt.getText().toString()));
        startActivity(iii);
    }

}