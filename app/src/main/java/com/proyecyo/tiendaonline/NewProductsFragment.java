package com.proyecyo.tiendaonline;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NewProductsFragment extends Fragment {


    public NewProductsFragment() {

    }


    public static NewProductsFragment newInstance(String param1, String param2) {
        NewProductsFragment fragment = new NewProductsFragment();Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_products, container, false);
    }
}