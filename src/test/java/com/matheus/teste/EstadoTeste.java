/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.domain.Estado;
import com.tramppos.repository.EstadoRepository;
import com.tramppos.service.EstadoService;
import com.matheus.util.Util;
import java.util.List;

/**
 *
 * @author matheus
 */
public class EstadoTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Estado estado = new Estado();
        //EstadoRepository estadoRepository = new EstadoRepository();
        EstadoService estadoService = new EstadoService();
        
      
        // inserir
        //estado.setNome("Distrito Federal");
        //estado.setSigla("DF");       
        //estadoRepository.insert(estado);
        //estadoService.insert(estado);
        
        //alterar
        //estado.setId(6);
        //estado.setNome("Basilia");
        //estado.setSigla("DF");
        //estadoService.update(estado);
        
        //deletar
        //estado.setId(1);
        //estadoService.delete(estado);
        
        
        
        ////
        //  Teste de de Consultas
        ///
        // Lista todos os dados
        //List<Estado> listaEstado = estadoService.consult();        
        //Util.printList((List<Object>) (Object) listaEstado);
        
        //System.out.println(estadoService.consult(67));
        
        /// consulta passando estado
        //estado.setId(0);
        //estado.setNome("Piaui");
        //estado.setSigla(null);
        System.out.println(estadoService.consult("SP"));
        System.out.println(estadoService.consult(1));

    }
    
    
    
}
