/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Schmalz
 */
public class Player{

   
Frame frame = JOptionPane.getRootFrame();
String playerName; //the players actual name
String nameOfArmy; //The name of the army
int unitLimit, unitsRemaning;
boolean isWinner = false;
ArrayList <Unit> allUnits; //all of a players units
    UnitLoader playerUnits = new UnitLoader();
   
public static void main(String[] args)
{
    Player newPlayer = new Player();
    newPlayer.takeTurn();
    
}

//LOADING Player problems
    public static void singlePlayerDialog(ArrayList<Player> players) throws HeadlessException, NumberFormatException {
    Player playHolder   = new Player();   
    playHolder.playerName = JOptionPane.showInputDialog ( "Enter player name: " );
   // playHolder.unitLimit = Integer.parseInt(JOptionPane.showInputDialog ( "Set the max number of Units to be deployed in this army: " ));
    singlePlayerLoader(playHolder, players);
    }

    private static void singlePlayerLoader(Player playHolder, ArrayList<Player> players) throws HeadlessException {
      
    JOptionPane.showMessageDialog(null,"Select a unit list to choose your army from");
        UnitLoader loader = new UnitLoader();
        ArrayList<Unit> allUnitsLocal = loader.runLoader();
        allUnitsLocal = loader.unitPrepper(allUnitsLocal);
        playHolder.allUnits = allUnitsLocal;
        players.add(playHolder);
        
    }
   
   

    public Player()
    {
       
    
    }
   
    public void addUnit(Unit u, int xPos, int yPos)
    {
        u.xPosition = xPos;
        u.yPosition = yPos;
        allUnits.add(u);
        
    }
    /*
    Provides the basic options for the player
    @selectedUnit - The unit being targeted 
    */
    public void menuOptions(Unit selectedUnit)
    {
        
    }
    //sets the players name
    public void setPlayerName(String playerName)
    {
        this.playerName=playerName;
    }
    //adds unit to the army

    public int getUnitCount()
    {
        return allUnits.size();
    }
    //Select a unit
    public Unit unitSelector(Player play)
    {
        return null;



    }
    //Each player gets to do 2 things with each unit
 /*   public void selectUnit(Unit unit)
    {
        //the following are all options that can be selected    
        System.out.println("Select what you want to do with the unit. "
                + "1.Move "
                + "2.Attack "
                + "3.Defend "
                + "4.Get Stats"
                + "5.Cancel" );
        int selection;
        while(scan.nextInt()!=0&&unit.unitPoints>0)
        {
            selection = scan.nextInt();
            if(selection==1) //move
            {
                this.openMoveMenu(unit);
                unit.unitPoints--;
            }
            else if(selection==2)
            {
                this.openAttackMenu(unit);
                unit.unitPoints--;
            }
            else if(selection==3)
            {
                this.setDefending(unit);
                unit.unitPoints--;
            }
            else if(selection==4)
            {
                this.displayStats(unit);
            }
            else
            {
                this.cancel(0);
            }
            
        }
    }*/

    
    public void openMoveMenu(Unit unit)
    {
        System.out.println("Which movement method would you like to use:");
        System.out.println("1.Sprint");
        System.out.println("2.Charge");
        System.out.println("3.Regular");
        System.out.println("4.Rest");
int selection =1;
            if(selection==1)
            {
                this.setUnitSprinting(unit);
                
                this.chooseMovementPath(unit);
            }
            else if(selection==2)
            {
                this.setUnitCharging(unit);
                this.chooseMovementPath(unit);
            }
            else if(selection==3)
            {
                this.chooseMovementPath(unit);
            }
            else if(selection==4)
            {
                this.restUnit(unit);
            }
                    
    }

    //Opens the attack menu, which includes the various options for the attack
    //to take. Specifically, selecting a target
    private void openAttackMenu(Unit unit) 
    {
       int selection;
       System.out.println("Select an opponent to attack!");
//  selection= scan.nextInt(); //
       //this needs work, must allow the player to select a unit and attack it.
              
    }

    private void setDefending(Unit unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void displayStats(Unit unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void endTurn()
    {
        for(int i=0; i<allUnits.size(); i++)
        {
            Unit s = (Unit)allUnits.get(i);
                    s.resetUnitPoints();
        }
    }

    private void cancel(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setUnitSprinting(Unit unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setUnitCharging(Unit unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void chooseMovementPath(Unit unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void restUnit(Unit unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public boolean takeTurn() 
    {
       this.unitSelector(this);
       return isWinner;
    }
}

