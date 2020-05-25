package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.fragments.ConsultaFormulaFragment;
import com.example.pediatrapp.fragments.ConsultaRegistroFragment;
import com.example.pediatrapp.fragments.DiagnosticoFragment;
import com.example.pediatrapp.fragments.FormulaFragment;
import com.example.pediatrapp.fragments.HistoriaPadreFragment;
import com.example.pediatrapp.fragments.HistoriaPediatraFragment;
import com.example.pediatrapp.fragments.ListaHijosFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;

public class HistoriaClinicaActivity extends AppCompatActivity implements OnDataSubmitted {

    private Fragment historiaPadreFragment, historiaPediatraFragment, diagnosticoFragment, formulaFragment,
            consultaRegistroFragment, consultaFormulaFragment, listaHijosFragment;

    private String hijoId;
    private String diagnostico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia_clinica);

        historiaPadreFragment = new HistoriaPadreFragment();
        ((HistoriaPadreFragment) historiaPadreFragment).setListener(this);

        historiaPediatraFragment = new HistoriaPediatraFragment();
        ((HistoriaPediatraFragment) historiaPediatraFragment).setListener(this);

        diagnosticoFragment = new DiagnosticoFragment();
        ((DiagnosticoFragment) diagnosticoFragment).setListener(this);

        formulaFragment = new FormulaFragment();
        ((FormulaFragment) formulaFragment).setListener(this);

        consultaRegistroFragment = new ConsultaRegistroFragment();
        ((ConsultaRegistroFragment) consultaRegistroFragment).setListener(this);

        consultaFormulaFragment = new ConsultaFormulaFragment();
        ((ConsultaFormulaFragment) consultaFormulaFragment).setListener(this);

        listaHijosFragment = new ListaHijosFragment();
        ((ListaHijosFragment) listaHijosFragment).setListener(this);

        findUser();

    }

    public void showFragment(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.layout, fragment);
        transaction.commit();


    }

    public void findUser(){

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    if(child.getKey().equals(id)){

                        showFragment(historiaPediatraFragment);

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query query2 = FirebaseDatabase.getInstance().getReference().child("Padres");
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    if(child.getKey().equals(id)){

                        showFragment(listaHijosFragment);

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onData(Fragment fragment, String type, String... args) {


        if(fragment.equals(listaHijosFragment)){

            if(type.equals("next")){

                hijoId = args[0];

                showFragment(historiaPadreFragment);



            } else if(type.equals("back")){

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }


        } else if(fragment.equals(historiaPadreFragment)){

            if(type.equals("next")){

                diagnostico = args[0];
                showFragment(consultaRegistroFragment);
            }


        }


    }

    public String getHijoId() {
        return hijoId;
    }

    public String getDiagnostico() {
        return diagnostico;
    }
}
