package com.example.cemusicplayer;

/**
 * Clase Biblioteca
 *
 * Contiene información de cada biblioteca
 * Presenta un puntero para indicar la biblioteca siguiente
 */
public class Biblioteca {
    private Biblioteca next;
    private String nombre;
    private Integer cantidad;
    private String fecha;

    /**
     * Constructor - Inicializa los atributos
     */
    public Biblioteca() {
        this.next = null;
        this.nombre = " ";
        this.cantidad = 0;
        this.fecha = " ";
    }

    /**
     * Crea la biblioteca con los valores ingresados
     * @param nombre nombre de biblioteca
     * @param cantidad cantidad de canciones
     * @param fecha fecha
     */
    public Biblioteca (String nombre, int cantidad, String fecha){
        this.next = null;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    /**
     * Conseguir la siguiente biblioteca
     * @return biblioteca siguiente
     */
    public Biblioteca getNext() {
        return next;
    }

    /**
     * Actualiza la siguiente biblioteca
     * @param biblioteca nueva biblioteca siguiente
     */
    public void setNext(Biblioteca biblioteca) {
        this.next = biblioteca;
    }

    /**
     * Consigue el nombre de la biblioteca
     * @return nombre biblioteca
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre de la biblioteca
     * @param nombre nombre nueva biblioteca
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Consigue la cantiad de canciones que tiene la biblioteca
     * @return cantidad de canciones
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Actualiza la cantidad de canciones de la biblioteca
     * @param cantidad cantidad de canciones
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Consigue la fecha de creación de la biblioteca
     * @return fecha de creación
     */
    public String getFecha() {
        return fecha;
    }

}
