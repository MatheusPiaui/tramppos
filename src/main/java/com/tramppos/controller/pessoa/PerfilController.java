/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.pessoa;

import com.tramppos.domain.Endereco;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Profissional;
import com.tramppos.repository.EnderecoRepository;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ProfissionalService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import com.tramppos.util.jsf.SessionUtil;
import com.tramppos.util.upload.Image;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author matheus
 */
@Named
@SessionScoped
public class PerfilController implements Serializable {
    
    private Pessoa pessoa;
    
    private PessoaService pessoaService;
    
    private Part arquivo;
    
    //Endereco
    private List<Endereco> listaEnderecoPessoa;
    private EnderecoService enderecoService;
    
    
    @PostConstruct
    public void start() {
//        this.pessoa = new Pessoa();
        this.clear();
        
        this.pessoaService = new PessoaService();
        this.pessoa = (Pessoa) SessionUtil.getParam("logPessoa"); 
        
        this.listEnderecos();
        
    }   

    public void clear(){
        this.listaEnderecoPessoa = new ArrayList<>();
        this.enderecoService = new EnderecoService();
    }
    
    public void enviarImg() {      
        
        if(this.getPessoaService().upImagemPerfil(arquivo, pessoa)){
             adicionarMensagem(FacesMessage.SEVERITY_INFO, "Arquivo enviado com sucesso.");
        }else{
            adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao enviar arquivo.");
        }
    }
    
     private void adicionarMensagem(FacesMessage.Severity nivel, String mensagem) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(nivel, mensagem, mensagem));
    }   
     
     
    // Lista de endereços por pessoa
    public void listEnderecos(){   
        System.out.println(getPessoa());
        this.listaEnderecoPessoa = getEnderecoService().consult(getPessoa());
    }

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }   

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }
    
    public String update(){
        this.getPessoaService().update(pessoa);
        return "homegeral.xhtml?faces-redirect=true";
    }
    
    public String getImgPerfil(){    
        if(this.pessoaService.linkImgPerfil(pessoa) != null){
            return this.pessoaService.linkImgPerfil(pessoa);
        } else {
            return "resources/images/perfil.png";
        }
    }

    public List<Endereco> getListaEnderecoPessoa() {
        return listaEnderecoPessoa;
    }

    public void setListaEnderecoPessoa(List<Endereco> listaEnderecoPessoa) {
        this.listaEnderecoPessoa = listaEnderecoPessoa;
    }

    public EnderecoService getEnderecoService() {
        return enderecoService;
    }

    public void setEnderecoService(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }
}
