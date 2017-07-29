package com.example.jorgepc.fotoceramica;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jorgepc.fotoceramica.adaptador.ProductoAdaptador;
import com.example.jorgepc.fotoceramica.bean.ProductosBean;
import com.example.jorgepc.fotoceramica.firebaseinstancia.FirebaseInstancia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTazas extends Fragment {

    RecyclerView recyclerTazas;
    TextView cargando;
    ProductosBean productosBean;


    public FragmentTazas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tazas, container, false);

            recyclerTazas = (RecyclerView) view.findViewById(R.id.recyclerTazas);
            cargando = (TextView) view.findViewById(R.id.cargando);


        if(ControladorInternet.isNetworkConnected(getActivity().getApplicationContext())){

            fillData();

        }else{

            cargando.setVisibility(View.GONE);
            recyclerTazas.setVisibility(View.INVISIBLE);
            alertaInternet("No hay conexión a Internet", "Conéctate a una red ahora mismo para ver nuestros productos");

        }

        return view;
    }

    private void alertaInternet(String titulo, String mensaje){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void fillData(){

        FirebaseInstancia.firebaseInstancia.Instancia(FirebaseInstancia.referenceName);

        recyclerTazas.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseInstancia.reference.child("tazas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseInstancia.arregloProductos.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    productosBean = ds.getValue(ProductosBean.class);
                    FirebaseInstancia.arregloProductos.add(productosBean);

                }

                ProductoAdaptador adaptador = new ProductoAdaptador(getContext(), FirebaseInstancia.arregloProductos);
                cargando.setVisibility(View.GONE);
                recyclerTazas.setVisibility(View.VISIBLE);
                recyclerTazas.setAdapter(adaptador);
                adaptador.notifyDataSetChanged();

               }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                alertaInternet("Error", "Ha ocurrido un error al tratar de obtener nuestros productos, por favor inténtalo más tarde.");

            }
        });

    }

}
