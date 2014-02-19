package historicalbattlesimulatorbasic;

import java.awt.Rectangle;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
//class for moving units by tiles

public class UnitMovement extends Rectangle
{
    int tileWidth;
    public static void main(String[] args)
    {
        System.out.println("Select unit to move:");
        
    }
    public UnitMovement()
    {
         tileWidth =GUI.tileWidth;
    }
    /*Move selected unit in the selected direction
   @Unit unitToMove - The unit which you want to move 
   @int dir - The direction to move. 
    1 is north
    2 is northeast
    3 is east
    4 is southeast
    5 is south
    6 is southwest
    7 is west
    8 is northwest
    */
    public void moveUnit(Unit unitToMove, int dir)
    {
        
        unitToMove.moveDirection(dir);
        
         //draw the unit at the top left corner of the tile i Click
    }
    
    //check to see if the unit has enough move counters to 
    //get where they want to Go
    public void checkMovementDistance()
    {
        
    }
    
    
    
}
