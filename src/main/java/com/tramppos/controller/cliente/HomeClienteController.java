/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.cliente;

import com.tramppos.domain.Categoria;
import com.tramppos.domain.Cliente;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Profissao;
import com.tramppos.domain.Servico;
import com.tramppos.service.CategoriaService;
import com.tramppos.service.ClienteService;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ProfissaoService;
import com.tramppos.service.ServicoService;
import com.tramppos.util.jsf.SessionUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author matheus
 */
@Named(value = "homeClienteController")
@SessionScoped
public class HomeClienteController implements Serializable {

    private Cliente cliente;
    private ClienteService clienteService;
    private PessoaService pessoaService;
    
  
    
    @PostConstruct
    public void start() {
        this.clear();              
        
        this.clienteService = new ClienteService();
        this.cliente = (Cliente) SessionUtil.getParam("logCliente");      
    }   

    public void clear(){         
    }
    
    public String getImgPerfil(){      
        return this.pessoaService.linkImgPerfil(cliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }    
}
