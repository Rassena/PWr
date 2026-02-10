package com.example.cwiczenie_3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    private TextView red;
    private TextView green;
    private TextView blue;

    private int ired;
    private int igreen;
    private int iblue;

    private RadioGroup rg;


    private SeekBar seekBarRed;
    private SeekBar seekBarGreen;
    private SeekBar seekBarBlue;
    private View view;

    private RatingBar rb;

    private int color;
    private String name;
    private String number;
    private String age;
    int gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.dodawanie);


        seekBarRed = (SeekBar) findViewById(R.id.seekBar3);
        seekBarGreen = (SeekBar) findViewById(R.id.seekBar4);
        seekBarBlue = (SeekBar) findViewById(R.id.seekBar5);


        red = (TextView) findViewById(R.id.textView11);
        green = (TextView) findViewById(R.id.textView12);
        blue = (TextView) findViewById(R.id.textView13);

        view = (View) findViewById(R.id.view);

        name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
        number = ((EditText)findViewById(R.id.editTextPhone)).getText().toString();
        age = ((EditText)findViewById(R.id.editTextAge)).getText().toString();
        rg = (RadioGroup) findViewById(R.id.radiogroup);

        rb = (RatingBar)findViewById(R.id.ratingBar);

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

                switch (rb.getId()) {
                    case R.id.radioButtonWomen:
                        gender =0;
                        break;
                    case R.id.radioButtonMen:
                        gender =1;
                        break;
                    case R.id.radioButtonAgender:
                        gender =2;
                        break;
                    case R.id.radioButtonTrap:
                        gender =3;
                        break;
                    case R.id.radioButtonReverseTrap:
                        gender =4;
                        break;
                    case R.id.radioButtonNonBinary:
                        gender =5;
                        break;
                }

                Toast.makeText(getApplicationContext(),rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    protected void onStart(){
        super.onStart();
    }


    public void powrotDoMain(View view){
        onBackPressed();
    }

    public void AddToTheList(View view){
        Intent resultIntent = new Intent();

        name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
        age = ((EditText)findViewById(R.id.editTextAge)).getText().toString();
        number = ((EditText)findViewById(R.id.editTextPhone)).getText().toString();

        resultIntent.putExtra("name", name);
        resultIntent.putExtra("number", number);
        resultIntent.putExtra("age", age);
        resultIntent.putExtra("redprogress", seekBarRed.getProgress());
        resultIntent.putExtra("blueprogress", seekBarBlue.getProgress());
        resultIntent.putExtra("greenProgress", seekBarGreen.getProgress());
        resultIntent.putExtra("gender", rg.getCheckedRadioButtonId());
        resultIntent.putExtra("rating",rb.getRating());
        setResult(RESULT_OK,resultIntent);
        Toast.makeText(getApplicationContext(),"Dodano " + name,Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }


}