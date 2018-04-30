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
public class Memento {
    
    
    LinkedList<AbstractShape> shapes;
    //int counter;

    public Memento() {
    }

    public LinkedList<AbstractShape> getShapes() {
        return shapes;
    }

    public void setShapes(LinkedList<AbstractShape> shapes) {
        this.shapes = shapes;
    }
    
    public Memento (LinkedList<AbstractShape> state){
    
    this.shapes = new LinkedList<AbstractShape>();
    try{
    
    shapes = (LinkedList<AbstractShape>) state.clone();
        
    }catch(Exception e){
    
    System.out.println(e.toString());
    
    }
    
    }
    
    
   
    //get listof shapes
    
    // get listShapes
    
    
    
}
