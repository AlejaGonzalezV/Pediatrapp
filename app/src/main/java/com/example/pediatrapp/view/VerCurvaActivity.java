package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.DatosCurva;

public class VerCurvaActivity extends AppCompatActivity {

    private DatosCurva curva;
    private TextView fecha, peso, talla, cabeza, edad;
    private Button salirBtn, backBTN, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_curva);

        fecha = findViewById(R.id.fechaVerCur);
        peso = findViewById(R.id.pesoVerCur);
        talla= findViewById(R.id.tallaVerCur);
        cabeza = findViewById(R.id.cabezaVerCur);
        edad = findViewById(R.id.edadVerCur);
        salirBtn =  findViewById(R.id.salirBTNVerCur);
        eliminar =  findViewById(R.id.eliminarBTNCurv);
        backBTN =  findViewById(R.id.backVerCurva);

        curva = (DatosCurva) getIntent().getExtras().getSerializable("verCurva");


        fecha.setText(curva.getFecha());
        peso.setText(""+curva.getPeso());
        talla.setText(""+curva.getTalla());
        cabeza.setText(""+curva.getMedida_cabeza());
        edad.setText(""+curva.getEdad());



        eliminar.setOnClickListener(

                (v)->{


                }
        );


        salirBtn.setOnClickListener(

                (v)->{

                    this.finish();
                }
        );
        backBTN.setOnClickListener(

                (v)->{

                    this.finish();
                }
        );

    }
}
