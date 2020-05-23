package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextView email, password;
    private Button signIn, signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.name);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);
        signIn.setOnClickListener(

                (v) -> {

                    boolean mail = TextUtils.isEmpty(email.getText().toString().trim());
                    boolean pass = TextUtils.isEmpty(password.getText().toString().trim());
                    if(mail == false && pass == false){

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                .addOnSuccessListener(
                                        authResult -> {

                                            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();


                                            Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
                                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    for(DataSnapshot child: dataSnapshot.getChildren()){

                                                        if(child.getKey().equals(id)){

                                                            //Pediatra pediatra = child.getValue(Pediatra.class);
                                                            Intent intent = new Intent(v.getContext(), ActivityMainPediatra.class);
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

                                                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                                                            startActivity(intent);

                                                        }

                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });

                                        }
                                ).addOnFailureListener(e->{
                            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        });

                    }else {

                        Toast.makeText(getApplicationContext(), "Ingrese sus datos de usuario o regístrese para continuar", Toast.LENGTH_LONG).show();

                    }

                }

        );

        signUp.setOnClickListener(

                (v) -> {

                Intent intent = new Intent(v.getContext(), SignUpActivity.class);
                startActivityForResult(intent, 0);

                }

        );


    }




}
