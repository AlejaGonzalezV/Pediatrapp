package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.PediatraAdapter_ChatList;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.view.ActivityMainPediatra;
import com.example.pediatrapp.view.MessageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PediatraFragment_ChatList extends Fragment {

    private EditText SearchChatET;
    private ImageButton SearchChatBT;
    private Switch switchDisp;
    private ListView pediatra_ChatList;
    private Button FiltroChatBT;
    private PediatraAdapter_ChatList adapter;
    private ArrayList<Padre> padres;

    public PediatraFragment_ChatList() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pediatrafragment_chatlist, container, false);

        SearchChatET = view.findViewById(R.id.SearchChatET);
        SearchChatBT = view.findViewById(R.id.SearchChatBT);
        switchDisp = view.findViewById(R.id.switchDisp);
        pediatra_ChatList = view.findViewById(R.id.pediatra_ChatList);
        FiltroChatBT = view.findViewById(R.id.FiltroChatBT);

        FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(fuser.getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Pediatra ped = dataSnapshot.getValue(Pediatra.class);
                if(ped.getEstado().equals("offline")){
                    switchDisp.setChecked(false);
                }else{
                    switchDisp.setChecked(true);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        padres = new ArrayList<Padre>();
        adapter = new PediatraAdapter_ChatList();
        pediatra_ChatList.setAdapter(adapter);

        Log.e(">>>", "Set");

        readParents();

        switchDisp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    changeEstado("online");
                }else{
                    changeEstado("offline");
                }
            }
        });

        pediatra_ChatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Padre p = (Padre) adapter.getItem(i);
                new Thread(
                        () ->{

                            Intent intent = new Intent(getActivity(), MessageActivity.class);
                            intent.putExtra("userid", p.getId());
                            intent.putExtra("type", "padre");
                            Log.e(">>>", "inicioIntent");
                            getActivity().startActivity(intent);
                        }
                ).start();

            }
        });


        SearchChatBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>", "Buscar");
                String nombre = SearchChatET.getText().toString();
                adapter.setPadres(buscarChat(nombre));
            }
        });

        FiltroChatBT.setOnClickListener(
                (v)->{
                    Log.e(">>>","FiltroChat");
                    adapter.setPadres(padres);
                    SearchChatET.setText("");
                }
        );
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
     //   padres.clear();
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
                        int pos = 0;
                        boolean exist = false;
                        for (int i = 0; i< adapter.getPadres().size() && !exist; i++){

                            if(adapter.getPadres().get(i).getId().equals(padre.getId())){
                                pos = i;
                                exist = true;
                            }

                        }

                        if(exist){
                            adapter.getPadres().remove(pos);
                            padres.remove(pos);
                            adapter.addPadre(padre);
                            padres.add(padre);
                        }else{
                            adapter.addPadre(padre);
                            padres.add(padre);
                            Log.e(">>>", "size: "+padres.size());
                        }

                    }

//                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

    }

    public ArrayList<Padre> buscarChat(String nombre){
        ArrayList<Padre> resultado = new ArrayList<>();

        for(int i = 0; i< adapter.getPadres().size(); i++){
            if(adapter.getPadres().get(i).getNombre().contains(nombre)){
                resultado.add(adapter.getPadres().get(i));
            }
        }

        return  resultado;
    }

    public void changeEstado(String estado){
        FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Pediatras").child(fuser.getUid()).child("estado").setValue(estado);

    }

}
