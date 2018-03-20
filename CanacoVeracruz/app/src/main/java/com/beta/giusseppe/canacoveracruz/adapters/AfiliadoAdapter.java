package com.beta.giusseppe.canacoveracruz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.models.Afiliado;

import java.util.List;

/**
 * Created by Giusseppe on 12/10/2017.
 */

public class AfiliadoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Afiliado> afiliacion;

    public AfiliadoAdapter(Context context, int layout, List<Afiliado> afiliacion)
    {
        this.context = context;
        this.layout = layout;
        this.afiliacion = afiliacion;
    }


    @Override
    public int getCount() {
        return this.afiliacion.size();
    }

    @Override
    public Object getItem(int position) {
        return this.afiliacion.get(position);
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
            holder.titulo = (TextView)convertView.findViewById(R.id.textViewAfi);
            holder.descripcion = (TextView)convertView.findViewById(R.id.textViewAfi2);
            holder.icon = (ImageView)convertView.findViewById(R.id.imageViewAfi);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        final Afiliado currentAfiliado = (Afiliado) getItem(position);
        holder.titulo.setText(currentAfiliado.getTitulo());
        holder.descripcion.setText(currentAfiliado.getDescripcion());
        holder.icon.setImageResource(currentAfiliado.getIcono());

        return convertView;
    }
    static class ViewHolder
    {
        private TextView titulo;
        private TextView descripcion;
        private ImageView icon;
    }
}
