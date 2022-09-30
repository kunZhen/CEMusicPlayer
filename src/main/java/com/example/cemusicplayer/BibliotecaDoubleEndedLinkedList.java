package com.example.cemusicplayer;

public class BibliotecaDoubleEndedLinkedList {

    private Biblioteca head;
    private Biblioteca tail;
    private int size;

    public void BibliotecaDoubleEndedLinkedList(){
        this.head = null;
        this.tail = null;
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

        if (isEmpty()) { //Si la lista esta vacia
            this.head = this.tail = nuevo;
        } else {
            nuevo.setNext(this.head);
            this.head = nuevo;
        } this.size++;

    }

    public void insertFirstBiblioteca(Biblioteca bibliotecaNew) {
        if (isEmpty()) { //Si la lista esta vacia
            this.head = this.tail = bibliotecaNew;
        } else{
            bibliotecaNew.setNext(this.head);
            this.head = bibliotecaNew;
        } this.size++;
    }


    public void insertLast(String nombre, int cantidad, String fecha) {
        Biblioteca nuevo = new Biblioteca(nombre, cantidad, fecha);

        if (isEmpty()) {
            this.head = this.tail = nuevo;
        } else{
            tail.setNext(nuevo);
            this.tail = nuevo;
        } this.size++;
    }

    public void insertLastBiblioteca(Biblioteca bibliotecaNew) {

        if (isEmpty()) {
        this.head = this.tail = bibliotecaNew;
        } else{
            this.tail.setNext(bibliotecaNew);
            this.tail = bibliotecaNew;;
        } this.size++;
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

    public Biblioteca deleteSelectedBiblioteca (String nombre){
        Biblioteca current = this.head;
        Biblioteca previous = this.head;

        while (current != null){ //Recorre la lista
            if (current.getNombre().equals(nombre)){
                if (current == this.head){
                    this.head = this.head.getNext();
                    this.size--;
                    return current;
                } else if (current == this.tail){
                    this.tail = previous;
                    previous.setNext(null);
                    this.size--;
                    return current;
                } else {
                    previous.setNext(current.getNext());
                    this.size--;
                    return current;
                } 
            } else{
                previous = current;
                current = current.getNext();
            }
        }
        return null;
    }

    public Biblioteca displayBiblioteca() {
        Biblioteca current = this.head;

        System.out.println(" ");
        System.out.println("Lista de Bibliotecas");
        System.out.println("HEAD: " + this.head.getNombre() + "     TAIL: " + this.tail.getNombre());
        System.out.println("Cantidad de Bibliotecas: " + this.size);

        while (current != null) {
            System.out.println("Bibloteca: " + current.getNombre() + "      Fecha: " + current.getFecha() + "      Cantidad de canciones: " + current.getCantidad());
            current = current.getNext();
        }
        return null;
    }
}
