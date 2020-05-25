package com.example.pediatrapp.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.view.ActivityMainPediatra;
import com.example.pediatrapp.view.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import static android.app.Activity.RESULT_OK;


public class ChangePhotoFragment extends Fragment {

    public static final int GALLERY_CALLBACK = 1;
    private View view;
    private ImageView photoIV, checkP;
    private Button profilePhoto, cambiar, back;
    private Uri uri;
    private Fragment pediatraPerfil, padrePerfil;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_change_photo, container, false);
        photoIV = view.findViewById(R.id.photoIV);
        checkP = view.findViewById(R.id.checkP);
        profilePhoto = view.findViewById(R.id.profilePhoto);
        cambiar = view.findViewById(R.id.cambiar);
        back = view.findViewById(R.id.back);
        checkP.setVisibility(View.GONE);

        profilePhoto.setOnClickListener(

                (v)->{

                    Intent gal = new Intent(Intent.ACTION_GET_CONTENT);
                    gal.setType("image/*");
                    startActivityForResult(gal, GALLERY_CALLBACK);

                }

        );

        cambiar.setOnClickListener(

                (v)->{

                    if(uri!= null){


                        String id = FirebaseAuth.getInstance().getUid();

                        Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot child: dataSnapshot.getChildren()){

                                    if(child.getKey().equals(id)){

                                        FirebaseStorage storage = FirebaseStorage.getInstance();
                                        storage.getReference().child("Doctor").child(id+"*"+"Foto").putFile(uri);
                                        Toast.makeText(getContext(), "La foto se ha cambiado con éxito", Toast.LENGTH_SHORT).show();;
                                        pediatraPerfil = new PediatraFragment_Perfil();
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.fragmentContainer, pediatraPerfil);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();

                                    }

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        Query query2 = FirebaseDatabase.getInstance().getReference().child("Padres");
                        query2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot child: dataSnapshot.getChildren()){

                                    if(child.getKey().equals(id)){

                                        FirebaseStorage storage = FirebaseStorage.getInstance();
                                        storage.getReference().child("Padre").child(id).putFile(uri);
                                        Toast.makeText(getContext(), "La foto se ha cambiado con éxito", Toast.LENGTH_SHORT).show();;
                                        padrePerfil = new ParentFragment_Perfil();
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.fragmentContainer2, padrePerfil);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();

                                    }

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }else {

                        Toast.makeText(getContext(), "Debe seleccionar una foto para continuar", Toast.LENGTH_SHORT).show();

                    }

                }

        );

        back.setOnClickListener(

                (v) ->{


                    String id = FirebaseAuth.getInstance().getUid();

                    Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot child: dataSnapshot.getChildren()){

                                if(child.getKey().equals(id)){

                                    pediatraPerfil = new PediatraFragment_Perfil();
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fragmentContainer, pediatraPerfil);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();

                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Query query2 = FirebaseDatabase.getInstance().getReference().child("Padres");
                    query2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot child: dataSnapshot.getChildren()){

                                if(child.getKey().equals(id)){

                                    padrePerfil = new ParentFragment_Perfil();
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fragmentContainer2, padrePerfil);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();

                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }

        );

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == GALLERY_CALLBACK && resultCode == RESULT_OK){

            uri = data.getData();
            if(uri != null){

                photoIV.setImageURI(uri);
                checkP.setVisibility(View.VISIBLE);

            }


        }

    }

}
