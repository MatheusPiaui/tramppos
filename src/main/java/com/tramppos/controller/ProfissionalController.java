/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Endereco;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Profissional;
import com.tramppos.service.ProfissionalService;
import java.io.Serializable;
import java.util.AbstractList;
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
public class ProfissionalController implements Serializable{
    
    private Profissional profissional;
    private Profissional profissionalEdit;
    private ProfissionalService profissionalService;
    private List<Profissional> listaProfissional;
    
    private List<Endereco> listEnd;
    
    private Endereco endereco;
    private Estado estado;

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.profissionalService = new ProfissionalService();
        this.profissional = new Profissional();
        this.profissionalEdit = new Profissional();
        this.listaProfissional = new ArrayList<>();
        this.endereco = new Endereco();
        this.listEnd = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        
        if(this.getEndereco() != null){            
            this.getListEnd().add(endereco);    
        } else {
            this.setListEnd(null);
        } 
        //this.profissional.setEnderecos(listEnd);
        
        this.getProfissionalService().insert(profissional);
        this.clear();
        this.list();
        return "login.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getProfissionalService().update(profissionalEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
//    public String delete(){
//        this.getProfissionalService().delete(profissionalEdit);
//        this.clear();
//        this.list();
//        return "lista.xhtml?faces-redirect=true";
//    }
    
    public void list(){
        this.listaProfissional = getProfissionalService().consult();
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
    

    public ProfissionalService getProfissionalService() {
        return profissionalService;
    }

    public void setProfissionalService(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public List<Profissional> getListaProfissional() {
        return listaProfissional;
    }

    public void setListaProfissional(List<Profissional> listaProfissional) {
        this.listaProfissional = listaProfissional;
    }

    public Profissional getProfissionalEdit() {
        return profissionalEdit;
    }

    public void setProfissionalEdit(Profissional profissionalEdit) {
        this.profissionalEdit = profissionalEdit;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getListEnd() {
        return listEnd;
    }

    public void setListEnd(List<Endereco> listEnd) {
        this.listEnd = listEnd;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
}
