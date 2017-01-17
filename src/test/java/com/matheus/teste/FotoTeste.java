/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.domain.Foto;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Servico;
import com.tramppos.service.FotoService;
import java.util.Date;

/**
 *
 * @author matheus
 */
public class FotoTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Foto foto = new Foto(); 
        
        Servico servico = new Servico();
        Pessoa pessoa = new Pessoa();
        
        FotoService fotoService = new FotoService();
        
        servico.setId(1);
        pessoa.setId(1);
        
        foto.setPessoa(pessoa);
        foto.setServico(servico);
        foto.setDescricao("teste");
        foto.setTitulo("Teste Titulo");
        foto.setLink("teste.jpg");
        
        Date data = new Date(System.currentTimeMillis());
        System.out.println("Data: " + data);
        
        foto.setDataPestagem(data);
        
        
        fotoService.insert(foto);
        
        
    }
    
}
