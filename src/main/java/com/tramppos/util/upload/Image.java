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
    
    public String up(Part arquivo, String caminho, String nome) {


       String nomeArquivoSaida;        
//        nomeArquivoSaida = diretorio + arquivo.getSubmittedFileName();
       String caminhoAbs; 
       caminhoAbs = diretorio + caminho;
       
       String extensao = arquivo.getSubmittedFileName().substring(
               arquivo.getSubmittedFileName().lastIndexOf(".")
               ,arquivo.getSubmittedFileName().length());
       
       nomeArquivoSaida = caminhoAbs + nome + extensao;

        System.out.println("caminho entrada: " + caminho);
       //  Verifica se apasta exeite, caso contrario cria 
        System.out.println("Criou?: " + this.buildCaminho(caminhoAbs));
//       File fileSaveDir = new File(caminho);
//       
//       if (!fileSaveDir.exists()) {
//           fileSaveDir.mkdir();
//       } 

       System.out.println("Caminho: " + nomeArquivoSaida);

       try (InputStream is = arquivo.getInputStream();

           FileOutputStream out = new FileOutputStream(nomeArquivoSaida)) {

           int read = 0;
           byte[] bytes = new byte[1024];

           while ((read = is.read(bytes)) != -1) {
               out.write(bytes, 0, read);
           }

           return caminho + nome + extensao;

//                    adicionarMensagem(FacesMessage.SEVERITY_INFO, "Arquivo \"" 
//                                    + nome + "\" enviado com sucesso.");
       } catch (IOException e) {
           return null;
//                adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao enviar arquivo.");
       }
   }
    
   //funçao nova para o servelet
   public boolean up(){
       return false;
   }
    
   public String linkImg(String caminhoBD){
       
       if(caminhoBD == null){
           return null;
       }else{
           return this.path + caminhoBD;
       }
   }

    private boolean buildCaminho(String caminho){

       File fileSaveDir = new File(caminho);
       
       return fileSaveDir.mkdirs();
    }
}
