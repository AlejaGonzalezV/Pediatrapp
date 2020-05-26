package com.example.pediatrapp.pagercontroller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.model.Padre;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class Informacion extends Fragment {

    private String id;
    private View view;
    private TextView telPadre, correoPadre;

    public Informacion(String id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_informacion, container, false);
        telPadre = view.findViewById(R.id.telPadre);
        correoPadre = view.findViewById(R.id.correoPadre);

        Query query = FirebaseDatabase.getInstance().getReference().child("Padres");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Padre padre = child.getValue(Padre.class);
                    if(padre.getId().equals(id)){

                        telPadre.setText(padre.getTelefono());
                        correoPadre.setText(padre.getCorreo());

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }
}
