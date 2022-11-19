package br.edu.femass.model;

import javax.persistence.Entity;

@Entity
public class Aluno extends Leitor {
    protected String matricula;
    public Aluno(){

    }

    public Aluno(String nome, String endereco, String telefone, String matricula){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.matricula = matricula;
        prazoMaximoDev = 15;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula(){
        return matricula;
    }

    @Override
    public String toString() {
        return this.nome + " " + this.matricula;
    }
}
