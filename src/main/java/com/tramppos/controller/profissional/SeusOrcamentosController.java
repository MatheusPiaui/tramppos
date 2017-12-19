/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.profissional;

import com.tramppos.controller.applicationScoped.FotoControllerAppliScoped;
import com.tramppos.controller.applicationScoped.OrcamentoControllerAppliScoped;
import com.tramppos.domain.Foto;
import com.tramppos.domain.Orcamento;
import com.tramppos.domain.Profissional;
import com.tramppos.service.FotoService;
import com.tramppos.service.OrcamentoService;
import com.tramppos.util.jsf.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author matheus
 */
@ManagedBean
@javax.faces.bean.RequestScoped
public class SeusOrcamentosController implements Serializable{
    
    private Orcamento orcamentoEdit;
    private Orcamento orcamentoAux;

    
    private List<Orcamento> orcamentos;
    
    private List<String> materiais;
    
    @ManagedProperty("#{orcamentoControllerAppliScoped}")
    private OrcamentoControllerAppliScoped orcamentoApplication;
    
    
    private Profissional profissional;
    
    @PostConstruct
    public void start() {
        
        this.orcamentoApplication.carregar();
        
        this.orcamentos = new ArrayList<>();
        this.orcamentoAux = new Orcamento();
        this.materiais = new ArrayList<>();
        
        this.profissional = new Profissional();
        this.profissional = (Profissional) SessionUtil.getParam("logProf");
        
        this.orcamentos = this.orcamentoApplication.orcamentos(this.profissional);
        
        // Pegando objeto do FlashScoped
        this.orcamentoAux = (Orcamento) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flashOrcamento");
        if(this.orcamentoAux != null)
        {
            OrcamentoService orcamentoService = new OrcamentoService();
        
            this.materiais = new ArrayList<>();
            this.materiais = orcamentoService.listMateriais(this.orcamentoAux);
        }

        

    }
    
    //      Utils
    //  Metodo retorna caminho da imagem do servico
    public String getImgServico(Orcamento orcamento) {
        FotoService fotoService = new FotoService();
        Foto f = new Foto();
        
        try {
            if(orcamento != null){
                f = fotoService.consult(orcamento.getServico());

            }else{
//                if(this.servico != null){
//                f = fotoService.consult(this.servico);           
//                
//                }else{
//                    System.out.println("ENTROU SERVICO NULO");
//                }
            }
            
            if (f != null) {
                String str = fotoService.linkImg(f);
                if (str != null) {
                    return str;
                }                
            }    
            
            return "";
        } catch (Exception e) { 
            e.printStackTrace();
            return "";
        }
        
    }
    
    public void materiais(Orcamento orc) throws IOException{
//        OrcamentoService orcamentoService = new OrcamentoService();
//        
//        this.materiais = new ArrayList<>();
//        this.materiais = orcamentoService.listMateriais(orc);
        
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().put("flashOrcamento", orc); // incluindo um objeto no escopo Flash
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("orcamentos.xhtml#materiais");
        
    }
    
    
    
    //      Links
    public void doUpdate(Orcamento orcamento) throws IOException{
        
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().put("flashOrcamentoEdit", orcamento); // incluindo um objeto no escopo Flash
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("editorcamento.xhtml");
    }
    
    
    
    //      Getters e setters --------------------------------------------------

    public Orcamento getOrcamentoEdit() {
        return orcamentoEdit;
    }

    public void setOrcamentoEdit(Orcamento orcamentoEdit) {
        this.orcamentoEdit = orcamentoEdit;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public List<String> getMateriais() {
        return materiais;
    }

    public Orcamento getOrcamentoAux() {
        return orcamentoAux;
    }

    public void setOrcamentoAux(Orcamento orcamentoAux) {
        this.orcamentoAux = orcamentoAux;
    }

    public void setOrcamentoApplication(OrcamentoControllerAppliScoped orcamentoApplication) {
        this.orcamentoApplication = orcamentoApplication;
    }

    
    
}
