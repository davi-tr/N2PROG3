package br.edu.femass.dao;


import br.edu.femass.model.Aluno;
import br.edu.femass.model.Autor;

import java.util.List;

public class DaoAutor extends Dao<Autor> {

    public List<Autor> buscarTodos(){
        return em.createQuery("select au from Autor au order by au.nome").getResultList();
    }
    
}
