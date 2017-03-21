/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.domain.Bairro;
import com.tramppos.domain.Cep;
import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Logradouro;
import com.tramppos.service.CepService;
import com.matheus.util.Util;
import java.time.Clock;
import java.util.List;

/**
 *
 * @author matheus
 */
public class CepTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Cep cep = new Cep();        
        CepService cepService = new CepService();
        //
        Cidade cidade = new Cidade();
        Bairro bairro = new Bairro();
        Logradouro logradouro = new Logradouro();
        
        
        ///
        // inserir
        ///
        cidade.setId(8);//birigui
        bairro.setId(2);//Joao crevelaro
        logradouro.setId(3);//Valdevino Pereira
        
        cep.setNumeroCep("16202338");
        cep.setBairro(bairro);
        cep.setCidade(cidade);
        cep.setLogradouro(logradouro);
        
        //cepService.insert(cep);
        
        ///
        // alterar
        ///
       
       // cepService.update(cep);
        
        
        ///
        //lista
        //Util.printList((List<Object>) (Object) cepService.consult());
        
        // Busca CEP
        
//        System.out.println(cepService.buscaCep("16202000"));
        // ex que nao existe: 16202003;
        // cep sem bairro e logradouro: 16265000
        System.out.println(cepService.consult("16202338"));
        
    }
    
}
