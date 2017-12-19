/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.converter;

import com.tramppos.domain.Cidade;
import com.tramppos.service.CidadeService;
import com.tramppos.util.jpa.ServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
  * @author Administrador
 */
@FacesConverter(value = "cidadeConverter",forClass = Cidade.class)
public class CidadeConverter implements Converter {

    private CidadeService cidadeService;
    
    private Cidade cidade;

    public CidadeConverter() {
//        cidadeService = ServiceLocator.getBean(CidadeService.class);
          cidadeService = new CidadeService();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value == null || value.length() == 0) {
//            return null;
//        }else{
//            
//        }
//        cidade = cidadeService.consult(this.getKey(value));
//        return cidade;
        if(value == null || value.isEmpty()){
            return null;
        }else{
            Integer id = Integer.parseInt(value);
//            Cidade cidade = new GenericDAO<Cidade>(Cidade.class).find(id);
            cidade = cidadeService.consult(id);
            return cidade;
        }     
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if ( value==null ){
//             return null;
//        }
//        if ( value instanceof Cidade ) {
//             Cidade cidade = (Cidade) value;
//             return getStringKey(cidade.getId());
//             
//        }
//        return null;
        Cidade cidade = (Cidade)value;
        if(cidade != null){
            return String.valueOf(cidade.getId());
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
