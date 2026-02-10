package com.example.cwiczenie_3_4;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Options_f3#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Options_f3 extends Fragment implements AdapterView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ActionMode mActionMode;


    String[] lista = {"WHITE","RED","GREEN","YELLOW","BLACK"};
    String[] p ={"WHITE","RED","GREEN","YELLOW","BLACK"};




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Options_f3.
     */
    // TODO: Rename and change types and number of parameters
    public static Options_f3 newInstance(String param1, String param2) {
        Options_f3 fragment = new Options_f3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Options_f3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options_f3, container, false);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.checkable_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Spinner opcje = (Spinner) getActivity().findViewById(R.id.spinner);

        if(opcje !=null){
            opcje.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,lista);
            opcje.setAdapter(adapter);
        }

        TextView textView = view.findViewById(R.id.textViewFragment3);
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) {
                    return false;
                }
                ((AppCompatActivity) getActivity()).startSupportActionMode( mActionModeCallback);
                return true;
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (p[position]){
            case "BLACK":
                this.getView().setBackgroundColor(BLACK);
                break;
            case "WHITE":
                this.getView().setBackgroundColor(WHITE);
                break;
            case "YELLOW":
                this.getView().setBackgroundColor(YELLOW);
                break;
            case "GREEN":
                this.getView().setBackgroundColor(GREEN);
                break;
            case "RED":
                this.getView().setBackgroundColor(RED);
                break;
            default:
                this.getView().setBackgroundColor(WHITE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action, menu);
            mode.setTitle("Set color");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            View setColor = getActivity().findViewById(R.id.textViewFragment3).getRootView();
            switch (item.getItemId()) {
                case R.id.option_1:
                    setColor.setBackgroundColor(WHITE);
                    mode.finish();
                    return true;
                case R.id.option_2:
                    setColor.setBackgroundColor(RED);
                    mode.finish();
                    return true;
                case R.id.option_3:
                    setColor.setBackgroundColor(YELLOW);
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView text = getActivity().findViewById(R.id.textViewFragment3);
        Typeface typeface = text.getTypeface() != null ? text.getTypeface() : Typeface.DEFAULT;

        switch (item.getItemId()) {

            case R.id.checkable_item1:
                if(!item.isChecked())
                {
                    if(typeface.isBold())
                    {
                        text.setTypeface(null,Typeface.BOLD_ITALIC);
                    }
                    else
                    {
                        text.setTypeface(null, Typeface.ITALIC);
                    }

                }
                else
                {
                    if(typeface.isBold())
                    {
                        text.setTypeface(null,Typeface.BOLD);
                    }
                    else
                    {
                        text.setTypeface(null, Typeface.NORMAL);
                    }
                }
                item.setChecked(!item.isChecked());
                return true;

            case R.id.checkable_item2:

                if(!item.isChecked())
                {
                    if(typeface.isItalic())
                    {
                        text.setTypeface(typeface,Typeface.BOLD_ITALIC);
                    }
                    else
                    {
                        text.setTypeface(typeface, Typeface.BOLD);
                    }

                }
                else
                {
                    if(typeface.isItalic())
                    {
                        text.setTypeface(typeface,Typeface.ITALIC);
                    }
                    else
                    {
                        text.setTypeface(null, Typeface.NORMAL);
                    }
                }
                item.setChecked(!item.isChecked());
                return true;
            default:
                return false;

        }
    }




}
