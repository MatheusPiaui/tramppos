/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import com.tramppos.repository.CidadeRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class CidadeService {
    
    private CidadeRepository cidadeRepository;

    //contrutor
    public CidadeService() 
    {
        this.cidadeRepository = new CidadeRepository();
    }

    //getter e setter
    public CidadeRepository getCidadeRepository() {
        return cidadeRepository;
    }
    public void setCidadeRepository(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }
    
    // comandos
    public void insert(Cidade cidade){  
        getCidadeRepository().insert(cidade);
    }    
    public void update(Cidade cidade){
        getCidadeRepository().update(cidade);
    }
    public void delete(Cidade cidade){
        getCidadeRepository().delete(cidade);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Cidade> consult(){
        return getCidadeRepository().consult();
    }
    
    public List<Cidade> consult(Estado uf){
        return getCidadeRepository().consult(uf);
    }
    
    public Cidade consult(String nome){
        return getCidadeRepository().consult(nome);
    }

    public Cidade consult(int id) {
        return getCidadeRepository().consult(id);
    }
    
}
