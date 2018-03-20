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
import com.beta.giusseppe.canacoveracruz.adapters.CursoNotaAdapter;
import com.beta.giusseppe.canacoveracruz.models.Nota;

import java.util.ArrayList;
import java.util.List;

public class CursoNotaActivity extends AppCompatActivity {

    private String cursoTitulo;
    private String cursoDescripcion;
    private String cursoImagen;
    private String cursoFecha;

    private List<Nota> nota;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        setToolbar();

        if(getIntent().getExtras() != null)
        {
            cursoTitulo = getIntent().getExtras().getString("titulo");
            cursoDescripcion = getIntent().getExtras().getString("descripcion");
            cursoImagen = getIntent().getExtras().getString("imagen");
            cursoFecha = getIntent().getExtras().getString("fecha");
        }

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewCurso);

        this.nota = DatosDeLaNota();

        // crear el adaptador y configurarlo en la vista de lista
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new CursoNotaAdapter(nota, R.layout.list_curso);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    //Lista de datos para crear los datos a mostrar en afiliacion
    private List<Nota> DatosDeLaNota()
    {
        List<Nota> nota = new ArrayList<Nota>() {{
            add(new Nota(cursoTitulo,cursoDescripcion,cursoImagen,cursoFecha));
        }};
        return nota;
    }

    private void setToolbar()
    {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarCurso);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Cursos");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_cursos, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.solicitarCurso:
                Intent intent = new Intent(CursoNotaActivity.this, SolicitarActivity.class);
                intent.putExtra("elegido", cursoTitulo);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
