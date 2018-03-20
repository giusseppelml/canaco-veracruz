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
import com.beta.giusseppe.canacoveracruz.activities.NotaActivity;
import com.beta.giusseppe.canacoveracruz.adapters.NoticiasAdapter;
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

public class NoticiasFragment extends Fragment {

    private static final String NOTICIAS = "noticias";
    private static final String PUBLICO = "publico";

    private static final String VISITANTE = Api.URL_READ_CONTENIDO_CATEGORIA_PRIVADO+NOTICIAS+Api.URL_READ_CONTENIDO_PRIVADO_ESTADO+PUBLICO;
    private static final String LOGUEADO = Api.URL_READ_CONTENIDO+NOTICIAS;
    String verificar;

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    //usaremos esta lista para mostrar el héroe en listview
    List<Contenido> conten;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView sinRed;

    public NoticiasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        if (!SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
            verificar = VISITANTE;
        }else{
            verificar = LOGUEADO;
        }

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewNoticias);
        sinRed = (ImageView)view.findViewById(R.id.red);

        conten = new ArrayList<>();

        // llamando al método leer Contenido para leer las Noticias existentes de la base de datos
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
                    // refrescar la lista después de cada operación
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
        // limpiar noticias anteriores
        conten.clear();
        int i;

        // recorrer todos los elementos de la matriz json
        // el json que recibimos de la respuesta
        for (i = 0; i < contenido.length(); i++) {
            // obteniendo cada objeto noticia
            JSONObject obj = contenido.getJSONObject(i);

            // Añadiendo la noticia a la lista
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

        mAdapter = new NoticiasAdapter(conten, R.layout.card_view_noticias, new NoticiasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contenido conten, int position) {

                Intent intent = new Intent(getActivity(), NotaActivity.class);
                intent.putExtra("titulo", conten.getTitulo());
                intent.putExtra("descripcion", conten.getDescripcion());
                intent.putExtra("imagen", conten.getImagen());
                intent.putExtra("fecha", conten.getFecha());
                getActivity().startActivity(intent);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
