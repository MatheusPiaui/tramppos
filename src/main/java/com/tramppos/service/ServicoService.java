/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Servico;
import com.tramppos.repository.ServicoRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ServicoService {
    
    private ServicoRepository servicoRepository;

    //contrutor
    public ServicoService() 
    {
        this.servicoRepository = new ServicoRepository();
    }

    //getter e setter
    public ServicoRepository getServicoRepository() {
        return servicoRepository;
    }
    public void setServicoRepository(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }
    
    // comandos
    public void insert(Servico servico){
        getServicoRepository().insert(servico);
    }    
    public void update(Servico servico){
        getServicoRepository().update(servico);
    }
    public void delete(Servico servico){
        getServicoRepository().delete(servico);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Servico> consult(){
        return getServicoRepository().consult();
    }
    
}
