/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import java.util.LinkedList;
import paint.model.AbstractShape;

/**
 *
 * @author toshiba
 */
public interface SaveAndLoad1 {

    public void write(LinkedList<AbstractShape>shape, String path);
    
   
    public void read(String path);
    
}
