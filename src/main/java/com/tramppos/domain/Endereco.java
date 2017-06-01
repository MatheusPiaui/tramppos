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
public class Endereco implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    private String tipo;
    private String numero;
    private String complemento;
    @ManyToOne
    @JoinColumn(name = "idCep", nullable = false, referencedColumnName = "id")
    private Cep cep;
    @ManyToOne
    @JoinColumn(name = "idLogradouro", nullable = false, referencedColumnName = "id")
    private Logradouro logradouro;
    @ManyToOne
    @JoinColumn(name = "idBairro", nullable = false, referencedColumnName = "id")
    private Bairro bairro;
    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false, referencedColumnName = "id")
    private Pessoa pessoa;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Endereco other = (Endereco) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", tipo=" + tipo + ", numero=" + numero + ", complemento=" + complemento + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro + ", pessoa=" + pessoa + '}';
    }

    
    
}
