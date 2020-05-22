package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Vacuna;

public class VerVacunaActivity extends AppCompatActivity {

    private Vacuna laVacuna;

    private TextView nombreAplicadorET, ipsET,fechaET, dosis, nombreVacuna, edad ;
    private Button salirBtn, backBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_vacuna);

        laVacuna = (Vacuna) getIntent().getExtras().getSerializable("verVacuna");

       nombreAplicadorET = findViewById(R.id.verAplicador);
       ipsET = findViewById(R.id.verIps);
        fechaET = findViewById(R.id.verFechaVa);
       dosis = findViewById(R.id.verDosis);
       nombreVacuna = findViewById(R.id.verVacunaNombre);
       edad = findViewById(R.id.verEdadVa);
       salirBtn = findViewById(R.id.salirVerVaBTN);
       backBTN = findViewById(R.id.backVerVacuna);

       nombreAplicadorET.setText(laVacuna.getNombre_aplicador());
       ipsET.setText(laVacuna.getIps());
       fechaET.setText(laVacuna.getFecha_aplicacion());
       dosis.setText(laVacuna.getDosis());
       nombreVacuna.setText(laVacuna.getNombre_vacuna());
       edad.setText(laVacuna.getEdad_aplicacion());

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
