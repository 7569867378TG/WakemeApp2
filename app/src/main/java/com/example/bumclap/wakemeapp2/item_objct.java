package com.example.bumclap.wakemeapp2;

/**
 * Created by Bumclap on 18/2/15.
 */
public class item_objct {
    private String titulo;
    private int icono;

    public item_objct(String title, int icon){
        this.titulo =title;
        this.icono=icon;
    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }

    public int getIcono(){
        return icono;
    }
    public void setIcono(int icono){
        this.icono=icono;
    }

}
