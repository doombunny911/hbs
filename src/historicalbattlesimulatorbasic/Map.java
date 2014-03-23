/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 *
 * @author Schmalz
 * The overall gameMap
 */
public class Map 
{
   BufferedImageLoaders bil = new BufferedImageLoaders();
   private double squareHeight;
   private double squareWidth;
   private String name;
   BufferedImage dirt = BufferedImageLoaders.dirtLoader();
   BufferedImage rock = BufferedImageLoaders.rockLoader();
   BufferedImage grass =  BufferedImageLoaders.grassLoader();
   
    public static void main(String[] args) throws IOException
    {
        Map m = MapCreator.createMap();
       // m.saveMap("file");
        
    }
    public Map(int xHeight, int yHeight)
    {
     
    }
   //populates the gameMap with basic tiles with no features other than area.
    public Map( int xWidth)
    {
        //this.name = name;
        GUI.tileWidth=xWidth;
        this.generateTiles();
        Painter gen = new Painter(squareWidth,squareHeight);
        GUI gui = new GUI(gen); //initialzes mouselistener
        GUI.panel.setLayout(null);
        GUI.copy(Openingmenuscreen.tilePanel,GUI.panel);
        
        GUI.placeUnitTester();
        
      

        
        
        
        
        GUI.gameFrame.add(gen);
    }
 
    
    public final void generateTiles()
    {
        int width = Openingmenuscreen.tilePanel.getWidth();
        int height = Openingmenuscreen.tilePanel.getHeight();
        squareWidth = Math.floor(width/GUI.tileWidth);
        squareHeight = Math.floor(height/GUI.tileWidth);
        GUI.numberOfTilesWidth=squareWidth;
        GUI.numberOfTilesHeight=squareHeight;
        GUI.tileGameMap=new Tile[(int)squareWidth][(int)squareHeight];

     System.out.println(width);
         for(int i=0;i<squareHeight;i++)
        {
            for(int j=0;j<squareWidth;j++)
            {
                GUI.tileGameMap[j][i]= new Tile(j*GUI.tileWidth,i*GUI.tileWidth,GUI.tileWidth,GUI.tileWidth);
                if(j>i)
                {
                GUI.tileGameMap[j][i].setImage(grass);}
                else
                {
                    GUI.tileGameMap[j][i].setImage(dirt);
                }
            }
        } 
    }
    //inserts a specific tile into a specific location
    public void insertTile(Tile insertTile, int x,int y)
    {
        GUI.tileGameMap[x][y]=insertTile;
    }
    
    
    
    //Sets the tile to have north, east, south, west
    public void setTileAllDirections()
    {
         for(int x=0; x<GUI.tileGameMap.length ;x++)
         {   
            for(int y=0;y<GUI.tileGameMap[x].length;y++)
            {
                Tile north=null, northeast=null, east=null, southeast=null, south=null, southwest = null, west=null, northwest=null;
                if(GUI.tileGameMap[x][y].hasNorth())
                {
                    north=GUI.tileGameMap[x][y+1];
                }
                if(GUI.tileGameMap[x][y].hasNorthEast())
                {
                    northeast=GUI.tileGameMap[x+1][y+1];
                }
                if(GUI.tileGameMap[x][y].hasEast())
                {
                    east= GUI.tileGameMap[x+1][y];
                }
                if(GUI.tileGameMap[x][y].hasSouthEast())
                {
                    southeast=GUI.tileGameMap[x+1][y-1];
                }
                 if(GUI.tileGameMap[x][y].hasSouth())
                {
                    east= GUI.tileGameMap[x][y-1];
                }
                 if(GUI.tileGameMap[x][y].hasSouthWest())
                 {
                     southwest=GUI.tileGameMap[x-1][y-1];
                 }
                  if(GUI.tileGameMap[x][y].hasWest())
                {
                    east= GUI.tileGameMap[x-1][y];
                }
                  if(GUI.tileGameMap[x][y].hasNorthWest())
                  {
                     northwest= GUI.tileGameMap[x-1][y+1];
                  }

             
            GUI.tileGameMap[x][y].setDirections(north, east, south, west);
             //populate the gameMap with tiles. NESW. Might need 
            
        }
    }
    }
    
    public void setTileTerrain(Tile t, BufferedImage img)
    {
        t.image = img;
    }
    public void saveMap(String fileName2) throws IOException
    {
        PrintWriter writer = null;
        String fileName=fileName2+".txt";
        File file = new File("Maps"+File.separator+fileName);
        File parent = file.getParentFile();

if(!parent.exists() && !parent.mkdirs()){
    throw new IllegalStateException("Couldn't create dir: " + parent);
} 
        try {                      
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            writer.println("---"); //seperator 
            writer.println(this.squareWidth);
            System.out.println("Saved");
            writer.close();
             } 
        catch (FileNotFoundException ex)
             {
            System.out.println("Unable to save file");
        } 
        finally {
            writer.close();
        }
    }
    
     
    
 }
   

    
    
   

