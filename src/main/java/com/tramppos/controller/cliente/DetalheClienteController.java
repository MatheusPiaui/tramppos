/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.cliente;

import com.tramppos.domain.Foto;
import com.tramppos.domain.Orcamento;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Servico;
import com.tramppos.service.FotoService;
import com.tramppos.service.OrcamentoService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ServicoService;
import com.tramppos.util.jsf.SessionUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author matheus
 */
@Named(value = "detalheClienteController")
@RequestScoped
public class DetalheClienteController implements Serializable {
   
    private Orcamento orcamento = new Orcamento();
    private Pessoa pessoa;
    
    
    @PostConstruct
    public void start(){
        try {
            PessoaService pessoaService = new PessoaService();
            this.pessoa = (Pessoa) SessionUtil.getParam("logPessoa"); 

            this.orcamento = new Orcamento();
            OrcamentoService orcamentoService = new OrcamentoService();
            
            // get
            String id = "";
            id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            
            if(id == null || id == ""){
                this.orcamento = orcamentoService.consult(this.orcamento.getId());

            }
            else{
                this.orcamento = orcamentoService.consult(Integer.parseInt(id));
            }   
            
            //get
            String finaliza = "";
            finaliza = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("finaliza");
            
            if(finaliza != null && finaliza != "")
                this.finalizarServico();
            
            try {
                // Pegando objeto do FlashScoped        
                Foto foto = (Foto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flashFoto");
                FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashFoto", foto); // incluindo um objeto no escopo Flash
            } catch (Exception e) {
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }       
    }

    
     //  Metodo retorna caminho da imagem do servico
    public String getImgServico() {
        FotoService fotoService = new FotoService();
        Foto f = new Foto();
        
        try {
            if(this.orcamento != null){
                f = fotoService.consult(this.orcamento.getServico());

            }else{                
                System.out.println("ENTROU SERVICO NULO");
            }
            
            if (f != null) {
                String str = fotoService.linkImg(f);
                if (str != null) {
                    return str;
                }                
            }    
            
            return "";
        } catch (Exception e) { 
            e.printStackTrace();
            return "";
        }
    }
    
    public void finalizarServico(){       
        
        try {
            ServicoService servicoService = new ServicoService();
            
            this.orcamento.getServico().setStatus(3);
            servicoService.update(this.orcamento.getServico());
            
            System.out.println("ORCA.:" + this.orcamento);
            
//            return "detalhes.xhtml?id=" + orcamento.getId();
//            FacesContext.getCurrentInstance().getExternalContext().redirect("Web/paginas/cliente/detalhes.xhtml?id=" + this.orcamento.getId());
//            NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
//            navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "detalhes.xhtml?id=" + this.orcamento.getId());

            
        } catch (Exception e) {            
            e.printStackTrace();
//            return "";
        }       
    }
    
    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
   
}
