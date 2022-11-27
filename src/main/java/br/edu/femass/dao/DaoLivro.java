package br.edu.femass.dao;


import br.edu.femass.model.Aluno;
import br.edu.femass.model.Livro;

import java.util.List;

public class DaoLivro extends Dao<Livro> {

    public List<Livro> buscarTodos(){
        return em.createQuery("select l from Livro l order by l.titulo").getResultList();
    }
    
}
