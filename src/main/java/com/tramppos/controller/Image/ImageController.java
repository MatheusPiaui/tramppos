/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.Image;

import com.tramppos.domain.Foto;
import com.tramppos.domain.Servico;
import com.tramppos.service.FotoService;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


/**
 *
 * @author matheus
 */
@Named(value = "imageController")
@javax.enterprise.context.RequestScoped
public class ImageController {

    /**
     * Creates a new instance of ImageController
     */
    public ImageController() {
    }
    
     // Metodos referentes a manipulaçao de imagem
    ///    
    //  Metodo retorna caminho da imagem do servico
    public String getImgServico(Servico servico) {
        try {
            FotoService fotoService = new FotoService();
            if(servico !=null){
                Foto f = fotoService.consult(servico);
            
                if (f != null) {
                    String str = fotoService.linkImg(f);
                    if (str != null) {
                        return str;
                    }                
                }    
            }else{
                System.out.println("ENTROU SERVICO NULO");
            }
            
            return "";
        } catch (Exception e) { 
            e.printStackTrace();
            return "";
        }
        
    }
    
}
