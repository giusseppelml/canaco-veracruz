package com.beta.giusseppe.canacoveracruz.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.adapters.NotaAdapter;
import com.beta.giusseppe.canacoveracruz.models.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaActivity extends AppCompatActivity {

    private String noticiaTitulo;
    private String noticiaDescripcion;
    private String noticiaImagen;
    private String noticiaFecha;

    private List<Nota> nota;
    private NotaAdapter notaAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);
        if(getIntent().getExtras() != null)
        {
            noticiaTitulo = getIntent().getExtras().getString("titulo");
            noticiaDescripcion = getIntent().getExtras().getString("descripcion");
            noticiaImagen = getIntent().getExtras().getString("imagen");
            noticiaFecha = getIntent().getExtras().getString("fecha");
        }

        listView = (ListView)findViewById(R.id.ListViewNota);

        //quita las fronteras del listView
        listView.setDivider(null);

        this.nota = DatosDeLaNota();

        //enlazamos con nuestro adaptador personalizado
        notaAdapter = new NotaAdapter(this, R.layout.list_nota, nota);
        listView.setAdapter(notaAdapter);
        registerForContextMenu(listView);
    }

    //Lista de datos para crear los datos a mostrar en afiliacion
    private List<Nota> DatosDeLaNota()
    {
        List<Nota> nota = new ArrayList<Nota>() {{
            add(new Nota(noticiaTitulo,noticiaDescripcion,noticiaImagen,noticiaFecha));
        }};
        return nota;
    }
}
