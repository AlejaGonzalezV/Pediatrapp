package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Vacuna;

import java.io.Serializable;
import java.util.ArrayList;

public class AddVacunaActivity extends AppCompatActivity implements Serializable {

    private Spinner nombreVacunaSpinner;
    private EditText nombreAplicadorET;
    private Spinner dosisSpinner;
    private EditText ipsET;
    private Spinner edadSpinner;
    private EditText fechaET;
    private Button guardarBTN;
    private Button cancelarBTN;
    private Button backBTN;
    private ArrayList<String> listaNombresVacunas;
    private ArrayList<String> listaEdades;
    private ArrayList<String> listaDosis;

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
        backBTN = findViewById(R.id.backañadirVacunaBTN);
        listaNombresVacunas = new ArrayList<>();
        listaDosis = new ArrayList<>();
        listaEdades = new ArrayList<>();

        for (int i= 0; i<5;i++){

            listaNombresVacunas.add("Vacuna: "+ i);
            listaEdades.add("Edad: "+i);
            listaDosis.add("dosis: "+i);
        }

        ArrayAdapter<CharSequence>  adapterNombreVacunas = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listaNombresVacunas);
        nombreVacunaSpinner.setAdapter(adapterNombreVacunas);
        nombreVacunaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(),"Se añadió: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence>  adapterEdadess = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listaEdades);
        edadSpinner.setAdapter(adapterEdadess);
        edadSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(),"Se añadió: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence>  adapterDosis = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listaDosis);
        dosisSpinner.setAdapter(adapterDosis);
        dosisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(),"Se añadió: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        guardarBTN.setOnClickListener(

                (v)->{
                    /*
                    Intent intent = new Intent();
                    intent.putExtra("nuevaVacuna", enviarVacunaNueva());
                    setResult(RESULT_OK, intent);
                    this.finish();*/
                    Toast.makeText(this,"Se añadió: ", Toast.LENGTH_SHORT).show();
                    this.finish();

                }
        );
        cancelarBTN.setOnClickListener(

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


    public Vacuna enviarVacunaNueva(){

     // Vacuna vacuna = new Vacuna(String dosis, String edad_aplicacion, String ips, String nombre_aplicador, String nombre_vacuna, LocalDate fecha_aplicacion;

    return null;
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
