/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Bairro;
import com.tramppos.repository.BairroRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class BairroService {
    
    private BairroRepository bairroRepository;

    //contrutor
    public BairroService() 
    {
        this.bairroRepository = new BairroRepository();
    }

    //getter e setter
    public BairroRepository getBairroRepository() {
        return bairroRepository;
    }
    public void setBairroRepository(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }
    
    // comandos
    public void insert(Bairro bairro){
        getBairroRepository().insert(bairro);
    }    
    public void update(Bairro bairro){
        getBairroRepository().update(bairro);
    }
    public void delete(Bairro bairro){
        getBairroRepository().delete(bairro);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Bairro> consult(){
        return getBairroRepository().consult();
    }
    
    public Bairro consult(String nome){
        return getBairroRepository().consult(nome);
    }
    
}
