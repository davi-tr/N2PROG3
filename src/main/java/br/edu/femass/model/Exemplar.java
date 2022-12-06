package br.edu.femass.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected Integer quantidade;
    protected LocalDate dataAq;
    protected String titulo;
    protected String nomeDoautor;
    protected Integer selected;
    @ManyToOne(cascade = CascadeType.DETACH)
    protected Livro livro;

    public Exemplar(){

    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Exemplar(Integer quantidade, Livro livro){
        this.quantidade = quantidade;
        this.livro = livro;
    }

    public LocalDate getDataAq() {
        LocalDate novo = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatado = novo.format(formatador);
        return dataAq = LocalDate.parse(formatado, formatador);
    }

    public String getTitulo() {
        return livro.getTitulo();
    }

    public String getNomeDoautor() {
        return livro.getNomeDoautor();
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
