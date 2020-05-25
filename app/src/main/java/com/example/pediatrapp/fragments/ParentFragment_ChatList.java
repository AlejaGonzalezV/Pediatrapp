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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.Adapter_ChatG;
import com.example.pediatrapp.adapter.PadreAdapter_ChatList;
import com.example.pediatrapp.model.ChatGrupal;
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
import java.util.Date;
import java.util.HashMap;

public class ParentFragment_ChatList extends Fragment {

    private EditText SearchChatPaET;
    private ImageButton SearchChatPaBT;
    private ListView padre_ChatList;
    private Button FiltroChatPaBT;
    private Button chatGrupalBT;
    private ArrayList<Pediatra> pediatras;
    private PadreAdapter_ChatList adapter;
    private Adapter_ChatG adapterG;
    private ListView padre_chatGrupal;
    private String chatGrup;
    private  HashMap<String, String> pedia;


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
        padre_chatGrupal = view.findViewById(R.id.padre_chatGrupal);
        chatGrupalBT = view.findViewById(R.id.chatGrupalBT);

        pediatras = new ArrayList<>();
        adapter = new PadreAdapter_ChatList();
        padre_ChatList.setAdapter(adapter);

        adapterG = new Adapter_ChatG();
        padre_chatGrupal.setAdapter(adapterG);

        chatGrup = "";
        readChatGrupal();
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

        padre_chatGrupal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ChatGrupal cg = (ChatGrupal) adapterG.getItem(i);
                new Thread(
                        () ->{

//                            Intent intent = new Intent(getActivity(), MessageActivity.class);
//                            intent.putExtra("userid", p.getId());
//                            intent.putExtra("type", "pediatra");
                            Log.e(">>>", "inicioIntent");
//                            getActivity().startActivity(intent);
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


        chatGrupalBT.setOnClickListener(
                (v) -> {

                    if(adapterG.getCount() >0){

                        Toast.makeText(getActivity().getApplicationContext(), "Ya existe un chat temporal activo", Toast.LENGTH_SHORT).show();
                    }else{

                        crearChatGrupal();

                    }

                }
        );
        return view;
    }

    private void crearChatGrupal() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.e(">>>", firebaseUser.getUid());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Padres").child(firebaseUser.getUid());

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Padre p = dataSnapshot.getValue(Padre.class);

                initializeChat(p);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initializeChat(Padre p) {

        pedia = new HashMap<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Pediatras");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child : dataSnapshot.getChildren()){

                    pedia.put(child.getKey(), child.getKey());
                }

                String idc = FirebaseDatabase.getInstance().getReference().child("Chat_grupal").push().getKey();

                ChatGrupal cg = new ChatGrupal(System.currentTimeMillis(),
                    p.getId(),
                    pedia,
                    p.getNombre(), "Grupo: " + p.getNombre(),
                    idc);

                FirebaseDatabase.getInstance().getReference().child("Chat_grupal").child(idc).setValue(cg);
                p.setChat_grupal_id(idc);
                FirebaseDatabase.getInstance().getReference().child("Padres").child(p.getId()).setValue(p);

                for(String pe : pedia.values()){

                    FirebaseDatabase.getInstance().getReference().child("Pediatras").child(pe).child("chats_grupales").child(idc).setValue(idc);
                }

                adapterG.addChatG(cg);
                padre_chatGrupal.setVisibility(View.VISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void readChatGrupal() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.e(">>>", firebaseUser.getUid());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Padres").child(firebaseUser.getUid());

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Padre p = dataSnapshot.getValue(Padre.class);
                if(p.getChat_grupal_id() != null){
                    chatGrup = p.getChat_grupal_id();
                    loadChatG(chatGrup);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void loadChatG(String chatGrup) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Chat_grupal").child(chatGrup);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ChatGrupal c = dataSnapshot.getValue(ChatGrupal.class);
                if(c != null){
             //       chatGrup = p.getChat_grupal_id();
                    Log.e(">>>", "diferencia "+ getDiferenciaDias(new Date(c.getFecha_creacion()), new Date(System.currentTimeMillis())) );

                    if(getDiferenciaDias(new Date(c.getFecha_creacion()), new Date(System.currentTimeMillis())) >= ChatGrupal.DURACION){
                        Log.e(">>>", "diferencia "+ getDiferenciaDias(new Date(c.getFecha_creacion()), new Date(System.currentTimeMillis())) );
                        padre_chatGrupal.setVisibility(View.GONE);
                        borrarChatG();
                    }else{
                        adapterG.addChatG(c);
                        padre_chatGrupal.setVisibility(View.VISIBLE);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void borrarChatG() {

        //IMPLEMENTAR
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Padres").child(firebaseUser.getUid()).child("chat_grupal_id").removeValue();
        FirebaseDatabase.getInstance().getReference().child("Chat_grupal").child(chatGrup).removeValue();
        adapterG.remove();
        Log.e(">>>", "CHAT GRUPAL ELIMINADO "+ adapterG.getCount());
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


    public long getDiferenciaDias(Date fechaInicial, Date fechaFinal){

        long diferencia = fechaFinal.getTime() - fechaInicial.getTime();

        Log.e(">>>>>", "fechaInicial : " + fechaInicial);
        Log.e(">>>>>", "fechaFinal : " + fechaFinal);

        long segsMilli = 1000;
        long minsMilli = segsMilli * 60;
        long horasMilli = minsMilli * 60;
        long diasMilli = horasMilli * 24;

        long diasTranscurridos = diferencia / diasMilli;
        diferencia = diferencia % diasMilli;

//        long horasTranscurridos = diferencia / horasMilli;
//        diferencia = diferencia % horasMilli;
//
//        long minutosTranscurridos = diferencia / minsMilli;
//        diferencia = diferencia % minsMilli;
//
//        long segsTranscurridos = diferencia / segsMilli;
//        return "diasTranscurridos: " + diasTranscurridos + " , horasTranscurridos: " + horasTranscurridos +
//                " , minutosTranscurridos: " + minutosTranscurridos + " , segsTranscurridos: " + segsTranscurridos;

        return diasTranscurridos;
    }

}
