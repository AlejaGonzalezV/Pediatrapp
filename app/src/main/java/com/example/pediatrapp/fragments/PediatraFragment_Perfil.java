package com.example.pediatrapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;

public class PediatraFragment_Perfil extends Fragment {

    private ImageView pediatra_foto;
    private TextView nombre_pediatra;
    private TextView cedula_pediatra;
    private TextView email_pediatra;
    private TextView numeroUnico_pediatra;
    private Button cerrarSesion;

    public PediatraFragment_Perfil() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pediatrafragment_perfil, container, false);

        pediatra_foto = view.findViewById(R.id.pediatra_foto);
        nombre_pediatra = view.findViewById(R.id.nombre_pediatra);
        cedula_pediatra = view.findViewById(R.id.cedula_pediatraCV);
        email_pediatra = view.findViewById(R.id.email_pediatra);
        numeroUnico_pediatra = view.findViewById(R.id.numeroID);
        cerrarSesion = view.findViewById(R.id.next);

        return view;

    }


}
