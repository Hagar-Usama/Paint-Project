/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;

import java.awt.Color;
import java.awt.Point;
import paint.view.Canvas;

/**
 *
 * @author cuteycat_2017
 * % modified >> Amira Khalid
 * % modified >> Hagar Osama :
 * >> parameters : shapeType>int , object canvas
 */
public class ShapeFactory {
     public static AbstractShape createShape(int ShapeType , int x1 , int y1 , Color color , Boolean filled){
   
     switch(ShapeType){
       
       case 1:
           Circle circ = new Circle(  x1,  y1,  color ,  filled );
            return circ;
           
       case 2:
          Ellipse ellipse = new Ellipse(x1,  y1,  color ,  filled);
           
           return ellipse;
           
       case 6:
           Line line = new Line( x1 ,  y1 ,  color, filled );
           
           return line;
           
       case 4:
           Rectangle rect = new Rectangle(x1 ,  y1 ,  color, filled);
           
           return rect;
           
           
       case 3:
           Square sqr = new Square(x1 ,  y1 ,  color, filled);
           
           return sqr;
           
       case 5:
           Triangle tri = new Triangle(x1 ,  y1 ,  color, filled);
           
           return tri;
                   
              
          
           
       default:
           return null; 
   }

    /**
     *
     * @param shape
     */
    
     }   
public static void calcLength(AbstractShape shape){
         
          int x = shape.getX2() - shape.getX1();
           int y = shape.getY2() - shape.getY1();
           
           int d = (int) Math.hypot(x , y );
           shape.setLength(d);
           shape.setColor(Color.BLACK);
    }
     
  }   

