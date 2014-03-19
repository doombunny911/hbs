/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Andrew
 */
//this class initalizes and draws the tiles for the game

public class Painter extends JPanel
{
    private int tileWidth;
    static double remainderHeight;
    static double remainderWidth;
    private double sHeight,sWidth;
    int x,y;
    Unit unit;
    public Painter(int widthf)
    {
        this.tileWidth=widthf;
//        tiles=Map.gameMap;
    }
    public Painter(double sWidth,double sHeight)
    {
        
        this.sWidth=sWidth;
        this.sHeight=sHeight;
    }
    
    //don't think this contructor is used
    public Painter(int locationX,int locationY)
    {
        x =locationX;
        y=locationY;
    }
    //untested
    public Painter(Unit unit)
    {
        this.unit=unit;
    }
    /**
     *
     * @param g
     */
   @Override
   public void paintComponent(Graphics g)
   {
       
      Graphics2D g2=(Graphics2D)g;
       
      //tileMap
      for(int i=0;i<sHeight;i++)
      {
         for(int j=0;j<sWidth;j++)
         {
            if(j%10==0)
            {
                g2.fill(GUI.tileGameMap[j][i]);
            }
            if(i%10==0)
            {
                g2.fill(GUI.tileGameMap[j][i]);
            }
            else
            {
                 g2.draw(GUI.tileGameMap[j][i]);
            }
         }
       }
      
      //units and sprites
      if(!GUI.units.isEmpty())
      {
         for(int i=0;i<GUI.units.size();i++)
         {
             GUI.units.get(i).paint(g);
             GUI.units.get(i).thisUnit.currentFormation.paintFormation(g);
         }
      }
      //not currently used
      if(GUI.unitSelected!=null&&GUI.tileClicked!=null&&!GUI.impendingAttack)
      {
          GUI.paintRange(GUI.unitSelected,g);
      }
      //sprites 
//      if(unit!=null)
//      {
//         GUI.tileGameMap[50][50].loadSprite(g,GUI.tileGameMap[50][50],unit);
//      }
//      GUI.gameFrame.validate();
//      GUI.gameFrame.repaint();
    }
}
