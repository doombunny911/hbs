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
    //arbritary size of units atm
   
    int xDraw,yDraw;
    public UnitDraw(Unit getUnit)
    {
        super.setBounds(getUnit.xPosition,getUnit.yPosition,getUnit.yHeight,getUnit.xWidth);
        System.out.println("unit width = " + getUnit.xWidth+ " unit height = " + getUnit.yHeight);
        this.xDraw=x;
        this.yDraw=y;
    }
    public UnitDraw(int x, int y, int heightY, int widthX)
    {
        super.setBounds(x,y,heightY,widthX);
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
