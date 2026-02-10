package com.example.cwiczenie_3_4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpenItem_f1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenItem_f1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
    private View viewV;

    private RatingBar rb;

    private int color;
    private EditText NameView;
    private EditText NumberView;
    private EditText AgeView;
    private int RecordPosition;
    int gender;

    private OpenItemListener listener;

    public interface OpenItemListener {
        void onOpenInputSent(Bundle bundle);
    }

    public OpenItem_f1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpenItem_f1.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenItem_f1 newInstance(String param1, String param2) {
        OpenItem_f1 fragment = new OpenItem_f1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_item_f1, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();

        //getSupportActionBar().setTitle(R.string.podglad);

        seekBarRed = (SeekBar) view.findViewById(R.id.SeekBarRed_open);
        seekBarGreen = (SeekBar) view.findViewById(R.id.SeekBarGreen_open);
        seekBarBlue = (SeekBar) view.findViewById(R.id.SeekBarBlue_open);


        red = (TextView) view.findViewById(R.id.textView11);
        green = (TextView) view.findViewById(R.id.textView12);
        blue = (TextView) view.findViewById(R.id.textView13);

        viewV = (View) view.findViewById(R.id.ColorView_open);

        NameView = (EditText) view.findViewById(R.id.Name_Open);
        NumberView = (EditText) view.findViewById(R.id.Number_Open);
        AgeView = (EditText) view.findViewById(R.id.Age_Open);

        rg = (RadioGroup) view.findViewById(R.id.radiogroup_open);

        rb = (RatingBar) view.findViewById(R.id.ratingBar_open);

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
                viewV.setBackgroundColor(color);
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
                viewV.setBackgroundColor(color);
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
                viewV.setBackgroundColor(color);
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
        viewV.setBackgroundColor(color);


        switch (intent.getIntExtra("gender", 0)) {
            case 0:
                ((RadioButton) view.findViewById(R.id.radioButtonWomen_open)).setChecked(true);
                break;
            case 1:
                ((RadioButton) view.findViewById(R.id.radioButtonMen_open)).setChecked(true);
                break;
            case 2:
                ((RadioButton) view.findViewById(R.id.radioButtonAgender_open)).setChecked(true);
                break;
            case 3:
                ((RadioButton) view.findViewById(R.id.radioButtonTrap_open)).setChecked(true);
                break;
            case 4:
                ((RadioButton) view.findViewById(R.id.radioButtonReverseTrap_open)).setChecked(true);
                break;
            case 5:
                ((RadioButton) view.findViewById(R.id.radioButtonNonBinary_open)).setChecked(true);
                break;
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) view.findViewById(checkedId);

                Toast.makeText(getActivity().getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonMod = view.findViewById(R.id.button_mod_f);
        buttonMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify_f(v);
            }
        });

        Button buttonDial = view.findViewById(R.id.button_dial_f);
        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial_f(v);
            }
        });

    }

    public void modify_f(View view) {
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
        Toast.makeText(getActivity().getApplicationContext(), "Zapisano zmiany", Toast.LENGTH_SHORT).show();
    }


    public void powrot(View view) {
        getActivity().onBackPressed();
    }

    public void dial_f(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + NumberView.getText().toString()));
        Toast.makeText(getActivity().getApplicationContext(), "Open Dial", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void onStart() {
        super.onStart();
        //    Toast.makeText(getApplicationContext(), "Podgląd", Toast.LENGTH_SHORT).show();
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


}



