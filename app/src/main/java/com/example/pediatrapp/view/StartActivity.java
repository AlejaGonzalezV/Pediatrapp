package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.graphics.Color;
import android.os.Bundle;

import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.ViewPagerAdapter;
import com.example.pediatrapp.fragments.Slider;


public class StartActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private LinearLayout dotsLayout;

    private String[] text = {"Aquí puedes llevar el seguimiento del desarrollo y crecimiento de tus hijos.", "Controla las curvas de crecimiento de tu hijo y manten actualizado a tu pediatra", "Podrás que llevar el control de las vacunas de tu hijo siempre en tu celular", "Puedes enviarle mesajes a tu pediatra y si te receta algo, se cargará a la historia clínica"};
    private String[] title = {"¡Bienvenido!","Curvas de crecimiento", "Carnet de vacunas", "Contacta a tu pediatra"};
    private int[] image = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4};
    private int colorDot;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        colorDot = getResources().getColor(R.color.dot_1);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dotsLayout);
        addDots(0);
        loadViewPager();

    }

    private void addDots(int actual){

        dots=new TextView[title.length];

        dotsLayout.removeAllViews();

        for(int i=0; i<dots.length; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);

            if(i==actual){

                dots[i].setTextColor(colorDot);

            }else {

                dots[i].setTextColor(Color.GRAY);

            }


        }

    }

    public void loadViewPager(){

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for(int i=0; i<title.length; i++){

            adapter.addFragment(newInstance(title[i],text[i],image[i], i==title.length-1));

        }

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pagelistener);

    }

    private Slider newInstance(String title, String text, int image, boolean ya){

        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("text", text);
        bundle.putInt("image", image);
        bundle.putBoolean("ya", ya);

        Slider sd = new Slider();
        sd.setArguments(bundle);
        return sd;
    }

    ViewPager.OnPageChangeListener pagelistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
