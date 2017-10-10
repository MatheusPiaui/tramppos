/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Cliente;
import com.tramppos.domain.Servico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class ServicoRepository {
    
    // Comandos sem retorno
    public Servico insert(Servico servico){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
        
        
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
//        entityManager.persist(servico);  //grava um novo registro
        Servico ser = entityManager.merge(servico);
        entityManager.flush();
        
//        System.out.println("INSERT obj: " +  ser.getId());
//        System.out.println("INSERT: " +  servico.getId());

        entityManager.getTransaction().commit();  //executa o banco para grava  
//        entityManager.refresh(servico);
        entityManager.close();       
        
        
        return ser;
        
    }// fim do método add   
    
    public void update(Servico servico){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(servico);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(Servico servico){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Servico servicoEncontrado = entityManager.find(Servico.class, servico.getId());
        entityManager.remove(servicoEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Servico> consult()
    {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp");
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    ///
    // Comandos com Retorno de List
    public List<Servico> consult(Cliente cliente)
    {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.cliente.id = :p1");
            query.setParameter("p1", cliente.getId());  
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
     public List<Servico> consult(int status)
    {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.status = :p1");
            query.setParameter("p1", status);  
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    public Servico consultUltimo(Cliente cliente)
    {        
        EntityManager entityManager = JPAconnection.getEntityManager();
        Servico servico = null;
        //try {
    //        select * From tramppos.Servico 
    //        where id = (select MAX(id) from tramppos.Servico where idPessoa = 23);
            Query query = entityManager.createQuery("SELECT u FROM Servico u WHERE "
                    + "id = (select MAX(y.id) from Servico y where idPessoa = :idCli)");
            query.setParameter("idCli", cliente.getId());
            servico = (Servico) query.getSingleResult();            
            
        return servico;
    }
}
