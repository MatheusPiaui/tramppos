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
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
    private List<Servico> listaServico;
    
    private Categoria categoria;
    private CategoriaService categoriaService;
    private List<Categoria> listaCategoria;
    
    private Profissao profissao;
    private ProfissaoService profissaoService;
    private List<Profissao> listaProfissao;
    private List<Profissao> listaProfissaoEdit;
    
    private Endereco endereco;
    private EnderecoService enderecoService;
    private List<Endereco> listaEndereco;   
    
    // imagem
    private Part arquivo;
    private Part arquivoEdit;
    private Foto foto;
    private FotoService fotoService;
    private FotoService fotoServiceAux;

    private boolean habilitaFoto;

    
    @PostConstruct
    public void start() {

        
        this.clienteService = new ClienteService();
        this.cliente = (Cliente) SessionUtil.getParam("logCliente"); 
        
        this.clear();

////        carrega listas
        this.listCategoria(); 
        this.listEndereco();
        this.listServico();
        
//        this.listEnderecos();
        
    }   

    public void clear(){
        this.categoria = new Categoria();
        this.categoriaService = new CategoriaService();
        this.listaCategoria = new ArrayList<>();

        this.profissao = new Profissao();
        this.profissaoService = new ProfissaoService();
        this.listaProfissao = new ArrayList<>();
        this.listaProfissaoEdit = new ArrayList<>();
        
        this.endereco = new Endereco();
        this.enderecoService = new EnderecoService();
        this.listaEndereco = new ArrayList<>();
        
        this.servico = new Servico();
        this.servicoService = new ServicoService();
        this.listaServico = new ArrayList<>();
         
        this.foto = new Foto();
        this.fotoService = new FotoService();
        this.habilitaFoto = true;

        
//        try {
//            this.arquivo.delete();
//            this.arquivoEdit.delete();
//        } catch (Exception e) {
//           e.printStackTrace();
//        }
        
        
//        carrega listas
//        this.listCategoria(); 
//        this.listEndereco();
//        this.listServico();
    }
    
    public void listCategoria(){
        this.listaCategoria = getCategoriaService().consult();
    }    
    public void listProfissao(){
        System.out.println("Entrou na lista profissao");
        
        this.listaProfissao = getProfissaoService().consult(categoria);
        System.out.println(this.listaProfissao);
    }    
    public void listProfissaoEdit(){
//        System.out.println("Entrou na lista profissao");
        
        this.listaProfissao = getProfissaoService().consult(servicoEdit.getProfissao().getCategoria());        
        System.out.println(this.listaProfissao);
    }    
    public void listEndereco(){
        this.listaEndereco = getEnderecoService().consult(cliente);
    }    
    public void listServico(){
        try {
            this.listaServico = getServicoService().consult(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    public void listServico(int status){
        try {
            this.listaServico = getServicoService().consult(status);
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }
    
    ///
    // 
    public void insert(){
        // data        
        Date dataAtual = new Date(System.currentTimeMillis());
        System.out.println(dataAtual);
        
        this.servico.setDataSolicitacao(dataAtual);
        this.servico.setCliente(cliente);
        this.servico.setStatus(1);
        
//        this.getServicoService().insert(servico);
        
//        aqui sera inserido e retornado o objeto inserido        
        this.servico = this.getServicoService().insert(servico);
        
        this.habilitaFoto = false;
        
//        this.foto.setTitulo(serv.getTitulo());
////        this.foto.setDescricao();
//        this.foto.setDataPestagem(dataAtual);
////        this.foto.setLink(link);
//        this.foto.setServico(serv);
//        this.foto.setPessoa(cliente);
//        
//        // inserindo e armzenando foto
//        this.fotoService.insert(arquivo, foto);
//        
//        
//        this.clear();
//        
//        this.listCategoria(); 
//        this.listEndereco();
        
//        return "criarservico.xhtml?faces-redirect=true";
    }
    public String insertFoto(){        
        // data        
        Date dataAtual = new Date(System.currentTimeMillis());
        System.out.println(dataAtual);        
        
        this.foto.setTitulo(this.servico.getTitulo());
//        this.foto.setDescricao();
        this.foto.setDataPestagem(dataAtual);
//        this.foto.setLink(link);
        this.foto.setServico(this.servico);
        this.foto.setPessoa(cliente);
        
        // inserindo e armzenando foto
        this.fotoService.insert(arquivo, foto);
        
        
        this.clear();
        
        this.listCategoria(); 
        this.listEndereco();
        this.listServico();
        
        return "criarservico.xhtml?faces-redirect=true";

    }
    
    public String update(){
        
//        System.out.println("Serviço Edit: " + servicoEdit.toString());
//        this.categoria = servicoEdit.getProfissao().getCategoria();       
        try {
            this.getServicoService().update(servicoEdit);
        
            this.clear();
            this.listaServico = new ArrayList<>();

            this.listServico(1);

            return "servicosativos.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        
    }
    public String updateFoto() throws IOException{
        
//        System.out.println("Serviço Edit: " + servicoEdit.toString());
//        this.categoria = servicoEdit.getProfissao().getCategoria();
        try {
            if(arquivoEdit != null){
                //alterar imagem do serviço
                this.fotoServiceAux = new FotoService();
                Foto f = fotoServiceAux.consult(servicoEdit);
    //            if ( f != null) {
                    this.fotoServiceAux.update(arquivoEdit, f);
    //            }else{
    //                // nao tem foto cadastrada
    //                
    //                // data        
    //                Date dataAtual = new Date(System.currentTimeMillis());
    //                System.out.println(dataAtual);
    //                
    //                this.foto.setTitulo(this.servicoEdit.getTitulo());  
    //                this.foto.setDataPestagem(dataAtual); 
    //                this.foto.setServico(this.servicoEdit);
    //                this.foto.setPessoa(cliente);
    //                
    //                this.fotoService.insert(arquivoEdit, foto);
    //                
    //                this.foto = new Foto();
    //            }
            }        

    //        this.getServicoService().update(servicoEdit);
    //        this.clear();
//            this.listServico();
              this.listServico(1);

    //        FacesContext.getCurrentInstance().getExternalContext().redirect("servicos.xhtml#editarServico");
            return "servicosativos.xhtml#editarServico?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    // links
    public void doUpdate() throws IOException{
//        return "homegeral.xhtml#upEndereco?faces-redirect=true
        this.listProfissaoEdit();
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosativos.xhtml#editarServico");
        
    }    
    public void doServicos() throws IOException{
//        this.listCategoria(); 
//        this.listEndereco();
        this.listaServico = new ArrayList<>();
        this.listServico();
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosativos.xhtml");
    }    
    public void doCriarServico() throws IOException{
        this.listCategoria();
        this.listEndereco();
        FacesContext.getCurrentInstance().getExternalContext().redirect("criarservico.xhtml");
    }
    public void doServicosAtivos() throws IOException{
        this.listaServico = new ArrayList<>();
        this.listServico(1);
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosativos.xhtml");
    }
    public void doServicosAvaliacao() throws IOException{
        this.listaServico = new ArrayList<>();
        this.listServico(2);
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosavaliacao.xhtml");
    }
    public void doServicosContato() throws IOException{
        this.listaServico = new ArrayList<>();
        this.listServico(3);
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicoscontato.xhtml");
    }
    public void doServicosFinalizados() throws IOException{
        this.listaServico = new ArrayList<>();
        this.listServico(4);
        FacesContext.getCurrentInstance().getExternalContext().redirect("servicosfinalizado.xhtml");
    }
    
      
    
    
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

    // metodos de tratamento de imagem
    public void enviarImg() {
        
        this.getFotoService().insert(arquivo, foto);
            
    }
    
    public String getImgServico(Servico servico) {
        
        try {
            if(servico !=null){
                Foto f = this.fotoService.consult(servico);
            
                if (f != null) {
                    String str = this.fotoService.linkImg(f);
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
    
    public String getImgServicoEdit() {
        
        return this.getImgServico(servicoEdit);
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
        this.listCategoria();
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
        this.listEndereco();
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

    public FotoService getFotoService() {
        return fotoService;
    }
    
    public void setFotoService(FotoService fotoService) {
        this.fotoService = fotoService;
    }
    
    public List<Profissao> getListaProfissaoEdit() {
        return listaProfissaoEdit;
    }

    public void setListaProfissaoEdit(List<Profissao> listaProfissaoEdit) {
        this.listaProfissaoEdit = listaProfissaoEdit;
    }

    public List<Servico> getListaServico() {
        return listaServico;
    }

    public void setListaServico(List<Servico> listaServico) {
        this.listaServico = listaServico;
    }  

    public Part getArquivoEdit() {
        return arquivoEdit;
    }

    public void setArquivoEdit(Part arquivoEdit) {
        this.arquivoEdit = arquivoEdit;
    }

    public boolean isHabilitaFoto() {
        return habilitaFoto;
    }

    public void setHabilitaFoto(boolean habilitaFoto) {
        this.habilitaFoto = habilitaFoto;
    }

    public FotoService getFotoServiceAux() {
        return fotoServiceAux;
    }

    public void setFotoServiceAux(FotoService fotoServiceAux) {
        this.fotoServiceAux = fotoServiceAux;
    }
    
}
