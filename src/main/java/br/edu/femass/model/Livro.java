package br.edu.femass.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Long codigo;
    protected String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    protected Autor autor;
    public Livro(){

    }

    public Livro(String titulo, Autor autor){
    this.autor = autor;
    this.titulo = titulo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return this.titulo + " | " + this.autor;
    }
}
