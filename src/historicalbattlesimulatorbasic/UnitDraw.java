/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author Andrew
 */

//Units at some level are rectangles holding soldiers.  Therefore, I will draw
public class UnitDraw extends Rectangle
{
    //arbritary size of units atm
    int height = 30;
    int width = 50;

    //just add where they need to be painted and this method will do it
    public void paint(Graphics g)
    {
//        Graphics2D g2 = (Graphics2D) g;
//        Rectangle rect[] = new Rectangle[2];
//        
//        for (Rectangle rect1 : rect) 
//            g2.fill(rect1);
//        
        
    }

}
