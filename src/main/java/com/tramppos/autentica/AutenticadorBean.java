package com.tramppos.autentica;

import com.tramppos.domain.Cliente;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Profissional;
import com.tramppos.service.ClienteService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ProfissionalService;
import static com.tramppos.util.hash.GeraHash.gerarHash;
import static com.tramppos.util.hash.GeraHash.stringHexa;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.tramppos.util.jsf.SessionUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class AutenticadorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String senha;

    private PessoaService pessoaService;
    
    

    @PostConstruct
    public void startDados(){
        this.pessoaService = new PessoaService();
    }

    public String autentica() {
        
        try {
            // senha
            this.senha = stringHexa(gerarHash(this.senha, "MD5"));

            System.out.println("autentica..");  

            // valida se for administrador
            if (email.equals("admin@admin") && senha.equals("admin")) 
            {
               System.out.println("Confirmou  usuario e senha ...");		

               //ADD USUARIO NA SESSION

               Object b = new Object();

               SessionUtil.setParam("logAdm", b);

               return "adm/homeAdm.xhtml?faces-redirect=true";

            } 
            else 
            {
                // valida Pessoa
                if (pessoaService.autenticar(email, senha)) {
                    System.out.println("Confirmou  usuario e senha ...");		

                    //ADD USUARIO NA SESSION
                    Pessoa pessoa = new Pessoa();                
                    PessoaService pessoaService = new PessoaService();                

                    pessoa = pessoaService.consult(email);

                    if(pessoa.isValidado()){
                        SessionUtil.setParam("logPessoa", pessoa);
                        return "pessoa/homegeral.xhtml?faces-redirect=true";
                    }else{
                        this.warn();
                        return "paginas/login.xhtml?faces-redirect=true";
                    }              

                } 
                else{ 
                    this.warn();
//                    this.messagens("erro", "Problema ao logar!!");
                    return null;
                }       
            }
        } catch (Exception e) {
            this.warn();
//            this.messagens("erro", "Problema ao logar!!");
            return null;
        }        
    }
    
    
    
    /**
     * M�todo que efetua o logout
     * 
     * @return
     */
    public String registraSaida() {

            //REMOVER USUARIO DA SESSION
//            SessionUtil.remove("logAdm");
//            SessionUtil.remove("logProf");
//            SessionUtil.remove("logPessoa");
        SessionUtil.invalidate();

        return "/paginas/index.xhtml?faces-redirect=true";
    }
    
    public void messagens(String id, String msg){
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(id, new FacesMessage(msg));
    }
    
    // PrimeFaces - messagens --------------------------------------------------
    
    public void info() {
        FacesContext.getCurrentInstance().addMessage(
                null, 
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Info", 
                        "PrimeFaces Rocks."));
    }
     
    public void warn() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        "Problema de Login",
                        "Email e/ou senha errado."));
    }
     
    public void error() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Error!",
                        "Contact admin."));
    }
     
    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(
                        FacesMessage.SEVERITY_FATAL,
                        "Fatal!",
                        "System Error"));
    }

    //-- GETTERS E SETTERS -----------------------------------------------


    public String getSenha() {
            return senha;
    }

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public void setSenha(String senha) {
            this.senha = senha;
    }

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

}
