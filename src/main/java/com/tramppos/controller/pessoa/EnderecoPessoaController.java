/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.pessoa;

import com.tramppos.controller.pessoa.PerfilController;
import com.tramppos.domain.Bairro;
import com.tramppos.domain.Cep;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Cep;
import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Logradouro;
import com.tramppos.domain.Pessoa;
import com.tramppos.service.BairroService;
import com.tramppos.service.CepService;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.CepService;
import com.tramppos.service.CidadeService;
import com.tramppos.service.EstadoService;
import com.tramppos.service.LogradouroService;
import com.tramppos.service.PessoaService;
import com.tramppos.util.jsf.SessionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author matheus
 */
@ManagedBean
@ViewScoped
public class EnderecoPessoaController implements Serializable{
    
    private Endereco endereco;
    private Endereco enderecoEdit;
    private EnderecoService enderecoService;
    private List<Endereco> listaEndereco;
    
    //Endereco
    private List<Endereco> listaEnderecoPessoa;
    
    //
    private Cep cep;
    private CepService cepService;
    //
    private Cidade cidade;
    private CidadeService cidadeService;
    //
    private Bairro bairro;
    private BairroService bairroService;
    //
    private Logradouro logradouro;
    private LogradouroService logradouroService;
    //
    private Pessoa pessoa;
    private PessoaService pessoaService;
    
    private String strCep = null;
        

   

    @PostConstruct
    public void start() {  
        this.pessoa = new Pessoa();
        clear();
                
//        this.pessoaService = new PessoaService();
        this.pessoa = (Pessoa) SessionUtil.getParam("logPessoa");
        
        list();
    }
    
    public void buscaCEP(){
        cep = cepService.consult(getStrCep());  
        System.out.println(cep);


        if(cep != null){
            cepService.insert(cep);
            setCidade(cep.getCidade());
            setBairro(cep.getBairro());
            setLogradouro(cep.getLogradouro());
        }
    }    

    
    ///
    //  limpar
    public void clear(){
        this.pessoaService = new PessoaService();
        
        this.cidade = new Cidade();
        this.cidadeService = new CidadeService();
        //
        this.bairro = new Bairro();
        this.bairroService = new BairroService();
        //
        this.logradouro = new Logradouro();
        this.logradouroService = new LogradouroService();
        //
        this.cepService = new CepService();
        this.cep = new Cep();
        //
        this.endereco = new Endereco();
        this.enderecoEdit = new Endereco();
        this.enderecoService = new EnderecoService();        
        this.listaEndereco = new ArrayList<>();
    }
    
    ///
    // 
    public String insert(){
        
        this.endereco.setBairro(this.bairro);
        this.endereco.setLogradouro(this.logradouro);
        this.endereco.setCep(this.cep);        
        this.endereco.setPessoa(this.pessoa);
        
//        this.endereco.getCep().getCidade().setEstado(this.estado);
        
        this.getEnderecoService().insert(endereco);
        
        this.clear();
        this.list();    
        
        return "homegeral.xhtml?faces-redirect=true";
    }
  
    public String update(){
        this.getEnderecoService().update(enderecoEdit);
        this.clear();        
        this.list();
        
        return "homegeral.xhtml?faces-redirect=true";
    }
    
    public String delete(){
        try {
            EnderecoService es = new EnderecoService();
        
            es.delete(this.enderecoEdit);
            this.clear();
            this.list();

            return "homegeral.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        
    }
    
    
    public boolean temServico(Endereco endereco){
        try {
            EnderecoService enderecoService = new EnderecoService();
            
            return enderecoService.temServico(endereco);
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }       
    }
    
    // Lista de endereços por pessoa
    public void list(){        
//        System.out.println(pessoa);
        this.listaEndereco = getEnderecoService().consult(pessoa);
    }   
    
//    links
    public void doUpdate() throws IOException{
//        return "homegeral.xhtml#upEndereco?faces-redirect=true
        FacesContext.getCurrentInstance().getExternalContext().redirect("homegeral.xhtml#upEndereco");
    }

    public EnderecoService getEnderecoService() {
        return enderecoService;
    }

    public void setEnderecoService(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public Endereco getEnderecoEdit() {
        return enderecoEdit;
    }

    public void setEnderecoEdit(Endereco enderecoEdit) {
        this.enderecoEdit = enderecoEdit;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public CepService getCepService() {
        return cepService;
    }

    public void setCepService(CepService cepService) {
        this.cepService = cepService;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public BairroService getBairroService() {
        return bairroService;
    }

    public void setBairroService(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public LogradouroService getLogradouroService() {
        return logradouroService;
    }

    public void setLogradouroService(LogradouroService logradouroService) {
        this.logradouroService = logradouroService;
    }

    public String getStrCep() {
        return strCep;
    }

    public void setStrCep(String strCep) {
        this.strCep = strCep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public List<Endereco> getListaEnderecoPessoa() {
        return listaEnderecoPessoa;
    }

    public void setListaEnderecoPessoa(List<Endereco> listaEnderecoPessoa) {
        this.listaEnderecoPessoa = listaEnderecoPessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

      
}
