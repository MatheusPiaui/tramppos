/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.cliente;

import com.tramppos.controller.applicationScoped.FotoControllerAppliScoped;
import com.tramppos.controller.applicationScoped.OrcamentoControllerAppliScoped;
import com.tramppos.domain.Categoria;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Foto;
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

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedProperty;
;

import javax.faces.context.FacesContext;
import javax.inject.Inject;


import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author matheus
 */
@Named
@ConversationScoped
public class CadastroServicoClienteController implements Serializable{
    
    
    private Pessoa pessoa;        
    
    private Servico servico = new Servico();    
    
    private List<Servico> listaServico;    
    private Categoria categoria;
    private List<Categoria> listaCategoria;   
    private List<Profissao> listaProfissao;  
    private List<Endereco> listaEndereco;   
    
    // imagem
    private Part arquivo;
    
    
    private Foto foto;    
    private List<Foto> listaFotos;
    
    @Inject
    private Conversation conversation;  
    
    @ManagedProperty("#{fotoControllerAppliScoped}")
    private FotoControllerAppliScoped fotoControllerAppliScoped = new FotoControllerAppliScoped();
    
//    @ManagedProperty("#{fotoControllerAppliScoped}")
//    private FotoControllerAppliScoped fotoControllerAppliScoped;

    public void initConversation(){
      if (conversation.isTransient()) {
        conversation.setTimeout(1800000L);
        conversation.begin();
      }
    }

    public void endConversation(){
      if(!conversation.isTransient()){
        conversation.end();
      }
//      return "step1?faces-redirect=true";
    }    

    
    //Metodos gerais   
    @PostConstruct
    public void start() {

        
        PessoaService pessoaService = new PessoaService();
        this.pessoa = (Pessoa) SessionUtil.getParam("logPessoa"); 
        
        this.clear();

////        carrega listas
        this.listCategoria(); 
        this.listEndereco();
        
//        this.listEnderecos();
        
    }
    public void clear(){
        this.categoria = new Categoria();       
        this.listaCategoria = new ArrayList<>();    
        this.listaProfissao = new ArrayList<>();

        this.listaEndereco = new ArrayList<>();        
//        this.servico = new Servico();         
        this.foto = new Foto();
    }
    
    
    //    metodos lista
    public void listCategoria(){
        CategoriaService categoriaService = new CategoriaService();
        this.listaCategoria = categoriaService.consult();
    }    
    public void listProfissao(){
        ProfissaoService profissaoService = new ProfissaoService();
        
        this.listaProfissao = profissaoService.consult(this.categoria);
        System.out.println(this.listaProfissao);
    }    
      
    public void listEndereco(){
        EnderecoService enderecoService = new EnderecoService();
        
        this.listaEndereco = enderecoService.consult(pessoa);
    }    
    public void listServico(){
        ServicoService servicoService = new ServicoService();
        try {
            this.listaServico = servicoService.consult(pessoa);
        } catch (Exception e) {
            e.printStackTrace();            
        }
       
    }
    
    
    
    // Metodos de insert
    ///
    // 
    public void insert(){
        ServicoService servicoService = new ServicoService();
        
        // data        
        Date dataAtual = new Date(System.currentTimeMillis());
        System.out.println(dataAtual);
        
        this.servico.setDataSolicitacao(dataAtual);
//        this.servico.setPessoa(pessoa);
        this.servico.setStatus(1);
        
//        aqui sera inserido e retornado o objeto inserido        
        this.servico = servicoService.insert(servico);
        
        
//        this.habilitaFoto = false;
    }
    
    
    
    public String insertServico(){        
        // data        
        ServicoService servicoService = new ServicoService();
        
        Date dataAtual = new Date(System.currentTimeMillis());
        System.out.println(dataAtual);

        this.servico.setDataSolicitacao(dataAtual);
        this.servico.setPessoa(pessoa);
        this.servico.setStatus(1);
        
        
        System.out.println("SERVICO: "+this.servico);
        
        //        aqui sera inserido e retornado o objeto inserido        
        this.servico = servicoService.insert(servico);        
        
        this.foto.setTitulo(this.servico.getTitulo());
        //        this.foto.setDescricao();
        this.foto.setDataPestagem(dataAtual);
        //        this.foto.setLink(link);
        this.foto.setServico(this.servico);
        this.foto.setPessoa(this.pessoa);
        
        // inserindo e armzenando foto
        FotoService fotoService = new FotoService();
        fotoService.insert(this.arquivo, this.foto);
        
        
        this.clear();
        
        this.listCategoria(); 
        this.listEndereco();
        this.listServico();
        
        
        this.fotoControllerAppliScoped.carregar();
        
        this.endConversation();       
        
        
        return "criarservico01.xhtml?faces-redirect=true";

    }
    
    
    
   
    
    
    //  Metodos de link
    ///
    public void doVerOrcamentos() throws IOException{       
        FacesContext.getCurrentInstance().getExternalContext().redirect("orcamentos.xhtml");
    }
    public void doCriarServicoVoltar() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("criarservico01.xhtml");
    }
    public String doCriarServico02() throws IOException{
//        FacesContext.getCurrentInstance().getExternalContext().redirect("criarservico02.xhtml");
          return "criarservico02.xhtml?faces-redirect=true";
    }
    public void doFecharModal() throws IOException{
//        this.listServico(1);
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosativos.xhtml");
    }
        
    public void doServicos() throws IOException{
        this.listaServico = new ArrayList<>();
        this.listServico();
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosativos.xhtml");
    }    
    public void doCriarServico() throws IOException{
        this.listCategoria();
        this.listEndereco();
        FacesContext.getCurrentInstance().getExternalContext().redirect("criarservico01.xhtml");
    }
    
    
    

    // Metodos referentes a manipulaçao de imagem
    ///    
    //  Metodo retorna caminho da imagem do servico
    public String getImgServico(Servico servico) {
        
        FotoService fotoService = new FotoService();
        
        try {
            if(servico !=null){
                Foto f = fotoService.consult(servico);
            
                if (f != null) {
                    String str = fotoService.linkImg(f);
                    if (str != null) {
                        return str;
                    }                
                }    
            }else{
                System.out.println("ENTROU SERVICO NULO");
            }
            
            return "";
        } catch (Exception e) { 
            e.printStackTrace();
            return "";
        }
        
    }
    //  Retorna imagem para Edit
    public String getImgServicoEdit() {
        
//        return this.getImgServico(servicoEdit);
return null;
    }
    
    //    getters e setters
    ///
    ////
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = new Servico();
        this.servico = servico;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }



    public List<Categoria> getListaCategoria() {
        this.listCategoria();
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

  
    public List<Profissao> getListaProfissao() {
        return listaProfissao;
    }

    public void setListaProfissao(List<Profissao> listaProfissao) {
        this.listaProfissao = listaProfissao;
    }


    public List<Endereco> getListaEndereco() {
        this.listEndereco();
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    
  

    public List<Servico> getListaServico() {
        return listaServico;
    }

    public void setListaServico(List<Servico> listaServico) {
        this.listaServico = listaServico;
    }  



    

    public List<Foto> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }
    
    public Conversation getConversation() {
      return conversation;
    }

    public void setFotoControllerAppliScoped(FotoControllerAppliScoped fotoControllerAppliScoped) {
        this.fotoControllerAppliScoped = fotoControllerAppliScoped;
    }
}
