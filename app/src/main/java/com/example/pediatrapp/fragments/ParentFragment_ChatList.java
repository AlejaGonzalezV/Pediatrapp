package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.PadreAdapter_ChatList;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.view.MessageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentFragment_ChatList extends Fragment {

    private EditText SearchChatPaET;
    private ImageButton SearchChatPaBT;
    private ListView padre_ChatList;
    private Button FiltroChatPaBT;
    private ArrayList<Pediatra> pediatras;
    private PadreAdapter_ChatList adapter;

    public ParentFragment_ChatList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parentfragment_chatlist, container, false);

        SearchChatPaET = view.findViewById(R.id.SearchChatPaET);
        SearchChatPaBT = view.findViewById(R.id.SearchChatPaBT);
        padre_ChatList = view.findViewById(R.id.padre_ChatList);
        FiltroChatPaBT = view.findViewById(R.id.FiltroChatPaBT);

        pediatras = new ArrayList<>();
        adapter = new PadreAdapter_ChatList();
        padre_ChatList.setAdapter(adapter);

        readPediatras();


        padre_ChatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Pediatra p = (Pediatra) adapter.getItem(i);
                new Thread(
                        () ->{

                            Intent intent = new Intent(getActivity(), MessageActivity.class);
                            intent.putExtra("userid", p.getId());
                            intent.putExtra("type", "pediatra");
                            Log.e(">>>", "inicioIntent");
                            getActivity().startActivity(intent);
                        }
                ).start();

            }
        });


        SearchChatPaBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>","BuscarChat");
                String nombre = SearchChatPaET.getText().toString();
                adapter.setPediatras(buscarChat(nombre));
            }
        });

        FiltroChatPaBT.setOnClickListener(
                (v) ->{
                    Log.e(">>>", "FiltroChat");
                    adapter.setPediatras(pediatras);
                    SearchChatPaET.setText("");
                }
        );
        return view;
    }

    public ArrayList<Pediatra> buscarChat(String nombre){
        ArrayList<Pediatra> resultado = new ArrayList<>();

        for(int i = 0; i< adapter.getPediatras().size(); i++){
            if(adapter.getPediatras().get(i).getNombre().contains(nombre)){
                resultado.add(adapter.getPediatras().get(i));
            }
        }
        return  resultado;
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
                        int pos = 0;
                        boolean exist = false;
                        for (int i = 0; i< adapter.getPediatras().size() && !exist; i++){

                            if(adapter.getPediatras().get(i).getId().equals(pediatra.getId())){
                                pos = i;
                                exist = true;
                            }

                        }

                        if(exist){
                            adapter.getPediatras().remove(pos);
                            pediatras.remove(pos);
                            adapter.addPediatra(pediatra);
                            pediatras.add(pediatra);
                        }else{
                            adapter.addPediatra(pediatra);
                            pediatras.add(pediatra);
                            Log.e(">>>", "size: "+pediatras.size());
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }


    }

}
