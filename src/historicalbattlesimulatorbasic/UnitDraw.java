/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


/**
 *
 * @author Andrew
 */

//Units at some level are rectangles holding soldiers.  Therefore, I will draw
public class UnitDraw
{
    
    //the position of the top left point of the unit
    int xDraw,yDraw; //x and y position
    Unit thisUnit; //the unit being drawn
    Tile tileLocationOfUnit; //tile location of where the unit is at
    
    //this constructor should be used when first loading in the units
    public UnitDraw(Unit getUnit)
    {
        this.xDraw=getUnit.xPosition;
        this.yDraw=getUnit.yPosition;
        getUnit.currentFormation = new UnitFormations
                (getUnit,GUI.tileClicked);
        this.tileLocationOfUnit=GUI.tileClicked;
        this.thisUnit=getUnit;
    }
    /*
    Returns the specific unit
    */
    public Unit getThisUnit()
    {
        return thisUnit;
    }

    
    //this constructor should be used for future updates to the unit location
    public UnitDraw(Unit getUnit,Tile tile)
    {
        this.xDraw=getUnit.xPosition;
        this.yDraw=getUnit.yPosition;
        getUnit.currentFormation = new UnitFormations
                (getUnit,tile);
        this.tileLocationOfUnit=tile;
        this.thisUnit=getUnit;
    }
    //just add where they need to be painted and this method will do it


}
