/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import paint.model.AbstractShape;
import paint.model.Circle;
import paint.model.Ellipse;
import paint.model.Line;
import paint.model.Rectangle;
import paint.model.RoundRect;
import paint.model.Shape;
import paint.model.Square;
import paint.model.Triangle;
import paint.view.Canvas;
import paint.view.Gui;

/**
 *
 * @author Hagar Osama
 */
public class MyController implements DrawingEngine {
    
    
    private LinkedList<AbstractShape> Shapes;
     private LinkedList<AbstractShape> undoShapes;
     
     
     private int selectedIndex;
     private RoundRect selectedRegion;
     
     private AbstractShape tempo;
    private ArrayList<AbstractShape> myShapes;
    private ArrayList<AbstractShape> shapesDel;

       
    private int currentShapeType;
    private AbstractShape currentShape; 
    private Color currentShapeColor;
    private boolean currentShapeFilled; 
   
    Canvas can = new Canvas();
    
     //+------------Singleton-----------------+//
    private static MyController uniqueController;
    
    private MyController(Canvas c){  };
    
    public static MyController getInstance(){
      if(uniqueController == null)
          uniqueController = new MyController();
     
      return uniqueController;
    
    }
    //+--------------------------------------+//
    
    public MyController() {  
        //can.addMouseListener(new MouseHandler());
        //can.addMouseMotionListener(new MouseHandler());
        Gui GUI = new Gui(can);
        LinkedList<AbstractShape> currentState = can.getShapes();
     
     
    }

    
    @Override
    public void refresh(Object canvas) {
        Canvas a = (Canvas) canvas;
        a.repaint();
        
    }

    @Override
    public void addShape(Shape shape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeShape(Shape shape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape[] getShapes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void installPluginShape(String jarPath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
    
    private class MouseHandler extends MouseAdapter 
    {
        /**
         * When mouse is pressed draw a shape object based on type, color and filled.
         * X1,Y1 & X2,Y2 coordinate for the drawn shape are both set to the same X & Y mouse position.
         */
        public void mousePressed( MouseEvent event )
        {
            System.out.println("ENtered mouse pressed");
            System.out.println("currentShapeType = " + currentShapeType);
            
            
            switch (currentShapeType) 
            {
                case 6:
                    currentShape = new Line( event.getX(), event.getY(), 
                                              event.getX(), event.getY() , currentShapeColor);
                    
                    selectedRegion = null;
                    
                    //createShape(int ShapeType , Point start , Point end , Color color , Boolean filled)
                    System.out.println("entered case 6");
                    System.out.println("Current Color = " + currentShapeColor);
                   // myShapes.add(currentShape);
                    
                  
                    can.repaint();
                    break;
                    
                case 1:
                    
                    currentShape = new Circle(event.getX(), event.getY(),currentShapeColor , false);
                    selectedRegion = null;
                    
                    //createShape(int ShapeType , Point start , Point end , Color color , Boolean filled)
                    System.out.println("entered case 1 circle");
                    System.out.println("Current Color = " + currentShapeColor);
                   // myShapes.add(currentShape);
                    
                  
                    can.repaint();
                    break;
                    
                case 2:
                    
                    currentShape = new Ellipse(event.getX(), event.getY(),currentShapeColor , false);
                    selectedRegion = null;
                    
                    //createShape(int ShapeType , Point start , Point end , Color color , Boolean filled)
                    System.out.println("entered case 1 circle");
                    System.out.println("Current Color = " + currentShapeColor);
                   // myShapes.add(currentShape);
                    
                  
                    can.repaint();
                    break;
                
                 case 3:
                    
                    currentShape = new Square(event.getX(), event.getY(),currentShapeColor , false);
                    selectedRegion = null;
                    
                    System.out.println("entered case 1 square");
                    System.out.println("Current Color = " + currentShapeColor);
                   
                    
                  
                    can.repaint();
                    break;
                    
                
                  case 4:
                    
                    currentShape = new Rectangle(event.getX(), event.getY(),currentShapeColor , false);
                    selectedRegion = null;
                    
                    System.out.println("x= " + event.getX() + "y = " + event.getY());
                    System.out.println("entered case 4 Rect");
                    System.out.println("Current Color = " + currentShapeColor);
                   
                    
                  
                    can.repaint();
                    break;
                    
                    
                  case 5 :
                       currentShape = new Triangle(event.getX(), event.getY(),currentShapeColor , false);
                    
                    System.out.println("x= " + event.getX() + "y = " + event.getY());
                    System.out.println("entered case 4 Rect");
                    System.out.println("Current Color = " + currentShapeColor);
                    selectedRegion = null;
                    can.repaint();
                    break;
                     case 7 :
                         
                         selectedRegion = new RoundRect(event.getX() , event.getY(), Color.PINK , false);
                         can.repaint();
                      
                   
                    break;
                  default:
                      break;
            }
        }
        
        
        public void mouseReleased( MouseEvent event )
        {
            
           if(currentShapeType !=0 && currentShapeType < 7){
           currentShape.setX2(event.getX());
           currentShape.setY2(event.getY());
           System.out.println("in canvas " + currentShape.getX1() +"in canvas " + currentShape.getX2() + ""
                   + " in canvas "+ currentShape.getY1() + " " + currentShape.getY2() );
           
           //currentShape.setEndPoint(e);
           int x = currentShape.getX2() - currentShape.getX1();
           int y = currentShape.getY2() - currentShape.getY1();
           
           //System.out.println("");
           int d = (int) Math.hypot(x , y );
           currentShape.setLength(d);
           
           System.out.println("length is " + currentShape.getLength() );
           
           
            //myShapes.add(currentShape);
            Shapes.addFirst(currentShape);
            
          
        }else if (currentShapeType == 7){
            selectedRegion.setX2(event.getX());
           selectedRegion.setY2(event.getY());
           

            //currentShape.setEndPoint(e);
           int x = selectedRegion.getX2() - selectedRegion.getX1();
           int y = selectedRegion.getY2() - selectedRegion.getY1();
           
           //System.out.println("");
           int d = (int) Math.hypot(x , y );
           selectedRegion.setLength(d);
     
        
        }
            
           can.repaint();
        } // end method mouseReleased
        
        /**
         * This method gets the mouse pos when it is moving and sets it to statusLabel.
         */
        public void mouseMoved( MouseEvent event )
        {
            //statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
        } // end method mouseMoved
        
       
        public void mouseDragged( MouseEvent event )
        {
            //sets currentShapeObject x2 & Y2
            if(currentShapeType != 0 && currentShapeType < 7 ){
            
           currentShape.setX2(event.getX());
           currentShape.setY2(event.getY());
           
           int x = currentShape.getX2() - currentShape.getX1();
           int y = currentShape.getY2() - currentShape.getY1();
           
         //  selectedRegion.setX2(event.getX());
         //  selectedRegion.setY2(event.getY());
           
           int d = (int) Math.hypot(x , y );
           currentShape.setLength(d); 
            
            
            }if(currentShapeType == 7){
            
                selectedRegion.setX2(event.getX());
           selectedRegion.setY2(event.getY());
           
           int x = selectedRegion.getX2() - selectedRegion.getX1();
           int y = selectedRegion.getY2() - selectedRegion.getY1();
           
           int d = (int) Math.hypot(x , y );
           selectedRegion.setLength(d); 
           
            
            }
           
          // selectedRegion.setLength(d);
            can.repaint();
            
        } // end method mouseDragged
        
    }
  
    
    
    
}//end of MyController class

 