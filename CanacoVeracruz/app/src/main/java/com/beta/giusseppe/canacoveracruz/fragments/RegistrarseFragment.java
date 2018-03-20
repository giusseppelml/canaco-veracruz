package com.beta.giusseppe.canacoveracruz.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.activities.RegistroCorrectoActivity;
import com.beta.giusseppe.canacoveracruz.api.Api;
import com.beta.giusseppe.canacoveracruz.api.RequestHandler;
import com.beta.giusseppe.canacoveracruz.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegistrarseFragment extends Fragment {

    EditText editTextUsername, editTextEmail, editTextPassword;
    private ProgressDialog progressDialog;

    View view;

    public RegistrarseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registrarse, container, false);

        editTextUsername = (EditText)view.findViewById(R.id.editTextUsername);
        editTextEmail = (EditText)view.findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)view.findViewById(R.id.editTextPassword);
        //radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);


        view.findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
            }
        });

        return view;
    }

    private void registerUser() {
        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        final String gender = "INACTIVO";

        //first we will do the validations

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Ingrese un nombre");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Ingrese su No. de Afiliado");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Ingrese una contraseña");
            editTextPassword.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("nombre", username);
                params.put("numeroafiliado", email);
                params.put("password", password);
                params.put("role", gender);

                //returing the response
                return requestHandler.sendPostRequest(Api.URL_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressDialog = ProgressDialog.show(getActivity(),"Enviando Registro","Espere por favor...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("nombre"),
                                userJson.getString("numeroafiliado"),
                                userJson.getString("role")
                        );

                        Intent intent = new Intent(getActivity(), RegistroCorrectoActivity.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }
}
