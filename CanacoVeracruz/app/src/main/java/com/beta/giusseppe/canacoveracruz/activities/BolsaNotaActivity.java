package com.beta.giusseppe.canacoveracruz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.adapters.BolsaNotaAdapter;
import com.beta.giusseppe.canacoveracruz.models.Nota;

import java.util.ArrayList;
import java.util.List;

public class BolsaNotaActivity extends AppCompatActivity {

    private String bolsaTitulo;
    private String bolsaDescripcion;
    private String bolsaImagen;
    private String bolsaFecha;

    private List<Nota> nota;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolsa);

        setToolbar();

        if(getIntent().getExtras() != null)
        {
            bolsaTitulo = getIntent().getExtras().getString("titulo");
            bolsaDescripcion = getIntent().getExtras().getString("descripcion");
            bolsaImagen = getIntent().getExtras().getString("imagen");
            bolsaFecha = getIntent().getExtras().getString("fecha");
        }

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewBolsa);

        this.nota = DatosDeLaNota();

        // crear el adaptador y configurarlo en la vista de lista
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new BolsaNotaAdapter(nota, R.layout.list_bolsa);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    //Lista de datos para crear los datos a mostrar en afiliacion
    private List<Nota> DatosDeLaNota()
    {
        List<Nota> nota = new ArrayList<Nota>() {{
            add(new Nota(bolsaTitulo,bolsaDescripcion,bolsaImagen,bolsaFecha));
        }};
        return nota;
    }

    private void setToolbar()
    {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarBolsa);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Bolsa de Trabajo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_bolsa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.EnviarCurriV:
                Intent intent = new Intent(BolsaNotaActivity.this, CvitaeActivity.class);
                intent.putExtra("elegido", bolsaTitulo);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
