package com.example.pediatrapp.pagercontroller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter {
    private int numoftabs;
    private String id;

    public PagerController(FragmentManager fm, int behavior, String id) {
        super(fm);

        this.id = id;
        this.numoftabs =  behavior;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Hijos(id);


            case 1:
                return new Informacion(id);

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
