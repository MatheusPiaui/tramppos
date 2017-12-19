/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.profissional;

import com.tramppos.controller.applicationScoped.FotoControllerAppliScoped;
import com.tramppos.domain.Bairro;
import com.tramppos.domain.Categoria;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Cidade;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Foto;
import com.tramppos.domain.Profissao;
import com.tramppos.domain.Profissional;
import com.tramppos.domain.Servico;
import com.tramppos.service.BairroService;
import com.tramppos.service.CategoriaService;
import com.tramppos.service.CidadeService;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.EstadoService;
import com.tramppos.service.FotoService;
import com.tramppos.service.OrcamentoService;
import com.tramppos.service.ProfissaoService;
import com.tramppos.service.ServicoService;
import com.tramppos.util.jsf.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author matheus
 */
@ManagedBean
@ViewScoped
public class HomeProfissionalController implements Serializable {

    private Profissional profissional;
    
    private Estado estado;    
    private Cidade cidade;
    private Bairro bairro;
    private Categoria categoria;
    private Profissao profissao;
    private Endereco endereco;
  
    
    private List<Estado> listaEstado;
    private List<Cidade> listaCidade;
    private List<Bairro> listaBairro;
    private List<Endereco> listaEndereco;
    private List<Categoria> listaCategoria;
    private List<Profissao> listaProfissao;
    private List<Servico> listaServico;
    
    @ManagedProperty("#{fotoControllerAppliScoped}")
    private FotoControllerAppliScoped fotoControllerAppliScoped;
    
    private List<Foto> fotos;

    
    @PostConstruct
    public void start() {
        
        // carrega lista
        this.fotoControllerAppliScoped.carregar();
        
        this.fotos = fotoControllerAppliScoped.fotos(1);
//        this.estado = new Estado();
//        this.cidade = new Cidade();
//        this.bairro = new Bairro();
//        System.out.println("Cidade: "+this.cidade);
        
        
        this.profissional = (Profissional) SessionUtil.getParam("logProf"); 
        
        // chamar listas
        this.listEstado();
        this.listServicoAll();
        this.listCategoria();
//        this.listBairro();
        
    }
    
    //    links 
    public void doSeusOrcamentos() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("orcamentos.xhtml");
    }
    
    public void doCriarOrcamento(Foto foto) throws IOException{
        
        try {
            Profissional prof = (Profissional) SessionUtil.getParam("logProf");
            OrcamentoService orcamentoService = new OrcamentoService();

            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().put("flashFoto", foto); // incluindo um objeto no escopo Flash
            
            //  Verifica se o profissional ja fez um orçamento deste serviço
            if(orcamentoService.consult(prof,foto.getServico()) != null){
                //  Ja possui Orçamento para este serviço, caso queira visualizar ou alterar
                //  entre em "Seus Orçamentos" que contem todos os seus Orçamentos
                FacesContext.getCurrentInstance().getExternalContext().redirect("orcamentocriado.xhtml");
            }else{

                
                FacesContext.getCurrentInstance().getExternalContext().redirect("criarorcamento.xhtml");
            }
        } catch (Exception e) {
        }        
    }
    public void doHomeProfissional() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
    } 
    
    //  lists
    public void listServicoAll(){
        this.listaServico = new ArrayList<>();
        ServicoService servicoService = new ServicoService();
        
//        this.listaServico = servicoService.consult(1);
        this.fotos = fotoControllerAppliScoped.fotos(1);
                       
    }
    
    public void listEstado(){
        this.listaEstado = new ArrayList<>();
        EstadoService estadoService = new EstadoService();
        
        this.listaEstado = estadoService.consult();
    }
    public void listServicoEstado(){
//        this.listaServico = new ArrayList<>();
//        ServicoService servicoService = new ServicoService();
        
//        this.fotos = new ArrayList<>();
//        this.listaServico = servicoService.consult(1,this.estado);
        this.fotos = this.fotoControllerAppliScoped.fotos(this.estado, 1);
                       
    }
    public void listCidade(){
        // Chamada busca anterior
        this.listServicoEstado();
        
        this.listaCidade = new ArrayList<>();
        CidadeService cidadeService = new CidadeService();
               
        this.listaCidade = cidadeService.consult(this.estado);

    }
    public void listServicoCidade(){
//        this.listaServico = new ArrayList<>();
//        ServicoService servicoService = new ServicoService
        
//        this.fotos = new ArrayList<>();
//        this.listaServico = servicoService.consult(1,this.cidade);
        this.fotos = this.fotoControllerAppliScoped.fotos(this.cidade, 1);

                       
    }    
    
    public void listBairro() {
        
        this.listServicoCidade();
        
        this.listaBairro = new ArrayList<>();
        BairroService bairroService = new BairroService();
        
        this.listaBairro = bairroService.consult(this.cidade);
    }
    public void listServicoBairro(){
        
//        this.listaServico = new ArrayList<>();
//        ServicoService servicoService = new ServicoService();
        
//        this.fotos = new ArrayList<>();
//        this.listaServico = servicoService.consult(1,this.cidade);
        this.fotos = this.fotoControllerAppliScoped.fotos(this.bairro, 1);
                       
    }
    public void listCategoria(){
        this.listaCategoria = new ArrayList<>();
        CategoriaService categoriaService = new CategoriaService();
        
        this.listaCategoria = categoriaService.consult();
    }    
    public void listServicoCategoria(){
        this.listaServico = new ArrayList<>();
        ServicoService servicoService = new ServicoService();
        
        
        this.listaServico = servicoService.consult(1,this.categoria);
                       
    }
    public void listProfissao(){
        // Chamada busca anterior
        this.listServicoCategoria();
        
        this.listaProfissao = new ArrayList<>();
        ProfissaoService profissaoService = new ProfissaoService();
        
        this.listaProfissao = profissaoService.consult(this.categoria);
    }
    public void listServicoProfissao(){
        this.listaServico = new ArrayList<>();
        ServicoService servicoService = new ServicoService();
        
        
        this.listaServico = servicoService.consult(1,this.profissao);                       
    }    
    
    
   
    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public List<Estado> getListaEstado() {
        return listaEstado;
    }

    public List<Cidade> getListaCidade() {
        return listaCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }   

    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public void setListaCidade(List<Cidade> listaCidade) {
        this.listaCidade = listaCidade;
    }

    public List<Servico> getListaServico() {
        return listaServico;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public List<Bairro> getListaBairro() {
        return listaBairro;
    }

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public List<Profissao> getListaProfissao() {
        return listaProfissao;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public void setFotoControllerAppliScoped(FotoControllerAppliScoped fotoControllerAppliScoped) {
        this.fotoControllerAppliScoped = fotoControllerAppliScoped;
    }
}
