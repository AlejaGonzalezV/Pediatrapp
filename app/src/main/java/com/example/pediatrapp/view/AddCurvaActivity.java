package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.dialog.DatePickerFragment;
import com.example.pediatrapp.model.DatosCurva;

import java.util.ArrayList;

public class AddCurvaActivity extends AppCompatActivity {

    private EditText peso, medidaCabeza, fecha, talla;
    private Spinner edadSpinner;
    private String edadSelected;
    private ArrayList<String> listaEdades;
    private Button guardarBTN, cancelarBTN, backBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_curva);

        peso = findViewById(R.id.pesoCurvaET);
        medidaCabeza =  findViewById(R.id.alturaCurvaET);
        fecha = findViewById(R.id.fechaCurvaET);
        edadSpinner = findViewById(R.id.spinnerEdadCurva);
        guardarBTN = findViewById(R.id.guardarCurvaBTN);
        backBTN = findViewById(R.id.backAddCurva);
        cancelarBTN = findViewById(R.id.cancelarCurvaBTN);
        talla = findViewById(R.id.tallaCurvaET2);

        edadSelected = "";
        listaEdades = new ArrayList<>();
        listaEdades.add("Seleccionar");



        for (int i= 1; i<18;i++){

            listaEdades.add(""+i);

        }

        datosSpinnerEdad();
        darFuncionalidadBotones();



    }

    //Meétodo que contiene los botones

    public void darFuncionalidadBotones() {


        guardarBTN.setOnClickListener(

                (v) -> {

                    if (validarDatos()) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                            DatosCurva laCurva = new DatosCurva(fecha.getText().toString(), Integer.parseInt(medidaCabeza.getText().toString()), Integer.parseInt(peso.getText().toString()), Integer.parseInt(talla.getText().toString()), edadSelected);

                            Intent i = new Intent();
                            i.putExtra("laCurva", laCurva);
                            setResult(RESULT_OK, i);
                            this.finish();

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

        fecha.setOnClickListener(

                (v)->{

                    showDatePickerDialog();
                }

        );
    }

    private void showDatePickerDialog() {

        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 because January is zero
            String selectedDate =  day + "/" + (month+1) + "/" + year;

            Log.e(">>>>>>>", selectedDate);
            //Toast.makeText(parent.getContext(),"Se añadió: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

            fecha.setText(selectedDate);
        });


        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }


    public boolean validarDatos(){

        Boolean retorno = true;

        String pesoE = peso.getText().toString();
        String alturaE = medidaCabeza.getText().toString();
        String fechaE= fecha.getText().toString();
        String tallaE = talla.getText().toString();

        if(edadSelected.equals("Seleccionar")){

            ((TextView)edadSpinner.getSelectedView()).setError("Vacío");
            ((TextView)edadSpinner.getSelectedView()).setTextColor(Color.RED);
            retorno = false;
        }
        if(fechaE.isEmpty()){

            fecha.setError("Vacío");
            retorno = false;
        }

        if(pesoE.isEmpty()){

            peso.setError("Vacío");
            retorno = false;

        }
        if(alturaE.isEmpty()){

            medidaCabeza.setError("Vacío");
            retorno = false;

        }
        if(tallaE.isEmpty()){

            talla.setError("Vacío");
            retorno = false;

        }
        return retorno;
    }


    public void datosSpinnerEdad(){

        ArrayAdapter<CharSequence> adapterEdadess = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listaEdades);
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

    /* Intent i = new Intent();
                            i.putExtra("marcador", laVacuna);
                            setResult(RESULT_OK, i);
                            finish();*/
}
