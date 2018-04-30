/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import java.awt.Color;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import paint.model.AbstractShape;
import paint.model.Circle;
import paint.model.Ellipse;
import paint.model.Line;
import paint.model.Rectangle;
import paint.model.ShapeFactory;
import paint.model.Square;
import paint.model.Triangle;

/**
 *
 * @author Amira
 */









public class XML extends SaveAndLoad {


    static int count = 0;
    
  /*  public ArrayList<AbstractShape> RXML (ArrayList<String> ss)
{
ArrayList<Shape1> sh = new ArrayList<Shape1> ();
     Shape1 shape = new Shape1();
     Iterator<String> it = ss.iterator();
    while (it.hasNext())
    {
       if ("Circle".equals(it.next()))
       {
       shape=ShapeFactory.createShape("Circle",Integer.valueOf(it.next()),);
       shape.setName("Circle");
       
       }
       else if ("Triangle".equals(it.next()))
           {
      shape= ShapeFactory.createShape("Triangle");
      shape.setName("Triangle");
       }
     else if ("Square".equals(it.next()))
           {
     shape=  ShapeFactory.createShape("Square");
     shape.setName("Square");
       }
     else {
         shape.setX1(Integer.valueOf(it.next()));
        //  shape.set1nteger.valueOf(it.next()));
          
     }
    }
return sh;
}*/
    @Override
    public LinkedList<AbstractShape>  read  (String path)throws Exception{
        
       LinkedList<AbstractShape> sh =  new LinkedList<AbstractShape>();
       try {
            File xmlFile =new File(path); // change file path
      
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder =documentBuilderFactory.newDocumentBuilder();
            Document doc = (Document) docBuilder.parse(xmlFile);
            Node root = doc.getFirstChild();
            NodeList shapes = root.getChildNodes();
      
            //System.out.println(list.getLength());
            for (int i=0; i< shapes.getLength(); i++) {
                Element shapeElement = (Element) shapes.item(i);
                String type = shapeElement.getElementsByTagName("name").item(0).getTextContent();
                String x1 = shapeElement.getElementsByTagName("X1").item(0).getTextContent();
                String y1 = shapeElement.getElementsByTagName("Y1").item(0).getTextContent();
                String x2 = shapeElement.getElementsByTagName("X2").item(0).getTextContent();
                String y2 = shapeElement.getElementsByTagName("Y2").item(0).getTextContent();
                String framecolor =(shapeElement.getElementsByTagName("frameColor").item(0).getTextContent());
                String fillcolor = (shapeElement.getElementsByTagName("fillColor").item(0).getTextContent());
             
               
               
                          
                        
             
                AbstractShape shape;
                shape = ShapeFactory.createShape(Integer.parseInt(type),Integer.parseInt(x1),Integer.parseInt(y1),Color.getColor(framecolor),Boolean.parseBoolean(fillcolor));
                
               // shape.setX1(Integer.parseInt(x1));
               // shape.setY1(Integer.parseInt(y1));
                shape.setX2(Integer.parseInt(x2));
                shape.setY2(Integer.parseInt(y2));
               // shape.setColor(Color.getColor(framecolor));
                sh.add(shape);
              //  System.out.println("readxml method :" + shape.getClass().getSimpleName());
                //System.out.println("readxml method :" + shape.getX1());
            }
            for (AbstractShape ff : sh){
                
             System.out.println(" gui readxml method :" + ff.getClass().getSimpleName());
           System.out.println(" gui readXML method :"+ff.getX1() +5);
            }
            
          
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return sh;
    }
     
     
     
    
    @Override
    public void write (LinkedList<AbstractShape> shapes ,String path)throws Exception{
        
       
       DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder =documentBuilderFactory.newDocumentBuilder();
        Document doc = (Document) docBuilder.newDocument();
        Element root = doc.createElement("Shapes"); // tag name 
        doc.appendChild(root);
        for (AbstractShape sh:shapes) {
            
           int m = 0;
            if( sh instanceof Circle ){
            m=1;
           }
            if( sh instanceof  Ellipse){
            m=2;
           }
            if( sh instanceof Line ){
            m=6;
           }
            if( sh instanceof Rectangle ){
            m=4;
           }
            if( sh instanceof Square ){
            m=3;
           }
            if( sh instanceof Triangle) { 
                m=5;
           
              }
            System.out.println(m);
            count++;
            Element element = doc.createElement("shape");

            Attr attr = doc.createAttribute("shapeNo.");
            attr.setValue("");
            element.setAttributeNode(attr);
            Element ShapeType = doc.createElement("name");
            ShapeType.appendChild(doc.createTextNode(""+m));
            element.appendChild(ShapeType);
            Element X1 = doc.createElement("X1");
            X1.appendChild(doc.createTextNode(Integer.toString(sh.getX1())));
            element.appendChild(X1);

            Element Y1 = doc.createElement("Y1");
            Y1.appendChild(doc.createTextNode(Integer.toString(sh.getY1())));
            element.appendChild(Y1);

          Element X2 = doc.createElement("X2");
            X2.appendChild(doc.createTextNode(Integer.toString(sh.getX2())));
            element.appendChild(X2);
            Element Y2 = doc.createElement("Y2");
            Y2.appendChild(doc.createTextNode(Integer.toString(sh.getY2())));
            element.appendChild(Y2);

            Element frameColor = doc.createElement("frameColor");
            frameColor.appendChild(doc.createTextNode(sh.getColor().toString()));
            element.appendChild(frameColor);

            Element fillColor = doc.createElement("fillColor");
            fillColor.appendChild(doc.createTextNode(Boolean.toString(sh.isFilled())));
            element.appendChild(fillColor);
            
            root.appendChild(element);
        }
        
        TransformerFactory transformerFactory =  TransformerFactory.newInstance();
        Transformer transformer =transformerFactory.newTransformer();
        DOMSource source = new DOMSource (doc);
        
        StreamResult streamResult = new StreamResult(new File(path+"Shapes.xml"));
       
        transformer.transform(source, streamResult);
        
    }
    
    
    
    }
    

