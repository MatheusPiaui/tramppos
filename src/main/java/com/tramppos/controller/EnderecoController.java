/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Bairro;
import com.tramppos.domain.Cep;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Cep;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Logradouro;
import com.tramppos.service.BairroService;
import com.tramppos.service.CepService;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.CepService;
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
public class EnderecoController implements Serializable{
    
    private Endereco endereco;
    private Endereco enderecoEdit;
    private EnderecoService enderecoService;
    private List<Endereco> listaEndereco;
    //
    private Cep cep;
    private CepService cepService;
    //
    private Bairro bairro;
    private BairroService bairroService;
    //
    private Logradouro logradouro;
    private LogradouroService logradouroService;
    
            

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        //
        this.bairro = new Bairro();
        this.bairroService = new BairroService();
        //
        this.logradouro = new Logradouro();
        this.logradouroService = new LogradouroService();
        //
        this.cepService = new CepService();
        this.cep = new Cep();
        //
        this.enderecoService = new EnderecoService();
        this.endereco = new Endereco();
        this.enderecoEdit = new Endereco();
        this.listaEndereco = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        this.getEnderecoService().insert(endereco);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getEnderecoService().update(enderecoEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getEnderecoService().delete(enderecoEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaEndereco = getEnderecoService().consult();
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
    

    public EnderecoService getEnderecoService() {
        return enderecoService;
    }

    public void setEnderecoService(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public Endereco getEnderecoEdit() {
        return enderecoEdit;
    }

    public void setEnderecoEdit(Endereco enderecoEdit) {
        this.enderecoEdit = enderecoEdit;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public CepService getCepService() {
        return cepService;
    }

    public void setCepService(CepService cepService) {
        this.cepService = cepService;
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

      
}
