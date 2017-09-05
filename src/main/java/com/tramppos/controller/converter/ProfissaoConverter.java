/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.converter;

import com.tramppos.domain.Profissao;
import com.tramppos.service.ProfissaoService;
import com.tramppos.util.jpa.ServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
  * @author Administrador
 */
@FacesConverter(value = "profissaoConverter",forClass = Profissao.class)
public class ProfissaoConverter implements Converter {

    private ProfissaoService profissaoService;
    
    private Profissao profissao;

    public ProfissaoConverter() {
//        profissaoService = ServiceLocator.getBean(ProfissaoService.class);
          profissaoService = new ProfissaoService();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value == null || value.length() == 0) {
//            return null;
//        }else{
//            
//        }
//        profissao = profissaoService.consult(this.getKey(value));
//        return profissao;
        if(value == null || value.isEmpty()){
            return null;
        }else{
            Integer id = Integer.parseInt(value);
//            Profissao profissao = new GenericDAO<Profissao>(Profissao.class).find(id);
            profissao = profissaoService.consult(id);
            return profissao;
        }     
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if ( value==null ){
//             return null;
//        }
//        if ( value instanceof Profissao ) {
//             Profissao profissao = (Profissao) value;
//             return getStringKey(profissao.getId());
//             
//        }
//        return null;
        Profissao profissao = (Profissao)value;
        if(profissao != null){
            return String.valueOf(profissao.getId());
        }else{
            return null;
        }
    }

    private Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    private String getStringKey(Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

}
