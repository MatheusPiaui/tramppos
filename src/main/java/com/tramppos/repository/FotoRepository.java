/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Foto;
import com.tramppos.domain.Servico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class FotoRepository {
    
    // Comandos sem retorno
    public void insert(Foto foto){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(foto);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public Foto update(Foto foto){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Foto f = entityManager.merge(foto);  //grava um novo registro
        entityManager.flush();
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
        return f;
    }// fim do método update 
    
    public void delete(Foto foto){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Foto fotoEncontrado = entityManager.find(Foto.class, foto.getId());
        entityManager.remove(fotoEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Foto> consult()
    {
        List<Foto> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Foto tp");
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    
    public Foto consult(Servico servico)
    {
        try {
            EntityManager entityManager = JPAconnection.getEntityManager();
           

            Query query = entityManager.createQuery("SELECT tp FROM Foto tp WHERE idServico = :p1");
            query.setParameter("p1", servico.getId());
            
            return (Foto) query.getSingleResult();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
