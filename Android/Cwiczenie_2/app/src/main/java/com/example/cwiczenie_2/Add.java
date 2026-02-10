package com.example.cwiczenie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Enumeration;

public class Add extends AppCompatActivity {

    private TextView red;
    private TextView green;
    private TextView blue;

    private int ired;
    private int igreen;
    private int iblue;

    private RadioGroup rg;
    private RadioButton rFemle;
    private RadioButton rMale;

    private SeekBar seekBarRed;
    private SeekBar seekBarGreen;
    private SeekBar seekBarBlue;
    private View view;

    private int color;
    private String name;
    private String number;
    boolean sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        seekBarRed = (SeekBar) findViewById(R.id.seekBar3);
        seekBarGreen = (SeekBar) findViewById(R.id.seekBar4);
        seekBarBlue = (SeekBar) findViewById(R.id.seekBar5);

        rMale = (RadioButton) findViewById(R.id.radioButton);
        rFemle = (RadioButton) findViewById(R.id.radioButton2);

        red = (TextView) findViewById(R.id.textView11);
        green = (TextView) findViewById(R.id.textView12);
        blue = (TextView) findViewById(R.id.textView13);

        view = (View) findViewById(R.id.view);

        name = ((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString();
        number = ((EditText)findViewById(R.id.editTextPhone)).getText().toString();
        rg = (RadioGroup) findViewById(R.id.radiogroup);


        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                red.setText("R " + progress);
                ired = progress;
                color = Color.rgb(ired,igreen,iblue);
                view.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                green.setText("G " + progress);
                igreen = progress;
                color = Color.rgb(ired,igreen,iblue);
                view.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                blue.setText("B " + progress);
                iblue = progress;
                color = Color.rgb(ired,igreen,iblue);
                view.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=(RadioButton)findViewById(checkedId);
                if(rb==rFemle)
                    sex = false;
                sex=true;
                Toast.makeText(getApplicationContext(),rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    protected void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(),"Add",Toast.LENGTH_SHORT).show();
    }


    public void powrotDoMain(View view){
        onBackPressed();
    }




}