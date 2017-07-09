/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;


import com.tramppos.domain.Profissional;
import com.tramppos.repository.ProfissionalRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ProfissionalService {
    
    private ProfissionalRepository profissionalRepository;

    //contrutor
    public ProfissionalService() 
    {
        this.profissionalRepository = new ProfissionalRepository();
    }

    //getter e setter
    public ProfissionalRepository getProfissionalRepository() {
        return profissionalRepository;
    }
    public void setProfissionalRepository(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }
    
    // comandos
    public void insert(Profissional profissional){
        getProfissionalRepository().insert(profissional);
    }    
    public void update(Profissional profissional){
        getProfissionalRepository().update(profissional);
    }
//    public void delete(Profissional profissional){
//        getProfissionalRepository().delete(profissional);
//    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Profissional> consult(){
        return getProfissionalRepository().consult();
    }
    
    public Profissional consult(String mail){
        return getProfissionalRepository().consult(mail);
    }
}
