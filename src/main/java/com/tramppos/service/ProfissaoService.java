/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Categoria;
import com.tramppos.domain.Profissao;
import com.tramppos.repository.ProfissaoRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ProfissaoService {
    
    private ProfissaoRepository profissaoRepository;

    //contrutor
    public ProfissaoService() 
    {
        this.profissaoRepository = new ProfissaoRepository();
    }

    //getter e setter
    public ProfissaoRepository getProfissaoRepository() {
        return profissaoRepository;
    }
    public void setProfissaoRepository(ProfissaoRepository profissaoRepository) {
        this.profissaoRepository = profissaoRepository;
    }
    
    // comandos
    public void insert(Profissao profissao){
        getProfissaoRepository().insert(profissao);
    }    
    public void update(Profissao profissao){
        getProfissaoRepository().update(profissao);
    }
    public void delete(Profissao profissao){
        getProfissaoRepository().delete(profissao);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Profissao> consult(){
        return getProfissaoRepository().consult();
    }
    public List<Profissao> consult(Categoria categoria){
        return getProfissaoRepository().consult(categoria);
    }
    public Profissao consult(int id){
        return getProfissaoRepository().consult(id);
    }
}
