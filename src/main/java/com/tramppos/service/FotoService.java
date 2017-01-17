/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Foto;
import com.tramppos.repository.FotoRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class FotoService {
    
    private FotoRepository fotoRepository;

    //contrutor
    public FotoService() 
    {
        this.fotoRepository = new FotoRepository();
    }

    //getter e setter
    public FotoRepository getFotoRepository() {
        return fotoRepository;
    }
    public void setFotoRepository(FotoRepository fotoRepository) {
        this.fotoRepository = fotoRepository;
    }
    
    // comandos
    public void insert(Foto foto){
        getFotoRepository().insert(foto);
    }    
    public void update(Foto foto){
        getFotoRepository().update(foto);
    }
    public void delete(Foto foto){
        getFotoRepository().delete(foto);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Foto> consult(){
        return getFotoRepository().consult();
    }
    
}
