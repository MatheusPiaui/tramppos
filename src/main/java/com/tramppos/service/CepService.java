/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Cep;
import com.tramppos.repository.CepRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class CepService {
    
    private CepRepository cepRepository;

    //contrutor
    public CepService() 
    {
        this.cepRepository = new CepRepository();
    }

    //getter e setter
    public CepRepository getCepRepository() {
        return cepRepository;
    }
    public void setCepRepository(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }
    
    // comandos
    public void insert(Cep cep){
        getCepRepository().insert(cep);
    }    
    public void update(Cep cep){
        getCepRepository().update(cep);
    }
    public void delete(Cep cep){
        getCepRepository().delete(cep);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Cep> consult(){
        return getCepRepository().consult();
    }
    
}
