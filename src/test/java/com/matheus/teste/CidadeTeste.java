/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import com.tramppos.service.CidadeService;
import com.matheus.util.Util;
import java.util.List;

/**
 *
 * @author matheus
 */
public class CidadeTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Cidade cidade = new Cidade();        
        CidadeService cidadeService = new CidadeService();
        //
        Estado estado = new Estado();
        
        
        ///
        // inserir
        ///
        estado.setId(67);
//        estado.setNome("Bahia");
//        estado.setSigla("BA");
        //
        cidade.setNome("");        
        cidade.setEstado(estado);
        //cidadeService.insert(cidade); 
        
        ///
        // alterar
        ///
        estado.setId(4);
        cidade.setId(6);
        cidade.setNome("Rio de Janeiro");
        cidade.setEstado(estado);
       // cidadeService.update(cidade);
        
        
        ///
        //lista
        //Util.printList((List<Object>) (Object) cidadeService.consult());
        ///
        // lista por estado
        estado.setId(67);
        Util.printList((List<Object>) (Object) cidadeService.consult(estado));

    }
    
}
