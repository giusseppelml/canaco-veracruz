package com.beta.giusseppe.canacoveracruz.activities;


import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.widget.ListView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.adapters.DetalleDirectorioAdapter;
import com.beta.giusseppe.canacoveracruz.api.Api;
import com.beta.giusseppe.canacoveracruz.api.RequestHandler;
import com.beta.giusseppe.canacoveracruz.models.DetalleDirectorio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetalleDirectorioActivity extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private int idCategoria;
    private String nombre;

    private ListView listViewD;

    private DetalleDirectorioAdapter detalleAdapter;


    private List<DetalleDirectorio> detalle;

    private MenuItem login;
    private MenuItem signup;
    private MenuItem close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_directorio);

        if(getIntent().getExtras() != null)
        {
            idCategoria = getIntent().getExtras().getInt("id");
            nombre = getIntent().getExtras().getString("nombre");
        }

        setToolbar(nombre);

        listViewD = (ListView) findViewById(R.id.listViewDetalleDirec);

        detalle = new ArrayList<>();

        // llamando al método leer héroes para leer heros existentes de la base de datos
        readHeroes();

    }

    private void setToolbar(String nombre)
    {
        //Carga el toolbar que hemos creado a nuestra aplicacion
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarDirecDetalle);
        setSupportActionBar(myToolbar);

        //flecha para retroceder
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(nombre);
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
                    refreshHeroList(object.getJSONArray("directorio"));
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
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_DIRECTORIO+idCategoria, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void refreshHeroList(JSONArray contenido) throws JSONException {
        // limpiar héroes anteriores
        detalle.clear();

        // recorrer todos los elementos de la matriz json
        // el json que recibimos de la respuesta
        for (int i = 0; i < contenido.length(); i++) {
            // obteniendo cada objeto héroe
            JSONObject obj = contenido.getJSONObject(i);

            // Añadiendo el héroe a la lista
            detalle.add(new DetalleDirectorio(
                    obj.getInt("id"),
                    obj.getString("nobre"),
                    obj.getString("direccion"),
                    obj.getString("imagen"),
                    obj.getString("telefono"),
                    obj.getInt("idcategoria")
            ));
        }

        // crear el adaptador y configurarlo en la vista de lista
        this.detalleAdapter = new DetalleDirectorioAdapter(this, R.layout.list_detalle, detalle);
        listViewD.setAdapter(detalleAdapter);
        registerForContextMenu(listViewD);
    }
}
