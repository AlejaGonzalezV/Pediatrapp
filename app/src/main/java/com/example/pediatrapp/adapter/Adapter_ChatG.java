package com.example.pediatrapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.ChatGrupal;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.utilities.HTTPSWebUtilDomi;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_ChatG extends BaseAdapter {

    private ArrayList<ChatGrupal> chatG;

    public Adapter_ChatG() {
        this.chatG = new ArrayList<>();
    }

    public ArrayList<ChatGrupal> getChatG() {
        return chatG;
    }

    public void setChatG(ArrayList<ChatGrupal> chatG) {
        this.chatG = chatG;
        notifyDataSetChanged();
    }

    public void addChatG(ChatGrupal chat){
        chatG.add(chat);
        notifyDataSetChanged();
    }

    public void remove(){
        chatG.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return chatG.size();
    }

    @Override
    public Object getItem(int position) {
        return chatG.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.parentfragment_chatlistitem, null);

        ChatGrupal chat = chatG.get(position);
        TextView nombrePediatraTV = root.findViewById(R.id.nombrePediatraTV);
        CircleImageView imagePediatraCIV = root.findViewById(R.id.imagePediatraCIV);
        TextView Ped_Estatus = root.findViewById(R.id.Ped_Estatus);

        nombrePediatraTV.setText(chat.getNombre());
        Date inicial = new Date(chat.getFecha_creacion());
        Date fin = new Date(System.currentTimeMillis());


        if(getDiferenciaDias(inicial, fin) == ChatGrupal.ALARMA){

            Log.e(">>>>", getDiferencia(inicial, fin));
        long hRestantes = 24 - getDiferenciaHoras(inicial, fin) ;
        Ped_Estatus.setText(String.valueOf(hRestantes) + "h Restantes");
        Log.e(">>>", "HORAS RESTANTES: "+ hRestantes);

        }else{
            long hRestantes = 24 - getDiferenciaHoras(inicial, fin) ;
            Ped_Estatus.setText("1d "+ hRestantes+"h Restantes");
        }

        //INSERTAR IMAGEN
        imagePediatraCIV.setImageResource(R.drawable.chatgrupal);

        return root ;
    }

    public long getDiferenciaDias(Date fechaInicial, Date fechaFinal){

        long diferencia = fechaFinal.getTime() - fechaInicial.getTime();

        Log.e(">>>>>", "fechaInicial : " + fechaInicial);
        Log.e(">>>>>", "fechaFinal : " + fechaFinal);

        long segsMilli = 1000;
        long minsMilli = segsMilli * 60;
        long horasMilli = minsMilli * 60;
        long diasMilli = horasMilli * 24;

        long diasTranscurridos = diferencia / diasMilli;
        diferencia = diferencia % diasMilli;

        long horasTranscurridos = diferencia / horasMilli;
        diferencia = diferencia % horasMilli;

        Log.e(">>>>", "HORAS TRANSCURRIDAS "+ horasTranscurridos);
        Log.e(">>>>", "DIAS TRANSCURRIDAS "+ horasTranscurridos);
//
//        long minutosTranscurridos = diferencia / minsMilli;
//        diferencia = diferencia % minsMilli;
//
//        long segsTranscurridos = diferencia / segsMilli;
//        return "diasTranscurridos: " + diasTranscurridos + " , horasTranscurridos: " + horasTranscurridos +
//                " , minutosTranscurridos: " + minutosTranscurridos + " , segsTranscurridos: " + segsTranscurridos;

        return diasTranscurridos;
    }

    public long getDiferenciaHoras(Date fechaInicial, Date fechaFinal){

        long diferencia = fechaFinal.getTime() - fechaInicial.getTime();

        Log.e(">>>>>", "fechaInicial : " + fechaInicial);
        Log.e(">>>>>", "fechaFinal : " + fechaFinal);

        long segsMilli = 1000;
        long minsMilli = segsMilli * 60;
        long horasMilli = minsMilli * 60;
        long diasMilli = horasMilli * 24;

        long diasTranscurridos = diferencia / diasMilli;
        diferencia = diferencia % diasMilli;

        long horasTranscurridos = diferencia / horasMilli;
        diferencia = diferencia % horasMilli;

//
//        long minutosTranscurridos = diferencia / minsMilli;
//        diferencia = diferencia % minsMilli;
//
//        long segsTranscurridos = diferencia / segsMilli;
//        return "diasTranscurridos: " + diasTranscurridos + " , horasTranscurridos: " + horasTranscurridos +
//                " , minutosTranscurridos: " + minutosTranscurridos + " , segsTranscurridos: " + segsTranscurridos;

        return horasTranscurridos;
    }

//    public int getHora(Date date){
//
//        int hRestantes = 0;
//        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
//
//        String[] split = hourFormat.format(date).split(":");
//        hRestantes = Integer.valueOf(split[0]);
//        Log.e(">>>>", "Hora: "+ hRestantes);
//
//        return hRestantes;
//    }

    public String getDiferencia(Date fechaInicial, Date fechaFinal){

        long diferencia = fechaFinal.getTime() - fechaInicial.getTime();

        Log.e(">>>>>", "fechaInicial : " + fechaInicial);
        Log.e(">>>>>", "fechaFinal : " + fechaFinal);

        long segsMilli = 1000;
        long minsMilli = segsMilli * 60;
        long horasMilli = minsMilli * 60;
        long diasMilli = horasMilli * 24;

        long diasTranscurridos = diferencia / diasMilli;
        diferencia = diferencia % diasMilli;

        long horasTranscurridos = diferencia / horasMilli;
        diferencia = diferencia % horasMilli;

        Log.e(">>>>", "HORAS TRANSCURRIDAS "+ horasTranscurridos);
        Log.e(">>>>", "DIAS TRANSCURRIDAS "+ horasTranscurridos);

        long minutosTranscurridos = diferencia / minsMilli;
        diferencia = diferencia % minsMilli;

        long segsTranscurridos = diferencia / segsMilli;
        return "diasTranscurridos: " + diasTranscurridos + " , horasTranscurridos: " + horasTranscurridos +
                " , minutosTranscurridos: " + minutosTranscurridos + " , segsTranscurridos: " + segsTranscurridos;

    }
}
