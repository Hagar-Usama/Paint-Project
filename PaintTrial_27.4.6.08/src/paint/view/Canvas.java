/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.view;

/**
 *
 * @author Hagar OSama
 */

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JPanel;
import paint.controller.Memento;
import paint.model.AbstractShape;
import paint.model.Circle;
import paint.model.Ellipse;
import paint.model.Line;
import paint.model.Rectangle;
import paint.model.RoundRect;
import paint.model.Triangle;
import paint.model.Shape;
import static paint.model.ShapeFactory.createShape;
import paint.model.Square;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hagar Osama
 */
public class Canvas extends JPanel {
     
    private Point startPoint = new Point();
    private Point endPoint = new Point();
    
    private int pointer = -1;
    
     private LinkedList<AbstractShape> Shapes;
     private LinkedList<AbstractShape> undoShapes;
      private LinkedList<AbstractShape> pShape;
      
     private LinkedList<Memento> memento = new LinkedList<>(); 
     
     private Memento tempMemento ;
     
     private int selectedIndex;
     private RoundRect selectedRegion;
     private Line mov;
     
     private AbstractShape tempo;
     private AbstractShape tempoMov;
     private AbstractShape cShape;
     private AbstractShape clShape;
     private AbstractShape coShape;
     private Square sq;
    // private boolean isSelected;

    public void setSelectedRegion(RoundRect selectedRegion) {
        this.selectedRegion = selectedRegion;
    }
     
     
    
    Color col = Color.green;

    
    AbstractShape getSelectedShape(){
    
        int x1 ,x2 ,y1, y2 , selX1, selX2 , selY1 , selY2 , maxX , minX , maxY,minY;
        
       
        selX1 = selectedRegion.getX1();
        selX2 = selectedRegion.getX2();
        selY1 = selectedRegion.getY1();
        selY2 = selectedRegion.getY2();
        
        maxX = Math.max(selX1,selX2);
        minX = Math.min(selX1,selX2);
        
        maxY = Math.max(selY1,selY2);
        minY = Math.min(selY1,selY2);
        boolean cond;
        
        for ( int counter=Shapes.size()-1; counter>=0; counter-- ){
            
            //same address >> same pointer >> same access
        tempo = Shapes.get(counter);
        x1 = Shapes.get(counter).getX1();
        x2 = Shapes.get(counter).getX2();
        y1 = Shapes.get(counter).getY1();
        y2 = Shapes.get(counter).getY2();
        
        cond = x1>minX && x1<maxX && y1>minY && y1<maxY;
        
        if(cond){
        //Shapes.remove(counter);
        selectedIndex = counter;
        return tempo;
                
        }
         
            
    }
          
    return null;
    }
    
    public void delete(AbstractShape shape){
    
    if(shape != null){
    
    shape.setColor(Color.WHITE);
    shape.setFilled(false);
    Shapes.addFirst(shape);
    
    }
    //repaint();
    //Shapes.remove(tempo);
    repaint();
    
    
    }
    
    
    public void copy(AbstractShape shape){
    
        
        coShape = clone(shape);

            //mov (line oject to set where to copy) >> getfrom
        coShape.setX1(mov.getX2());
        coShape.setY1(mov.getY2());
        
        //lacks memo
        Shapes.addFirst(clShape);
        repaint();
    
    }
    
    public void move(AbstractShape shape){
    
        
    shape.setX1(mov.getX2() );
    shape.setX2(mov.getX1() );
    shape.setY1(mov.getY2() );
    shape.setY2(mov.getY1() );
    
   
    //lacks memo
    Shapes.poll();
   
    Shapes.addFirst(shape);
    selectedRegion = null;
    mov = null;
    repaint();
    
    }
    
    public void resize(AbstractShape shape){
    
           int x = mov.getX2() - shape.getX1();
            int y = mov.getY2() - shape.getY1();
           
           int d = (int) Math.hypot(x , y );
           shape.setLength(d);
        
        if(mov.getX1() > mov.getX2()){
        
            shape.setLength((int) (d*0.2));
            
        }else{
           
         
             shape.setLength((int)(d*1.3));
          
        
        }
    
    
    //lacks memo
    Shapes.poll();
   
    Shapes.addFirst(shape);
    selectedRegion = null;
    mov = null;
    repaint();
    
    }
    
     public void fill(AbstractShape shape){
    
    
         
    //undoShapes.addLast(shape);
    
    if(shape != null){
    
    shape.setColor(currentShapeColor);
    shape.setFilled(true);
    Shapes.addLast(shape);
    
    }
    repaint();
    
    }
     
     public void undo(){
     int index =memento.size()-1;
     
     /// pointer is initialized >> -1
     pShape = new LinkedList<>();
    
     
     if(index >= 1){
     
     System.out.println("index1 is" + index);
     if(pointer <=-1){
     pointer = index-1;
     pShape =  memento.get(pointer).getShapes();
     Shapes = (LinkedList<AbstractShape>) pShape;
      System.out.print("shapes are : " + Shapes);
      repaint();
     }else if(pointer > 0){
     
         pointer--;
         pShape =  memento.get(pointer).getShapes();
         Shapes = (LinkedList<AbstractShape>) pShape;
         System.out.print("shapes are : " + Shapes);
         repaint();
     }else{
     System.out.println("else is " + pointer);
     
     }
     
     }
     
     }
     
     
     
     public void redo(){
     int index =memento.size()-1;
     
     /// pointer is initialized >> -1
     pShape = new LinkedList<>();
     
     if(index >= 0){
     
     System.out.println("index1 is" + index);
     if(pointer <=-1){
     pointer = index-1;
     pShape =  memento.get(pointer).getShapes();
     Shapes = (LinkedList<AbstractShape>) pShape;
      System.out.print("shapes are : " + Shapes);
      repaint();
     }else if(pointer >= 0){
     
         pointer++;
         pShape =  memento.get(pointer).getShapes();
         Shapes = (LinkedList<AbstractShape>) pShape;
         System.out.print("shapes are : " + Shapes);
         repaint();
     }else{
     System.out.println("else is " + pointer);
     
     }
     
     
     
     
     
     }
     
     }
     
     
    public Color getCol() {
        return col;
    }

    public LinkedList<AbstractShape> getShapes() {
        return Shapes;
    }

    
    public AbstractShape popShape(){
    
    return Shapes.pop();
    
    }
    
    public void pushShape(AbstractShape shape){
    this.Shapes.push(shape);
    
    
    
    }
    
   /* 
    public void undo(){
       
        if(!Shapes.isEmpty()){
            
            //cShape = clone(Shapes.pop());
            
            
            undoShapes.push(Shapes.pop());
        }
        
        
       currentShape = null;
        repaint();
        
    
    }
    
    */
    
    /*
    public void redo(){
       
        if(!undoShapes.isEmpty()){
        Shapes.push(undoShapes.pop());
        }
        
       currentShape = null;
        repaint();
        
    
    }
    
    */
    public AbstractShape popUndo(){
    
    return undoShapes.pop();
    
    }
    
    public void pushUndo(AbstractShape shape){
    
    this.undoShapes.push(shape);
   
    }
    public void setShapes(LinkedList<AbstractShape> Shapes) {
        this.Shapes = Shapes;
    }
    public LinkedList<AbstractShape> getUndoShapes() {
        return undoShapes;
    }

    public void setUndoShapes(LinkedList<AbstractShape> undoShapes) {
        this.undoShapes = undoShapes;
    }

    public void setCol(Color col) {
        this.col = col;
    }
    
    
    //private ArrayList<AbstractShape> myShapes;
    private ArrayList<AbstractShape> shapesDel;
    
    
   
    
    
    public void addUndo(AbstractShape shape) {
        this.shapesDel.add(shape);
    }

     public AbstractShape getUndo() {
         int size;
        return this.shapesDel.get(1);
        
    }
    public ArrayList<AbstractShape> getShapesDel() {
        return shapesDel;
    }

    public void setShapesDel(ArrayList<AbstractShape> shapesDel) {
        this.shapesDel = shapesDel;
    }
   
    
   
   
    private int currentShapeType;
    private AbstractShape currentShape; 
    private Color currentShapeColor;
    private boolean currentShapeFilled; 

    public Color getCurrentShapeColor() {
        return currentShapeColor;
    }

    public void setCurrentShapeColor(Color currentShapeColor) {
        this.currentShapeColor = currentShapeColor;
    }
    

    
    
    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public int getCurrentShapeType() {
        return currentShapeType;
    }

    public void setCurrentShapeType(int currentShapeType) {
        this.currentShapeType = currentShapeType;
    }

    public AbstractShape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(AbstractShape currentShape) {
        this.currentShape = currentShape;
    }
    
    
    private ArrayList<Shape> ListOfShapes;

    public ArrayList<Shape> getListOfShapes() {
        return ListOfShapes;
    }

    public void setListOfShapes(ArrayList<Shape> ListOfShapes) {
        this.ListOfShapes = ListOfShapes;
    }

    public Canvas() {
               
        //myShapes = new ArrayList<AbstractShape>();
        shapesDel = new ArrayList<AbstractShape>();
        
        Shapes = new LinkedList<AbstractShape>();
        undoShapes = new LinkedList<AbstractShape>();
        memento = new LinkedList<Memento>();
        
        /*
        Line lin1 = new Line(50,10,56,74,Color.RED);
        Line lin2 = new Line(145,101,76,23,Color.darkGray);
        Point p1 = new Point(12,122);
        Point p2 = new Point(14,136);
        */
        //Circle circ = new Circle( p1 , p2, Color.YELLOW ,false );
    /*
        myShapes.add(lin1);
        myShapes.add(lin2);
        myShapes.add(circ);
      */  
        //Initialize current Shape variables
        currentShapeType=0;
        currentShape=null;
        currentShapeColor=Color.BLACK;
        currentShapeFilled=false;
        
        //this.statusLabel = statusLabel; //Initialize statusLabel
        
        setLayout(new BorderLayout()); 
        setBackground( Color.WHITE ); 
       
        MouseHandler handler = new MouseHandler();                                    
        addMouseListener( handler );
        addMouseMotionListener( handler );
    }
     
  
    private class MouseHandler extends MouseAdapter 
    {
        public void mousePressed( MouseEvent event )
        {
            System.out.println("ENtered mouse pressed");
            System.out.println("currentShapeType = " + currentShapeType);
            
            if( currentShapeType >0 && currentShapeType < 7)
               {  currentShape = createShape(currentShapeType , event.getX(), event.getY(),currentShapeColor , false);
             
           }else if(currentShapeType == 7)
               selectedRegion = new RoundRect(event.getX() , event.getY(), Color.BLACK , false);
                       
           else if( currentShapeType == 8)
               mov = new Line(event.getX() , event.getY(), Color.DARK_GRAY , false);
            
            
            repaint();
                      
            
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
            pointer++;
            
            ////////////////////////////////////////////////////////
            //////////////////////Memento //////////////////////////
            /////////////////////////////////////////////////////////
            
           
            tempMemento = new Memento(Shapes);
             memento.add(tempMemento);
            
            ////////////////////////////////////////////////////////
            ///////TOOOO ADD LIST OF SHAPES To MEMENTOO /////////////
            /////////////////////////////////////////////////////////
            
        }else if (currentShapeType == 7){
            selectedRegion.setX2(event.getX());
           selectedRegion.setY2(event.getY());
           

            //currentShape.setEndPoint(e);
           int x = selectedRegion.getX2() - selectedRegion.getX1();
           int y = selectedRegion.getY2() - selectedRegion.getY1();
           
           //System.out.println("");
           int d = (int) Math.hypot(x , y );
           selectedRegion.setLength(d);
           
        }else if(currentShapeType == 8){
           mov.setX2(event.getX());
           mov.setY2(event.getY());
            
           int x = mov.getX2() - mov.getX1();
           int y = mov.getY2() - mov.getY1();
           
           int d = (int) Math.hypot(x , y );
           mov.setLength(d);
           
           
        
        }
            
           repaint();
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
           
            
            }else if( currentShapeType == 8){
             mov.setX2(event.getX());
           mov.setY2(event.getY());
           
           int x = mov.getX2() - mov.getX1();
           int y = mov.getY2() - mov.getY1();
           
           int d = (int) Math.hypot(x , y );
           mov.setLength(d); 
            
            }
            
           
          // selectedRegion.setLength(d);
            repaint();
            
        } // end method mouseDragged
        
    }
    
    public AbstractShape clone(AbstractShape shape){
     int type=1;   
        
    if(shape instanceof Circle){
            type = 1;
            System.out.println("it's a circle");
    
    }else if(shape instanceof Ellipse){
        type = 2;
        System.out.println("it's an Ellipse");
    }else if(shape instanceof Square){
        type = 3;
        System.out.println("it's a Square");
    }else if(shape instanceof Rectangle){
        type = 4;
    System.out.println("it's a Rect");
    }else if(shape instanceof Triangle){
        type = 5;
    System.out.println("it's a Triangle");
    }else if(shape instanceof Line){
        type = 6;
        System.out.println("it's a Line");
    
    }else{
    
    System.out.println("unknown Type");
    }
        
        clShape = createShape(type , shape.getX1(),shape.getY1(),shape.getColor(),shape.getFilled());
        clShape.setX2(shape.getX2());
        clShape.setY2(shape.getY2());
        return clShape;
    
    
    }
    
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        // draw the shapes
        //ArrayList<AbstractShape> shapeArray=myShapes.getArray()
                 
        for ( int counter=Shapes.size()-1; counter>=0; counter-- ){
            if(Shapes.get(counter) != null) {
                Shapes.get(counter).drawShape(g);
            } 
        }
           
        
        //draws the current Shape Object if it is not null
        if (currentShape!=null)
            currentShape.drawShape(g);
        
        if(selectedRegion !=null)
        selectedRegion.drawShape(g);
        
         if(mov !=null)
         mov.drawShape(g);
         
         
        
        repaint();
        
        
//        Graphics2D g2 = (Graphics2D) g;
//        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
//
//        g2.setPaint(Color.RED);
//        g2.setStroke(dashed);
//        
        
         //Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        //g2d.setStroke(dashed);
       /*
        double x = 20;
        double y = selectedRegion.getY1();
        if(currentShapeType == 7){
        int sidelen = (int)(selectedRegion.getLength() / Math.sqrt(2)) ;
        
    }
        */
        
        //g2.draw(new RoundRectangle2D.Double(20,70, 100, 150, 50, 50));
        
    }
    
    
    /*@Override
    public void paintComponent(Graphics g){
    
    super.paintComponent(g);
    //setBackground(Color.BLACK);
    
    g.setColor(Color.black);
    g.fillRect(20, 20, 100, 50);
    Shape x = new Circle();
    
    x.setStartPoint(new Point(200, 200));
    HashMap<String, Double> properties = new HashMap<>();
    properties.put("diameter", (double) 100);
    x.setProperties(properties);
    x.draw(g);
    
    for(Shape shape : ListOfShapes)
        shape.draw(g);
    
    }
    */
    
     
     public void saveImage(String extension , String path){
    BufferedImage imagebuf=null;;
    try {
        imagebuf = new Robot().createScreenCapture(this.getBounds());
    } catch (AWTException e1) {
        
        e1.printStackTrace();
    }  
     Graphics2D graphics2D = imagebuf.createGraphics();
     this.paint(graphics2D);
     try {
         
          ImageIO.write(imagebuf,extension, new File(path + File.separator + "imag" + "." + extension));
       
    } catch (Exception e) {
        
        System.out.println("error");
    }
}

     
}

