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
public class Ellipse extends AbstractShape{
    
    int verticalLength, horizontalLength;
    
    public Ellipse() {
        prop = new HashMap<>();
        prop.put("vertical length", 0.0);
        prop.put("horizontal length", 0.0);
    }
    
    
     public Ellipse( int x1, int y1, Color color , boolean filled )
    {
        super(x1 , y1 , color, filled);
        
    }
    @Override
    public void setProperties(Map<String, Double> properties) {
      verticalLength = properties.get("vertical length").intValue();
      horizontalLength = properties.get("horizontal length").intValue();
    }

    @Override
    public Map<String, Double> getProperties() {
      HashMap <String, Double> properties = new HashMap<>();
        properties.put("vertical length",(double)verticalLength);
        properties.put("horizontal length",(double)horizontalLength);
        return properties;  
    }
   
    @Override
    public void draw(Object canvas) {
        Graphics c = (Graphics) canvas;
        c.setColor(frameColor);
        c.fillOval(getStartPoint().x, getStartPoint().y, verticalLength, horizontalLength);
        c.setColor(fillColor); 
        c.drawOval(getStartPoint().x, getStartPoint().y, verticalLength, horizontalLength);
    }
   @Override
    public Object clone() throws CloneNotSupportedException {
        Shape e = new Ellipse();
        e.setFrameColor(this.frameColor);
        e.setFillColor(this.fillColor);
        e.setStartPoint(getStartPoint());
        Map newprop = new HashMap<>();
        for (Map.Entry s: prop.entrySet())
            newprop.put(s.getKey(), s.getValue());
        e.setProperties(newprop);
        return e;
}  

   

    @Override
    public void drawShape(Object canvas) {
        Graphics g = (Graphics) canvas;
        g.setColor( getColor() );
              
        int width = getLength(), height = 2*getLength();
        int slope = getY1() - getX1();
        
               

         if (getX1() > getX2()){
        width = 2*getLength();
        height = getLength();
        
        }
            
         if(getFilled() == false){
        g.drawOval(getX1(),getY1(), width, height);
        
        }else{
        
        g.fillOval(getX1(),getY1(), width, height);
        
        }
    
        
    }
}
