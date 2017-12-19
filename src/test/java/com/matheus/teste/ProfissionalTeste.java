/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.matheus.util.Util;
import com.tramppos.domain.Profissional;
import com.tramppos.service.ProfissionalService;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ProfissionalTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Profissional profissional = new Profissional();
        ProfissionalService profissionalService = new ProfissionalService();
        
        
        ///
        //  inserir
        //
//        profissional.setId(9);
        profissional.setNome("profissional");
        profissional.setEmail("prof@profissional.com");
        profissional.setSenha("123");
        profissional.setTelefone("12341234987");
        //
        //profissional.setDiscrimina(1);
//        profissional.setNomeFantasia("Dodo");
//        profissional.setNota(0);
//        profissional.setRaioAtendimento(10);
        
//        profissionalService.insert(profissional);
//        profissionalService.update(profissional);
        
//        Util.printList((List<Object>) (Object) profissionalService.consult());

        System.out.println("Teste: " + profissionalService.consult("teste@pessoa.com"));
        
        
        
    }
    
}
