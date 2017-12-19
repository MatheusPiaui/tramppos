/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Pessoa;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class PessoaRepository implements Serializable{
    
    public PessoaRepository() {
    }
       
    
    // Comandos sem retorno
    public void insert(Pessoa pessoa){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(pessoa);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public Pessoa update(Pessoa pessoa){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Pessoa p = entityManager.merge(pessoa);  //grava um novo registro
        entityManager.flush();
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
        return p;
    }// fim do método update 
    
    public void delete(Pessoa pessoa){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Pessoa pessoaEncontrado = entityManager.find(Pessoa.class, pessoa.getId());
        entityManager.remove(pessoaEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    public void updateForProf(Pessoa pessoa){
        updateDiscrimina(pessoa);
    }
    
    private void updateDiscrimina(Pessoa pessoa){      
        
        try
        {            
            // create a java mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/tramppos";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            // create the java mysql update preparedstatement
            String query = "update Pessoa set discrimina = ? ,nota = ? ,raioAtendimento = ? where id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, pessoa.getDiscrimina());
            preparedStmt.setInt (2, 0);
            preparedStmt.setInt (3, 0);
            preparedStmt.setInt (4, pessoa.getId());

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    ///
    // Comandos com Retorno de List
    public List<Pessoa> consult()
    {
        List<Pessoa> listaPessoa = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Pessoa tp order by nome");
            listaPessoa = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return listaPessoa;
    }// fim do método list
    
    public Pessoa consult(String email)
    {        
        EntityManager entityManager = JPAconnection.getEntityManager();
        Pessoa pessoa = null;
        try {            
            Query query = entityManager.createQuery("SELECT u FROM Pessoa u WHERE u.email = :mail");
            query.setParameter("mail", email);
            pessoa = (Pessoa) query.getSingleResult(); 
        } catch (Exception e) {
            pessoa = null;
        }
        return pessoa;

    }
    
    public boolean autentica(String email,String senha){
        
        
        Pessoa log;
        log = new Pessoa();
        log = this.consult(email);
        
        if(log != null){
            if(log.getSenha().equals(senha)) return true;
            else return false;
        }else{ // mais de 1 registro, com o mesmo email
           
           System.out.println("Nao existe PESSOA");
           return false; 
        }      
    }
    
    
    public boolean validaCadastro(String email,String hash){
        
        Pessoa log = new Pessoa();
        
        log = this.consult(email);
        
        if(log != null){
            if(log.getUid().equals(hash)){
                
                //validando no banco
                log.setValidado(true);
                update(log);
                
                return true;
            }
            else{
                return false;
            }
        }else{ // mais de 1 registro, com o mesmo email
           
           System.out.println("Nao existe PESSOA");
           return false; 
        }      
    }
    public boolean alterarSenha(String email,String hash, String novaSenha){
        try {
            Pessoa log = new Pessoa();        
            log = this.consult(email);

            if(log != null){
                if(log.getUid().equals(hash)){

                    //trocando senha
//                    log.setValidado(true);                    
                    this.update(log);

                    return true;
                }
                else{
                    return false;
                }
            }else{ // mais de 1 registro, com o mesmo email

               System.out.println("Nao existe PESSOA");
               return false; 
            }    
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
}
