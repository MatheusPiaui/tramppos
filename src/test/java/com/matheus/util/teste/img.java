/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.util.teste;

import com.tramppos.util.upload.Image;
import java.io.File;

/**
 *
 * @author matheus
 */
public class img {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Image image = new Image();
        
        String caminho = "/home/matheus/pasta/teste/10/outro/teste/teste2/teste3/teste4";
        
        File fileSaveDir = new File(caminho);
       
//        System.out.println("result: "+image.buildCaminho(caminho));
//       if (!fileSaveDir.exists()) {
//           fileSaveDir.mkdirs();
//       }
//       System.out.println("Resultado: "+ image.buildCaminho(caminho));

//        String nome = "Vinícius";
//        char[] nomeArray = nome.toCharArray();
//        if (nomeArray.length == 0)
//            System.out.println("String vazia!");
//        else
//            System.out.println(nomeArray); //Imprime v.
    }
    
}
