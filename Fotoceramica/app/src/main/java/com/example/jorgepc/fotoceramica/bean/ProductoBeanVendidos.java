package com.example.jorgepc.fotoceramica.bean;



/**
 * Created by JORGEPC on 22/03/2017.
 */

public class ProductoBeanVendidos {

    private String tipo;
    private String descr;
    private String img;
    private String imgalert;
    private String precio;


    public ProductoBeanVendidos(){


    }

    public ProductoBeanVendidos(String tipo, String descr, String img, String imgalert, String precio){

                this.setTipo(tipo);
                this.setDescr(descr);
                this.setImg(img);
                this.setImgalert(imgalert);
                this.setPrecio(precio);

    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgalert() {
        return imgalert;
    }

    public void setImgalert(String imgalert) {
        this.imgalert = imgalert;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
