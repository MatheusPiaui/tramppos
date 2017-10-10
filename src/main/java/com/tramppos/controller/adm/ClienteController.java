    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.adm;

import com.tramppos.domain.Cliente;
import com.tramppos.domain.Endereco;
import com.tramppos.service.ClienteService;
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
public class ClienteController implements Serializable{
    
    private Cliente cliente;
    private Cliente clienteEdit;
    private ClienteService clienteService;
    private List<Cliente> listaCliente;
    
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
        this.clienteService = new ClienteService();
        this.cliente = new Cliente();
        this.clienteEdit = new Cliente();
        this.listaCliente = new ArrayList<>();
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
        //this.cliente.setEnderecos(listEnd);
        
        this.getClienteService().insert(cliente);
        this.clear();
        this.list();
        return "login.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getClienteService().update(clienteEdit);
        this.clear();
        this.list();
        return "lista.xhtml?faces-redirect=true";
    }
    
//    public String delete(){
//        this.getClienteService().delete(clienteEdit);
//        this.clear();
//        this.list();
//        return "lista.xhtml?faces-redirect=true";
//    }
    
    public void list(){
        this.listaCliente = getClienteService().consult();
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

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Cliente getClienteEdit() {
        return clienteEdit;
    }

    public void setClienteEdit(Cliente clienteEdit) {
        this.clienteEdit = clienteEdit;
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
