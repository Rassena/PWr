package com.example.cwiczenie_3_4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Options_f1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Lista_f1 extends Fragment implements OpenItem_f1.OpenItemListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public MyAdapter adapter;
    ListView lista;
    ArrayList<ListElement> ItemList;
    private int color;

    @Override
    public void onOpenInputSent(Bundle bundle) {
        adapter.notifyDataSetChanged();
    }

    public interface ListFragmentListener {
        void onListInputSent(Bundle bundle);
    }
    private ListFragmentListener listener;

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

    }


    public class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater = null;
        boolean[] zazn_pozycje;
        LVitem myLVitem;

        MyAdapter(ArrayList<ListElement> lista) {
            super();
            zazn_pozycje = new boolean[lista.size()];
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return ItemList.size();
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
                myLVitem.Color = listitemView.findViewById(R.id.view_Row);

                listitemView.setTag(myLVitem);
            }
            else
                myLVitem = (LVitem) listitemView.getTag();

            myLVitem.tv1.setText(ItemList.get(position).name);
            myLVitem.tv2.setText("Wiek: "+ ItemList.get(position).age);

            switch (ItemList.get(position).gender){
                case 0:
                    myLVitem.img.setImageResource(R.drawable.female);
                    break;
                case 1:
                    myLVitem.img.setImageResource(R.drawable.male);
                    break;
                case 2:
                    myLVitem.img.setImageResource(R.drawable.agender);
                    break;
                case 3:
                    myLVitem.img.setImageResource(R.drawable.trap);
                    break;
                case 4:
                    myLVitem.img.setImageResource(R.drawable.trapreverse);
                    break;
                case 5:
                    myLVitem.img.setImageResource(R.drawable.nonbinary);
                    break;
            }


            color = Color.rgb(ItemList.get(position).RedProgress,ItemList.get(position).GreenProgress,ItemList.get(position).BlueProgress);
            myLVitem.Color.setBackgroundColor(color);


            listitemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(),"Usunąłeś: "+ ItemList.get(position).name,Toast.LENGTH_LONG).show();
                    ItemList.remove(position);
                    SaveList();
                    notifyDataSetChanged();
                    return true;

                }
            });

            listitemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (getResources().getConfiguration().orientation == 1) {
                        Intent intent = new Intent(getActivity(), OpenItem.class);

                        intent.putExtra("name", ItemList.get(position).name);
                        intent.putExtra("number", ItemList.get(position).number);
                        intent.putExtra("age", ItemList.get(position).age);
                        intent.putExtra("redprogress", ItemList.get(position).RedProgress);
                        intent.putExtra("blueprogress", ItemList.get(position).BlueProgress);
                        intent.putExtra("greenProgress", ItemList.get(position).GreenProgress);
                        intent.putExtra("gender", ItemList.get(position).gender);
                        intent.putExtra("rating", ItemList.get(position).rating);
                        intent.putExtra("position", position);

                        startActivityForResult(intent, 2);
                        Toast.makeText(getActivity().getApplicationContext(), " F Wybrałeś: " + ItemList.get(position).name, Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Bundle bundle = new Bundle();

                        bundle.putString("name", ItemList.get(position).name);
                        bundle.putString("number", ItemList.get(position).number);
                        bundle.putString("age", ItemList.get(position).age);
                        bundle.putInt("redprogress", ItemList.get(position).RedProgress);
                        bundle.putInt("blueprogress", ItemList.get(position).BlueProgress);
                        bundle.putInt("greenProgress", ItemList.get(position).GreenProgress);
                        bundle.putInt("gender", ItemList.get(position).gender);
                        bundle.putFloat("rating", ItemList.get(position).rating);
                        bundle.putInt("position", position);
                        Toast.makeText(getActivity().getApplicationContext(), "Kliknalles: " + ItemList.get(position).name, Toast.LENGTH_LONG).show();

                        listener.onListInputSent(bundle);
                    }

                }
            });

            return listitemView;
        }
    }

    public Lista_f1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Options_f1.
     */
    // TODO: Rename and change types and number of parameters
    public static Lista_f1 newInstance(String param1, String param2) {
        Lista_f1 fragment = new Lista_f1();
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
        return inflater.inflate(R.layout.fragment_lista_f1, container, false);
    }


    
    public void onStart(){
        super.onStart();
        LoadData();
        adapter = new MyAdapter(ItemList);
        lista = (ListView) getActivity().findViewById(R.id.listView);
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LoadData();
        adapter = new MyAdapter(ItemList);
        lista = (ListView) getActivity().findViewById(R.id.listView);
        //lista.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ListElement Element = new ListElement();
        if (requestCode == 1) {
            if (resultCode == getActivity().RESULT_OK) {
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
            if (resultCode == getActivity().RESULT_OK) {
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Lista)
        {
        }
        LoadData();

        if (context instanceof ListFragmentListener) {
            listener = (ListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ListFragmentListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }



    private void SaveList()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences( "shared preferences", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ItemList);
        editor.putString("List",json);
        editor.apply();
    }
    private void LoadData()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences( "shared preferences", getActivity().MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("List" ,null);
        Type type = new TypeToken<ArrayList<ListElement>>() {}.getType();
        ItemList = gson.fromJson(json,type);
        if(ItemList == null)
        {
            ItemList = new ArrayList<>();
        }

    }
}