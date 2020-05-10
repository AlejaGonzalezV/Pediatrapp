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
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;

public class ParentFragment_PediatraList extends Fragment {

    private EditText SearchPediatraET;
    private ImageButton SearchPediatraBT;
    private Button FiltroPaBT;
    private RecyclerView padre_pediatraList;


    public ParentFragment_PediatraList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parentfragment_pediatraslist, container, false);

        SearchPediatraET = view.findViewById(R.id.SearchPediatraET);
        SearchPediatraBT = view.findViewById(R.id.SearchPediatraBT);
        FiltroPaBT = view.findViewById(R.id.FiltroPaBT);
        padre_pediatraList = view.findViewById(R.id.padre_pediatraList);

        SearchPediatraBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>", "BuscarPediatra");
            }
        });

        FiltroPaBT.setOnClickListener(
                (v) ->{
                    Log.e(">>>", "FiltroPad");
                }
        );

        return view;
    }
}

