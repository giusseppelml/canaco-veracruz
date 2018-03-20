package com.beta.giusseppe.canacoveracruz.fragments;


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
import com.beta.giusseppe.canacoveracruz.adapters.SiemAdapter;
import com.beta.giusseppe.canacoveracruz.api.Api;
import com.beta.giusseppe.canacoveracruz.api.Network;
import com.beta.giusseppe.canacoveracruz.api.RequestHandler;
import com.beta.giusseppe.canacoveracruz.models.Siem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PromotoresFragment extends Fragment {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    //usaremos esta lista para mostrar el héroe en listview
    List<Siem> siem;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView sinRed;

    public PromotoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_promotores, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewSiem);
        sinRed = (ImageView)view.findViewById(R.id.redPromotores);
        siem = new ArrayList<>();

        // llamando al método leer Siem para leer los siems existentes de la base de datos
        if(Network.isOnlineNet()) {
            readSiem();
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
                    refreshSiemList(object.getJSONArray("siem"));
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

    private void readSiem() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_SIEM, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void refreshSiemList(JSONArray siemArray) throws JSONException {
        // limpiar siem anteriores
        siem.clear();

        // recorrer todos los elementos de la matriz json
        // el json que recibimos de la respuesta
        for (int i = 0; i < siemArray.length(); i++) {
            // obteniendo cada objeto siem
            JSONObject obj = siemArray.getJSONObject(i);

            // Añadiendo la noticia a la lista
            siem.add(new Siem(
                    obj.getInt("id"),
                    obj.getString("titulo"),
                    obj.getString("descripcion"),
                    obj.getString("imagen"),
                    obj.getString("estado")
            ));
        }

        // crear el adaptador y configurarlo en la vista de lista
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new SiemAdapter(siem, R.layout.card_view_siem);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
