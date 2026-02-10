package com.example.cwiczenie_3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

    public class Lista extends AppCompatActivity implements OpenItem_f1.OpenItemListener, Lista_f1.ListFragmentListener {


       // MyAdapter adapter;
        ListView lista;
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
        ArrayList<ListElement> ItemList;


        @Override
        public void onOpenInputSent(Bundle bundle) {
            ListView lista = (ListView) findViewById(R.id.listView);

            Lista_f1.MyAdapter adapter = (Lista_f1.MyAdapter) lista.getAdapter();

            int elemposition = bundle.getInt("position", 0);

            ListElement Element = ItemList.get(elemposition);

            Element.name = bundle.getString("name");
            Element.number = bundle.getString("number");
            Element.BlueProgress = bundle.getInt("blueprogress", 0);
            Element.RedProgress = bundle.getInt("redprogress", 0);
            Element.GreenProgress = bundle.getInt("greenprogress", 0);
            Element.rating = bundle.getFloat("rating", 0);

            int test = bundle.getInt("gender",0);
            switch (test) {
                case R.id.radioButtonWomen_open:
                    Element.gender =0;
                    break;
                case R.id.radioButtonMen_open:
                    Element.gender =1;
                    break;
                case R.id.radioButtonAgender_open:
                    Element.gender =2;
                    break;
                case R.id.radioButtonTrap_open:
                    Element.gender =3;
                    break;
                case R.id.radioButtonReverseTrap_open:
                    Element.gender =4;
                    break;
                case R.id.radioButtonNonBinary_open:
                    Element.gender =5;
                    break;
            }
            ItemList.set(elemposition, Element);
            adapter.notifyDataSetChanged();
            SaveList();
            LoadData();
            adapter.notifyDataSetChanged();


        }

        @Override
        public void onListInputSent(Bundle bundle) {

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

            NameView.setText(bundle.getString("name"));
            NumberView.setText(bundle.getString ("number"));
            AgeView.setText(bundle.getString ("age"));

            RecordPosition = bundle.getInt ("position", 0);

            ired = bundle.getInt ("redprogress", 0);
            iblue = bundle.getInt ("blueprogress", 0);
            igreen = bundle.getInt ("greenprogress", 0);
            seekBarRed.setProgress(ired);
            seekBarBlue.setProgress(iblue);
            seekBarGreen.setProgress(igreen);




            rb.setRating(bundle.getFloat ("rating", 0));

            color = Color.rgb(ired, igreen, iblue);
            view.setBackgroundColor(color);


            switch (bundle.getInt ("gender", 0)) {
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


        private class LVitem{
            TextView tv1;
            TextView tv2;
            ImageView img;
            View Color;
        }

        protected static class ListElement //implements Comparable
        {
            String name;
            String number;
            String age;
            int RedProgress;
            int GreenProgress;
            int BlueProgress;
            int gender;
            float rating;

            public ListElement()
            {

            }

        /*
        @Override
        public int compareTo(ListElement o) {
            String comparename =((ListElement)o).name;
            int byname = comparename.compareTo(this.name);
            if(byname!=0)
                return 0;
            String compareage =((ListElement)o).name;
            int byage = compareage.compareTo(this.age);
            return byage;
        }
         */
        }



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.Lista);

            LoadData();
            //adapter = new MyAdapter(ItemList);
           // lista = (ListView) findViewById(R.id.listView);
           // lista.setAdapter(adapter);

            FloatingActionButton Button = findViewById(R.id.floatingActionButton);
            Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uruchomAdd(v);
                }
            });
        }

        protected void onStart(){
            super.onStart();
            //adapter.notifyDataSetChanged();
            LoadData();
        }


        public void powrotDoMain(View view){
            onBackPressed();
            SaveList();
        }

        public void uruchomAdd(View view){
            final Intent intencja = new Intent(this, Add.class);
            startActivityForResult(intencja,1);

        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            ListElement Element = new ListElement();
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    Element.name = data.getStringExtra("name");
                    Element.number = data.getStringExtra("number");
                    Element.age = data.getStringExtra("age");
                    Element.BlueProgress = data.getIntExtra("blueprogress", 0);
                    Element.RedProgress = data.getIntExtra("redprogress", 0);
                    Element.GreenProgress = data.getIntExtra("greenprogress", 0);
                    Element.rating = data.getFloatExtra("rating", 0);

                    switch (data.getIntExtra("gender", 0)) {
                        case R.id.radioButtonWomen:
                            Element.gender =0;
                            break;
                        case R.id.radioButtonMen:
                            Element.gender =1;
                            break;
                        case R.id.radioButtonAgender:
                            Element.gender =2;
                            break;
                        case R.id.radioButtonTrap:
                            Element.gender =3;
                            break;
                        case R.id.radioButtonReverseTrap:
                            Element.gender =4;
                            break;
                        case R.id.radioButtonNonBinary:
                            Element.gender =5;
                            break;
                    }

                    ItemList.add(Element);
                    SaveList();
                }
            }
            if (requestCode == 2) {
                if (resultCode == RESULT_OK) {
                    int elemposition = data.getIntExtra("position", 0);
                    Element.name = data.getStringExtra("name");
                    Element.number = data.getStringExtra("number");
                    Element.age = data.getStringExtra("age");
                    Element.BlueProgress = data.getIntExtra("blueprogress", 0);
                    Element.RedProgress = data.getIntExtra("redprogress", 0);
                    Element.GreenProgress = data.getIntExtra("greenprogress", 0);
                    Element.rating = data.getFloatExtra("rating", 0);
                    int test = data.getIntExtra("gender",0);
                    switch (test) {
                        case R.id.radioButtonWomen_open:
                            Element.gender =0;
                            break;
                        case R.id.radioButtonMen_open:
                            Element.gender =1;
                            break;
                        case R.id.radioButtonAgender_open:
                            Element.gender =2;
                            break;
                        case R.id.radioButtonTrap_open:
                            Element.gender =3;
                            break;
                        case R.id.radioButtonReverseTrap_open:
                            Element.gender =4;
                            break;
                        case R.id.radioButtonNonBinary_open:
                            Element.gender =5;
                            break;
                    }
                    ItemList.set(elemposition, Element);
                    SaveList();

                }
            }

        }

        private void SaveList()
        {
            SharedPreferences sharedPreferences = getSharedPreferences( "shared preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(ItemList);
            editor.putString("List",json);
            editor.apply();
        }
        private void LoadData()
        {
            SharedPreferences sharedPreferences = getSharedPreferences( "shared preferences", MODE_PRIVATE);

            Gson gson = new Gson();
            String json = sharedPreferences.getString("List" ,null);
            Type type = new TypeToken<ArrayList<ListElement>>() {}.getType();
            ItemList = gson.fromJson(json,type);
            if(ItemList == null)
            {
                ItemList = new ArrayList<>();
            }

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