/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
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
       
       JButton[] button=new JButton[6];
       button=initializeButtons(button);
    
       int frameHeight=GUI.panel.getHeight()-100;
       int frameWidth = GUI.panel.getWidth()/6-100;
       int frameHalfway = GUI.panel.getWidth()/2;
//       button[0].setLocation(300,300);
       button[0].setBounds(0,0,100,30);
       button[1].setBounds(frameWidth*2,frameHeight,100,30);
       button[2].setBounds(frameWidth*3,frameHeight,150,30);
       button[3].setBounds(frameWidth*4+100,frameHeight,100,30);
       button[4].setBounds(frameWidth*5+100,frameHeight,150,30);
       button[5].setBounds(frameWidth*6+100,frameHeight,150,30);
       
       
       GUI.panel.add(button[0]);
       GUI.panel.add(button[1]);
       GUI.panel.add(button[2]);
       GUI.panel.add(button[3]);
       GUI.panel.add(button[4]);
       GUI.panel.add(button[5]);
       
//       JPanel buttonPanel = new JPanel();
//       buttonPanel.setLayout(null);
//       addButtonsToPanel(button,buttonPanel);
       System.out.println(GUI.panel.getHeight()+ "height");
       System.out.println(GUI.panel.getWidth()+ "width");
//       buttonPanel.setBounds(0,GUI.panel.getHeight()-150, GUI.panel.getWidth()-1, 150);
       
       
       button[0].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               //attack the unit that is targeted next
           }
       });
       
       button[1].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae)
           {
               //switch unit into "defense"
           }
       });
       
       
       button[2].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               //get the stats of the unit clicked
               //print them on side of screen
           }
       });
       button[3].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               
               
               
               System.out.println("please click the tile that you wish to move to");
               
               //get unit that is being clicked on to move
                    
               //get tile Clicked 
                    //wait for mouseEvent to happen
                   
               //find previous unit spot, delete it
//                    //loop through the arrayList until you find a unit with the same x,y starting point (xDraw,yDraw)
//                for(int i=0;i<units.size();i++)
//                {
//                    if(units.get(i).xDraw=thecurrentlytargetUnit.xPosition&&units.get(i).yDraw=thecurrentlytargetUnit.yPosition)
//                    {
//                        units.remove(i);
//                        break;
//                    }
//                        
//                    //should never leave loop without deleting the unit
//                }
//               
//               //put new unit into array
//                    units.add(new UnitDraw(moveXPosition,moveYPosition));
//               
//               Unit unit=  UnitLoader.allUnits.get(GUI.unitNum-1);
//        
//                unit.setPosition(GUI.tileClicked.xPosition,GUI.tileClicked.yPosition);
//            System.out.println("place unit " +unit.unitName + " at (" +GUI.tileClicked.xPosition+","+GUI.tileClicked.yPosition+") ");
//            GUI.units.add(new UnitDraw(unit));
//            GUI.tileClicked=null;
//            unitNum--;
//            System.out.println("UnitNum after sub ="+ unitNum);
//            GUI.panel.repaint();
           }
       });
       button[4].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               System.out.println("SpecialAbility Activate!");
           }
       });
       button[5].addActionListener(new ActionListener() 
       {

           @Override
           public void actionPerformed(ActionEvent ae)
           {
               //deselect Unit 
               //make buttons disapear/click through or delete and remake
           }
       });
   }

   
   
    public static void addButtonsToPanel(JButton[] button,JPanel aPanel) {
//       GUI.panel.setLayout(null);
//       panel.setLayout(null);
        GUI.panel.setLayout(null);
        aPanel.setPreferredSize(new Dimension(500,300));
       aPanel.setBounds(0, 0, 500, 200);
//       int frameHeight=GUI.panel.getHeight()-100;
//       int frameWidth = GUI.panel.getWidth()/6-100;
//       int frameHalfway = GUI.panel.getWidth()/2;
////       button[0].setLocation(300,300);
//       button[0].setBounds(0,0,100,30);
//       button[1].setBounds(frameWidth*2,frameHeight,100,30);
//       button[2].setBounds(frameWidth*3,frameHeight,150,30);
//       button[3].setBounds(frameWidth*4+100,frameHeight,100,30);
//       button[4].setBounds(frameWidth*5+100,frameHeight,150,30);
//       button[5].setBounds(frameWidth*6+100,frameHeight,150,30);
//        aPanel.add(button[0]);
//        aPanel.add(button[1]);
//        aPanel.add(button[2]);
//        aPanel.add(button[3]);
//        aPanel.add(button[4]);
//        aPanel.add(button[5]);
    
       
//        GUI.panel.add(aPanel);
        GUI.panel.repaint();
        GUI.gameFrame.revalidate();
    }
   
   public static JButton[] initializeButtons(JButton[] button)
   {
       button[0]=new JButton("Attack");
       button[1]=new JButton("Defend");
       button[2]=new JButton("Check Stats");
       button[3]=new JButton("Move");
       button[4]=new JButton("Special Ability");
       button[5]=new JButton("Cancel Selection");
       return button;
   }
   
   public static void copy(Component c,Component d)
   {
       
       d.setBounds(0, 0,c.getWidth(),c.getHeight());
//       r.getBounds
//       return r;
   }
   
   

    
    public void loadUnit() 
    {
        System.out.println("Unitnum!=0");
        System.out.println(GUI.tileClicked+"Right before condition");
        System.out.println(unitNum+ " Before anything happens ");
        Unit unit=  UnitLoader.allUnits.get(GUI.unitNum-1);
        
        unit.setPosition(GUI.tileClicked.xPosition,GUI.tileClicked.yPosition);
        System.out.println("place unit " +unit.unitName + " at (" +GUI.tileClicked.xPosition+","+GUI.tileClicked.yPosition+") ");
        GUI.units.add(new UnitDraw(unit));
        GUI.tileClicked=null;
        unitNum--;
        System.out.println("UnitNum after sub ="+ unitNum);
        GUI.panel.repaint();
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
        System.out.println(GUI.tileGameMap[(int)findTileY][(int)findTileX]);
        while(thereIsAUnitReadyToBeLoaded())
            loadUnit();
//       if(GUI.tileClicked!=null&&GUI.tileClicked.getIsOccupied())
//       {
//           System.out.println("tis true");
//           System.out.println(GUI.tileClicked.getOccupier().getUnitID());
//       }

       
    }
    //checks to see if someone clicked a tile and there are units in "queue"
    
    
    public boolean thereIsAUnitReadyToBeLoaded() 
    {
        return GUI.tileClicked!=null&&unitNum!=0;
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
