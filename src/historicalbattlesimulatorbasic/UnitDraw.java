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
    int heightY = 30;
    int widthX = 50;
    int xDraw,yDraw;
    public UnitDraw(int x, int y)
    {
        super.setBounds(x,y,height,width);
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
