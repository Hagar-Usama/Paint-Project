package paint.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cuteycat_2017
 */
public class Circle extends AbstractShape {

    

    public Circle() {
        prop = new HashMap<>();
        prop.put("diameter", 0.0);
       int x = getStartPoint().x - getEndPoint().x;
       int y = getStartPoint().y - getEndPoint().y;
        //diameter = (int) Math.hypot(x , y );
        
    }
    
    
     public Circle( Point start, Point end, Color color , boolean filled )
    {
        super(start , end ,color ,filled);
        //int x = getStartPoint().x - getEndPoint().x;
        //int y = getStartPoint().y - getEndPoint().y;
        //this.diameter = (int) Math.hypot(x , y );
        
    }

     
     public Circle( int x1, int y1, Color color , boolean filled )
    {
        super(x1 , y1 , color, filled);
        
    }
     
    @Override
    public void setProperties(Map<String, Double> properties) {
        //diameter = properties.get("diameter").intValue();
    }

    @Override
    public Map<String, Double> getProperties() {
        HashMap<String, Double> properties = new HashMap<>();
       // properties.put("diameter", (double) diameter);
        return properties;
    }

    @Override
    public void draw(Object canvas) {
        Graphics c = (Graphics) canvas;
        c.setColor(frameColor);
        c.fillOval(getStartPoint().x,getStartPoint().y, getLength(),getLength());
        c.setColor(fillColor);
        c.drawOval(getStartPoint().x, getStartPoint().y, getLength(), getLength());
      
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        
        Shape cir = new Circle();
        cir.setFrameColor(this.frameColor);
        cir.setFillColor(this.fillColor);
        cir.setStartPoint(getStartPoint());
        Map newprop = new HashMap<>();
        for (Map.Entry s: prop.entrySet())
            newprop.put(s.getKey(), s.getValue());
        cir.setProperties(newprop);
        return cir;
    }

    

    @Override
    public void drawShape(Object canvas) {
        Graphics g = (Graphics) canvas;
        g.setColor( getColor() ); //sets the color
       
        if(getFilled() == false){
        g.drawOval(getX1(),getY1(), getLength(), getLength());
        
        }else{
        
        g.fillOval(getX1(),getY1(), getLength(), getLength());
        
        }
    

    }
}