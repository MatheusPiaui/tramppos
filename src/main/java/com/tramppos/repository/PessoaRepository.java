/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author matheus
 */
public class PessoaRepository implements Serializable{
    
    public PessoaRepository() {
    }
       
    
    // Comandos sem retorno
    public void insert(Pessoa pessoa){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(pessoa);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Pessoa pessoa){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(pessoa);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(Pessoa pessoa){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Pessoa pessoaEncontrado = entityManager.find(Pessoa.class, pessoa.getId());
        entityManager.remove(pessoaEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Pessoa> consult()
    {
        List<Pessoa> listaPessoa = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Pessoa tp order by nome");
            listaPessoa = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return listaPessoa;
    }// fim do método list
    
    public Pessoa consult(String email)
    {        
        EntityManager entityManager = JPAconnection.getEntityManager();
        Pessoa pessoa = null;
        //try {
            Query query = entityManager.createQuery("SELECT u FROM Pessoa u WHERE u.email = :mail");
            query.setParameter("mail", email);
            pessoa = (Pessoa) query.getSingleResult();            
            
        return pessoa;
    }
    
    public boolean autentica(String email,String senha){
        
        
        Pessoa log;
        log = new Pessoa();
        log = this.consult(email);
        
        if(log != null){
            if(log.getSenha().equals(senha)) return true;
            else return false;
        }else{ // mais de 1 registro, com o mesmo email
           
           System.out.println("Nao existe PESSOA");
           return false; 
        }      
    }
}
