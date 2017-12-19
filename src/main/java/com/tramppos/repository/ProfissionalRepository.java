/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;


import com.tramppos.domain.Profissional;

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
public class ProfissionalRepository implements Serializable{
    
    public ProfissionalRepository() {
    }
       
    
    // Comandos sem retorno
    public void insert(Profissional profissional){ 
        // definiçoes de profissional
        
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.persist(profissional);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Profissional profissional){
        // definiçoes de profissional        
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(profissional);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
//    public void delete(Profissional profissional){
//        EntityManager entityManager = JPAconnection.getEntityManager();
//        entityManager.getTransaction().begin();
//        Profissional profissionalEncontrado = entityManager.find(Profissional.class, profissional.getId());
//        entityManager.remove(profissionalEncontrado);
//        entityManager.getTransaction().commit();  //executa o banco para grava 
//        entityManager.close();
//    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Profissional> consult()
    {
        List<Profissional> listaProfissional = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Pessoa tp "
                    + "where discrimina = 2"
                    + "order by nome");
            listaProfissional = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return listaProfissional;
    }// fim do método list
    
     public Profissional consult(String email)
    {   
        try {
            EntityManager entityManager = JPAconnection.getEntityManager();
            Profissional profissional = null;
  
            Query query = entityManager.createQuery("SELECT u FROM Pessoa u WHERE discrimina = 2 "
                    + "and  u.email = :mail");
            query.setParameter("mail", email);
            profissional = (Profissional) query.getSingleResult();            
            
            return profissional;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
