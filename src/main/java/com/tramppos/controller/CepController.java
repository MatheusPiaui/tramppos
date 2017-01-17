/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Bairro;
import com.tramppos.domain.Cep;
import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Logradouro;
import com.tramppos.service.BairroService;
import com.tramppos.service.CepService;
import com.tramppos.service.CidadeService;
import com.tramppos.service.EstadoService;
import com.tramppos.service.LogradouroService;
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
public class CepController implements Serializable{
    
    private Cep cep;
    private Cep cepEdit;
    private CepService cepService;
    private List<Cep> listaCep;
    //
    private Cidade cidade;
    private CidadeService cidadeService;
    //
    private Bairro bairro;
    private BairroService bairroService;
    //
    private Logradouro logradouro;
    private LogradouroService logradouroService;
    //
    private Boolean existeBairro;
    private Boolean existeLogradouro;
    
            

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.existeBairro = false;
        this.existeLogradouro = false;
        //
        this.bairro = new Bairro();
        this.bairroService = new BairroService();
        //
        this.logradouro = new Logradouro();
        this.logradouroService = new LogradouroService();
        //
        this.cidadeService = new CidadeService();
        this.cidade = new Cidade();
        //
        this.cepService = new CepService();
        this.cep = new Cep();
        this.cepEdit = new Cep();
        this.listaCep = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        
        this.bairro = (existeBairro)?null:this.bairro;
        this.logradouro = (existeLogradouro)?null:this.logradouro;
        
        this.getCepService().insert(cep);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        
        this.bairro = (existeBairro)?null:this.bairro;
        this.logradouro = (existeLogradouro)?null:this.logradouro;
        
        this.getCepService().update(cepEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getCepService().delete(cepEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaCep = getCepService().consult();
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
    

    public CepService getCepService() {
        return cepService;
    }

    public void setCepService(CepService cepService) {
        this.cepService = cepService;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public List<Cep> getListaCep() {
        return listaCep;
    }

    public void setListaCep(List<Cep> listaCep) {
        this.listaCep = listaCep;
    }

    public Cep getCepEdit() {
        return cepEdit;
    }

    public void setCepEdit(Cep cepEdit) {
        this.cepEdit = cepEdit;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public BairroService getBairroService() {
        return bairroService;
    }

    public void setBairroService(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public LogradouroService getLogradouroService() {
        return logradouroService;
    }

    public void setLogradouroService(LogradouroService logradouroService) {
        this.logradouroService = logradouroService;
    }

    public Boolean getExisteBairro() {
        return existeBairro;
    }

    public void setExisteBairro(Boolean existeBairro) {
        this.existeBairro = existeBairro;
    }

    public Boolean getExisteLogradouro() {
        return existeLogradouro;
    }

    public void setExisteLogradouro(Boolean existeLogradouro) {
        this.existeLogradouro = existeLogradouro;
    }

      
}
