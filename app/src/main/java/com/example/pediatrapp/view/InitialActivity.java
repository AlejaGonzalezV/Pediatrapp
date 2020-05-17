package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pediatrapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        startActivity(intent);

        /*
        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

            Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot child: dataSnapshot.getChildren()){

                        if(child.getKey().equals(id)){

                            Intent intent = new Intent(getApplicationContext(), ActivityMainPediatra.class);
                            startActivity(intent);

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

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else {

            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);

        }

         */

    }
}
