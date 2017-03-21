/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.TipoLogradouro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class TipoLogradouroRepository {
    
    // Comandos sem retorno
    public void insert(TipoLogradouro tipoLogradouro){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(tipoLogradouro);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(TipoLogradouro tipoLogradouro){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(tipoLogradouro);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(TipoLogradouro tipoLogradouro){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        TipoLogradouro tipoLogradouroEncontrado = entityManager.find(TipoLogradouro.class, tipoLogradouro.getId());
        entityManager.remove(tipoLogradouroEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<TipoLogradouro> consult()
    {
        List<TipoLogradouro> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM TipoLogradouro tp order by nome");
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    // Busca por nome do tipoLogradouro
    public TipoLogradouro consult(String nome)     
    {
        EntityManager entityManager = JPAconnection.getEntityManager();
        TipoLogradouro tipoLogradouro = null;
        //try {
            Query query = entityManager.createQuery("SELECT u FROM TipoLogradouro u WHERE u.nome = :p1");
            query.setParameter("p1", nome);
            //tipoLogradouro = (TipoLogradouro) query.getSingleResult();            
            
        List results = query.getResultList();

        if (results.isEmpty()) 
        {
            return null;
        }
        else
        {
            if (results.size() == 1)
            {
                return (TipoLogradouro) results.get(0);
            }
        }
        throw new NonUniqueResultException(); 
    }
    
}
