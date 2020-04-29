package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;

import android.widget.LinearLayout;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.ViewPagerAdapter;


public class StartActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private LinearLayout dotsLayout;

    private String[] text = {"Aquí puedes llevar el seguimiento del desarrollo y crecimiento de tus hijos.", "Controla las curvas de crecimiento de tu hijo y manten actualizado a tu pediatra", "Podrás que llevar el control de las vacunas de tu hijo siempre en tu celular", "Puedes enviarle mesajes a tu pediatra y si te receta algo, se cargará a la historia clínica"};
    private String[] title = {"¡Bienvenido!","Curvas de crecimiento", "Carnet de vacunas", "Contacta a tu pediatra"};
    private int[] image = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dotsLayout);
        loadViewPager();

    }

    public void loadViewPager(){

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for(int i=0; i<title.length; i++){

            adapter.addFragment(newInstance(title[i],text[i],image[i], i==title.length-1));

        }

        viewPager.setAdapter(adapter);

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


}
