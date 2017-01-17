/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Bairro;
import com.tramppos.service.BairroService;
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
public class BairroController implements Serializable{
    
    private Bairro bairro;
    private Bairro bairroEdit;
    private BairroService bairroService;
    private List<Bairro> listaBairro;

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.bairroService = new BairroService();
        this.bairro = new Bairro();
        this.bairroEdit = new Bairro();
        this.listaBairro = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        this.getBairroService().insert(bairro);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getBairroService().update(bairroEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getBairroService().delete(bairroEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaBairro = getBairroService().consult();
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
    

    public BairroService getBairroService() {
        return bairroService;
    }

    public void setBairroService(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public List<Bairro> getListaBairro() {
        return listaBairro;
    }

    public void setListaBairro(List<Bairro> listaBairro) {
        this.listaBairro = listaBairro;
    }

    public Bairro getBairroEdit() {
        return bairroEdit;
    }

    public void setBairroEdit(Bairro bairroEdit) {
        this.bairroEdit = bairroEdit;
    }
    
     
    
}
