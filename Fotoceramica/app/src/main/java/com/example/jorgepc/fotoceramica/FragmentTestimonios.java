package com.example.jorgepc.fotoceramica;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jorgepc.fotoceramica.adaptador.TestimoniosAdaptador;
import com.example.jorgepc.fotoceramica.bean.TestimoniosBean;
import com.example.jorgepc.fotoceramica.firebaseinstancia.FirebaseInstancia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTestimonios extends Fragment {

    RecyclerView recyclerTestimonios;
    TextView cargando;
    TestimoniosBean testimoniosBean;


    public FragmentTestimonios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_testimonios, container, false);

        recyclerTestimonios = (RecyclerView) view.findViewById(R.id.recyclerTestimonios);
        cargando = (TextView) view.findViewById(R.id.cargando);

        if(ControladorInternet.isNetworkConnected(getActivity().getApplicationContext())){

            fillData();

        }else{

            cargando.setVisibility(View.GONE);
            recyclerTestimonios.setVisibility(View.INVISIBLE);
            alertaInternet("No hay conexión a Internet", "Conéctate a una red ahora mismo para ver los testimonios acerca de nuestros productos.");
        }

        return view;
    }

    private void fillData(){

        recyclerTestimonios.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseInstancia.firebaseInstancia.Instancia(FirebaseInstancia.referenceNameVideos);


        FirebaseInstancia.reference.child("testimonios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseInstancia.arregloTestimonios.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    testimoniosBean = ds.getValue(TestimoniosBean.class);
                    FirebaseInstancia.arregloTestimonios.add(testimoniosBean);

                }

                TestimoniosAdaptador adaptador = new TestimoniosAdaptador(getActivity(), FirebaseInstancia.arregloTestimonios);
                cargando.setVisibility(View.GONE);
                recyclerTestimonios.setVisibility(View.VISIBLE);
                recyclerTestimonios.setAdapter(adaptador);

                adaptador.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                alertaInternet("Error", "Ha ocurrido un error al tratar de obtener nuestros productos, por favor inténtalo más tarde.");
            }
        });

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



}
