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
import com.example.pediatrapp.model.Diagnostico;
import com.example.pediatrapp.model.FormulaMedica;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class HistoriaClinicaActivity extends AppCompatActivity implements OnDataSubmitted {

    private Fragment historiaPadreFragment, historiaPediatraFragment, diagnosticoFragment, formulaFragment,
            consultaRegistroFragment, consultaFormulaFragment, listaHijosFragment;

    private String hijoId;
    private String padreId;
    private String diagnostico;
    private Pediatra pediatra;
    private String tipo;
    private String idPed;
    private String data;
    private boolean formula;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia_clinica);

        Intent intent = getIntent();
        padreId = intent.getStringExtra("idUser");
        hijoId = intent.getStringExtra("idHijo");
        Log.e("ANTEEEEES", String.valueOf(padreId == null));

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

                        pediatra = child.getValue(Pediatra.class);
                        tipo = "Pediatra";
                        Bundle args = new Bundle();

                        args.putString("ids", padreId+"/"+hijoId);
                        historiaPediatraFragment.setArguments(args);



                        showFragment(historiaPediatraFragment);

                        /*
                        Query query = FirebaseDatabase.getInstance().getReference().child("Padres").child(padreId);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Padre padre = dataSnapshot.getValue(Padre.class);
                                for(Object value: padre.getPediatras_asig().values()){

                                    if(value.equals(id)){

                                        showFragment(historiaPediatraFragment);
                                        Bundle args = new Bundle();
                                        args.putString("idUser", padreId);
                                        args.putString("idHijo", hijoId);

                                    }else {

                                        //Accion para pediatra sin autorización

                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                         */

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

                        tipo = "Padre";
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

                this.finish();

            }


        } else if(fragment.equals(historiaPadreFragment)){

            if(type.equals("next")){

                diagnostico = args[0];
                Bundle args1 = new Bundle();
                args1.putString("idDiag", diagnostico);
                consultaRegistroFragment.setArguments(args1);
                showFragment(consultaRegistroFragment);


            } else if(type.equals("back")){

                showFragment(listaHijosFragment);

            }


        }else if(fragment.equals(historiaPediatraFragment)){

            if(type.equals("next")){

                diagnostico = args[0];
                Bundle args1 = new Bundle();
                args1.putString("ids", diagnostico+"/"+hijoId);
                consultaRegistroFragment.setArguments(args1);
                showFragment(consultaRegistroFragment);

            } else if(type.equals("back")){

                this.finish();

            } else if(type.equals("add")){

                data = "";
                Bundle args1 = new Bundle();
                args1.putString("ids", hijoId);
                diagnosticoFragment.setArguments(args1);
                showFragment(diagnosticoFragment);

            }

        }else if(fragment.equals(consultaRegistroFragment)){

            if(type.equals("next")){

                Bundle args1 = new Bundle();
                args1.putString("ids", diagnostico+"/"+hijoId);
                consultaFormulaFragment.setArguments(args1);
                showFragment(consultaFormulaFragment);

            }else if(type.equals("back")){

                if(tipo.equals("Pediatra")){

                    showFragment(historiaPediatraFragment);

                }else if(tipo.equals("Padre")){

                    showFragment(historiaPadreFragment);

                }

            }

        }else if(fragment.equals(consultaFormulaFragment)){

            if(type.equals("back")){


                showFragment(consultaRegistroFragment);

            }

        }else if(fragment.equals(diagnosticoFragment)){

            formula = false;


            if(type.equals("formula")){

                showFragment(formulaFragment);

            }else if(type.equals("back")){

                showFragment(historiaPediatraFragment);

            }else if(type.equals("next")){

                for(int i=0; i<args.length; i++){

                    data+=args[i]+",";

                }

                agregarRegistro();

            }

        }else if(fragment.equals(formulaFragment)){

            if(type.equals("back")){

                showFragment(diagnosticoFragment);

            }else if(type.equals("next")){

                formula= true;

                for(int i=0; i<args.length; i++){

                    data+= args[i]+",";

                }

                showFragment(diagnosticoFragment);

            }


        }


    }

    public void agregarRegistro(){

        if(formula){

            String[] val = data.split(",");
            String fechaFormula = val[0];
            String posologia = val[1];
            String titulo = val[2];
            String fechaR = val[3];
            String diagnostico = val[4];
            String nombrePed = val[5];
            String idPed = val[6];
            String firma = val[7];

            FormulaMedica formula = new FormulaMedica(posologia, fechaFormula, idPed, nombrePed,firma);
            String idDiagnostico = FirebaseDatabase.getInstance().getReference().child("Historia_clinica").child(hijoId).push().getKey();
            Diagnostico diagnos = new Diagnostico(idDiagnostico, diagnostico,fechaR,idPed,nombrePed,titulo,formula);
            FirebaseDatabase.getInstance().getReference().child("Historia_clinica").child(hijoId).child(idDiagnostico).setValue(diagnos);
            Toast.makeText(this, "El registro se ha añadido con éxito", Toast.LENGTH_SHORT);
            showFragment(historiaPediatraFragment);


        }else {

            String[] val = data.split(",");
            String titulo = val[0];
            String fechaR = val[1];
            String diagnostico = val[2];
            String nombrePed = val[3];
            String idPed = val[4];

            String idDiagnostico = FirebaseDatabase.getInstance().getReference().child("Historia_clinica").child(hijoId).push().getKey();
            Diagnostico diagnos = new Diagnostico(idDiagnostico, diagnostico,fechaR,idPed,nombrePed,titulo);
            FirebaseDatabase.getInstance().getReference().child("Historia_clinica").child(hijoId).child(idDiagnostico).setValue(diagnos);
            Toast.makeText(this, "El registro se ha añadido con éxito", Toast.LENGTH_SHORT);
            showFragment(historiaPediatraFragment);

        }


    }

    public String getHijoId() {
        return hijoId;
    }

    public String getPadreId() {
        return padreId;
    }

    public String getDiagnostico() {
        return diagnostico;
    }
}
