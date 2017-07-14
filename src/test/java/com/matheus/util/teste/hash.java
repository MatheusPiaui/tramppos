/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.util.teste;

import static com.tramppos.util.hash.GeraHash.*;

/**
 *
 * @author matheus
 */
public class hash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String frase = "Quero gerar códigos hash desta mensagem.";
        System.out.println(stringHexa(gerarHash(frase, "MD5")));
        System.out.println(stringHexa(gerarHash(frase, "SHA-1")));
        System.out.println(stringHexa(gerarHash(frase, "SHA-256")));
        System.out.println(stringHexa(gerarHash("francisco@gmail.com", "MD5")));

    }
    
}
