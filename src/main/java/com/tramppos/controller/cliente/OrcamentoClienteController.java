/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.cliente;

import com.tramppos.controller.applicationScoped.FotoControllerAppliScoped;
import com.tramppos.controller.applicationScoped.OrcamentoControllerAppliScoped;
import com.tramppos.domain.Foto;
import com.tramppos.domain.Orcamento;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Servico;
import com.tramppos.service.OrcamentoService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ProfissionalService;
import com.tramppos.service.ServicoService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;

import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author matheus
 */
@ManagedBean
@ViewScoped
public class OrcamentoClienteController implements Serializable{

    
    private Orcamento orcamento;
    private Servico servico;
    
    private List<Orcamento> listaOrcamento;
    private List<String> materiais;
    private Foto foto = new Foto();
    
    @ManagedProperty("#{orcamentoControllerAppliScoped}")
    private OrcamentoControllerAppliScoped orcamentoControllerAppliScoped = new OrcamentoControllerAppliScoped();
    @ManagedProperty("#{fotoControllerAppliScoped}")
    private FotoControllerAppliScoped fotoControllerAppliScoped = new FotoControllerAppliScoped();
    
    @PostConstruct
    public void start(){
        
        //  atualizar listas
        this.atualizarListas();
        
        try {
            // Pegando objeto do FlashScoped        
            this.foto = (Foto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flashFoto");
        } catch (Exception e) {
            e.printStackTrace();
        }           
            
            
        
        this.servico = new Servico();
        this.orcamento = new Orcamento();
        this.materiais = new ArrayList<>();
        
        this.listOrcamentoPorServico();
        
    }
    
    public void atualizarListas(){
        //  atualizar listas
        this.orcamentoControllerAppliScoped.carregar();
        this.fotoControllerAppliScoped.carregar();
    }
    
    public void escolherOrcamento(Orcamento orcamento) throws IOException{
        
        try {
            OrcamentoService orcamentoService = new OrcamentoService();

            //verifica se o serviço ja tem um orçamento SELECIONADO
            if(orcamentoService.temOrcaSelecionado(orcamento.getServico())){
                // se ja tiver
                // mandar mensagem se o cliente deseja recusar
                // o orçamento antigo e selecionar este novo
                // Reativar serviço

                orcamentoService.selecionarOrcamento(orcamento);
                
                //  atualizar listas
                this.atualizarListas();
                
                
                System.out.println("ORÇA: " + orcamento);
                
                this.doDetalhe(orcamento);
//                this.doDetalhe(orcamento);
//                FacesContext.getCurrentInstance().getExternalContext().redirect("detalhes.xhtml?id="+orcamento.getId());

    //            FacesContext.getCurrentInstance().getExternalContext().redirect("detalhes.xhtml?id="+orcamento.getId());

            }else{
    //             System.out.println("Entrou else");
                orcamentoService.selecionarOrcamento(orcamento);
                
                //  atualizar listas
                this.atualizarListas();
                
                // ir para uma pagina de informaçoes
                this.doDetalhe(orcamento);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
             
    }    
    
    
    public void reativarServico() throws IOException{
        System.out.println("ENTROU");
        
        OrcamentoService orcamentoService = new OrcamentoService();
        
        // reativa serviço tirando o selecionado e deixando serviço ativo
        orcamentoService.reativarServico(this.foto.getServico());
        
        //  atualizar listas
        this.atualizarListas();
        
        this.listOrcamentoPorServico();       
        
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashFoto", this.foto); // incluindo um objeto no escopo Flash
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("orcamentos.xhtml");
    }
    
    
    public void listOrcamentoPorServico(){
//        OrcamentoService orcamentoService = new OrcamentoService();  
//
//        this.listaOrcamento = new ArrayList<>();        
//        this.listaOrcamento = orcamentoService.consult(this.servico);
        this.listaOrcamento = new ArrayList<>();
        
        this.listaOrcamento = this.orcamentoControllerAppliScoped.orcamentos(this.foto.getServico());
               
    }
    
    
    
    public String getImgPerfil(Pessoa pessoa){  
        PessoaService pessoaService = new PessoaService();
        if(pessoaService.linkImgPerfil(pessoa) != null){
            return pessoaService.linkImgPerfil(pessoa);
        } else {
            return "resources/images/perfil.png";
        }
    }
    
    //  Links
    ///
    
    public void doDetalhe(Orcamento orcamento) throws IOException{
        
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashFoto", this.foto); // incluindo um objeto no escopo Flash
        FacesContext.getCurrentInstance().getExternalContext().redirect("detalhes.xhtml?id="+orcamento.getId());
    }
    
    // PrimeFaces messages ----------------------------------------------------
    public void destroyWorld() {
        addMessage("System Error", "Please try again later.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void confirma(){
        // condition about the file existence
        // if true 
        RequestContext.getCurrentInstance().execute("PF('confirmDlg').show();");
    }
    
    
    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }  

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<Orcamento> getListaOrcamento() {
        return listaOrcamento;
    }

    public List<String> getMateriais() {
        return materiais;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public void setOrcamentoControllerAppliScoped(OrcamentoControllerAppliScoped orcamentoControllerAppliScoped) {
        this.orcamentoControllerAppliScoped = orcamentoControllerAppliScoped;
    }

    public void setFotoControllerAppliScoped(FotoControllerAppliScoped fotoControllerAppliScoped) {
        this.fotoControllerAppliScoped = fotoControllerAppliScoped;
    }
}
