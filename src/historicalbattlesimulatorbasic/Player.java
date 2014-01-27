/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.util.Scanner;

/**
 *
 * @author Schmalz
 */

public class Player
{
    Scanner scan = new Scanner(System.in);
    
    int numOfUnits;//the number of units under a player control.
    Unit units[];//units under control
    String playername;
    public Player(int numOfUnits)
    {
        this.numOfUnits=numOfUnits;
    Unit tunits[] = new Unit[numOfUnits];
    
    }
    //sets the players name
    public void setPlayerName(String playerName)
    {
        this.playername=playerName;
    }
    //adds unit at designated index point.
    public void addUnitToArmy(Unit unit, int i)
    {
        units[i]=unit;
    }
    public int getUnitCount()
    {
        return numOfUnits;
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
        for(int i=0; i<numOfUnits; i++)
        {
            units[i].resetMovementPoints();
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

