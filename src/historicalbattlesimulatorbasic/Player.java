/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.util.ArrayList;

/**
 *
 * @author Schmalz
 */
public class Player{

   UnitPlacer up;
   boolean myTurn;
   
String playerName; //the players actual name
String nameOfArmy; //The name of the army
int unitLimit, unitsRemaining;
boolean isWinner = false;
public ArrayList <Unit> allUnits; //all of a players units
//ArrayList <UnitDraw> playersUnitDraws;
UnitLoader playerUnits = new UnitLoader();
   
public static void main(String[] args)
{
    Player newPlayer = new Player("Edward");
    newPlayer.generatePlayerUnitPlacer();
}
public ArrayList<Unit> getUnitList()
{
    return allUnits;
}
public Player(String playerName)
{
    up=new UnitPlacer(playerName);  
    allUnits=up.unitArrayList;
    this.playerName = playerName;
}
public Player(String playerName,ArrayList <Unit> u)
{
    up=new UnitPlacer(playerName,u);
    allUnits=u;
    this.playerName = playerName;
}
public void generatePlayerUnitPlacer()
{
    
    this.up = new UnitPlacer(playerName);
    up.setPlayer(playerName);
    up.setUpButtons();
  
}
public UnitPlacer getPlayerUnitPlacer()
{
    return this.up;
}
public static void setWinner(Player p)
{
    p.isWinner = true;
}

public void takeTurn()
{
    //set value of all player units clickable to true.
    for(Unit u: allUnits)
    {
        u.setClickable();
    }
    
    //allow player to select a unit. So long as the unit is clickable, it should respond. Once a unit has made a move, or done something
    //and expended 2 unit points, set it unclickable
    
}    
   
    

public boolean anyClickable()
{
boolean anyClickable = true;
    for(Unit u: allUnits)
    {
        if(!u.isClickable())
        {
            anyClickable = false;
        }
    }
    return anyClickable;
}

}

