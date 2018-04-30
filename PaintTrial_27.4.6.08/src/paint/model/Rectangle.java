/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cuteycat_2017
 */
public class Rectangle extends AbstractShape{
    
    int length,width;
    
    public Rectangle() {
       prop = new HashMap<>();
       prop.put("length", 0.0);
       prop.put("width", 0.0);
    }
    
  
     public Rectangle( int x1, int y1, Color color , boolean filled )
    {
        super(x1 , y1 , color, filled);
        
    }
    
    
    @Override
    public void setProperties(Map<String, Double> properties) {
        length = properties.get("length").intValue();
        width = properties.get("width").intValue();
    }

    @Override
    public Map<String, Double> getProperties() {
        HashMap <String, Double> properties = new HashMap<>();
        properties.put("length",(double)length);
        properties.put("width",(double)width);
        return properties;
    }

    @Override
    public void draw(Object canvas) {
        Graphics c = (Graphics) canvas; // casting canvas to be of type Graphics and placing that in c
        c.setColor(frameColor);
        c.fillRect(getStartPoint().x, getStartPoint().y, length, width);
        c.setColor(fillColor);
        c.drawRect(getStartPoint().x, getStartPoint().y, length, width);
    }
   
    @Override
    public Object clone() throws CloneNotSupportedException {
        Shape r = new Rectangle();
        r.setFrameColor(this.frameColor);
        r.setFillColor(this.fillColor);
        r.setStartPoint(getStartPoint());
        Map newprop = new HashMap<>();
        for (Map.Entry s: prop.entrySet())
            newprop.put(s.getKey(), s.getValue());
        r.setProperties(newprop);
        return r;
}

  
    @Override
    public void drawShape(Object canvas) {
        
        Graphics g = (Graphics) canvas;
        g.setColor( getColor() ); 
        //int sidelen = (int)(getLength() / Math.sqrt(2)) ;
        int height = getLength()*2;
        int width = getLength();
        
        if(getX1() > getX2()){
        
            width = getLength()*2;
            height = getLength();
        
        }
        
        
        
        if(getFilled() == false){
        g.drawRect(getX1(),getY1(), width, height);
       
        
        }else{
        
        g.fillRect(getX1(),getY1(), width, height);
        
        }
        double slope = getY1()/getX1();
        //System.out.println("slope = " + slope);
        double theta = Math.atan(Math.toDegrees(slope));
        //System.out.println("theta = " + theta);
        //System.out.println("length = " + getLength());
        
        /*
        height = (int) (getLength()* Math.sin(theta));
        width = (int) (getLength()* Math.cos(theta));
        
        height = (int)(getLength()*Math.sqrt(3));
        width = (int)(getLength());
        */
    
    }
}
