package com.example.cwiczenie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Lista3 extends Activity {

    private class LVitem{
        TextView tv1;
        TextView tv2;
        ImageView img;
        CheckBox cBox;
        Button but;
        boolean sex;
    }

    public class MyAdapter extends BaseAdapter{

        private LayoutInflater inflater = null;
        boolean[] zazn_pozycje;
        LVitem myLVitem;

        MyAdapter(String[] lista) {
            super();
            zazn_pozycje = new boolean[ltxt1.length];
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return ltxt1.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View listitemView, ViewGroup parent) {
            if (listitemView==null) {
                listitemView = inflater.inflate(R.layout.list_row, null);
                myLVitem = new LVitem();
                myLVitem.tv1 = (TextView) listitemView.findViewById(R.id.row_tv1);
                myLVitem.tv2 = (TextView) listitemView.findViewById(R.id.row_tv2);
                myLVitem.img = (ImageView) listitemView.findViewById(R.id.row_image);
                myLVitem.cBox = (CheckBox) listitemView.findViewById(R.id.lrow_checkBox);
                myLVitem.but = (Button) listitemView.findViewById(R.id.row_but);
                myLVitem.sex = true;
                listitemView.setTag(myLVitem);
            }
            else
                myLVitem = (LVitem) listitemView.getTag();
            myLVitem.tv1.setText(ltxt1[position]);
            myLVitem.tv2.setText(ltxt2[position]);
            myLVitem.cBox.setChecked(zazn_pozycje[position]);
            if(position%2==0)
                myLVitem.img.setImageResource(R.drawable.row);
            else
                myLVitem.img.setImageResource(R.drawable.sasuke);
            myLVitem.cBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CheckBox)v).isChecked()) {
                        zazn_pozycje[position] = true;
                    }
                    else
                        zazn_pozycje[position]=false;
                    Toast.makeText(getApplicationContext(),"Zaznaczyłeś/Odznaczyłeś: "+(position+1),Toast.LENGTH_SHORT).show();
                }
            });

         return listitemView;
        }
    }


    String[] ltxt1 = {"Pozycja 1", "Pozycja 2","Pozycja 3", "Pozycja 4","Pozycja 5", "Pozycja 6","Pozycja 7", "Pozycja 8","Pozycja 9", "Pozycja 10","Pozycja 11", "Pozycja 12","Pozycja 13", "Pozycja 14"};
    String[] ltxt2 = {"Tekst  1", "Tekst  2","Tekst  3", "Tekst  4","Tekst  5", "Tekst  6","Tekst  7", "Tekst  8","Tekst  9", "Tekst  10","Tekst  11", "Tekst  12","Tekst  13", "Tekst  14"};
    ListView lista3;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista3);

        adapter = new MyAdapter(ltxt1);
        lista3 = (ListView) findViewById(R.id.listView3);
        lista3.setAdapter(adapter);

        lista3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ltxt1[position] = "";
                ltxt2[position] = "";
                Toast.makeText(getApplicationContext(),"Usunięto" + position,Toast.LENGTH_SHORT).show();
                return false;

            }
        });

        ltxt1[5]= "to nie jest test";
        change();
    }

    protected void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(),"Lista3",Toast.LENGTH_SHORT).show();
    }

    public void uruchomOpen(View view){
        final Intent intencja2 = new Intent(this, OpenItem.class);
        startActivity(intencja2);
    }

    public void powrotDoMain(View view){
        onBackPressed();
    }

    public void change(View view){
        ltxt1[3]= "ala ma kota";
        Toast.makeText(getApplicationContext(),"Kliknieto change",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    public void change(){
        ltxt1[6]= "ala ma kota 3 tysiące";
        Toast.makeText(getApplicationContext(),"Kliknieto change",Toast.LENGTH_SHORT).show();
    }

    public void uruchomAdd(View view){
        final Intent intencja2 = new Intent(this, Add.class);
        startActivity(intencja2);
    }

    public void text(View view){
        Toast.makeText(getApplicationContext(),"Kliknieto " ,Toast.LENGTH_SHORT).show();

    }


}