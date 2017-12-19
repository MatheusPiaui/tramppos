/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.matheus.util.Util;
import com.tramppos.domain.Cliente;
import com.tramppos.domain.Endereco;
import com.tramppos.service.ClienteService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ClienteTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Cliente cliente = new Cliente();
            ClienteService clienteService = new ClienteService();




            ///
            //  inserir
            //

            cliente = clienteService.consult("jefferson.marchi@hotmail.com");

    //        cliente.setId(28);
    //        cliente.setNome("Matheus Cliente");
    //        cliente.setEmail("teste@Cliente.com");
    //        cliente.setSenha("123");
    //        cliente.setTelefone("123456789");
            //
            cliente.setDiscrimina(2);
    //        cliente.setNomeFantasia(null);
    //        cliente.setNota(0);
    //        cliente.setRaioAtendimento(0);

    //        clienteService.insert(cliente);
            clienteService.update(cliente);


    //        ///
    //        //  inserir - Com endereço
    //        //
    //        Endereco endereco1 = new Endereco();
    //        Endereco endereco2 = new Endereco();
    //        List<Endereco> listaEnd = new ArrayList<>();
    //        
    //        // 1
    //        endereco1.setId(1);
    //        // 2
    //        endereco2.setId(2);
    //        
    //        listaEnd.add(endereco2);
    //        listaEnd.add(endereco1);                


    //        cliente.setNome("Matheus Piaui - cliente");
    //        cliente.setEmail("matheus@piaui.com");
    //        cliente.setSenha("123");
    //        cliente.setTelefone(null);
    //        //cliente.setEnderecos(listaEnd);
    //        
    //        clienteService.insert(cliente);

            //Consulta

            //Util.printList((List<Object>) (Object) clienteService.consult());

    //        System.out.println(clienteService.consult("igor@gordo.com"));
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
    
}
