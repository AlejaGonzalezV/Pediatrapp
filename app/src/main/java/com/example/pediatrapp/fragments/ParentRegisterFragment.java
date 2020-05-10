package com.example.pediatrapp.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;

public class ParentRegisterFragment extends Fragment {

    private View view;
    private EditText name, id, email, password, address, phone;
    private Button next, back;
    private OnDataSubmitted listener;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_parent_register,container,false);

        name = view.findViewById(R.id.name);
        id = view.findViewById(R.id.id);
        email = view.findViewById(R.id.date);
        password = view.findViewById(R.id.password);
        address = view.findViewById(R.id.address);
        phone = view.findViewById(R.id.phone);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        next.setOnClickListener(

                (v) -> {

                    boolean nombre = TextUtils.isEmpty(name.getText().toString().trim());
                    boolean correo = TextUtils.isEmpty(email.getText().toString().trim());
                    boolean ident =  TextUtils.isEmpty(id.getText().toString().trim());
                    boolean pass = TextUtils.isEmpty(password.getText().toString().trim());
                    boolean addr = TextUtils.isEmpty(address.getText().toString().trim());
                    boolean ph = TextUtils.isEmpty(phone.getText().toString().trim());
                    boolean valid = Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();

                    if(listener != null && nombre == false && ident == false && correo == false && pass == false && addr == false && ph == false && valid){

                        listener.onData(this, "next", name.getText().toString(), id.getText().toString(), email.getText().toString(), password.getText().toString(), address.getText().toString(), phone.getText().toString());

                    }else {

                        if(correo){
                            email.setError("Debe ingresar un correo válido");
                        }
                        if(nombre){
                            name.setError("Debe llenar este campo para continuar");
                        }
                        if(correo){
                            email.setError("Debe ingresar este campo para continuar");
                        }
                        if(ident){
                            id.setError("Debe ingresar este campo para continuar");
                        }
                        if(pass){
                            password.setError("Debe ingresar este campo para continuar");
                        }
                        if(addr){
                            address.setError("Debe ingresar este campo para continuar");
                        }
                        if(ph){
                            phone.setError("Debe ingresar este campo para continuar");
                        }
                        if(valid){
                            email.setError("Debe ingresar una dirección de correo válida");
                        }

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
