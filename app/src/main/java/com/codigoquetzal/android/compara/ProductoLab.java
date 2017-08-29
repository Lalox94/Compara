package com.codigoquetzal.android.compara;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductoLab {

    private static ProductoLab qProductoLab;

    private List<Producto> qProductos;
    /*
     *  Un objecto tipo 'Context' es un conjunto de datos,
     *  almacenados en una clase que actua como una structura
     *  con modificadores.
     */
    public static ProductoLab get(Context context) {

        if (qProductoLab == null) {
            qProductoLab = new ProductoLab(context);
        }
        return qProductoLab;
    }

    private ProductoLab(Context context) {
        qProductos = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Producto producto = new Producto();
            producto.setNombre("Producto #" + i);
            qProductos.add(producto);
        }
    }

    public List<Producto> getProductos() {
        return qProductos;
    }

    public Producto getProducto(UUID id) {

        for (Producto producto : qProductos) {
            if (producto.getId().equals(id))
                return producto;
        }
        return  null;
    }
}
