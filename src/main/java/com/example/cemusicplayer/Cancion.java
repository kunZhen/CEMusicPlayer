package com.example.cemusicplayer;
public class Cancion {
    private Cancion next;
    private Cancion prev;
    private String nombre;
    private boolean favorite;
    private String path;
    public Cancion() {
        this.next = null;
        this.prev = null;
        this.nombre = null;
        this.favorite = false; //ninguna cancion es favorita al comienzo
        this.path = null;
    }
    public Cancion(String nombre, boolean favorite, String path) {
        this.next = next;
        this.prev = prev;
        this.nombre = nombre;
        this.favorite = favorite;
        this.path = path;
    }

    public Cancion getNext() {
        return next;
    }

    public void setNext(Cancion next) {
        this.next = next;
    }

    public Cancion getPrev() {
        return prev;
    }

    public void setPrev(Cancion prev) {
        this.prev = prev;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}