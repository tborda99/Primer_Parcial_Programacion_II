package Entities;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

import java.util.Objects;

public class Cliente {
    private String cedula;
    private long numero;
    private String nombre;
    private MyList<Compra> compras = new MyLinkedListImpl<Compra>();

    public Cliente(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public Cliente() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MyList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(MyList<Compra> compras) {
        this.compras = compras;
    }

    //Setteo cedula como el identificador de cliente unico.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cedula.equals(cliente.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
}
