package com.tramppos.autentica;

import com.tramppos.domain.Cliente;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Profissional;
import com.tramppos.service.ClienteService;
import com.tramppos.service.PessoaService;
import com.tramppos.service.ProfissionalService;
import java.io.Serializable;
import com.tramppos.util.jsf.SessionUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class CriaSessaoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pessoa pessoa;
    
    private PessoaService pessoaService;
    

    @PostConstruct
    public void startDados(){
        this.pessoa = (Pessoa) SessionUtil.getParam("logPessoa");
        this.pessoaService = new PessoaService();
    }

    public void sessaoCliente() throws IOException {
        
        // valida se for cliente
        if (pessoaService.autenticar(pessoa.getEmail(), pessoa.getSenha()) && pessoaService.consult(pessoa.getEmail()).getDiscrimina() == 1) {
            System.out.println("Confirmou  usuario e senha cliente ...");		

            //ADD USUARIO NA SESSION
            Cliente cliente = new Cliente();                
            ClienteService clienteService = new ClienteService();                

            cliente = clienteService.consult(pessoa.getEmail());
            
            

            if(cliente.isValidado()){
                System.out.println("VAMO VE.: "+pessoa);
//                SessionUtil.invalidate();
                SessionUtil.setParam("logCliente", cliente);
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("../cliente/criarservico.xhtml");
                
//                return "/Web/paginas/pessoa/teste.xhtml?faces-redirect=true";
            }else{
//                return "paginas/login.xhtml?faces-redirect=true";
            }              

        } 
        else   
        {
//            return null;
        }
        
    }
    public void sessaoProf() throws IOException {       
        
        // valida se for profissional
        if (pessoaService.autenticar(pessoa.getEmail(), pessoa.getSenha())) {
            if (pessoaService.consult(pessoa.getEmail()).getDiscrimina() == 2) {
                
                //ADD USUARIO NA SESSION
                Profissional profissional = new Profissional();
                ProfissionalService profissionalService = new ProfissionalService();

                profissional = profissionalService.consult(pessoa.getEmail());
                
                if(profissional.isValidado()){
//                SessionUtil.invalidate();
                    SessionUtil.setParam("logProf", profissional);

                    //muda para a pagina do home prof
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../profissional/homeprof.xhtml");
    //                return "profissional/homeprof.xhtml?faces-redirect=true";
                }else{
    //                return "paginas/login.xhtml?faces-redirect=true";
                }
            }
            else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("../pessoa/cadprof.xhtml");
            }
            System.out.println("Confirmou  usuario e senha ...");            
        } 
        else
        {
//            return null;
        }
    }
    /**
     * M�todo que efetua o logout
     * 
     * @return
     */
   

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
}
