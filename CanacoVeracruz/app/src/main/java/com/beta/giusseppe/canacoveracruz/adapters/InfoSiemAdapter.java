package com.beta.giusseppe.canacoveracruz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.models.InfoSiem;

import java.util.List;

/**
 * Created by Giusseppe on 30/10/2017.
 */

public class InfoSiemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<InfoSiem> infosiem;

    public InfoSiemAdapter(Context context, int layout, List<InfoSiem> infosiem)
    {
        this.context = context;
        this.layout = layout;
        this.infosiem = infosiem;
    }


    @Override
    public int getCount() {
        return this.infosiem.size();
    }

    @Override
    public Object getItem(int position) {
        return this.infosiem.get(position);
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
            holder.titulo = (TextView)convertView.findViewById(R.id.textViewInfoSiemEncabezado);
            holder.descripcion = (TextView)convertView.findViewById(R.id.textViewInfoSiemDescripcion);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        final InfoSiem currentInfoSiem = (InfoSiem) getItem(position);
        holder.titulo.setText(currentInfoSiem.getEncabezado());
        holder.descripcion.setText(currentInfoSiem.getInformacion());

        return convertView;
    }
    static class ViewHolder
    {
        private TextView titulo;
        private TextView descripcion;
    }
}
