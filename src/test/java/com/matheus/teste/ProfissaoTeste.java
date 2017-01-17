/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.domain.Categoria;
import com.tramppos.domain.Profissao;
import com.tramppos.service.ProfissaoService;

/**
 *
 * @author matheus
 */
public class ProfissaoTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Profissao profissao =new Profissao();
        ProfissaoService profissaoService = new ProfissaoService();
        Categoria categoria =new Categoria();
        
        categoria.setId(1);
        
        profissao.setCategoria(categoria);        
        profissao.setNome("Pedreiro");
        profissao.setDescricao("Construçao Civil");
        
        // insert
        profissaoService.insert(profissao);
        
        
        
    }
    
}
