package com.example.jorgepc.fotoceramica.bean;

/**
 * Created by JORGEPC on 23/03/2017.
 */

public class ProductosBean {

    private String descripcion;
    private String imgalerta;
    private String imgnormal;
    private String precio;


    public ProductosBean(){


    }

    public ProductosBean(String descripcion, String imgalerta, String imgnormal, String precio){

                this.setDescripcion(descripcion);
                this.setImgalerta(imgalerta);
                this.setImgnormal(imgnormal);
                this.setPrecio(precio);

    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImgalerta() {
        return imgalerta;
    }

    public void setImgalerta(String imgalerta) {
        this.imgalerta = imgalerta;
    }

    public String getImgnormal() {
        return imgnormal;
    }

    public void setImgnormal(String imgnormal) {
        this.imgnormal = imgnormal;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
