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
import com.beta.giusseppe.canacoveracruz.models.Contenido;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Giusseppe on 12/10/2017.
 */

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder>{
    private List<Contenido> conten;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

    public NoticiasAdapter(List<Contenido> conten, int layout, OnItemClickListener listener)
    {
        this.conten = conten;
        this.layout = layout;
        this.itemClickListener = listener;
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
        holder.bind(conten.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return conten.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView TextViewTitulo;
        public TextView TextViewDescripcion;
        public TextView TextViewVerMas;
        public ImageView ImageViewPortada;

        public ViewHolder(View v)
        {
            super(v);
            TextViewTitulo = (TextView)itemView.findViewById(R.id.cardViewTitulo);
            TextViewDescripcion = (TextView)itemView.findViewById(R.id.cardViewDescripcion);
            TextViewVerMas = (TextView)itemView.findViewById(R.id.cardViewVerMas);
            ImageViewPortada = (ImageView)itemView.findViewById(R.id.cardViewImagen);
        }

        public void bind(final Contenido conten, final OnItemClickListener listener) {
            //procesamos los datos a renderizar

            TextViewTitulo.setText(conten.getTitulo());
            TextViewDescripcion.setText(conten.getDescripcion());
            TextViewVerMas.setText(conten.getFecha());
            Picasso.with(context).load(Api.URL_GALERIA+conten.getImagen()).fit().into(ImageViewPortada);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ... pasamos nuestro objeto modelo (este caso String) y posición
                    listener.onItemClick(conten, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Contenido conten, int position);
    }
}
