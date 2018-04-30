/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.view;

import java.util.ArrayList;
import java.util.LinkedList;
import paint.controller.MyController;
import paint.controller.SaveAndLoad;
import paint.controller.XML;
import paint.model.AbstractShape;
import paint.model.Circle;
import paint.model.Square;
import paint.model.Triangle;

/**
 *
 * @author cuteycat_2017
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //Canvas can = new Canvas();
        MyController con = new MyController();
            
            /*  new Gui().setVisible(true);
        });
        */ LinkedList<AbstractShape> shapes= new LinkedList<AbstractShape>();
      
     AbstractShape  circ = new Circle();
         circ.setX1(10);
         circ.setY1(20);
       //circ.setName( "Circle");
     //  circ.setX2(153);
       
       
       AbstractShape sqr = new Square();
       sqr.setX1(55);
       sqr.setY1(0);
       sqr.setName("Square");
       
       AbstractShape tri = new Triangle();
       tri.setX1(105);
       tri.setY1(12);
       tri.setName("Triangle");
       
       
       shapes.add(circ);
       shapes.add(sqr);
       shapes.add(tri);
       
           
     SaveAndLoad gg = new XML();
     //gg.write(shapes,"D:/Amira/" );
 //  gg.read("D:/Amira/Shapes.xml");
    }
}
