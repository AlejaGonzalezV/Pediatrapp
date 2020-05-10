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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;

public class ParentFragment_ChatList extends Fragment {

    private EditText SearchChatPaET;
    private ImageButton SearchChatPaBT;
    private RecyclerView padre_ChatList;
    private Button FiltroChatPaBT;
    private Padre padre;

    public ParentFragment_ChatList(Padre padre) {
        this.padre = padre;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parentfragment_chatlist, container, false);

        SearchChatPaET = view.findViewById(R.id.SearchChatPaET);
        SearchChatPaBT = view.findViewById(R.id.SearchChatPaBT);
        padre_ChatList = view.findViewById(R.id.padre_ChatList);
        FiltroChatPaBT = view.findViewById(R.id.FiltroChatPaBT);

        SearchChatPaBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>","BuscarChat");
            }
        });

        FiltroChatPaBT.setOnClickListener(
                (v) ->{
                    Log.e(">>>", "FiltroChat");
                }
        );
        return view;
    }
}
