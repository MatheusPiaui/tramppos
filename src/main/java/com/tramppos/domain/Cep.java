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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author matheus
 */
@Entity
public class Cep implements Serializable{
    
    @Id 
    @GeneratedValue
    private int id;
    private String numeroCep;
    private int numeroInicial;
    private int numeroFinal;
    @ManyToOne
    @JoinColumn(name = "idCidade", nullable = false, referencedColumnName = "id")
    private Cidade cidade;
    @ManyToOne
    @JoinColumn(name = "idBairro", nullable = false, referencedColumnName = "id")
    private Bairro bairro;
    @ManyToOne
    @JoinColumn(name = "idLogradouro", nullable = false, referencedColumnName = "id")
    private Logradouro logradouro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCep() {
        return numeroCep;
    }

    public void setNumeroCep(String numeroCep) {
        this.numeroCep = numeroCep;
    }

    public int getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(int numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public int getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(int numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
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
        final Cep other = (Cep) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cep{" + "id=" + id + ", numeroCep=" + numeroCep + ", numeroInicial=" + numeroInicial + ", numeroFinal=" + numeroFinal + ", cidade=" + cidade + ", bairro=" + bairro + ", logradouro=" + logradouro + '}';
    }
    
    
}
