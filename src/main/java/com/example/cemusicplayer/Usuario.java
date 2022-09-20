package com.example.cemusicplayer;

public class Usuario {

    private Usuario next;
    private String email;
    private String password;
    private String nombre;
    private String provincia;

    public Usuario (String email, String password, String nombre, String provincia){ //Los parametros que yo necesito para la verificacion
        this.next = null;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getNext() {
        return next;
    }

    public void setNext(Usuario usuario) {
        this.next = usuario;
    }

}
