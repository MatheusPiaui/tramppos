package com.matheus.util.teste;


import com.tramppos.util.mail.Mail;
import javax.mail.MessagingException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matheus
 */
public class mail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code applicataion logic here
        
      
       
        try {
             Mail m = new Mail();
//
//            String[] dest = new String[100];
//         dest[0] = "matheus.piaui.iami@gmail.com";
//         dest[1] = "matheus.piaui.mail.01@gmail.com";

//       dest[0] = "fabio.baptista.desing@gmail.com";
//       dest[1] = "matheus.piaui.iami@gmail.com";
//       dest[2] = "matheus.piaui.mail@gmail.com";
       
//       System.out.println();

       //util.Mail.sendMail("matheus.piaui.mail@gmail.com", "mailteste", dest);
       
//       System.out.print("****Resultado: " + m.sendMail(dest, "assunto", "msg"));
// Enviar email de notificaçao          
        String[] dest = new String[100];            
        dest[0] = "matheus.piaui.iami@gmail.com";
        dest[1] = "matheus.piaui.mail.01@gmail.com";

        System.out.println("Resultado: "+m.notificacaoEscolha(dest, 7));
        
       
//       System.out.print("****Resultado: " + m.validaCadastro("matheus.piaui.iami@gmail.com", "137285c5c5ddd44667ef934e8d077957"));
        } catch (Exception e) {
        }
      
       
       
       
    }
    
}
