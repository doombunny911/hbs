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
    private Tile[][] locations;
    private int index;
    BufferedImage img;
    public UnitFormations(Unit unit,Tile tile)
    {
        index=0;
//        locations = new Tile[5000][5000];
        //set default formation
        this.unit = unit;
        boxFormation(tile);
//        defaultFormation(tile);
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
      
      
      spriteLocations = new Tile[numberOfSoldiers/soldiersPerSprite];

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
    
    public UnitFormations boxFormation(Tile tile)
    {
        int soldiersPerSprite =  1;//each sprite represents this many soldiers
        int depth=0;
        int numberOfSoldiers= unit.unitSoldiers.length;
        int effectiveSoldiers=numberOfSoldiers/soldiersPerSprite;
        System.out.println("numberOfSoldiers = " + numberOfSoldiers );
       if(effectiveSoldiers<4)
           this.defaultFormation(tile);
       else if(effectiveSoldiers>4&&effectiveSoldiers<35)
           depth=1;
       else
           depth=2;
         System.out.println("in box formation");
      
//       System.out.println("unit is facing " + unit.unitFacing); //don't understand why this gives different value than next line
       
       System.out.println("unit is facing " + unit.unitSoldiers[0].facing);
       
      int num=0;
      int extra = 0;
      int indexI = 0;
    
      for(int i =25;i>0;i--)
      {
          num=0;
         for(int j=0;j<depth;j++)
         {
             //12*4-4 + 36 = 
              num+=(i-j*2)*4-4;
         } 
                
         if(num<numberOfSoldiers/soldiersPerSprite)
         {
             locations = new Tile[i][i];
             System.out.println("the i that made the magic = " + i);
             indexI = i;
             extra = numberOfSoldiers/soldiersPerSprite-num;
             System.out.println("extra = " + extra);
             break;
         }
             
      }
  
       int count =0;
      switch(unit.unitSoldiers[0].facing)
      { 
          case 1: //north
          {
             for(int i=0;i<indexI;i++)
             {
                 for(int j=0;j<indexI;j++)
                 {
                     
//                     System.out.println("the bad number is now at " + (indexI*i+j));
                     if(i<depth||i>=indexI-depth)
                     {
//                         System.out.println("on the top of the box or the bottom of the box, draw the sprites");
                         locations[j][i] = GUI.tileGameMap[tile.xPosition/GUI.tileWidth+j][tile.yPosition/GUI.tileWidth+i];
                         this.unit.unitSoldiers[count].tileOccupied=locations[j][i];
                         locations[j][i].occupyBy(unit.unitSoldiers[count]); 
                         count++;
                         
                     }
                     else if(j>=depth&&j<indexI-depth&&extra>0)
                     {
                         locations[j][i] = GUI.tileGameMap[tile.xPosition/GUI.tileWidth+j][tile.yPosition/GUI.tileWidth+i];
                         this.unit.unitSoldiers[count].tileOccupied=locations[j][i];
                         locations[j][i].occupyBy(unit.unitSoldiers[count]); 
                         count++;
                         extra--;
                     }
                     else if(j<depth||j>=indexI-depth)
                     {
//                         System.out.println("on the left side of the box, draw the unit");
                         locations[j][i]=GUI.tileGameMap[tile.xPosition/GUI.tileWidth+j][tile.yPosition/GUI.tileWidth+i];
                         this.unit.unitSoldiers[count].tileOccupied=locations[j][i];
                         locations[j][i].occupyBy(unit.unitSoldiers[count]); 
                          count++;
                     }
                 }
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
//              this.index++;
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
//                 this.index++;
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
//           this.index++;
         }
            break;
       }
     }    
       
       
        return this;
    }
    
    public void paintFormation(Graphics g)
    {
       if(spriteLocations!=null)
       {
           int temp = this.index;
//        System.out.println("index = " + index);
//        int j=0;
        //need to loop over the unitDraws to find which unitDraw you are looking for to paint the right ones
//        System.out.println("the size of unitDraws is "  +GUI.unitDraws.size());
        for(int i=0;i<GUI.unitDraws.size();i++)
        {
          
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
       else
       {
           
            Unit u =  GUI.unitDraws.get(0).getThisUnit();
//           System.out.println("in paintFormation");
           BufferedImage  unImg = Unit.getUnitPic(u);
           
           
           for(int i=0;i<locations[0].length;i++)
            {
            for(int j=0;j<locations.length;j++)
            {
                if(locations[j][i]!=null&&locations[j][i].isOccupied)
                {
                      g.drawImage(unImg, locations[j][i].xPosition,
                              locations[j][i].yPosition, locations[j][i]
                              .xLength,locations[j][i].yHeight, null);

                }
            }
        }
       }
        
        
            
        
    }
}
