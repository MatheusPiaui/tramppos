/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.profissional;

import com.tramppos.domain.Profissional;
import com.tramppos.util.jsf.SessionUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

/**
 *
 * @author matheus
 */
@Named(value = "sessaoProfissionalController")
@SessionScoped
public class SessaoProfissionalController implements Serializable{

    private Profissional profissional;

    
        
    public SessaoProfissionalController() {
    }
    
    @PostConstruct
    public void start() {        
        this.profissional = new Profissional();
        this.profissional = (Profissional) SessionUtil.getParam("logProf");      
    }

    
    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
    
}
