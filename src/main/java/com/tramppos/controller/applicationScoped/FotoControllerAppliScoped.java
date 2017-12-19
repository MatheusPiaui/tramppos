/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.applicationScoped;

import com.tramppos.domain.Bairro;
import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Foto;
import com.tramppos.domain.Pessoa;
import com.tramppos.service.FotoService;
import com.tramppos.util.upload.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author matheus
 */
@ManagedBean(eager = true)
@javax.faces.bean.ApplicationScoped
public class FotoControllerAppliScoped implements Serializable{

    private List<Foto> listaFotos = new ArrayList<>();
           

    public FotoControllerAppliScoped() {
        
        this.carregar();
    }
    
    public void carregar(){
        try {
            FotoService fotoService = new FotoService();
            listaFotos = new ArrayList<>();
            
            listaFotos = fotoService.consult();
                        
        } catch (Exception e) {
        }
    }
    
    /// Listas------------------------------------------------------------------
    
    public List<Foto> fotos(Pessoa pessoa,int status){
        try {
            List<Foto> lista = new ArrayList<>();

            for(Foto f:this.listaFotos){
                if(f.getPessoa().getId() == pessoa.getId() && 
                   f.getServico().getStatus() == status){
                    lista.add(f);
                }
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Foto> fotos(int status){
        try {
            List<Foto> lista = new ArrayList<>();

            for(Foto f:this.listaFotos){
                if(f.getServico().getStatus() == status){
                    lista.add(f);
                }
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Foto> fotos(Estado estado,int status){
        try {
            List<Foto> lista = new ArrayList<>();

            for(Foto f:this.listaFotos){
                if(f.getServico().getStatus() == status &&
                   f.getServico().getEndereco().getCep().getCidade().getEstado().getId() == estado.getId()){
                    lista.add(f);
                }
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Foto> fotos(Cidade cidade,int status){
        try {
            List<Foto> lista = new ArrayList<>();

            for(Foto f:this.listaFotos){
                if(f.getServico().getStatus() == status &&
                   f.getServico().getEndereco().getCep().getCidade().getId() == cidade.getId()){
                    lista.add(f);
                }
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Foto> fotos(Bairro bairro, int status) {
       try {
            List<Foto> lista = new ArrayList<>();

            for(Foto f:this.listaFotos){
                if(f.getServico().getStatus() == status &&
                   f.getServico().getEndereco().getBairro().getId() == bairro.getId()){
                    lista.add(f);
                }
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }
    
    
    
    //utils---------------------------------------------------------------------
    public String linkImg(Foto foto){
        try {
            Image image = new Image();
                       
            return image.linkImg(foto.getLink());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }     
        
    }
        
        
        
        
    
    
    /// getters e setters ------------------------------------------------------

    public List<Foto> getListaFotos() {
        return listaFotos;
    }

    
    
}
