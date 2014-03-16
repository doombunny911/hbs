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
    private Tile[] spriteLocations;
    private int index;
    public UnitFormations(Unit unit, int dir,Tile tile)
    {
        index=0;
        spriteLocations = new Tile[50];
        //set default formation
        this.unit = unit;
        defaultFormation(tile,dir);
    }
    public static void setPikeWall(Unit unit)
    {
        
    }
    
    //the default formation for a unit
    //need to check to see if tile is occupied
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
           img = ImageIO.read(new File("SoldierSprite.jpg"));
            switch(dir)
           {
               case 0: //north
               {
                   for(int i=0;i<numberOfSoldiers;i=i+5)
                  {
           
                        spriteLocations[this.index]= 
                        GUI.tileGameMap[tile.xPosition/GUI.tileWidth+this.index][tile.yPosition/GUI.tileWidth];
                        this.unit.unitSoldiers[0].tileOccupied=tile;
                        GUI.tileClicked.occupyBy(unit.unitSoldiers[0]);
                        this.index++;
                  }
                    
                    break;
               }
               case 1: //east
               {
                   for(int i=0;i<numberOfSoldiers;i=i+5)
                  {
                    spriteLocations[this.index]= 
                    GUI.tileGameMap[tile.xPosition/GUI.tileWidth][tile.yPosition/GUI.tileWidth+this.index];
                    this.unit.unitSoldiers[0].tileOccupied=tile;
                    GUI.tileClicked.occupyBy(unit.unitSoldiers[0]);
                    this.index++;
                   

                    }
                    break;
               }
               case 2: //south
               {
                   System.out.println("case 2");
                   for(int i=0;i<numberOfSoldiers;i=i+5)
                  {
                    spriteLocations[this.index]= 
                    GUI.tileGameMap[tile.xPosition/GUI.tileWidth-this.index][tile.yPosition/GUI.tileWidth];
                    this.unit.unitSoldiers[0].tileOccupied=tile;
                    GUI.tileClicked.occupyBy(unit.unitSoldiers[0]);  
                    this.index++;
                  }
                   break;
               }
               case 3: //west
               {
                   for(int i=0;i<numberOfSoldiers;i=i+5)
                  {
                    spriteLocations[this.index]= 
                    GUI.tileGameMap[tile.xPosition/GUI.tileWidth][tile.yPosition/GUI.tileWidth-this.index];
                    this.unit.unitSoldiers[0].tileOccupied=tile;
                    GUI.tileClicked.occupyBy(unit.unitSoldiers[0]);
                    this.index++;
                  }
                   break;
               }
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
//                System.out.println("spriteLocation at ["+i+"][0] = " +this.spriteLocations[i][0]);
                g.drawImage(img, spriteLocations[i].xPosition,
                spriteLocations[i].yPosition, spriteLocations[i].xLength,
                spriteLocations[i].yLength,null);
            }
        }
        catch (IOException e)
        {
            System.out.println("error in paintFormation " + e );
        }
        
        this.index=temp;
    }
}
