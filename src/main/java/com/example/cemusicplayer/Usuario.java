package com.example.cemusicplayer;

/**
 * Clase usuario
 *
 * Contiene información de cada usuario
 * Presenta un puntero para indicar el usuario siguiente
 */
public class Usuario {

    private Usuario next;
    private String email;
    private String password;
    private String nombre;
    private String provincia;

    /**
     * Crea un usuario
     * @param email correo
     * @param password contraseña
     * @param nombre nombre de usuario
     * @param provincia provincia
     */
    public Usuario (String email, String password, String nombre, String provincia){ //Los parametros que yo necesito para la verificacion
        this.next = null;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    /**
     * Consigue el nombre del usuario
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre del usuario
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Consigue la provincia
     * @return provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Actualiza la provincia
     * @param provincia nueva provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Consigue el correo
     * @return correo
     */
    public String getEmail() {
        return email;
    }

    /**
     * Actualiza el correo
     * @param email nuevo correo
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Consigue la contraseña
     * @return contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Actualiza la contraseña
     * @param password nueva contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Consigue el siguiente usuario
     * @return usuario siguiente
     */
    public Usuario getNext() {
        return next;
    }

    /**
     * Actualiza el siguiente usuario
     * @param usuario nuevo usuario
     */
    public void setNext(Usuario usuario) {
        this.next = usuario;
    }

}
