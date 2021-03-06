/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Logradouro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class LogradouroRepository {
    
        
    // Comandos sem retorno
    public void insert(Logradouro logradouro){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(logradouro);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Logradouro logradouro){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(logradouro);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(Logradouro logradouro){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Logradouro logradouroEncontrado = entityManager.find(Logradouro.class, logradouro.getId());
        entityManager.remove(logradouroEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Logradouro> consult()
    {
        List<Logradouro> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Logradouro tp order by nome");
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    // Busca por nome do logradouro
    public Logradouro consult(String nomeLogra, String tipoLogra)     
    {
        EntityManager entityManager = JPAconnection.getEntityManager();
        Logradouro logradouro = null;
        //try {
        Query query = entityManager.createQuery("SELECT u FROM Logradouro u,TipoLogradouro p "
                                                    +" WHERE  u.nome = :p1    and"
                                                    +"        p.nome = :p2");        
        query.setParameter("p1", nomeLogra);
        query.setParameter("p2", tipoLogra);
        
        //logradouro = (Logradouro) query.getSingleResult();      
    
            
        List results = query.getResultList();

        if (results.isEmpty()) 
        {
            return null;
        }
        else
        {
            if (results.size() == 1)
            {
                return (Logradouro) results.get(0);
            }
        }
        throw new NonUniqueResultException(); 
    }
}
