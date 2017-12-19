/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.adm;

import com.tramppos.domain.Orcamento;
import com.tramppos.domain.Profissional;
import com.tramppos.domain.Servico;
import com.tramppos.service.OrcamentoService;
import com.tramppos.service.ProfissionalService;
import com.tramppos.service.ServicoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.MessagingException;

/**
 *
 * @author matheus
 */
@Named
@SessionScoped
public class OrcamentoController implements Serializable{
    
    private Orcamento orcamento;
    private Orcamento orcamentoEdit;
    private OrcamentoService orcamentoService;
    private List<Orcamento> listaOrcamento;
    
    private ProfissionalService profissionalService;
    private List<Profissional> listaProfissionais;
    private ServicoService servicoService;
    private List<Servico> listaServico;
    
    private int hora;
    

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.orcamentoService = new OrcamentoService();
        this.orcamento = new Orcamento();
        this.orcamentoEdit = new Orcamento();
        this.listaOrcamento = new ArrayList<>();
        this.listaProfissionais = new ArrayList<>();
        this.listaServico = new ArrayList<>();
        this.profissionalService = new ProfissionalService();
        this.servicoService = new ServicoService();
        
      
    }
    
    ///
    // 
    public String insert() throws MessagingException{
        
        this.orcamento.setSelecionado(false);
        this.getOrcamentoService().insert(orcamento);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getOrcamentoService().update(orcamentoEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getOrcamentoService().delete(orcamentoEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaOrcamento = getOrcamentoService().consult();
        this.listaProfissionais = getProfissionalService().consult();
        this.listaServico = getServicoService().consult();
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
    

    public OrcamentoService getOrcamentoService() {
        return orcamentoService;
    }

    public void setOrcamentoService(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
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

    public void setListaOrcamento(List<Orcamento> listaOrcamento) {
        this.listaOrcamento = listaOrcamento;
    }

    public Orcamento getOrcamentoEdit() {
        return orcamentoEdit;
    }

    public void setOrcamentoEdit(Orcamento orcamentoEdit) {
        this.orcamentoEdit = orcamentoEdit;
    }

    public List<Profissional> getListaProfissionais() {
        return listaProfissionais;
    }

    public void setListaProfissionais(List<Profissional> listaProfissionais) {
        this.listaProfissionais = listaProfissionais;
    }

    public List<Servico> getListaServico() {
        return listaServico;
    }

    public void setListaServico(List<Servico> listaServico) {
        this.listaServico = listaServico;
    }

    public ProfissionalService getProfissionalService() {
        return profissionalService;
    }

    public void setProfissionalService(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    public ServicoService getServicoService() {
        return servicoService;
    }

    public void setServicoService(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    
    
}
