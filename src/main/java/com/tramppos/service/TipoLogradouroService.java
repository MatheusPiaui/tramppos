/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.TipoLogradouro;
import com.tramppos.repository.TipoLogradouroRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class TipoLogradouroService {
    
    private TipoLogradouroRepository tipoLogradouroRepository;

    //contrutor
    public TipoLogradouroService() 
    {
        this.tipoLogradouroRepository = new TipoLogradouroRepository();
    }

    //getter e setter
    public TipoLogradouroRepository getTipoLogradouroRepository() {
        return tipoLogradouroRepository;
    }
    public void setTipoLogradouroRepository(TipoLogradouroRepository tipoLogradouroRepository) {
        this.tipoLogradouroRepository = tipoLogradouroRepository;
    }
    
    // comandos
    public void insert(TipoLogradouro tipoLogradouro){
        getTipoLogradouroRepository().insert(tipoLogradouro);
    }    
    public void update(TipoLogradouro tipoLogradouro){
        getTipoLogradouroRepository().update(tipoLogradouro);
    }
    public void delete(TipoLogradouro tipoLogradouro){
        getTipoLogradouroRepository().delete(tipoLogradouro);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<TipoLogradouro> consult(){
        return getTipoLogradouroRepository().consult();
    }
    
}
