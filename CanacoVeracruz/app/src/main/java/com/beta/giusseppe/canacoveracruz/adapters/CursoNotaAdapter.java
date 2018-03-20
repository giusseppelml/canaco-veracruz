package com.beta.giusseppe.canacoveracruz.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.api.Api;
import com.beta.giusseppe.canacoveracruz.models.Nota;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CursoNotaAdapter extends RecyclerView.Adapter<CursoNotaAdapter.ViewHolder> {

    private List<Nota> nota;
    private int layout;

    private Context context;

    public CursoNotaAdapter(List<Nota> nota, int layout)
    {
        this.nota = nota;
        this.layout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(nota.get(position));
    }

    @Override
    public int getItemCount() {
        return nota.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView TextViewTitulo;
        public TextView TextViewDescripcion;
        public TextView TextViewFecha;
        public ImageView ImageViewPortada;

        public ViewHolder(View v)
        {
            super(v);
            TextViewTitulo = (TextView)itemView.findViewById(R.id.textCursoTitulo);
            TextViewDescripcion = (TextView)itemView.findViewById(R.id.textCursoDescripcion);
            TextViewFecha = (TextView)itemView.findViewById(R.id.textCursoFecha);
            ImageViewPortada = (ImageView)itemView.findViewById(R.id.cursoViewImagen);
        }

        public void bind(final Nota nota) {
            //procesamos los datos a renderizar

            TextViewTitulo.setText(nota.getTitulo());
            TextViewDescripcion.setText(nota.getDescripcion());
            TextViewFecha.setText(nota.getFecha());
            Picasso.with(context).load(Api.URL_GALERIA+nota.getImagen()).fit().into(ImageViewPortada);
        }
    }
}
