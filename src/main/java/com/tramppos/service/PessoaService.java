/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Pessoa;
import com.tramppos.repository.PessoaRepository;
import java.util.List;

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
    public void update(Pessoa pessoa){
        getPessoaRepository().update(pessoa);
    }
    public void delete(Pessoa pessoa){
        getPessoaRepository().delete(pessoa);
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
    
}
