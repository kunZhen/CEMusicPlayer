package com.example.cemusicplayer;

import java.io.File;

/**
 * Clase Canción
 *
 * Contienen información de cada canción
 * Presenta punteros para indicar el siguiente o el anterior
 */
public class Cancion {
    private Cancion next;
    private Cancion prev;
    private String nombre;
    private File selected;
    private boolean favorite;
    private String path;

    /**
     * Constructor - Inicializa los atributos
     */
    public Cancion() {
        this.next = null;
        this.prev = null;
        this.nombre = null;
        this.favorite = false; //ninguna cancion es favorita al comienzo
        this.path = null;
    }

    /**
     * Crea una canción con los atributos ingresados
     * @param nombre nombre
     * @param favorite favorito
     * @param path path
     * @param selected seleccionado
     */
    public Cancion(String nombre, boolean favorite, String path, File selected) {
        this.nombre = nombre;
        this.favorite = favorite;
        this.path = path;
        this.selected = selected;
    }

    /**
     * Consigue la canción siguiente
     * @return canción siguiente
     */
    public Cancion getNext() {
        return next;
    }

    /**
     * Actualiza la siguiente canción
     * @param next canción
     */
    public void setNext(Cancion next) {
        this.next = next;
    }

    /**
     * Consigue la canción anterior
     * @return canción anterior
     */
    public Cancion getPrev() {
        return prev;
    }

    /**
     * Actualiza la canción anterior
     * @param prev canción
     */
    public void setPrev(Cancion prev) {
        this.prev = prev;
    }

    /**
     * Consigue el nombre de la canción
     * @return nombre de canción
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre de la canción
     * @param nombre nombre de canción
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Idnica si la canción es favorita
     * @return true o false
     */

    public boolean isFavorite() {
        return favorite;
    }

    /**
     * Actualiza si la canción es favorita o no
     * @param favorite true o false
     */
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * Consigue la dirección de la canción
     * @return dirección de la canción
     */
    public String getPath() {
        return path;
    }

    /**
     * Actualiza la dirección de la canción
     * @param path dirección de la canción
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Consigue el archivo mp3
     * @return archivo mp3
     */
    public File getFile() {
        return selected;
    }

    /**
     * Actualiza el archivo mp3
     * @param selected achivo mp3
     */
    public void setFile(File selected) {
        this.selected = selected;
    }
}