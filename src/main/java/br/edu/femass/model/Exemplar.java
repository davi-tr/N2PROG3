package br.edu.femass.model;

import javax.persistence.*;

@Entity
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected Integer quantidade;
    protected String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    protected Livro livro;

    public Exemplar(){

    }

    public String getTitulo() {
        return livro.getTitulo();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

}
