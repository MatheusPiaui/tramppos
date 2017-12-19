/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.applicationScoped;

import com.tramppos.domain.Orcamento;
import com.tramppos.domain.Profissional;
import com.tramppos.domain.Servico;
import com.tramppos.service.OrcamentoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author matheus
 */
@ManagedBean(eager = true)
@javax.faces.bean.ApplicationScoped
public class OrcamentoControllerAppliScoped implements Serializable{

    private List<Orcamento> listaOrcamentos = new ArrayList<>();
    
    public OrcamentoControllerAppliScoped() {
        this.carregar();
    }

    
    public void carregar(){
        try {
            OrcamentoService orcamentoService = new OrcamentoService();
            
            this.listaOrcamentos = new ArrayList<>();
            
            this.listaOrcamentos = orcamentoService.consult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //      Listas -------------------------------------------------------------
    public List<Orcamento> orcamentos(Profissional profissional){
        try {
            List<Orcamento> lista = new ArrayList<>();

            for(Orcamento i:this.listaOrcamentos){
                if(i.getProfissional().getId() == profissional.getId()){
                    lista.add(i);
                }
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Orcamento> orcamentos(Servico servico){
        try {
            List<Orcamento> lista = new ArrayList<>();

            for(Orcamento i:this.listaOrcamentos){
                if(i.getServico().getId() == servico.getId()){
                    lista.add(i);
                }
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }               
    }
    
    
    //  Utils  -----------------------------------------------------------------
    public boolean temSelecionado(Servico servico){
        OrcamentoService orcamentoService = new OrcamentoService();
        
        if(orcamentoService.temOrcaSelecionado(servico)){
            return true;
        }else{
            return false;
        }
    }
    public List<String> listMateriais(Orcamento orca){
//        this.materiais = new ArrayList<>();
        OrcamentoService orcamentoService = new OrcamentoService();  
        
        return orcamentoService.listMateriais(orca);
    }
    
    
    
    
    //    Getters e Setters  ---------------------------------------------------

    public List<Orcamento> getListaOrcamentos() {
        return listaOrcamentos;
    }    
}
