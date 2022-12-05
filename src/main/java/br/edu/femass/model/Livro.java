package br.edu.femass.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected Long codigo;
    protected String titulo;
    protected String nomeDoautor;
    @ManyToOne(cascade = CascadeType.ALL)
    protected Autor autor;
    public Livro(){

    }

    public Livro(String titulo, Autor autor){
    this.autor = autor;
    this.titulo = titulo;
    }

    public String getNomeDoautor() {
        return autor.getNome() + " " + autor.getSobreNome();
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

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.titulo + " | " + this.autor;
    }
}
