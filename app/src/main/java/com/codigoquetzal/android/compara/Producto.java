package com.codigoquetzal.android.compara;


import java.util.UUID;

public class Producto {

    private UUID Id;

    private String nombre;

    public Producto() {
        Id = UUID.randomUUID();
    }

    public UUID getId() {
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
