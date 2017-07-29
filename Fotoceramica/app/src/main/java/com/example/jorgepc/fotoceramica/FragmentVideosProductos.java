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

import com.example.jorgepc.fotoceramica.adaptador.VideosAdaptador;
import com.example.jorgepc.fotoceramica.bean.VideosBean;
import com.example.jorgepc.fotoceramica.firebaseinstancia.FirebaseInstancia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVideosProductos extends Fragment {

    RecyclerView recyclerVideos;
    TextView cargando;
    VideosBean videosBean;



    public FragmentVideosProductos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_videos_productos, container, false);

        recyclerVideos = (RecyclerView) view.findViewById(R.id.recyclerVideos);
        cargando = (TextView) view.findViewById(R.id.cargando);

        if(ControladorInternet.isNetworkConnected(getActivity().getApplicationContext())){

            fillData();

        }else{

            cargando.setVisibility(View.GONE);
            recyclerVideos.setVisibility(View.INVISIBLE);
            alertaInternet("No hay conexión a Internet", "Conéctate a una red ahora mismo para ver los videos de nuestros productos");
        }


        return view;
    }

    private void fillData(){

        recyclerVideos.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseInstancia.firebaseInstancia.Instancia(FirebaseInstancia.referenceNameVideos);


        FirebaseInstancia.reference.child("productos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseInstancia.arregloVideos.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    videosBean = ds.getValue(VideosBean.class);
                    FirebaseInstancia.arregloVideos.add(videosBean);

                }

                VideosAdaptador adaptador = new VideosAdaptador(getActivity(), FirebaseInstancia.arregloVideos);
                cargando.setVisibility(View.GONE);
                recyclerVideos.setVisibility(View.VISIBLE);
                recyclerVideos.setAdapter(adaptador);

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
