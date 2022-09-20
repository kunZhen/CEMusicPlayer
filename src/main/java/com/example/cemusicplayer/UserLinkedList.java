package com.example.cemusicplayer;

public class UserLinkedList {
    private Usuario head;
    private int size;



    public void UserLinkedList(){
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public int size() {
        return this.size;
    }

    public void insertFirst(String email, String password,String nombre, String provincia){
        Usuario nuevo = new Usuario(email,password,nombre,provincia);
        nuevo.setNext(this.head);
        this.head = nuevo;
        this.size++;
    }

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
}
