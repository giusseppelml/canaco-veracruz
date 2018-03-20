package com.beta.giusseppe.canacoveracruz.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beta.giusseppe.canacoveracruz.R;

public class AcercaFragment extends Fragment implements View.OnClickListener{

    TextView textView;

    public AcercaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_acerca, container, false);

        textView = (TextView)view.findViewById(R.id.owlBtnText);
        textView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = new OwlFragment();
        String title = "Creamos tu app";

        // Crea el nuevo fragmento y la transacción.
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        transaction.addToBackStack(null);

        // Commit a la transacción
        transaction.commit();
    }
}
