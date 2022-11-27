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
    @OneToMany(cascade = CascadeType.ALL)
    protected List<Autor> autores;
    public Livro(){

    }

    public Livro(String titulo, List<Autor> autores){
    if(autores == null) autores = new ArrayList<>();
    autores.addAll(autores);
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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return this.titulo + " " + this.autores;
    }
}
