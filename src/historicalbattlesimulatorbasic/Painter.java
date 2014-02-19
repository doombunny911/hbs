/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

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
    Tile tiles[][];
    private int tileWidth;
    static double remainderHeight;
    static double remainderWidth;
    int x,y;
    Unit unit;
    public Painter(int widthf)
    {
        this.tileWidth=widthf;
//        tiles=Map.gameMap;
    }
    
    public Painter(int locationX,int locationY)
    {
        x =locationX;
        y=locationY;
    }
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
       
      int width = Openingmenuscreen.tilePanel.getWidth();
      int height = Openingmenuscreen.tilePanel.getHeight();
//      System.out.println(remainderWidth = Math.floor(width%tileWidth)) ;
//      System.out.println(remainderHeight = Math.floor(height%tileWidth));
      double squareWidth = Math.floor(width/tileWidth);
      double squareHeight = Math.floor(height/tileWidth);
      tiles=new Tile[(int)squareHeight][(int)squareWidth];
//
      System.out.println("squarewidth ="+ squareWidth + 
               " tileWidth = " + tileWidth+ " squareHeight = " + squareHeight +
               " width = " + width );
      int a=0;
      for(int i=0;i<squareHeight;i++)
      {
         for(int j=0;j<squareWidth;j++)
         {
            tiles[i][j]= new Tile(j*tileWidth,i*tileWidth,tileWidth,tileWidth);
            g2.draw(tiles[i][j]);
         }
       }
      GUI.tileGameMap=tiles;
      if(GUI.units.isEmpty()!=true)
      {
         for(int i=0;i<GUI.units.size();i++)
         {
             GUI.units.get(i).paint(g);
         }
      }
      
      tiles[50][50].loadSprite(g,tiles[50][50],u);
//      GUI.gameFrame.validate();
//      GUI.gameFrame.repaint();
    }
}
