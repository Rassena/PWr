package com.example.cwiczenie_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {

    private Context ctx;

    public Integer[] id_obrazkow = {
            R.drawable.cat, R.drawable.rabbit, R.drawable.unicorn, R.drawable.shout,
            R.drawable.cat, R.drawable.rabbit, R.drawable.unicorn, R.drawable.shout,
            R.drawable.cat, R.drawable.rabbit, R.drawable.unicorn, R.drawable.shout
    };

    public MyAdapter(Context c){
        ctx =c;
    }

    @Override
    public int getCount() {
        return id_obrazkow.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View cV, ViewGroup parent) {
        ImageView mV;

        if (cV ==null){
            mV = new ImageView(ctx);
            mV.setLayoutParams(new ViewGroup.LayoutParams(200,200));
            mV.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mV.setPadding(8,8,8,8);
        }
        else
            mV = (ImageView) cV;
        mV.setImageResource(id_obrazkow[position]);
        return mV;
    }
}
