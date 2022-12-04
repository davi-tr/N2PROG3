package br.edu.femass;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;

public class teste {
    public static void main(String[] args) {
        Aluno aluno = new Aluno("teste2","teste1","teste3","teste89");
        DaoAluno dao = new DaoAluno();
        dao.inserir(aluno);
        System.out.println(aluno.toString());
        aluno.setMatricula("batatinha39");
        aluno.setEndereco("ceu");
        dao.alterar(aluno);
        System.out.println(aluno);
    }
}
