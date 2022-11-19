package br.edu.femass.model;

import javax.persistence.Entity;

@Entity
public class Professor extends Leitor {
    protected String disciplina;
    public Professor(){

    }

    public Professor(String nome, String endereco, String telefone, String disciplina){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.disciplina = disciplina;
        prazoMaximoDev = 30;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDisciplina(){
        return disciplina;
    }

    @Override
    public String toString() {
        return this.nome + " " + this.telefone;
    }
}
