/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Bairro;
import com.tramppos.domain.Cidade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class BairroRepository {
    
    // Comandos sem retorno
    public void insert(Bairro bairro){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(bairro);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Bairro bairro){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(bairro);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(Bairro bairro){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Bairro bairroEncontrado = entityManager.find(Bairro.class, bairro.getId());
        entityManager.remove(bairroEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Bairro> consult()
    {
        List<Bairro> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Bairro tp order by nome");
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    // Busca por nome do bairro
    public Bairro consult(String nome)     
    {
        EntityManager entityManager = JPAconnection.getEntityManager();
        Bairro bairro = null;
        //try {
            Query query = entityManager.createQuery("SELECT u FROM Bairro u WHERE u.nome = :p1");
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
                return (Bairro) results.get(0);
            }
        }
        throw new NonUniqueResultException(); 
    }

    public List<Bairro> consult(Cidade cidade) {
        List<Bairro> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT DISTINCT bai "
                    + "FROM Bairro bai, Endereco en, Cep cep "
                    + "WHERE bai.id = cep.bairro.id AND en.cep.id = cep.id AND cep.cidade.id = :p1");
            query.setParameter("p1", cidade.getId());
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }

    public Bairro consult(int id) {
        
     
        EntityManager entityManager = JPAconnection.getEntityManager();
        Bairro bairro = null;
        
        bairro=entityManager.find(Bairro.class, id);
        
        return bairro;
    
    
    }
    
}
