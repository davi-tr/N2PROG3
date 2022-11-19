package br.edu.femass.dao;


import br.edu.femass.model.Aluno;
import br.edu.femass.model.Leitor;

import java.util.List;

public class DaoAluno extends Dao<Aluno> {

    public List<Aluno> buscarTodos(){
        return em.createQuery("select a from Aluno a order by a.nome").getResultList();
    }
    
}
