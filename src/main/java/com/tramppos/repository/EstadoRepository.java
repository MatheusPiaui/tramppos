/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Estado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class EstadoRepository implements Serializable{
    
    public EstadoRepository() {
    }
       
    
    // Comandos sem retorno
    public void insert(Estado estado){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(estado);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Estado estado){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(estado);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(Estado estado){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Estado estadoEncontrado = entityManager.find(Estado.class, estado.getId());
        entityManager.remove(estadoEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Estado> consult()
    {
        List<Estado> listaEstado = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Estado tp order by nome");
            listaEstado = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return listaEstado;
    }// fim do método list
    
    public Estado consult(int id)
    {
        EntityManager entityManager = JPAconnection.getEntityManager();
        Estado estado = null;
        
        estado=entityManager.find(Estado.class, id);
        
        return estado;
    }
    
    // Busca por nome do estado
    public Estado consult(String sigla)     
    {
        EntityManager entityManager = JPAconnection.getEntityManager();
        Estado estado = null;
        //try {
            Query query = entityManager.createQuery("SELECT u FROM Estado u WHERE u.sigla = :p1");
            query.setParameter("p1", sigla);
           // estado = (Estado) query.getSingleResult();            
            
        List results = query.getResultList();

        if (results.isEmpty()) 
        {
            return null;
        }
        else
        {
            if (results.size() == 1)
            {
                return (Estado) results.get(0);
            }
        }
        throw new NonUniqueResultException(); 
    }
    
    
    
}
