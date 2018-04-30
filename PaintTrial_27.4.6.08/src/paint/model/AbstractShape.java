/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;
import paint.view.Canvas;

/**
 *
 * @author cuteycat_2017
 */
public abstract  class AbstractShape implements Shape {
   private String name ;  
   private Point startPoint = new Point();
   private Point endPoint = new Point();
   private int length;
   private int x1,y1,x2,y2; //coordinates of shape
    private Color color; // color of shape
    private boolean filled;

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
   
 
 public boolean getFilled() {
        
        return filled;
    }

    public void setFilled(Boolean filled) {
        this.filled = filled;
    }
   

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
   
    
     public AbstractShape()
    {
        x1=0;
        y1=0;
        x2=0;
        y2=0;
        color=Color.BLACK;
    }
     
       public AbstractShape(int x1 , int y1 , int x2 , int y2 , Color color)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
    
       
       public AbstractShape(int x1 , int y1,  Color color , boolean filled)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
    }
       
         public AbstractShape(Point start , Point end ,Color color , boolean filled)
    {
        this.startPoint.x = start.x;
        this.startPoint.y = start.y;
        
        this.endPoint.x = end.x;
        this.endPoint.y = end.y;
        
        this.color = color;
        this.filled = filled;
        
        
    }
         
         
         public AbstractShape(Point start , Point end ,Color color)
    {
        this.startPoint.x = start.x;
        this.startPoint.y = start.y;
        
        this.endPoint.x = end.x;
        this.endPoint.y = end.y;
        
        this.color = color;
        
        
        
    }
  protected  Color frameColor = (Color.BLACK);
  protected Color fillColor = (Color.WHITE);
  
  protected Map<String, Double> prop;
    
    
@Override
    public void setProperties(java.util.Map<String,Double> properties){
        prop = properties;
    }
    
@Override
    public java.util.Map<String,Double> getProperties(){
      return prop;
    }
    
@Override    
     public void setStartPoint(Point p){
       this.startPoint = startPoint;
     }
     
@Override
     public Point getStartPoint(){ 
        return this.startPoint;
     } 
     
   @Override
     public void setEndPoint(Point p){
       this.endPoint = endPoint;
     }
    
   @Override
     public Point getEndPoint(){
       return this.endPoint;
     }
     
@Override     
    public void setFrameColor(java.awt.Color color){
    this.frameColor = frameColor;
    }
    
@Override
    public java.awt.Color getFrameColor(){
        return this.frameColor;
    }
    
@Override    
    public void setFillColor(java.awt.Color color){
        this.fillColor = fillColor;
    }
    
@Override
    public java.awt.Color getFillColor(){
        return this.fillColor;
    } 
    
    
 abstract public void drawShape( Object canvas );
  
    /*
  @Override
   public void draw(Object canvas){
   
       Canvas can = new Canvas();
       
       canvas = (Canvas)can;
       
   
      
       
      
       

   }
   
*/

    void paintComponent(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
