package com.example.pediatrapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;

public class ChildRegisterFragment extends Fragment {

    private OnDataSubmitted listener;
    private View view;
    private Button next, back;
    private EditText name, id, date;
    private Spinner doctor, sex;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_child_register,container,false);
        name = view.findViewById(R.id.name);
        id = view.findViewById(R.id.id);
        date = view.findViewById(R.id.date);
        doctor = view.findViewById(R.id.doctor);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        sex = view.findViewById(R.id.sex);

        next.setOnClickListener(

                (v) -> {

                    if(listener != null){

                        listener.onData(this,"next", name.getText().toString(), id.getText().toString(), date.getText().toString(), doctor.getSelectedItem().toString(), sex.getSelectedItem().toString());

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

        return view;
    }

}
