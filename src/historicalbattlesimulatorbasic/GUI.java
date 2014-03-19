/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
    static int move;
    static JButton endTurn;
    static int indexToRemove;
    static JPanel buttonPanel = new JPanel();
    static JPanel statPanel= new JPanel();
    static Unit unitSelected;
    static Unit attackUnit;
    static Unit defendUnit;
    static boolean impendingAttack=false;
    static boolean moveBoolean;
    static Compass moveC;

   
    
  //initualize GUI whenever need to have a new Panel with mouselistener

    public GUI(JPanel panel)
   {
       GUI.panel=panel;
       //System.out.println("mouseListener check");
       GUI.panel.addMouseListener(this);
       GUI.statPanel.setVisible(false);
   }
    
     private static void initializeCompass()
     {
           GUI.moveC=new Compass();
           moveC.init();
           GUI.moveC.setBounds(GUI.gameFrame.getWidth()-200,GUI.gameFrame.getHeight()-500,200,200);
           GUI.moveC.setVisible(true);
           GUI.panel.add(GUI.moveC);
    }
    
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
            case 1: //north
            {
                facing="North";
                break;

            }
            case 2: //East
            {
                facing="East";
                break;
            }
            case 3: //south
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
        GUI.statPanel.setVisible(true);
        GUI.panel.add(GUI.statPanel);
        GUI.gameFrame.revalidate();
        GUI.gameFrame.repaint();
    }

    public static void paintRange(Unit unitSelected,Graphics g) 
    {
//        Graphics2D g2=(Graphics2D)g;
//        System.out.println("in paintRange");
//        double range = unitSelected.unitSoldiers[0].range;
////        find which tiles have soldiers on them in the area with the same unitID
//        
////        brute force method ftw
//        Tile tiles[][] = new Tile[(int)numberOfTilesWidth][(int)numberOfTilesHeight];
//        int iMin = Integer.MAX_VALUE;
//        int iMax = Integer.MIN_VALUE;
//        int jMin = Integer.MAX_VALUE;
//        int jMax = Integer.MIN_VALUE;
//        for(int i=0;i<GUI.numberOfTilesHeight;i++)
//        {
//            for(int j=0;j<GUI.numberOfTilesWidth;j++)
//            {
//               if(GUI.tileGameMap[j][i].isOccupied)
//               {
////                   System.out.println("tile at ("+j+","+i+") is occupied");
//                   if(GUI.tileGameMap[j][i].getOccupier().getUnitID()==unitSelected.getUnitID())//the unit belongs to the selected unit
//                   {
//                       System.out.println("unit id of parameter = " +unitSelected.getUnitID() );
//                       System.out.println("unit id of tileOccupier = " +GUI.tileGameMap[j][i].getOccupier().getUnitID() );
//                       System.out.println("this is an occupied tile");
//                         System.out.println("tile located at i= "+i+ " j = " + j );
////                   check to see if index changes in
//                        if(i<iMin)
//                        {
//                           iMin=i;
////                           System.out.println("new imin = " + i);
//                        }
//                        if(i>iMax)
//                        {
//                            iMax=i;
////                             System.out.println("new imax = " + i );
//                        }
//
//                        if(j<jMin)
//                        {
//                            jMin=j;
////                             System.out.println("new jmin = " + j);
//                        }
//                        if(j>jMax)
//                        {
//                           jMax=j; 
////                           System.out.println("new jmax = " + j);
//                        }
//                   tiles[j][i]=GUI.tileGameMap[j][i];
//                   }
//               }  
//            }
//        }
//       Color temp= g2.getColor();
//       g2.setColor(Color.yellow);
//       
//       for(int i=iMin;i<=iMax;i++)
//       {
//           for(int j=jMin;j<=jMax;j++)
//           {
//               if(tiles[j][i].isOccupied)
//               {
//                   
//                   if(i==iMin&&j==jMax) //northEast
//                   {
//                       for(int k=1;k<=range;k++)
//                       {
//                           if(GUI.tileGameMap[j+k][i-k].hasNorthEast())
//                             {
//                                  System.out.println("northWest at ("+(j+k)+","+(i-k)+")");
//                                 g2.fill(GUI.tileGameMap[j+k][i-k]);
//                             }
//                       }    
//                   }
//                   if(i==iMin&&j==jMin) //northWest
//                   {
//                     for(int k=1;k<=range;k++)
//                     {
//                         if(GUI.tileGameMap[j-k][i-k].hasNorthWest())
//                         {
//                                  System.out.println("northEast at ("+(j+k)+","+(i-k)+")");
//                                    g2.fill(GUI.tileGameMap[j-k][i-k]);
//                         }
//                     }
//                   }
//                   if(i==iMax&&j==jMin) //southWest
//                   {
//                      for(int k=1;k<=range;k++)
//                      {
//                          if(GUI.tileGameMap[j-k][i+k].hasSouthWest())
//                          {
//                                  System.out.println("SouthEast at ("+(j+k)+","+(i+k)+")");
//                                    g2.fill(GUI.tileGameMap[j-k][i+k]);
//                          }
//                      } 
//                   }
//                   if(i==iMax&&j==jMax) //southWest
//                   {
//                      for(int k=1;k<=range;k++)
//                      {
//                          if(GUI.tileGameMap[j+k][i+k].hasSouthEast())
//                          {
//                                  System.out.println("SouthWest at ("+(j-k)+","+(i+k)+")");
//                                    g2.fill(GUI.tileGameMap[j+k][i+k]);
//                          }
//                      }
//                   }
//                   
//                   
//                   
//                   
//                   
//                   
//                   
//                    if(i==iMin) //paint north
//                    {
//                        for(int k=1;k<=range;k++)
//                        {
//                             if(GUI.tileGameMap[j][i-k].hasNorth())
//                             {
////                                  System.out.println("north");
//                                    g2.fill(GUI.tileGameMap[j][i-k]);
//                             }
//                             else
//                                 break;
//                        }
//                    }
//                    if(i==iMax) //paint south
//                    {
//                         for(int k=1;k<=range;k++)
//                         {
//                             if(GUI.tileGameMap[j][i+k].hasSouth())
//                             {
////                                   System.out.println("south");
//                                  g2.fill(GUI.tileGameMap[j][i+k]);
//
//                             }
//                             else
//                                 break;
//                         }
////                        color the three times to the south if they are there
//                    }
//                    if(j==jMin) //paint west
//                    {
//                         for(int k=1;k<=range;k++)
//                         {
//                            if(GUI.tileGameMap[j-k][i].hasWest())
//                            {
////                                 System.out.println("west");
//                                g2.fill(GUI.tileGameMap[j-k][i]);
//
//                            }
//                            else
//                                break;
//                         }
////                        color the three tiles to the west if they are there
//                    }
//                    if(j==jMax) //paint east
//                    {
//                         for(int k=1;k<=range;k++)
//                         {
//                             if(GUI.tileGameMap[j+k][i].hasEast())
//                             {                                 
////                                 System.out.println("east");
//                                g2.fill(GUI.tileGameMap[j+k][i]);
//                             }
//                             else
//                                 break;
//                         }
//                            
////                        color the three tiles to the east
//                    }
//               }
//               
//               
//           }
//       }
////        //paint range from the first tile
////        //paint 3 up (according to direction) from each tile after that
////        //on the last tile, paint range to the right
////        //check to see if it is the last 
////        //find the outside tiles
////        
////     //not sure if necessary, i dont think it is but doesn't hurt   
//    g2.setColor(temp);
    }
   
    //not currently used
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
    public static void addButtonsToPanel(JButton[] button) 
    {
       GUI.panel.setLayout(null);
//       aPanel.setPreferredSize(new Dimension(500,300));
       buttonPanel.setBounds(0, GUI.gameFrame.getHeight()-150,GUI.gameFrame.getWidth(), 150);
       buttonPanel.setEnabled(false);
       buttonPanel.setOpaque(false);
       button[0].setBounds(buttonPanel.getWidth()/6-30-100,35,100,30);
       button[1].setBounds(buttonPanel.getWidth()/6*2-30-100,35,100,30);
       button[2].setBounds(buttonPanel.getWidth()/6*3-30-100,35,125,30);
       button[3].setBounds(buttonPanel.getWidth()/6*4-30-100,35,100,30);
       button[4].setBounds(buttonPanel.getWidth()/6*5-45-100,35,150,30);
       button[5].setBounds(buttonPanel.getWidth()/6*6-45-100,35,150,30);
       buttonPanel.add(button[0]);
       buttonPanel.add(button[1]);
       buttonPanel.add(button[2]);
       buttonPanel.add(button[3]);
       buttonPanel.add(button[4]);
       buttonPanel.add(button[5]);
       buttonPanel.setVisible(false);
       GUI.panel.add(buttonPanel);
       GUI.panel.repaint();
       GUI.gameFrame.revalidate();
    }

    public static void addCompassToPanel(JButton[] button,JPanel aPanel) {
       GUI.panel.setLayout(null );
       panel.setLayout(null);
       GUI.panel.setLayout(null);
       aPanel.setPreferredSize(new Dimension(250,250));
       aPanel.setBounds(0, GUI.panel.getHeight()-150,GUI.panel.getWidth(), 150);
       button[0].setBounds(aPanel.getWidth()/6-100,35,100,30);
       button[1].setBounds(aPanel.getWidth()/6*2-100,35,100,30);
       button[2].setBounds(aPanel.getWidth()/6*3-100,35,100,30);
       button[3].setBounds(aPanel.getWidth()/6*4-100,35,100,30);
       button[4].setBounds(aPanel.getWidth()/6*5-100,35,100,30);
       button[5].setBounds(aPanel.getWidth()/6*6-100,35,100,30);
       button[6].setBounds(aPanel.getWidth()/6*7-100,35,100,30);
       button[7].setBounds(aPanel.getWidth()/6*8-100,35,100,30);
       buttonPanel.add(button[0]);
       buttonPanel.add(button[1]);
       buttonPanel.add(button[2]);
       buttonPanel.add(button[3]);
       buttonPanel.add(button[4]);
       buttonPanel.add(button[5]);
       buttonPanel.add(button[6]);
       buttonPanel.add(button[7]);
       buttonPanel.setVisible(false);
       GUI.panel.add(aPanel);
       GUI.panel.repaint();
       GUI.gameFrame.revalidate();
    }
   

    public static void printStatsUpdater(Unit unit)
   {
       if(GUI.statPanel!=null&&GUI.statPanel.isVisible())
       {
           GUI.printStats(unit); //should be equal to GUI.unitSelected
       }
   }
//
    
   //not in final spots, didn't want 
//   to waste time finding the optimal spots atm
   public static void buttonLoader()
   {
       GUI.panel.setLayout(null);
       JButton[] button=new JButton[7];
       button=initializeButtons(button);
       GUI.endTurn=new JButton("End Turn");
//       GUI.endTurn.setBounds(GUI.gameFrame.getWidth()/2,0,100,35);
//       JPanel endTurnPanel = new JPanel();
//       endTurnPanel.setLayout(null);
//       endTurnPanel.setVisible(true);
       endTurn.setVisible(true);
//       endTurnPanel.setBounds(GUI.gameFrame.getWidth()/2,0,100,35);
       endTurn.setBounds(GUI.panel.getWidth()/2,0,100,35);
//       endTurnPanel.setOpaque(false);
//       endTurnPanel.setEnabled(false);
//       endTurnPanel.add(endTurn);
//       GUI.panel.add(endTurnPanel);
       GUI.panel.add(endTurn);
       GUI.buttonPanel.setLayout(null);
       addButtonsToPanel(button);
       
       button[0].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               
               GUI.attackUnit=GUI.unitSelected;
           }
       });
       
       button[1].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae)
           {
               //can't prepare to defend if attacking
               if(GUI.attackUnit==null)
               {
                   GUI.unitSelected.brace();
               }
           }
       });
       
       
       button[2].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               if(GUI.statPanel!=null&&GUI.statPanel.isVisible()==false)
                    GUI.printStats(GUI.unitSelected);
               //get the stats of the unit clicked
               //print them on side of screen
           }
       });
       button[3].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
              
               if(GUI.tileClicked!=null)
                   GUI.tileClicked=null;
                 // System.out.println("please click the tile that you wish to move to");
                  if(GUI.moveC==null)
                  {
                     GUI.initializeCompass();
                  }
                  if(!GUI.moveC.isVisible())
                        GUI.moveC.setVisible(true);
                  
                  GUI.panel.repaint();
                  GUI.gameFrame.repaint();
                  GUI.gameFrame.revalidate();
              
              
           }
       });
       button[4].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               //set formation button, not done
          //
              //System.out.println("this button does not do anything atm");
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
                   GUI.moveC.setVisible(false);
                 
               }
               //make buttons disapear/click through or delete and remake
           }
       });
       endTurn.addActionListener(new ActionListener()
       {
             @Override
             public void actionPerformed(ActionEvent ae)
             {
                //this button will end the turn of the player and go to next player's turn
//                 System.out.println("test for end Turn Button");
                 
             }
        
       });
   }

   private void moveUnit() 
   {
       
       
       
           //locate the unitDraw whose unit = GUI.selectedUnit;
//       int index = Integer.MAX_VALUE;
//       for(int i=0;i<units.size();i++)
//       {
//         //So this the space that unit is being moved too   
//          UnitDraw draw =GUI.units.get(GUI.indexToRemove);
//          Unit unit =draw.thisUnit;
////          draw.xDraw = GUI.tileClicked.xPosition;
////          draw.yDraw= GUI.tileClicked.yPosition;
//          //this is the movement redraw
//          double originalXPosition = unit.xPosition;
//          double originalYPosition = unit.yPosition;
//          
//          unit.xPosition=GUI.tileClicked.xPosition;
//          unit.yPosition = GUI.tileClicked.yPosition;
//          //get them in relation
//          double xVal = (unit.xPosition - originalXPosition);
//          double yVal = (unit.yPosition-originalYPosition);
//     
//          //This should allow stuff
//          if(xVal==0&&yVal>0)
//          {
//              unit.moveNorth();
//   
//          }
//          else if(xVal>0&&yVal>0)
//          {
//              unit.moveNorthEast();
//          }
//          else if(xVal==1&&yVal==0)
//          {
//              unit.moveEast();
//          }
//          else if(xVal>0&&yVal<0)
//          {
//              unit.moveSouthEast();
//          }
//          else if(xVal==0&&yVal<0)
//          {
//              unit.moveSouth();
//          }
//          else if(xVal<0&&yVal<0)
//          {
//              unit.moveSouthWest();
//          }
//          else if(xVal==0&&yVal<0)
//          {
//              unit.moveWest();
//          }
//          else if(xVal<0&&yVal>0)
//          {
//              unit.moveNorthWest();
//          }
//       }
//       int index = Integer.MAX_VALUE;
//       for(int i=0;i<units.size();i++)
//       {
//         //So this the space that unit is being moved too   
//          UnitDraw draw =GUI.units.get(GUI.indexToRemove);
//          Unit unit =draw.thisUnit;
////          draw.xDraw = GUI.tileClicked.xPosition;
////          draw.yDraw= GUI.tileClicked.yPosition;
//          //this is the movement redraw
//          double originalXPosition = unit.xPosition;
//          double originalYPosition = unit.yPosition;
//          
//          unit.xPosition=GUI.tileClicked.xPosition;
//          unit.yPosition = GUI.tileClicked.yPosition;
//          //get them in relation
//          double xVal = (unit.xPosition - originalXPosition);
//          double yVal = (unit.yPosition-originalYPosition);
//     
//          //This should allow stuff
//          if(xVal==0&&yVal>0)
//          {
//              unit.moveNorth();
//   
//          }
//          else if(xVal>0&&yVal>0)
//          {
//              unit.moveNorthEast();
//          }
//          else if(xVal==1&&yVal==0)
//          {
//              unit.moveEast();
//          }
//          else if(xVal>0&&yVal<0)
//          {
//              unit.moveSouthEast();
//          }
//          else if(xVal==0&&yVal<0)
//          {
//              unit.moveSouth();
//          }
//          else if(xVal<0&&yVal<0)
//          {
//              unit.moveSouthWest();
//          }
//          else if(xVal==0&&yVal<0)
//          {
//              unit.moveWest();
//          }
//          else if(xVal<0&&yVal>0)
//          {
//              unit.moveNorthWest();
//          }
//          UnitDraw draw2=new UnitDraw(unit);
//          GUI.units.remove(GUI.units.get(GUI.indexToRemove));
//         // unit.moveDirection(unitNum);
//          GUI.units.add(draw2);
//          GUI.indexToRemove=0;
//
//           if(GUI.unitSelected.getUnitID()==GUI.units.get(i).thisUnit.getUnitID())
//           {
//      //       System.out.println("unitID of unitDraw at "+ i+ " = "+GUI.units.get(i).thisUnit.getUnitID());
//        //     System.out.println("the name of the unit at " + i + " = " + GUI.units.get(i).thisUnit.nameOfUnit);
////
//               index=i;
////               break;
//           }
//       }
//          GUI.unitSelected.xPosition=GUI.tileClicked.xPosition;
//          GUI.unitSelected.yPosition=GUI.tileClicked.yPosition;
//          UnitDraw draw = new UnitDraw(GUI.unitSelected);
//          GUI.units.add(draw);
//          GUI.units.remove((index));
//          moveBoolean=false;
//          GUI.tileClicked=null;
//
//          GUI.gameFrame.revalidate();
//          GUI.panel.repaint();
//          GUI.gameFrame.repaint();

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
//        System.out.println("Unitnum!=0");
        Unit unit= UnitLoader.allUnits.get(GUI.unitNum-1);
        unit.setPosition(GUI.tileClicked.xPosition,GUI.tileClicked.yPosition);
//        System.out.println("place unit " +unit.nameOfUnit + " at (" +GUI.tileClicked.xPosition+","+GUI.tileClicked.yPosition+") ");
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
     //   System.out.println("just clicked on tile (" + (int)findTileX +","+ (int)findTileY+")");
        if(thereIsAUnitReadyToBeLoaded())
            loadUnit();
        if(GUI.tileClicked!=null&&GUI.tileClicked.isOccupied&&!GUI.impendingAttack&&GUI.unitSelected==null)
        {
            System.out.println("in the unitThingPlace");
           
            //get the unit that has the same thisUnit inside arrayList units by getting the soldier occupying the tile
           for(int i =0;i<units.size();i++)
           {
     //          System.out.println("unitID of getOccupier on tile  = " + GUI.tileClicked.getOccupier().getUnitID());
              if(GUI.units.get(i).thisUnit.unitID==GUI.tileClicked.getOccupier().getUnitID())
              {
                  
                  GUI.unitSelected=GUI.units.get(i).thisUnit;
        //          System.out.println("the unitID of unit " + GUI.unitSelected.nameOfUnit + " is = to " +GUI.unitSelected.unitID);
              }

           }

           //paint the range that the unit can move
                //call to the painter is happening
            
             toggleButtons(true);
//             System.out.println(GUI.tileClicked.isOccupied);
             //get the unit selected
        }


        

        
       
        else if(GUI.tileClicked!=null&&!GUI.tileClicked.isOccupied&&!GUI.impendingAttack)
        {
//           toggleButtons(false);
        }
            
//        if(GUI.moveBoolean&&tileClicked!=null)
//        {
//             moveUnit();
//        }
        
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
        System.out.println("in there is a unit ready to be loaded");
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
