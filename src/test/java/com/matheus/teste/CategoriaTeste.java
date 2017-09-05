/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.domain.Categoria;
import com.tramppos.service.CategoriaService;

/**
 *
 * @author matheus
 */
public class CategoriaTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Categoria categoria = new Categoria();
        CategoriaService categoriaService = new CategoriaService();
        
        categoria.setNome("Construçao");
        categoria.setDescricao("Toda e qualquer profissao que se relacione com algum"
                + "processo de construçao de imoveis");
        
//        categoriaService.insert(categoria);
        
//        categoriaService.consult()
    }
    
}
