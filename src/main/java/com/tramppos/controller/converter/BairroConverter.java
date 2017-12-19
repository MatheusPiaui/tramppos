/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.converter;

import com.tramppos.domain.Bairro;
import com.tramppos.service.BairroService;
import com.tramppos.util.jpa.ServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
  * @author Administrador
 */
@FacesConverter(value = "bairroConverter",forClass = Bairro.class)
public class BairroConverter implements Converter {

    private BairroService bairroService;
    
    private Bairro bairro;

    public BairroConverter() {
//        bairroService = ServiceLocator.getBean(BairroService.class);
          bairroService = new BairroService();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value == null || value.length() == 0) {
//            return null;
//        }else{
//            
//        }
//        bairro = bairroService.consult(this.getKey(value));
//        return bairro;
        if(value == null || value.isEmpty()){
            return null;
        }else{
            Integer id = Integer.parseInt(value);
//            Bairro bairro = new GenericDAO<Bairro>(Bairro.class).find(id);
            bairro = bairroService.consult(id);
            return bairro;
        }     
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if ( value==null ){
//             return null;
//        }
//        if ( value instanceof Bairro ) {
//             Bairro bairro = (Bairro) value;
//             return getStringKey(bairro.getId());
//             
//        }
//        return null;
        Bairro bairro = (Bairro)value;
        if(bairro != null){
            return String.valueOf(bairro.getId());
        }else{
            return "";
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
