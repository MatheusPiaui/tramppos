/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller;

import com.tramppos.domain.Cliente;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Servico;
import com.tramppos.service.ClienteService;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.ServicoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class ServicoController implements Serializable{
    
    private Servico servico;
    private Servico servicoEdit;
    
    private ServicoService servicoService;
    private ClienteService clienteService;
    private EnderecoService enderecoService;
    
    private List<Servico> listaServico;
    private List<Cliente> listaCliente;
    private List<Endereco> listaEndereco;

    @PostConstruct
    public void start() {
        clear();
        list();
    }
    
    ///
    //  limpar
    public void clear(){
        this.servicoService = new ServicoService();
        this.clienteService = new ClienteService();
        this.enderecoService = new EnderecoService();
        this.servico = new Servico();
        this.servicoEdit = new Servico();
        this.listaServico = new ArrayList<>();
        this.listaCliente = new ArrayList<>();
        this.listaEndereco = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        // data        
        Date dataAtual = new Date(System.currentTimeMillis());
        System.out.println(dataAtual);
        
        this.servico.setDataSolicitacao(dataAtual);
        
        this.getServicoService().insert(servico);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getServicoService().update(servicoEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        this.getServicoService().delete(servicoEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
    public void list(){
        this.listaServico = getServicoService().consult();
        this.listaCliente = getClienteService().consult();
        this.listaEndereco = getEnderecoService().consult();
    }
    
    public String retornaStatus(int i){
        switch (i) {
            case 1:
                return "Aberto";
            case 2:
                return "Avaliaçao";
            case 3:
                return "Contato";
            case 4:
                return "Finalizado";
            default:
                return "Invalido";
        }
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
    

    public ServicoService getServicoService() {
        return servicoService;
    }

    public void setServicoService(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<Servico> getListaServico() {
        return listaServico;
    }

    public void setListaServico(List<Servico> listaServico) {
        this.listaServico = listaServico;
    }

    public Servico getServicoEdit() {
        return servicoEdit;
    }

    public void setServicoEdit(Servico servicoEdit) {
        this.servicoEdit = servicoEdit;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public EnderecoService getEnderecoService() {
        return enderecoService;
    }

    public void setEnderecoService(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }
    
    
}
