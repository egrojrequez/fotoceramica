package com.example.jorgepc.fotoceramica.bean;

/**
 * Created by JORGEPC on 24/03/2017.
 */

public class TestimoniosBean {


        private String cliente;
        private String img;


    public TestimoniosBean(){

    }


    public TestimoniosBean(String cliente, String img){

                this.setCliente(cliente);
                this.setImg(img);

    }


    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
