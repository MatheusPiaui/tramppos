/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author matheus
 */

@Entity
@DiscriminatorValue("2")
public class Profissional extends Pessoa{
    
    private String nomeFantasia;
    private int nota;
    private int raioAtendimento;
    

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getRaioAtendimento() {
        return raioAtendimento;
    }

    public void setRaioAtendimento(int raioAtendimento) {
        this.raioAtendimento = raioAtendimento;
    }   

    @Override
    public String toString() {
        return "Profissional{" + "nomeFantasia=" + nomeFantasia + ", nota=" + nota + ", raioAtendimento=" + raioAtendimento + ", telefone=" + telefone + ", email=" + email +'}';
    }
    
}
