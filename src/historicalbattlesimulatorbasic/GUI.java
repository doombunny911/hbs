/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Andrew
 */
//the overall animation class of the project, every class regarding animation
//should go through this in some degree
//will be used for mouse listening too
public class GUI implements MouseListener
{
    static JFrame gameFrame;
    static Map gameMap;
    static Tile[][] tileGameMap;
    static int tileWidth;
    static JPanel panel;
    static ArrayList<UnitDraw> units=new ArrayList<>();
    static Tile tileClicked;
    static UnitLoader loader;
    static int unitNum;
    
    static Tile getTileClicked() 
    {
        return GUI.tileClicked;
    }
   //initualize GUI whenever need to have a new Panel with mouselistener
   public GUI(JPanel panel)
   {
       GUI.panel=panel;
       System.out.println("mouseListener check");
       GUI.panel.addMouseListener(this);
   }
   public static void addToFrame(Component c)
   {
       gameFrame.add(c);
//       this.repainter();
   }
   public JFrame getFrame(JFrame frame)
   {
        return gameFrame;
   }
   
   public void repainter()
   {
       gameFrame.validate();
       gameFrame.repaint();
   }
   public boolean checkInitalization()
   {
       return GUI.gameMap != null && GUI.tileGameMap != null && GUI.tileWidth != 0;
   }
   
   
   //not in final spots, didn't want 
//   to waste time finding the optimal spots atm
   public static void buttonLoader()
   {
      
       JPanel buttonPanel = new JPanel();
       JButton attack=new JButton("Attack");
       JButton defend=new JButton("Defend");
       JButton checkStats = new JButton("Check Stats");
       JButton move = new JButton("Move");
       JButton specialAbility =new JButton("Special Ability");
       JButton cancel = new JButton("Cancel Selection");

       int frameHeight=GUI.panel.getHeight()-100;
       int frameWidth = GUI.panel.getWidth()/6-100;
       int frameHalfway = GUI.panel.getWidth()/2;
       attack.setBounds(frameWidth,frameHeight,100,30);
       defend.setBounds(frameWidth*2,frameHeight,100,30);
       checkStats.setBounds(frameWidth*3,frameHeight,150,30);
       move.setBounds(frameWidth*4+100,frameHeight,100,30);
       specialAbility.setBounds(frameWidth*5+100,frameHeight,150,30);
       cancel.setBounds(frameWidth*6+100,frameHeight,150,30);
       
       attack.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               //attack the unit that is targeted next
           }
       });
       
       defend.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae)
           {
               //switch unit into "defense"
           }
       });
       
       
       checkStats.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               //get the stats of the unit clicked
               //print them on side of screen
           }
       });
       move.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               //move
           }
       });
       specialAbility.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               System.out.println("SpecialAbility Activate!");
           }
       });
       cancel.addActionListener(new ActionListener() 
       {

           @Override
           public void actionPerformed(ActionEvent ae)
           {
               //deselect Unit 
               //make buttons disapear/click through or delete and remake
           }
       });
       
       GUI.panel.setLayout(null);
       GUI.panel.add(attack);
       GUI.panel.add(defend);
       GUI.panel.add(checkStats);
       GUI.panel.add(move);
       GUI.panel.add(specialAbility);
       GUI.panel.add(cancel);
//       SpringLayout layout=new Springlayout();
//       buttonPanel.setLayout(layout);
//       System.out.println(Openingmenuscreen.tilePanel);
//       System.out.println(GUI.panel);
       System.out.println(GUI.panel.getHeight()+ "height");
       System.out.println(GUI.panel.getWidth()+ "width");
       buttonPanel.setBounds(0,GUI.panel.getHeight()-150, GUI.panel.getWidth()-1, 150);
//       buttonPanel.setBounds(300,400,350,350);
//       buttonPanel.setLocation(300,500);
//       GUI.panel.add(buttonPanel);
//       GUI.panel.setLayout(lm);
   }
   //puts the buttons on a panel instead of directly on the tilePanel
   public void buttonsOnPanel(JButton[] button)
   {
       
   }
   
   public static void copy(Component c,Component d)
   {
       
       d.setBounds(0, 0,c.getWidth(),c.getHeight());
       
//       r.getBounds
//       return r;
   }
   
    @Override
    public void mousePressed(MouseEvent me) 
    {
    }
    @Override
    public void mouseClicked(MouseEvent mac) 
    {
        //while there are still units that need to be loaded, 
        //wait for a tile to be clicked and than paint it
        System.out.println("Mouse clicked");
        double findTileX= Math.ceil(mac.getX()/10);
        double findTileY=Math.ceil(mac.getY()/10);
        GUI.tileClicked=GUI.tileGameMap[(int)findTileY][(int)findTileX];

        Tile clickedTile;
        System.out.println(GUI.tileGameMap[(int)findTileY][(int)findTileX]);
        while(GUI.tileClicked!=null&&unitNum!=0)
         {
              
//  

             System.out.println("Unitnum!=0");
//            
             System.out.println(GUI.tileClicked+"Right before condition");
             
                 
//                 System.out.println("GUI.tileClicked!=null");
                 System.out.println(unitNum+ " Before anything happens ");
                 Unit unit=  UnitLoader.allUnits.get(GUI.unitNum-1);
//                 System.out.println(GUI.tileClicked);
                 
                 unit.setPosition(GUI.tileClicked.xPosition,GUI.tileClicked.yPosition);
                 System.out.println("place unit " +unit.unitName + " at (" +GUI.tileClicked.xPosition+","+GUI.tileClicked.yPosition+") ");
                 GUI.units.add(new UnitDraw(unit));
                 GUI.tileClicked=null;
                 unitNum--;
                 System.out.println("UnitNum after sub ="+ unitNum);
                 GUI.panel.repaint();
//             System.out.println("GUI.tileClicked after"); 

         }
             
         
//         for(Unit selectedUnit: allUnits)
//            {
//                System.out.println("Enter x position: ");
//                System.out.println(GUI.tileClicked);
//                int x = clickedTile[i].xPosition;
//                System.out.println("Enter y position: ");
//                int y = clickedTile[i].yPosition;
//                selectedUnit.setPosition(x,y);
//                GUI.units.add(new UnitDraw(selectedUnit));
//                i++;
//            }
//       
        
        //may have to catch java.lang.ArrayIndexOutOfBoundsException, 
        //or actually probably better to round click to nearest tile
        
        //this is the tile that was clicked on, currently doing nothing
//        System.out.println("tileXSpot " +findTileX);
//        System.out.println("fileYSpot " +findTileY);
       
    }
    
    @Override
    public void mouseReleased(MouseEvent me) 
    {
    }

    @Override
    public void mouseEntered(MouseEvent me) 
    {
    }

    @Override
    public void mouseExited(MouseEvent me)
    {
    }
  
}
