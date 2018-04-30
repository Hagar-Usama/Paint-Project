/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hagar Osama
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cuteycat_2017
 */
public class RoundRect extends AbstractShape{
    
    int length,width;
    
    public RoundRect() {
       prop = new HashMap<>();
       prop.put("length", 0.0);
       prop.put("width", 0.0);
    }
    
  
     public RoundRect( int x1, int y1, Color color , boolean filled )
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
        
         
        int sidelen = (int)(getLength() / Math.sqrt(2)) ;
        
        Graphics2D g2 = (Graphics2D) canvas;
        
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        
        g2.setStroke(dashed);
       // g2.fill(new RoundRectangle2D.Double(getX1(), getY1(), sidelen,sidelen, 25, 25));
        
        
         //Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        //g2d.setStroke(dashed);
        
        
        g2.draw(new RoundRectangle2D.Double(getX1(), getY1(), sidelen,sidelen, 25, 25));
        //int sidelen = (int)(getLength() / Math.sqrt(2)) ;
        
        
        
        
        double slope = getY1()/getX1();
       // System.out.println("slope = " + slope);
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
