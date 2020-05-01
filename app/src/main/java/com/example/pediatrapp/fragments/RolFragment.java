package com.example.pediatrapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.view.SignUpActivity;

public class RolFragment extends Fragment {

    private View view;
    private Button padre,pediatra,back;
    private OnDataSubmitted listener;


    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_rol,container,false);

        padre = view.findViewById(R.id.padre);
        pediatra = view.findViewById(R.id.pediatra);
        back = view.findViewById(R.id.back);

        padre.setOnClickListener(

                (v) -> {

                    if(listener != null){

                        listener.onData(this, "padre", null);

                    }


                }

        );

        pediatra.setOnClickListener(

                (v) -> {

                    if(listener != null){

                        listener.onData(this, "pediatra", null);

                    }

                }

        );

        back.setOnClickListener(

                (v) -> {


                    if(listener != null){

                        listener.onData(this, "back", null);

                    }


                }

        );


        return view;



    }

}
