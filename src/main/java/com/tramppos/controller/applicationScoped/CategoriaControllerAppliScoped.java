/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.applicationScoped;

import com.tramppos.domain.Bairro;
import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Categoria;
import com.tramppos.domain.Pessoa;
import com.tramppos.service.CategoriaService;
import com.tramppos.util.upload.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author matheus
 */
@ManagedBean(eager = true)
@javax.faces.bean.ApplicationScoped
public class CategoriaControllerAppliScoped implements Serializable{

    private List<Categoria> listaCategorias = new ArrayList<>();
           

    public CategoriaControllerAppliScoped() {
        
        this.carregar();
    }
    
    public void carregar(){
        try {
            CategoriaService categoriaService = new CategoriaService();
            listaCategorias = new ArrayList<>();
            
            listaCategorias = categoriaService.consult();
                        
        } catch (Exception e) {
        }
    }
        
        
        
        
    
    
    /// getters e setters ------------------------------------------------------

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    
    
}
