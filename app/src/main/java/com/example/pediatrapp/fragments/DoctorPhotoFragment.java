package com.example.pediatrapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;

public class DoctorPhotoFragment extends Fragment {

    private Button back, next, firma, profilePhoto;
    private ImageView photoIV;
    private View view;
    private OnDataSubmitted listener;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_doctor_photo, container, false);

        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        firma = view.findViewById(R.id.firma);
        profilePhoto = view.findViewById(R.id.profilePhoto);

        next.setOnClickListener(

                (v) -> {

                    if (listener != null) {

                        //Paso las imagenes al SignUpActivity

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

        firma.setOnClickListener(

                (v) -> {

                    //Abre los docs del celular

                }

        );

        profilePhoto.setOnClickListener(

                (v) -> {

                    //Abre la galería

                }

        );

        return view;

    }




}
