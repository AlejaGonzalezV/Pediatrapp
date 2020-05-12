package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;

import com.example.pediatrapp.R;

public class AddVacunaActivity extends AppCompatActivity {

    private Spinner nombreVacunaSpinner;
    private EditText nombreAplicadorET;
    private Spinner dosisSpinner;
    private EditText ipsET;
    private Spinner edadSpinner;
    private EditText fechaET;
    private Button guardarBTN;
    private Button cancelarBTN;
    private ListaVacunasActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacuna);

        nombreVacunaSpinner = findViewById(R.id.nombreSpinner);
        nombreAplicadorET = findViewById(R.id.aplicadorET);
        dosisSpinner = findViewById(R.id.dosisSpinner3);
        ipsET = findViewById(R.id.ipsET);
        edadSpinner = findViewById(R.id.edadSpinner2);
        fechaET = findViewById(R.id.fechaET);
        guardarBTN = findViewById(R.id.guardarBTN);
        cancelarBTN = findViewById(R.id.cancelarBTN);


        guardarBTN.setOnClickListener(

                (v)->{


                }
        );
        cancelarBTN.setOnClickListener(

                (v)->{

                        this.finish();
                }
        );

    }




    public Spinner getNombreVacunaSpinner() {
        return nombreVacunaSpinner;
    }

    public void setNombreVacunaSpinner(Spinner nombreVacunaSpinner) {
        this.nombreVacunaSpinner = nombreVacunaSpinner;
    }

    public EditText getNombreAplicadorET() {
        return nombreAplicadorET;
    }

    public void setNombreAplicadorET(EditText nombreAplicadorET) {
        this.nombreAplicadorET = nombreAplicadorET;
    }

    public Spinner getDosisSpinner() {
        return dosisSpinner;
    }

    public void setDosisSpinner(Spinner dosisSpinner) {
        this.dosisSpinner = dosisSpinner;
    }

    public EditText getIpsET() {
        return ipsET;
    }

    public void setIpsET(EditText ipsET) {
        this.ipsET = ipsET;
    }

    public Spinner getEdadSpinner() {
        return edadSpinner;
    }

    public void setEdadSpinner(Spinner edadSpinner) {
        this.edadSpinner = edadSpinner;
    }

    public EditText getFechaET() {
        return fechaET;
    }

    public void setFechaET(EditText fechaET) {
        this.fechaET = fechaET;
    }

    public Button getGuardarBTN() {
        return guardarBTN;
    }

    public void setGuardarBTN(Button guardarBTN) {
        this.guardarBTN = guardarBTN;
    }

    public Button getCancelarBTN() {
        return cancelarBTN;
    }

    public void setCancelarBTN(Button cancelarBTN) {
        this.cancelarBTN = cancelarBTN;
    }
}
