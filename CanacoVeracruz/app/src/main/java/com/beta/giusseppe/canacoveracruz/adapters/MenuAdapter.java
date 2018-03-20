package com.beta.giusseppe.canacoveracruz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.models.MenuPrincipal;

import java.util.List;

/**
 * Created by Giusseppe on 12/10/2017.
 */

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MenuPrincipal> menu;

    public MenuAdapter(Context context, int layout, List<MenuPrincipal> menu)
    {
        this.context = context;
        this.layout = layout;
        this.menu = menu;
    }


    @Override
    public int getCount() {
        return this.menu.size();
    }

    @Override
    public Object getItem(int position) {
        return this.menu.get(position);
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
            holder.name = (TextView)convertView.findViewById(R.id.textViewPrincipal);
            holder.icon = (ImageView)convertView.findViewById(R.id.imageViewPrincipal);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        final MenuPrincipal currentFruta = (MenuPrincipal) getItem(position);
        holder.name.setText(currentFruta.getNombre());
        holder.icon.setImageResource(currentFruta.getIcono());

        return convertView;
    }
    static class ViewHolder
    {
        private TextView name;
        private ImageView icon;
    }
}
