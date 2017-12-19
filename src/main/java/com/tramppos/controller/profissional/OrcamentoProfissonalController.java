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
import com.tramppos.domain.Servico;
import com.tramppos.service.FotoService;
import com.tramppos.service.OrcamentoService;
import com.tramppos.util.jsf.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

/**
 *
 * @author matheus
 */
@ManagedBean
@javax.faces.view.ViewScoped
public class OrcamentoProfissonalController implements Serializable {

    private Foto foto = new Foto();
    
    private Servico servico;
    private Orcamento orcamento = new Orcamento();
    private Orcamento orcamentoEdit;
    
    
    private List<Orcamento> listaOrcamento;

    @ManagedProperty("#{orcamentoControllerAppliScoped}")
    private OrcamentoControllerAppliScoped orcamentoControllerAppliScoped;
    
    @ManagedProperty("#{fotoControllerAppliScoped}")
    private FotoControllerAppliScoped fotoControllerAppliScoped;
      
    
    
    @PostConstruct
    public void start(){
        this.orcamento = new Orcamento();
        
        //  lists
        this.listOrcamento();
        
        
        this.foto = new Foto();
        // Pegando objeto do FlashScoped
        this.foto = (Foto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flashFoto");

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().put("flashFoto", this.foto); // incluindo um objeto no escopo Flash
        
        
    }
    
    public void insert() throws IOException, MessagingException{
        
//        System.out.println("ENTROU");
        
        Profissional profissional = (Profissional) SessionUtil.getParam("logProf"); 
        OrcamentoService orcamentoService = new OrcamentoService();
        
        this.orcamento.setProfissional(profissional);
//        this.orcamento.setServico(this.servico);
        this.orcamento.setServico(this.foto.getServico());
        this.orcamento.setSelecionado(false);
        
        orcamentoService.insert(this.orcamento);
        this.orcamento = new Orcamento();
        
//        this.listOrcamento();
//        this.orcamentoControllerAppliScoped.carregar();
        this.carregaListas();

        
        
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        
    }
    
    public void carregaListas(){
        this.orcamentoControllerAppliScoped.carregar();
        this.fotoControllerAppliScoped.carregar();
        
    }
    
    
    
    public void listOrcamento(){
        this.listaOrcamento = new ArrayList<>();
        Profissional profissional = (Profissional) SessionUtil.getParam("logProf"); 
        OrcamentoService orcamentoService = new OrcamentoService();
        
        this.listaOrcamento = orcamentoService.consult(profissional);
        
//        System.out.println("ORCAMENTOS: "+this.listaOrcamento);

    }
    
    
    
    
    
    //  Links
      

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = new Servico();
        this.servico = servico;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public List<Orcamento> getListaOrcamento() {
        return listaOrcamento;
    }

    public Orcamento getOrcamentoEdit() {
        return orcamentoEdit;
    }

    public void setOrcamentoEdit(Orcamento orcamentoEdit) {
        this.orcamentoEdit = orcamentoEdit;
    }

   

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public void setOrcamentoControllerAppliScoped(OrcamentoControllerAppliScoped orcamentoControllerAppliScoped) {
        this.orcamentoControllerAppliScoped = orcamentoControllerAppliScoped;
    }

    public void setFotoControllerAppliScoped(FotoControllerAppliScoped fotoControllerAppliScoped) {
        this.fotoControllerAppliScoped = fotoControllerAppliScoped;
    }
    
}
