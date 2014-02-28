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

    String playerName; //the players actual name
    String nameOfArmy; //The name of the army
    int unitLimit, unitsRemaning;
    ArrayList allUnits; //all of a players units
    
    ArrayList player1Units, player2Units;
    public static ArrayList<Player> playersForDemo = new ArrayList();
public static void main(String[] args)
{
 playersForDemo = playerCreator();
  
        
     }
private static boolean createMore(Frame frame) throws HeadlessException {
        Object[] options = {"Yes",
            "No."};
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Would you like to add another player?"," ",
                JOptionPane.YES_NO_OPTION);
       
        if(n==0)
        {
            return true;
        }
        else if(n==1)
        {
            return false;
        }
          return false;
    }
    private static ArrayList playerCreator()
    {
       boolean first = true;
       Frame frame = JOptionPane.getRootFrame();
          
       ArrayList<Player> players = new ArrayList();     
       Player playHolder = new Player(); 
      
       boolean createMorePlayers = true;
       while(createMorePlayers)
        {           
            singlePlayerCreator(playHolder, players); 
            createMorePlayers = createMore(frame);
        }
        return players;
    }

    private static void singlePlayerCreator(Player playHolder, ArrayList<Player> players) throws HeadlessException, NumberFormatException {
        playHolder.playerName = JOptionPane.showInputDialog ( "Enter player name: " );
        playHolder.nameOfArmy = JOptionPane.showInputDialog ( "Enter army name:\n 'Mars Fury', 'The 13th Legion of Rome', 'Ryan's Raiders' " );
        playHolder.unitLimit = Integer.parseInt(JOptionPane.showInputDialog ( "Set the max number of Units to be deployed in this army: " ));
        JOptionPane.showMessageDialog(null,"Select a unit list to choose your army from");
        UnitLoader playerUnits = new UnitLoader();
        playerUnits.runLoader();
        ArrayList<Unit> allUnitsLocal = playerUnits.unitPrepper();
        playHolder.allUnits = allUnitsLocal;
        players.add(playHolder);
    }
    Scanner scan = new Scanner(System.in);
   

    public Player()
    {
       
    
    }
    //sets the players name
    public void setPlayerName(String playerName)
    {
        this.playerName=playerName;
    }
    //adds unit to the army
    public void addUnitToArmy(Unit unit)
    {
        allUnits.add(unit);
    }
    public int getUnitCount()
    {
        return allUnits.size();
    }
    //Each player gets to do 2 things with each unit
    public void selectUnit(Unit unit)
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
    }
    
    public void openMoveMenu(Unit unit)
    {
        System.out.println("Which movement method would you like to use:");
        System.out.println("1.Sprint");
        System.out.println("2.Charge");
        System.out.println("3.Regular");
        System.out.println("4.Rest");
        int selection = scan.nextInt();
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
       selection= scan.nextInt(); //
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

    void takeTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

