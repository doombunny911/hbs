package historicalbattlesimulatorbasic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edward
 * This class contains the details of all different unit formations.
 * Setting a unit to be in one of these formation will change the unit layout to be as follows.
 */
public class UnitFormations 
{
    private Unit unit;
    private Tile[][] spriteLocations;
    private int index;
    public UnitFormations(Unit unit, int dir,Tile tile)
    {
        index=0;
        spriteLocations = new Tile[50][50];
        //set default formation
        this.unit = unit;
        defaultFormation(tile,dir);
    }
    public static void setPikeWall(Unit unit)
    {
        
    }
    
    //the default formation for a unit
    public UnitFormations defaultFormation(Tile tile,int dir)
    {
        //start at the unit start tile
        //for every 5 soldiers, draw a sprite
        //depending on the direction, draw to the "right" tile
        //less than hundred, just a stright line
       int numberOfSoldiers = 50;
       BufferedImage img;
       System.out.println(tile);
//       int numberOfSoldiers =this.unit.unitSoldiers.length;
       try 
       { 
          for(int i=0;i<numberOfSoldiers;i=i+5)
          {
            //draw a sprite i to the right of the first tile
              img = ImageIO.read(new File("SoldierSprite.jpg"));
              spriteLocations[this.index][0]= 
              GUI.tileGameMap[tile.yPosition/GUI.tileWidth][tile.xPosition/GUI.tileWidth+this.index];
//              GUI.panel.getGraphics().drawImage(img,tile.xPosition+
//                      i*tile.xLength, tile.yPosition+
//                      i*tile.yLength, tile.xLength,
//                      tile.yLength, null);
          
           this.index++;
          }
       }
       
       
       catch (IOException e) 
       {
          System.out.println("error loading file " + e);
       }
       
       
       return this;
    
    }
    
    public void paintFormation(Graphics g)
    {
        
        BufferedImage img;
        int temp = this.index;
        try
        {
           img = ImageIO.read(new File("SoldierSprite.jpg"));
//           System.out.println("spriteLocations.length " + this.spriteLocations.length );
            for(int i=this.index-1;i>=0;i--)
            {
                System.out.println("spriteLocation at ["+i+"][0] = " +this.spriteLocations[i][0]);
                g.drawImage(img, spriteLocations[i][0].xPosition,
                spriteLocations[i][0].yPosition, spriteLocations[i][0].xLength,
                spriteLocations[i][0].yLength,null);
            }
        }
        catch (IOException e)
        {
            System.out.println("error in paintFormation " + e );
        }
        
        this.index=temp;
    }
}
