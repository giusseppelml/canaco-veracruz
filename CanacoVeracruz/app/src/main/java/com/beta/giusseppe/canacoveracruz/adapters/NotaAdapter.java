package com.beta.giusseppe.canacoveracruz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.api.Api;
import com.beta.giusseppe.canacoveracruz.models.Nota;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Giusseppe on 13/10/2017.
 */

public class NotaAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Nota> nota;

    public NotaAdapter(Context context, int layout, List<Nota> nota)
    {
        this.context = context;
        this.layout = layout;
        this.nota = nota;
    }


    @Override
    public int getCount() {
        return this.nota.size();
    }

    @Override
    public Object getItem(int position) {
        return this.nota.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            holder = new ViewHolder();
            holder.titulo = (TextView)convertView.findViewById(R.id.textNotaTitulo);
            holder.descripcion = (TextView)convertView.findViewById(R.id.textNotadescripcion);
            holder.imagen = (ImageView)convertView.findViewById(R.id.notaViewImagen);
            holder.fecha = (TextView)convertView.findViewById(R.id.textNotaFecha);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        final Nota nota = (Nota) getItem(position);
        holder.titulo.setText(nota.getTitulo());
        holder.descripcion.setText(nota.getDescripcion());
        holder.fecha.setText(nota.getFecha());
        Picasso.with(context).load(Api.URL_GALERIA+nota.getImagen()).fit().into(holder.imagen);

        return convertView;
    }
    static class ViewHolder
    {
        private TextView titulo;
        private TextView descripcion;
        private ImageView imagen;
        private TextView fecha;
    }
}
