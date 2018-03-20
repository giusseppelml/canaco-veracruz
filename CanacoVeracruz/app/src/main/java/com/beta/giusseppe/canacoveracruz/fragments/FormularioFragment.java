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

public class FormularioFragment extends Fragment implements View.OnClickListener{

    private EditText NombreComercialText;
    private EditText ContactoText;
    private EditText NumeroTelefonicoText;
    private EditText CorreoText;
    private EditText RazonText;

    private Button buttonSend;

    public FormularioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formulario, container, false);

        //Initializing the views
        NombreComercialText = (EditText)view.findViewById(R.id.editTextNombreComercialEm);
        ContactoText = (EditText)view.findViewById(R.id.editTextContactoEm);
        NumeroTelefonicoText = (EditText)view.findViewById(R.id.editTextTelefonoEm);
        CorreoText = (EditText)view.findViewById(R.id.editTextCorreoEm);
        RazonText = (EditText)view.findViewById(R.id.editTextRazonEm);

        buttonSend = (Button)view.findViewById(R.id.botonEnviarEm);

        //Adding click listener
        buttonSend.setOnClickListener(this);

        return view;
    }

    private void sendEmail() {
        //Getting content for email
        String comer = NombreComercialText.getText().toString().trim();
        String contac = ContactoText.getText().toString().trim();
        String telef = NumeroTelefonicoText.getText().toString().trim();
        String email = CorreoText.getText().toString().trim();
        String razon = RazonText.getText().toString().trim();

        String subject = "Desarrollo Empresarial";

        String mensaje = "Nombre Comercial: " +comer + "\n"
                + "Contacto: " + contac+ "\n"
                + "Teléfono: " + telef+ "\n"
                + "Correo Electrónico: " + email+ "\n"
                + "Mensaje:\n"
                + razon;

        if (TextUtils.isEmpty(comer)) {
            NombreComercialText.setError("Ingrese su Nombre Comercial");
            NombreComercialText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(contac)) {
            ContactoText.setError("Ingrese su Nombre de Contacto");
            ContactoText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(telef)) {
            NumeroTelefonicoText.setError("Ingrese su Telefono");
            NumeroTelefonicoText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            CorreoText.setError("Ingrese su Correo Electrónico");
            CorreoText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(razon)) {
            RazonText.setError("Escriba su mensaje");
            RazonText.requestFocus();
            return;
        }

        //Creating SendMail object
        SendMail sm = new SendMail(getActivity(), Config.EMPRESARIAL, subject, mensaje);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View view) {
        if(Network.isOnlineNet()) {
            sendEmail();
        } else {
            Toast.makeText(getActivity(), "No hay conexión", Toast.LENGTH_LONG).show();
        }
    }
}
