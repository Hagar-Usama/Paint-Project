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
import java.util.Random;

/**
 *
 * @author cuteycat_2017
 */
public class Square extends AbstractShape{

    int length;

    public Square() {
       prop = new HashMap<>();
       prop.put("length", 0.0);
    }

     public Square( int x1, int y1, Color color , boolean filled )
    {
        super(x1 , y1 , color, filled);
        
    }

    
    @Override
    public void setProperties(Map<String, Double> properties) {
        length = properties.get("length").intValue();
    }

    @Override
    public Map<String, Double> getProperties() {
        HashMap <String, Double> properties = new HashMap<>();
        properties.put("length",(double)length);
        return properties;
    }


    @Override
    public void draw(Object canvas) {
        Graphics c = (Graphics) canvas;
        c.setColor(frameColor);
        c.fillRect(getStartPoint().x, getStartPoint().y, length, length);
        c.setColor(fillColor);
        c.drawRect(getStartPoint().x, getStartPoint().y,length,length); 
    }
 
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Shape sq = new Square();
        sq.setFrameColor(this.frameColor);
        sq.setFillColor(this.fillColor);
        sq.setStartPoint(getStartPoint());
        Map newprop = new HashMap<>();
        for (Map.Entry s: prop.entrySet())
            newprop.put(s.getKey(), s.getValue());
        sq.setProperties(newprop);
        return sq;
}


    @Override
    public void drawShape(Object canvas) {
   Graphics g = (Graphics) canvas;
   g.setColor( getColor() ); 
        int sidelen = (int)(getLength() / Math.sqrt(2)) ;
        
        if(getFilled() == false){
        g.drawRect(getX1(),getY1(), sidelen, sidelen);
        
        }else{
        
        g.fillRect(getX1(),getY1(), sidelen, sidelen);
        
        }
        
    }
    
}
