/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.util.mail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author matheus
 */
public class Mail {
    
    //Configure email e senha de envio apena uma vez aqui:
    final private String endMail = "matheus.piaui.mail@gmail.com";
    final private String senha = "mailteste";
    
    private final Session session;
    private Properties props;
    private final Message message;
    
    public Mail() throws MessagingException{
        
        props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                         @Override
                         protected PasswordAuthentication getPasswordAuthentication() 
                         {
                               return new PasswordAuthentication(endMail, senha);
                         }
                    });
        /** Ativa Debug para sessão */
        session.setDebug(true);
        
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress(endMail)); //Remetente
    }
    
    // Manda email para mais de um endereço
    public boolean sendMail(String[] destinatarios, String assunto, String msg)
    {
        String dest = new String();
        
        dest = destinatarios[0];
        
        for (int i = 1; destinatarios[i]!=null; i++) {
            dest += "," + destinatarios[i];
        }        

        
        try {              

              Address[] toUser = InternetAddress //Destinatário(s)
                         .parse(dest);
              
              message.setRecipients(Message.RecipientType.TO, toUser);
//              message.setSubject("Enviando email com JavaMail");//Assunto
//              message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
              messageMail(assunto, msg, message);
              
              
              /**Método para enviar a mensagem criada*/
              Transport.send(message);
              //System.out.println("Feito!!!");
              return true;//enviado com sucesso
              
         } catch (MessagingException e) {
             return false;//  nao enviado, erro!!
             //throw new RuntimeException(e);
        }        
        
    }
    
    // mansa email apena par um endereço
    public boolean sendMail(String dest,String assunto,String msg)
    {
        try {            

              Address[] toUser = InternetAddress //Destinatário(s)
                         .parse(dest);
              
              message.setRecipients(Message.RecipientType.TO, toUser);
              messageMail(assunto, msg, message);
              
              
              /**Método para enviar a mensagem criada*/
              Transport.send(message);
              
              return true;//enviado com sucesso
              
         } catch (MessagingException e) {
             return false;//  nao enviado, erro!!
             //throw new RuntimeException(e);
        }        
        
    }
    
    
    private void messageMail(String assunto, String msg, Message m) throws MessagingException{
        m.setSubject(assunto);//Assunto
        m.setText(msg);
    }
    
    public String getEndMail() {
        return endMail;
    } 
    
    
    ///////////////////////////
    
    // emails pre-definidos:
    //    Valida cadastro
    //    Esqueceu senha
    //    Notificaçao
    
    
    
    public boolean validaCadastro(String dest, String hash)
    {
        String assunto,msg;
        String link = "http://localhost:8080/Web/paginas/valida?email="+ dest +"&hash="+hash;
        
        assunto = "Tramppos";
        msg = "Entre na seguinte pagina: " + link
                + "\n\n"
                + "Copie e cole o seguinte codigo: " + hash
                + "\n\n"
                + "     Tramppos";
               
        
        try {            

              Address[] toUser = InternetAddress //Destinatário(s)
                         .parse(dest);
              
              message.setRecipients(Message.RecipientType.TO, toUser);
              messageMail(assunto, msg, message);
              
              
              /**Método para enviar a mensagem criada*/
              Transport.send(message);
              
              return true;//enviado com sucesso
              
         } catch (MessagingException e) {
             return false;//  nao enviado, erro!!
             //throw new RuntimeException(e);
        }        
        
    }
    
    public boolean notificacaoNovoOrcamento(String dest, String servico){
        
        try {
            String assunto = "TRAMPPOS Notifica";
            
            String msg;           

            
            msg = "Olá, você tem um novo orçamento para o serviço:"
                    + "\n"
                    + "\"" +servico+"\"";
        
            this.sendMail(dest,assunto,msg);
            return true;
        
        } catch (Exception e) {
            return false;
        }
        
    }

    public boolean notificacaoEscolha(String[] destinatarios, int idOrcamento) {
        try {
            String link = "http://localhost:8080/Web/paginas/cliente/detalhes.xhtml?id="+String.valueOf(idOrcamento);
            String assunto = "TRAMPPOS Notifica ";
            
            String msg;           
 
            msg = "Olá, parece que você tem um serviço em andamento"
                    + "\n"
                    + "Acesse o link e veja os detalhes: "
                    + link;
        
            this.sendMail(destinatarios,assunto,msg);
            return true;
        
        } catch (Exception e) {
            return false;
        }
    }
    
}
