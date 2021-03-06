package com.millennialapps.musicum.lists;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.millennialapps.musicum.R;
import com.millennialapps.musicum.common.ModificarVistas;
import com.millennialapps.musicum.common.Navegar;
import com.millennialapps.musicum.common.ObtenerCursores;
import com.millennialapps.musicum.common.ObtenerDatos;
import com.millennialapps.musicum.common.objects.Constantes;
import com.millennialapps.musicum.lists.adaptadores.AdaptadorGeneros;

/**
 * Created by ErickSteven on 27/06/2015.
 */
public class Generos extends Fragment {

    private Cursor cursor;
    private RecyclerView rclrVwLista;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar cargando;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_generos, container, false);

        cargando = (ProgressBar) rootView.findViewById(R.id.loading_spinner);
        rclrVwLista = (RecyclerView) rootView.findViewById(R.id.listaArtistas);

        cursor = ObtenerCursores.listaGeneros(getActivity(), Constantes.NO_RANDOM_GENEROS);
        mLayoutManager = new LinearLayoutManager(getActivity());

        rclrVwLista.setVisibility(View.GONE);
        rclrVwLista.setHasFixedSize(true);
        rclrVwLista.setLayoutManager(mLayoutManager);
        adaptador = new AdaptadorGeneros(cursor, getActivity());
        rclrVwLista.setAdapter(adaptador);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ModificarVistas.sumarMargenes(rclrVwLista, 0, 0, 0, ObtenerDatos.alturaNavigationBar(getActivity()));
            ModificarVistas.sumarMargenes(rclrVwLista, 0, ObtenerDatos.alturaStatusBar(getActivity()), 0, 0);
        }

        Navegar.mostrarVista(getActivity(), rclrVwLista, cargando);
        return rootView;
    }

}
