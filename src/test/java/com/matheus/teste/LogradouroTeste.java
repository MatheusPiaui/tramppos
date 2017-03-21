/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.service.LogradouroService;

/**
 *
 * @author matheus
 */
public class LogradouroTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        LogradouroService logradouroService =new LogradouroService();
        
        System.out.println(logradouroService.consult("Valdevino Pereira","Rua"));
    }
    
}
