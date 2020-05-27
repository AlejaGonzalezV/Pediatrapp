package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
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

public class ParentFragment_Perfil extends Fragment {

    private View view;
    private CircleImageView padre_foto;
    private TextView nombre_padre;
    private TextView cedula_padre;
    private TextView email_padre;
    private TextView numeroTel;
    private Button cerrarSesion, editarFoto, agregarHijo;
    private Fragment fragmentFoto, newChildFragment;

    public ParentFragment_Perfil() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.parentfragment_perfil, container, false);

        padre_foto = view.findViewById(R.id.padre_foto);
        nombre_padre = view.findViewById(R.id.nombre_padre);
        cedula_padre = view.findViewById(R.id.cedula_padre);
        email_padre = view.findViewById(R.id.email_padre);
        numeroTel = view.findViewById(R.id.numeroTel);
        cerrarSesion = view.findViewById(R.id.cerrarSesion);
        editarFoto = view.findViewById(R.id.editarFoto);
        agregarHijo = view.findViewById(R.id.agregarHijo);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        Log.e("<<<<<<<<", "Entra al oncreate");
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = FirebaseDatabase.getInstance().getReference().child("Padres");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    if(child.getKey().equals(id)){

                        Padre padre = child.getValue(Padre.class);
                        cedula_padre.setText(padre.getCedula());
                        email_padre.setText(padre.getCorreo());
                        numeroTel.setText(padre.getTelefono());
                        nombre_padre.setText(padre.getNombre());
                        storage.getReference().child("Padre").child(padre.getFoto()).getDownloadUrl().addOnSuccessListener(
                                uri -> {
                                    Glide.with(getContext()).load(uri).centerCrop().into(padre_foto);
                                }
                        );

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cerrarSesion.setOnClickListener(

                (v)-> {

                    Query query2 = FirebaseDatabase.getInstance().getReference().child("chat");
                    query2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot child: dataSnapshot.getChildren()){

                                Chat chat = child.getValue(Chat.class);
                                if(chat.getId_padre().equals(id)){

                                    FirebaseMessaging.getInstance().unsubscribeFromTopic(chat.getId());

                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Query query3 = FirebaseDatabase.getInstance().getReference().child("Chat_grupal");
                    query3.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot child: dataSnapshot.getChildren()){

                                ChatGrupal chatG = child.getValue(ChatGrupal.class);
                                if(chatG.getId_padre().equals(id)){

                                    FirebaseMessaging.getInstance().unsubscribeFromTopic(chatG.getId());


                                }

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

        agregarHijo.setOnClickListener(

                (v) -> {

                    newChildFragment = new NewChildFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer2, newChildFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();


                }

        );


        editarFoto.setOnClickListener(

                (v) -> {

                    fragmentFoto = new ChangePhotoFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(this);
                    fragmentTransaction.replace(R.id.fragmentContainer2, fragmentFoto);

                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();


                }

        );

        return view;
    }

}
