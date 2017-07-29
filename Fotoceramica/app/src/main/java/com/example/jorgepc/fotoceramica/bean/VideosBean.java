package com.example.jorgepc.fotoceramica.bean;

/**
 * Created by JORGEPC on 24/03/2017.
 */

public class VideosBean {

    private String descripcion;
    private String img;

    public VideosBean(){

    }

    public VideosBean(String descripcion, String img){
                this.setDescripcion(descripcion);
                this.setImg(img);

    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
