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
 * @author Hagar Osama
 */
public abstract class SaveAndLoad  {
    
 
    public abstract void  write(LinkedList<AbstractShape>shape, String path) throws Exception;
    

    public abstract LinkedList<AbstractShape> read(String path) throws Exception;
}
