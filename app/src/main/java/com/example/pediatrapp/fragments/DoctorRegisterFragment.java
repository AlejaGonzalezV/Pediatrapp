package com.example.pediatrapp.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD

=======
>>>>>>> 8897a140cfed08b24090d620c42d0076b0a4ae03
import androidx.fragment.app.Fragment;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;

public class DoctorRegisterFragment extends Fragment {

    private View view;
    private OnDataSubmitted listener;
    private EditText name, id, email, password, idDoctor;
    private Button next, back;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_doctor_register, container, false);

        name = view.findViewById(R.id.name);
        id = view.findViewById(R.id.id);
        email = view.findViewById(R.id.date);
        password = view.findViewById(R.id.password);
        idDoctor = view.findViewById(R.id.idDoctor);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);

        next.setOnClickListener(

                (v) -> {

                    boolean nombre = TextUtils.isEmpty(name.getText().toString().trim());
                    boolean ident = TextUtils.isEmpty(id.getText().toString().trim());
                    boolean correo = TextUtils.isEmpty(id.getText().toString().trim());
                    boolean pass = TextUtils.isEmpty(password.getText().toString().trim());
                    boolean idDoc = TextUtils.isEmpty(idDoctor.getText().toString().trim());
                    boolean valid = Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();

                    if(listener!= null && !nombre && !ident && !correo && !pass && !idDoc && valid){

                        listener.onData(this, "next", name.getText().toString(), id.getText().toString(), email.getText().toString(), password.getText().toString(), idDoctor.getText().toString());

                    } else {

                        if(nombre){
                            name.setError("Este campo no puede estar vacío");
                        }
                        if(ident){
                            id.setError("Este campo no puede estar vacío");
                        }
                        if(correo){
                            email.setError("Este campo no puede estar vacío");
                        }
                        if(pass){
                            password.setError("Este campo no puede estar vacío");
                        }
                        if(idDoc){
                            idDoctor.setError("Este campo no puede estar vacío");
                        }
                        if(!valid){
                            email.setError("Debe ingresar un correo válido");
                        }
                        if(password.getText().toString().length() < 6){
                            password.setError("La contraseña debe tener al menos 6 caracteres");
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
