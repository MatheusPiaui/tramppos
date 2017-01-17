/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.matheus.util.Util;
import com.tramppos.domain.Cliente;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Servico;
import com.tramppos.service.ServicoService;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.ResourceHandler;

/**
 *
 * @author matheus
 */
public class ServicoTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Servico servico = new Servico();
        ServicoService servicoService = new ServicoService();
        
        // insert
        servico.setDescricao("Rebobar parede");
        
        // data        
        Date data = new Date(System.currentTimeMillis());
        System.out.println(data);
//        servico.setDataSolicitacao(data);
        
        Cliente cliente = new Cliente();
        cliente.setId(2);
//        servico.setIdCliente(cliente);
        
        Endereco endereco = new Endereco();
        endereco.setId(1);
//        servico.setIdEndereco(endereco);
        
        //servicoService.insert(servico);   
        
        
        // update
        servico.setId(1);
        servico.setDescricao("Rebocar parede");
        
        // data        
//        servico.setDataSolicitacao(data);
// 
//        servico.setIdCliente(cliente);
//        servico.setIdEndereco(endereco);
        //servicoService.update(servico);

        //System.out.println("Teste: " + data);
        
        //Util.printList((List<Object>) (Object) servicoService.consult());
        
        System.out.print(ResourceHandler.RESOURCE_IDENTIFIER);
    }
    
}
