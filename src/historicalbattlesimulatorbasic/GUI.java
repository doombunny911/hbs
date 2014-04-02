/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
//    JLabel bgL = new JLabel();
    static double numberOfTilesWidth; //the number of tiles that make up the width
    static double numberOfTilesHeight;//the number of tiles that make up the height
    static JFrame gameFrame; //the gameframe, holds the panels/buttons
    static Map gameMap; //the Map, not being used atm
    static Tile[][] tileGameMap; //the array of tiles that make up the tileGameMap
    static int tileWidth; //the width of an individual tile
    static JPanel panel; //the panel holding all the tiles
    static ArrayList<UnitDraw> unitDraws=new ArrayList<>();//the unitDraws gotten via unitLoader
    static Tile tileClicked;//the tile that was clicked last
    static UnitLoader loader; //a unitLoader, used to get all the unitDraws loaded for unitDraws ArrayList
    static int unitNum; //the number of unitDraws in the unitLoader, decreased to parse through the arrayList
    static JButton endTurn; //the button that ends a players turn
    static JPanel buttonPanel = new JPanel(); //the panel that holds the buttons on bottom of screen
    static JPanel attackButtonPanel;
    static JPanel statPanel= new JPanel(); //the panel that holds the stats when they are printed
    static JPanel formationPanel;
    static Unit unitSelected; //the unit that is currently selected
    static Unit attackUnit; //may not be used, currently used for attacking
    static boolean impendingAttack=false; //probably won't be used
    static Compass moveC; //the compass
    static ArrayList<Unit> player1AllUnits;//player 1's unitDraws
    static ArrayList<Unit> player2AllUnits;//player 2's unitDraws
    static int player1UnitNum; //same as unitNum
    static int player2UnitNum; //same as above
    static UnitPlacer unitPlacerTest;
    static Unit enemySelected;
//    static boolean busy;
    
//    static JPanel moveButtonPanel; //panel for moving options (sprint etc)

   
    
  //initualize GUI whenever need to have a new Panel with mouselistener (only called once i think)
    public GUI(JPanel panel)
   {
       GUI.panel=panel;
       GUI.panel.addMouseListener(this);
       GUI.statPanel.setVisible(false);
       
   }
    
     private static void initFormPanel() 
     {
         BufferedImageLoaders bil = new BufferedImageLoaders();
         bil.loadAllButtons();
         formationPanel=new JPanel();
         JButton[] button = new JButton[3];
         formationPanel.setLayout(null);
         formationPanel.setBounds(GUI.buttonPanel.getBounds());
         ImageIcon lineB = bil.getLineFormIcon();
         ImageIcon boxB = bil.getSquareFormIcon();
         ImageIcon wedgeB = bil.getWedgeFormIcon();
         button[0]=  new JButton(lineB);
         button[1] = new JButton(boxB);
         button[2] = new JButton(wedgeB);
         Component c = GUI.buttonPanel.getComponent(0);
         button[0].setBounds(c.getX(),c.getY(),c.getWidth(),c.getHeight());
         button[1].setBounds(c.getX()+c.getWidth()*2,c.getY(),c.getWidth(),c.getHeight());
         button[2].setBounds(c.getX()+c.getWidth()*3,c.getY(),c.getWidth(),c.getHeight());
         formationPanel.add(button[0]);
         formationPanel.add(button[1]);
         formationPanel.add(button[2]);
//         busy=true;
         button[0].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) //line Formation
           {
               GUI.unitSelected.currentFormation.defaultFormation();
               GUI.toggleButtons(formationPanel, false);
               GUI.toggleButtons(buttonPanel, true);
//               busy=false;
//               GUI.repainter();
          }
       });
       
       button[1].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) //box Formation
           {
               GUI.unitSelected.currentFormation.setBoxFormation();
               GUI.toggleButtons(formationPanel, false);
               GUI.toggleButtons(buttonPanel, true);
           }
       });
       
       button[2].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) //Wedge Formation
           {
               
               GUI.unitSelected.currentFormation.setWedgeFormation();
               GUI.toggleButtons(formationPanel, false);
               GUI.toggleButtons(buttonPanel, true);
//               busy = false;
//               GUI.repainter();

           }
       });
       
       panel.add(formationPanel);
       
     }
    
    
//    public static void placeUnitTester() 
//    {
//      
//        UnitPlacer unitP = new UnitPlacer("holder");
//        GUI.unitPlacerTest = unitP;
//        ArrayList units = GUI.unitPlacerTest.getUnitList();
//        GUI.unitPlacerTest.unitArrayList=units;    
//        GUI.unitPlacerTest.setBounds(GUI.gameFrame.getWidth()/2-200,4*GUI.gameFrame.getHeight()/5, 300, 300);
//        GUI.unitPlacerTest.setUpButtons();
//        GUI.panel.add(GUI.unitPlacerTest);    
//    } 
    //method that returns  the value of tileClicked
    public static Tile getTileClicked() 
    {
       if(GUI.tileClicked!=null)
          return GUI.tileClicked;
       else
           return null;
    }
   
    //initializes defenseButton
    private static void initDefenseButton()
    {
        final JButton sAbility = new JButton("Special Ability");
        sAbility.setBounds(GUI.buttonPanel.getComponent(2).getBounds());
        sAbility.setLocation(GUI.buttonPanel.getComponent(2).getX(),GUI.gameFrame.getHeight()-buttonPanel.getHeight());
        sAbility.setVisible(true);
        GUI.panel.add(sAbility);
        
        sAbility.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               System.out.println("Special Ability activated");
               GUI.unitSelected.useSpecialAbility();
                //reload the buttons that are loaded when a unit is clicked
               sAbility.setVisible(false);
//               cancel.setVisible(false);
              toggleButtons(GUI.buttonPanel,true);

          }
       });
        
    } 
    //initializes attackButton
    private static void initAttackButton() 
    {
         attackButtonPanel=new JPanel();
         attackButtonPanel.setLayout(null);
         attackButtonPanel.setBounds(GUI.buttonPanel.getBounds());
         JButton[] attackButton = new JButton[3];
         attackButton[0]=  new JButton("Regular Attack");
         attackButton[1] = new JButton("Charge");
         attackButton[2] = new JButton("Special Ability Attack");
         Component c = GUI.buttonPanel.getComponent(0);
         attackButton[0].setBounds(c.getX(),c.getY(),c.getWidth(),c.getHeight());
         attackButton[1].setBounds(c.getX()+c.getWidth()*2,c.getY(),c.getWidth(),c.getHeight());
         attackButton[2].setBounds(c.getX()+c.getWidth()*3,c.getY(),c.getWidth(),c.getHeight());
         attackButtonPanel.add(attackButton[0]);
         attackButtonPanel.add(attackButton[1]);
         attackButtonPanel.add(attackButton[2]);
         GUI.repainter();
        
        
        attackButton[0].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) //normal attack
           {
               System.out.println("Please click on the unit you wish to attack");
               GUI.impendingAttack=true;
               GUI.toggleButtons(attackButtonPanel, false);
               GUI.toggleButtons(buttonPanel, true);
          }
       });
       
       attackButton[1].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) //charge
           {
               System.out.println("charging, now losing stamina");
               GUI.attackUnit.charge();
               GUI.impendingAttack=true;
               GUI.toggleButtons(attackButtonPanel, false);
               GUI.toggleButtons(buttonPanel, true);
               
           }
       });
       
       attackButton[2].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) //special ability
           {
               
               System.out.println("specialAbility Activated");
               GUI.attackUnit.useSpecialAbility();
               GUI.impendingAttack=true;
               GUI.toggleButtons(attackButtonPanel, false);
               GUI.toggleButtons(buttonPanel, true);
           }
       }); 
     }
    
    
   //buttonLoader, used for button action listening
   public static void buttonLoader()
   {
       //set the layout to null so we can place it exactly where we want using pixels
       GUI.panel.setLayout(null);
       GUI.buttonPanel.setLayout(null);

       JButton[] button=new JButton[6]; //the array of all the "bottom" buttons
       button=initializeButtons(button); //method that creates the buttons, than returned to the variable
       
       addButtonsToPanel(button); //this method adds the buttons to the panel
       
       
       //the rest of the method deals with what happens when you push each button
       button[0].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               GUI.attackUnit=GUI.unitSelected;
               GUI.toggleButtons(GUI.buttonPanel,false);
               
               if(GUI.attackButtonPanel==null)
               {
                 initAttackButton();
                 //if attackUnit does not have a special ability,or doesn't 
                 //have an offensive special ability disable button or don't show it
               }
               if(!GUI.attackButtonPanel.isVisible())
                   GUI.toggleButtons(GUI.attackButtonPanel,true);
               
               
           }

           
       });
       
       button[1].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae)
           {
               //can't prepare to defend if attacking
              GUI.toggleButtons(GUI.buttonPanel,false);

               if(GUI.attackUnit==null)
               {
                   //if unitSelected does not have a defensive special ability, don't load extra button
                   //otherwise, load a panel with a button for speical ability
                   //i assume is the defense method, prepareation or whatnot
                   //if there is a specialAbility and the button hasn't been initialized
                        initDefenseButton();
                  //elseIf(there is a specialAbility and the button has been intialized
                        GUI.panel.getComponentAt(GUI.buttonPanel.getComponent(2).getLocation()).setVisible(true);
                   GUI.unitSelected.brace();
               }
                   
           }
       });
       
       button[2].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
      //if statPanel!=null (should be never) and is not visible, call method print stats
               if(GUI.statPanel!=null&&GUI.statPanel.isVisible()==false)
                    GUI.printStats(GUI.unitSelected);
               else if(GUI.statPanel!=null&&GUI.statPanel.isVisible())
                   GUI.statPanel.setVisible(false);
               //get the stats of the unit clicked and print them on side of screen
           }
       });
       button[3].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
              //don't think this is necessary but I will look into it, I think i did this to help isolate a bug
//               if(GUI.tileClicked!=null)
//                   GUI.tileClicked=null;
//                   System.out.println("the value of move is " + moveC);
                  if(GUI.moveC==null) //if the compass has yet to be initalized the first time, init it
                  {
                     GUI.initializeCompass();
                  }
                  else if(!moveC.isVisible())
                  {
                     GUI.moveC.setVisible(true);      
                  }
                  //if it is not visible, make it visible
                  GUI.repainter();
           }
       });
       button[4].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               
                   initFormPanel();
               
               
               {
                   if(!formationPanel.isVisible())
                   {
                       formationPanel.setVisible(true);
                   }
               }
               
               GUI.toggleButtons(buttonPanel, false);
               
               if(moveC!=null&&moveC.isVisible())
                   moveC.setVisible(false);
               if(GUI.attackButtonPanel!=null&&attackButtonPanel.isVisible())
                   attackButtonPanel.setVisible(false);
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
                   GUI.toggleButtons(GUI.buttonPanel,false);
                   GUI.statPanel.setVisible(false);
                   if(GUI.moveC!=null&&GUI.moveC.isVisible())
                        GUI.moveC.setVisible(false);
                   if(GUI.formationPanel!=null&&formationPanel.isVisible())
                   {
                       formationPanel.setVisible(false);
                       toggleButtons(formationPanel,false);
                   }
               }
               else
               {
                   //should not be possible
                   System.out.println("logic is broken in cancel selection");
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
                 GUI.unitSelected.resetUnitPoints();
                 GUI.unitSelected.endTurn();
                 GUI.unitSelected=null;
                 GUI.tileClicked=null;
                 GUI.toggleButtons(buttonPanel, false);
                 
                 if(Game.playersForDemo.get(0).allUnits.size()==0||Game.playersForDemo.get(1).isWinner)
                 {
                      Game.playersForDemo.get(1).isWinner=true;
                      JOptionPane.showMessageDialog(null, "congratulations " + Game.playersForDemo.get(1).playerName + " you are victorious");
                 }
                 else if(Game.playersForDemo.get(1).allUnits.size()==0||Game.playersForDemo.get(0).isWinner)
                 {
                     Game.playersForDemo.get(0).isWinner=true;
                     JOptionPane.showMessageDialog(null, "congratulations " + Game.playersForDemo.get(0).playerName + " you are victorious");
                     
                 }
                    
                 
                 
                 if(Game.playersForDemo.get(0).myTurn)
                 {
                     Game.playersForDemo.get(0).myTurn=false;
                     Game.playersForDemo.get(1).myTurn=true;
                     JOptionPane.showMessageDialog(null, Game.playersForDemo.get(0).playerName + " your turn is now over. It is now time for " + Game.playersForDemo.get(1).playerName + " to take their turn" );

                 }
                 else if(Game.playersForDemo.get(1).myTurn)
                 {
                     Game.playersForDemo.get(1).myTurn=false;
                     Game.playersForDemo.get(0).myTurn=true;
                     JOptionPane.showMessageDialog(null, Game.playersForDemo.get(0).playerName + " your turn is now over. It is now time for " + Game.playersForDemo.get(0).playerName + " to take their turn" );
                 }
             }
        
       });
   }

 
   //this is called when the original unitDraws need to be loaded the first time, 
   //will probably become useless in final project but still pivotal now
    public void loadUnit(Unit unit) 
    {
        System.out.println("in loadUnit");
        //gets the arrayList stored in unitLoader
//        Unit unit= UnitLoader.allUnits.get(GUI.unitNum-1);
        //sets the tile location of where they are at
        unit.setPosition(GUI.tileClicked.xPosition,GUI.tileClicked.yPosition);
        //adds it to the arrayList in GUI that is currently storing the unitDraws
        GUI.unitDraws.add(new UnitDraw(unit));
        
        
        GUI.tileClicked=null; //used to avoid potential errors, tileClicked is reloaded everytime a tile is clicked on
//        unitNum--; //keeps track of how many more unitDraws there are to be drawn,
        //it is intialized with how many unitDraws there are in the arraylist+1 (arrayList.size()
//        GUI.repainter(); //repaints
    }
   
    
    
    //mouseClicked holds a lot of logic due to the event driven processes of our project
    //and how we get around waiting for users to do something.
    //this is how we get around while loops
    @Override
    public void mouseClicked(MouseEvent mac) 
    {
        double findTileX= Math.ceil(mac.getX()/GUI.tileWidth);
        double findTileY=Math.ceil(mac.getY()/GUI.tileWidth);
       GUI.tileClicked=GUI.tileGameMap[(int)findTileX][(int)findTileY]; //sets the tile= the tile with the coords in the tileGameMap
       if(Game.playersForDemo!=null&&Game.playersForDemo.get(0).up.check&&GUI.tileClicked!=null) 
       {
           loadUnit(Game.playersForDemo.get(0).up.unitToBeLoaded);
           Game.playersForDemo.get(0).up.check=false;
           Game.playersForDemo.get(0).up.unitToBeLoaded=null;
           System.out.println("in mouseClicked going to unitplacer ");
       }
       else if(Game.playersForDemo!=null&&Game.playersForDemo.get(1).up.check&&GUI.tileClicked!=null)
       {
           loadUnit(Game.playersForDemo.get(1).up.unitToBeLoaded);
           Game.playersForDemo.get(1).up.check=false;
           Game.playersForDemo.get(1).up.unitToBeLoaded=null;
           System.out.println("in mouseClicked going to unitplacer ");
       }
       else if(GUI.unitPlacerTest!=null&&GUI.tileClicked!=null &&GUI.unitPlacerTest.check)
       {
           loadUnit(unitPlacerTest.unitToBeLoaded);
           unitPlacerTest.check=false;
           unitPlacerTest.unitToBeLoaded=null;
           System.out.println("in UnitPlacer ");
       }
        
        //if a tile has been clicked(should be always) and the tile clicked on
        //has a soldier in it and there is not already a unit selected
        if(GUI.tileClicked!=null&&GUI.tileClicked.isOccupied&&!GUI.impendingAttack&&GUI.unitSelected==null&&formationPanel==null&&Game.playersForDemo.get(0).up.numOfUnitsToPlace==0&&Game.playersForDemo.get(1).up.numOfUnitsToPlace==0||(formationPanel!=null&&formationPanel.isVisible()))
        {
//             if(GUI.unitDraws.get(i).thisUnit.unitID==GUI.tileClicked.getOccupier().getUnitID())
               if(Game.playersForDemo.get(0).myTurn)   
              {
                  for(int i=0;i<Game.playersForDemo.get(0).allUnits.size();i++)
                  {
                      if(Game.playersForDemo.get(0).allUnits.get(i).getUnitID()==GUI.tileClicked.getOccupier().getUnitID())
                    {
                        System.out.println("unitSelected is being initialized ");
                  
                        //this is where unitselected gets initialized.  it will stay initialized until cancel selection is pressed
//                      GUI.unitSelected=GUI.unitDraws.get(i).thisUnit;
                        GUI.unitSelected=Game.playersForDemo.get(0).allUnits.get(i);
                        toggleButtons(GUI.buttonPanel,true);
                        break;
                    }
                  }
                  for(int i=0;i<Game.playersForDemo.get(1).allUnits.size();i++)
                  {
                      if(Game.playersForDemo.get(1).allUnits.get(i).getUnitID()==GUI.tileClicked.getOccupier().getUnitID())
                      {
                          if(GUI.enemySelected!=null&&GUI.enemySelected==Game.playersForDemo.get(1).allUnits.get(i))
                          {
                              GUI.statPanel.setVisible(false);
                              enemySelected=null;
                          }
                          else
                          {
                               GUI.enemySelected = Game.playersForDemo.get(1).allUnits.get(i);
                               GUI.printStats(Game.playersForDemo.get(1).allUnits.get(i));

                          }
                      }
                          //print the stats
                  }
              }
               else if(Game.playersForDemo.get(1).myTurn)
               {
                   for(int i=0;i<Game.playersForDemo.get(1).allUnits.size();i++)
                   {
                      if(Game.playersForDemo.get(1).allUnits.get(i).getUnitID()==GUI.tileClicked.getOccupier().getUnitID())
                        {
                            System.out.println("unitSelected is being initialized ");
                            GUI.unitSelected=Game.playersForDemo.get(1).allUnits.get(i);
                            toggleButtons(GUI.buttonPanel,true);
                        } 
                   }
                   for(int i=0;i<Game.playersForDemo.get(0).allUnits.size();i++)
                   {
                      if(Game.playersForDemo.get(0).allUnits.get(i).getUnitID()==GUI.tileClicked.getOccupier().getUnitID())
                      {
                          if(GUI.enemySelected!=null&&GUI.enemySelected==Game.playersForDemo.get(1).allUnits.get(i))
                          {
                              GUI.statPanel=null;
                              enemySelected=null;
                          }
                          else
                          {
                               GUI.enemySelected = Game.playersForDemo.get(0).allUnits.get(0);
                               GUI.printStats(Game.playersForDemo.get(0).allUnits.get(0));
                          } 
                      }
                          
                          //print the stats
                   }
               }
           }
        else if(GUI.tileClicked!=null&&GUI.tileClicked.isOccupied&&!GUI.impendingAttack&&GUI.unitSelected==null&&formationPanel==null&&(Game.playersForDemo.get(1).up.numOfUnitsToPlace>0||Game.playersForDemo.get(0).up.numOfUnitsToPlace>0))  
        {
            //user is trying to select a unit before all the units are loaded
            JOptionPane.showMessageDialog(null, "please load all units before trying to select a unit ");
        }
        
        //used for attacking only, if attack button is selected, The unitSelected
       // is stored in attackUnit and we wait until a user clicks the unit that 
       // they want to attack.  Currently no range check to see if they are
       //able to reach them
        if(GUI.attackUnit!=null&&GUI.tileClicked!=null&&GUI.tileClicked.isOccupied)
        {
            GUI.attackUnit.attack(GUI.unitSelected);
            GUI.attackUnit=null;
            impendingAttack=false;
        }
        
        GUI.repainter();

    }
    //checks to see if someone clicked a tile and there are unitDraws in "queue"
    public boolean thereIsAUnitReadyToBeLoaded() 
    {
        System.out.println("in there is a unit ready to be loaded");
        return GUI.tileClicked!=null&&unitNum!=0;
    }
    

    //toggles whether buttons are seen or not seen
    //gets buttons by checking to see components on the panel
    
    //toggles visiblity of buttons and panel that holds the buttons
    private static void toggleButtons(JPanel panel,boolean b) 
    {
        //sets the buttonPanel to b, 
        panel.setVisible(b);
        
        //the number of components in the panel
        int bNum =panel.getComponentCount();
        for(int i=0;i<bNum;i++)
        {        
                panel.getComponent(i).setVisible(b);
             
        }
       GUI.repainter();
    }

    //method I rushed through last night, based of loadUnit(no parameters)
//    private void loadUnit(ArrayList<Unit> player1AllUnits,int num) 
//    {
//        Unit unit=player1AllUnits.get(num-1);
//        unit.setPosition(GUI.tileClicked.xPosition,GUI.tileClicked.yPosition);
//        GUI.unitDraws.add(new UnitDraw(unit));
//        GUI.tileClicked=null;
//        num--;
//        GUI.panel.repaint();
//    }

    //determines if i can load player 2 unitDraws onto game and into arrayList
//    private boolean playerTwoLoadUnits() 
//    {
//         return GUI.tileClicked!=null&&player2UnitNum!=0;
//    }
//    
//    public boolean playerOneLoadUnits()
//    {
//        return GUI.tileClicked!=null&&player1UnitNum!=0;
//    }
    
      public static JButton[] initializeButtons(JButton[] button)
   {
       BufferedImageLoaders bil = new BufferedImageLoaders();
       bil.loadAllButtons();
       ImageIcon attackB = bil.getIconAttack();
       ImageIcon defendB = bil.getIconDefend();
       ImageIcon checkB = bil.getCheckStatsIcon();
       ImageIcon moveB = bil.getIconMove();
       ImageIcon formB = bil.getIconSetFormation();
       ImageIcon cancelB = bil.getIconCancel();
       
       button[0]=new JButton(attackB);
       button[1]=new JButton(defendB);
       button[2]=new JButton(checkB);
       button[3]=new JButton(moveB);
       button[4]=new JButton(formB);
       button[5]=new JButton(cancelB);
       button[5].setName("cancel");
       
       ImageIcon end = bil.getIconEndTurn();
       GUI.endTurn=new JButton(end);
       endTurn.setVisible(true);
       endTurn.setOpaque(false);
       endTurn.setBorderPainted(false);

       endTurn.setBounds(GUI.panel.getWidth()/2,0,100,45);
       GUI.panel.add(endTurn);
       return button;
   }
      
   //method that prints stats, gets the information from the unit and prints it
    private static void printStats(Unit unitSelected) 
    {
        //unitSoldiers[0] won't work when soldier 0 dies
        //need to add these stats to the unit itself
        statPanel = new JPanel();
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
        
        //determing which way the unit is facing
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
        //using html allows me to carriage return
        JLabel stats = new JLabel("<html> Soldiers in Unit: "+unitNumLocal+
                "<br>AttackPower: "+unitAttack+"<br>DefensePower: "+unitDefense+
                "<br>unit is facing "+facing+"<br>armorClass: "+armorClass+
                "<br>speed: "+speed+"<br>range: "+range+"<br>chargeBonus: "+
                chargeBonus+"<br>stamina "+stamina+"</html>", SwingConstants.CENTER);
        
        //add the label to the panel
        GUI.statPanel.add(stats);
        //set the location and size of panel
        GUI.statPanel.setBounds(GUI.panel.getWidth()-250,0,200,300);
        //set the panel to true
        GUI.statPanel.setVisible(true);
        //add the panel to the big panel
        GUI.panel.add(GUI.statPanel);
        GUI.repainter();
    }

   
      
      
      
       //adds buttons to the panel
    public static void addButtonsToPanel(JButton[] button) 
    {
       GUI.panel.setLayout(null);
       BufferedImageLoaders bil = new BufferedImageLoaders();
       bil.loadAllButtons();
      
//       aPanel.setPreferredSize(new Dimension(500,300));
     
       buttonPanel.setBounds(0, GUI.gameFrame.getHeight()-150,GUI.gameFrame.getWidth(), 150);
       buttonPanel.setEnabled(false);
       buttonPanel.setOpaque(false);
       
       button[0].setBounds(buttonPanel.getWidth()/6-30-100,0,100,100);
       button[0].setOpaque(false);
       button[0].setContentAreaFilled(false);
       button[0].setBorderPainted(false);
       button[1].setBounds(buttonPanel.getWidth()/6*2-30-100,0,100,100);
       button[1].setOpaque(false);
       button[1].setContentAreaFilled(false);
       button[1].setBorderPainted(false);
       button[2].setBounds(buttonPanel.getWidth()/6*3-30-100,0,125,100);
       button[2].setOpaque(false);
       button[2].setContentAreaFilled(false);
       button[2].setBorderPainted(false);
       button[3].setBounds(buttonPanel.getWidth()/6*4-30-100,0,100,100);
       button[3].setOpaque(false);
       button[3].setContentAreaFilled(false);
       button[3].setBorderPainted(false);
       button[4].setBounds(buttonPanel.getWidth()/6*5-45-100,0,150,100);
       button[4].setOpaque(false);
       button[4].setContentAreaFilled(false);
       button[4].setBorderPainted(false);
       button[5].setBounds(buttonPanel.getWidth()/6*6-45-100,0,150,100);
       button[5].setOpaque(false);
       button[5].setContentAreaFilled(false);
       button[5].setBorderPainted(false);
       buttonPanel.add(button[0]);
       buttonPanel.add(button[1]);
       buttonPanel.add(button[2]);
       buttonPanel.add(button[3]);
       buttonPanel.add(button[4]);
       buttonPanel.add(button[5]);
       buttonPanel.setVisible(false);
       GUI.panel.add(buttonPanel);
       GUI.repainter();
    }
      
   
       //this will clean up the code a little, instead of doing these all the time, can just make one call
   public static boolean repainter()
   {
      if(GUI.panel!=null&&GUI.gameFrame!=null)
      {
         GUI.panel.repaint();
         GUI.gameFrame.repaint();
         GUI.gameFrame.revalidate(); 
         return true;
      }
      else
          return false;
   }

    //sets up the compass if it hasn't been initalized yet
     private static void initializeCompass() 
     {
           //the variable that is holding compass
           GUI.moveC=new Compass();
           moveC.init(); //initialize the compass
           
           //set where the compass is going to be on the panel
           GUI.moveC.setBounds(GUI.gameFrame.getWidth()-200,GUI.gameFrame.getHeight()-500,200,200);
           
           GUI.moveC.setVisible(true);
           GUI.panel.add(GUI.moveC); //add it to the panel
    }
    
    
     //only used once, used it to get information from the original panel to the new panel. 
   public static void copy(Component c,Component d)
   {
       
       d.setBounds(0, 0,c.getWidth(),c.getHeight());
//       r.getBounds
//       return r;
   }
   
   
    //if called and statPanel is initalized and visible, will update the stats of the unit
    public static void printStatsUpdater(Unit unit)
   {
       if(GUI.statPanel!=null&&GUI.statPanel.isVisible())
       {
           GUI.printStats(unit); //should be equal to GUI.unitSelected
       }
   }
    
     //paints the area around the unit that it can move, very useful.  broken atm
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
    
 @Override
    public void mousePressed(MouseEvent me) 
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
