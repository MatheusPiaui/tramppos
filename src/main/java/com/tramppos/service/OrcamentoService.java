/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Orcamento;
import com.tramppos.repository.OrcamentoRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class OrcamentoService {
    
    private OrcamentoRepository orcamentoRepository;

    //contrutor
    public OrcamentoService() 
    {
        this.orcamentoRepository = new OrcamentoRepository();
    }

    //getter e setter
    public OrcamentoRepository getOrcamentoRepository() {
        return orcamentoRepository;
    }
    public void setOrcamentoRepository(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }
    
    // comandos
    public void insert(Orcamento orcamento){
        getOrcamentoRepository().insert(orcamento);
    }    
    public void update(Orcamento orcamento){
        getOrcamentoRepository().update(orcamento);
    }
    public void delete(Orcamento orcamento){
        getOrcamentoRepository().delete(orcamento);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Orcamento> consult(){
        return getOrcamentoRepository().consult();
    }
    
}
