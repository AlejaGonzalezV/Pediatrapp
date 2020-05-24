package com.example.pediatrapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pediatrapp.R;


public class ChangePhotoFragment extends Fragment {

    public static final int GALLERY_CALLBACK = 1;
    private View view;
    private ImageView photoIV, checkP;
    private Button profilePhoto, cambiar, back;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_change_photo, container, false);
        return view;
    }

}
