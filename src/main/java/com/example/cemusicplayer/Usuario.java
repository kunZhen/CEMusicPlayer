package com.example.cemusicplayer;

public class Usuario {

    private String nomApellido;
    private String correo;
    private String provincia;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(String nomApellido, String correo, String provincia, String contrasena) {
        this.nomApellido = nomApellido;
        this.correo = correo;
        this.provincia = provincia;
        this.contrasena = contrasena;
    }

    public String getNomApellido() {
        return nomApellido;
    }

    public void setNomApellido(String nomApellido) {
        this.nomApellido = nomApellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nomApellido='" + nomApellido + '\'' +
                ", correo='" + correo + '\'' +
                ", provincia='" + provincia + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
