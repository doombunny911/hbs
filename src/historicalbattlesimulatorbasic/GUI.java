/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
    static double turnCountForPersians=30;
    static double numberOfTilesWidth; //the number of tiles that make up the width
    static double numberOfTilesHeight;//the number of tiles that make up the height
    static JFrame gameFrame; //the gameframe, holds the panels/buttons
    static Map gameMap; //the Map, not being used atm
    static Tile[][] tileGameMap; //the array of tiles that make up the tileGameMap
    static int tileWidth; //the width of an individual tile
    static JPanel panel; //the panel holding all the tiles
    static ArrayList<UnitDraw> unitDraws=new ArrayList<>();//the unitDraws gotten via unitLoader
    static Tile tileClicked;//the tile that was clicked last
    static int unitNum; //the number of unitDraws in the unitLoader, decreased to parse through the arrayList
    static JButton endTurn; //the button that ends a players turn
    static JPanel buttonPanel = new JPanel(); //the panel that holds the buttons on bottom of screen
    static JPanel attackButtonPanel;
    static JPanel statPanel= new JPanel(); //the panel that holds the stats when they are printed
    static JPanel formationPanel;
    static Unit unitSelected; //the unit that is currently selected
    static Unit attackUnit; //may not be used, currently used for attacking
    static Compass moveC; //the compass
    static ArrayList<Unit> player1AllUnits;//player 1's unitDraws
    static ArrayList<Unit> player2AllUnits;//player 2's unitDraws
    static int player1UnitNum; //same as unitNum
    static int player2UnitNum; //same as above
    static Unit enemySelected;
    static JPanel combatPanel;
    private static JPanel turnPanel;
    static ImageIcon specialAbility;
    
  //initualize GUI whenever need to have a new Panel with mouselistener (only called once i think)
   public GUI(JPanel panel) {
       GUI.panel=panel;
       GUI.panel.addMouseListener(this);
       GUI.statPanel.setVisible(false);
       
   }
   private static void initCombatPanel(){
       CombatPanel combatPanel2 = new CombatPanel();
       combatPanel2.setLayout(null);
       combatPanel2.setVisible(true);
       combatPanel2.setBounds(GUI.gameFrame.getWidth()/2-100,GUI.gameFrame.getHeight()-4*GUI.gameFrame.getHeight()/5,300,200);
       GUI.panel.add(combatPanel2);
       GUI.repainter();
       
       combatPanel2.initCPanel();
       combatPanel = combatPanel2;
       
   }
   private static void initFormPanel()  {
         BufferedImageLoaders bil = new BufferedImageLoaders();
         bil.loadAllButtons();
         formationPanel=new JPanel();
         formationPanel.setLayout(null);
         formationPanel.setBounds(GUI.buttonPanel.getBounds());
         JButton[] button = new JButton[4];
         ImageIcon lineB = bil.getLineFormIcon();
         ImageIcon boxB = bil.getSquareFormIcon();
         ImageIcon wedgeB = bil.getWedgeFormIcon();
         button[0]=  new JButton(lineB);
         button[1] = new JButton(boxB);
         button[2] = new JButton(wedgeB);
         button[3] = new JButton("column");
         Component c = GUI.buttonPanel.getComponent(0);
         button[0].setBounds(c.getX(),c.getY(),c.getWidth(),c.getHeight());
         button[1].setBounds(c.getX()*2+c.getWidth(),c.getY(),c.getWidth(),c.getHeight());
         button[2].setBounds(c.getX()*3+c.getWidth(),c.getY(),c.getWidth(),c.getHeight());
         button[3].setBounds(c.getX()*4+c.getWidth(),c.getY(),c.getWidth(),c.getHeight());
         formationPanel.add(button[0]);
         formationPanel.add(button[1]);
         formationPanel.add(button[2]);
         formationPanel.add(button[3]);
         button[0].setBorderPainted(false);
         button[0].setContentAreaFilled(false);
         button[0].setOpaque(false);
         button[1].setBorderPainted(false);
         button[1].setContentAreaFilled(false);
         button[1].setOpaque(false);
         button[2].setBorderPainted(false);
         button[2].setContentAreaFilled(false);
         button[2].setOpaque(false);
         button[3].setBorderPainted(false);
         button[3].setContentAreaFilled(false);
         button[3].setOpaque(true);
        formationPanel.setOpaque(false);
//         busy=true;
         button[0].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) //line Formation
           {
               GUI.unitSelected.expendUnitPoint();
               GUI.removeSoldiersFromPreviousTiles(GUI.unitSelected);
               GUI.unitSelected.currentFormation.rowFormation();
               GUI.toggleButtons(formationPanel, false);
               GUI.toggleButtons(buttonPanel, true);
          }
       });
       
       button[1].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) //box Formation
           {
               GUI.unitSelected.expendUnitPoint();
               GUI.removeSoldiersFromPreviousTiles(GUI.unitSelected);
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

           }
       });
       button[3].addActionListener(new ActionListener(){
           
           @Override
           public void actionPerformed(ActionEvent ae){
               
               GUI.unitSelected.expendUnitPoint();
               GUI.removeSoldiersFromPreviousTiles(GUI.unitSelected);
               GUI.unitSelected.currentFormation.setColumnFormation();
               GUI.toggleButtons(formationPanel, false);
               GUI.toggleButtons(buttonPanel, true);
             
               
           }
       
           });
       panel.add(formationPanel);
       
     }
   //method that returns  the value of tileClicked
   public static Tile getTileClicked() {
         if(GUI.tileClicked!=null)
            return GUI.tileClicked;
        else
            return null;
    }
    //initializes defenseButton
   private static void initDefenseButton(){
       
       BufferedImageLoaders bil = new BufferedImageLoaders();
       bil.loadDefenseButtons();
        final JButton sAbility = new JButton(bil.getIconSpecialAbility());
        final JButton defendN = new JButton(bil.getIconDefend());
        sAbility.setBounds(GUI.buttonPanel.getComponent(2).getBounds());
        sAbility.setLocation(GUI.buttonPanel.getComponent(2).getX(),GUI.gameFrame.getHeight()-buttonPanel.getHeight());
        sAbility.setVisible(true);
        sAbility.setOpaque(false);
        sAbility.setBorderPainted(false);
        sAbility.setContentAreaFilled(false);
   
        defendN.setBounds(GUI.buttonPanel.getComponent(2).getBounds());
        defendN.setLocation(GUI.buttonPanel.getComponent(2).getX()+GUI.buttonPanel.getComponent(2).getBounds().width,GUI.gameFrame.getHeight()-buttonPanel.getHeight());
        defendN.setVisible(true);
        defendN.setOpaque(false);
        defendN.setBorderPainted(false);
        defendN.setContentAreaFilled(false);
        GUI.panel.add(sAbility);
        GUI.panel.add(defendN);
        
        sAbility.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               GUI.unitSelected.expendUnitPoint();
               System.out.println("Special Ability activated");
               GUI.unitSelected.useSpecialAbility();
                //reload the buttons that are loaded when a unit is clicked
               sAbility.setVisible(false);
               defendN.setVisible(false);
//               cancel.setVisible(false);
              toggleButtons(GUI.buttonPanel,true);

          }
       });defendN.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               GUI.unitSelected.expendUnitPoint();
               JOptionPane.showMessageDialog(GUI.panel, GUI.unitSelected+ " is now defending. This means that it gets a +"+GUI.unitSelected.defense+" bonus until the start of your next turn");
               System.out.println("Unit Defending");
               GUI.unitSelected.defend();
                //reload the buttons that are loaded when a unit is clicked
               defendN.setVisible(false);
               sAbility.setVisible(false);
//               cancel.setVisible(false);
              toggleButtons(GUI.buttonPanel,true);

          }
       });
        
    } 
   //this is called when the original unitDraws need to be loaded the first time, 
   //will probably become useless in final project but still pivotal now
   public void loadUnit(Unit unit) {
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
    
   public void mouseClicked(MouseEvent mac) {
       double findTileX= Math.ceil(mac.getX()/GUI.tileWidth);
       double findTileY=Math.ceil(mac.getY()/GUI.tileWidth);
       GUI.tileClicked=GUI.tileGameMap[(int)findTileX][(int)findTileY]; //sets the tile= the tile with the coords in the tileGameMap
       if(player1IsReadyToLoadUnits()) 
       {
           loadUnit(Game.playersForDemo.get(0).up.unitToBeLoaded);
           Game.playersForDemo.get(0).up.check=false;
           Game.playersForDemo.get(0).up.unitToBeLoaded=null;
//           System.out.println("in mouseClicked going to unitplacer ");
       }
       else if(player2IsReadyToLoadUnits())
       {
           loadUnit(Game.playersForDemo.get(1).up.unitToBeLoaded);
           Game.playersForDemo.get(1).up.check=false;
           Game.playersForDemo.get(1).up.unitToBeLoaded=null;
//           System.out.println("in mouseClicked going to unitplacer ");
       }
       if(GUI.tileClicked!=null)
        System.out.println("is this tile blocked " + GUI.tileClicked.tileBlocked);
      if(thereIsNoUnitCurrentlyAndThereIsAUnitOnThisTile())
      {
              if(player1Turn())   
              {
                  for(int i=0;i<Game.playersForDemo.get(0).allUnits.size();i++)
                  {
                      if(player1UnitIsEqualToUnitSelectedAt(i))
                      {
                          System.out.println("unitSelected is being initialized ");
                  
                        //this is where unitselected gets initialized.  it will stay initialized until cancel selection is pressed
                          GUI.unitSelected=Game.playersForDemo.get(0).allUnits.get(i);
                          toggleButtons(GUI.buttonPanel,true);
                      }
                  }
                  for(int i=0;i<Game.playersForDemo.get(1).allUnits.size();i++)
                  {
                      if(player2UnitIsEqualToUnitSelectedAt(i))
                      {
                          if(PlayerSelectedSameEnemyUnitAgain(1,i))
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
                  }
              }
               else if(player2Turn())
               {
                   for(int i=0;i<Game.playersForDemo.get(1).allUnits.size();i++)
                   {
                      if(player2UnitIsEqualToUnitSelectedAt(i))
                        {
                            System.out.println("unitSelected is being initialized ");
                            GUI.unitSelected=Game.playersForDemo.get(1).allUnits.get(i);
                            toggleButtons(GUI.buttonPanel,true);
                        } 
                   }
                   for(int i=0;i<Game.playersForDemo.get(0).allUnits.size();i++)
                   {
                      if(player1UnitIsEqualToUnitSelectedAt(i))
                      {
                          if(PlayerSelectedSameEnemyUnitAgain(0,i))
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
                   }
               }
           }
           
//        else if(userTriesToSelectUnitBeforeAllUnitsArePlaced())  
//        {
//            //user is trying to select a unit before all the units are loaded
//            JOptionPane.showMessageDialog(null, "please load all units before trying to select a unit ");
//        }
        
        //used for attacking only, if attack button is selected, The unitSelected
       // is stored in attackUnit and we wait until a user clicks the unit that 
       // they want to attack.  Currently no range check to see if they are
       //able to reach them
//        if(thereIsAnAttackReadyToHappenAndTileClickedIsOccupied())
//        {
//            GUI.attackUnit.attack(GUI.unitSelected);
//            GUI.attackUnit=null;
//            impendingAttack=false;
//        }
        
        GUI.repainter();
    }
    //checks to see if someone clicked a tile and there are unitDraws in "queue"
   public boolean thereIsAUnitReadyToBeLoaded() {
        System.out.println("in there is a unit ready to be loaded");
        return GUI.tileClicked!=null&&unitNum!=0;
    }
    //toggles whether buttons are seen or not seen
    //gets buttons by checking to see components on the panel
      //buttonLoader, used for button action listening
   public static void buttonLoader(){
       GUI.panel.setLayout(null);
       GUI.buttonPanel.setLayout(null);
       buttonPanel.setBounds(0, GUI.gameFrame.getHeight()-150,GUI.gameFrame.getWidth(), 150);
       buttonPanel.setEnabled(false);
       buttonPanel.setVisible(false);
       buttonPanel.setOpaque(false);
       JButton[] button=new JButton[6]; //the array of all the "bottom" buttons
       button=initializeButtons(button); //method that creates the buttons, than returned to the variable
       
       
       addButtonsToPanel(button); //this method adds the buttons to the panel
       
       
       //the rest of the method deals with what happens when you push each button
       button[0].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               if(GUI.unitSelected.hasUnitPoints())
               {
               GUI.attackUnit=GUI.unitSelected;
               GUI.toggleButtons(GUI.buttonPanel,false);
               GUI.initCombatPanel();
               }
               else
                   {
                       JOptionPane.showMessageDialog(formationPanel, GUI.unitSelected.nameOfUnit+" is out of unit points. Select a different unit, or end your turn" );
                       GUI.toggleButtons(GUI.buttonPanel,false);
                       GUI.unitSelected=null;
                   }
           }
           
       });
       
       button[1].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae)
           {
               if(GUI.unitSelected.hasUnitPoints())
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
               else
                   {
                       JOptionPane.showMessageDialog(formationPanel, GUI.unitSelected.nameOfUnit+" is out of unit points. Select a different unit, or end your turn" );
                       GUI.toggleButtons(GUI.buttonPanel,false);
                       GUI.unitSelected=null;
                   }
           }
       });
       
       button[2].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
      //if statPanel!=null (should be never) and is not visible, call method print stats
               if(componentNotNullAndIsNotVisible(statPanel))
                    GUI.printStats(GUI.unitSelected);
               else if(componentNotNullAndIsVisible(statPanel))
                   GUI.statPanel.setVisible(false);
               //get the stats of the unit clicked and print them on side of screen
           }
       });
       button[3].addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) 
           {
               if(GUI.unitSelected.hasUnitPoints())
               {
                   GUI.unitSelected.expendUnitPoint();
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
                  else
                   {
                       JOptionPane.showMessageDialog(formationPanel, GUI.unitSelected.nameOfUnit+" is out of unit points. Select a different unit, or end your turn" );
                       GUI.toggleButtons(GUI.buttonPanel,false);
                       GUI.unitSelected=null;
                   }
           }
       });
       button[4].addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) 
           {
                   initFormPanel();
                   if(GUI.unitSelected.hasUnitPoints())
               {
                   
                   if(!formationPanel.isVisible())
                   {
                       formationPanel.setVisible(true);
                   }
               
                    GUI.toggleButtons(buttonPanel, false);

                    if(componentNotNullAndIsVisible(moveC))
                        moveC.setVisible(false);
                    if(componentNotNullAndIsVisible(attackButtonPanel))
                        attackButtonPanel.setVisible(false);
               }
                   
           else
                   {
                       JOptionPane.showMessageDialog(formationPanel, GUI.unitSelected.nameOfUnit+" is out of unit points. Select a different unit, or end your turn" );
                       GUI.toggleButtons(GUI.buttonPanel,false);
                       GUI.unitSelected=null;
                   }
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
                   if(componentNotNullAndIsVisible(moveC))
                        GUI.moveC.setVisible(false);
                   if(componentNotNullAndIsVisible(formationPanel))
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
           }
       });
       endTurn.addActionListener(new ActionListener()
       {
             @Override
             public void actionPerformed(ActionEvent ae)
             { 
                 
                 GUI.refreshTurnPanel();
                 
                 
                 if(GUI.formationPanel!=null&&formationPanel.isVisible())
                     GUI.toggleButtons(formationPanel, false);
                //this button will end the turn of the player and go to next player's turn
//                 System.out.println("test for end Turn Button");
                 if(GUI.unitSelected!=null)
                 {
                      GUI.unitSelected.resetUnitPoints();
                      GUI.unitSelected.endTurn();
                     
                      GUI.unitSelected=null;
                 }
                 GUI.refreshTurnPanel();
                 GUI.tileClicked=null;
                 GUI.toggleButtons(buttonPanel, false);
                 if(moveC!=null&&moveC.isVisible())
                     moveC.setVisible(false);
                 if(player1HasNoUnits()||spartanVictory())
                 {
                      Game.playersForDemo.get(1).isWinner=true;
                      JOptionPane.showMessageDialog(null, "Congratulations " + Game.playersForDemo.get(1).playerName + " You are victorious");
                 }
                 else if(player2HasNoUnits())
                 {
                     Game.playersForDemo.get(0).isWinner=true;
                     JOptionPane.showMessageDialog(null, "Congratulations " + Game.playersForDemo.get(0).playerName + " You are victorious");
                 }
                 if(player1Turn())
                 {
                     for(Unit u: Game.playersForDemo.get(0).getUnitList())
                     {
                         u.resetUnitPoints();
                            
                     }
                     for(Unit u: Game.playersForDemo.get(1).getUnitList())
                     {
                         u.undefend();
                     }
                     
                    Game.playersForDemo.get(0).myTurn=false;
                    Game.playersForDemo.get(1).myTurn=true;
                     JOptionPane.showMessageDialog(null, Game.playersForDemo.get(0).playerName + " Your turn is now over. It is now time for " + Game.playersForDemo.get(1).playerName + " to take their turn" );
                    
                      GUI.refreshTurnPanel();
                 }
                 else if(player2Turn())
                 {
                     turnCountForPersians--;
                     for(Unit u: Game.playersForDemo.get(1).getUnitList())
                     {
                         u.resetUnitPoints();
                         
                     }
                     for(Unit u: Game.playersForDemo.get(0).getUnitList())
                     {
                         u.undefend();
                     }
                     Game.playersForDemo.get(1).myTurn=false;
                     Game.playersForDemo.get(0).myTurn=true;
                     JOptionPane.showMessageDialog(null, Game.playersForDemo.get(1).playerName + " Your turn is now over. It is now time for " + Game.playersForDemo.get(0).playerName + " to take their turn" );
                   
                    GUI.refreshTurnPanel();
                 }
             } 
       });
   }
   public static void addButtonsToPanel(JButton[] button) {
        
        button = setButtonsWithoutDefaults(button);
            
        
        buttonPanel.add(button[0]);
        buttonPanel.add(button[1]);
        buttonPanel.add(button[2]);
        buttonPanel.add(button[3]);
        buttonPanel.add(button[4]);
        buttonPanel.add(button[5]);
       GUI.panel.add(buttonPanel);
       GUI.repainter();
    }
   protected static JButton[] setButtonsWithoutDefaults(JButton[] button){
       
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
        return button;
    }
    //toggles visiblity of buttons and panel that holds the buttons
   public static void toggleButtons(JPanel panel,boolean b){
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
   public static JButton[] initializeButtons(JButton[] button){

       BufferedImageLoaders bil = new BufferedImageLoaders();
       
       ImageIcon[] buttonImages = new ImageIcon[6];
      buttonImages= getButtonImages(buttonImages,bil);
       
      button=getButtonInit(button,buttonImages);

        
       
       ImageIcon end = bil.getIconEndTurn();
       GUI.endTurn=new JButton(end);
       endTurn.setVisible(true);
       //endTurn.setOpaque(false);
       endTurn.setBorderPainted(true);
       endTurn.setBackground(Color.black);
     //  endTurn.setContentAreaFilled(false);
       javax.swing.border.Border borderUsed = BorderFactory.createLineBorder(Color.white);
       endTurn.setBorder(borderUsed);
       GUI.initTurnPanel();
       endTurn.setBounds(GUI.panel.getWidth()-100,turnPanel.getHeight(),100,50);
       GUI.panel.add(endTurn);
       
      
       return button;
   }
   protected static JButton[] getButtonInit(JButton[] button, ImageIcon[] buttonImages) {
        button[0]=new JButton(buttonImages[0]);
        button[1]=new JButton(buttonImages[1]);
        button[2]=new JButton(buttonImages[2]);
        button[3]=new JButton(buttonImages[3]);
        button[4]=new JButton(buttonImages[4]);
        button[5]=new JButton(buttonImages[5]);
        return button;
    }
   protected static ImageIcon[] getButtonImages(ImageIcon[] buttonImages, BufferedImageLoaders bil) {
        bil.loadAllButtons();
        buttonImages[0] = bil.getIconAttack();
        buttonImages[1] = bil.getIconDefend();
        buttonImages[2] = bil.getCheckStatsIcon();
        buttonImages[3] = bil.getIconMove();
        buttonImages[4] = bil.getIconSetFormation();
        buttonImages[5] = bil.getIconCancel();
        
        return buttonImages;
    }
   public static int unitTally(ArrayList<Unit> units){
          int tally =0;
          for(Unit u : units)
          {
              tally++;
          }
          return tally;
      }   
   public static void initTurnPanel(){
     // GUI.panel.remove(turnPanel);
       turnPanel = new JPanel();
       turnPanel.setBackground(Color.black);
       turnPanel.setBounds(GUI.panel.getWidth()-100, 0, 100, 500);
    
        refreshTurnPanel();
       javax.swing.border.Border borderUsed = BorderFactory.createLineBorder(Color.white);
       turnPanel.setBorder(borderUsed);
       
       GUI.panel.add(turnPanel);
       refreshTurnPanel();
      
   } 
   static boolean oneDisplay1 = true;
          static  boolean oneDisplay2 = true;
        static   boolean oneDisplay3 = true;
        static   boolean oneDisplay4 = true;
        static   boolean oneDisplay5 = true;
          static boolean oneDisplay6 = true;
   
    public static void refreshTurnPanel() {
        GUI.panel.remove(turnPanel);
       turnPanel = new JPanel();
       turnPanel.setBackground(Color.black);
       turnPanel.setBounds(GUI.panel.getWidth()-100, 0, 100, 500);
        JLabel whichPlayersTurn = new JLabel();
        JLabel turnCount = new JLabel();
        if(player1Turn())
        {
            whichPlayersTurn.setText("<html><center><h3><font color = 'white' face='Times New Roman'>Player<h1><font color = 'white'>1</h1><font color='white'>'s Turn</font></h3></center><br><b><font color='white'>-------------</b></font></html>");
            turnPanel.add(whichPlayersTurn);
            for(Unit u: Game.playersForDemo.get(0).getUnitList())
            {
                JLabel unitPoints = new JLabel("<html><center><font face='Times New Roman' color='white'>"+u.nameOfUnit+":<br><font color='red'><b>"+u.unitPoints+"</b></font> U.P.</center></html>");
                turnPanel.add(unitPoints);
                
            }
            
        }
        else if(player2Turn())
        {
            whichPlayersTurn.setText("<html><center><h3><font color = 'white' face='Times New Roman'>Player<br><h1><font color = 'white'>2</font></h1><font color='white'>s Turn</font></h3></center></html>");
            turnPanel.add(whichPlayersTurn);
            for(Unit u: Game.playersForDemo.get(1).getUnitList())
            {
                JLabel unitPoints = new JLabel("<html><center><font face='Times New Roman' color='white'>"+u.nameOfUnit+":<br> <font color='red'><b>"+u.unitPoints+"</b></font> U.P.</center></html>");
                turnPanel.add(unitPoints);
                
            }
        }
        //Turn Count
        turnCount.setText("<html><br><center><font face='Times New Roman' color='white'>Turns Till<br>"
                + " The Spartans Win:<br> <h1><b><font color = 'red' face='Times New Roman'>"+
                turnCountForPersians+"</font></b></h1></center></font></html>");
            turnPanel.add(turnCount);
           
            if(turnCountForPersians==30&&oneDisplay1)
            {
                JOptionPane.showMessageDialog(GUI.panel,"<html><i>The 'God King' Xerxes of Persia stands on the verge of conquering all of Greece. <br>"
                        + " In between him and his goal is a group of 300 Spartans and approximately 7,000 other Greeks, <br>lead by King Leonidas of Sparta prepared to face his army"
                        + "of over a million military personnel</i></html>");
                oneDisplay1 = false;
            }
            else if(turnCountForPersians==20&&oneDisplay2)
            {
                JOptionPane.showMessageDialog(GUI.panel,"<html><i>After assault by the Persian forces, the first day of fighting draws to a close...</i></html>");
                oneDisplay2 = false;
            }
            else if(turnCountForPersians==10&&oneDisplay3)
            {
                JOptionPane.showMessageDialog(GUI.panel,"<html><i>After the failure of Xerxes deadly 'Immortals' to encircle and destroy the Spartan forces <br"
                        + "on the Second day of fighting, his forces were given an unexpected boon:<br "
                        + "the discovery of a mountain path that would allow them to outflank the Greeks.<br>"
                        + " Meanwhile, the majority of the Greek army retreated, but Leonidas and his Spartans and some of the Thebans remained as a rearguard... </i></html>");
                oneDisplay3 = false;
            }
            else if(turnCountForPersians==0&&oneDisplay4)
            {
                JOptionPane.showMessageDialog(GUI.panel,"<html><i>Although all the Spartans and Thebans who remained perished, they held the pass for long enough to allow the <br>"
                        + "rest of Greece to prepare for war. Herodatus, classical historian remarks<br>"
                        + "</i></html>");
                JOptionPane.showMessageDialog(GUI.panel,"<html><i>'Go tell the Spartans, passerby:\n" +
                "That here, by Spartan law, we lie</i></html>");
                oneDisplay4 = false;
       
            }
        GUI.panel.add(turnPanel);
        GUI.repainter();
    }
   //method that prints stats, gets the information from the unit and prints it
   private static void printStats(Unit unitSelected){
        //unitSoldiers[0] won't work when soldier 0 dies
        //need to add these stats to the unit itself
        statPanel = new JPanel();
      
    //GUI.statPanel.setLayout(new GridLayout(3,1));
       
       
        int unitNumLocal=unitSelected.unitSize;
        int unitAttack=(int)unitSelected.attack;
        int unitDefense = (int)unitSelected.defense;
        int facingInt = unitSelected.unitFacing;
        int armorClass = (int)unitSelected.armorClass;
        int speed = (int)unitSelected.speed;
        int range = (int)unitSelected.range;
        int chargeBonus = (int)unitSelected.chargeBonus;
        int stamina = (int)unitSelected.stamina;
        
//        double xPos = unitSelected.tileOccupied.xPosition;
  //      double yPos = unitSelected.tileOccupied.yPosition;
        String unitName = unitSelected.nameOfUnit;
        int unitPoints = unitSelected.returnUnitPoints();
        JButton close = new JButton("Close");
       // close.setBounds(GUI.statPanel.getWidth(), -4*GUI.statPanel.getHeight()/5, GUI.statPanel.getWidth(), 50);

        close.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) //box Formation
           {
              statPanel.setVisible(false);
           }
       });
        String facing = unitIsFacing(facingInt);
        //using html allows me to carriage return
        statPanel.setBackground(Color.BLACK);
        javax.swing.border.Border borderUsed = BorderFactory.createLineBorder(Color.white);
        statPanel.setBorder(borderUsed);
        JLabel nameSection = new JLabel("<html> <b><h3><font color = 'white'> "+unitName +"</h3></b></font></html>");
        GUI.statPanel.add(nameSection);
        
        JLabel stats = new JLabel("<html><br> <font color = 'white'> Soldiers in Unit: "+unitNumLocal+
                "<br>Attack Power: "+unitAttack+"<br>DefensePower: "+unitDefense+
                "<br>Orientation "+facing+"<br>Armor Class: "+armorClass+
                "<br>speed: "+speed+"<br>Range: "+range+"<br>Charge Bonus: "+
                chargeBonus+"<br>Stamina "+stamina+"<br><br> Unit Points Remaining"+unitPoints+"</font></html>", SwingConstants.CENTER);
        
      
        //add the label to the panel
        GUI.statPanel.add(stats);
        BufferedImage unitPic = unitSelected.getUnitPic(unitSelected);
        ImageIcon unitPic2 = new ImageIcon(unitPic);
        JLabel up = new JLabel(unitPic2);
       //add image of unit selected
        GUI.statPanel.add(up);
        //GUI.statPanel.add(new JLabel(" "));
        GUI.statPanel.add(close);
        //set the location and size of panel
        GUI.statPanel.setBounds(statPanel.getWidth(),200/*(GUI.turnPanel.getHeight()+GUI.endTurn.getHeight())*/,200,300);
        //set the panel to true
        GUI.statPanel.setVisible(true);
        //add the panel to the big panel
        GUI.panel.add(GUI.statPanel);
        GUI.repainter();
    }
       //this will clean up the code a little, instead of doing these all the time, can just make one call
   public static boolean repainter(){
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
   private static void initializeCompass() {
           //the variable that is holding compass
           GUI.moveC=new Compass();
           moveC.init(); //initialize the compass
           
           //set where the compass is going to be on the panel
           GUI.moveC.setBounds(GUI.gameFrame.getWidth()-200,GUI.gameFrame.getHeight()-500,200,200);
           
           GUI.moveC.setVisible(true);
           GUI.panel.add(GUI.moveC); //add it to the panel
    }
     //only used once, used it to get information from the original panel to the new panel. 
   public static void copy(Component c,Component d){
       d.setBounds(0, 0,c.getWidth(),c.getHeight());
   }
    //if called and statPanel is initalized and visible, will update the stats of the unit
   public static void printStatsUpdater(Unit unit){
       if(GUI.statPanel!=null&&GUI.statPanel.isVisible())
       {
           GUI.printStats(unit); //should be equal to GUI.unitSelected
       }
   }
   protected boolean userTriesToSelectUnitBeforeAllUnitsArePlaced() {
        return GUI.tileClicked!=null&&GUI.tileClicked.isOccupied&&GUI.unitSelected==null&&formationPanel==null&&(Game.playersForDemo.get(1).up.numOfUnitsToPlace>0||Game.playersForDemo.get(0).up.numOfUnitsToPlace>0);
    }
   protected static boolean spartanVictory()
   {
       if(turnCountForPersians<0)
       {
           return true;
       }
       else
       {
           return false;
       }
   }
   protected boolean PlayerSelectedSameEnemyUnitAgain(int playerNum,int i) {
        return GUI.enemySelected!=null&&GUI.enemySelected==Game.playersForDemo.get(playerNum).allUnits.get(i);
    }
   protected boolean player2UnitIsEqualToUnitSelectedAt(int i) {
        return Game.playersForDemo.get(1).allUnits.get(i).getUnitID()==GUI.tileClicked.getOccupier().getUnitID();
//        return Game.playersForDemo.get(1).allUnits.get(i).yPosition==GUI.tileClicked.yPosition&&Game.playersForDemo.get(1).allUnits.get(i).xPosition==GUI.tileClicked.xPosition;

    }
   protected boolean player1UnitIsEqualToUnitSelectedAt(int i) {
//        return Game.playersForDemo.get(0).allUnits.get(i).yPosition==GUI.tileClicked.yPosition&&Game.playersForDemo.get(0).allUnits.get(i).xPosition==GUI.tileClicked.xPosition;
        return Game.playersForDemo.get(0).allUnits.get(i).getUnitID()==GUI.tileClicked.getOccupier().getUnitID();
    }
   protected boolean thereIsNoUnitCurrentlyAndThereIsAUnitOnThisTile() {
        return GUI.tileClicked!=null&&GUI.tileClicked.isOccupied&&GUI.unitSelected==null;
        
//        return GUI.tileClicked!=null&&GUI.tileClicked.isOccupied&&!GUI.impendingAttack&&GUI.unitSelected==null&&formationPanel==null&&Game.playersForDemo.get(0).up.numOfUnitsToPlace==0&&Game.playersForDemo.get(1).up.numOfUnitsToPlace==0||(formationPanel!=null&&formationPanel.isVisible());
    }
   protected boolean player2IsReadyToLoadUnits() {
        return Game.playersForDemo!=null&&Game.playersForDemo.get(1).up.check&&GUI.tileClicked!=null;
    }
   protected boolean player1IsReadyToLoadUnits() {
        return Game.playersForDemo!=null&&Game.playersForDemo.get(0).up.check&&GUI.tileClicked!=null;
    }
   protected static JButton[] removeDefaultLookOfJButtons(JButton[] button) {
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
        return button;
    }
   protected static String unitIsFacing(int facingInt) {
        String facing;
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
        return facing;
    }
   public static boolean player2Turn() {
               return Game.playersForDemo.get(1).myTurn;
           }
   public static boolean player1Turn() {
               return Game.playersForDemo.get(0).myTurn;
           }
   public static boolean secretPathAvailable(){
     boolean secret=false;
       if(turnCountForPersians<20&&secret!=true)
       {
           System.out.println("TRUEEEE");
            GUI.gameMap.generateTiles();
           secret = true;
          
       }
       else if(turnCountForPersians<20)
       {
           secret = true;
       }
       else
       {
          secret = false;
       }
       return secret;
   }
   public static boolean player2HasNoUnits() {
               return Game.playersForDemo.get(1).allUnits.isEmpty()||Game.playersForDemo.get(0).isWinner;
           }
   public static boolean player1HasNoUnits() {
               return Game.playersForDemo.get(0).allUnits.isEmpty()||Game.playersForDemo.get(1).isWinner;
           }
   protected static boolean componentNotNullAndIsVisible(Component c) {
        return c!=null&&c.isVisible();
    }
   protected static boolean componentNotNullAndIsNotVisible(Component c) {
        return c!=null&&c.isVisible()==false;
    }
   protected static int determineWhichUnitDrawContainsUnitIdEqaulToUnitSelectedAt(Unit u) {
        //find the index of the unitDraw that needs to be removed
        int index=-1;
        for(int i=0;i<GUI.unitDraws.size();i++)
        {
//           System.out.println("in moveLogic before index selection");
            if(u.getUnitID()==GUI.unitDraws.get(i).thisUnit.getUnitID())
            {
//                System.out.println("unitID of unitDraw at "+ i+ " = "+
//                        GUI.unitDraws.get(i).thisUnit.getUnitID());
                index=i;
                break;
            }
        }if(index==-1)
           System.out.println("never found the right index in compass");
        return index;
    }
   protected static void removeSoldiersFromPreviousTiles(Unit u) {
        //remove the soldiers from the previous tiles
        for(int i=0;i<GUI.numberOfTilesHeight;i++)
        {
           for(int j=0;j<GUI.numberOfTilesWidth;j++)
            {
                if(thereIsASoldierWhereThereShouldNotBe(j, i,u))
                    GUI.tileGameMap[j][i].removeSoldier();
            } 
        }
            
    }
   protected static ArrayList<Point>  findSoldiersOfThisUnit(Unit unit){
       ArrayList<Point> list = new ArrayList<>();
       for(int i=0;i<GUI.numberOfTilesHeight;i++)
       {
           for(int j=0;j<GUI.numberOfTilesWidth;j++)
           {
               if(GUI.tileGameMap[j][i].isOccupied&&GUI.tileGameMap[j][i].getOccupier().getUnitID()==unit.getUnitID())
                   list.add(new Point(j,i));
           }
       }
       
       
       return list;
   }
   protected static boolean thereIsASoldierWhereThereShouldNotBe(int j, int i,Unit u) {
        return GUI.tileGameMap[j][i].getOccupier()!=null&&GUI.tileGameMap[j][i].getOccupier().getUnitID()==u.getUnitID();
    }
     //paints the area around the unit that it can move, very useful.  broken atm
   public static void paintRange(Unit unitSelected,Graphics g)  {
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
   public void mousePressed(MouseEvent me) {
    }
   @Override
   public void mouseReleased(MouseEvent me) {
    }
   @Override
   public void mouseEntered(MouseEvent me) {
    }
   @Override
   public void mouseExited(MouseEvent me){
    }
}
