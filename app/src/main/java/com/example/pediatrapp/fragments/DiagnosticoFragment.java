package com.example.pediatrapp.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;


public class DiagnosticoFragment extends Fragment {

    private View view;

    private OnDataSubmitted listener;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_diagnostico, container, false);
        return view;
    }

}
