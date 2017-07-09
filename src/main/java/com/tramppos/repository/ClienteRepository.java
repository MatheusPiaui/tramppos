/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;


import com.tramppos.domain.Cliente;
import com.tramppos.domain.Pessoa;

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
public class ClienteRepository implements Serializable{
    
    public ClienteRepository() {
    }
       
    
    // Comandos sem retorno
    public void insert(Cliente cliente){ 
        // definiçoes de cliente
//        cliente.setDiscrimina(1);
//        cliente.setNomeFantasia(null);
//        cliente.setNota(0);
//        cliente.setRaioAtendimento(0);
        
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Cliente cliente){
        // definiçoes de cliente        
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
//    public void delete(Cliente cliente){
//        EntityManager entityManager = JPAconnection.getEntityManager();
//        entityManager.getTransaction().begin();
//        Cliente clienteEncontrado = entityManager.find(Cliente.class, cliente.getId());
//        entityManager.remove(clienteEncontrado);
//        entityManager.getTransaction().commit();  //executa o banco para grava 
//        entityManager.close();
//    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Cliente> consult()
    {
        List<Cliente> listaCliente = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Pessoa tp "
                    + "where discrimina = 1"
                    + "order by nome");
            listaCliente = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return listaCliente;
    }// fim do método list
    
    public Cliente consult(String email)
    {        
        EntityManager entityManager = JPAconnection.getEntityManager();
        Cliente cliente = null;
        //try {
            Query query = entityManager.createQuery("SELECT u FROM Pessoa u WHERE discrimina = 1 "
                    + "and  u.email = :mail");
            query.setParameter("mail", email);
            cliente = (Cliente) query.getSingleResult();            
            
        return cliente;
    }
}
