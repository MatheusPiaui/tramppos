package com.tramppos.autentica;

import com.tramppos.domain.Cliente;
import com.tramppos.domain.Profissional;
import com.tramppos.service.PessoaService;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.tramppos.util.jsf.SessionUtil;
import javax.annotation.PostConstruct;

@RequestScoped
@ManagedBean
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
        System.out.println("autentica..");  

        // valida se for administrador
        if (email.equals("admin") && senha.equals("admin")) 
        {
           System.out.println("Confirmou  usuario e senha ...");		

           //ADD USUARIO NA SESSION

           Object b = new Object();

           SessionUtil.setParam("logAdm", b);

           return "adm/homeAdm.xhtml?faces-redirect=true";

        } 
        else 
        {
            // valida se for cliente
            if (pessoaService.autenticar(email, senha) && pessoaService.consult(email).getDiscrimina() == 1) {
                System.out.println("Confirmou  usuario e senha ...");		

                //ADD USUARIO NA SESSION
                Cliente cliente = new Cliente();

                SessionUtil.setParam("logCliente", cliente);

                return "cliente/homeCliente.xhtml?faces-redirect=true";

            } 
            else 
            {// valida se for profissional
                if (pessoaService.autenticar(email, senha) && pessoaService.consult(email).getDiscrimina() == 2) {
                    System.out.println("Confirmou  usuario e senha ...");		

                    //ADD USUARIO NA SESSION
                    Profissional profissional = new Profissional();

                    SessionUtil.setParam("logProf", profissional);

                    return "profissional/homeProf.xhtml?faces-redirect=true";

                } 
                else
                {
                    return null;
                }
            }       
        }
    }   
    /**
     * M�todo que efetua o logout
     * 
     * @return
     */
    public String registraSaida() {

            //REMOVER USUARIO DA SESSION
            SessionUtil.remove("logAdm");

            return "/paginas/index.xhtml?faces-redirect=true";
    }

    // GETTERS E SETTERS


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
