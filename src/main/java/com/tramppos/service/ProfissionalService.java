/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;


import com.tramppos.domain.Profissional;
import com.tramppos.repository.ProfissionalRepository;
import static com.tramppos.util.hash.GeraHash.gerarHash;
import static com.tramppos.util.hash.GeraHash.stringHexa;
import com.tramppos.util.mail.Mail;
import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author matheus
 */
public class ProfissionalService {
    
    private ProfissionalRepository profissionalRepository;

    //contrutor
    public ProfissionalService() 
    {
        this.profissionalRepository = new ProfissionalRepository();
    }

    //getter e setter
    public ProfissionalRepository getProfissionalRepository() {
        return profissionalRepository;
    }
    public void setProfissionalRepository(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }
    
    // comandos
    public void insert(Profissional profissional){
         // Gera UID(hash)
        
        profissional.setUid(stringHexa(gerarHash(profissional.getEmail(), "MD5")));
        
        // senha
        profissional.setSenha(stringHexa(gerarHash(profissional.getSenha(), "MD5")));
        
        //Verifica se:
        //     email    => unico
        //     telefone => unico
        
        
        // antes de inserir, mandar email de validaçao
        try {
            // envia email
            Mail m = new Mail();
            
            //System.out.println("## email: "+ m.getEndMail());  
            //System.out.println("## "+ m.sendMail("matheus.piaui.iami@gmail.com", "teste ", "teste"));
            
            System.out.println(" ## Email enviado: " + m.validaCadastro(profissional.getEmail(),profissional.getUid()));
            
            //Se estiver tudo certo manda inserir! 
            getProfissionalRepository().insert(profissional); 
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }    
    public void update(Profissional profissional){
        getProfissionalRepository().update(profissional);
    }
//    public void delete(Profissional profissional){
//        getProfissionalRepository().delete(profissional);
//    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Profissional> consult(){
        return getProfissionalRepository().consult();
    }
    
    public Profissional consult(String mail){
        try {
            return getProfissionalRepository().consult(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
