package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.pagercontroller.PagerController;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;

public class ParentPerfil extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1, tab2 ;
    private PagerController pagerAdapater;
    private String idParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_perfil);

        Intent intent = getIntent();
        idParent = intent.getStringExtra("userid");

        tabLayout = findViewById(R.id.tabLayout_parent);
        viewPager = findViewById(R.id.viewPager);
        tab1 = findViewById(R.id.tabHijos);
        tab2 = findViewById(R.id.tabInformacion);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        pagerAdapater = new PagerController(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapater);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    pagerAdapater.notifyDataSetChanged();
                }

                if(tab.getPosition()==1){
                    pagerAdapater.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
