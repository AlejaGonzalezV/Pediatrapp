package com.example.pediatrapp.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.dialog.DatePickerFragment;

public class FormulaFragment extends Fragment implements View.OnClickListener{

    private View view;
    private EditText fecha, posologia;
    private Button addFormulaBt, back;
    private OnDataSubmitted listener;
    private String fechaS;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_formula, container, false);

        fecha = view.findViewById(R.id.fecha);
        posologia = view.findViewById(R.id.posologiaTxt);
        addFormulaBt = view.findViewById(R.id.addFormulaBt);
        back = view.findViewById(R.id.back);

        fecha.setOnClickListener(this);

        back.setOnClickListener(

                (v)->{

                    listener.onData(this, "back", null);

                }

        );

        addFormulaBt.setOnClickListener(

                (v)->{

                    boolean fec = TextUtils.isEmpty(fecha.getText().toString().trim());
                    boolean poso = TextUtils.isEmpty(posologia.getText().toString().trim());

                    if(fec == false && poso == false){

                        listener.onData(this, "next", fechaS,posologia.getText().toString());

                    }else {

                        if(fec){

                            fecha.setError("Debe ingresar este campo para continuar");

                        }
                        if(poso){

                            posologia.setError("Debe ingresar este campo para continuar");

                        }

                    }

                }

        );

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fecha:
                showDatePickerDialog();
                break;
        }

    }

    public void showDatePickerDialog(){
        DatePickerFragment datePicker = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                if((month+1) >= 10 && (day>=10)){

                    fechaS = day + "/" + (month+1) + "/" + year;
                    fecha.setText(fechaS);

                } else if((month+1) >= 10 && (day<10)) {

                    fechaS = "0" + day + "/" + (month+1) + "/" + year;
                    fecha.setText(fechaS);

                } else if((month+1) < 10 && (day>=10)){

                    fechaS = day + "/" + "0" + (month+1) + "/" + year;
                    fecha.setText(fechaS);

                }else if((month+1) < 10 && (day<10)){

                    fechaS = "0" + day + "/" + "0" + (month+1) + "/" + year;
                    fecha.setText(fechaS);

                }


                Log.e("<<<<<<<<<<<", fechaS);


            }
        });

        datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");


    }
}
