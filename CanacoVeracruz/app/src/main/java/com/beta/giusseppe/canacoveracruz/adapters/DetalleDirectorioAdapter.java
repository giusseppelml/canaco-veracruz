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
import com.beta.giusseppe.canacoveracruz.models.DetalleDirectorio;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Giusseppe on 13/10/2017.
 */

public class DetalleDirectorioAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DetalleDirectorio> detalle;

    public DetalleDirectorioAdapter(Context context, int layout, List<DetalleDirectorio> detalle)
    {
        this.context = context;
        this.layout = layout;
        this.detalle = detalle;
    }

    @Override
    public int getCount() {
        return this.detalle.size();
    }

    @Override
    public Object getItem(int position) {
        return this.detalle.get(position);
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
            holder.nombre = (TextView)convertView.findViewById(R.id.textViewDetalleNombre);
            holder.direccion = (TextView)convertView.findViewById(R.id.textViewDetalleDireccion);
            holder.imagen = (ImageView)convertView.findViewById(R.id.ImageViewDetalle);
            holder.telefono = (TextView)convertView.findViewById(R.id.textViewDetalleTelefono);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        final DetalleDirectorio direcDetl = (DetalleDirectorio) getItem(position);
        holder.nombre.setText(direcDetl.getNobre());
        holder.direccion.setText(direcDetl.getDireccion());
        holder.telefono.setText(direcDetl.getTelefono());
        Picasso.with(context).load(Api.URL_GALERIA+direcDetl.getImagen()).fit().into(holder.imagen);

        return convertView;
    }
    static class ViewHolder
    {
        private TextView nombre;
        private TextView direccion;
        private ImageView imagen;
        private TextView telefono;
    }
}
