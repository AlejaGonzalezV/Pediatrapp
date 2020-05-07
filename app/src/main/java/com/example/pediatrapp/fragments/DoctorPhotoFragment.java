package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.utilities.Utilities;


import java.io.File;

import static android.app.Activity.RESULT_OK;

public class DoctorPhotoFragment extends Fragment {

    public static final int GALLERY_CALLBACK = 1;
    public static final int GALLERY_CALLBACK2 = 2;

    private Utilities utilities;
    private Button back, next, firma, profilePhoto;
    private ImageView photoIV;
    private View view;
    private OnDataSubmitted listener;
    private Uri uri;
    private String firma1;
    private String foto;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        utilities = new Utilities();
        view = inflater.inflate(R.layout.fragment_doctor_photo, container, false);

        photoIV = view.findViewById(R.id.photoIV);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        firma = view.findViewById(R.id.firma);
        profilePhoto = view.findViewById(R.id.profilePhoto);

        next.setOnClickListener(

                (v) -> {

                    if (listener != null) {

                        //Paso las imagenes al SignUpActivity

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

            Log.e("<<<<<<<<<<<", "IMAGEN AQUI");
            uri = data.getData();
            photoIV.setImageURI(uri);

        } else if(requestCode == GALLERY_CALLBACK2 && resultCode == RESULT_OK){

            Uri elec = data.getData();


        }

    }


}
