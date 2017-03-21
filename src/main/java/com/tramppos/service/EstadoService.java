/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Estado;
import com.tramppos.repository.EstadoRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class EstadoService {
    
    private EstadoRepository estadoRepository;

    //contrutor
    public EstadoService() 
    {
        this.estadoRepository = new EstadoRepository();
    }

    //getter e setter
    public EstadoRepository getEstadoRepository() {
        return estadoRepository;
    }
    public void setEstadoRepository(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }
    
    // comandos
    public void insert(Estado estado){
        getEstadoRepository().insert(estado);
    }    
    public void update(Estado estado){
        getEstadoRepository().update(estado);
    }
    public void delete(Estado estado){
        getEstadoRepository().delete(estado);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Estado> consult(){
        return getEstadoRepository().consult();
    }
    ///
    //
    public Estado consult(int id){
        return getEstadoRepository().consult(id);
    }
    
    public Estado consult(String sigla){
        return getEstadoRepository().consult(sigla);
    }
}
