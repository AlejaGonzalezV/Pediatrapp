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
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.Adapter_ChatG;
import com.example.pediatrapp.adapter.PediatraAdapter_ChatList;
import com.example.pediatrapp.model.ChatGrupal;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.view.MessageGroupActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Date;

public class PediatraFragment_ChatG extends Fragment {

    private EditText SearchChatET;
    private ImageButton SearchChatBT;
    private ListView pediatra_ChatG;
    private Button FiltroChatBT;
    private Adapter_ChatG adapterG;
    private ArrayList<ChatGrupal> chats;
    private ArrayList<String> idpediatras;


    public PediatraFragment_ChatG() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pediatrafragment_chatg, container, false);

        SearchChatET = view.findViewById(R.id.SearchChatET);
        SearchChatBT = view.findViewById(R.id.SearchChatBT);
        pediatra_ChatG = view.findViewById(R.id.pediatra_ChatG);
        FiltroChatBT = view.findViewById(R.id.FiltroChatBT);

        chats = new ArrayList<>();
        idpediatras = new ArrayList<>();
        adapterG = new Adapter_ChatG();
        pediatra_ChatG.setAdapter(adapterG);

        Log.e(">>>", "ENTRA A CHATG");
        loadAllGroupChats();

        pediatra_ChatG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ChatGrupal cg = (ChatGrupal) adapterG.getItem(i);
                new Thread(
                        () ->{

                            Intent intent = new Intent(getActivity(), MessageGroupActivity.class);
                            intent.putExtra("chat", cg.getId());
                            intent.putExtra("idpadre", cg.getId_padre());
                            intent.putExtra("nombre_chat", cg.getNombre());
                            intent.putExtra("type", "pediatra");
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
                adapterG.setChatG(buscarChat(nombre));
            }
        });

        FiltroChatBT.setOnClickListener(
                (v)->{
                    Log.e(">>>","FiltroChat");
                    adapterG.setChatG(chats);
                    SearchChatET.setText("");
                }
        );

        return view;
    }

    private void loadAllGroupChats() {

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Chat_grupal");
        Log.e(">>>", "bUSCA CHATS");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSn : dataSnapshot.getChildren()){

                        Log.e(">>>Data snapshot", "" +dataSn.getValue());
                        ChatGrupal g = dataSn.getValue(ChatGrupal.class);
                        if(g != null) {

                            if(getDiferenciaDias(new Date(g.getFecha_creacion()), new Date(System.currentTimeMillis())) >= ChatGrupal.DURACION){
                                Log.e(">>>", "diferencia "+ getDiferenciaDias(new Date(g.getFecha_creacion()), new Date(System.currentTimeMillis())) );

                                borrarChatG(g);
                            }else{
                                chats.add(g);
                                adapterG.addChatG(g);
                            }


                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }


    private void borrarChatG(ChatGrupal chat) {

        //IMPLEMENTAR
        //adapterG.remove();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseMessaging.getInstance().unsubscribeFromTopic(chat.getId());
        FirebaseDatabase.getInstance().getReference().child("Padres").child(chat.getId_padre()).child("chat_grupal_id").removeValue();
        FirebaseDatabase.getInstance().getReference().child("Chat_grupal").child(chat.getId()).removeValue();
        findAllPediatras(chat.getId());
    }

    private void findAllPediatras(String id) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Pediatras");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){

                    String id = data.getKey();
                    if(id != null){
                        idpediatras.add(id);
                    }
                }

                for(int i = 0; i < idpediatras.size(); i++){
                    FirebaseDatabase.getInstance().getReference().child("Pediatras").child(idpediatras.get(i)).child("chats_grupales").child(id).removeValue();
                }

                idpediatras.clear();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public ArrayList<ChatGrupal> buscarChat(String nombre){
        ArrayList<ChatGrupal> resultado = new ArrayList<>();

        for(int i = 0; i< adapterG.getChatG().size(); i++){
            if(adapterG.getChatG().get(i).getNombre().contains(nombre)){
                resultado.add(adapterG.getChatG().get(i));
            }
        }

        return  resultado;
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

        return diasTranscurridos;
    }



}
