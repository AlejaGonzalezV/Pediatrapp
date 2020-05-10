package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pediatrapp.R;

public class ListaVacunasActivity extends AppCompatActivity {

    private ImageButton buscarBtn;
    private EditText buscarET;
    private TextView nombreHijo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vacunas);

        buscarBtn = findViewById(R.id.searchHijoVaListaBT);
        buscarET = findViewById(R.id.searchHijoVaListET);
        nombreHijo = findViewById(R.id.nombreHijoVaList);
    }

    public ImageButton getBuscarBtn() {
        return buscarBtn;
    }

    public void setBuscarBtn(ImageButton buscarBtn) {
        this.buscarBtn = buscarBtn;
    }

    public EditText getBuscarET() {
        return buscarET;
    }

    public void setBuscarET(EditText buscarET) {
        this.buscarET = buscarET;
    }

    public TextView getNombreHijo() {
        return nombreHijo;
    }

    public void setNombreHijo(TextView nombreHijo) {
        this.nombreHijo = nombreHijo;
    }
}
