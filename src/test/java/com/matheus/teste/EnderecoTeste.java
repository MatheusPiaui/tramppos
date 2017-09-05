/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.domain.Bairro;
import com.tramppos.domain.Cep;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Logradouro;
import com.tramppos.service.EnderecoService;
import com.matheus.util.Util;
import com.tramppos.domain.Pessoa;
import java.util.List;

/**
 *
 * @author matheus
 */
public class EnderecoTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        EnderecoService enderecoService = new EnderecoService();
        
        Endereco endereco = new Endereco();
        
        Cep cep = new Cep();
        Bairro bairro = new Bairro();
        Logradouro logradouro = new Logradouro();
        
        ///
        //inserir
        ///
        // para inserir e necessario associar um bairro e um logradouro, mesmo que esse estejam no cep
        // entao e necessario um metodo que fassa a busca dos dados de CEP para preencher bairro e logradouro
        // se esse metodo retornar dados nulos para logradouro e/ou bairro, o preenchimento 
        // deve ser manual(caso em que o CEP identifica apenas a cidade)
        ///
        //bairro.setId(2);
        cep.setId(2);
//      bairro.setId(2);
//      logradouro.setId(3);
        
        endereco.setTipo("Trabalho");
        endereco.setNumero("21");
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setLogradouro(logradouro);
        
//        enderecoService.insert(endereco);
        
//        Util.printList((List<Object>) (Object) enderecoService.consult());
        

        Pessoa pessoa = new Pessoa();
        
        pessoa.setId(23);
        
        Util.printList((List<Object>) (Object) enderecoService.consult(pessoa));

    }
    
}
