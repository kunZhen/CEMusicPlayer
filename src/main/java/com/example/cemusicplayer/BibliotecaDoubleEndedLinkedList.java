package com.example.cemusicplayer;

/**
 * BibliotecaDoubleEndedList es una clase que permite enlazar los Nodos Biblioteca que
 * se van creando, es decir, forma la lista de las bibliotecas. Presenta un head, tail y size.
 */
public class BibliotecaDoubleEndedLinkedList {

    private Biblioteca head;
    private Biblioteca tail;
    private int size;

    /**
     * Constructor. Inicializar los atributos de la lista.
     */
    public void BibliotecaDoubleEndedLinkedList(){
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
     * Indica el tamaño que presenta la lista
     * @return el tamaño de la lista
     */
    public int size() {
        return this.size;
    }

    /**
     * Inserta el objeto biblioteca al final de la lista
     * @param bibliotecaNew - biblioteca
     */
    public void insertLastBiblioteca(Biblioteca bibliotecaNew) {

        if (isEmpty()) {
        this.head = this.tail = bibliotecaNew;
        } else{
            this.tail.setNext(bibliotecaNew);
            this.tail = bibliotecaNew;;
        } this.size++;
    }

    /**
     * Busca la biblioteca dentro de la lista
     * @param nombre - nombre de la biblioteca
     * @return null o la biblioteca que se está buscando
     */
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

    /**
     * Elimina la biblioteca seleccionada
     * @param nombre - nomrbe de la biblioteca
     * @return null o la biblioteca que se eliminó
     */
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

    /**
     * Muestra las bibliotecas que cuenta la lista en la consola
     * @return null
     */
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
