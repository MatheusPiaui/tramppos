/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.converter;

import com.tramppos.domain.Estado;
import com.tramppos.service.EstadoService;
import com.tramppos.util.jpa.ServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
  * @author Administrador
 */
@FacesConverter(value = "estadoConverter",forClass = Estado.class)
public class EstadoConverter implements Converter {

    private EstadoService estadoService;
    
    private Estado estado;

    public EstadoConverter() {
//        estadoService = ServiceLocator.getBean(EstadoService.class);
          estadoService = new EstadoService();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value == null || value.length() == 0) {
//            return null;
//        }else{
//            
//        }
//        estado = estadoService.consult(this.getKey(value));
//        return estado;
        if(value == null || value.isEmpty()){
            return null;
        }else{
            Integer id = Integer.parseInt(value);
//            Estado estado = new GenericDAO<Estado>(Estado.class).find(id);
            estado = estadoService.consult(id);
            return estado;
        }     
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if ( value==null ){
//             return null;
//        }
//        if ( value instanceof Estado ) {
//             Estado estado = (Estado) value;
//             return getStringKey(estado.getId());
//             
//        }
//        return null;
        Estado estado = (Estado)value;
        if(estado != null){
            return String.valueOf(estado.getId());
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
