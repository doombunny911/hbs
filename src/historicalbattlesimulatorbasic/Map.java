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
import java.util.Random;


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
   BufferedImageName dirt = BufferedImageLoaders.dirtLoader();
   BufferedImageName rock = BufferedImageLoaders.rockLoader();
   BufferedImageName grass =  BufferedImageLoaders.grassLoader();
   BufferedImageName water = BufferedImageLoaders.waterLoader();
   BufferedImageName mountain = BufferedImageLoaders.imageLoader("mountainRock.png");
   BufferedImageName wave = BufferedImageLoaders.imageLoader("wave.png");
   BufferedImageName sand = BufferedImageLoaders.imageLoader("sand.png");
   BufferedImageName rocksGround = BufferedImageLoaders.imageLoader("rocksGround2.png");
    public static void main(String[] args) throws IOException{
//        Map m = MapCreator.createMap();
       // m.saveMap("file");
        
    }
   //populates the gameMap with basic tiles with no features other than area.
    public Map( int xWidth){
        //this.name = name;
        GUI.tileWidth=xWidth;
        this.generateTiles();
        Painter gen = new Painter(squareWidth,squareHeight);
        GUI gui = new GUI(gen); //initialzes mouselistener
        GUI.panel.setLayout(null);
        GUI.copy(Openingmenuscreen.tilePanel,GUI.scroll);
        
//        GUI.placeUnitTester();
        GUI.gameFrame.add(gen);
    }
    public final void generateBasic(){
        int width = Openingmenuscreen.tilePanel.getWidth();
        int height = Openingmenuscreen.tilePanel.getHeight();
        squareWidth = Math.floor(width/GUI.tileWidth);
        squareHeight = Math.floor(height/GUI.tileWidth);
        GUI.numberOfTilesWidth=squareWidth;
        GUI.numberOfTilesHeight=squareHeight;
        GUI.tileGameMap=new Tile[(int)squareWidth][(int)squareHeight];

   
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
    public final void generateTiles(){
        int width = Openingmenuscreen.tilePanel.getWidth();
        int height = Openingmenuscreen.tilePanel.getHeight();
        squareWidth = Math.floor(width/GUI.tileWidth);
        squareHeight = Math.floor(height/GUI.tileWidth);
        GUI.numberOfTilesWidth=squareWidth;
        GUI.numberOfTilesHeight=squareHeight;
        GUI.tileGameMap=new Tile[(int)squareWidth][(int)squareHeight];
    Random rng = new Random();
    //boolean switcher = false;
    boolean goatPath = true;
         for(int i=0;i<squareHeight;i++)
        {
            for(int j=0;j<squareWidth;j++)
            {
                GUI.tileGameMap[j][i]= new Tile(j*GUI.tileWidth,i*GUI.tileWidth,GUI.tileWidth,GUI.tileWidth);
                
                //secret path
                
                if((goatPath&&i>8.5*GUI.numberOfTilesHeight/10 && i<9*GUI.numberOfTilesHeight/10 )|| (j>8.3*GUI.numberOfTilesWidth/10 && j<(8.5*GUI.numberOfTilesWidth/10)&&i<4.5*GUI.numberOfTilesHeight/5)&&i>3*GUI.numberOfTilesHeight/5)
                {
                    if(i<GUI.numberOfTilesHeight/4)
                    {
                     GUI.tileGameMap[j][i].setImage(dirt);
                     GUI.tileGameMap[j][i].setHeight(2);
                    }
                    else 
                    {
                        GUI.tileGameMap[j][i].setImage(rock);
                        GUI.tileGameMap[j][i].setHeight(2);
                    }
                }
               // mountains 
                 if(i>4*GUI.numberOfTilesHeight/10&& j<GUI.numberOfTilesWidth/2 && j>GUI.numberOfTilesWidth/6)
                {
                    if(2*(GUI.numberOfTilesWidth/2-j)<i)
                    {
                        if(rng.nextBoolean())    
                        { 
                            GUI.tileGameMap[j][i].setImage(mountain);
                        }
                        else
                        {
                            GUI.tileGameMap[j][i].setImage(rocksGround);
                        }
                         GUI.tileGameMap[j][i].setTileBlocked();
                    }
                    else
                    {
                        GUI.tileGameMap[j][i].setImage(dirt);
                    }
                }
                 else if(i>4*GUI.numberOfTilesHeight/10&& j>=GUI.numberOfTilesWidth/2 && j<=9*GUI.numberOfTilesWidth/10)
                {
                    if(2*(j-GUI.numberOfTilesWidth/2)<i)
                    {
                        if(rng.nextBoolean())    
                        { 
                            GUI.tileGameMap[j][i].setImage(mountain);
                        }
                        else
                        {
                            GUI.tileGameMap[j][i].setImage(rocksGround);
                        }
                         GUI.tileGameMap[j][i].setTileBlocked();
                    }
                    else
                    {
                        GUI.tileGameMap[j][i].setImage(dirt);
                    }
                }
                else if(i<GUI.numberOfTilesHeight/6&&!(j<GUI.numberOfTilesWidth/5))
                {
                    if(rng.nextBoolean())
                    {
                    GUI.tileGameMap[j][i].setImage(water);
                    }
                    else
                    {
                    GUI.tileGameMap[j][i].setImage(wave);
                    }
                    GUI.tileGameMap[j][i].setTileBlocked();
                }
              
                else if(j<GUI.numberOfTilesWidth/5&&j>i&&i<GUI.numberOfTilesHeight/6)
                {
                   if(rng.nextBoolean())
                    {
                    GUI.tileGameMap[j][i].setImage(water);
                    }
                    else
                    {
                    GUI.tileGameMap[j][i].setImage(wave);
                    }
                    GUI.tileGameMap[j][i].setTileBlocked();
                }
                  else if(i<10*GUI.numberOfTilesHeight/60 && i<13*GUI.numberOfTilesHeight/60)
                {
                    if(rng.nextBoolean())
                    GUI.tileGameMap[j][i].setImage(sand);
                    else
                    GUI.tileGameMap[j][i].setImage(dirt);
                }
                else
                { 
                    
                    if(rng.nextInt(15)>14)
                    {
                        GUI.tileGameMap[j][i].setImage(grass);
                      //  switcher = false;
                    }
                    else 
                    {
                        GUI.tileGameMap[j][i].setImage(dirt);
                        //switcher = true;
                    }
                    
                }
                
            }
        } 
    }
    //inserts a specific tile into a specific location
    public void insertTile(Tile insertTile, int x,int y) {
        GUI.tileGameMap[x][y]=insertTile;
    }
    public void insertSquare(Tile insertTile, int topX, int topY, int width, int height){
        for(int i=0; i<width; i++)
        {
            for(int j=0; j<height; j++)
            {
                GUI.tileGameMap[topX+width][topY+height]=insertTile;
            }
        }
    }
    //public void 
    
    //Sets the tile to have north, east, south, west
//    public void setTileAllDirections()
//    {
//         for(int x=0; x<GUI.tileGameMap.length ;x++)
//         {   
//            for(int y=0;y<GUI.tileGameMap[x].length;y++)
//            {
//                Tile north=null, northeast=null, east=null, southeast=null, south=null, southwest = null, west=null, northwest=null;
//                if(GUI.tileGameMap[x][y].hasNorth())
//                {
//                    north=GUI.tileGameMap[x][y+1];
//                }
//                if(GUI.tileGameMap[x][y].hasNorthEast())
//                {
//                    northeast=GUI.tileGameMap[x+1][y+1];
//                }
//                if(GUI.tileGameMap[x][y].hasEast())
//                {
//                    east= GUI.tileGameMap[x+1][y];
//                }
//                if(GUI.tileGameMap[x][y].hasSouthEast())
//                {
//                    southeast=GUI.tileGameMap[x+1][y-1];
//                }
//                 if(GUI.tileGameMap[x][y].hasSouth())
//                {
//                    east= GUI.tileGameMap[x][y-1];
//                }
//                 if(GUI.tileGameMap[x][y].hasSouthWest())
//                 {
//                     southwest=GUI.tileGameMap[x-1][y-1];
//                 }
//                  if(GUI.tileGameMap[x][y].hasWest())
//                {
//                    east= GUI.tileGameMap[x-1][y];
//                }
//                  if(GUI.tileGameMap[x][y].hasNorthWest())
//                  {
//                     northwest= GUI.tileGameMap[x-1][y+1];
//                  }
//
//             
//            GUI.tileGameMap[x][y].setDirections(north, east, south, west);
//             //populate the gameMap with tiles. NESW. Might need 
//            
//        }
//    }
//    }
//    
    //Sets a tile's terrain image
    public void setTileTerrain(Tile t, BufferedImage img){
        t.image.setImage(img);
    }
    public void saveMap(String fileName2) throws IOException{
        PrintWriter writer = null;
        String fileName=fileName2+".txt";
        File file = new File("Maps"+File.separator+fileName);
        File parent = file.getParentFile();

if(!parent.exists() && !parent.mkdirs()){
    throw new IllegalStateException("Couldn't create dir: " + parent);
} 
        try {                      
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            //seperator 
             for(int i=0;i<squareHeight;i++)
        {
                 for(int j=0;j<squareWidth;j++)
                    {
                   String saver[] = Tile.saveTile(GUI.tileGameMap[j][i]);
                        writer.println("---");
                            for(int l=0; l<saver.length;l++)
                            {
                                writer.print(saver[l]);
                            }
                     }
        } 
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
   

    
    
   

