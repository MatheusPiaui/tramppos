/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.cliente;

import com.tramppos.domain.Categoria;
import com.tramppos.domain.Cliente;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Foto;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Profissao;
import com.tramppos.domain.Servico;
import com.tramppos.service.CategoriaService;
import com.tramppos.service.ClienteService;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.FotoService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ProfissaoService;
import com.tramppos.service.ServicoService;
import com.tramppos.util.jsf.SessionUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Part;

/**
 *
 * @author matheus
 */
@Named
@SessionScoped
public class FotoServicoClienteController implements Serializable {

    private Cliente cliente;
    private ClienteService clienteService;
    private PessoaService pessoaService; 
    
    private Foto foto;
    private FotoService fotoService;
    
    private Part arquivo;

  
    
    @PostConstruct
    public void start() {
        this.cliente = new Cliente();
        this.clear();              
        
        this.clienteService = new ClienteService();
        this.cliente = (Cliente) SessionUtil.getParam("logCliente");      
    }   

    public void clear(){
        foto = new Foto();
        fotoService = new FotoService();        
    }
    
    public void enviarImg() {      
        
        if(this.getFotoService().upImagem(arquivo, foto)){
             adicionarMensagem(FacesMessage.SEVERITY_INFO, "Arquivo enviado com sucesso.");
        }else{
            adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao enviar arquivo.");
        }
    }
    
     private void adicionarMensagem(FacesMessage.Severity nivel, String mensagem) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(nivel, mensagem, mensagem));
    }
     
    public void insert(Servico servico){
        // data        
        Date dataAtual = new Date(System.currentTimeMillis());
        
        this.foto.setPessoa(cliente);
        this.foto.setDataPestagem(dataAtual);
        
        if(servico != null){
            foto.setTitulo("Serviço_" + String.valueOf(servico.getId()));
        }
        foto.setTitulo("Serviço_");

        System.out.println("**Foto: " + this.foto);
        if(this.getFotoService().insert(arquivo, foto)){
             adicionarMensagem(FacesMessage.SEVERITY_INFO, "Arquivo enviado com sucesso.");
        }else{
            adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao enviar arquivo.");
        }
    }
    
    public String getImgPerfil(){      
        return this.pessoaService.linkImgPerfil(cliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }    

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }

    public FotoService getFotoService() {
        return fotoService;
    }

    public void setFotoService(FotoService fotoService) {
        this.fotoService = fotoService;
    }
}
