/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Logradouro;
import com.tramppos.domain.Estado;
import com.tramppos.service.LogradouroService;
import com.tramppos.service.EstadoService;
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
public class LogradouroController implements Serializable{
    
    private Logradouro logradouro;
    private Logradouro logradouroEdit;
    private LogradouroService logradouroService;
    private List<Logradouro> listaLogradouro;
    //
    private Estado estado;
    private EstadoService estadoService;
    private List<Estado> listaEstado;

    public EstadoService getEstadoService() {
        return estadoService;
    }

    public void setEstadoService(EstadoService estadoService) {
        this.estadoService = estadoService;
    }
    
            

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.estadoService = new EstadoService();
        this.estado = new Estado();
        this.listaEstado = estadoService.consult();
        //
        this.logradouroService = new LogradouroService();
        this.logradouro = new Logradouro();
        this.logradouroEdit = new Logradouro();
        this.listaLogradouro = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        this.getLogradouroService().insert(logradouro);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getLogradouroService().update(logradouroEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getLogradouroService().delete(logradouroEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaLogradouro = getLogradouroService().consult();
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
    

    public LogradouroService getLogradouroService() {
        return logradouroService;
    }

    public void setLogradouroService(LogradouroService logradouroService) {
        this.logradouroService = logradouroService;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public List<Logradouro> getListaLogradouro() {
        return listaLogradouro;
    }

    public void setListaLogradouro(List<Logradouro> listaLogradouro) {
        this.listaLogradouro = listaLogradouro;
    }

    public Logradouro getLogradouroEdit() {
        return logradouroEdit;
    }

    public void setLogradouroEdit(Logradouro logradouroEdit) {
        this.logradouroEdit = logradouroEdit;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }   
}
