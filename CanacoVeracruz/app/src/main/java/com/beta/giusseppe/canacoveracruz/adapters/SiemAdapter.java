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
import com.beta.giusseppe.canacoveracruz.models.Siem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Giusseppe on 21/10/2017.
 */

public class SiemAdapter extends RecyclerView.Adapter<SiemAdapter.ViewHolder> {
    private List<Siem> siem;
    private int layout;

    private Context context;

    public SiemAdapter(List<Siem> siem, int layout)
    {
        this.siem = siem;
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
        holder.bind(siem.get(position));
    }

    @Override
    public int getItemCount() {
        return siem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView TextViewNombre;
        public TextView TextViewTelefono;
        public TextView TextViewDireccion;
        public ImageView ImageViewFoto;

        public ViewHolder(View v)
        {
            super(v);
            TextViewNombre = (TextView)itemView.findViewById(R.id.textViewSiemNombre);
            TextViewTelefono = (TextView)itemView.findViewById(R.id.textViewSiemTelefono);
            TextViewDireccion = (TextView)itemView.findViewById(R.id.textViewSiemDireccion);
            ImageViewFoto = (ImageView)itemView.findViewById(R.id.imageViewFotoSiem);
        }

        public void bind(final Siem siem) {
            //procesamos los datos a renderizar

            TextViewNombre.setText(siem.getNombre());
            TextViewTelefono.setText(siem.getTelefono());
            TextViewDireccion.setText(siem.getDireccion());
            Picasso.with(context).load(Api.URL_GALERIA+siem.getImagen()).fit().into(ImageViewFoto);
        }
    }
}
