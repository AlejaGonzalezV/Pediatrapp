package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.pediatrapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class InitialActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        ActivityCompat.requestPermissions(this, new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

        /*
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        startActivity(intent);
         */


        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

            Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot child: dataSnapshot.getChildren()){

                        if(child.getKey().equals(id)){


                            new Handler().postDelayed(() -> {
                                Intent intent = new Intent(getApplicationContext(), ActivityMainPediatra.class);
                                startActivity(intent);
                                finish();
                            }, DURACION_SPLASH);



                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            Query query2 = FirebaseDatabase.getInstance().getReference().child("Padres");
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot child: dataSnapshot.getChildren()){

                        if(child.getKey().equals(id)){


                            new Handler().postDelayed(() -> {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }, DURACION_SPLASH);



                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else {


            new Handler().postDelayed(() -> {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                finish();
            }, DURACION_SPLASH);


        }



    }
}
