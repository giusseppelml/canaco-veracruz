package com.beta.giusseppe.canacoveracruz.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.api.Network;
import com.beta.giusseppe.canacoveracruz.email.Config;
import com.beta.giusseppe.canacoveracruz.email.SendMail;

public class ContactanosFragment extends Fragment implements View.OnClickListener {

    private EditText TextName;
    private EditText TextEmail;
    private EditText TextSubject;
    private EditText TextMessage;

    private Button buttonSend;

    public ContactanosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contactanos, container, false);

        //Initializing the views
        TextName = (EditText)view.findViewById(R.id.editTextNombre);
        TextEmail = (EditText)view.findViewById(R.id.editTextEmail);
        TextSubject = (EditText)view.findViewById(R.id.editTextAsunto);
        TextMessage = (EditText)view.findViewById(R.id.editTextMensaje);

        buttonSend = (Button)view.findViewById(R.id.botonEnviar);

        //Adding click listener
        buttonSend.setOnClickListener(this);

        return view;
    }

    private void sendEmail() {
        //Getting content for email
        String nombre = TextName.getText().toString().trim();
        String email = TextEmail.getText().toString().trim();
        String subject = TextSubject.getText().toString().trim();
        String message = TextMessage.getText().toString().trim();

        String mensaje = "Nombre: " +nombre + "\n"
                + "Correo Electronico: " + email+ "\n"
                + "Mensaje:\n"
                + message;

        if (TextUtils.isEmpty(nombre)) {
            TextName.setError("Ingrese su Nombre");
            TextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            TextEmail.setError("Ingrese su E-Mail");
            TextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(subject)) {
            TextSubject.setError("Ingrese un Asunto");
            TextSubject.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(message)) {
            TextMessage.setError("Escriba su mensaje");
            TextMessage.requestFocus();
            return;
        }

        //Creating SendMail object
        SendMail sm = new SendMail(getActivity(), Config.CONTACTANOS, subject, mensaje);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        if(Network.isOnlineNet()) {
            sendEmail();
        } else {
            Toast.makeText(getActivity(), "No hay conexión", Toast.LENGTH_LONG).show();
        }
    }
}
