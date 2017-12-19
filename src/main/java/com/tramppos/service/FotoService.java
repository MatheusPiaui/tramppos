/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.service;

import com.tramppos.domain.Cliente;
import com.tramppos.domain.Foto;
import com.tramppos.domain.Pessoa;
import com.tramppos.domain.Servico;
import com.tramppos.repository.FotoRepository;
import com.tramppos.util.jsf.SessionUtil;
import com.tramppos.util.upload.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Part;

/**
 *
 * @author matheus
 */
public class FotoService {
    
    private FotoRepository fotoRepository;

    //contrutor
    public FotoService() 
    {
        this.fotoRepository = new FotoRepository();
    }

    //getter e setter
    public FotoRepository getFotoRepository() {
        return fotoRepository;
    }
    public void setFotoRepository(FotoRepository fotoRepository) {
        this.fotoRepository = fotoRepository;
    }
    
    // comandos
    public boolean insert(Part arquivo, Foto foto){      
        
        try {
             // se a imagem foi armazenada com sucesso, cadastra no banco
            if(this.upImagem(arquivo, foto)){            
    //            foto.setLink(caminho + nome);                        
    //            this.update(foto);            
                this.getFotoRepository().insert(foto);
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {            
            e.printStackTrace();
            return false;
        }
       
        
        
    }    
    public Foto update(Part arquivo, Foto foto){
        
         // se a imagem foi armazenada com sucesso, cadastra no banco
        try {
            if(this.upImagem(arquivo, foto)){            
//            foto.setLink(caminho + nome);                        
//            this.update(foto);            
            return this.getFotoRepository().update(foto);
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    public void delete(Foto foto){
        getFotoRepository().delete(foto);
    }
    
    ///
    // Metodos que retornam lista
    ///
    public List<Foto> consult(){
        return getFotoRepository().consult();
    }
    
    public Foto consult(Servico servico){
        try {
            return getFotoRepository().consult(servico);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    ///
    // Metodos auxiliares
    ///
    public boolean upImagem(Part arquivo,Foto foto){
        
        try {
            Image image = new Image();

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date(System.currentTimeMillis());

    //        String nome = format.format(date) + "-foto"+ arquivo.getSubmittedFileName();
            String nome = format.format(date) + "-foto";

            System.out.println("Extensao: "+ arquivo.getContentType());

            String idPessoa = String.valueOf(foto.getPessoa().getId());

            String album = "foto";

    //        se tiver serviço atribui ao nome do album o ID do serviço
            if(foto.getServico() != null){
                album = "servicos/" + String.valueOf(foto.getServico().getId());
            }        

            String caminho = idPessoa + "/" + album + "/";

            String e = image.up(arquivo, caminho, nome);
            if(e != null){            
                foto.setLink(e);                        
    //            this.update(foto); 

//                getFotoRepository().insert(foto);
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
    public String linkImg(Foto foto){
        try {
            Image image = new Image();
                       
            return image.linkImg(foto.getLink());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }     
        
    }
}
