/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.util.converter;

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
@FacesConverter(forClass = Estado.class, value = "converterEstado")
public class ConverterEstado implements Converter {

    private EstadoService estadoService;
    
    private Estado estado;

    public ConverterEstado() {
        estadoService = ServiceLocator.getBean(EstadoService.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        estado = estadoService.consult(getKey(value));
        return estado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if ( value==null ){
             return null;
        }
        if ( value instanceof Estado ) {
             Estado o = (Estado) value;
             return getStringKey(o.getId());
        }
        return null;
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
