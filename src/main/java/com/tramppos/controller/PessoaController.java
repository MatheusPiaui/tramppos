    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Endereco;
import com.tramppos.service.PessoaService;
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
public class PessoaController implements Serializable{
    
    private Pessoa pessoa;
    private Pessoa pessoaEdit;
    private PessoaService pessoaService;
    private List<Pessoa> listaPessoa;
    
    private List<Endereco> listEnd;
    
    private Endereco endereco;

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.pessoaService = new PessoaService();
        this.pessoa = new Pessoa();
        this.pessoaEdit = new Pessoa();
        this.listaPessoa = new ArrayList<>();
        this.endereco = new Endereco();
        //this.listEnd = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
//        if(this.getEndereco() != null){            
//            this.getListEnd().add(endereco);    
//        } else {
//            this.setListEnd(null);
//        } 
        //this.pessoa.setEnderecos(listEnd);
        
        this.getPessoaService().insert(pessoa);
        this.clear();
        this.list();
        return "login.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getPessoaService().update(pessoaEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
//    public String delete(){
//        this.getPessoaService().delete(pessoaEdit);
//        this.clear();
//        this.list();
//        return "lista.xhtml?faces-redirect=true";
//    }
    
    public void list(){
        this.listaPessoa = getPessoaService().consult();
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
    

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListaPessoa() {
        return listaPessoa;
    }

    public void setListaPessoa(List<Pessoa> listaPessoa) {
        this.listaPessoa = listaPessoa;
    }

    public Pessoa getPessoaEdit() {
        return pessoaEdit;
    }

    public void setPessoaEdit(Pessoa pessoaEdit) {
        this.pessoaEdit = pessoaEdit;
    }

    public List<Endereco> getListEnd() {
        return listEnd;
    }

    public void setListEnd(List<Endereco> listEnd) {
        this.listEnd = listEnd;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
}
