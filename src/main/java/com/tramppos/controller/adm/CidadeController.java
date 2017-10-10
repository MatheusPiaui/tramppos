/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.adm;

import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import com.tramppos.service.CidadeService;
import com.tramppos.service.EstadoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.validation.OverridesAttribute;

/**
 *
 * @author matheus
 */

@Named
@SessionScoped
public class CidadeController implements Serializable{
    
    private Cidade cidade;
    private Cidade cidadeEdit;
    private CidadeService cidadeService;
    private List<Cidade> listaCidade;
    private List<Cidade> listaCidadePorEstado;
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
    
     public String teste() {
//        clear();
        System.out.println(cidade.getEstado().toString());
        return "";
    }
    
    ///
    //  limpar
    public void clear(){
        this.estadoService = new EstadoService();
        this.estado = new Estado();
        this.listaEstado = estadoService.consult();
        //
        this.cidadeService = new CidadeService();
        this.cidade = new Cidade();
        this.cidadeEdit = new Cidade();
        this.listaCidade = new ArrayList<>();
        //
        this.listaCidadePorEstado = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        this.getCidadeService().insert(cidade);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getCidadeService().update(cidadeEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getCidadeService().delete(cidadeEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaCidade = getCidadeService().consult();
    }
    
    public void list(Estado UF){
        this.listaCidadePorEstado = getCidadeService().consult(UF);
    }
    
    ///
    //
    /**
        * Listener que atualiza o combo de cidades dependendo do estado escolhido
        * 
        * @param event
        */
    public void trocaCidadesEstado(ValueChangeEvent event) {
        
        Estado e = new Estado();
        e = (Estado) event.getNewValue();
        
        System.out.print("TESTE DE ESTADO: " + e);
        
        this.list((Estado) event.getNewValue());         
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
    

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getListaCidade() {
        return listaCidade;
    }

    public void setListaCidade(List<Cidade> listaCidade) {
        this.listaCidade = listaCidade;
    }

    public Cidade getCidadeEdit() {
        return cidadeEdit;
    }

    public void setCidadeEdit(Cidade cidadeEdit) {
        this.cidadeEdit = cidadeEdit;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }   

    public List<Cidade> getListaCidadePorEstado() {
        return listaCidadePorEstado;
    }

    public void setListaCidadePorEstado(List<Cidade> listaCidadePorEstado) {
        this.listaCidadePorEstado = listaCidadePorEstado;
    }

    public List<Estado> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
    }
}
