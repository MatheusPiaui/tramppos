/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.domain.Bairro;
import com.tramppos.repository.BairroRepository;
import com.tramppos.service.BairroService;
import com.matheus.util.Util;
import java.util.List;

/**
 *
 * @author matheus
 */
public class BairroTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Bairro bairro = new Bairro();
        //BairroRepository bairroRepository = new BairroRepository();
        BairroService bairroService = new BairroService();
        
      
        // inserir
        bairro.setNome("Portal");
        //bairroRepository.insert(bairro);
        //bairroService.insert(bairro);
        
        //alterar
        bairro.setId(1);
        bairro.setNome("Portal da Perola");
        //bairroService.update(bairro);
        
        //deletar
       
        //bairroService.delete(bairro);
        
        
        
        ////
        //  Teste de de Consultas
        ///
        // Lista todos os dados
               
        //Util.printList((List<Object>) (Object) bairroService.consult());
    }
    
    
    
}
