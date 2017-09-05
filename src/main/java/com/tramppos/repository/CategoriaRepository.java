/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class CategoriaRepository {
    
    // Comandos sem retorno
    public void insert(Categoria categoria){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(categoria);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Categoria categoria){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(categoria);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(Categoria categoria){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Categoria categoriaEncontrado = entityManager.find(Categoria.class, categoria.getId());
        entityManager.remove(categoriaEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Categoria> consult()
    {
        List<Categoria> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Categoria tp order by nome");
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    public Categoria consult(int id)
    {
        EntityManager entityManager = JPAconnection.getEntityManager();
        Categoria categoria = null;
        
        categoria=entityManager.find(Categoria.class, id);
        
        return categoria;
    }
    
}
