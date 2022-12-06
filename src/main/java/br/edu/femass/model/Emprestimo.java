package br.edu.femass.model;

import org.hibernate.sql.Select;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected LocalDate dataEmprestimo;
    protected LocalDate dataDevolucaoPrevista;
    protected LocalDate dataDevolucao;
    protected String titulo;
    protected String nomeDoautor;
    protected Integer prazo;

    protected Integer selected;
    @ManyToOne(cascade = CascadeType.DETACH)
    protected Exemplar exemplar;
    @ManyToOne(cascade = CascadeType.DETACH)
    protected Leitor leitor;


    public Emprestimo(){

    }

    public Emprestimo(Exemplar exemplar, Leitor leitor){
        this.dataEmprestimo = LocalDate.now();
        this.exemplar = exemplar;
        this.leitor = leitor;
    }

    public String getTitulo() {
        return exemplar.getTitulo();
    }

    public String getNomeDoautor() {
        return exemplar.getNomeDoautor();
    }

    public Integer getPrazo() {
        return leitor.getPrazoMaximoDev();
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public Long getId() {
        return id;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }
}
