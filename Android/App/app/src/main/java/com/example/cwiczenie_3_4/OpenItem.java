package com.example.cwiczenie_3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class OpenItem extends AppCompatActivity {


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
    private EditText NameView;
    private EditText NumberView;
    private EditText AgeView;
    private int RecordPosition;
    int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_open_item);

        getSupportActionBar().setTitle(R.string.podglad);

        seekBarRed = (SeekBar) findViewById(R.id.SeekBarRed_open);
        seekBarGreen = (SeekBar) findViewById(R.id.SeekBarGreen_open);
        seekBarBlue = (SeekBar) findViewById(R.id.SeekBarBlue_open);


        red = (TextView) findViewById(R.id.textView11);
        green = (TextView) findViewById(R.id.textView12);
        blue = (TextView) findViewById(R.id.textView13);

        view = (View) findViewById(R.id.ColorView_open);

        NameView = (EditText) findViewById(R.id.Name_Open);
        NumberView = (EditText) findViewById(R.id.Number_Open);
        AgeView = (EditText) findViewById(R.id.Age_Open);

        rg = (RadioGroup) findViewById(R.id.radiogroup_open);

        rb = (RatingBar) findViewById(R.id.ratingBar_open);

        NameView.setText(intent.getStringExtra("name"));
        NumberView.setText(intent.getStringExtra("number"));
        AgeView.setText(intent.getStringExtra("age"));

        RecordPosition = intent.getIntExtra("position", 0);

        ired = intent.getIntExtra("redprogress", 0);
        iblue = intent.getIntExtra("blueprogress", 0);
        igreen = intent.getIntExtra("greenprogress", 0);
        seekBarRed.setProgress(ired);
        seekBarBlue.setProgress(iblue);
        seekBarGreen.setProgress(igreen);
        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                red.setText("R " + progress);
                ired = progress;
                color = Color.rgb(ired, igreen, iblue);
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
                color = Color.rgb(ired, igreen, iblue);
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
                color = Color.rgb(ired, igreen, iblue);
                view.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rb.setRating(intent.getFloatExtra("rating", 0));

        color = Color.rgb(ired, igreen, iblue);
        view.setBackgroundColor(color);


        switch (intent.getIntExtra("gender", 0)) {
            case 0:
                ((RadioButton) findViewById(R.id.radioButtonWomen_open)).setChecked(true);
                break;
            case 1:
                ((RadioButton) findViewById(R.id.radioButtonMen_open)).setChecked(true);
                break;
            case 2:
                ((RadioButton) findViewById(R.id.radioButtonAgender_open)).setChecked(true);
                break;
            case 3:
                ((RadioButton) findViewById(R.id.radioButtonTrap_open)).setChecked(true);
                break;
            case 4:
                ((RadioButton) findViewById(R.id.radioButtonReverseTrap_open)).setChecked(true);
                break;
            case 5:
                ((RadioButton) findViewById(R.id.radioButtonNonBinary_open)).setChecked(true);
                break;
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);

                Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void Modify(View view) {
        Intent resultIntent = new Intent();


        resultIntent.putExtra("name", NameView.getText().toString());
        resultIntent.putExtra("number", NumberView.getText().toString());
        resultIntent.putExtra("age", AgeView.getText().toString());
        resultIntent.putExtra("redprogress", seekBarRed.getProgress());
        resultIntent.putExtra("blueprogress", seekBarBlue.getProgress());
        resultIntent.putExtra("greenprogress", seekBarGreen.getProgress());
        int test = rg.getCheckedRadioButtonId();
        resultIntent.putExtra("gender", test);
        resultIntent.putExtra("rating", rb.getRating());
        resultIntent.putExtra("position", RecordPosition);

        setResult(RESULT_OK, resultIntent);
        Toast.makeText(getApplicationContext(), "Zapisano zmiany", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void powrot(View view) {
        onBackPressed();
    }

    public void dial(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + NumberView.getText().toString()));
        startActivity(intent);
    }

    protected void onStart() {
        super.onStart();
        //    Toast.makeText(getApplicationContext(), "Podgląd", Toast.LENGTH_SHORT).show();
    }
}
