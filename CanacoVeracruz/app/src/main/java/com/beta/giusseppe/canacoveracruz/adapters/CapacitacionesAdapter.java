package com.beta.giusseppe.canacoveracruz.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class CapacitacionesAdapter extends RecyclerView.Adapter<CapacitacionesAdapter.ViewHolder> {
    private List<Contenido> conten;
    private int layout;
    private OnItemClickListener itemClickListener;
    private OnButtonClickListener btnListener;

    private Context context;

    public CapacitacionesAdapter(List<Contenido> conten, int layout, OnItemClickListener listener, OnButtonClickListener btnListener)
    {
        this.conten = conten;
        this.layout = layout;
        this.itemClickListener = listener;
        this.btnListener = btnListener;
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
        holder.bind(conten.get(position), itemClickListener, btnListener);
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
        public Button btnSolicitar;

        public ViewHolder(View v)
        {
            super(v);
            TextViewTitulo = (TextView)itemView.findViewById(R.id.cardViewCapacitacionesTitulo);
            TextViewDescripcion = (TextView)itemView.findViewById(R.id.cardViewCapacitacionesDescripcion);
            TextViewVerMas = (TextView)itemView.findViewById(R.id.cardViewCapacitacionesVerMas);
            ImageViewPortada = (ImageView)itemView.findViewById(R.id.cardViewCapacitacionesImagen);
            btnSolicitar = (Button)itemView.findViewById(R.id.buttonSolicitarCurso);
        }

        public void bind(final Contenido conten, final OnItemClickListener listener, final OnButtonClickListener btnClikListener) {
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

            btnSolicitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnListener.onButtonClick(conten, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Contenido conten, int position);
    }

    public interface OnButtonClickListener
    {
        void onButtonClick(Contenido conten, int position);
    }
}
