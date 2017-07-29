package com.example.jorgepc.fotoceramica;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jorgepc.fotoceramica.adaptador.ProductosVendidosAdaptador;
import com.example.jorgepc.fotoceramica.bean.ProductoBeanVendidos;
import com.example.jorgepc.fotoceramica.productostodos.TodosProductos;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInicio extends Fragment {

    RecyclerView recyclerVendidas;
    ArrayList<ProductoBeanVendidos> productoBeanVendidoses;

    public FragmentInicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);

        recyclerVendidas = (RecyclerView) view.findViewById(R.id.recyclerVendidas);
        recyclerVendidas.setLayoutManager(new LinearLayoutManager(getContext()));
        productoBeanVendidoses = new ArrayList<>();

        productoBeanVendidoses.clear();

        TodosProductos todos = new TodosProductos();

        productoBeanVendidoses = todos.productos();

        ProductosVendidosAdaptador adaptador = new ProductosVendidosAdaptador(getContext(), productoBeanVendidoses);
        recyclerVendidas.setAdapter(adaptador);



        return view;
    }

}
