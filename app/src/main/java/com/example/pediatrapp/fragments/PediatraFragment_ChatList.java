package com.example.pediatrapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.view.ActivityMainPediatra;

public class PediatraFragment_ChatList extends Fragment {

    private EditText SearchChatET;
    private ImageButton SearchChatBT;
    private Switch switchDisp;
    private RecyclerView pediatra_ChatList;
    private Button FiltroChatBT;

    public PediatraFragment_ChatList() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pediatrafragment_chatlist, container, false);

        SearchChatET = view.findViewById(R.id.SearchChatET);
        SearchChatBT = view.findViewById(R.id.SearchChatBT);
        switchDisp = view.findViewById(R.id.switchDisp);
        pediatra_ChatList = view.findViewById(R.id.pediatra_ChatList);
        FiltroChatBT = view.findViewById(R.id.FiltroChatBT);

        SearchChatBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>", "Buscar");
            }
        });

        FiltroChatBT.setOnClickListener(
                (v)->{
                    Log.e(">>>","FiltroChat");
                }
        );
        return view;
    }
}
