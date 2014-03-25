package historicalbattlesimulatorbasic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edward
 * edited: Andrew
 * This class contains the details of all different unit formations.
 * Setting a unit to be in one of these formation will change the unit layout to be as follows.
 */
public final class UnitFormations 
{
    private Unit unit;
    private Tile[] spriteLocations;
    private int index;
    BufferedImage img;
    public UnitFormations(Unit unit,Tile tile)
    {
        index=0;
        spriteLocations = new Tile[5000];
        //set default formation
        this.unit = unit;
        defaultFormation(tile);
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
                    //not completed
                }
            }
        }
        
        
        
        return this;
    }
    //the default formation for a unit
    //need to check to see if tile is occupied
    public UnitFormations defaultFormation(Tile tile)
    {
        //start at the unit start tile
       int numberOfSoldiers= unit.unitSoldiers.length;
       System.out.println("numberOfSoldiers = " + numberOfSoldiers );
       
      System.out.println("In default formation");
      
     //this class has a lot of work to allow it to be used for more complex formations 
       System.out.println("unit is facing " + unit.unitFacing); //don't understand why this gives different value than next line
       
       //
       System.out.println("unit is facing " + unit.unitSoldiers[0].facing);
      int soldiersPerSprite =  1; //each sprite represents this many soldiers
            switch(unit.unitSoldiers[0].facing)
           { 
               case 1: //north
               {
                   for(int i=0;i<numberOfSoldiers;i=i+soldiersPerSprite)
                  {
                       //store the location of the sprites for later painting
                    //this works by getting base tile of unit start location and 
                      //adjusting index amount of tiles over
                      //so first will be at start tile and each subsuquent 
                      //soldier will be +1 tile to the "right"
                        spriteLocations[this.index]= 
                        GUI.tileGameMap[tile.xPosition/GUI.tileWidth+this.index]
                                 [tile.yPosition/GUI.tileWidth]; 
                        
                        
                        //put the soldier onto the tile
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
//        System.out.println("index = " + index);
        int j=0;
        //need to loop over the unitDraws to find which unitDraw you are looking for to paint the right ones
//        System.out.println("the size of unitDraws is "  +GUI.unitDraws.size());
        for(int i=0;i<GUI.unitDraws.size();i++)
        {
           j=i;
        }
        Unit u =  GUI.unitDraws.get(0).getThisUnit();
//        System.out.println("in paintFormation");
        BufferedImage  unImg = Unit.getUnitPic(u);
        
        for(int i=this.index-1;i>=0;i--)
        {
          
            g.drawImage(unImg, spriteLocations[i].xPosition,
                    spriteLocations[i].yPosition, spriteLocations[i].xLength,
                    spriteLocations[i].yHeight,null);
        }
        
        this.index=temp;
    }
}
