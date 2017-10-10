/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.adm;

import com.tramppos.domain.Profissao;
import com.tramppos.service.ProfissaoService;
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
public class ProfissaoController implements Serializable{
    
    private Profissao profissao;
    private Profissao profissaoEdit;
    private ProfissaoService profissaoService;
    private List<Profissao> listaProfissao;

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.profissaoService = new ProfissaoService();
        this.profissao = new Profissao();
        this.profissaoEdit = new Profissao();
        this.listaProfissao = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        this.getProfissaoService().insert(profissao);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getProfissaoService().update(profissaoEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getProfissaoService().delete(profissaoEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaProfissao = getProfissaoService().consult();
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
    

    public ProfissaoService getProfissaoService() {
        return profissaoService;
    }

    public void setProfissaoService(ProfissaoService profissaoService) {
        this.profissaoService = profissaoService;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public List<Profissao> getListaProfissao() {
        return listaProfissao;
    }

    public void setListaProfissao(List<Profissao> listaProfissao) {
        this.listaProfissao = listaProfissao;
    }

    public Profissao getProfissaoEdit() {
        return profissaoEdit;
    }

    public void setProfissaoEdit(Profissao profissaoEdit) {
        this.profissaoEdit = profissaoEdit;
    }
    
    
}
