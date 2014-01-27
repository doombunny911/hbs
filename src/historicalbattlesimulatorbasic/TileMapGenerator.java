/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author Andrew
 */
public class TileMapGenerator extends JButton
{
    
    Tile tiles[][];
   private int tileWidth;
    public TileMapGenerator(int widthf)
    {
        this.tileWidth=widthf;
        tiles=Map.gameMap;
        

//       Rectangle rect[][] = Map.gameMap;
//       Rectangle rect[][]=Map.gameMap;
       int width = Openingmenuscreen.gameFrame.getWidth();
//       System.out.println(width);
       
       int height = Openingmenuscreen.tilePanel.getHeight();
       double squareWidth = Math.floor(width/tileWidth);
       double squareHeight = Math.floor(height/tileWidth);
       Tile[][] tiles=new Tile[(int)squareHeight][(int)squareWidth-1];
       System.out.println("squarewidth ="+ squareWidth + 
               " tileWidth = " + tileWidth+ " height = " + height +
               " width = " + width );
////        tileWidth=10;
        //= new Rectangle[5000][5000];
        SpringLayout sLayout = new SpringLayout();
        Openingmenuscreen.tilePanel.setLayout(sLayout);
        for(int i=0;i<squareHeight;i++)
        {
            for(int j=0;j<squareWidth-1;j++)
            {
//                tileWidth=10;
//                System.out.println(tileWidth);
                tiles[i][j]= new Tile(j*tileWidth,i*tileWidth,tileWidth,tileWidth);
//                tiles[i][j]= new Tile(j*10,i*10,10,10);
//               tiles[i][j]=new Tile();
               tiles[i][j].setPreferredSize(new Dimension(tileWidth,tileWidth));
               tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.black));

                
                sLayout.putConstraint(SpringLayout.WEST,tiles[i][j], j*10, SpringLayout.WEST, Openingmenuscreen.tilePanel);
                sLayout.putConstraint(SpringLayout.NORTH,tiles[i][j],i*10,SpringLayout.NORTH,Openingmenuscreen.tilePanel);
                Openingmenuscreen.tilePanel.add(tiles[i][j]);

//                  rect[i][j]= new Rectangle(j*tileWidth,i*tileWidth,tileWidth,tileWidth);
//                  g2.draw(rect[i][j]);
//                  System.out.println(tiles[i][j]);
//                if(ifLastTile(i, squareHeight, j, squareWidth))
//                {
//                    initializeNewTileArray(i, j, squareHeight, squareWidth, tileWidth);
//                }
            }
            
        }
        
       
        
        
     
    }
    // determine the size the tiles should be
    //NOT IMPLEMENTED
    public void tileSizeDeterminer()
    {
            
    }
    
    
    //checks to see if last tile
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
