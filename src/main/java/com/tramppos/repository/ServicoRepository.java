/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Categoria;
import com.tramppos.domain.Cidade;
import com.tramppos.domain.Endereco;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Profissao;
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
        Servico ser = entityManager.merge(servico);
        entityManager.flush();
        entityManager.getTransaction().commit();  //executa o banco para grava  
        entityManager.close();       
        
        
        return ser;
        
    }// fim do método add   
    
    public Servico update(Servico servico){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        servico = entityManager.merge(servico);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
        
        return servico;
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
    public List<Servico> consult(Pessoa pessoa)
    {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.pessoa.id = :p1");
            query.setParameter("p1", pessoa.getId());  
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
     public List<Servico> consult(Pessoa pessoa, int status)
    {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.status = :p1 and "
                    + "tp.pessoa.id = :p2");
            query.setParameter("p1", status);
            query.setParameter("p2", pessoa.getId());
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    public Servico consultUltimo(Pessoa pessoa)
    {        
        EntityManager entityManager = JPAconnection.getEntityManager();
        Servico servico = null;
        //try {
    //        select * From tramppos.Servico 
    //        where id = (select MAX(id) from tramppos.Servico where idPessoa = 23);
            Query query = entityManager.createQuery("SELECT u FROM Servico u WHERE "
                    + "id = (select MAX(y.id) from Servico y where idPessoa = :idCli)");
            query.setParameter("idCli", pessoa.getId());
            servico = (Servico) query.getSingleResult();            
            
        return servico;
    }

    public List<Servico> consult(int status, Cidade cidade) {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.status = :p1 and "
                    + "tp.endereco.cep.cidade.id = :p2");
            query.setParameter("p1", status);
            query.setParameter("p2", cidade.getId());
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }

    public List<Servico> consult(int status) {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.status = :p1");
            query.setParameter("p1", 1);
            
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }

    public List<Servico> consult(int status, Estado estado) {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.status = :p1 and "
                    + "tp.endereco.cep.cidade.estado.id = :p2");
            query.setParameter("p1", status);
            query.setParameter("p2", estado.getId());
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }

    public List<Servico> consult(int status, Categoria categoria) {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.status = :p1 and "
                    + "tp.profissao.categoria.id = :p2");
            query.setParameter("p1", status);
            query.setParameter("p2", categoria.getId());
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }

    public List<Servico> consult(int status, Profissao profissao) {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.status = :p1 and "
                    + "tp.profissao.id = :p2");
            query.setParameter("p1", status);
            query.setParameter("p2", profissao.getId());
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }

    public Servico consultId(int id) {
        EntityManager entityManager = JPAconnection.getEntityManager();
        Servico servico = null;
        //try {
    //        select * From tramppos.Servico 
    //        where id = (select MAX(id) from tramppos.Servico where idPessoa = 23);
            Query query = entityManager.createQuery("SELECT u FROM Servico u WHERE u.id = :p1");
            query.setParameter("p1", id);
            servico = (Servico) query.getSingleResult();            
            
        return servico;
    }

    public List<Servico> consult(Endereco endereco) {
        List<Servico> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Servico tp WHERE tp.endereco.id = :p1");
            query.setParameter("p1", endereco.getId());
            
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }
}
