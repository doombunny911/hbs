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
//this class initalizes and draws the tiles for the game

public class TileMapGenerator extends JPanel
{
    
    Tile tiles[][];
    private int tileWidth;
    public TileMapGenerator(int widthf)
    {
//        super.getSize(new Dimension(Openingmenuscreen.tilePanel.getHeight(),Openingmenuscreen.tilePanel.getWidth()));
        this.tileWidth=widthf;
        tiles=Map.gameMap;
    }


    /**
     *
     * @param g
     */
   @Override
   public void paintComponent(Graphics g)
   {
        //this has clearing power, don't fully understand
//      super.paintComponent(g);
      System.out.println("Test");
      Graphics2D g2=(Graphics2D)g;
       
      int width = Openingmenuscreen.tilePanel.getWidth();
       
      int height = Openingmenuscreen.tilePanel.getHeight();
      double squareWidth = Math.floor(width/tileWidth);
      double squareHeight = Math.floor(height/tileWidth);
      tiles=new Tile[(int)squareHeight][(int)squareWidth-1];

      System.out.println("squarewidth ="+ squareWidth + 
               " tileWidth = " + tileWidth+ " height = " + height +
               " width = " + width );
      for(int i=0;i<squareHeight;i++)
      {
         for(int j=0;j<squareWidth-1;j++)
         {
            tiles[i][j]= new Tile(j*tileWidth,i*tileWidth,tileWidth,tileWidth);
            g2.draw(tiles[i][j]);
         }
    
       }
      

    }
}
