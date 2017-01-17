/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.tramppos.domain.Orcamento;
import com.tramppos.domain.Profissional;
import com.tramppos.domain.Servico;
import com.tramppos.repository.OrcamentoRepository;
import com.tramppos.service.OrcamentoService;
import java.sql.Time;

/**
 *
 * @author matheus
 */
public class OrcamentoTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Orcamento orcamento = new Orcamento();
        OrcamentoService orcamentoService = new OrcamentoService();
        
        Servico servico = new Servico();
        Profissional profissional = new Profissional();
        
        servico.setId(1);
        profissional.setId(4);
        
        
//        Time tempoPrevisto = new Time(25,30,0);        
        
        orcamento.setValor(55.56789879);
        orcamento.setMateriais("teste");
        orcamento.setSelecionado(false);
        orcamento.setTempoPrevisto(28);
        orcamento.setServico(servico);
        orcamento.setProfissional(profissional);
        
        //orcamentoService.insert(orcamento);
        
    }
    
}
