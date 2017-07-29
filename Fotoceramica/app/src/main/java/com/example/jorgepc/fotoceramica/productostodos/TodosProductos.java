package com.example.jorgepc.fotoceramica.productostodos;

import com.example.jorgepc.fotoceramica.bean.ProductoBeanVendidos;

import java.util.ArrayList;

/**
 * Created by JORGEPC on 22/03/2017.
 */

public class TodosProductos {

    public ArrayList<ProductoBeanVendidos> productos(){


        ArrayList<ProductoBeanVendidos> productoArreglo = new ArrayList<ProductoBeanVendidos>();

        ProductoBeanVendidos productobean;

        productobean = new ProductoBeanVendidos();

        productobean.setTipo("Taza");
        productobean.setDescr("Taza de 'The Beatles'.");
        productobean.setImg("tazanormal");
        productobean.setImgalert("tazaalert");
        productobean.setPrecio("50 Soles");

        productoArreglo.add(productobean);


        ////////////////////////////////////////////////////////


        productobean = new ProductoBeanVendidos();

        productobean.setTipo("Lápidas");
        productobean.setDescr("Lápida con virgen, paloma y recuerdo.");
        productobean.setImg("lapida1");
        productobean.setImgalert("lapidaalert");
        productobean.setPrecio("130 Soles");

        productoArreglo.add(productobean);

        ////////////////////////////////////////////////////////


        productobean = new ProductoBeanVendidos();

        productobean.setTipo("Promoción");
        productobean.setDescr("Promoción de colegio parroquial.");
        productobean.setImg("promocion");
        productobean.setImgalert("promoalert");
        productobean.setPrecio("120 Soles");

        productoArreglo.add(productobean);


        return productoArreglo;

    }

}
