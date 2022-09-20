package com.example.cemusicplayer;

public class BibliotecaLinkedList {

    private Biblioteca head;
    private int size;



    public void BibliotecaLinkedList(){
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public int size() {
        return this.size;
    }

    public void insertFirst(String nombre, int cantidad, String fecha){
        Biblioteca nuevo = new Biblioteca(nombre,cantidad,fecha);
        nuevo.setNext(this.head);
        this.head = nuevo;
        this.size++;
    }

    public Biblioteca deleteFirst(){
        if (this.head != null){
            Biblioteca temp = this.head;
            this.head = this.head.getNext();
            this.size--;
            return temp;
        }else{
            return null;
        }
    }

    public Biblioteca find (String nombre){
        Biblioteca current = this.head;
        while (current != null){
            if (current.getNombre().equals(nombre)){
                return current;
            }else{
                current = current.getNext();
            }
        }
        return null;
    }
}
