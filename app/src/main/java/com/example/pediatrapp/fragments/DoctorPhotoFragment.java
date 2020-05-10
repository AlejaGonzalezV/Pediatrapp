package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.utilities.Utilities;

import static android.app.Activity.RESULT_OK;

public class DoctorPhotoFragment extends Fragment {

    public static final int GALLERY_CALLBACK = 1;
    public static final int GALLERY_CALLBACK2 = 2;

    private Utilities utilities;
    private Button back, next, firma, profilePhoto;
    private ImageView photoIV, checkP, checkF;
    private View view;
    private OnDataSubmitted listener;
    private Uri uriP, uriF;
    private String data;
    private Pediatra pediatra;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        utilities = new Utilities();
        view = inflater.inflate(R.layout.fragment_doctor_photo, container, false);

        checkP = view.findViewById(R.id.checkP);
        checkP.setVisibility(View.GONE);
        checkF = view.findViewById(R.id.checkF);
        checkF.setVisibility(View.GONE);
        photoIV = view.findViewById(R.id.photoIV);
        next = view.findViewById(R.id.consultar_btn);
        back = view.findViewById(R.id.back);
        firma = view.findViewById(R.id.firma);
        profilePhoto = view.findViewById(R.id.profilePhoto);

        next.setOnClickListener(

                (v) -> {

                    boolean correcto=uriF!=null;
                    boolean fPerfil=uriP!=null;
                    if (listener != null && correcto && fPerfil) {


                        listener.onData(this,"nextF", uriP.toString(),uriF.toString());


                    }else if(listener!=null && correcto && !fPerfil){

                        listener.onData(this, "next", uriF.toString());

                    } else if(!correcto){

                        Toast.makeText(getContext(), "Debe subir una firma electrónica para continuar", Toast.LENGTH_LONG);

                    }


                }

        );

        back.setOnClickListener(

                (v) -> {

                    if(listener != null){

                        listener.onData(this, "back", null);

                    }

                }

        );

        firma.setOnClickListener(

                (v) -> {

                    Intent gal = new Intent(Intent.ACTION_GET_CONTENT);
                    gal.setType("image/*");
                    startActivityForResult(gal, GALLERY_CALLBACK2);


                }

        );

        profilePhoto.setOnClickListener(

                (v) -> {

                    Intent gal = new Intent(Intent.ACTION_GET_CONTENT);
                    gal.setType("image/*");
                    startActivityForResult(gal, GALLERY_CALLBACK);



                }

        );

        return view;

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == GALLERY_CALLBACK && resultCode == RESULT_OK){

            uriP = data.getData();
            if(uriP != null){

                photoIV.setImageURI(uriP);
                checkP.setVisibility(View.VISIBLE);

                //FirebaseStorage storage = FirebaseStorage.getInstance();
                //storage.getReference().child("ProfilePhotoDoctor").child()


            }


        } else if(requestCode == GALLERY_CALLBACK2 && resultCode == RESULT_OK){

            uriF = data.getData();

            if(uriF != null){

                checkF.setVisibility(View.VISIBLE);

            }



        }

    }


}
