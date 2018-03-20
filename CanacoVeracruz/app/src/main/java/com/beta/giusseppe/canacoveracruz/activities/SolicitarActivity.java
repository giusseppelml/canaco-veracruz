package com.beta.giusseppe.canacoveracruz.activities;

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
import com.beta.giusseppe.canacoveracruz.email.SendMail;

public class SolicitarActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView cursoSelect;
    private EditText TextName;
    private EditText TextEmail;
    private EditText TextTelefono;
    private EditText TextSubject;
    private EditText TextMessage;

    private Button buttonSend;

    private String cursoElegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar);

        if(getIntent().getExtras() != null)
        {
            cursoElegido = getIntent().getExtras().getString("elegido");
        }

        //Initializing the views
        cursoSelect = (TextView)findViewById(R.id.textViewCurso);
        TextName = (EditText)findViewById(R.id.editTextSolicitarNombre);
        TextEmail = (EditText)findViewById(R.id.editTextSolicitarEmail);
        TextTelefono = (EditText)findViewById(R.id.editTextSolicitarTelefono);
        TextSubject = (EditText)findViewById(R.id.editTextSolicitarEmpresa);
        TextMessage = (EditText)findViewById(R.id.editTextSolicitarMensaje);

        buttonSend = (Button)findViewById(R.id.botonSolicitarEnviar);

        cursoSelect.setText(cursoElegido);

        //Adding click listener
        buttonSend.setOnClickListener(this);

    }

    private void sendEmail() {
        //Getting content for email
        String nombre = TextName.getText().toString().trim();
        String email = TextEmail.getText().toString().trim();
        String tel = TextTelefono.getText().toString().trim();
        String subject = TextSubject.getText().toString().trim();
        String message = TextMessage.getText().toString().trim();

        String mensaje = "Nombre: " +nombre + "\n"
                + "Correo electronico: " + email+ "\n"
                + "Telefono: " + tel + "\n"
                + "Nombre de la Empresa: " + subject + "\n"
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

        //Creating SendMail object
        SendMail sm = new SendMail(this, Config.CAPACITACION, cursoElegido, mensaje);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        if(Network.isOnlineNet()) {
            sendEmail();
        } else {
            Toast.makeText(SolicitarActivity.this, "No hay conexión", Toast.LENGTH_LONG).show();
        }
    }
}
