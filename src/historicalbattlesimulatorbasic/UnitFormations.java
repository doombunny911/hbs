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
    public  int whichFormation;
    private Tile thisTile;
    int value;
    private int soldiersPerSprite =1;
    public static final int WEDGE = 2;
    public static final int SQUARE = 1;
    public static final int DEFAULT = 0;
    
    
    
    public UnitFormations(Unit unit,Tile tile){
        index=0;
//        locations = new Tile[5000][5000];
        //set default formation
        this.unit = unit;
        this.thisTile = tile;
//        setBoxFormation(tile);
//        defaultFormation(tile);
    }
    public void updateTileAndUnit(Unit unit, Tile tile){
        thisTile = tile;
        this.unit = unit;
    }
    public static void setPikeWall(Unit unit){
        
    }
    public UnitFormations locateLocationOfSprites(Unit unit) {
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
    public UnitFormations defaultFormation(){
        
        System.out.println(this.unit.getSpriteName());
        
        whichFormation =DEFAULT;
        //start at the unit start tile
       int numberOfSoldiers= unit.unitSize;
       System.out.println("numberOfSoldiers = " + numberOfSoldiers );

      System.out.println("In default formation");
      
      int count=0;
       System.out.println("unit is facing " + unit.unitFacing);
       soldiersPerSprite =  1; //each sprite represents this many soldiers
      int effectiveSoldiers = numberOfSoldiers/soldiersPerSprite;
      int halfway  = effectiveSoldiers/2;
      spriteLocations = new Tile[effectiveSoldiers];
      this.index=0;
            switch(unit.unitFacing)
           { 
               case 1: //north
               {
                   for(int i=0;i<effectiveSoldiers;i=i+soldiersPerSprite)
                  {
                       
                      if(thisTile.xPosition/GUI.tileWidth+this.index==GUI.numberOfTilesWidth)
                      {
                          //make the button visible and undraw the sprites already drawn, currently not being done
                          System.out.println("I am out of bounds");
                      }
                      else if(i<halfway)
                      {
                           //soldier will be +1 tile to the "right"
                            spriteLocations[this.index]= 
                            GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+this.index]
                                     [thisTile.yPosition/GUI.tileWidth]; 
                            //put the soldier onto the tile
                            this.unit.unitSoldiers.get(i).tileOccupied=spriteLocations[this.index];
                            spriteLocations[this.index].occupyBy(unit.unitSoldiers.get(i)); 
                            this.index++;
                      }
                      else
                      {
                          spriteLocations[this.index]= 
                          GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+count]
                                     [thisTile.yPosition/GUI.tileWidth+1]; 
                          //put the soldier onto the tile
                          this.unit.unitSoldiers.get(this.index).tileOccupied=spriteLocations[this.index];
                          spriteLocations[this.index].occupyBy(unit.unitSoldiers.get(this.index)); 
                          this.index++;
                          count++;
                      }
                  }
                    break;
               }
               case 2: //east
               {
                   
                       for(int i=0;i<numberOfSoldiers;i=i+soldiersPerSprite)
                        {
                          if(thisTile.xPosition/GUI.tileWidth+this.index==GUI.numberOfTilesWidth)
                            {
                          //make the button revisible and undraw the sprites already drawn, currently not being done
                          System.out.println("I am out of bounds");
                            }
                          else if(i<halfway)
                        {
                            spriteLocations[this.index]= 
                            GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth][thisTile.yPosition/GUI.tileWidth+this.index];
                            this.unit.unitSoldiers.get(i).tileOccupied=spriteLocations[this.index];
                            spriteLocations[this.index].occupyBy(unit.unitSoldiers.get(i));
                            this.index++;
                        }
                       else
                       {
                            spriteLocations[this.index]= 
                            GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth-1][thisTile.yPosition/GUI.tileWidth+count];
                            this.unit.unitSoldiers.get(i).tileOccupied=spriteLocations[this.index];
                            spriteLocations[this.index].occupyBy(unit.unitSoldiers.get(i));
                            this.index++;
                            count++;
                       }
                   }
                   
                    break;
               }
               case 3: //south
               {
                    for(int i=0;i<numberOfSoldiers;i=i+soldiersPerSprite)
                    {
                        if(thisTile.xPosition/GUI.tileWidth+this.index==GUI.numberOfTilesWidth)
                        {
                          //make the button revisible and undraw the sprites already drawn, currently not being done
                              System.out.println("I am out of bounds");
                        }
                        else if(i<halfway)
                      {
                           //soldier will be +1 tile to the "right"
                            spriteLocations[this.index]= 
                            GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+this.index]
                                     [thisTile.yPosition/GUI.tileWidth]; 
                            //put the soldier onto the tile
                            this.unit.unitSoldiers.get(i).tileOccupied=spriteLocations[this.index];
                            spriteLocations[this.index].occupyBy(unit.unitSoldiers.get(i)); 
                            this.index++;
                      }
                      else
                      {
                          spriteLocations[this.index]= 
                          GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+count]
                                     [thisTile.yPosition/GUI.tileWidth+1]; 
                          //put the soldier onto the tile
                          this.unit.unitSoldiers.get(this.index).tileOccupied=spriteLocations[this.index];
                          spriteLocations[this.index].occupyBy(unit.unitSoldiers.get(this.index)); 
                          this.index++;
                          count++;
                      }
                  }         
                   break;
               }
               case 4: //west
               {
                     for(int i=0;i<numberOfSoldiers;i=i+soldiersPerSprite)
                     {
                       if(thisTile.xPosition/GUI.tileWidth+this.index==GUI.numberOfTilesWidth)
                         {
                          //make the button revisible and undraw the sprites already drawn, currently not being done
                          System.out.println("I am out of bounds");
                         }
                        else if(i<halfway)
                        {
                            spriteLocations[this.index]= 
                            GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth][thisTile.yPosition/GUI.tileWidth+this.index];
                            this.unit.unitSoldiers.get(i).tileOccupied=spriteLocations[this.index];
                            spriteLocations[this.index].occupyBy(unit.unitSoldiers.get(i));
                            this.index++;
                        }
                       else
                       {
                            spriteLocations[this.index]= 
                            GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth-1][thisTile.yPosition/GUI.tileWidth+count];
                            this.unit.unitSoldiers.get(i).tileOccupied=spriteLocations[this.index];
                            spriteLocations[this.index].occupyBy(unit.unitSoldiers.get(i));
                            this.index++;
                            count++;
                       }
                   }
                   break;
               }
           }
       value=0;
       GUI.repainter();
       return this;
    
    }
    public UnitFormations setBoxFormation(){
        whichFormation=1;
        soldiersPerSprite =  1;//each sprite represents this many soldiers
        int depth=0;
        int numberOfSoldiers= unit.unitSoldiers.size();
        int effectiveSoldiers=numberOfSoldiers/soldiersPerSprite;
//        System.out.println("numberOfSoldiers = " + numberOfSoldiers );
       if(effectiveSoldiers<12)
       {
           return this.defaultFormation();
       }
           
       else if(effectiveSoldiers>=12&&effectiveSoldiers<35)
           depth=1;
       else
           depth=2;
         System.out.println("in box formation");
      
//       System.out.println("unit is facing " + unit.unitFacing); //don't understand why this gives different value than next line
       
       System.out.println("unit is facing " + unit.unitFacing);
       
      int num=0;
      int boxLength = 0;
      int jIndex=-1;
      while(num<effectiveSoldiers)
      {   
          jIndex++;
          boxLength = 2+jIndex*2;
          num+=boxLength*4-4;
      }  
        System.out.println("boxLength = " + boxLength);
    
        locations=new Tile[boxLength][boxLength];
//      System.out.println("unit is facing " + unit.unitSoldiers[0].facing);
      
       int count =0;
      switch(unit.unitFacing)
      { 
          case 1: //north
          {
             for(int i=0;i<boxLength;i++)
             {
                 for(int j=0;j<boxLength;j++)
                 {
//                     if(thisTile.xPosition/GUI.tileWidth+j+thisTile.yPosition/GUI.tileWidth+i==GUI.numberOfTilesWidth||thisTile.xPosition/GUI.tileWidth+j+thisTile.yPosition/GUI.tileWidth+i==GUI.numberOfTilesHeight)
//                     {
//                         ask user to try again
//                         System.out.println("don't draw out of bounds ");
//                     }
//                     if(count==effectiveSoldiers)
//                     {
//                         
//                     }
                     if(i==0||j==0||i==boxLength-1||j==boxLength-1)
//                      if(i<depth||i>=jIndex-depth||j<depth||j>=jIndex-depth)
                     {
//                         System.out.println("on the top of the box or the bottom of the box, draw the sprites");
                         locations[j][i] = GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+j][thisTile.yPosition/GUI.tileWidth+i];
                         this.unit.unitSoldiers.get(count).tileOccupied=GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+j][thisTile.yPosition/GUI.tileWidth+i];
                         locations[j][i].occupyBy(unit.unitSoldiers.get(count));
                         GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+j][thisTile.yPosition/GUI.tileWidth+i].occupyBy(unit.unitSoldiers.get(count));
                         count++;
//                         System.out.println("Tile added  at ["+(thisTile.xPosition/GUI.tileWidth+j)+"] ["+(thisTile.yPosition/GUI.tileWidth+i)+"]");
                         
                     }
//                    
                 }
             }
             if(count<effectiveSoldiers)
             {
                for(int i=0;i<boxLength;i++)
                {
                    for(int j=0;j<boxLength;j++)
                    {
                        if(locations[j][i]==null&&count<effectiveSoldiers)
                        {
                            locations[j][i] = GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+j][thisTile.yPosition/GUI.tileWidth+i];
                            this.unit.unitSoldiers.get(count).tileOccupied=locations[j][i];
                            locations[j][i].occupyBy(unit.unitSoldiers.get(count)); 
                            count++;
                            if(count==effectiveSoldiers)
                            {
                                break;
                            }
                        }
                    }
                }
             }
             
                 break;
          }

        case 2: //east
        {
           for(int x=boxLength-1;x>=0;x--)
             {
                 for(int y=0;y<boxLength;y++)
                 {
//                     if(thisTile.xPosition/GUI.tileWidth+x+thisTile.yPosition/GUI.tileWidth+y==GUI.numberOfTilesWidth||thisTile.xPosition/GUI.tileWidth+x+thisTile.yPosition/GUI.tileWidth+y==GUI.numberOfTilesHeight)
//                     {
//                         //ask user to try again
//                         System.out.println("don't draw out of bounds ");
//                     }
                     if(x==boxLength-1||x==0||y==0||y==boxLength-1)
                     {
                         locations[x][y] = GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth-x][thisTile.yPosition/GUI.tileWidth+y];
                         this.unit.unitSoldiers.get(count).tileOccupied=locations[x][y];
                         locations[x][y].occupyBy(unit.unitSoldiers.get(count)); 
                         count++;
                     }
//                    
                    
                 }
             }
           for(int x=0;x<boxLength;x++)
             {
                 for(int y=0;y<boxLength;y++)
                 {
                   if(locations[x][y]==null&&count<effectiveSoldiers)
                   {
                       locations[x][y] = GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth-x][thisTile.yPosition/GUI.tileWidth+y];
                       this.unit.unitSoldiers.get(count).tileOccupied=locations[x][y];
                       locations[x][y].occupyBy(unit.unitSoldiers.get(count)); 
                       count++;
                   }
                }
           }
              break;
        }
        case 3: //south
       {
           for(int i=0;i<boxLength;i++)
           {
               for(int j=0;j<boxLength;j++)
               {
                   if(i==0||j==0||i==boxLength-1||j==boxLength-1)
                     {
//                         System.out.println("on the top of the box or the bottom of the box, draw the sprites");
                         locations[j][i] = GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+j][thisTile.yPosition/GUI.tileWidth+i];
                         this.unit.unitSoldiers.get(count).tileOccupied=locations[j][i];
                         locations[j][i].occupyBy(unit.unitSoldiers.get(count)); 
                         count++;
//                         System.out.println("Tile added  at ["+(thisTile.xPosition/GUI.tileWidth+j)+"] ["+(thisTile.yPosition/GUI.tileWidth+i)+"]");
                         
                     }
               }
           }
          for(int i=boxLength-1;i>=0;i--)
           {
               for(int j=0;j<boxLength;j++)
               {
                   if(locations[j][i]==null&&count<effectiveSoldiers)
                   {
                      locations[j][i] = GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth+j][thisTile.yPosition/GUI.tileWidth+i];
                       this.unit.unitSoldiers.get(count).tileOccupied=locations[j][i];
                      locations[j][i].occupyBy(unit.unitSoldiers.get(count)); 
                       count++;
                   }
               }
           }
           break;
        }
       case 4: //west
       {
          for(int x=boxLength-1;x>=0;x--)
             {
                 for(int y=0;y<boxLength;y++)
                 {
//                     if(thisTile.xPosition/GUI.tileWidth+x+thisTile.yPosition/GUI.tileWidth+y==GUI.numberOfTilesWidth||thisTile.xPosition/GUI.tileWidth+x+thisTile.yPosition/GUI.tileWidth+y==GUI.numberOfTilesHeight)
//                     {
//                         //ask user to try again
//                         System.out.println("don't draw out of bounds ");
//                     }
                     if(x==boxLength-1||x==0||y==0||y==boxLength-1)
                     {
                         locations[x][y] = GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth-x][thisTile.yPosition/GUI.tileWidth+y];
                         this.unit.unitSoldiers.get(count).tileOccupied=locations[x][y];
                         locations[x][y].occupyBy(unit.unitSoldiers.get(count)); 
                         count++;
                     }
//                    
                    
                 }
             }
          for(int x=boxLength-1;x>=0;x--)
          {
              for(int y=boxLength-1;y>=0;y--)
              {
                  
                  if(locations[x][y]==null&&count<effectiveSoldiers)
                  {
                      locations[x][y] = GUI.tileGameMap[thisTile.xPosition/GUI.tileWidth-x][thisTile.yPosition/GUI.tileWidth+y];
//                      System.out.println("width dimension = " + (thisTile.xPosition/GUI.tileWidth+i));
//                      System.out.println("height dimension = " + (thisTile.xPosition/GUI.tileWidth-j));
                      this.unit.unitSoldiers.get(count).tileOccupied=locations[x][y];
                      locations[x][y].occupyBy(unit.unitSoldiers.get(count)); 
                      count++;
                  }
              }
          }
            break;
       }
     }    
       value=1;
       
        return this;
    }
    public void paintFormation(Graphics g,int index) {
//        System.out.println("spriteLocations is " + spriteLocations);
       if(value==0)
       {
          int temp = this.index;
          Unit u =  GUI.unitDraws.get(index).getThisUnit();
          BufferedImage  unImg = u.getUnitPic(u);

        for(int i=this.index-1;i>=0;i--)
        {
            g.drawImage(unImg, spriteLocations[i].xPosition,
                    spriteLocations[i].yPosition, spriteLocations[i].xLength,
                    spriteLocations[i].yHeight,null);
        }
        
        this.index=temp;
      }
       else if(value==1)
     {
          Unit u =  GUI.unitDraws.get(index).getThisUnit();
//        System.out.println("in paintFormation");
          BufferedImage  unImg = u.getUnitPic(u);
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
    void setWedgeFormation() {
        whichFormation=2;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
