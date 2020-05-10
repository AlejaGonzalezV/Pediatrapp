package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.google.firebase.auth.FirebaseAuth;

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


                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(
                                    authResult -> {

                                        //Si es user normal
//                                        Intent intent = new Intent(v.getContext(), MainActivity.class);
//                                        startActivity(intent);

                                        //Si es pediatra
                                        Intent intent = new Intent(v.getContext(), ActivityMainPediatra.class);
                                        startActivity(intent);

                                    }
                            ).addOnFailureListener(e->{
                        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    });


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
