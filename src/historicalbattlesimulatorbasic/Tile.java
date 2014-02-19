/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * One tile can hold one unit. Each tile has a different effect. Each tile is a square
 * @author Schmalz
 */
public class Tile extends Rectangle
{
  
    boolean tileBlocked;
    int xPosition, yPosition, zPosition;
    int terrainEffect; //terrain effects will be all categorized as ints to allow easy return access.
    int levelOfCover; // 0 is none, 1 is light, 2 is full
    int  xLength, yLength;
    Soldier occupyingSoldier;
    Boolean isOccupied;
    Tile tileNorth, tileNorthEast, tileEast, tileSouthEast, tileSouth, tileSouthWest, tileWest, tileNorthWest;
    //a tile is just a rectangle, therefore, xPosition and yPosition refer
    //to the (x,y) coords for the top left point
    
    public Tile(int xPosition, int yPosition, int xLength, int yLength)
    {
            
        
        super.setBounds(xPosition, yPosition, xLength, yLength);
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        this.xLength= xLength; 
        this.yLength= yLength; 
       
    }
    
    //set the height
    public void setHeight(int Height)
    {
        this.zPosition=Height;
    }
    //set level of cover. 0 is none, 1 is light, 2 is full
    public void setCover(int cover)
    {
        this.levelOfCover = cover;
    }
    
    /*set Terrain level of difficulty for movement.
    level 1(-1 movement) -  A unit is attempting to move across terrain considered to be 'rough'. Light foliage, some rock, slight incline or slight mud.
    level 2(-2 movement) - A unit is attempting to move across terrain considered to be 'rough'. Medium foliage, medium rocks or medium mud. 
    level 3(-3 movement) - A unit is attempting to move across terrain considered to be 'rough'. Harsh 	foliage, harsh rocks, harsh incline or heavy mud
    level 4(-4 movement) - A unit is attempting to move across terrain considered to be 'rough'. Extreme foliage, extreme rocks, extreme incline or extreme mud.	
    */
    public void setTerrainDifficultyLevel(int level)
    {
        terrainEffect=level;
    }
    public int getTerrainDifficultyLevel()
    {
        return terrainEffect;
    }
    //occupy by a soldier
    public void occupyBy(Soldier soldier)
    {
        occupyingSoldier = soldier;
        isOccupied = true;
    }
    //set tileBlocked boolean variable to true, to be used for collision detection. 
    public void setTileBlocked()
    {
        tileBlocked=true;
    }
    //test if someone is occupying it. Will be used to prevent collision
    public boolean getIsOccupied()
    {
        return isOccupied;
    }
    //
    public Soldier getOccupier()
    {
        return occupyingSoldier;
    }
    public double calculateDistanceTo(Tile otherTile)
    {
        double distance = Math.sqrt((otherTile.yPosition-this.yPosition)+((otherTile.xPosition-this.xPosition)));
        return distance;
     }

    //refers to if the tile has a neighbor[anotehr tile in the designated direction
    boolean hasNorth() 
    {
        return yPosition!=0;
    }
    
    boolean hasNorthEast()
    {
        return yPosition!=0 &&  xPosition!=yLength;
    }
    boolean hasWest()
    {
        return xPosition!=0;
    }
    boolean hasSouthEast()
    {
        return hasEast()&&hasSouth();     
    }
    boolean hasSouth()
    {
        return yPosition!=yLength-Openingmenuscreen.tilePanel.getHeight()-Painter.remainderHeight;
    }      
     boolean hasSouthWest()
    {
        return hasSouth()&&hasWest();
    }
    boolean hasEast()
    {
        return xPosition!=xLength-Openingmenuscreen.tilePanel.getWidth()-Painter.remainderWidth;
    } 
    boolean hasNorthWest()
    {
        return hasNorth()&&hasWest();
    }
    
    //returns the designated tile, if they exist.
    Tile tileNorth()
    {
        if(hasNorth())
         return tileNorth;
        else
            return null;
    }
    Tile tileNorthEast()
    {
        if(hasNorthEast())
            return tileNorthEast;
        else
            return null;
    }
    Tile tileEast()
    {
        if(hasEast())
         return tileEast;
        else
            return null;
    }
    Tile tileSouthEast()
    {
        if(hasSouthEast())
            return tileSouthEast;
        else
            return null;
    }
      Tile tileSouth()
    {
        if(hasSouth())
         return tileSouth;
        else
            return null;
    }
      Tile tileSouthWest()
      {
          if(hasSouthWest())
              return tileSouthWest;
          else
              return null;
      }
       Tile tileWest()
    {
        if(hasWest())
         return tileWest;
        else
            return null;
    }
       Tile tileNorthWest()
       {
           if(hasNorthWest())
               return tileNorthWest;
           else
               return null;
       }
     //have to set up the other directions.
    void setDirections(Tile north, Tile east, Tile south, Tile west)
    {
        tileNorth = north;
        tileEast = east;
        tileSouth = south;
        tileWest = west;
       
    }

    public void loadSprite(Graphics g,Tile t) 
    {
       BufferedImage img = null;
//               new BufferedImage(xLength,yLength,1);
       try 
       {
          img = ImageIO.read(new File("SoldierSprite.jpg"));
          g.drawImage(img, t.xPosition, t.yPosition, t.xLength, t.yLength, null);
          
       } 
       catch (IOException e) 
       {
           System.out.println("error loading file " + e);
        }
    }
}
