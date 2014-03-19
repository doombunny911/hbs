package historicalbattlesimulatorbasic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

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
public final class UnitFormations 
{
    private Unit unit;
    private Tile[] spriteLocations;
    private int index;
    BufferedImage img;
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
    
    public static void setSquare(Unit unit)
    {
        
    }
    public UnitFormations locateLocationOfSprites(Unit unit)
    {
        //locate all the location of soldiers
        
        for(int i=0;i<GUI.numberOfTilesHeight;i++)
        {
            for(int j=0;j<GUI.numberOfTilesWidth;j++)
            {
                if(unit.unitID==GUI.tileGameMap[j][i].getOccupier().getUnitID())
                {
                    
                }
            }
        }
        
        
        
        return this;
    }
    //the default formation for a unit
    //need to check to see if tile is occupied
    public UnitFormations defaultFormation(Tile tile,int dir)
    {
        //start at the unit start tile
        //depending on the direction, draw to the "right" tile
        //less than hundred, just a stright line
       int numberOfSoldiers= unit.unitSoldiers.length;
      
     //TO DO: 
     //      add a new row if number of soldiers> X
     //      add paint in diagonals
       
       
       
      int soldiersPerSprite =  1; //each sprite represents this many soldiers
            switch(dir)
           {
               case 1: //north
               {
                   for(int i=0;i<numberOfSoldiers;i=i+soldiersPerSprite)
                  {
                       //store the location of the sprites for later painting
                        spriteLocations[this.index]= 
                        GUI.tileGameMap[tile.xPosition/GUI.tileWidth+this.index]
                                 [tile.yPosition/GUI.tileWidth];
                        
                        //put the soldier into the tile
                        this.unit.unitSoldiers[i].tileOccupied=spriteLocations[this.index];
                        spriteLocations[this.index].occupyBy(unit.unitSoldiers[i]); 
                        this.index++;
                  }
                    
                    break;
               }
               case 2: //east
               {
                   for(int i=0;i<numberOfSoldiers;i=i+soldiersPerSprite)
                  {
                    spriteLocations[this.index]= 
                    GUI.tileGameMap[tile.xPosition/GUI.tileWidth][tile.yPosition/GUI.tileWidth+this.index];
                    this.unit.unitSoldiers[i].tileOccupied=spriteLocations[this.index];
                    spriteLocations[this.index].occupyBy(unit.unitSoldiers[i]);
                    this.index++;
                    }
                    break;
               }
               case 3: //south
               {
                   System.out.println("case 2");
                   for(int i=0;i<numberOfSoldiers;i=i+soldiersPerSprite)
                  {
                    spriteLocations[this.index]= 
                    GUI.tileGameMap[tile.xPosition/GUI.tileWidth-this.index][tile.yPosition/GUI.tileWidth];
                    this.unit.unitSoldiers[i].tileOccupied=spriteLocations[this.index];
                    spriteLocations[this.index].occupyBy(unit.unitSoldiers[i]);
                    this.index++;
                  }
                   break;
               }
               case 4: //west
               {
                   for(int i=0;i<numberOfSoldiers;i=i+soldiersPerSprite)
                  {
                    spriteLocations[this.index]= 
                    GUI.tileGameMap[tile.xPosition/GUI.tileWidth][tile.yPosition/GUI.tileWidth-this.index];
                    this.unit.unitSoldiers[i].tileOccupied=spriteLocations[this.index];
                    spriteLocations[this.index].occupyBy(unit.unitSoldiers[i]);
                    this.index++;
                  }
                   break;
               }
           }
       return this;
    
    }
    
    public void paintFormation(Graphics g)
    {
        
      
        int temp = this.index;
        Unit u =  GUI.unitDraws.get(0).getThisUnit();
        BufferedImage  img = Unit.getUnitPic(u);
        
        for(int i=this.index-1;i>=0;i--)
        {
            g.drawImage(img, spriteLocations[i].xPosition,
                    spriteLocations[i].yPosition, spriteLocations[i].xLength,
                    spriteLocations[i].yLength,null);
        }
        
        this.index=temp;
    }
}
