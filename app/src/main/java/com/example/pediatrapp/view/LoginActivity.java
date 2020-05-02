package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.pediatrapp.R;

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

                    Intent intent = new Intent(v.getContext(), ActivityMainPediatra.class);
                    startActivity(intent);

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
