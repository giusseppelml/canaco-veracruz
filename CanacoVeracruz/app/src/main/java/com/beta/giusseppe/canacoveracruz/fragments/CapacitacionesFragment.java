package com.beta.giusseppe.canacoveracruz.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.activities.CursoNotaActivity;
import com.beta.giusseppe.canacoveracruz.activities.SolicitarActivity;
import com.beta.giusseppe.canacoveracruz.adapters.CapacitacionesAdapter;
import com.beta.giusseppe.canacoveracruz.api.Api;
import com.beta.giusseppe.canacoveracruz.api.Network;
import com.beta.giusseppe.canacoveracruz.api.RequestHandler;
import com.beta.giusseppe.canacoveracruz.api.SharedPrefManager;
import com.beta.giusseppe.canacoveracruz.models.Contenido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CapacitacionesFragment extends Fragment {

    private static final String CAPACITACIONES = "cursos";
    private static final String PUBLICO = "publico";

    private static final String VISITANTE = Api.URL_READ_CONTENIDO_CATEGORIA_PRIVADO+CAPACITACIONES+Api.URL_READ_CONTENIDO_PRIVADO_ESTADO+PUBLICO;
    private static final String LOGUEADO = Api.URL_READ_CONTENIDO+CAPACITACIONES;
    String verificar;

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    //usaremos esta lista para mostrar el héroe en listview
    List<Contenido> conten;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView sinRed;

    public CapacitacionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_capacitaciones, container, false);

        if (!SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
            verificar = VISITANTE;
        }else{
            verificar = LOGUEADO;
        }

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewCapacitaciones);
        sinRed = (ImageView)view.findViewById(R.id.redCurso);
        conten = new ArrayList<>();


        // llamando al método leer Contenido para leer los cursos existentes de la base de datos
        if(Network.isOnlineNet()) {
            readContenido(verificar);
        }else{
            this.sinRed.setVisibility(View.VISIBLE);
        }

        return view;
    }

    // clase interna para realizar la solicitud de red extendiendo un AsyncTask
    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        // la url donde necesitamos enviar la solicitud
        String url;

        //the parameters
        HashMap<String, String> params;

        // el código de solicitud para definir si se trata de un GET o POST
        int requestCode;

        // constructor para inicializar valores
        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        // este método dará la respuesta de la petición
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    //Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    // refrescar el herolista después de cada operación
                    // para que obtengamos una lista actualizada
                    refreshContenidoList(object.getJSONArray("contenido"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // la operación de red se realizará en segundo plano
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }

    private void readContenido(String verificar) {
        PerformNetworkRequest request = new PerformNetworkRequest(verificar, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void refreshContenidoList(JSONArray contenido) throws JSONException {
        // limpiar héroes anteriores
        conten.clear();

        // recorrer todos los elementos de la matriz json
        // el json que recibimos de la respuesta
        for (int i = 0; i < contenido.length(); i++) {
            // obteniendo cada objeto héroe
            JSONObject obj = contenido.getJSONObject(i);

            // Añadiendo el héroe a la lista
            conten.add(new Contenido(
                    obj.getInt("id"),
                    obj.getString("titulo"),
                    obj.getString("descripcion"),
                    obj.getString("imagen"),
                    obj.getString("fecha"),
                    obj.getString("categoria"),
                    obj.getString("estado")
            ));
        }


        // crear el adaptador y configurarlo en la vista de lista
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new CapacitacionesAdapter(conten, R.layout.card_view_capacitaciones, new CapacitacionesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contenido conten, int position) {

                Intent intent = new Intent(getActivity(), CursoNotaActivity.class);
                intent.putExtra("titulo", conten.getTitulo());
                intent.putExtra("descripcion", conten.getDescripcion());
                intent.putExtra("imagen", conten.getImagen());
                intent.putExtra("fecha", conten.getFecha());
                getActivity().startActivity(intent);
            }
        }, new CapacitacionesAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(Contenido conten, int position) {
                Intent intent = new Intent(getActivity(), SolicitarActivity.class);
                intent.putExtra("elegido", conten.getTitulo());
                startActivity(intent);
            }
        }
        );

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
