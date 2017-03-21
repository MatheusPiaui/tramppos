/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.webService.teste;

import com.tramppos.webService.WebServiceCep;
import javax.swing.JOptionPane;

/**
 *
 * @author matheus
 */
public class BuscaCepTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        buscaCep("16265000");
        
    }
    
    public static void buscaCep(String cep) 
    {
        //Faz a busca para o cep 58043-280
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
        //A ferramenta de busca ignora qualquer caracter que n?o seja n?mero.

        //caso a busca ocorra bem, imprime os resultados.
        if (webServiceCep.wasSuccessful()) 
        {
//            r1.setText(webServiceCep.getLogradouroFull());
//            c1.setText(webServiceCep.getCidade());
//            b1.setText(webServiceCep.getBairro());
//            e1.setSelectedItem(webServiceCep.getUf());
            
            System.out.println("logradouro: " + webServiceCep.getLogradouro());

            System.out.println("tipo logradouro: " + webServiceCep.getLogradouroType());
            System.out.println("Cep: " + webServiceCep.getCep());
            System.out.println("Logradouro: " + webServiceCep.getLogradouroFull());
            System.out.println("Bairro: " + webServiceCep.getBairro());
            System.out.println("Cidade: "
                    + webServiceCep.getCidade() + "/" + webServiceCep.getUf());

            //caso haja problemas imprime as exce??es.
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());

            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
        }
    }       
}
