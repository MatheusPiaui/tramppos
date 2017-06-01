/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author matheus
 */

@Entity
public class PertenceProfissao implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    private Date dataCadastroNaProfissao;
    
    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false, referencedColumnName = "id")
    private Pessoa pessoa;
    @ManyToOne
    @JoinColumn(name = "idProfissao", nullable = false, referencedColumnName = "id")
    private Profissao profissao;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCadastroNaProfissao() {
        return dataCadastroNaProfissao;
    }

    public void setDataCadastroNaProfissao(Date dataCadastroNaProfissao) {
        this.dataCadastroNaProfissao = dataCadastroNaProfissao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
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
        final PertenceProfissao other = (PertenceProfissao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PertenceProfissao{" + "id=" + id + ", dataCadastroNaProfissao=" + dataCadastroNaProfissao + ", pessoa=" + pessoa + ", profissao=" + profissao + '}';
    }
    
    
    
}
