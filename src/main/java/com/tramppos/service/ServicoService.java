/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Categoria;
import com.tramppos.domain.Cidade;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Profissao;
import com.tramppos.domain.Servico;
import com.tramppos.repository.ServicoRepository;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ServicoService {
    
    private ServicoRepository servicoRepository;

    //contrutor
    public ServicoService() 
    {
        this.servicoRepository = new ServicoRepository();
    }

    //getter e setter
    public ServicoRepository getServicoRepository() {
        return servicoRepository;
    }
    public void setServicoRepository(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }
    
    // comandos
    public Servico insert(Servico servico){
        return getServicoRepository().insert(servico);
    }    
    public Servico update(Servico servico){
        return getServicoRepository().update(servico);
    }
    public void delete(Servico servico){
        getServicoRepository().delete(servico);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Servico> consult(){
        return getServicoRepository().consult();
    }
    public List<Servico> consult(Pessoa pessoa){
        return getServicoRepository().consult(pessoa);
    }
    public List<Servico> consult(Pessoa pessoa,int status){
        return getServicoRepository().consult(pessoa,status);
    }
    
    public Servico consultUltimo(Pessoa pessoa){
        return getServicoRepository().consultUltimo(pessoa);
    }

    public List<Servico> consult(int status, Cidade cidade) {
        return getServicoRepository().consult(status,cidade);
    }

    public List<Servico> consult(int status) {
        return getServicoRepository().consult(status);
    }

    public List<Servico> consult(int status, Estado estado) {
        return getServicoRepository().consult(status,estado);
    }

    public List<Servico> consult(int status, Categoria categoria) {
        return getServicoRepository().consult(status,categoria);

    }

    public List<Servico> consult(int status, Profissao profissao) {
        return getServicoRepository().consult(status,profissao);
    }

    public Servico consultId(int id) {
        return getServicoRepository().consultId(id);
    }    

    public List<Servico> consult(Endereco endereco) {
       return getServicoRepository().consult(endereco);
    }
}
