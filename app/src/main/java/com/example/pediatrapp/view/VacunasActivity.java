package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.pediatrapp.R;

public class VacunasActivity extends AppCompatActivity {

    private ImageButton buscarBtn;
    private EditText buscarET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas);

        buscarBtn = findViewById(R.id.searchHijoVaBT);
         buscarET = findViewById(R.id.searchHijoVaET);
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
}
