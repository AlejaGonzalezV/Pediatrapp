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

    private ImageButton buscarBtn;
    private EditText buscarET;
    private Button backBTN;
    private String nombreHijo;
    private RecyclerView recycler;

    private ArrayList<Hijo> listaHijos;
    private HijosCurvasAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curvas_crecimiento);

        buscarBtn = findViewById(R.id.searchHijoCurvasBT);
        buscarET = findViewById(R.id.searchHijoCurvasET );
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
        buscarBtn.setOnClickListener(
                (v)->{


                    Toast.makeText(this, "Debe ingresar un nombre", Toast.LENGTH_LONG).show();

                }

        );
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
