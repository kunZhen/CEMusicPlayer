package com.example.cemusicplayer;

public class Biblioteca {
    private Biblioteca next;
    private String nombre;
    private Integer cantidad;
    private String fecha;

    public Biblioteca (String nombre, int cantidad, String fecha){
        this.next = null;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Biblioteca getNext() {
        return next;
    }

    public void setNext(Biblioteca biblioteca) {
        this.next = biblioteca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
