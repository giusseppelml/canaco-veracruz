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
import com.beta.giusseppe.canacoveracruz.activities.MainActivity;
import com.beta.giusseppe.canacoveracruz.api.Api;
import com.beta.giusseppe.canacoveracruz.api.RequestHandler;
import com.beta.giusseppe.canacoveracruz.api.SharedPrefManager;
import com.beta.giusseppe.canacoveracruz.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginFragment extends Fragment {

    private EditText editTextUsername, editTextPassword;
    private ProgressDialog progressDialog;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextUsername = (EditText)view.findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)view.findViewById(R.id.editTextPassword);


        //if user presses on login
        //calling the method login
        view.findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        return view;
    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Ingrese su número de Afiliado");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Ingrese su contraseña");
            editTextPassword.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(getActivity(),"Comprobando datos","Espere por favor...",false,false);
                //progressBar = (ProgressBar) findViewById(R.id.progressBar);
                //progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_LONG).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("nombre"),
                                userJson.getString("numeroafiliado"),
                                userJson.getString("role")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getActivity()).userLogin(user);

                        //starting the profile activity
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.cancel();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("numeroafiliado", username);
                params.put("password", password);

                //returing the response
                return requestHandler.sendPostRequest(Api.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }

}
