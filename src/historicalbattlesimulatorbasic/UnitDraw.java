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
public class UnitDraw extends Rectangle
{
    
    //the position of the top left point of the unit
    int xDraw,yDraw;
    Unit thisUnit;
    Tile locationOfUnit;
    public UnitDraw(Unit getUnit)
    {
        super.setBounds(getUnit.xPosition,getUnit.yPosition,getUnit.yHeight,getUnit.xWidth);
        this.thisUnit=getUnit;
        System.out.println("unit width = " + getUnit.xWidth+ " unit height = " + getUnit.yHeight);
        this.xDraw=getUnit.xPosition;
        this.yDraw=getUnit.yPosition;
        this.locationOfUnit=GUI.tileClicked;
        for(int i=0;i<this.thisUnit.unitSoldiers.length;i++)
        {
            //setting all soldiers to occupy a single tile
            this.thisUnit.unitSoldiers[i].tileOccupied=GUI.tileClicked;
        }
        
        
    }
    //may have no need for this one, will keep for now
    public UnitDraw(int x, int y, int heightY, int widthX)
    {
        super.setBounds(x,y,heightY,widthX);
        this.locationOfUnit=GUI.tileClicked;
        this.xDraw=x;
        this.yDraw=y;
    }
    //just add where they need to be painted and this method will do it
    public void paint(Graphics g)
    { 
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(this);
    }

}
