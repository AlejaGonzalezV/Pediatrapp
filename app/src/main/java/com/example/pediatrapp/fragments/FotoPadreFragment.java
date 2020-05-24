package com.example.pediatrapp.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;

import static android.app.Activity.RESULT_OK;


public class FotoPadreFragment extends Fragment {

    public static final int GALLERY_CALLBACK = 1;
    public static final int GALLERY_CALLBACK2 = 2;
    private View view;
    private Button profilePhoto, next;
    private ImageView photoIV, checkP;
    private Uri uri;


    private OnDataSubmitted listener;
    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_foto_padre, container, false);

        profilePhoto = view.findViewById(R.id.profilePhoto);
        next = view.findViewById(R.id.next);
        photoIV = view.findViewById(R.id.photoIV);
        checkP = view.findViewById(R.id.checkP);
        checkP.setVisibility(View.GONE);

        next.setOnClickListener(

                (v)->{

                    boolean correcto = uri!= null;

                    if(listener != null && correcto){

                        listener.onData(this,"next", uri.toString());

                    }else {

                        listener.onData(this,"next", "no");

                    }

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

    public void uriFromResource(){

        uri = Uri.parse("android.resource://" + getContext().getPackageName()
                + "/" + R.drawable.user);

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
