/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author matheus
 */

@Entity
public class Avaliacao implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    private String comentario;
    private int nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Avaliacao other = (Avaliacao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "id=" + id + ", comentario=" + comentario + ", nota=" + nota + '}';
    }
        
    
}
