package com.example.pediatrapp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;

public class Slider extends Fragment {

    private View view;
    private ImageView imageView;
    private Button empezar;
    private TextView texto;
    private TextView titulo;
    private boolean ya = false;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_slider,container,false);
        imageView = view.findViewById(R.id.imageView);
        empezar = view.findViewById(R.id.empezar);
        empezar.setEnabled(false);
        empezar.setBackgroundColor(Color.GRAY);
        texto = view.findViewById(R.id.texto);
        titulo = view.findViewById(R.id.titulo);
        if(getArguments()!=null){

            titulo.setText(getArguments().getString("title"));
            texto.setText(getArguments().getString("text"));
            imageView.setImageResource(getArguments().getInt("image"));
            ya = getArguments().getBoolean("ya");
            if(ya){

                empezar.setEnabled(true);
                empezar.setBackgroundColor(Color.parseColor("#2F80ED"));

            }

        }

        empezar.setOnClickListener(

                (v) -> {


                    if(!ya){

                        Toast.makeText(getActivity(), "Termine las instrucciones antes de continuar", Toast.LENGTH_LONG).show();

                    } else {




                    }

                }

        );



        return view;
    }
}
