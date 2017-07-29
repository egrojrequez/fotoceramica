package com.example.jorgepc.fotoceramica.firebaseinstancia;

import com.example.jorgepc.fotoceramica.bean.ProductosBean;
import com.example.jorgepc.fotoceramica.bean.TestimoniosBean;
import com.example.jorgepc.fotoceramica.bean.VideosBean;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by JORGEPC on 07/07/2017.
 */

public class FirebaseInstancia {

    public static FirebaseInstancia firebaseInstancia = new FirebaseInstancia();

    private FirebaseDatabase firebaseDatabase;
    public static DatabaseReference reference;
    public static ArrayList<ProductosBean> arregloProductos = new ArrayList<>();
    public static ArrayList<TestimoniosBean> arregloTestimonios = new ArrayList<>();
    public static ArrayList<VideosBean> arregloVideos = new ArrayList<>();
    public static String referenceName = "productos";
    public static String referenceNameVideos = "videos";

    public void Instancia(String referencia){

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference(referencia);

    }
}
