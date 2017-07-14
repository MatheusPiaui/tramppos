/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.util.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author matheus
 */
public class GeraHash {
    
    public static byte[] gerarHash(String frase, String algoritmo) 
    {
        try {
          MessageDigest md = MessageDigest.getInstance(algoritmo);
          md.update(frase.getBytes());
          return md.digest();
        } catch (NoSuchAlgorithmException e) {
          return null;
        }
    }
    
    /**
     *
     * @param bytes
     * @return
     */
    public static String stringHexa(byte[] bytes) 
    {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) 
        {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }
    
}
