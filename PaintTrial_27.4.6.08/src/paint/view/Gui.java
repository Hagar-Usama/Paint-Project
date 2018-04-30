/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.view;

import paint.view.Canvas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import paint.controller.MyController;
import paint.model.AbstractShape;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import paint.controller.Json;
import paint.controller.SaveAndLoad;
import paint.controller.XML;
import paint.model.ShapeFactory;
import static paint.model.ShapeFactory.calcLength;

/**
 *
 * @Hagar Osama
 */

public class Gui extends JFrame {

   private int flag;
   private AbstractShape tempShape;
    
    private JButton[] tools = new JButton[14];
    
    private Canvas can = new Canvas();
    private JButton[] shTools = new JButton[7];
    
    private SaveAndLoad xml = new XML();
     private SaveAndLoad json = new Json();
    //private JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private JFileChooser fc = new JFileChooser();
    String shapeName [] = {"Color", "Circle", "Ellipse", "Square", "Rectangle", "Triangle", "Line"};
    String toolName [] = {"Undo", "Redo", "Select","Set Action", "Move", "Resize", "Fill", "Copy","Delete",
        "Save as XML",  "Save as Json", "Save as PNG" ,"Save as JPEG", "Load File"};
    // +-------------------------------+

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    
    private JPanel[] panels = new JPanel[4];
    Color color = (Color.WHITE);
    
    public Gui( Canvas can) {
        this.can = can;
        this.setSize(700, 700);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setFont(new Font("Serif", Font.PLAIN, 20));
	//JFrame frame = new JFrame();
        
        BorderLayout border = new BorderLayout();
        this.setLayout(border);
        
       // can = new Canvas();
        
       for (int i = 0; i < panels.length; i++) {
          
           panels[i] = new JPanel();
                        
        }

      
       add(panels[0] , border.WEST); // for tools
       add(can , border.CENTER);
       add(panels[1] , border.NORTH); // for tools
       
       
      
       
       
       
       for(int i = 0; i < shTools.length; i++){
       shTools[i] = new JButton(shapeName[i]);
       panels[1].add(shTools[i]);
       
       }
      
        for(int i = 0; i < tools.length; i++){
       tools[i] = new JButton(toolName[i]);
       panels[0] .add(tools[i]);     
       }
       
       //shTools[0].setText("color");
       panels[0].setLayout(new GridLayout(14,1));
       panels[1].setLayout(new GridLayout(1,7));
       
       
       //actionLister
       for (JButton tool : tools) {
                tool.addActionListener(new AcHandler());	
		}
                
       
       for (JButton tool : shTools) {
                tool.addActionListener(new AcHandler());	
		}
       
       
       
       
       //this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setTitle("Paint");
		this.setResizable(true);
      
        
        
//        this.setSize(700, 700);
//	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//	this.setFont(new Font("Serif", Font.PLAIN, 20));
//	//JFrame frame = new JFrame();
//        
//        BorderLayout border = new BorderLayout();
//        this.setLayout(border);
//        
//       for (int i = 0; i < panels.length; i++) {
//          
//           panels[i] = new JPanel();
//                        
//        }
//
//      
//       add(panels[0] , border.WEST); // for tools
//       add(can , border.CENTER);
//       add(panels[1] , border.NORTH); // for tools
//       
//       
//       for(int i = 0; i < tools.length; i++){
//       tools[i] = new JButton("Tool");
//       panels[0] .add(tools[i]);     
//       }
//       
//       
//       for(int i = 0; i < shTools.length; i++){
//       shTools[i] = new JButton(shapeName[i]);
//       panels[1].add(shTools[i]);
//       
//       }
//       
//       //shTools[0].setText("color");
//       panels[0].setLayout(new GridLayout(10,1));
//       panels[1].setLayout(new GridLayout(1,7));
//       
//       
//       //actionLister
//       for (JButton tool : tools) {
//                tool.addActionListener(new AcHandler());	
//		}
//                
//       
//       for (JButton tool : shTools) {
//                tool.addActionListener(new AcHandler());	
//		}
//       
//       
//        
//        //this.setLayout(new BorderLayout());
//		this.setVisible(true);
//		this.setTitle("Paint");
//		this.setResizable(true);
//      
//                
    }

    
    public Gui() {
        
        this.setSize(700, 700);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setFont(new Font("Serif", Font.PLAIN, 20));
	//JFrame frame = new JFrame();
        
        BorderLayout border = new BorderLayout();
        this.setLayout(border);
        
       // can = new Canvas();
        
       for (int i = 0; i < panels.length; i++) {
          
           panels[i] = new JPanel();
                        
        }

      
       add(panels[0] , border.WEST); // for tools
       add(can , border.CENTER);
       add(panels[1] , border.NORTH); // for tools
       
       
       for(int i = 0; i < tools.length; i++){
       tools[i] = new JButton("Tool");
       panels[0] .add(tools[i]);     
       }
       
       
       for(int i = 0; i < shTools.length; i++){
       shTools[i] = new JButton(shapeName[i]);
       panels[1].add(shTools[i]);
       
       }
       
       for(int i = 0; i < tools.length; i++){
       tools[i] = new JButton(toolName[i]);
       panels[1].add(tools[i]);
       
       }
       //shTools[0].setText("color");
       panels[0].setLayout(new GridLayout(10,1));
       panels[1].setLayout(new GridLayout(1,7));
       
       
       //actionLister
       for (JButton tool : tools) {
                tool.addActionListener(new AcHandler());	
		}
                
       
       for (JButton tool : shTools) {
                tool.addActionListener(new AcHandler());	
		}
       
       
        
        //this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setTitle("Paint");
		this.setResizable(true);
      
                
    }
    
  public class AcHandler implements ActionListener{

        
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            Object obj = ae.getSource();
            if (obj == tools[0]){
                
                    
                    can.undo();
                
                         
            }else if(obj == tools[1]){
            
                can.redo();
            System.out.println("fff");
            }else if(obj == tools[2]){
                //select
            flag = 7;
            
            System.out.println("fff");
            }else if(obj == tools[3]){
            //setAction
            flag = 8;
               
            }else if(obj == tools[4]){
            //move
            tempShape = can.getSelectedShape();
             if( tempShape!= null){
               can.move(tempShape);  
               System.out.println("moved");
             }
            //System.out.println("fff");
            }else if(obj == tools[5]){
              //resize  
              tempShape = can.getSelectedShape();
             if( tempShape!= null){
               can.resize(tempShape);  
               System.out.println("resized");
             }
            
            //System.out.println("fff");
            }else if(obj == tools[6]){
                //fill
              tempShape = can.getSelectedShape();
             if( tempShape!= null){
               can.fill(tempShape);  
               System.out.println("filled");
             }
                
            }else if(obj == tools[7]){
                //copy()
                tempShape = can.getSelectedShape();
                if( tempShape!= null)
               can.copy(tempShape);  
           
            }else if(obj == tools[8]){
                
            //delete object    
            
            tempShape = can.getSelectedShape();
             if( tempShape!= null){
              can.delete(tempShape);  
               System.out.println("deleted");
             
             }
             
            } else if(obj == tools[9]){
            ///save xml ***********
                
                fc.setCurrentDirectory(new java.io.File("."));
            fc.setDialogTitle("Save as XML");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(fc.showOpenDialog(tools[9])== JFileChooser.APPROVE_OPTION){
             String path = (fc.getSelectedFile().getAbsolutePath()+"/");
                    try {
                        xml.write(can.getShapes(), path);
                    } catch (Exception ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
            System.out.println(fc.getSelectedFile().getAbsolutePath());
            }
            
            }else if(obj == tools[10]){
            fc.setCurrentDirectory(new java.io.File("."));
            fc.setDialogTitle("Save as Json");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(fc.showOpenDialog(tools[10])== JFileChooser.APPROVE_OPTION){
             String path = (fc.getSelectedFile().getAbsolutePath()+"/");
                    try {
                        json.write(can.getShapes(), path);
                    } catch (Exception ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
            System.out.println(fc.getSelectedFile().getAbsolutePath());
                // save
           
           // path 
           System.out.println(fc.getSelectedFile().getAbsolutePath());
            }
            
            }else if(obj == tools[11]){
             //save as PNG
             String path;
            fc.setCurrentDirectory(new java.io.File("."));
            fc.setDialogTitle("Save as Image");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(fc.showOpenDialog(tools[7])== JFileChooser.APPROVE_OPTION){
            
            System.out.println(fc.getSelectedFile().getAbsolutePath());
           
            } //end of if
            path = fc.getSelectedFile().getAbsolutePath();
                can.setSelectedRegion(null);
                can.saveImage("png" , path  );
                

            
            }
            else if(obj == tools[12]){
            
                //save as jpeg
             String path;
            fc.setCurrentDirectory(new java.io.File("."));
            fc.setDialogTitle("Save as Image");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(fc.showOpenDialog(tools[7])== JFileChooser.APPROVE_OPTION){
            
            System.out.println(fc.getSelectedFile().getAbsolutePath());
           
            } //end of if
            path = fc.getSelectedFile().getAbsolutePath();
                can.setSelectedRegion(null);
                can.saveImage("jpeg" , path  );
                
            } else if(obj == tools[13]){
            //////********************** load*********************/////
                //load file
            fc.setCurrentDirectory(new java.io.File("."));
            fc.setDialogTitle("Load");
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(fc.showOpenDialog(tools[8])== JFileChooser.APPROVE_OPTION){
            if(fc.getSelectedFile().getAbsolutePath().endsWith(".xml")){
            System.out.println("it is an xml file");
            // load as xml
            //read>>arraylist >> convert to linkedlist
            String path = (fc.getSelectedFile().getAbsolutePath());
            System.out.println(path);
                try {
                  
                 
                    can.setShapes(xml.read(path));
                  //  can.getShapes().get(1);
                   int size = can.getShapes().size() ;
                  for(int i =0 ;  i< size; i++){
                  
                  
                   calcLength(can.getShapes().get(i));
                  }
                   can.repaint();
                  
                    System.out.println(can.getShapes().get(0) + "," + can.getShapes().get(0).getX1()+ "," + 
                    can.getShapes().get(0).getY1()+ "," + can.getShapes().get(0).getX2()+ "," + 
                    can.getShapes().get(0).getY2()+ "," + can.getShapes().get(0).getColor() + "," + can.getShapes().get(0).getFilled());
                   
                } catch (Exception ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                
                }
           
            }else if(fc.getSelectedFile().getAbsolutePath().endsWith(".json")){
            System.out.println("it is an json file");
            // load as json
            // read by json
            String path = (fc.getSelectedFile().getAbsolutePath());
            System.out.println(path);
                
                 try {   
                    can.setShapes(json.read(path));
                    int size = can.getShapes().size() ;
                  for(int i =0 ;  i< size; i++){
                  
                  
                   calcLength(can.getShapes().get(i));
                  }
                   can.repaint();
                  
                    System.out.println(can.getShapes().get(0) + "," + can.getShapes().get(0).getX1()+ "," + 
                    can.getShapes().get(0).getY1()+ "," + can.getShapes().get(0).getX2()+ "," + 
                    can.getShapes().get(0).getY2()+ "," + can.getShapes().get(0).getColor() + "," + can.getShapes().get(0).getFilled());
                   
                 
                   can.repaint();
                  
                  }
                   
                  catch (Exception ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            }
            System.out.println(fc.getSelectedFile().getAbsolutePath());
              
            
            
            } 
            
            
            
            else if(obj == shTools[0]) // colorChooser actionListener
            {
                
            color = JColorChooser.showDialog(null, "Pick a Color :)", color);
            if(color != null){
            shTools[0].setForeground(color);
            can.setCurrentShapeColor(color);
            
            }else{ shTools[0].setForeground(Color.BLACK);}
            
            can.setCol(color);
            }else if(obj == shTools[1]){
                flag = 1;
                can.setSelectedRegion(null);
            System.out.println("fff");
            }else if(obj == shTools[2]){
            flag = 2;
            can.setSelectedRegion(null);
            System.out.println("fff");
            }else if(obj == shTools[3]){
            flag = 3;
            can.setSelectedRegion(null);
            System.out.println("fff");
            }else if(obj == shTools[4]){
            flag = 4;
            can.setSelectedRegion(null);
            System.out.println("fff");
            }else if(obj == shTools[5]){
            flag = 5;
            can.setSelectedRegion(null);
            System.out.println("fff");
            }else if(obj == shTools[6]){
            flag = 6;
            can.setSelectedRegion(null);
            System.out.println("fff");
            }
            
          can.setCurrentShapeType(flag);
          System.out.println("flag= " + flag);
          System.out.println( " currentSHapeType in  Gui = "+can.getCurrentShapeType());
            
        }       
                
             }
     
    //private javax.swing.JPanel jPanel1;
}


