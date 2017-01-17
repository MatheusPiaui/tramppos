/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.matheus.util.Util;
import com.tramppos.domain.Pessoa;
import com.tramppos.service.PessoaService;
import java.util.List;

/**
 *
 * @author matheus
 */
public class PessoaTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Pessoa pessoa = new Pessoa();
        PessoaService pessoaService = new PessoaService();
        
        
        ///inserir
        //
        pessoa.setNome("teste Pessoa");
        pessoa.setEmail("matheus@gmail.com");
        pessoa.setSenha("123");
        pessoa.setTelefone(null);
        
        
        
        //pessoaService.insert(pessoa);
        
        //Util.printList((List<Object>) (Object) pessoaService.consult());
        
        System.out.print(pessoaService.consult("teste@Cliente.com")); 
        
        System.out.print(pessoaService.autenticar("teste@Cliente.com", "123"));
        
        
    }
    
}
