/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Categoria;
import com.tramppos.service.CategoriaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author matheus
 */
@Named
@SessionScoped
public class CategoriaController implements Serializable{
    
    private Categoria categoria;
    private Categoria categoriaEdit;
    private CategoriaService categoriaService;
    private List<Categoria> listaCategoria;
    
    public String teste() {
//        clear();
        System.out.println(categoria);
        return "";
    }

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.categoriaService = new CategoriaService();
        this.categoria = new Categoria();
        this.categoriaEdit = new Categoria();
        this.listaCategoria = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        this.getCategoriaService().insert(categoria);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getCategoriaService().update(categoriaEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getCategoriaService().delete(categoriaEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaCategoria = getCategoriaService().consult();
    }
    
    ///
    // Métodos para chamada às páginas    
    public String doAdd(){
        return "cadastro.xhtml?faces-redirect=true";
    }
    
    public String doUpdate(){
        return "edit.xhtml?faces-redirect=true";
    }
    
    public String doList(){
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String doVoltar(){
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public Categoria getCategoriaEdit() {
        return categoriaEdit;
    }

    public void setCategoriaEdit(Categoria categoriaEdit) {
        this.categoriaEdit = categoriaEdit;
    }
    
    
}
