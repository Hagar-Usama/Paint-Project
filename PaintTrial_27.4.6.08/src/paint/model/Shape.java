/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;

import java.awt.Point;

/**
 *
 * @author cuteycat_2017
 */
public interface Shape {
   // public void setPosition(java.awt.Point position);
  //  public java.awt.Point getPosition();
    
  
      
    public void setProperties(java.util.Map<String,Double> properties);
    public java.util.Map<String,Double> getProperties();
    
     public void setStartPoint(Point p);
     public Point getStartPoint(); 
     
     
     public void setEndPoint(Point p);
     public Point getEndPoint(); 
     
     
    public void setFrameColor(java.awt.Color color);
    public java.awt.Color getFrameColor();
    
    public void setFillColor(java.awt.Color color);
    public java.awt.Color getFillColor();
    
    public void draw(Object canvas);
    
    
}
