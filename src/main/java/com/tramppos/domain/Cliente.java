/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author matheus
 */
@Entity
@DiscriminatorValue("1") // 1 - cliente, 2 - profissional ,2 - ambos
public class Cliente extends Pessoa{ 
    
}
