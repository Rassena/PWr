package com.example.cwiczenie_2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class OpenItem_fragment extends Fragment {

    public interface OpenItemListener {
        void onOpenInputSent(Bundle bundle);
    }
    private OpenItemListener listener;
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
    private View colorView;

    private RatingBar rb;

    private int color;
    private EditText NameView;
    private EditText NumberView;
    private EditText AgeView;
    private int RecordPosition;
    int gender;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_lista, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getActivity().getIntent();
        getActivity().setContentView(R.layout.activity_open_item);

        getActivity().getActionBar().setTitle(R.string.podglad);

        seekBarRed = (SeekBar) getActivity().findViewById(R.id.SeekBarRed_open);
        seekBarGreen = (SeekBar) getActivity().findViewById(R.id.SeekBarGreen_open);
        seekBarBlue = (SeekBar) getActivity().findViewById(R.id.SeekBarBlue_open);


        red = (TextView) getActivity().findViewById(R.id.textView11);
        green = (TextView) getActivity().findViewById(R.id.textView12);
        blue = (TextView) getActivity().findViewById(R.id.textView13);

        colorView = (View) getActivity().findViewById(R.id.ColorView_open);

        NameView = (EditText)getActivity().findViewById(R.id.Name_Open);
        NumberView = (EditText)getActivity().findViewById(R.id.Number_Open);
        AgeView = (EditText)getActivity().findViewById(R.id.Age_Open);

        rg = (RadioGroup) getActivity().findViewById(R.id.radiogroup_open);

        rb = (RatingBar)getActivity().findViewById(R.id.ratingBar_open);

        NameView.setText(intent.getStringExtra("name"));
        NumberView.setText(intent.getStringExtra("number"));
        AgeView.setText(intent.getStringExtra("age"));

        RecordPosition = intent.getIntExtra("position",0);

        ired = intent.getIntExtra("redprogress",0);
        iblue = intent.getIntExtra("blueprogress",0);
        igreen = intent.getIntExtra("greenprogress",0);
        seekBarRed.setProgress(ired);
        seekBarBlue.setProgress(iblue);
        seekBarGreen.setProgress(igreen);
        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                red.setText("R " + progress);
                ired = progress;
                color = Color.rgb(ired,igreen,iblue);
                colorView.setBackgroundColor(color);
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
                colorView.setBackgroundColor(color);
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
                colorView.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rb.setRating(intent.getFloatExtra("rating",0));

        color = Color.rgb(ired,igreen,iblue);
        colorView.setBackgroundColor(color);


        switch (intent.getIntExtra("gender",0)){
            case 0:
                ((RadioButton) getActivity().findViewById(R.id.radioButtonWomen_open)).setChecked(true);
                break;
            case 1:
                ((RadioButton) getActivity().findViewById(R.id.radioButtonMen_open)).setChecked(true);
                break;
            case 2:
                ((RadioButton) getActivity().findViewById(R.id.radioButtonAgender_open)).setChecked(true);
                break;
            case 3:
                ((RadioButton) getActivity().findViewById(R.id.radioButtonTrap_open)).setChecked(true);
                break;
            case 4:
                ((RadioButton) getActivity().findViewById(R.id.radioButtonReverseTrap_open)).setChecked(true);
                break;
            case 5:
                ((RadioButton) getActivity().findViewById(R.id.radioButtonNonBinary_open)).setChecked(true);
                break;
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=(RadioButton)getActivity().findViewById(checkedId);

                Toast.makeText(getActivity().getApplicationContext(),rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void Modify(View view)
    {

        if(getResources().getConfiguration().orientation == 1) {
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

            getActivity().setResult(getActivity().RESULT_OK, resultIntent);
            Toast.makeText(getActivity().getApplicationContext(), "Zapisano zmiany", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
        else
        {

            Bundle bundle = new Bundle();


            bundle.putString("name", NameView.getText().toString());
            bundle.putString("number", NumberView.getText().toString());
            bundle.putString("age", AgeView.getText().toString());
            bundle.putInt("redprogress", seekBarRed.getProgress());
            bundle.putInt("blueprogress", seekBarBlue.getProgress());
            bundle.putInt("greenprogress", seekBarGreen.getProgress());
            int test = rg.getCheckedRadioButtonId();
            bundle.putInt("gender", test);
            bundle.putFloat("rating", rb.getRating());
            bundle.putInt("position", RecordPosition);

            listener.onOpenInputSent(bundle);
        }



    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OpenItemListener) {
            listener = (OpenItemListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OpenItemListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public void powrot(View view) {
        getActivity().onBackPressed();
    }

    public void dial(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + NumberView.getText().toString()));
        startActivity(intent);
    }

    public void onStart() {
        super.onStart();
    //    Toast.makeText(getApplicationContext(), "Podgląd", Toast.LENGTH_SHORT).show();
    }
}