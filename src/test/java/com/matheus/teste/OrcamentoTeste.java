/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.teste;

import com.matheus.util.Util;
import com.tramppos.domain.Orcamento;
import com.tramppos.domain.Profissional;
import com.tramppos.domain.Servico;
import com.tramppos.repository.OrcamentoRepository;
import com.tramppos.service.OrcamentoService;
import com.tramppos.service.ServicoService;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
        OrcamentoRepository orcamentoRepository = new OrcamentoRepository();
        
        Servico servico = new Servico();
        Profissional profissional = new Profissional();
        
//        orcamento.setId(0);
//        servico.setId(4);
//        profissional.setId(23);
        ServicoService servicoService = new ServicoService();
        servico = servicoService.consultId(4);
        
//        Time tempoPrevisto = new Time(25,30,0);        
        
//        orcamento.setValor(55.56789879);
//        orcamento.setMateriais("teste");
//        orcamento.setSelecionado(false);
//        orcamento.setTempoPrevisto(28);
//        orcamento.setServico(servico);
//        orcamento.setProfissional(profissional);
        
        //orcamentoService.insert(orcamento);
        
//        System.out.println("O que retorna: "+orcamentoService.consult(profissional,servico));
        
//        orcamento = orcamentoService.consult(2);
//        System.out.println(orcamento);
//        System.out.println(orcamentoService.listMateriais(orcamento));
        
//        Util.printList((List<Object>) (Object) orcamentoService.listMateriais(orcamento));
//        System.out.println(listMateriais(";"));
        
//        orcamentoService.reativarServico(servico);
//        orcamentoRepository.tiraSelecionado(servico);
        
//        System.out.println("Resultado: "+orcamentoService.temOrcaSelecionado(servico));
        
//        orcamentoService.tiraSelecionados(servico);
        
//        System.out.println("Resultado: "+orcamentoService.temOrcaSelecionado(servico));
//        Util.printList((List<Object>) (Object) orcamentoRepository.consultSelecionado(servico));

        
        //teste de notificaçao por email        
        try {
            String[] dest = new String[100];            
            dest[0] = "matheus.piaui.iami@gmail.com";
            dest[1] = "matheus.piaui.mail.01@gmail.com";

    //        System.out.println("Resultado: "+m.notificacaoEscolha(dest, 7));
        
            orcamentoService.mailNotificaSelecionado(dest, 7);
            
        } catch (Exception e) {
        }
        

        
    }
    
    public static List<String> listMateriais(String string){
        List<String> lista = new ArrayList<>();
        
        String arr = string;
        String aux = "";
        
        int indexEnd,indexStart;
        indexEnd = arr.indexOf(';');
        indexStart = 0;       
        
        while(indexEnd != -1){
//            System.out.println(arr.substring(indexStart, indexEnd));
//            System.out.println("start: " + indexStart);
//            System.out.println("end:   " + indexEnd);
            aux = arr.substring(indexStart, indexEnd);
            lista.add(aux);
//            arr = arr.substring(indexEnd);
//            System.out.println("SubString: "+arr);
            indexStart = indexEnd+1;
            indexEnd = arr.indexOf(';', indexEnd+1);
        }     
        return lista;
    }
    
}
