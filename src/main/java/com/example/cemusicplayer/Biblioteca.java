package com.example.cemusicplayer;

import java.time.LocalDate;

public class Biblioteca {
    private Biblioteca next;
    private String nombre;
    private int cantCanciones;
    private String fechaCreacion;

    public Biblioteca(String nombreBiblioteca, LocalDate fechaActual) { //inicializa una biblioteca vacia
        this.next = null;
        this.nombre = " ";
        this.cantCanciones = 0;
        this.fechaCreacion = " ";
    }

    public Biblioteca(String nombre, String fechaCreacion) {
        this.next = null;
        this.nombre = nombre;
        this.cantCanciones = 0;
        this.fechaCreacion = fechaCreacion;
    }

    public Biblioteca getNext() {
        return next;
    }

    public void setNext(Biblioteca next) {
        this.next = next;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantCanciones() {
        return cantCanciones;
    }

    public void setCantCanciones(int cantCanciones) {
        this.cantCanciones = cantCanciones;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
