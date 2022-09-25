package com.example.cemusicplayer;
public class CancionCircularDoubleLinkedList {
    private Cancion head;
    private Cancion tail;
    private int size;
    public CancionCircularDoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public CancionCircularDoubleLinkedList(Cancion head, Cancion tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public int getSize() {
        return this.size;
    }

    public void display() {
        Cancion current = this.head;
        if (isEmpty()) {
            System.out.println("list is empty");
        } else {
            System.out.println("Canciones de la lista circular doblemente enlazada: ");
            System.out.println("Nombre: " + current.getNombre() + "Favorita: " + current.isFavorite() + "Path: " + current.getPath());
            current = current.getNext();
            while (current != head) {
                System.out.println("Nombre: " + current.getNombre() + "Favorita: " + current.isFavorite() + "Path: " + current.getPath());
                current = current.getNext();
            }

        }

    }

    public void addToCircularDoubleLinkedList(Cancion cancion) {
        if (head == null) { //si la lista es vacia, el head y tail son los mismos
                this.head = cancion;
                this.tail = cancion;
                head.setNext(tail);
                head.setPrev(tail);
    
        } else {
                cancion.setNext(tail.getNext()); //la nueva cancion apunta al head
                tail.setNext(cancion); //la cola apunta a la nueva cancion
                cancion.setPrev(tail); //el prev de la nueva cancion es el tail
                this.tail = cancion; //el nuevo tail es la cancion
                this.head.setPrev(tail); //el prev del head ahora es el tail, la nueva cancion
        }
        this.size++;
        }

}