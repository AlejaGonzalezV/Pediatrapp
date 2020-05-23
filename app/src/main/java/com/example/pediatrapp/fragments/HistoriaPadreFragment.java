package com.example.pediatrapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HistoriaPadreFragment extends Fragment {

    private View view;

    private OnDataSubmitted listener;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_historia_padre, container, false);

        return view;
    }

}
