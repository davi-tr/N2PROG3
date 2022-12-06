package br.edu.femass.dao;


import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;

import java.util.List;

public class DaoEmprestimo extends Dao<Emprestimo> {

    public List<Emprestimo> buscarTodos(){
        return em.createQuery("select em from Emprestimo em order by em.id").getResultList();
    }
    
}
