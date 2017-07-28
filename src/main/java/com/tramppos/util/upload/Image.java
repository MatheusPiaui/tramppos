/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.util.upload;

import com.tramppos.domain.Pessoa;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.swing.ImageIcon;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



/**
 *
 * @author matheus
 */
public class Image {
    
    private final String diretorio = "/home/matheus/uploadTramppos/";
    private final String path = "uploadTramppos/";
    
        
    public Image(){
        
    }
    
    public boolean up(Part arquivo, String caminho, String nome) {


       String nomeArquivoSaida;        
//        nomeArquivoSaida = diretorio + arquivo.getSubmittedFileName();
       caminho = diretorio + caminho;
       nomeArquivoSaida = caminho + nome;

        System.out.println("caminho entrada: " + caminho);
       //  Verifica se apasta exeite, caso contrario cria 
        System.out.println("Criou?: "+this.buildCaminho(caminho));
//       File fileSaveDir = new File(caminho);
//       
//       if (!fileSaveDir.exists()) {
//           fileSaveDir.mkdir();
//       } 

       System.out.println("Caminho: " + nomeArquivoSaida);

       try (InputStream is = arquivo.getInputStream();

           OutputStream out = new FileOutputStream(nomeArquivoSaida)) {

           int read = 0;
           byte[] bytes = new byte[1024];

           while ((read = is.read(bytes)) != -1) {
               out.write(bytes, 0, read);
           }

           return true;

//                    adicionarMensagem(FacesMessage.SEVERITY_INFO, "Arquivo \"" 
//                                    + nome + "\" enviado com sucesso.");
       } catch (IOException e) {
           return false;
//                adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao enviar arquivo.");
       }
   }
    
   public String linkImg(String caminhoBD){
       
       if(caminhoBD == null){
           return "resources/images/perfil.png";
       }else{
           return this.path + caminhoBD;
       }
   }

    private boolean buildCaminho(String caminho){

       File fileSaveDir = new File(caminho);
       
       return fileSaveDir.mkdirs();
    }
}
