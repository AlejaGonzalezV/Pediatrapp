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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.PediatraAdapter_PadreList;
import com.example.pediatrapp.model.Padre;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PediatraFragment_PadreLista extends Fragment {

    private EditText SearchPadreET;
    private Button FiltroBT;
    private ImageButton SearchPadreBT;
    private RecyclerView pediatra_padresList;
    private PediatraAdapter_PadreList adapter_padreList;
    private List<Padre> padres;

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

        pediatra_padresList.setHasFixedSize(true);
        pediatra_padresList.setLayoutManager(new LinearLayoutManager(getContext()));

        padres = new ArrayList<Padre>();
        
        readParents();

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

    private void readParents() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

       // DatabaseReference reference = FirebaseDatabase.getInstance().getReference()

    }
}
