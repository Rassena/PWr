package com.example.cwiczenie_3_4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Options_f2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Options_f2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public Options_f2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Options_f2.
     */
    // TODO: Rename and change types and number of parameters
    public static Options_f2 newInstance(String param1, String param2) {
        Options_f2 fragment = new Options_f2();
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
        return inflater.inflate(R.layout.fragment_options_f2, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ImageView image = view.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.agender);
        getActivity().registerForContextMenu(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.showContextMenu();
            }
        });
    }


    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.imageView)
        {
            menu.add(Menu.NONE, R.id.agender, Menu.NONE, "agender");
            menu.add(Menu.NONE, R.id.female, Menu.NONE, "female");
            menu.add(Menu.NONE, R.id.male, Menu.NONE, "male");
            menu.add(Menu.NONE, R.id.nonbinary, Menu.NONE, "nonbinary");
            menu.add(Menu.NONE, R.id.reversetrap, Menu.NONE, "reversetrap");
            menu.add(Menu.NONE, R.id.trap, Menu.NONE, "trap");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ImageView image = getActivity().findViewById(R.id.imageView);
        switch(item.getItemId())
        {
            case R.id.agender:
                image.setImageResource(R.drawable.agender);
                return true;
            case R.id.female:
                image.setImageResource(R.drawable.female);

                return true;
            case R.id.male:
                image.setImageResource(R.drawable.male);
                return true;
            case R.id.nonbinary:
                image.setImageResource(R.drawable.nonbinary);
                return true;
            case R.id.reversetrap:
                image.setImageResource(R.drawable.reversetrap);
                return true;
            case R.id.trap:
                image.setImageResource(R.drawable.trap);
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }



}