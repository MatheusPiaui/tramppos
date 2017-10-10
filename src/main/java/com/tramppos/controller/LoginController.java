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
public class LoginController implements Serializable{

    private Pessoa pessoa;
    
    private PessoaService pessoaService;
    
    
    public LoginController() {

       
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
