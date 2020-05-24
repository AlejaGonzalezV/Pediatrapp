package com.example.pediatrapp.pagercontroller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Hijos extends Fragment {

    private ListView listaHijos;
    private TextView nombreHijo;
    private Button verHijo;
    private CardView cardViewHijos;

    public Hijos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hijos, container, false);

        listaHijos = view.findViewById(R.id.listViewHijos);
        nombreHijo =view.findViewById(R.id.nombreHijoTV);
        verHijo = view.findViewById(R.id.verHijoBTN);
        cardViewHijos = view.findViewById(R.id.cardViewHijos);


        return view;
    }

    public ListView getListaHijos() {
        return listaHijos;
    }

    public void setListaHijos(ListView listaHijos) {
        this.listaHijos = listaHijos;
    }

    public TextView getNombreHijo() {
        return nombreHijo;
    }

    public void setNombreHijo(TextView nombreHijo) {
        this.nombreHijo = nombreHijo;
    }

    public Button getVerHijo() {
        return verHijo;
    }

    public void setVerHijo(Button verHijo) {
        this.verHijo = verHijo;
    }

    public CardView getCardViewHijos() {
        return cardViewHijos;
    }

    public void setCardViewHijos(CardView cardViewHijos) {
        this.cardViewHijos = cardViewHijos;
    }
}

