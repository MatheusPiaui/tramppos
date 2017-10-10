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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author matheus
 */

@Entity
public class Foto implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    private String titulo;
    private String descricao;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPostagem;
    private String link;
    
    @ManyToOne
    @JoinColumn(name = "idServico", referencedColumnName = "id")
    private Servico servico;
    
    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false, referencedColumnName = "id")
    private Pessoa pessoa;
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDataPestagem() {
        return dataPostagem;
    }

    public void setDataPestagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
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
        final Foto other = (Foto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Foto{" + "id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", link=" + link + ", servico=" + servico + ", pessoa=" + pessoa + '}';
    }
    
    

       
}
