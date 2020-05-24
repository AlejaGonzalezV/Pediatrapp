package com.example.pediatrapp.pagercontroller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter {
    int numoftabs;

    public PagerController(FragmentManager fm, int behavior) {
        super(fm);

        this.numoftabs =  behavior;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Hijos();


            case 1:
                return new Informacion();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
