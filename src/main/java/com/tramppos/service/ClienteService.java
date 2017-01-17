/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;


import com.tramppos.domain.Cliente;
import com.tramppos.repository.ClienteRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ClienteService {
    
    private ClienteRepository clienteRepository;

    //contrutor
    public ClienteService() 
    {
        this.clienteRepository = new ClienteRepository();
    }

    //getter e setter
    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    // comandos
    public void insert(Cliente cliente){
        getClienteRepository().insert(cliente);
    }    
    public void update(Cliente cliente){
        getClienteRepository().update(cliente);
    }
//    public void delete(Cliente cliente){
//        getClienteRepository().delete(cliente);
//    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Cliente> consult(){
        return getClienteRepository().consult();
    }
    
}
