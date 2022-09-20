package com.example.cemusicplayer;

import java.time.LocalDate;
import java.time.LocalTime;

public class BibliotecaDoubleEndedList { //Lista de Bibliotecas

    private Biblioteca head;
    private Biblioteca tail;
    private int size;

    public BibliotecaDoubleEndedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int size() {
        return this.size;
    }

    public void insertFirst(String nombreBiblioteca) {
        LocalDate fechaActual = LocalDate.now();

        Biblioteca newBiblioteca = new Biblioteca(nombreBiblioteca, fechaActual);

        if (isEmpty()) {
            this.head = this.tail = newBiblioteca;
            this.size++;
        } else {
            newBiblioteca.setNext(this.head);
            this.head = newBiblioteca;
            this.size++;
        }

    }

    public void insertLast(String nombreBiblioteca) {
        LocalDate fechaActual = LocalDate.now();

        Biblioteca newBiblioteca = new Biblioteca(nombreBiblioteca, fechaActual);

        if (isEmpty()) {
            this.head = this.tail = newBiblioteca;
        } else{
            this.tail.setNext(newBiblioteca);
            this.tail = newBiblioteca;
            this.size++;
        }
    }




}
