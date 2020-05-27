package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Vacuna;

public class VerVacunaActivity extends AppCompatActivity {

    private Vacuna laVacuna;

    private TextView nombreAplicadorET, ipsET,fechaET, dosis, nombreVacuna, edad, loteET, labET ;
    private Button salirBtn, backBTN, ElimiarBTN;

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
       ElimiarBTN = findViewById(R.id.ElimiarBTN);
       loteET  = findViewById(R.id.loteET);
       labET = findViewById(R.id.labET);

       nombreAplicadorET.setText(laVacuna.getNombre_aplicador());
       ipsET.setText(laVacuna.getIps());
       fechaET.setText(laVacuna.getFecha_aplicacion());
       dosis.setText(laVacuna.getDosis());
       nombreVacuna.setText(laVacuna.getNombre_vacuna());
       edad.setText(laVacuna.getEdad_aplicacion());
       loteET.setText(laVacuna.getLote());
       labET.setText(laVacuna.getLabortorio());

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

        ElimiarBTN.setOnClickListener(
                (v) ->{

                    Intent i = new Intent();
                    i.putExtra("marcador", laVacuna);
                    setResult(RESULT_OK, i);
                    finish();

                });
    }
}
