package com.beta.giusseppe.canacoveracruz.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.adapters.InfoSiemAdapter;
import com.beta.giusseppe.canacoveracruz.models.InfoSiem;

import java.util.ArrayList;
import java.util.List;

public class SiemFragment extends Fragment {

    private List<InfoSiem> infosiem;
    private InfoSiemAdapter infoSiemAdapter;
    private ListView listView;

    public SiemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_siem, container, false);

        listView = (ListView)view.findViewById(R.id.ListViewInfoSiem);

        //quita las fronteras del listView
        listView.setDivider(null);

        this.infosiem = DatosInfoSiem();

        //enlazamos con nuestro adaptador personalizado
        infoSiemAdapter = new InfoSiemAdapter(getActivity(), R.layout.list_infosiem, infosiem);
        listView.setAdapter(infoSiemAdapter);
        registerForContextMenu(listView);

        return view;
    }

    //Lista de datos para crear los datos a mostrar en afiliacion
    private List<InfoSiem> DatosInfoSiem()
    {
        List<InfoSiem> afiliacion = new ArrayList<InfoSiem>() {{
            add(new InfoSiem("","El SIEM integra un padrón de empresas, a nivel nacional, para promover la vinculación de negocios entre empresarios mexicanos y extranjeros.\n"
                    + "\nLa Secretaría de Economía es la encargada de la aplicación y cumplimiento de la Ley de Cámaras Empresariales y sus Confederaciones, en tanto que las Confederaciones y Cámaras Empresariales, son las responsables del registro y operación del SIEM.\n"
                    + "\n•\tEl 20 de enero del 2005 fue publicada la Ley de Cámaras Empresariales y sus confederaciones (LCEC), la que tiene por objeto normar el funcionamiento del Sistema de Información Empresarial Mexicano (SIEM).\n"
                    + "\n“ARTICULO 30, TODOS LOS COMERCIANTES E INDUSTRIALES, SIN EXCEPCION Y OBLIGATORIAMENTE, DEBERAN DE REGISTRAR Y ACTUALIZAR ANUALMENTE CADA UNO DE SUS ESTABLECIMIENTOS EN EL SIEM.”"));
            add(new InfoSiem("Principales Beneficios del SIEM:", "•\tAcceder, vía el portal del SIEM (www.siem.gob.mx) a más de 700 mil oportunidades de negocios.\n"
            + "•\tGuías Empresariales para iniciar o mejorar tu negocio.\n"
            + "•\tContactar a clientes y Proveedores.\n"
            + "•\tEncontrar herramientas para el desarrollo de los negocios.\n"
            + "•\tPublicar fotografías, logotipo, ofertas de productos y servicios de su negocio.\n"
            + "•\tConsultar empresas registradas por estado, municipio, sector, giro, razón social y/o comercial, productos que una empresa ofrece o demanda y estadísticas que facilitan el acceso a la información.\n"
            + "•\tAsesoría, fiscal y contable.\n"
            + "•\tConocer programas de apoyo de la Secretaría de Economía."));
            add(new InfoSiem("COSTOS:", "Hasta Una Persona $100.00 (Ejemplo Abarrotes) \n" +
                    "Tres personas o Menos $300.00 \n" +
                    "Cuatro personas o más $640.00"));
        }};
        return afiliacion;
    }
}
