/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Orcamento;
import com.tramppos.domain.Profissional;
import com.tramppos.domain.Servico;
import com.tramppos.repository.OrcamentoRepository;
import com.tramppos.repository.ServicoRepository;
import com.tramppos.util.mail.Mail;
import java.util.ArrayList;

import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author matheus
 */
public class OrcamentoService {
    
    private OrcamentoRepository orcamentoRepository;

    //contrutor
    public OrcamentoService() 
    {
        this.orcamentoRepository = new OrcamentoRepository();
    }

    //getter e setter
    public OrcamentoRepository getOrcamentoRepository() {
        return orcamentoRepository;
    }
    public void setOrcamentoRepository(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }
    
    // comandos
    public void insert(Orcamento orcamento) throws MessagingException{
        getOrcamentoRepository().insert(orcamento);
        
        this.mailNotifica(orcamento.getServico().getPessoa().getEmail(), orcamento.getServico().getTitulo());
    }    
    public void update(Orcamento orcamento){
        getOrcamentoRepository().update(orcamento);
    }
    public void delete(Orcamento orcamento){
        getOrcamentoRepository().delete(orcamento);
    }
    
    // utils
    private void mailNotifica(String destinatario,String tituloServico) throws MessagingException{
         Mail m = new Mail();
         
         m.notificacaoNovoOrcamento(destinatario, tituloServico);
    }
    
    public List<String> listMateriais(Orcamento orca){
        return this.listMateriais(orca.getMateriais());
    }
    private static List<String> listMateriais(String string){
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
    
    
    ///
    // Metodos que retornam lista
    ///
    public List<Orcamento> consult(){
        return getOrcamentoRepository().consult();
    }

    public List<Orcamento> consult(Profissional profissional) {
        return getOrcamentoRepository().consult(profissional);
    }

    public Orcamento consult(Profissional profissional, Servico servico) {
        return getOrcamentoRepository().consult(profissional,servico);
    }

    public Orcamento consult(int id) {
        return getOrcamentoRepository().consult(id);
    }

    public List<Orcamento> consult(Servico servico) {
        return getOrcamentoRepository().consult(servico);
    }

    
    // metodos utils
    
    //  Este metodo busca no banco se ja tem um orçamento selecionado para o
    //  serviço.
    //    1 - Caso tiver orçamento selecionado
    //            - tira todo o que esta selecionado
    //            - seta o novo orçamento como selecionado
    //    2 - Caso nao tiver orçamento selecionado
    //            - seta o novo orçamento como selecionado
    public void selecionarOrcamento(Orcamento orcamento){
        
        try {
            ServicoService servicoService = new ServicoService();
            //ServicoService servicoService = new ServicoService();

            //verifica se o serviço ja tem um orçamento SELECIONADO
            if(this.temOrcaSelecionado(orcamento.getServico())){
                // reativa serviço tirando o selecionado e deixando serviço ativo
                this.reativarServico(orcamento.getServico()); 

                // Seta o novo orçamento como selecionado
                orcamento.setSelecionado(true);
                this.update(orcamento);

                // muda status de serviço para em andamento
//                orcamento.getServico().setStatus(2);
//                servicoService.update(orcamento.getServico());


            }else{
                // Seta o novo orçamento como selecionado
                orcamento.setSelecionado(true);
                this.update(orcamento);
                // muda status de serviço para em andamento
//                orcamento.getServico().setStatus(2);
//                servicoService.update(orcamento.getServico());

            }
                try {
                    // Enviar email de notificaçao          
                    String[] dest = new String[100];            
                    dest[0]=orcamento.getServico().getPessoa().getEmail();// cliente
                    dest[1]=orcamento.getProfissional().getEmail();// profissional
                    
                    System.out.println("Dados de Email: Orçamento "+ orcamento.getId());
                    System.out.println("                Destinatarios: ");
                    System.out.println("                               "+dest[0]);
                    System.out.println("                               "+dest[1]);

                    this.mailNotificaSelecionado(dest,orcamento.getId());
                    
                    
                } catch (Exception e) {
                    e.printStackTrace();
            }       
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void mailNotificaSelecionado(String[] dest,int idOrca) throws MessagingException{
        // Enviar email de notificaçao
        Mail m = new Mail();
        
        m.notificacaoEscolha(dest,idOrca);
    }
    
    public boolean temOrcaSelecionado(Servico servico) {        
        // se nao tiver orçamento selecionado
        if(getOrcamentoRepository().consultSelecionado(servico).isEmpty())
            return false;
        else
            return true;       
    }
    
    
    //  Metodo recusa orçamento selecionado e muda Status do servico para = Ativo
    public void reativarServico(Servico servico){
        ServicoService servicoService = new ServicoService();
        
        System.out.println("Servico: " + servico.toString());
        
        // tira selecionado se tiver
        getOrcamentoRepository().tiraSelecionado(servico);
        
        //muda status para ativo
//        servico.setStatus(1);
//        return servicoService.update(servico);
    }
    
    public void tiraSelecionados(Servico servico){
        getOrcamentoRepository().tiraSelecionado(servico);
    }
    
}
