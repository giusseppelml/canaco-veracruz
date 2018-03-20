package com.beta.giusseppe.canacoveracruz.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.adapters.MenuAdapter;
import com.beta.giusseppe.canacoveracruz.models.MenuPrincipal;

import java.util.ArrayList;
import java.util.List;

public class PrincipalFragment extends Fragment {

    private List<MenuPrincipal> menu;
    private GridView gridView;
    private MenuAdapter menuAdapter;
    private Fragment nuevoFragmento;

    private String titulo;

    public PrincipalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        gridView = (GridView)view.findViewById(R.id.gridView);

        //datos a mostrar
        this.menu = DatosMenu();


        //evento para dar click en cada objeto del list view
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        //nos lleva al Fragment Afiliacion
                        // Crea el nuevo fragmento y la transacción.
                        nuevoFragmento = new AfiliacionFragment();
                        titulo = "Afiliación";
                        fragmentsOn(nuevoFragmento, titulo);
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setElevation(0);
                        break;
                    case 1:
                        //nos lleva al fragment Siem
                        // Crea el nuevo fragmento y la transacción.
                        nuevoFragmento = new ContenidoSiemFragment();
                        titulo = "Siem";
                        fragmentsOn(nuevoFragmento, titulo);
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setElevation(0);
                        break;
                    case 2:
                        //nos lleva al activity Directorio
                        // Crea el nuevo fragmento y la transacción.
                        nuevoFragmento = new DirectorioFragment();
                        titulo = "Directorio de Afiliados";
                        fragmentsOn(nuevoFragmento, titulo);
                        break;
                    case 3:
                        //nos lleva al fragment covenios
                        // Crea el nuevo fragmento y la transacción.
                        nuevoFragmento = new ConveniosFragment();
                        titulo = "Convenios";
                        fragmentsOn(nuevoFragmento, titulo);
                        break;
                    case 4:
                        //nos lleva al fragment bolsa de trabajo
                        // Crea el nuevo fragmento y la transacción.
                        nuevoFragmento = new BolsaDeTrabajoFragment();
                        titulo = "Bolsa de Trabajo";
                        fragmentsOn(nuevoFragmento, titulo);
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setElevation(0);
                        break;
                    case 5:
                        //nos lleva al fragment turismo
                        // Crea el nuevo fragmento y la transacción.
                        nuevoFragmento = new TurismoFragment();
                        titulo = "Turismo";
                        fragmentsOn(nuevoFragmento, titulo);
                        break;
                    case 6:
                        //nos lleva al fragment contenido
                        nuevoFragmento = new ContenidoFragment();
                        titulo = "Noticias Y Eventos";
                        fragmentsOn(nuevoFragmento, titulo);
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setElevation(0);
                        break;
                    case 7:
                        //nos lleva al fragment cursos
                        // Crea el nuevo fragmento y la transacción.
                        nuevoFragmento = new CapacitacionesFragment();
                        titulo = "Cursos";
                        fragmentsOn(nuevoFragmento, titulo);
                        break;
                    case 8:
                        //nos lleva al activity Contactanos
                        // Crea el nuevo fragmento y la transacción.
                        nuevoFragmento = new ContactanosFragment();
                        titulo = "Contáctanos";
                        fragmentsOn(nuevoFragmento, titulo);
                        break;
                    default:
                        return;
                }
            }
        });

        //enlazamos con nuestro adaptador personalizado
        menuAdapter = new MenuAdapter(getActivity(), R.layout.grid_menu, menu);
        gridView.setAdapter(menuAdapter);
        registerForContextMenu(gridView);

        return view;
    }

    //Lista de datos para crear el menu
    private List<MenuPrincipal> DatosMenu()
    {
        List<MenuPrincipal> menu = new ArrayList<MenuPrincipal>() {{

            add(new MenuPrincipal("AFILIACIÓN", R.mipmap.ic_afiliacion_icons));
            add(new MenuPrincipal("SIEM", R.mipmap.ic_pc));
            add(new MenuPrincipal("DIRECTORIO DE AFILIADOS", R.mipmap.ic_contactanos_icon));
            add(new MenuPrincipal("CONVENIOS", R.mipmap.ic_manos_icons));
            add(new MenuPrincipal("BOLSA DE TRABAJO", R.mipmap.ic_bolsa));
            add(new MenuPrincipal("TURISMO", R.mipmap.ic_sombrilla));
            add(new MenuPrincipal("NOTICIAS Y EVENTOS", R.mipmap.ic_noticias_icons));
            add(new MenuPrincipal("CURSOS", R.mipmap.ic_aula));
            add(new MenuPrincipal("CONTÁCTANOS", R.mipmap.ic_contacta_icons));
        }};
        return menu;
    }

    private void fragmentsOn(Fragment fragment, String titles)
    {
        // Crea el nuevo fragmento y la transacción.
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(titles);
        transaction.addToBackStack(null);

        // Commit a la transacción
        transaction.commit();
    }

    private boolean isNetDisponible() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                ((AppCompatActivity)getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }
}
