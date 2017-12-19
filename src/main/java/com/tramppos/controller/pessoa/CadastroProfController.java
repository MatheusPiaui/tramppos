/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.pessoa;

import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Profissional;
import com.tramppos.service.EnderecoService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ProfissionalService;
import com.tramppos.util.jsf.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author matheus
 */
@Named(value = "cadastroProfController")
@SessionScoped
public class CadastroProfController implements Serializable {

    private Pessoa pessoa;
    private PessoaService pessoaService;
    
    private Profissional profissional;
    private ProfissionalService profissionalService;
    
    
    
    @PostConstruct
    public void start(){
        this.profissional = new Profissional();
        this.pessoa = new Pessoa();
        this.profissionalService = new ProfissionalService();
        
        this.pessoaService = new PessoaService();
        this.pessoa = (Pessoa) SessionUtil.getParam("logPessoa");
        
//        this.profissional = (Profissional) this.pessoa;
    }
    
    //  funçoes
    public void insertProf() throws IOException{
        
        EnderecoService enderecoService = new EnderecoService();
        
        System.out.println("COMO ESTA OS DADOS: " + this.pessoa.toString());
        
        //  verificar se a pessoa ja possui endereço cadastrado
        if (enderecoService.consult(pessoa).isEmpty()) {
            //  se a lista for vazia
            //  A pessoa nao tem endereço cadastrado
            //  fazer cadastro
            FacesContext.getCurrentInstance().getExternalContext().redirect("cadprof02.xhtml");            
        }else{
            //  Alterar
            this.pessoa.setDiscrimina(2);
//            this.pessoaService.update(pessoa);
            this.pessoaService.updateForProf(pessoa);
            //  Atualizar cadastro
            Profissional prof = new Profissional();
            ProfissionalService profissionalService = new ProfissionalService();

            prof = profissionalService.consult(this.pessoa.getEmail());
            prof.setNomeFantasia(this.profissional.getNomeFantasia());
            prof.setTelefone(this.profissional.getTelefone());
            profissionalService.update(prof);
            //  Criar Session
            criaSession();
            
//            FacesContext.getCurrentInstance().getExternalContext().redirect("../profissional/home.xhtml");            
        }               
        
    }
    
    private void criaSession() throws IOException{
        //ADD USUARIO NA SESSION
        Profissional profissional = new Profissional();
        ProfissionalService profissionalService = new ProfissionalService();

        profissional = profissionalService.consult(this.pessoa.getEmail());

        if(profissional.isValidado()){
//                SessionUtil.invalidate();
            SessionUtil.setParam("logProf", profissional);

            //muda para a pagina do home prof
            FacesContext.getCurrentInstance().getExternalContext().redirect("../profissional/home.xhtml");
//                return "profissional/homeprof.xhtml?faces-redirect=true";
        }else{
//                return "paginas/login.xhtml?faces-redirect=true";
        }
    }
    
    //  Links
    public void doCadEnd() throws IOException{
        //  Alterar
        this.pessoa.setDiscrimina(2);
//            this.pessoaService.update(pessoa);
        this.pessoaService.updateForProf(pessoa);
        //  Atualizar cadastro
        Profissional prof = new Profissional();
        ProfissionalService profissionalService = new ProfissionalService();

        prof = profissionalService.consult(this.pessoa.getEmail());
        prof.setNomeFantasia(this.profissional.getNomeFantasia());
        prof.setTelefone(this.profissional.getTelefone());
        profissionalService.update(profissional);
        //  Criar Session
        criaSession();

//        FacesContext.getCurrentInstance().getExternalContext().redirect("../profissional/home.xhtml");
    }
    
    
    
    /////
    //
    /////
    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional Profissional) {
        this.profissional = Profissional;
    }    
}
