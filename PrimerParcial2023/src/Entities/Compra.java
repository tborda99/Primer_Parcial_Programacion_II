package Entities;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

import java.util.Objects;

public class Compra {
    private long numeroCompra;
    private MyList<Producto> productos = new MyLinkedListImpl<Producto>();

    public Compra(long numeroCompra, MyList<Producto> productos) {
        this.numeroCompra = numeroCompra;
        this.productos = productos;
    }

    public Compra() {
    }

    public long getNumeroCompra() {
        return numeroCompra;
    }

    public void setNumeroCompra(long numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    public MyList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(MyList<Producto> productos) {
        this.productos = productos;
    }

    //Un compra es igual a otra solo si el numero es igual.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return numeroCompra == compra.numeroCompra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCompra);
    }
}
