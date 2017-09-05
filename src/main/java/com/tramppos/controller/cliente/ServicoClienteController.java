/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.cliente;

import com.tramppos.controller.*;
import com.tramppos.domain.Categoria;
import com.tramppos.domain.Cliente;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Profissao;
import com.tramppos.domain.Servico;
import com.tramppos.service.CategoriaService;
import com.tramppos.service.ClienteService;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ProfissaoService;
import com.tramppos.service.ServicoService;
import com.tramppos.util.jsf.SessionUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author matheus
 */
@Named
@SessionScoped
public class ServicoClienteController implements Serializable{  
    
    private Cliente cliente;
    private ClienteService clienteService;
    private PessoaService pessoaService;
    
    private Servico servico;    
    private Servico servicoEdit;
    private ServicoService servicoService;    
    
    private Categoria categoria;
    private CategoriaService categoriaService;
    private List<Categoria> listaCategoria;
    
    private Profissao profissao;
    private ProfissaoService profissaoService;
    private List<Profissao> listaProfissao;
    
    private Endereco endereco;
    private EnderecoService enderecoService;
    private List<Endereco> listaEndereco;    
    
    @PostConstruct
    public void start() {

        this.clear();              
        
        this.clienteService = new ClienteService();
        this.cliente = (Cliente) SessionUtil.getParam("logCliente"); 
        
//        carrega listas
        this.listCategoria(); 
        this.listEndereco();
        
//        this.listEnderecos();
        
    }   

    public void clear(){
        this.categoria = new Categoria();
        this.categoriaService = new CategoriaService();
        this.listaCategoria = new ArrayList<>();

        this.profissao = new Profissao();
        this.profissaoService = new ProfissaoService();
        this.listaProfissao = new ArrayList<>();
        
        this.endereco = new Endereco();
        this.enderecoService = new EnderecoService();
        this.listaEndereco = new ArrayList<>();
        
        this.servico = new Servico();
        this.servicoService = new ServicoService();
         
    }
    
    public void listCategoria(){
        this.listaCategoria = getCategoriaService().consult();
    }
    
    public void listProfissao(){
        System.out.println("Entrou na lista profissao");
        
        this.listaProfissao = getProfissaoService().consult(categoria);
        
        System.out.println(this.listaProfissao);
    }
    
    public void listEndereco(){
        this.listaEndereco = getEnderecoService().consult(cliente);
    }
    
    ///
    // 
    public String insert(){
        // data        
        Date dataAtual = new Date(System.currentTimeMillis());
        System.out.println(dataAtual);
        
        this.servico.setDataSolicitacao(dataAtual);
        this.servico.setCliente(cliente);
        
        this.getServicoService().insert(servico);
        this.clear();
        return "homecliente.xhtml?faces-redirect=true";
    }   
    
  
//    public String update(){
//        this.getServicoService().update(servicoEdit);
//        this.clear();
//        this.list();
//        return "lista.xhtml?faces-redirect=true";
//    }
//    
//    public String delete(){
//        this.getServicoService().delete(servicoEdit);
//        this.clear();
//        this.list();
//        return "lista.xhtml?faces-redirect=true";
//    }
    
    public String retornaStatus(int i){
        switch (i) {
            case 1:
                return "Aberto";
            case 2:
                return "Avaliaçao";
            case 3:
                return "Contato";
            case 4:
                return "Finalizado";
            default:
                return "Invalido";
        }
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

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Servico getServicoEdit() {
        return servicoEdit;
    }

    public void setServicoEdit(Servico servicoEdit) {
        this.servicoEdit = servicoEdit;
    }

    public ServicoService getServicoService() {
        return servicoService;
    }

    public void setServicoService(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public ProfissaoService getProfissaoService() {
        return profissaoService;
    }

    public void setProfissaoService(ProfissaoService profissaoService) {
        this.profissaoService = profissaoService;
    }

    public List<Profissao> getListaProfissao() {
        return listaProfissao;
    }

    public void setListaProfissao(List<Profissao> listaProfissao) {
        this.listaProfissao = listaProfissao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public EnderecoService getEnderecoService() {
        return enderecoService;
    }

    public void setEnderecoService(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }
    
   
    
}
