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
    
    
    static Tile getTileClicked() 
    {
        return GUI.tileClicked;
    }
   //initualize GUI whenever need to have a new Panel with mouselistener
   public GUI(JPanel panel)
   {
       GUI.panel=panel;
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
        
//        System.out.println("Mouse pressed");
        double findTileX= Math.ceil(me.getX()/10);
        double findTileY=Math.ceil(me.getY()/10);
        
        //may have to catch java.lang.ArrayIndexOutOfBoundsException, 
        //or actually probably better to round click to nearest tile
        
        //this is the tile that was clicked on, currently doing nothing
//        System.out.println("tileXSpot " +findTileX);
//        System.out.println("fileYSpot " +findTileY);
        System.out.println(GUI.tileGameMap[(int)findTileY][(int)findTileX]);
        tileClicked=GUI.tileGameMap[(int)findTileY][(int)findTileX];
    }
    @Override
    public void mouseClicked(MouseEvent me) 
    {
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
