package com.example.pediatrapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.PadreAdapter_PediatraList;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ParentFragment_PediatraList extends Fragment {

    private EditText SearchPediatraET;
    private ImageButton SearchPediatraBT;
    private Button FiltroPaBT;
    private RecyclerView padre_pediatraList;
    private PadreAdapter_PediatraList adapter_pediatraList;
    private List<Pediatra> pediatras;


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

        padre_pediatraList.setHasFixedSize(true);
        padre_pediatraList.setLayoutManager(new LinearLayoutManager(getContext()));
        pediatras = new ArrayList<>();

        adapter_pediatraList = new PadreAdapter_PediatraList(getContext(), pediatras);
        padre_pediatraList.setAdapter(adapter_pediatraList);
        readPediatras();

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

    private void readPediatras() {

        ArrayList<String> idPediatrasAsignados = new ArrayList<>();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.e(">>>", firebaseUser.getUid());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Padres").child(firebaseUser.getUid()).child("pediatras_asig");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                idPediatrasAsignados.clear();

                Log.e(">>>", "Busca");

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String id = snapshot.getValue(String.class);
                    if(id != null){
                        Log.e(">>>", "Encuentra");
                        idPediatrasAsignados.add(id);
                    }


                }

                loadPediatras(idPediatrasAsignados);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void loadPediatras(ArrayList<String> idPediatrasAsignados) {

        pediatras.clear();
        Log.e(">>>", "Entra");
        for(int i = 0; i< idPediatrasAsignados.size(); i++){

            Log.e(">>>", idPediatrasAsignados.get(i));
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(idPediatrasAsignados.get(i));

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Pediatra pediatra = dataSnapshot.getValue(Pediatra.class);
                    if(pediatra != null){
                        Log.e(">>>", "Añade" + pediatra.getNombre());
                        //   padres.add(padre);
                        adapter_pediatraList.addPediatra(pediatra);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }


    }
}

