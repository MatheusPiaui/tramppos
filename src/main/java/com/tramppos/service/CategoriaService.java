/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Categoria;
import com.tramppos.repository.CategoriaRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class CategoriaService {
    
    private CategoriaRepository categoriaRepository;

    //contrutor
    public CategoriaService() 
    {
        this.categoriaRepository = new CategoriaRepository();
    }

    //getter e setter
    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    // comandos
    public void insert(Categoria categoria){
        getCategoriaRepository().insert(categoria);
    }    
    public void update(Categoria categoria){
        getCategoriaRepository().update(categoria);
    }
    public void delete(Categoria categoria){
        getCategoriaRepository().delete(categoria);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Categoria> consult(){
        return getCategoriaRepository().consult();
    }
    
    public Categoria consult(int id){
        return getCategoriaRepository().consult(id);
    }
    
}
