package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Chat;
import com.example.pediatrapp.model.ChatGrupal;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.view.ActivityMainPediatra;
import com.example.pediatrapp.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;

import de.hdodenhof.circleimageview.CircleImageView;

public class PediatraFragment_Perfil extends Fragment {

    private CircleImageView pediatra_foto;
    private TextView nombre_pediatra;
    private TextView cedula_pediatra;
    private TextView email_pediatra;
    private TextView numeroUnico_pediatra;
    private Button cerrarSesion, editarFoto;
    private Fragment fragmentFoto;

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
        cerrarSesion = view.findViewById(R.id.cerrarSesion);
        editarFoto = view.findViewById(R.id.editarFoto);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        cerrarSesion.setOnClickListener(

                (v)-> {

                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    Query query = FirebaseDatabase.getInstance().getReference().child("chat");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot child: dataSnapshot.getChildren()){

                                Chat chat = child.getValue(Chat.class);
                                if(chat.getId_pediatra().equals(id)){

                                    FirebaseMessaging.getInstance().unsubscribeFromTopic(chat.getId());

                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                    Query query2 = FirebaseDatabase.getInstance().getReference().child("Chat_grupal");
                    query2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot child: dataSnapshot.getChildren()){

                                ChatGrupal chatG = child.getValue(ChatGrupal.class);

                                FirebaseMessaging.getInstance().unsubscribeFromTopic(chatG.getId());


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getContext(), "La sesión se ha cerrado con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                }

        );


        editarFoto.setOnClickListener(

                (v) -> {

                    fragmentFoto = new ChangePhotoFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, fragmentFoto);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();



                }

        );

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    if(child.getKey().equals(id)){

                        Pediatra pediatra = child.getValue(Pediatra.class);
                        cedula_pediatra.setText(pediatra.getCedula());
                        email_pediatra.setText(pediatra.getCorreo());
                        numeroUnico_pediatra.setText(pediatra.getIdV());
                        nombre_pediatra.setText(pediatra.getNombre());
                        storage.getReference().child("Doctor").child(pediatra.getFoto()).getDownloadUrl().addOnSuccessListener(
                                uri -> {
                                    Glide.with(getContext()).load(uri).centerCrop().into(pediatra_foto);
                                }
                        );

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;



    }


}
