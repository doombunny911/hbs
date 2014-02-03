/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author Andrew
 */
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
//        super.paintComponent(g);
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
//               
//           
            
            
        
    }
       
        
       
   }
      

    }
  
    

    
    
    //checks to see if last tile, pretty sure I don't need these two methods anymore
    private boolean ifLastTile(int i, double squareHeight, int j, double squareWidth) 
    {
        return (i+1)>squareHeight&&(j+1)>squareWidth;
    }

    //this should cut the extra length on the double array
    private void initializeNewTileArray(int i, int j, double squareHeight, double squareWidth, int tileWidth) {
        Rectangle rectAwesome[][] = new Rectangle[i][j];
        Tile tileFinal[][] = new Tile[i][j];
        for(int k =0;k<squareHeight;k++)
        {
            for(int l =0;l<squareWidth;l++)
            {
                tiles[k][l]= new Tile(l*tileWidth,k*tileWidth,tileWidth,tileWidth);
                //return this as overall list of tiles
                
            }
        }
    }
}
