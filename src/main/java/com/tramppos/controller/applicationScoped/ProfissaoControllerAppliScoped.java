/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.applicationScoped;

import com.tramppos.domain.Categoria;
import com.tramppos.domain.Profissao;
import com.tramppos.domain.Profissional;
import com.tramppos.domain.Servico;
import com.tramppos.service.ProfissaoService;
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
public class ProfissaoControllerAppliScoped implements Serializable{

    private List<Profissao> listaProfissao = new ArrayList<>();
    
    public ProfissaoControllerAppliScoped() {
        this.carregar();
    }

    
    public void carregar(){
        try {
            ProfissaoService profissaoService = new ProfissaoService();
            
            this.listaProfissao = new ArrayList<>();
            
            this.listaProfissao = profissaoService.consult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //      Listas -------------------------------------------------------------
    public List<Profissao> profissoes(Categoria categoria){
        try {
            List<Profissao> lista = new ArrayList<>();

            for(Profissao i:this.listaProfissao){
                if(i.getCategoria().getId() == categoria.getId()){
                    lista.add(i);
                }
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    //    Getters e Setters  ---------------------------------------------------

    public List<Profissao> getListaProfissaos() {
        return listaProfissao;
    }    
}
