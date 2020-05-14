package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Vacuna;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddVacunaActivity extends AppCompatActivity implements Serializable {

    private Spinner nombreVacunaSpinner,dosisSpinner, edadSpinner;
    private EditText nombreAplicadorET, ipsET,fechaET ;
    private Button guardarBTN, cancelarBTN, backBTN;
    private ArrayList<String> listaNombresVacunas, listaEdades, listaDosis;

    private String vacunaSelected,  dosisSelected,edadSelected;


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
        vacunaSelected = "";
        dosisSelected = "";
        edadSelected = "";

        listaNombresVacunas.add("Seleccionar");
        listaDosis.add("Seleccionar");
        listaEdades.add("Seleccionar");

        for (int i= 0; i<5;i++){

            listaNombresVacunas.add("Vacuna: "+ i);
            listaEdades.add("Edad: "+i);
            listaDosis.add("dosis: "+i);
        }

        datosSpinnerDosis();
        datosSpinnerEdad();
        datosSpinnerVacunas();
        darFuncionalidadBotones();


    }


    //Meétodo que contiene los botones

    public void darFuncionalidadBotones() {


        guardarBTN.setOnClickListener(

                (v) -> {

                    if (validarDatos()) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            LocalDate localDate = (LocalDate) LocalDate.now();

                            Vacuna laVacuna = new Vacuna(dosisSelected, edadSelected, ipsET.getText().toString(), nombreAplicadorET.getText().toString(), vacunaSelected, localDate);

                            Intent i = new Intent();
                            i.putExtra("marcador", laVacuna);
                            setResult(RESULT_OK, i);
                            finish();

                            // Toast.makeText(this, "Se añadió: " + laVacuna.getNombre_vacuna(), Toast.LENGTH_SHORT).show();
                        }
                    }
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

    public boolean validarDatos(){

       Boolean retorno = true;

        String ips = ipsET.getText().toString();
        String aplicador = nombreAplicadorET.getText().toString();
        if(dosisSelected.equals("Seleccionar")){

            ((TextView)dosisSpinner.getSelectedView()).setError("");
           ((TextView)dosisSpinner.getSelectedView()).setTextColor(Color.RED);

            retorno = false;
        }
       if(edadSelected.equals("Seleccionar")){

           ((TextView)edadSpinner.getSelectedView()).setError("");
           ((TextView)edadSpinner.getSelectedView()).setTextColor(Color.RED);
            retorno = false;
        }
        if(vacunaSelected.equals("Seleccionar")){

            ((TextView)nombreVacunaSpinner.getSelectedView()).setError("");
             ((TextView)nombreVacunaSpinner.getSelectedView()).setTextColor(Color.RED);
            retorno = false;
        }

        if(ips.isEmpty()){

            ipsET.setError("");
            retorno = false;

        }
        if(aplicador.isEmpty()){

            nombreAplicadorET.setError("");
            retorno = false;

        }
        return retorno;
    }


    // Metodo que toma dato de Spinner Vacunas

    public void datosSpinnerVacunas(){
        ArrayAdapter<CharSequence>  adapterNombreVacunas = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listaNombresVacunas);
        nombreVacunaSpinner.setAdapter(adapterNombreVacunas);
        nombreVacunaSpinner.setSelection(0);
        nombreVacunaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(),"Se añadió: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                vacunaSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    // Metodo que toma dato de Spinner Edades

    public void datosSpinnerEdad(){

        ArrayAdapter<CharSequence>  adapterEdadess = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listaEdades);
        edadSpinner.setAdapter(adapterEdadess);
        edadSpinner.setSelection(0);
        edadSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(),"Se añadió: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                edadSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    // Metodo que toma dato de Spinner dosis
    public void datosSpinnerDosis(){

        ArrayAdapter<CharSequence>  adapterDosis = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listaDosis);
        dosisSpinner.setAdapter(adapterDosis);
        dosisSpinner.setSelection(0);
        dosisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(),"Se añadió: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                dosisSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
/*
* [10:35 PM, 5/12/2020] Valentina: floatingActionButton.setOnClickListener(
                (v)->{
                    if(tempo !=null) {
                        Intent i = new Intent(this, AddMarkers.class);
                        Marcador m = new Marcador( tempo.getTitle());
                        i.putExtra("marcador", m);
                        startActivityForResult(i, 11);
                    }
                }
        );
[10:36 PM, 5/12/2020] Valentina: protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 11 && resultCode == RESULT_OK){

            if(data != null){

                Serializable serializable = data.getExtras().getSerializable("marcador");
                Marcador marcador = (Marcador) serializable;
[10:36 PM, 5/12/2020] Valentina: Activity Add Marker
[10:36 PM, 5/12/2020] Valentina: Btn_agregar.setOnClickListener(
                (v) -> {
                    if(editText_name.getText().toString()!=null) {
                        Intent i = new Intent();
                        marcador.setTitulo(editText_name.getText().toString());
                        i.putExtra("marcador", marcador);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                }
        );
[10:36 PM, 5/12/2020] Valentina: marcador = (Marcador) getIntent().getExtras().getSerializable("marcador");
*
*
* */