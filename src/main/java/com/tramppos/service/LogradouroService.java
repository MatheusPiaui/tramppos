/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Logradouro;
import com.tramppos.repository.LogradouroRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class LogradouroService {
    
    private LogradouroRepository logradouroRepository;

    //contrutor
    public LogradouroService() 
    {
        this.logradouroRepository = new LogradouroRepository();
    }

    //getter e setter
    public LogradouroRepository getLogradouroRepository() {
        return logradouroRepository;
    }
    public void setLogradouroRepository(LogradouroRepository logradouroRepository) {
        this.logradouroRepository = logradouroRepository;
    }
    
    // comandos
    public void insert(Logradouro logradouro){
        getLogradouroRepository().insert(logradouro);
    }    
    public void update(Logradouro logradouro){
        getLogradouroRepository().update(logradouro);
    }
    public void delete(Logradouro logradouro){
        getLogradouroRepository().delete(logradouro);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Logradouro> consult(){
        return getLogradouroRepository().consult();
    }
    
}
