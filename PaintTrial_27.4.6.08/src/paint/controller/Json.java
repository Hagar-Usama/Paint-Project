/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import com.google.gson.Gson;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.stream.JsonWriter;
//import java.io.File;
//import java.nio.file.Files;
//import java.util.HashMap;
import java.util.LinkedList;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import paint.model.AbstractShape;
import paint.model.Circle;
import paint.model.Ellipse;
import paint.model.Line;
import paint.model.Rectangle;
import paint.model.Shape1;
import paint.model.Square;
import paint.model.Triangle;
//import org.apache.commons.io.FileUtils;

/**
 *
 * @author Amira
 */
public class Json extends SaveAndLoad{
    
    
    





    
     public  void createJson(String path){
        
        // create json oblect and set values to it  
    JSONObject obj = new JSONObject();
   
    
    
    
    // create json array and add values
    JSONArray  list = new JSONArray(); // array 
   /* for (Iterator<Shape> i = ss.iterator(); i.hasNext();)
    {list.add(i.next());
    }*/
    // add json array to json object
    obj.put("courses", list); // the key is "courses" and the value is the entire "list"
     
    try( FileWriter file = new FileWriter (path)) // file name with " .json " extension   
    {
        file.write(obj.toString());
        file.flush();
        file.close();
    }catch(IOException e) {
        e.printStackTrace();
    }
    
    System.out.println(obj);
      
    }
     
      ArrayList <String> shapeString= new ArrayList<String>();
    public  ArrayList <String> readJson(String path){ // just take the String array and convert it to
            JSONParser parser= new JSONParser();
        
        try 
        {
            Object obj = parser.parse(new FileReader(path+"Shapes.json")); // file name with " .json " extension     
           JSONObject jsonObject =(JSONObject) obj;
          
      
           // loop array
           
            ArrayList <Shape1> shapes= new ArrayList<Shape1>();
           
            JSONArray shapesArray =(JSONArray) jsonObject.get("Shapes");
            
         
             Iterator<String> iterator = shapesArray.iterator();
            
            while(iterator.hasNext() )
            {  // shapes.add(iterator.next());
            
           String s = iterator.next();
         //  System.out.println("shapes :  " +s);
           shapeString.add(s);
             
             
            }
           
           
                  
                
                for( String d : shapeString)
                  {
                      System.out.println(d);
                  }
              
            }
            
            
            
        catch (FileNotFoundException e){ e.printStackTrace();}
          catch (IOException e){ e.printStackTrace();}
          catch (ParseException e){ e.printStackTrace();}
          catch (Exception e){ e.printStackTrace();}
        
          return shapeString;
   
    }
     
    
    

     @Override
    public void write (LinkedList<AbstractShape> ss , String path) throws Exception{
  
  // create json oblect and set values to it  
    JSONObject obj = new JSONObject();
    int count=0;
    
    // create json array and add values
    JSONArray  list = new JSONArray(); // array 
   int m= 0;
  for (AbstractShape sh : ss)
   {     
      //list.add( item.getName().toString());
        if( sh instanceof Circle ){
            m=1;
            System.out.println(m);
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
            System.out.println(m);
           }
            if( sh instanceof Triangle) { 
                m=5;
                System.out.println(m);
           
              }
       list.add(Integer.toString(sh.getX1()));
       list.add(Integer.toString(sh.getY1()));
     /*  list.add( Integer.toString(sh.getX2()));
       list.add( Integer.toString(sh.getY2()));*/
       list.add(sh.getColor().toString());
       list.add( Boolean.toString(sh.getFilled()));
   }
// add json array to json object
    obj.put("Shapes", list); // the key is "courses" and the value is the entire "list"
     
  
    try( FileWriter file = new FileWriter (path+"Shapes.json")) // file name with " .json " extension   
    {
        file.write(obj.toString());
        file.flush();
        file.close();
    }catch(IOException e) {
        e.printStackTrace();
    }
    
    System.out.println(obj);
      
}
    

@Override
    public LinkedList<AbstractShape>  read  (String path)throws Exception{
               JSONParser parser= new JSONParser();
                LinkedList <AbstractShape> shapes= new LinkedList<AbstractShape>();
        
        try 
        {
            Object obj = parser.parse(new FileReader(path+"Shapes.json")); // file name with " .json " extension     
           JSONObject jsonObject =(JSONObject) obj;
         
           
           
           
            JSONArray shapesArray =(JSONArray) jsonObject.get("Shapes");
            
         
             Iterator<String> iterator = shapesArray.iterator();
            
            while(iterator.hasNext() )
            {  // shapes.add(iterator.next());
            
           String s = iterator.next();
         //  System.out.println("shapes :  " +s);
           shapeString.add(s);
             
            
            }
           
           
                  
                
                for( String d : shapeString)
                  {
                      System.out.println(d);
                  }
              
            }
            
            
            
        catch (FileNotFoundException e){ e.printStackTrace();}
          catch (IOException e){ e.printStackTrace();}
          catch (ParseException e){ e.printStackTrace();}
          catch (Exception e){ e.printStackTrace();}
        
          return shapes;
    
    }
}
