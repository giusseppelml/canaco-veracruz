package com.beta.giusseppe.canacoveracruz.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.adapters.AfiliadoAdapter;
import com.beta.giusseppe.canacoveracruz.models.Afiliado;

import java.util.ArrayList;
import java.util.List;

public class BeneficiosFragment extends Fragment {

    private List<Afiliado> afiliacion;
    private AfiliadoAdapter afiliadoAdapter;
    private ListView listView;


    public BeneficiosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beneficios, container, false);
        listView = (ListView)view.findViewById(R.id.ListViewAfi);

        //quita las fronteras del listView
        listView.setDivider(null);

        this.afiliacion = DatosAfiliacion();

        //enlazamos con nuestro adaptador personalizado
        afiliadoAdapter = new AfiliadoAdapter(getActivity(), R.layout.list_afiliacion, afiliacion);
        listView.setAdapter(afiliadoAdapter);
        registerForContextMenu(listView);

        return view;
    }

    //Lista de datos para crear los datos a mostrar en afiliacion
    private List<Afiliado> DatosAfiliacion()
    {
        List<Afiliado> afiliacion = new ArrayList<Afiliado>() {{
            add(new Afiliado("GESTORÍA DE TRÁMITES EN:","IMSS, INFONAVIT, CFE, AYUNTAMIENTO, PROFECO, SAT y más",
                    R.mipmap.ic_fa_edit));
            add(new Afiliado("CONVENIOS CON PRECIOS PREFERENCIALES", "", R.mipmap.ic_fa_money));
            add(new Afiliado("BOLSA DE TRABAJO", "",R.mipmap.ic_fa_user));
            add(new Afiliado("CAPACITACIÓN AVALADA POR LA SECRETARÍA DEL TRABAJO", "",R.mipmap.ic_fa_university));
            add(new Afiliado("RENTA DE SALONES", "", R.mipmap.ic_fa_fax));
            add(new Afiliado("CARRERAS TÉCNICAS ANTE LA SEP", "", R.mipmap.ic_fa_graduation_cap));
            add(new Afiliado("ENTREGA DE APOYO A EMPRESARIOS CON EQUIPO TECNOLÓGICO", "", R.mipmap.ic_fa_laptop));
            add(new Afiliado("REGISTRO DEL SIEM", "", R.mipmap.ic_fa_file_text));
        }};
        return afiliacion;
    }
}
