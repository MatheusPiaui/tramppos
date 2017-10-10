/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Pessoa;
import com.tramppos.service.PessoaService;
import com.tramppos.util.jsf.SessionUtil;
import java.io.Serializable;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

/**
 *
 * @author matheus
 */
@Named
@SessionScoped
public class IndexController implements Serializable{

    private Pessoa pessoa;
    
    private PessoaService pessoaService;
    
    
    public IndexController() {
    }
    
    public String doEntrar(){
        try {
            this.pessoa = (Pessoa) SessionUtil.getParam("logPessoa");
            this.pessoaService = new PessoaService();
            
            if(this.pessoa != null){
                return "pessoa/homegeral.xhtml?faces-redirect=true";
            } else {
                return "login.xhtml?faces-redirect=true";
            }
            
            
        } catch (Exception e) {
            return "login.xhtml?faces-redirect=true";
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
}
