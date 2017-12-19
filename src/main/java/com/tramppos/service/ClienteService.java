/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;


import com.tramppos.domain.Cliente;
import com.tramppos.repository.ClienteRepository;
import static com.tramppos.util.hash.GeraHash.*;
import com.tramppos.util.mail.Mail;
import java.util.List;
import javax.mail.MessagingException;

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
    public void insert(Cliente cliente) {
        
       
        
        // Gera UID(hash)
        
        cliente.setUid(stringHexa(gerarHash(cliente.getEmail(), "MD5")));
        
        // senha
        cliente.setSenha(stringHexa(gerarHash(cliente.getSenha(), "MD5")));
        
        //Verifica se:
        //     email    => unico
        //     telefone => unico
        
        
        // antes de inserir, mandar email de validaçao
        try {
            // envia email
            Mail m = new Mail();
            
            //System.out.println("## email: "+ m.getEndMail());  
            //System.out.println("## "+ m.sendMail("matheus.piaui.iami@gmail.com", "teste ", "teste"));
            
            System.out.println(" ## Email enviado: " + m.validaCadastro(cliente.getEmail(),cliente.getUid()));
            
            //Se estiver tudo certo manda inserir! 
            getClienteRepository().insert(cliente);  
            
        } catch (MessagingException e) {
                  throw new RuntimeException(e);
        }
        
              
        
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
    
    public Cliente consult(String mail){
        return getClienteRepository().consult(mail);
    }
}
