package com.example.pediatrapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Mensaje;

import java.util.ArrayList;

public class MessageListAdapter extends BaseAdapter {

    private ArrayList<Mensaje> messages;
    private String userID = " ";

    public MessageListAdapter() {
        messages = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root;

        if(userID.equals(messages.get(position).getUser_id())){
            root = inflater.inflate(R.layout.message_item_mine, null);
        }else{
            root = inflater.inflate(R.layout.message_item_others, null);
        }

        TextView message_row = root.findViewById(R.id.message_row);
        message_row.setText(messages.get(position).getBody());

        return root ;
    }

    public void addMessage(Mensaje mensaje){
        messages.add(mensaje);
        notifyDataSetChanged();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        notifyDataSetChanged();
    }
}
