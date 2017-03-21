/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class CidadeRepository {
    
        
    // Comandos sem retorno
    public void insert(Cidade cidade){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(cidade);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Cidade cidade){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(cidade);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(Cidade cidade){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Cidade cidadeEncontrado = entityManager.find(Cidade.class, cidade.getId());
        entityManager.remove(cidadeEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Cidade> consult()
    {
        List<Cidade> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Cidade tp order by nome");
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    public List<Cidade> consult(Estado uf)
    {       
        List<Cidade> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Cidade tp WHERE tp.estado.id = " + uf.getId());
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    // Busca por nome do cidade
    public Cidade consult(String nome)     
    {
        EntityManager entityManager = JPAconnection.getEntityManager();
        Cidade cidade = null;
        
        Query query = entityManager.createQuery("SELECT u FROM Cidade u WHERE u.nome = :p1");
        query.setParameter("p1", nome);       
            
                    
        List results = query.getResultList();

        if (results.isEmpty()) 
        {
            return null;
        }
        else
        {
            if (results.size() == 1)
            {
                return (Cidade) results.get(0);
            }
        }
        throw new NonUniqueResultException();    
            
        
    }
}
