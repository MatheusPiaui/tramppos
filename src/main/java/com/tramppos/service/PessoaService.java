/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Pessoa;
import com.tramppos.repository.PessoaRepository;
import com.tramppos.util.upload.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Part;

/**
 *
 * @author matheus
 */
public class PessoaService {
    
    private PessoaRepository pessoaRepository;

    //contrutor
    public PessoaService() 
    {
        this.pessoaRepository = new PessoaRepository();
    }

    //getter e setter
    public PessoaRepository getPessoaRepository() {
        return pessoaRepository;
    }
    public void setPessoaRepository(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    
    // comandos
    public void insert(Pessoa pessoa){
        getPessoaRepository().insert(pessoa);
    }    
    public Pessoa update(Pessoa pessoa){
        return getPessoaRepository().update(pessoa);
    }
    public void delete(Pessoa pessoa){
        getPessoaRepository().delete(pessoa);
    }
    public void updateForProf(Pessoa pessoa){
        getPessoaRepository().updateForProf(pessoa);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Pessoa> consult(){
        return getPessoaRepository().consult();
    }
    public Pessoa consult(String email){
        return getPessoaRepository().consult(email);
    }
    
    //
    public boolean autenticar(String email,String senha){
        return getPessoaRepository().autentica(email, senha);
    }
    
     //
    public boolean validaCadastro(String email,String UID){
        return getPessoaRepository().validaCadastro(email, UID);
    }
    public boolean alterarSenha(String email,String UID,String novaSenha){
        return getPessoaRepository().validaCadastro(email, UID);
    }
    
    public boolean upImagemPerfil(Part arquivo,Pessoa pessoa){
        
        Image image = new Image();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        
        String nome = format.format(date) + "-perfil";
        
        String idPessoa = String.valueOf(pessoa.getId());
        String album = "perfil";

        String caminho = idPessoa + "/" + album + "/";
        
        String e = image.up(arquivo, caminho, nome);
        if(e != null){            
            pessoa.setFotoPerfil(e);            
            this.update(pessoa);            
            return true;
        }else{
            return false;
        }
    }
    
    public String linkImgPerfil(Pessoa pessoa){
        
        Image image = new Image();
                       
        return image.linkImg(pessoa.getFotoPerfil());
    }
}
