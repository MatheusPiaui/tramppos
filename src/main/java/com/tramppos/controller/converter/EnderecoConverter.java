/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.converter;

import com.tramppos.domain.Endereco;
import com.tramppos.service.EnderecoService;
import com.tramppos.util.jpa.ServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
  * @author Administrador
 */
@FacesConverter(value = "enderecoConverter",forClass = Endereco.class)
public class EnderecoConverter implements Converter {

    private EnderecoService enderecoService;
    
    private Endereco endereco;

    public EnderecoConverter() {
//        enderecoService = ServiceLocator.getBean(EnderecoService.class);
          enderecoService = new EnderecoService();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value == null || value.length() == 0) {
//            return null;
//        }else{
//            
//        }
//        endereco = enderecoService.consult(this.getKey(value));
//        return endereco;
        if(value == null || value.isEmpty()){
            return null;
        }else{
            Integer id = Integer.parseInt(value);
//            Endereco endereco = new GenericDAO<Endereco>(Endereco.class).find(id);
            endereco = enderecoService.consult(id);
            return endereco;
        }     
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if ( value==null ){
//             return null;
//        }
//        if ( value instanceof Endereco ) {
//             Endereco endereco = (Endereco) value;
//             return getStringKey(endereco.getId());
//             
//        }
//        return null;
        Endereco endereco = (Endereco)value;
        if(endereco != null){
            return String.valueOf(endereco.getId());
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
