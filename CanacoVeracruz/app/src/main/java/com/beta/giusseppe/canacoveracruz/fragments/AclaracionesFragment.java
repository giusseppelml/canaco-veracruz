package com.beta.giusseppe.canacoveracruz.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.email.Config;


public class AclaracionesFragment extends Fragment implements View.OnClickListener{

    private Button formulariobtn;

    public AclaracionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aclaraciones, container, false);

        formulariobtn = (Button)view.findViewById(R.id.botonFormularioA);


        formulariobtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment Formulario = new FormularioFragment();
        String titulo = "Formulario";

        // Crea el nuevo fragmento y la transacción.
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, Formulario);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(titulo);
        transaction.addToBackStack(null);

        Config.x = false;
        ((AppCompatActivity)getActivity()).getSupportActionBar().setElevation(4);

        // Commit a la transacción
        transaction.commit();
    }
}
