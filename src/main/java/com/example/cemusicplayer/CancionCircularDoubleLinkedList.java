package com.example.cemusicplayer;

/**
 * Clase que permite enlazar las canciones de manera doble, es decir, forma una lista doblemente enlazada circular
 * de canciones. Presenta el head, tail y size
 */
public class CancionCircularDoubleLinkedList {
    private Cancion head;
    private Cancion tail;
    private int size;

    /**
     * Constructor. Inicializa los atributos de la lista
     */
    public CancionCircularDoubleLinkedList() {
        this.head = null;
        this.tail = null;
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
     * Indica el tamaño de la lista
     * @return tamaño de lista
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Muestra la información de cada canción en la consola
     */
    public void display() {
        Cancion current = this.head;
        if (isEmpty()) {
            System.out.println("list is empty");
        } else {
            System.out.println(" ");
            System.out.println("Canciones de la lista circular doblemente enlazada: ");
            System.out.println("HEAD: " + this.head.getNombre() + "       TAIL: " + this.tail.getNombre() + "       SIZE: " + this.size);
            System.out.println("Nombre: " + current.getNombre() + "         Favorita: " + current.isFavorite() + "          Path: " + current.getPath());
            current = current.getNext();
            while (current != head) {
                System.out.println("Nombre: " + current.getNombre() + "         Favorita: " + current.isFavorite() + "          Path: " + current.getPath());
                current = current.getNext();
            }
        }
    }

    /**
     * Muestra las canciones de la lista; cada una con su
     * respectiva canción siguiente o anterior.
     */
    public void displayPrevNext() {
        Cancion current = this.head;

        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.println(" ");
            System.out.println("Display Prev Current Next: ");
            System.out.println("HEAD: " + this.head.getNombre() + "       TAIL: " + this.tail.getNombre() + "       SIZE: " + this.size);
            System.out.println("Prev: " + current.getPrev().getNombre() + "             Current: " + current.getNombre() + "                Next: " + current.getNext().getNombre());

            current = current.getNext();
            while (current != head) {
                System.out.println("Prev: " + current.getPrev().getNombre() + "             Current: " + current.getNombre() + "             Next: " + current.getNext().getNombre());
                current = current.getNext();
            }
        }
    }

    /**
     * Añade la canción a la lista
     * @param cancion - canción
     */
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

    /**
     * Elimina la canción de la lista
     * @param nombre - nombre de la canción
     */
    public void delete(String nombre) {
        Cancion current = this.head;
        Cancion prev = this.head;

        if (isEmpty()) { //si la lista es vacia
            System.out.println("Lista vacia");
        } else {
            if (size == 1 && current.getNombre().equals(nombre)) { //si la lista es de un elemento y ese es el que se va a eliminar
                this.head = this.tail = null;
                this.size--;
                System.out.println("Se ha eliminado el unico elemento de la lista");

            } else if (current.getNombre().equals(nombre) && current.equals(head)) { //si es el head
                tail.setNext(tail.getNext().getNext());
                this.head = tail.getNext();
                head.setPrev(tail);
                this.size--;
                System.out.println("He eliminado el head");

            } else {
                //current ahora es el segundo elemento de la lista

                prev = current;
                current = current.getNext();

                while (current != head) {
                    if (current.getNombre().equals(nombre) && current.equals(tail)) { //si es el ultimo de la lista
                        prev.setNext(tail.getNext());
                        this.head.setPrev(prev);
                        this.tail = prev;
                        this.size--;
                        System.out.println("Elimino el ultimo de la lista");

                    } else if (current.getNombre().equals(nombre)) { //si es cualquier otro
                        prev.setNext(current.getNext());
                        current = prev.getNext();
                        current.setPrev(prev);
                        this.size--;
                        System.out.println("Elimino un elemento dentro de la lista");
                    }
                    prev = current;
                    current = current.getNext();
                }

            }
        }
    }

    /**
     * Busca si la canción se encuentra en la lista
     * @param nombre - nombre de la canción
     * @return null o la canción encontrada
     */
    public Cancion find (String nombre){
        Cancion current = this.head;
        while (current != null){
            if (current.getNombre().equals(nombre)){
                return current;
            }else{
                current = current.getNext();
            }
        }
        return null;
    }

    /**
     * Consigue la primera canción de la lista
     * @return primera canción
     */
    public Cancion getHead() {
        return head;
    }

    /**
     * Consigue la última canción de la lista
     * @return última canción
     */
    public Cancion getTail() {
        return tail;
    }
}