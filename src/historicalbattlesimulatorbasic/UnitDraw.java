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
public class UnitDraw extends JPanel
{
   ///arbitrary height and width for the units at this point, may need to be
    //fluid at a later date
    int height = 30;
    int width = 50;
    
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        //code to use once the file loading has been completed
        //this should work or if you want to split the units into team 1
        //and team 2, that should be easy to do as well
////////        Unit unit[];
////////        unit=UnitLoader.getAllUnits();
////////        int length=unit.length;
////////        Rectangle rect[]= new Rectangle[length];
////////        for(int i =0;i<length;i++)
////////        {
////////            rect[i]=new Rectangle(unit[i].unitStartLocationX,
////////                    unit[i].unitStartLocationY,height,width);
////////             g2.fill(rect[i]);
////////        }
        
        Rectangle rect[] = new Rectangle[2];
        rect[0]= new Rectangle(30,50,width,height);
        rect[1] = new Rectangle(80,90,width,height);
        
        for(int i=0;i<rect.length;i++)
        {
            g2.fill(rect[i]);
        }
        
    }

}
