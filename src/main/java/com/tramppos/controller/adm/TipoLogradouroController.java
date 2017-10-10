/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.adm;

import com.tramppos.domain.TipoLogradouro;
import com.tramppos.service.TipoLogradouroService;
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
public class TipoLogradouroController implements Serializable{
    
    private TipoLogradouro tipoLogradouro;
    private TipoLogradouro tipoLogradouroEdit;
    private TipoLogradouroService tipoLogradouroService;
    private List<TipoLogradouro> listaTipoLogradouro;

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.tipoLogradouroService = new TipoLogradouroService();
        this.tipoLogradouro = new TipoLogradouro();
        this.tipoLogradouroEdit = new TipoLogradouro();
        this.listaTipoLogradouro = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        this.getTipoLogradouroService().insert(tipoLogradouro);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getTipoLogradouroService().update(tipoLogradouroEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getTipoLogradouroService().delete(tipoLogradouroEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaTipoLogradouro = getTipoLogradouroService().consult();
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
    

    public TipoLogradouroService getTipoLogradouroService() {
        return tipoLogradouroService;
    }

    public void setTipoLogradouroService(TipoLogradouroService tipoLogradouroService) {
        this.tipoLogradouroService = tipoLogradouroService;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public List<TipoLogradouro> getListaTipoLogradouro() {
        return listaTipoLogradouro;
    }

    public void setListaTipoLogradouro(List<TipoLogradouro> listaTipoLogradouro) {
        this.listaTipoLogradouro = listaTipoLogradouro;
    }

    public TipoLogradouro getTipoLogradouroEdit() {
        return tipoLogradouroEdit;
    }

    public void setTipoLogradouroEdit(TipoLogradouro tipoLogradouroEdit) {
        this.tipoLogradouroEdit = tipoLogradouroEdit;
    }
    
     
    
}
