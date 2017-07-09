/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Cliente;
import com.tramppos.domain.Profissional;
import com.tramppos.service.ClienteService;
import com.tramppos.service.ProfissionalService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import com.tramppos.util.jsf.SessionUtil;
import javax.annotation.PostConstruct;

/**
 *
 * @author matheus
 */
@Named
@SessionScoped
public class PerfilController implements Serializable {
    
    private Cliente cliente;
    private Profissional profissional;
    private ClienteService clienteService;
    private ProfissionalService profissionalService;    
    
    
    @PostConstruct
    public void start() {
       this.cliente = (Cliente) SessionUtil.getParam("logCliente");
       this.profissional = (Profissional) SessionUtil.getParam("logProf");
    }    

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public ProfissionalService getProfissionalService() {
        return profissionalService;
    }

    public void setProfissionalService(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }
    
    
    
}
