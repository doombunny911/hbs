/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Component;
import java.awt.Dimension;
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
    static int moveInt;
    static int indexToRemove;
    static JPanel buttonPanel = new JPanel();
    
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
    public static void addButtonsToPanel(JButton[] button,JPanel aPanel) {
       GUI.panel.setLayout(null);
       panel.setLayout(null);
       GUI.panel.setLayout(null);
       aPanel.setPreferredSize(new Dimension(500,300));
       aPanel.setBounds(0, GUI.panel.getHeight()-150,GUI.panel.getWidth(), 150);
       button[0].setBounds(aPanel.getWidth()/6-100,35,100,30);
       button[1].setBounds(aPanel.getWidth()/6*2-100,35,100,30);
       button[2].setBounds(aPanel.getWidth()/6*3-100,35,100,30);
       button[3].setBounds(aPanel.getWidth()/6*4-100,35,100,30);
       button[4].setBounds(aPanel.getWidth()/6*5-100,35,100,30);
       button[5].setBounds(aPanel.getWidth()/6*6-100,35,100,30);
       buttonPanel.add(button[0]);
       buttonPanel.add(button[1]);
       buttonPanel.add(button[2]);
       buttonPanel.add(button[3]);
       buttonPanel.add(button[4]);
       buttonPanel.add(button[5]);
       buttonPanel.setVisible(false);
       GUI.panel.add(aPanel);
       GUI.panel.repaint();
       GUI.gameFrame.revalidate();
    }
   
    
   //not in final spots, didn't want 
//   to waste time finding the optimal spots atm
   public static void buttonLoader()
   {
       JButton[] button=new JButton[6];
       button=initializeButtons(button);
       
       GUI.buttonPanel.setLayout(null);
       addButtonsToPanel(button,GUI.buttonPanel);
       
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
               moveInt+=2;
               
              
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

   private void moveUnit() 
   {
       System.out.println("in moveUnit");
       if(moveInt%2==0)
       {
             System.out.println("mod 2 =0");

           //this is a unit being selected
            for(int i=0;i<GUI.units.size();i++)
           {
//               if(GUI.tileClicked.getOccupier().unitName==GUI.units.get(i).thisUnit.unitName)
//               {
               if(GUI.tileClicked.getOccupier().getUnitID()==GUI.units.get(i).thisUnit.getUnitID())
               {
                   System.out.println(GUI.tileClicked.getOccupier().unitName);
                   System.out.println(GUI.units.get(i).thisUnit.unitName);
                   System.out.println(GUI.units.get(i).thisUnit.unitID);
                   System.out.println(GUI.tileClicked.getOccupier().getUnitID());
                   //found the unit to delete
//                   System.out.println(GUI.units.get(i).xDraw);
                   GUI.indexToRemove=i;
                   System.out.println("indexToRemove " +GUI.indexToRemove);
                   break;
               }
           }
       }
       
       else
       {
             System.out.println("mod 2 =1");

          UnitDraw draw =GUI.units.get(GUI.indexToRemove);
          Unit unit =draw.thisUnit;
//          draw.xDraw = GUI.tileClicked.xPosition;
//          draw.yDraw= GUI.tileClicked.yPosition;
          
          unit.xPosition=GUI.tileClicked.xPosition;
          unit.yPosition = GUI.tileClicked.yPosition;
          
          UnitDraw draw2=new UnitDraw(unit);
          GUI.units.remove(GUI.units.get(GUI.indexToRemove));

          GUI.units.add(draw2);
          GUI.indexToRemove=0;
          GUI.gameFrame.revalidate();
          GUI.panel.repaint();
          GUI.gameFrame.repaint();
       }
         GUI.moveInt--;
         GUI.tileClicked=null;
         
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
    
   //need to set a direction when unit is placed on board
    public void loadUnit() 
    {
        System.out.println("Unitnum!=0");
        Unit unit= UnitLoader.allUnits.get(GUI.unitNum-1);
        UnitFormations form  = new UnitFormations(unit,0,GUI.tileClicked);
        unit.setFormation(form);
//        unit.setUnitUnitID();
        unit.setPosition(GUI.tileClicked.xPosition,GUI.tileClicked.yPosition);
//        System.out.println("place unit " +unit.unitName + " at (" +GUI.tileClicked.xPosition+","+GUI.tileClicked.yPosition+") ");
        GUI.units.add(new UnitDraw(unit));
        GUI.tileClicked=null;
        unitNum--;
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
        double findTileX= Math.ceil(mac.getX()/GUI.tileWidth);
        double findTileY=Math.ceil(mac.getY()/GUI.tileWidth);
        GUI.tileClicked=GUI.tileGameMap[(int)findTileY][(int)findTileX];
//        System.out.println(GUI.tileGameMap[(int)findTileY][(int)findTileX]);
        if(thereIsAUnitReadyToBeLoaded())
            loadUnit();

        if(GUI.moveInt>0&&tileClicked!=null)
        {
            System.out.println("moveUnit>0");
             moveUnit();
            
        }
        
        
        
        if(GUI.tileClicked!=null&&GUI.tileClicked.isOccupied)
        {
            
             toggleButtons(true);
             GUI.gameFrame.revalidate();
//             GUI.gameFrame.repaint();
//             GUI.panel.repaint();
             System.out.println(GUI.tileClicked.isOccupied);

        }
        else if(GUI.tileClicked!=null&&!GUI.tileClicked.isOccupied)
            toggleButtons(false);
            
       GUI.panel.repaint();
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

    //toggles whether buttons are seen or not seen
    //gets buttons by checking to see components on the 
    //panel
    private void toggleButtons(boolean b) 
    {
        //sets the buttonPanel to b, 
        GUI.buttonPanel.setVisible(b);
        
        //the number of components in the panel
        int bNum =GUI.buttonPanel.getComponentCount();
        for(int i=0;i<bNum;i++)
           GUI.buttonPanel.getComponent(i).setVisible(b);
        GUI.gameFrame.revalidate();
        GUI.gameFrame.repaint();
    }
}
