/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.cliente;

import com.tramppos.controller.applicationScoped.FotoControllerAppliScoped;
import com.tramppos.controller.applicationScoped.OrcamentoControllerAppliScoped;
import com.tramppos.controller.applicationScoped.ProfissaoControllerAppliScoped;
import com.tramppos.domain.Categoria;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Foto;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Profissao;
import com.tramppos.domain.Servico;
import com.tramppos.service.CategoriaService;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.FotoService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ProfissaoService;
import com.tramppos.service.ServicoService;
import com.tramppos.util.jsf.SessionUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author matheus
 */
@ManagedBean
@RequestScoped
public class EditServicoClienteController {

    private Foto foto;
    private List<Profissao> listaProfissao;
    
    @ManagedProperty("#{profissaoControllerAppliScoped}")
    private ProfissaoControllerAppliScoped profissaoControllerAppliScoped = new ProfissaoControllerAppliScoped();
    
    @ManagedProperty("#{orcamentoControllerAppliScoped}")
    private OrcamentoControllerAppliScoped orcamentoControllerAppliScoped = new OrcamentoControllerAppliScoped();
   
    @ManagedProperty("#{fotoControllerAppliScoped}")
    private FotoControllerAppliScoped fotoControllerAppliScoped = new FotoControllerAppliScoped();
    
    private Part arquivoEdit;
    
    @PostConstruct
    public void start(){
        // Pegando objeto do FlashScoped        
        this.foto = (Foto) FacesContext.getCurrentInstance()
                                      .getExternalContext()
                                      .getFlash().get("flashFoto");
        
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().put("flashFoto", this.foto); // incluindo um objeto no escopo Flash
        
        this.listProfissao();
    }

    //      Utils       --------------------------------------------------------
    public void listProfissao(){
//        ProfissaoService profissaoService = new ProfissaoService();
        
//        this.listaProfissao = profissaoService.consult(this.foto.getServico().getProfissao().getCategoria());
       
        this.listaProfissao = this.profissaoControllerAppliScoped.profissoes(this.foto.getServico().getProfissao().getCategoria());
//        System.out.println(this.listaProfissao);
    } 
    
    //    Metodos update
    ///
    public String update(){
        
//        System.out.println("Serviço Edit: " + servicoEdit.toString());
//        this.categoria = servicoEdit.getProfissao().getCategoria();       
        try {
            ServicoService servicoService = new ServicoService();
            
            servicoService.update(this.foto.getServico());
//            servicoService.update(this.servicoEdit);
            
            this.fotoControllerAppliScoped.carregar();
            this.orcamentoControllerAppliScoped.carregar();
            
//            FacesContext.getCurrentInstance().getExternalContext().redirect("servicosativos.xhtml");
            return "servicosativos.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        
    }
    
     public void updateFoto() throws IOException{
        
        FotoService fotoService = new FotoService();
        
        try {
            
            if(this.arquivoEdit != null){
                //alterar imagem do serviço
                
                fotoService.update(this.arquivoEdit, this.foto);
                
                this.fotoControllerAppliScoped.carregar();
                this.orcamentoControllerAppliScoped.carregar();
            }        

            FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().put("flashFoto", this.foto); // incluindo um objeto no escopo Flash
            

            FacesContext.getCurrentInstance().getExternalContext().redirect("servicosedit.xhtml");
//            return "servicosativos.xhtml#editarServico?faces-redirect=true";
            
        } catch (Throwable t){
            t.printStackTrace();
//            return "";
        }
    }
    
    
    
    
    //      Links       --------------------------------------------------------
    public void doUpdateFoto() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicoseditfoto.xhtml");
    }    
    
    
    //      Getters & Setters  -------------------------------------------------

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public List<Profissao> getListaProfissao() {
        return listaProfissao;
    }

    public void setProfissaoControllerAppliScoped(ProfissaoControllerAppliScoped profissaoControllerAppliScoped) {
        this.profissaoControllerAppliScoped = profissaoControllerAppliScoped;
    }

    public void setOrcamentoControllerAppliScoped(OrcamentoControllerAppliScoped orcamentoControllerAppliScoped) {
        this.orcamentoControllerAppliScoped = orcamentoControllerAppliScoped;
    }

    public void setFotoControllerAppliScoped(FotoControllerAppliScoped fotoControllerAppliScoped) {
        this.fotoControllerAppliScoped = fotoControllerAppliScoped;
    }

    public Part getArquivoEdit() {
        return arquivoEdit;
    }

    public void setArquivoEdit(Part arquivoEdit) {
        this.arquivoEdit = arquivoEdit;
    }
    
}
