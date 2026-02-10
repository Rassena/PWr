package com.example.cwiczenie_3_4;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Options extends AppCompatActivity {

    private static final int NUM_PAGES = 3;

    private ViewPager2 viewPager;
    private Menu menu;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("Frame " + (position + 1))
        ).attach();
    }
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, R.id.agender, Menu.NONE, "agender");
        menu.add(Menu.NONE, R.id.female, Menu.NONE, "female");
        menu.add(Menu.NONE, R.id.male, Menu.NONE, "male");
        menu.add(Menu.NONE, R.id.nonbinary, Menu.NONE, "nonbinary");
        menu.add(Menu.NONE, R.id.reversetrap, Menu.NONE, "reversetrap");
        menu.add(Menu.NONE, R.id.trap, Menu.NONE, "trap");
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {


            switch (position) {
                case 0:
                    return new Options_f1();
                case 1:
                    return new Options_f2();
                case 2:
                    return new Options_f3();
                default:
                    return new Options_f1();
            }


        }



        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

}