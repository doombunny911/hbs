/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Edward
 */
class Scenario {
    
    //needs to create a map
    Map map = null;
    Player p1 = null;
    Player p2 = null;
   
    public Scenario(){
       // createNewScenario();
        p1 = new Player();
        p2 = new Player();
    }

    public void createNewScenario() throws HeadlessException {
        Game game = new Game();
        
        map = new Map(10);
        map.generateLoaded();
        GUI.scenario = this;
   
        String name = JOptionPane.showInputDialog(null, "Enter the name for player 1");
        JOptionPane.showMessageDialog(null," choose the army for: " + name);
        p1 = new Player(name);
        name = JOptionPane.showInputDialog(null, " Enter the name for player 2");
        JOptionPane.showMessageDialog(null,"  choose the army for : " +name );
        p2 = new Player(name);
        Game.playersForDemo.add(p1);
        Game.playersForDemo.add(p2);
        
        p1.up.setUpButtons();
        p2.up.setUpButtons();
        
        p1.up.setBounds(200, GUI.gameFrame.getHeight()-400, 200, 200);
        p2.up.setBounds(GUI.gameFrame.getWidth()-400, GUI.gameFrame.getHeight()-400, 200, 200);
        GUI.panel.add(p1.up);
        GUI.panel.add(p2.up);
        GUI.scenario = this;
        game.gameMap=map;
        GUI.buttonLoader();
    }
    
    public void LoadScenario()
    {
       //GUI.scenario = null;
        
         
        final JFileChooser fc = new JFileChooser();
        File dir = new File("Scenarios"+File.separator+"SAnchor.txt");
        
        
        
        System.out.println(dir);
        fc.setCurrentDirectory(dir);
        File  current = fc.getCurrentDirectory();
        
        System.out.println(current);
        fc.showOpenDialog(null);
        
 
       
        String name = fc.getSelectedFile().getName();
       
        System.out.println("You have selected to load "+name+ "map.");
        
         this.loadAllScenarioParts(name);
      
        //loads in the file, and determines a few different things
        //Map 
        //Player Names
        //All Unit Stats and Positions
        
    }
    String mapName ="";
    String player1Name ="";
    String player2Name = "";
     private ArrayList<Tile> loadAllScenarioParts(String name) {
        try {
            //Load all units
            //while
            
            File file = new File("Scenarios"+ File.separator+name);
            Scanner reader = new Scanner(new FileInputStream(file));
         mapName = reader.nextLine();
         player1Name = reader.nextLine();
         System.out.println(player1Name+" =");
         //System.out.println(player1Name);
         p1.setName(player1Name);
         player2Name = reader.nextLine();
         p2.setName(player2Name);
         while(reader.hasNext())
         {
             if(reader.nextLine().equals("---"))
                 {
                     
                     String unitName = reader.nextLine(); ///   Unit Name 
                     System.out.println(unitName);
                     //System.out.println("xPos: "+xPos);
                    // String yPos = reader.nextLine();
                     //System.out.print("yPos: "+yPos);
                     //System.out.println("");
                    // String blocked = reader.nextLine();
                     //String imageName = reader.nextLine();
                     //System.out.println(imageName);
//                     Tile t = new Tile(Integer.parseInt(xPos),Integer.parseInt( yPos), 10,10);
 //                 if(blocked.equals("true"))
//               {
//                       t.setBlocked();
//                       }
//                    this.setImageFromString(t, imageName);
//                     nUnit.setUnitUnitID();
//                     tilesToIncorporate.add(t);   
                     //System.out.println("++++++++++++++++++++++ TILE "+t.image.name+" LOADED ");
                     loadUnit(reader,unitName);
                 }
             else 
             {
//                 reader.nextLine();
             }
         }
         
       
        
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UnitLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;//tilesToIncorporate;
    }
    public void loadUnit(Scanner reader, String unitName){        
       
            //Scanner reader = new Scanner(new FileInputStream(file));
            //reader.findWithinHorizon(nameOfUnit, 0);
            String nUnitName= unitName; //0
            
                int nUnitType= Integer.parseInt(reader.nextLine()); //1
                
                int nDMGDice = Integer.parseInt(reader.nextLine()); //3
                
                double nAttackBonus = Double.parseDouble(reader.nextLine()); //
                double dmgBonus = Double.parseDouble(reader.nextLine());//4
                double hp = Double.parseDouble(reader.nextLine()); //5
                double ac = Double.parseDouble(reader.nextLine()); //6
                double def =Double.parseDouble(reader.nextLine()); //7
                double speed = Double.parseDouble(reader.nextLine()); //8
                double range = Double.parseDouble(reader.nextLine()); //9
                double chargeBonus = Double.parseDouble(reader.nextLine()); //10
                double stamina = Double.parseDouble(reader.nextLine()); //11
                double morale = Double.parseDouble(reader.nextLine()); //12
                int unitSize = Integer.parseInt(reader.nextLine()); //13
                String spriteName = reader.nextLine();
                int xPos = Integer.parseInt(reader.nextLine());
                int yPos = Integer.parseInt(reader.nextLine());
                //System.out.println("XPOS " +xPos);
                int playerNum = Integer.parseInt(reader.nextLine());
                System.out.println("PLAYER NUM "+playerNum);
                String playerName = reader.nextLine();
                System.out.println(playerName);
            Soldier soldier = new Soldier(nUnitName, nUnitType, nDMGDice, nAttackBonus,dmgBonus, hp,ac,def, speed, range, chargeBonus, stamina, morale);
            
            Unit unit = new Unit(soldier,unitSize);
            unit.setSprite(spriteName);
            unit.xPosition = xPos; //may need to be multipled by 10
            unit.yPosition = yPos; //may need to be multiplied by 10
            unit.playerName = playerName;
            System.out.print(unit.playerName);
         //  System.out.println("Units Speed "+unit.speed);
            if(p1.playerName.equals(unit.playerName))
            {
                System.out.println("MATCH");
                p1.allUnits.add(unit);
            }
            else if(p2.playerName.equals(unit.playerName))
            {
                p2.allUnits.add(unit);
            }
            else
            {
                System.out.println("NO MATCH");
            }
    } 
  
    
    //use unitPlacers to allow prof to place units
    
    //need an arrayList of UnitDraws
    
    //need an arrayList of units for player 1 and player 2
    
    
    
}
