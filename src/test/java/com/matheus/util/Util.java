/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheus.util;

import java.util.List;

/**
 *
 * @author matheus
 */
public class Util {
    
    static public void printList(List<Object> listObject){
        
        for (int i=0; i<listObject.size();i++) {
            System.out.println(listObject.get(i).toString());
        }
    }
    
}
