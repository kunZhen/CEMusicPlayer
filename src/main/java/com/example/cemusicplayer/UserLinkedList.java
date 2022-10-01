package com.example.cemusicplayer;

/**
 * UserLinkedList es una clase que enlaza los usuarios que se van creando,
 * es decir, forma una lista de usuarios. Presenta un head y size.
 */
public class UserLinkedList {
    private Usuario head;
    private int size;


    /**
     * Constructor - Inicializa los atributos de la lista
     */
    public void UserLinkedList(){
        this.head = null;
        this.size = 0;
    }

    /**
     * Indica si la lista se encuentra vacia
     * @return true o false
     */
    public boolean isEmpty(){
        return this.head == null;
    }

    /**
     * Indica la cantidad de usuarios en hay en la lista
     * @return size
     */
    public int size() {
        return this.size;
    }

    /**
     * Inserta al principio de la lista el usuario que se crea a partir de
     * los atributos ingresados
     * @param email correo
     * @param password contraseña
     * @param nombre nombre
     * @param provincia provincia
     */
    public void insertFirst(String email, String password,String nombre, String provincia){
        Usuario nuevo = new Usuario(email,password,nombre,provincia);
        nuevo.setNext(this.head);
        this.head = nuevo;
        this.size++;
    }


    /**
     * Elimina el primero de la lista
     * @return null o retorna el usuario que eliminó
     */
    public Usuario deleteFirst(){
        if (this.head != null){
            Usuario temp = this.head;
            this.head = this.head.getNext();
            this.size--;
            return temp;
        }else{
            return null;
        }
    }

    /**
     * Busca al usuario con su usuario y contraseña
     * @param user usuario
     * @param pass contraseña
     * @return null o el usuario que encontraron
     */
    public Usuario find (String user, String pass){
        Usuario current = this.head;
        while (current != null){
            if (current.getEmail().equals(user) & current.getPassword().equals(pass)){
                return current;
            }else{
                current = current.getNext();
            }
        }
        return null;
    }

    /**
     * Muestra los usuarios de la lista
     * @return null
     */
    public Usuario displayUsuarios() {
    Usuario current = this.head;

    System.out.println(" ");
    System.out.println("Lista de usuarios: ");
    System.out.println("Cantidad de canciones: " + this.size);

    while(current != null) {
        System.out.println("Usuario: " + current.getNombre());
        current = current.getNext();

    } return null;

    }
}
