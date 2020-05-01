package com.example.pediatrapp.adapter;

import androidx.fragment.app.Fragment;

public interface OnDataSubmitted {

    void onData(Fragment fragment, String type, String... args);
}
