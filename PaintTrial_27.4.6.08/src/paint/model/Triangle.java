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
public class Triangle extends AbstractShape {
   
   // private int x1;
    //private int y1;
    
    public Triangle() {
      /*
        prop = new HashMap<>();
       prop.put("length1", 0.0);
       prop.put("length2", 0.0);
       prop.put("length3", 0.0);
       */
    }

     public Triangle( int x1, int y1, Color color , boolean filled )
    {
        super(x1 , y1 , color, filled);
        
    }
    
   
    
    int length1,length2,length3;

    @Override
    public void setProperties(Map<String, Double> properties) {
        length1 = properties.get("length1").intValue();
        length2 = properties.get("length2").intValue();
        length3 = properties.get("length3").intValue();

    }

    @Override
    public Map<String, Double> getProperties() {
        HashMap <String, Double> properties = new HashMap<>();
        properties.put("length1",(double)length1);
        properties.put("length2",(double)length2);
        properties.put("length3",(double)length3);
        return properties;
    }

    @Override
    public void draw(Object canvas) {
        Graphics c = (Graphics) canvas;
        c.setColor(frameColor);
        
        c.setColor(fillColor);
        //c.drawPolygon(x1,y1,3);  
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
       Shape tr = new Triangle();
        tr.setFrameColor(this.frameColor);
        tr.setFillColor(this.fillColor);
        tr.setStartPoint(getStartPoint());
        Map newprop = new HashMap<>();
        for (Map.Entry s: prop.entrySet())
            newprop.put(s.getKey(), s.getValue());
        tr.setProperties(newprop);
        return tr;
}  

    @Override
    public void drawShape(Object canvas) {
         //int sidelen = (int)(getLength() / Math.sqrt(2)) ;
         
        Graphics g = (Graphics) canvas;
        g.setColor( getColor() ); 
       
       // System.out.println("length of tri = " + getLength());
        int [] x = {getX1(),getX2(),getX2()-getLength()};
       
        int [] y = {getY1(),getY2(),getY2()};
        //System.out.println("x = "+ getX1()+", " + getX2() + "y" + getY1()+", " + getY2());
       
        if(getFilled() == false){
        g.drawPolygon(x, y,3);
        
        }else{
        
         g.fillPolygon(x, y,3);
        
        }
    }

}
