/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author matheus
 */
public class JPAconnection {
    
    private static final String PERSISTENCE_UNIT_NAME = "tramppos";
    
    private static EntityManagerFactory entityManagerFactory;
    
    private static ThreadLocal<EntityManager> manager = new ThreadLocal<EntityManager>();
    
    //private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tramppos");
    
//    public static EntityManager getEntityManager(){
//        return entityManagerFactory.createEntityManager();
//    }
    
    public static EntityManager getEntityManager() {
        if (JPAconnection.entityManagerFactory == null) {
            JPAconnection.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        EntityManager em = JPAconnection.manager.get();
        if (em == null || !em.isOpen()) {
            em = JPAconnection.entityManagerFactory.createEntityManager();
            JPAconnection.manager.set(em);
        }
        return em;
    }

}
