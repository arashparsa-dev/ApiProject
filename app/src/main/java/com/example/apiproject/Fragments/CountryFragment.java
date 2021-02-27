package com.example.apiproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apiproject.R;


public class CountryFragment extends Fragment {

    public CountryFragment() {
        // Required empty public constructor
    }

    public static CountryFragment getInstance(){
        return new CountryFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false);
    }
}