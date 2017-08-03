/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import com.tramppos.domain.Bairro;
import com.tramppos.domain.Cep;
import com.tramppos.domain.Cidade;
import com.tramppos.domain.Estado;
import com.tramppos.domain.Logradouro;
import com.tramppos.domain.TipoLogradouro;
import com.tramppos.service.BairroService;
import com.tramppos.service.CidadeService;
import com.tramppos.service.EstadoService;
import com.tramppos.service.LogradouroService;
import com.tramppos.service.TipoLogradouroService;
import com.tramppos.webService.WebServiceCep;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author matheus
 */
public class CepRepository {
    
        
    // Comandos sem retorno
    public void insert(Cep cep){
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("tramppos");
        // EntityManager entityManager = factory.createEntityManager();
                
        EntityManager entityManager = JPAconnection.getEntityManager();
       
        //EntityManager entityManager = (EntityManager) factory;
        entityManager.getTransaction().begin();
        entityManager.persist(cep);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add   
    
    public void update(Cep cep){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(cep);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update 
    
    public void delete(Cep cep){
        EntityManager entityManager = JPAconnection.getEntityManager();
        entityManager.getTransaction().begin();
        Cep cepEncontrado = entityManager.find(Cep.class, cep.getId());
        entityManager.remove(cepEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    ///
    // Comandos com Retorno de List
    public List<Cep> consult()
    {
        List<Cep> lista = new ArrayList<>();
        EntityManager entityManager = JPAconnection.getEntityManager();
        
        try 
        {       
            Query query;
            query = entityManager.createQuery("SELECT tp FROM Cep tp");
            lista = query.getResultList();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        entityManager.close();
        return lista;
    }// fim do método list
    
    
    
    
    public Cep consult(String numeroCep)
    {
        // 1- consulta no banco
        ///
        EntityManager entityManager = JPAconnection.getEntityManager();
        Cep cep;
        
        
            
//            Query query = entityManager.createQuery("SELECT u FROM Cep u WHERE u.numeroCep = :p1");
//            query.setParameter("p1", numeroCep);
//            cep = (Cep) query.getSingleResult();
            
        Query query = entityManager.createQuery("SELECT u FROM Cep u WHERE u.numeroCep = :p1");
        query.setParameter("p1", numeroCep);            
        List results = query.getResultList();

        if (results.isEmpty()) 
        {
            Estado estado;
            Cidade cidade;
            Bairro bairro;
            Logradouro logradouro;
            TipoLogradouro tipoLogradouro;

            CidadeService cidadeService = new CidadeService();
            EstadoService estadoService = new EstadoService();
            BairroService bairroService = new BairroService();
            LogradouroService logradouroService = new LogradouroService();
            TipoLogradouroService tipoLogradouroService = new TipoLogradouroService();
            
            
             /////
            // 2- consulta no WebServiceCEp
            WebServiceCep WSCep = WebServiceCep.searchCep(numeroCep);
              
             //caso a busca ocorra bem, imprime os resultados.
            if (!WSCep.isCepNotFound()) 
            {   

                // Logradouro
                if(WSCep.getLogradouro().equals("") != true)// se tiver logradouro vai procurar no BD
                {
                    //Deixando em caixa alta
                    String logra = WSCep.getLogradouro().toUpperCase();
                    String tipoLogra = WSCep.getLogradouroType().toUpperCase();


                    // Procura tipo de logradouro
                    tipoLogradouro = tipoLogradouroService.consult(tipoLogra);

                    if(tipoLogradouro == null){ // tipo logradouro
                        tipoLogradouro = new TipoLogradouro();

                        tipoLogradouro.setNome(tipoLogra);                        
                        tipoLogradouroService.insert(tipoLogradouro);

                        ///buscar tipoLogradouro
                        tipoLogradouro = tipoLogradouroService.consult(tipoLogra);
                    }

                    //Procurando no BD
                    logradouro = logradouroService.consult(logra,tipoLogra);

                    //System.out.println("Logradouro.: " + logra+ " tipo log.: " + tipoLogra);

                    if(logradouro == null)// caso nao tenha cadastrado no BD // logradouro
                    {
                        logradouro = new Logradouro();
                        //vamos cadastrar
                        logradouro.setNome(logra);
                        logradouro.setTipoLogradouro(tipoLogradouro);
                        // cadastra no BD
                        logradouroService.insert(logradouro);
                        // 
                        logradouro = logradouroService.consult(logra,tipoLogra);
                    }                   
                }
                else
                {
                    logradouro = null;
                }

                // Bairro
                if(WSCep.getBairro().equals("") != true)
                {
                    String bar = WSCep.getBairro().toUpperCase();

                    bairro = bairroService.consult(bar); // Busca bairro no BD
                    if(bairro == null)// Caso nao tenha cadastrado
                    {//vamos cadastrar
                        bairro = new Bairro();
                        bairro.setNome(bar);

                        bairroService.insert(bairro);

                        ///
                        bairro = bairroService.consult(bar); // Busca bairro no BD
                    }
                }
                else
                {
                    bairro = null;
                }


                // Cidade e Estado
                /* como todo CEP tem uma cidade que pertence a um estado,
                   so precisamos verificar se ambos estao cadastrados no BD.
                    Caso nao estiver cadastrado, cadastrar!!*/

            String uf = WSCep.getUf().toUpperCase();
             //estado
            estado = estadoService.consult(uf);// Busca Estado no BD

                if(estado == null)// estado nao esta cadastrada
                {
                    //cadastrar estado
                    estado = new Estado();

                    estado.setSigla(uf);

    //                    System.out.println(estado);

                    estadoService.insert(estado);

                    estado = estadoService.consult(uf); // Busca Estado no BD

    //                    System.out.println(estado);
    //                    System.out.println();

                }

            String city = WSCep.getCidade().toUpperCase();
            cidade = cidadeService.consult(city); // Busca Cidade no BD

    //                System.out.println();
    //                System.out.println("****** Teste ********");
    //                System.out.println(cidade);
    //                System.out.println(WSCep.getCidade());
    //                System.out.println();                

                if(cidade == null)// cidade nao esta cadastrada
                {
                    //cadastrar cidade
                    cidade = new Cidade();

                    cidade.setEstado(estado);
                    cidade.setNome(city);

    //                    System.out.println(cidade);

                    cidadeService.insert(cidade);

                    cidade = cidadeService.consult(city); // Busca Cidade no BD

    //                    System.out.println(cidade);
    //                    System.out.println();

                }


            // agora vamos retornar o CEP
            cep = new Cep();
            cep.setNumeroCep(WSCep.getCep());
            cep.setNumeroFinal(0);
            cep.setNumeroInicial(0);
            cep.setBairro(bairro);
            cep.setLogradouro(logradouro);
            cep.setCidade(cidade);

            //caso haja problemas imprime as exce??es.
            return cep;
        }
        else
        {
                System.err.println("Erro numero: " + WSCep.getResulCode());
                System.err.println("Descrição do erro: " + WSCep.getResultText());
//                JOptionPane.showMessageDialog(null, "Erro numero: " + WSCep.getResulCode());
//
//                JOptionPane.showMessageDialog(null, "Descrição do erro: " + WSCep.getResultText());

                return null;
            }            
        }
        else
        {
            if (results.size() == 1)
            {
                return (Cep) results.get(0);
            }
        }
        throw new NonUniqueResultException();      
    }  
    
}
