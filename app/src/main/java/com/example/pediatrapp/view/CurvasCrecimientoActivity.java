package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.pediatrapp.R;

public class CurvasCrecimientoActivity extends AppCompatActivity {

    private ImageButton buscarBtn;
    private EditText buscarET;
    private Button backBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curvas_crecimiento);

        buscarBtn = findViewById(R.id.searchHijoCurvasBT);
        buscarET = findViewById(R.id.searchHijoCurvasET );
        backBTN = findViewById(R.id.backCurvas);

        backBTN.setOnClickListener(
                (v)->{
                    this.finish();
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
