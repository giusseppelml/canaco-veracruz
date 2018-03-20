package com.beta.giusseppe.canacoveracruz.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.api.Network;
import com.beta.giusseppe.canacoveracruz.email.Config;

public class CvitaeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView cursoSelect;
    private EditText TextName;
    private EditText TextEmail;
    private EditText TextTelefono;
    private EditText TextMessage;

    private Button buttonSend;

    private String cursoElegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvitae);

        if(getIntent().getExtras() != null)
        {
            cursoElegido = getIntent().getExtras().getString("elegido");
        }

        //Initializing the views
        cursoSelect = (TextView)findViewById(R.id.textViewCv);
        TextName = (EditText)findViewById(R.id.editTextCvNombre);
        TextEmail = (EditText)findViewById(R.id.editTextCvEmail);
        TextTelefono = (EditText)findViewById(R.id.editTextCvTelefono);
        TextMessage = (EditText)findViewById(R.id.editTextCvMensaje);

        buttonSend = (Button)findViewById(R.id.botonCvEnviar);

        cursoSelect.setText(cursoElegido);

        //Adding click listener
        buttonSend.setOnClickListener(this);

    }

    private void sendEmail() {
        //Getting content for email
        String nombre = TextName.getText().toString().trim();
        String email = TextEmail.getText().toString().trim();
        String tel = TextTelefono.getText().toString().trim();
        String message = TextMessage.getText().toString().trim();

        String mensaje = "Nombre: " +nombre + "\n"
                + "Correo electronico: " + email+ "\n"
                + "Telefono: " + tel + "\n"
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

        if (TextUtils.isEmpty(tel)) {
            TextTelefono.setError("Ingrese un numero telefonico o celular");
            TextTelefono.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(message)) {
            TextMessage.setError("Escriba su mensaje");
            TextMessage.requestFocus();
            return;
        }

        //Email Completo
        Intent intentEmail = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"+ "cleto"));
        intentEmail.setType("plain/text");
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, cursoElegido);
        intentEmail.putExtra(Intent.EXTRA_TEXT, mensaje);
        intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[] {Config.BOLSA_DE_TRABAJO});

        startActivity(intentEmail);
    }

    @Override
    public void onClick(View v) {
        if(Network.isOnlineNet()) {
            sendEmail();
        } else {
            Toast.makeText(CvitaeActivity.this, "No hay conexión", Toast.LENGTH_LONG).show();
        }
    }

}
