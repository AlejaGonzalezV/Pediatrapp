package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddCurvaActivity extends AppCompatActivity {

    private EditText peso, medidaCabeza, fecha, talla;
    private String nacimiento;
    private ArrayList<String> listaEdades;
    private Button guardarBTN, cancelarBTN, backBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_curva);

        peso = findViewById(R.id.pesoCurvaET);
        medidaCabeza =  findViewById(R.id.alturaCurvaET);
        fecha = findViewById(R.id.fechaCurvaET);
        guardarBTN = findViewById(R.id.guardarCurvaBTN);
        backBTN = findViewById(R.id.backAddCurva);
        cancelarBTN = findViewById(R.id.cancelarCurvaBTN);
        talla = findViewById(R.id.tallaCurvaET2);

        nacimiento = getIntent().getStringExtra("nacimiento");

        listaEdades = new ArrayList<>();
        listaEdades.add("Seleccionar");




        for (int i= 1; i<18;i++){

            listaEdades.add(""+i);

        }
        darFuncionalidadBotones();

    }


    //Meétodo que contiene los botones

    public void darFuncionalidadBotones() {


        guardarBTN.setOnClickListener(

                (v) -> {

                    if (validarDatos()) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                            if( valiTiposDatos()){

                                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate fechaN = LocalDate.parse(nacimiento, fmt);
                                LocalDate hoy = LocalDate.parse(fecha.getText().toString(), fmt);;

                                Period periodo = Period.between(fechaN, hoy);
                                String edad1 = String.valueOf(periodo.getYears());
                                DatosCurva laCurva;
                                Log.e(">>>", edad1);
                                String edadR = "";

                                if(edad1.equals("0")){

                                    edadR = periodo.getMonths() + " Meses";


                                }else {
                                    edadR = periodo.getYears() + " Años y " + periodo.getMonths()+ " meses";
                                }

                                laCurva = new DatosCurva("id", fecha.getText().toString(), Integer.parseInt(medidaCabeza.getText().toString()), Integer.parseInt(peso.getText().toString()), Integer.parseInt(talla.getText().toString()),edadR );
                            Intent i = new Intent();
                            i.putExtra("laCurva", laCurva);
                            setResult(RESULT_OK, i);
                            this.finish();
                        }
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
            String dateSelected = "";

            if((month+1) >= 10 && (day>=10)){

                dateSelected = day + "/" + (month+1) + "/" + year;
                fecha.setText(dateSelected);

            } else if((month+1) >= 10 && (day<10)) {

                dateSelected = "0" + day + "/" + (month+1) + "/" + year;
                fecha.setText(dateSelected);

            } else if((month+1) < 10 && (day>=10)){

                dateSelected = day + "/" + "0" + (month+1) + "/" + year;
                fecha.setText(dateSelected);

            }else if((month+1) < 10 && (day<10)){

                dateSelected = "0" + day + "/" + "0" + (month+1) + "/" + year;
                fecha.setText(dateSelected);

            }



//            String selectedDate =  day + "/" + (month+1) + "/" + year;
//
//            Log.e(">>>>>>>", selectedDate);
//            //Toast.makeText(parent.getContext(),"Se añadió: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//
//            fecha.setText(selectedDate);
        });


        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }

    public boolean valiTiposDatos(){

        boolean retorno = true;
       if( isNumeric(medidaCabeza.getText().toString()) == false){

           medidaCabeza.setError("Ingrese números");
           retorno = false;
       }

        if( isNumeric(peso.getText().toString()) == false){

            peso.setError("Ingrese números");
            retorno = false;
        }
        if( isNumeric(talla.getText().toString()) == false){

            talla.setError("Ingrese números");
            retorno = false;
        }

        return  retorno;
    }

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    public boolean validarDatos(){

        Boolean retorno = true;

        String pesoE = peso.getText().toString();
        String alturaE = medidaCabeza.getText().toString();
        String fechaE= fecha.getText().toString();
        String tallaE = talla.getText().toString();

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


    /* Intent i = new Intent();
                            i.putExtra("marcador", laVacuna);
                            setResult(RESULT_OK, i);
                            finish();*/
}
