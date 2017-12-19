/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.profissional;

import com.tramppos.controller.applicationScoped.OrcamentoControllerAppliScoped;
import com.tramppos.domain.Orcamento;
import com.tramppos.service.OrcamentoService;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author matheus
 */
@ManagedBean
@RequestScoped
public class EditOrcamentoProfissionalController {

    private Orcamento orcamento;

    @ManagedProperty("#{orcamentoControllerAppliScoped}")
    private OrcamentoControllerAppliScoped orcamentoControllerAppliScoped = new OrcamentoControllerAppliScoped();
    
    
    public EditOrcamentoProfissionalController() {
        
        
    }
    
    @PostConstruct
    public void start(){
        
        this.orcamento = new Orcamento();
        
        // Pegando objeto do FlashScoped
        this.orcamento = (Orcamento) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flashOrcamentoEdit");

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().put("flashOrcamentoEdit", this.orcamento); // incluindo um objeto no escopo Flash
        
    }
    
    public void update() throws IOException{
        OrcamentoService orcamentoService = new OrcamentoService();
        
        orcamentoService.update(this.orcamento);
        
        this.orcamentoControllerAppliScoped.carregar();
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("orcamentos.xhtml");

    }
    
    
    
    
    

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public void setOrcamentoControllerAppliScoped(OrcamentoControllerAppliScoped orcamentoControllerAppliScoped) {
        this.orcamentoControllerAppliScoped = orcamentoControllerAppliScoped;
    }
    
}
