package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HijosCurvasAdapter;
import com.example.pediatrapp.adapter.HijosVacunasAdapter;
import com.example.pediatrapp.model.Hijo;

import java.util.ArrayList;

public class CurvasCrecimientoActivity extends AppCompatActivity {

    private Button backBTN;
    private String nombreHijo;
    private RecyclerView recycler;

    private ArrayList<Hijo> listaHijos;
    private HijosCurvasAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curvas_crecimiento);

        backBTN = findViewById(R.id.backCurvas);

        nombreHijo = "";

        recycler = findViewById(R.id.recyclerHijosCurvas);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listaHijos = new ArrayList<>();
        adapter = new HijosCurvasAdapter(this,listaHijos );
        recycler.setAdapter(adapter);

        listaHijos.add(new Hijo("", "", "String nacimiento", "Masculino", "Jair", "3"));




        backBTN.setOnClickListener(
                (v)->{
                    this.finish();
                }

        );

    }

}
