/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Orcamento;
import com.tramppos.domain.Profissional;
import com.tramppos.domain.Servico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class OrcamentoRepository {
    
    // Comandos sem retorno
    public void insert(Orcamento orcamento){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(orcamento);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Orcamento orcamento){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(orcamento);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(Orcamento orcamento){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Orcamento orcamentoEncontrado = entityManager.find(Orcamento.class, orcamento.getId());
        entityManager.remove(orcamentoEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Orcamento> consult()
    {
        List<Orcamento> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Orcamento tp");
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list

    public List<Orcamento> consult(Profissional profissional) {
        List<Orcamento> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Orcamento tp WHERE tp.profissional.id = :p1");
            query.setParameter("p1", profissional.getId());  
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }

    public Orcamento consult(Profissional profissional, Servico servico) {
        EntityManager entityManager = JPAconnection.getEntityManager();
        Orcamento orcamento = null;
        try {            
            Query query = entityManager.createQuery("SELECT u FROM Orcamento u WHERE u.servico.id = :p1 "
                    + "and u.profissional.id = :p2");
            query.setParameter("p1", servico.getId());
            query.setParameter("p2", profissional.getId());
            orcamento = (Orcamento) query.getSingleResult(); 
        } catch (Exception e) {
            orcamento = null;
        }
        return orcamento;
        //  Retorna null se nao encontrar
    }

    public Orcamento consult(int id) {
        EntityManager entityManager = JPAconnection.getEntityManager();
        Orcamento orcamento = null;
        try {            
            Query query = entityManager.createQuery("SELECT u FROM Orcamento u WHERE u.id = :p1 ");
            query.setParameter("p1", id);
            orcamento = (Orcamento) query.getSingleResult(); 
        } catch (Exception e) {
            orcamento = null;
        }
        return orcamento;
        //  Retorna null se nao encontrar
    }

    public List<Orcamento> consult(Servico servico) {
        List<Orcamento> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Orcamento tp WHERE tp.servico.id = :p1");
            query.setParameter("p1", servico.getId());  
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }

    public List<Orcamento> consultSelecionado(Servico servico) {
        List<Orcamento> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Orcamento tp WHERE tp.servico.id = :p1 and tp.selecionado = 1");
            query.setParameter("p1", servico.getId());  
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }

    public void tiraSelecionado(Servico servico) {
       
        List<Orcamento> lista = new ArrayList<>();
        lista = this.consultSelecionado(servico);

        if(!lista.isEmpty()){
//            System.out.println("entrou");
            for (int i=0; i < lista.size();i++) {
//                System.out.println(listObject.get(i).toString());
                lista.get(i).setSelecionado(false);
                this.update(lista.get(i));
                
            }
        }  
    }
    
}
