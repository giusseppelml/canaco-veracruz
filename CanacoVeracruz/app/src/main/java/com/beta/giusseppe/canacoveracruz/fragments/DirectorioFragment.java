package com.beta.giusseppe.canacoveracruz.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.activities.DetalleDirectorioActivity;
import com.beta.giusseppe.canacoveracruz.adapters.DirectorioAdapter;
import com.beta.giusseppe.canacoveracruz.api.Api;
import com.beta.giusseppe.canacoveracruz.api.Network;
import com.beta.giusseppe.canacoveracruz.api.RequestHandler;
import com.beta.giusseppe.canacoveracruz.models.Directorio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectorioFragment extends Fragment {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;


    //items para las opciones del menu
    private MenuItem itemGridView;

    //usaremos esta lista para mostrar el directorio en listview y gridview
    List<Directorio> direcList;

    private RecyclerView mRecyclerViewGrid;
    private RecyclerView.Adapter mAdapterGrid;
    private RecyclerView.LayoutManager mLayoutManagerGrid;

    private ImageView sinRed;

    public DirectorioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_directorio, container, false);

        mRecyclerViewGrid = (RecyclerView)view.findViewById(R.id.recyclerViewDirectorioGrid);
        sinRed = (ImageView)view.findViewById(R.id.redDirec);
        direcList = new ArrayList<>();

        // llamando al método leer héroes para leer heros existentes de la base de datos
        if(Network.isOnlineNet()) {
            readHeroes();
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
                    refreshHeroList(object.getJSONArray("categorias"));
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

    private void readHeroes() {
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_CATEGORIA, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void refreshHeroList(JSONArray contenido) throws JSONException {
        // limpiar héroes anteriores
        direcList.clear();

        // recorrer todos los elementos de la matriz json
        // el json que recibimos de la respuesta
        for (int i = 0; i < contenido.length(); i++) {
            // obteniendo cada objeto héroe
            JSONObject obj = contenido.getJSONObject(i);

            // Añadiendo el héroe a la lista
            direcList.add(new Directorio(
                    obj.getInt("id"),
                    obj.getString("categoria"),
                    obj.getString("imagen")
            ));
        }

        // crear el adaptador y configurarlo en la vista de lista
        //mLayoutManager = new LinearLayoutManager(this);
        mLayoutManagerGrid = new GridLayoutManager(getActivity(), 3);


        mAdapterGrid = new DirectorioAdapter(direcList, R.layout.grid_directorio, new DirectorioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Directorio direc, int position) {
                Intent intent = new Intent(getActivity(), DetalleDirectorioActivity.class);
                intent.putExtra("id", direc.getId());
                intent.putExtra("nombre", direc.getCategoria());
                startActivity(intent);
            }
        });

        mRecyclerViewGrid.setHasFixedSize(true);
        mRecyclerViewGrid.setItemAnimator(new DefaultItemAnimator());

        mRecyclerViewGrid.setLayoutManager(mLayoutManagerGrid);
        mRecyclerViewGrid.setAdapter(mAdapterGrid);
    }

}
