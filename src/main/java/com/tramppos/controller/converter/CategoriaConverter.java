/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.converter;

import com.tramppos.domain.Categoria;
import com.tramppos.service.CategoriaService;
import com.tramppos.util.jpa.ServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
  * @author Administrador
 */
@FacesConverter(value = "categoriaConverter",forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    private CategoriaService categoriaService;
    
    private Categoria categoria;

    public CategoriaConverter() {
//        categoriaService = ServiceLocator.getBean(CategoriaService.class);
          categoriaService = new CategoriaService();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value == null || value.length() == 0) {
//            return null;
//        }else{
//            
//        }
//        categoria = categoriaService.consult(this.getKey(value));
//        return categoria;
        if(value == null || value.isEmpty()){
            return null;
        }else{
            Integer id = Integer.parseInt(value);
//            Categoria categoria = new GenericDAO<Categoria>(Categoria.class).find(id);
            categoria = categoriaService.consult(id);
            return categoria;
        }     
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if ( value==null ){
//             return null;
//        }
//        if ( value instanceof Categoria ) {
//             Categoria categoria = (Categoria) value;
//             return getStringKey(categoria.getId());
//             
//        }
//        return null;
        Categoria categoria = (Categoria)value;
        if(categoria != null){
            return String.valueOf(categoria.getId());
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
