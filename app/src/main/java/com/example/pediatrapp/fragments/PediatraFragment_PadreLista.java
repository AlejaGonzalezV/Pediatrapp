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

        Log.e(">>>", "Set");
        adapter_padreList = new PediatraAdapter_PadreList(getContext(), padres);
        pediatra_padresList.setAdapter(adapter_padreList);

        
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

        ArrayList<String> idPadresAsignados = new ArrayList<>();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.e(">>>", firebaseUser.getUid());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(firebaseUser.getUid()).child("Padres_asignados");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                idPadresAsignados.clear();

                Log.e(">>>", "Busca");

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String id = snapshot.getValue(String.class);
                    if(id != null){
                        Log.e(">>>", "Encuentra");
                        idPadresAsignados.add(id);
                    }


                }

                loadParents(idPadresAsignados);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void loadParents(ArrayList<String> idpadres) {
        padres.clear();
        Log.e(">>>", "Entra");
        for(int i = 0; i< idpadres.size(); i++){

            Log.e(">>>", idpadres.get(i));
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Padres").child(idpadres.get(i));

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                    for(DataSnapshot snapshot : dataSnapshot){

//                        Log.e(">>>", snapshot.getValue(String.class));
                        Padre padre = dataSnapshot.getValue(Padre.class);
                        if(padre != null){
                            Log.e(">>>", "Añade" + padre.getNombre());
                         //   padres.add(padre);
                            adapter_padreList.addPadre(padre);
                        }

//                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

       //set


    }
}
