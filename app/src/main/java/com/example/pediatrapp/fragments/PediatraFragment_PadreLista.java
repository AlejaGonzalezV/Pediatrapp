package com.example.pediatrapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;

public class PediatraFragment_PadreLista extends Fragment {

    private EditText SearchPadreET;
    private Button FiltroBT;
    private ImageButton SearchPadreBT;
    private ListView pediatra_padresList;

    public PediatraFragment_PadreLista() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pediatrafragment_padreslist, container, false);

        SearchPadreET = view.findViewById(R.id.SearchPadreET);
        FiltroBT = view.findViewById(R.id.FiltroBT);
        SearchPadreBT = view.findViewById(R.id.SearchPadreBT);
        pediatra_padresList = view.findViewById(R.id.pediatra_padresList);

        FiltroBT.setOnClickListener(
                (v)->{
                    Log.e(">>>", "Filtro");
                }
        );

        SearchPadreBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>", "Search");
            }
        });



        return view;
    }
}
