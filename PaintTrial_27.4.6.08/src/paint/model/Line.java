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
import paint.view.Canvas;

/**
 *
 * @author cuteycat_2017
 */
public class Line extends AbstractShape{

    int length;
    
    public Line() {
       prop = new HashMap<>();
       prop.put("length", 0.0);
    }
  
    
     public Line( int x1, int y1, int x2, int y2, Color color )
    {
        super(x1, y1, x2, y2, color);
    }
     
      public Line( Point start , Point end , Color color )
    {
        super(start,end, color);
    }
      
     
        public Line( int x1 , int y1 , Color color, boolean filled )
    {
        super(x1,y1, color, filled);
    }
    @Override
    public void setProperties(Map<String, Double> properties) {
      length = properties.get("length").intValue();  
    }

    @Override
    public Map<String, Double> getProperties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
//     @Override
//    public void paintComponent(Graphics g){
//    
//    super.paintComponent(g);
    //setBackground(Color.BLACK);
    /*
    for(Shape shape : ListOfShapes)
        shape.draw(g); 
    */
//    }
    
    @Override 
    public void draw(Object canvas) {
        
       Graphics c = (Graphics) canvas; // casting canvas to be of type Graphics and placing that in c
       c.setColor(frameColor);
       c.setColor(fillColor);
       c.drawLine( getX1(), getY1(), getX2(), getY2() );
       //c.drawLine(getStartPoint().x ,getStartPoint().y, getEndPoint().x,getEndPoint().y);
      }
    @Override
    public Object clone() throws CloneNotSupportedException {
       Shape ln = new Line();
        ln.setFrameColor(this.frameColor);
        ln.setFillColor(this.fillColor);
        ln.setStartPoint(getStartPoint());
        Map newprop = new HashMap<>();
        for (Map.Entry s: prop.entrySet())
            newprop.put(s.getKey(), s.getValue());
        ln.setProperties(newprop);
        return ln;
}

    @Override
    public void drawShape(Object canvas) {
        Graphics g = (Graphics) canvas;
        g.setColor( getColor() ); //sets the color
        g.drawLine( getX1(), getY1(), getX2(), getY2() ); //draws the line
    
    }
    
}
