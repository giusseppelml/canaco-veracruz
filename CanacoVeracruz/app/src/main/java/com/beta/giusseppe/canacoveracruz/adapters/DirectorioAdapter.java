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
import com.beta.giusseppe.canacoveracruz.models.Directorio;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Giusseppe on 13/10/2017.
 */

public class DirectorioAdapter extends RecyclerView.Adapter<DirectorioAdapter.ViewHolder> {

    private List<Directorio> direcList;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

    public DirectorioAdapter(List<Directorio> direcList, int layout, OnItemClickListener listener)
    {
        this.direcList = direcList;
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
        holder.bind(direcList.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return direcList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewDirecCategoria;
        private ImageView ImageViewDirecIcono;

        public ViewHolder(View v)
        {
            super(v);
            textViewDirecCategoria = (TextView) itemView.findViewById(R.id.textViewCategoriaDirectorio);
            ImageViewDirecIcono = (ImageView) itemView.findViewById(R.id.ImageViewIconoDirectorio);
        }

        public void bind(final Directorio direcList, final OnItemClickListener listener) {
            //procesamos los datos a renderizar

            textViewDirecCategoria.setText(direcList.getCategoria());
            Picasso.with(context).load(Api.URL_GALERIA+direcList.getImagen()).fit().into(ImageViewDirecIcono);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ... pasamos nuestro objeto modelo (este caso String) y posición
                    listener.onItemClick(direcList, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Directorio direcList, int position);
    }

}
