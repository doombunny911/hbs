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
    static int numberArray=0;
    public UnitDraw(int x, int y)
    {
        super.setBounds(x,y,height,width);
        this.xDraw=x;
        this.yDraw=y;
        numberArray++;
    }
    //just add where they need to be painted and this method will do it
    public void paint(Graphics g)
<<<<<<< HEAD
    { 
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(this);
=======
    {
//   Graphics2D g2 = (Graphics2D) g;
//        Rectangle rect[] = new Rectangle[2];
//        
//        for (Rectangle rect1 : rect) 
//            g2.fill(rect1);
//        
>>>>>>> 054f44db2d71d5b4f42d28013470d9d0e598ae54
        
    }

}
