package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.pagercontroller.PagerController;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import de.hdodenhof.circleimageview.CircleImageView;

public class ParentPerfil extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1, tab2 ;
    private PagerController pagerAdapater;
    private String idParent;
    private Button back;
    private TextView nombre_padre;
    private CircleImageView padre_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_perfil);

        Intent intent = getIntent();
        idParent = intent.getStringExtra("userid");
        back = findViewById(R.id.back);
        tabLayout = findViewById(R.id.tabLayout_parent);
        viewPager = findViewById(R.id.viewPagerPerfil);
        tab1 = findViewById(R.id.tabHijos);
        tab2 = findViewById(R.id.tabInformacion);
        nombre_padre = findViewById(R.id.nombre_padre);
        padre_foto = findViewById(R.id.padre_foto);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        Query query = FirebaseDatabase.getInstance().getReference().child("Padres");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Padre padre = child.getValue(Padre.class);
                    if(padre.getId().equals(idParent)){

                        nombre_padre.setText(padre.getNombre());
                        storage.getReference().child("Padre").child(padre.getFoto()).getDownloadUrl().addOnSuccessListener(
                                uri -> {
                                    Glide.with(getApplicationContext()).load(uri).centerCrop().into(padre_foto);
                                }
                        );

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back.setOnClickListener(

                (v)->{

                    this.finish();

                }

        );

        pagerAdapater = new PagerController(getSupportFragmentManager(), tabLayout.getTabCount(), idParent);
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
