/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.RepaintManager;
/**
 * One tile can hold one unit. Each tile has a different effect. Each tile is a square
 * @author Schmalz
 */
public class Tile extends Rectangle
{
//   BufferedImage  grass = grassLoader();
//   BufferedImage dirt = dirtLoader();
//   BufferedImage rocks = rockLoader() ;
  BufferedImage tree = treeLoader();
//    boolean repaint = true;

    
    boolean tileBlocked;
    int xPosition, yPosition, zPosition;
    int terrainEffect; //terrain effects will be all categorized as ints to allow easy return access.
    int levelOfCover; // 0 is none, 1 is light, 2 is full
    int  xLength, yLength;
    Soldier occupyingSoldier;
    Boolean isOccupied;
    Tile tileNorth, tileNorthEast, tileEast, tileSouthEast, tileSouth, tileSouthWest, tileWest, tileNorthWest;
//    public static BufferedImage grassLoader() 
//    {
//       BufferedImage grass1=null;
//       try {
//           grass1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"greenGround.png"));
//       } catch (IOException ex) {
//           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
//       }
//       return grass1;
//    }
//      public static BufferedImage rockLoader() 
//    {
//       BufferedImage rock1=null;
//       try {
//           rock1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"rocksGround.png"));
//       } catch (IOException ex) {
//           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
//       }
//       return rock1;
//    }
        public static BufferedImage treeLoader() 
   {
      BufferedImage tree1=null;
       try {
           tree1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"tree.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
       return tree1;
    }
    
//    
//      public static BufferedImage dirtLoader() 
//    {
//       BufferedImage brown1=null;
//       try {
//           brown1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"brownGround.png"));
//       } catch (IOException ex) {
//           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
//       }
//       return brown1;
//    }
    
    //a tile is just a rectangle, therefore, xPosition and yPosition refer
    //to the (x,y) coords for the top left point
    //load tiles of buffered the images 
    public Tile(int xPosition, int yPosition, int xLength, int yLength)
    {
            
        
        super.setBounds(xPosition, yPosition, xLength, yLength);
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        this.xLength= xLength; 
        this.yLength= yLength; 
        this.isOccupied=false; 
       
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
    
    protected void colorTile(Graphics g) throws IOException
    {
        Random rng = new Random(100);
//       
       
      Graphics2D g2=(Graphics2D)g;
       g2.setColor(Color.LIGHT_GRAY);
//        g2.drawImage(grass, null, this.xPosition, this.yPosition);
         if(rng.nextInt()<13)
        {
            g2.drawImage(tree,null, this.xPosition, this.yPosition);
       }
         else
         {
             g2.fill(this);
         }
    Rectangle tileR = new Rectangle(this.xLength, this.yLength, this.xPosition, this.yPosition);
      GUI.panel.repaint(tileR);
//        
       
        
    }
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

    public void loadSprite(Graphics g,Tile t,Unit unit, String unitSprite) 
    {
        
        //ONLY EVEN NUMBER OF SOLDIERS ATM PLZ
       BufferedImage img = null;
//               new BufferedImage(xLength,yLength,1);
       int numberOfSoldiers =unit.unitSize;
       
       try 
       {
          img = ImageIO.read(new File("Sprites"+File.separator+"UnitSprites"+File.separator+ unitSprite));
          for(int i=0;i<unit.unitSoldiers.length/2;i++)
          {
              for(int j=0;j<numberOfSoldiers/2;j++)
              {
                   g.drawImage(img, t.xPosition+j*t.xLength, t.yPosition+i*t.yLength, t.xLength, t.yLength, null);
              }
             
          }
          
          
       } 
       catch (IOException e) 
       {
           System.out.println("error loading file " + e);
        }
    }
    
    public Unit checkUnitWithinBounds(Tile tile)
    {
//        //if a unit's x+width and y+height intersects with the tiles position
//        //return which unit that is
//        for(int i=0;i<GUI.units.size();i++)
//        {
//            if(GUI.units.get(i).xDraw)
//        }
        return null;
    }
}
