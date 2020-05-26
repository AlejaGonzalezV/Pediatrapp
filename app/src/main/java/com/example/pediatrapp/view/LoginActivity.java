package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Chat;
import com.example.pediatrapp.model.ChatGrupal;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends AppCompatActivity {

    private TextView email, password;
    private Button signIn, signUp;
    private String type = "";


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
                    if (mail == false && pass == false) {


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

                                                            Query queryC = FirebaseDatabase.getInstance().getReference().child("chat");
                                                            queryC.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                    for(DataSnapshot child: dataSnapshot.getChildren()){

                                                                        Chat ch = child.getValue(Chat.class);
                                                                        if(ch.getId_pediatra().equals(id)){



                                                                            FirebaseMessaging.getInstance().subscribeToTopic(ch.getId());

                                                                        }

                                                                    }

                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                }
                                                            });

                                                            //Lanzo
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

                                                            Log.e("<<<<<<<<", "Encontradooo");
                                                            Query queryC = FirebaseDatabase.getInstance().getReference().child("chat");
                                                            queryC.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                    for(DataSnapshot child: dataSnapshot.getChildren()){

                                                                        Chat chat = child.getValue(Chat.class);
                                                                        Log.e("CHAT NULO?", (chat==null)+"");
                                                                        if(chat.getId_padre().equals(id)){

                                                                            Log.e("<<<<<<<<<<", "DESUSCRIBIENDO");
                                                                            FirebaseMessaging.getInstance().subscribeToTopic(chat.getId());


                                                                        }

                                                                    }

                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                }
                                                            });


                                                            //Lanzo
                                                            Log.e("<<<<<<<<<<", "entrandooo");
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
                                ).addOnFailureListener(e -> {
                            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        });

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

    public void verificar(String ident){

        Log.e("<<<<<<<< ESTE ID <<<<<<", ident);
        Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    if(child.getKey().equals(ident)){

                        type = "Pediatra";

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

                    if(child.getKey().equals(ident)){

                        type = "Padre";
                        Log.e("<<<<<<<<<<<", "DETECTADOOOO");

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void chatsPed(String id){

        Query queryC = FirebaseDatabase.getInstance().getReference().child("chat");
        queryC.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Chat ch = child.getValue(Chat.class);
                    if(ch.getId_pediatra().equals(id)){

                        FirebaseMessaging.getInstance().subscribeToTopic(ch.getId());

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void chatsPad(String id){

        Query queryC = FirebaseDatabase.getInstance().getReference().child("chat");
        queryC.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Chat chat = child.getValue(Chat.class);
                    if(chat.getId_padre().equals(id)){

                        FirebaseMessaging.getInstance().subscribeToTopic(chat.getId());
                        Log.e("<<<<<<<<<<<", "SUSCRIBIENDOOOOO");

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

/*
    verificar(id);

                                            Log.e("<<<<<<<<<<<<<TIPO", type);
                                            if (type.equals("Padre")) {

        chatsPad(id);
        Log.e("<<<<<<<<<<", "entrandooo");
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);

    } else if (type.equals("Pediatra")) {

        chatsPed(id);
        Intent intent = new Intent(v.getContext(), ActivityMainPediatra.class);
        startActivity(intent);

    }


 */



}
