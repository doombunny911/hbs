/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Andrew
 */
//the overall animation class of the project, every class regarding animation
//should go through this in some degree
//will be used for mouse listening too
public class GUI implements MouseListener
{
    static double numberOfTilesWidth;
    static double numberOfTilesHeight;
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
    static JPanel statPanel= new JPanel();
    static Unit unitSelected;
    static Unit attackUnit;
    static Unit defendUnit;
    static boolean impendingAttack=false;
    static Tile getTileClicked() 
    {
        return GUI.tileClicked;
    }

    private static void printStats(Unit unitSelected) 
    {
        
        int unitNumLocal=unitSelected.getUnitsAlive();
        double unitAttack=unitSelected.unitSoldiers[0].attack;
        double unitDefense = unitSelected.unitSoldiers[0].defense;
        int facingInt = unitSelected.unitSoldiers[0].facing;
        String facing;
        double armorClass = unitSelected.unitSoldiers[0].armorClass;
        double speed = unitSelected.unitSoldiers[0].speed;
        double range = unitSelected.unitSoldiers[0].range;
        double chargeBonus = unitSelected.unitSoldiers[0].chargeBonus;
        double stamina = unitSelected.unitSoldiers[0].stamina;
        switch(facingInt)
        {
            case 0: //north
            {
                facing="North";
                break;

            }
            case 1: //East
            {
                facing="East";
                break;
            }
            case 2: //south
            {
                facing = "South";
                break;
            }
            default : //west
            {
                facing = "West";
                break;
            }
        }
        JLabel stats = new JLabel("<html> Soldiers in Unit: "+unitNumLocal+
                "<br>AttackPower: "+unitAttack+"<br>DefensePower: "+unitDefense+
                "<br>unit is facing "+facing+"<br>armorClass: "+armorClass+
                "<br>speed: "+speed+"<br>range: "+range+"<br>chargeBonus: "+
                chargeBonus+"<br>stamina "+stamina+"</html>", SwingConstants.CENTER);
        GUI.statPanel.add(stats);
        GUI.statPanel.setBounds(GUI.panel.getWidth()-250,0,200,300);
        GUI.panel.add(GUI.statPanel);
        GUI.gameFrame.revalidate();
        GUI.gameFrame.repaint();
    }

    private static void paintRange(Unit unitSelected) 
    {
////        double range = unitSelected.unitSoldiers[0].range;
////        boolean flag = false;
////        find which tiles have soldiers on them in the area with the same unitID
////        ArrayList listOfIndices = new ArrayList<>();
////        
////        brute force method ftw
////        Tile tiles[][] = new Tile[(int)numberOfTilesWidth][(int)numberOfTilesHeight];
////        int iMin = Integer.MAX_VALUE;
////        int iMax = Integer.MIN_VALUE;
////        int jMin = Integer.MAX_VALUE;
////        int jMax = Integer.MIN_VALUE;
////        for(int i=0;i<GUI.numberOfTilesHeight;i++)
////        {
////            for(int j=0;j<GUI.numberOfTilesWidth;i++)
////            {
////               int unitID=GUI.tileGameMap[j][i].getOccupier().getUnitID();
////               if(unitID==unitSelected.getUnitID())//the unit belongs to the selected unit
////               {
////                   check to see if index changes in
////                   if(i<iMin)
////                       iMin=i;
////                   if(i>iMax)
////                       iMax=i;
////                   if(j<jMin)
////                       jMin=j;
////                   if(j>jMax)
////                       jMax=j;
////                   
////                   
////                   
////                   
////                   tiles[j][i]=GUI.tileGameMap[j][i];
////                   
////                  
////                   
////               }
////
////            }
////        }
////       for(int i=iMin;i<iMax;i++)
////       {
////           for(int j=jMin;j<jMax;j++)
////           {
////               if(tiles[j][i].isOccupied)
////               {
////                   if(i==iMin)
////                    {
////                        color the three tiles to the north if they are there
////                    }
////                    if(i==iMax)
////                    {
////                        color the three times to the south if they are there
////                    }
////                    if(j==jMin)
////                    {
////                        color the three tiles to the west if they are there
////                    }
////                    if(j==jMax)
////                    {
////                        color the three tiles to the east
////                    }
////               }
////               
////               
////           }
////       }
        //paint range from the first tile
        //paint 3 up (according to direction) from each tile after that
        //on the last tile, paint range to the right
        //check to see if it is the last 
        //find the outside tiles
        
        
        
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
               
               //need to wait until a mouse click is given, than call attack
               
               GUI.attackUnit=GUI.unitSelected;
           }
       });
       
       button[1].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae)
           {
               GUI.unitSelected.brace();
               
           }
       });
       
       
       button[2].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               GUI.printStats(GUI.unitSelected);
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
               if(GUI.unitSelected!=null)
               {
                   GUI.unitSelected=null;
                   GUI.toggleButtons(false);
                   GUI.statPanel.setVisible(false);
                   
               }
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
       button[4]=new JButton("Choose Formation");
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
//        UnitFormations form  = new UnitFormations(unit,0,GUI.tileClicked);
//        unit.setFormation(form);
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
        double findTileX= Math.ceil(mac.getX()/GUI.tileWidth);
        double findTileY=Math.ceil(mac.getY()/GUI.tileWidth);
        GUI.tileClicked=GUI.tileGameMap[(int)findTileX][(int)findTileY];
        
        if(thereIsAUnitReadyToBeLoaded())
            loadUnit();

        
        if(GUI.tileClicked!=null&&GUI.tileClicked.isOccupied&&!GUI.impendingAttack)
        {
            
           GUI.unitSelected=GUI.units.get(GUI.tileClicked.getOccupier().getUnitID()).thisUnit;

           //paint the range that the unit can move
            GUI.paintRange(GUI.unitSelected);
            
            
             toggleButtons(true);
             System.out.println(GUI.tileClicked.isOccupied);
             //get the unit selected
        }
        else if(GUI.tileClicked!=null&&!GUI.tileClicked.isOccupied&&!GUI.impendingAttack)
        {
//           toggleButtons(false);
        }
            
        if(GUI.moveInt>0&&tileClicked!=null)
        {
            System.out.println("moveUnit>0");
             moveUnit();
            
        }
        
        if(GUI.attackUnit!=null&&GUI.tileClicked!=null&&GUI.tileClicked.isOccupied)
        {
            GUI.attackUnit.attack(GUI.unitSelected);
            GUI.attackUnit=null;
        }
        
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
    private static void toggleButtons(boolean b) 
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
