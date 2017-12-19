/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.cliente;

import com.tramppos.controller.applicationScoped.FotoControllerAppliScoped;
import com.tramppos.domain.Foto;
import com.tramppos.domain.Pessoa;
import com.tramppos.util.jsf.SessionUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author matheus
 */
@ManagedBean
@javax.faces.bean.RequestScoped
public class ServicosClienteController {

    private List<Foto> listaFotos;

    @ManagedProperty("#{fotoControllerAppliScoped}")
    private FotoControllerAppliScoped fotoControllerAppliScoped;
    private Pessoa pessoa;
    private int status;
    
    public ServicosClienteController() {
    }
    
    /**
     *
     * @throws IOException
     */
    @PostConstruct
    public void start(){
        // carrega lista
        this.fotoControllerAppliScoped.carregar();
        
        this.pessoa = (Pessoa) SessionUtil.getParam("logPessoa"); 
//        this.fotos(1);

        try {
            
            // Pegando objeto do FlashScoped        
            this.status = (int) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flashStatus");
            
            if(status >= 1 && status <= 3 ){
                this.fotos(status);
                FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashStatus", status); // incluindo um objeto no escopo Flash
            }else{
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            
                this.fotos(1);
//                FacesContext.getCurrentInstance().getExternalContext().redirect("servicosativos.xhtml");
//           
//                printStackTrace();
            
            
        } 
    }
    
    //  Utils ------------------------------------------------------------------
    public void fotos(int status){
        this.listaFotos = new ArrayList<>();
        this.listaFotos = this.fotoControllerAppliScoped.fotos(this.pessoa, status);
    }
    

    //  Links ------------------------------------------------------------------
    public void doServicosAtivos() throws IOException{
//        this.fotos(1);
        int status = 1;
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashStatus", status); // incluindo um objeto no escopo Flash
                
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosativos.xhtml");
    }
    public void doServicosAndamento() throws IOException{
        //        this.listaServico = new ArrayList<>();
        //        this.listServico(2);
//        this.fotos(2);
        int status = 2;
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashStatus", status); // incluindo um objeto no escopo Flash
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosandamento.xhtml");
    }
    public void doServicosFinalizados() throws IOException{
//        this.fotos(3);
        int status = 3;
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashStatus", status); // incluindo um objeto no escopo Flash
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosfinalizado.xhtml");
    }
    public void doUpdate(Foto foto) throws IOException{
//        this.listProfissaoEdit();
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashFoto", foto); // incluindo um objeto no escopo Flash
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosedit.xhtml");        
    }
    public void doVerOrcamentos(Foto foto) throws IOException{
//        this.listOrcamentoPorServico();
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashFoto", foto); // incluindo um objeto no escopo Flash
        FacesContext.getCurrentInstance().getExternalContext().redirect("orcamentos.xhtml");
    }
    
    
    
    
    //      Getters e Setters --------------------------------------------------
    
    public void setFotoControllerAppliScoped(FotoControllerAppliScoped fotoControllerAppliScoped) {
        this.fotoControllerAppliScoped = fotoControllerAppliScoped;
    }

    public List<Foto> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }
    
}
