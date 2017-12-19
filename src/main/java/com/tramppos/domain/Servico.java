/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author matheus
 */
@Entity
public class Servico implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    private String titulo;
    private String descricao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataSolicitacao;
    private int status;
    
    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false, referencedColumnName = "id")
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "idEndereco", nullable = false, referencedColumnName = "id")
    private Endereco endereco;
    
    @ManyToOne
    @JoinColumn(name = "idAvaliacao", nullable = true, referencedColumnName = "id")
    private Avaliacao Avaliacao;
    
    @ManyToOne
    @JoinColumn(name = "idProfissao", nullable = false, referencedColumnName = "id")
    private Profissao profissao;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Avaliacao getAvaliacao() {
        return Avaliacao;
    }

    public void setAvaliacao(Avaliacao Avaliacao) {
        this.Avaliacao = Avaliacao;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Servico other = (Servico) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Servico{" + "id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", dataSolicitacao=" + dataSolicitacao + ", status=" + status + ", pessoa=" + pessoa + ", endereco=" + endereco + ", Avaliacao=" + Avaliacao + ", profissao=" + profissao + '}';
    }
    
    
}
