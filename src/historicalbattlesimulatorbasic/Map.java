/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import static historicalbattlesimulatorbasic.UnitLoader.loadUnit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


/**
 *
 * @author Schmalz
 * The overall gameMap
 */
public class Map 
{
   BufferedImageLoaders bil = new BufferedImageLoaders();
   ArrayList<Tile> tilesToIncorporate = new ArrayList();
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
     BufferedImageName tree2 = BufferedImageLoaders.imageLoader("tree.png");
   BufferedImageName rocksGround = BufferedImageLoaders.imageLoader("rocksGround2.png");
 
    public static void main(String[] args) throws IOException{
//        Map m = MapCreator.createMap();
       // m.saveMap("file");
        
    }
   //populates the gameMap with basic tiles with no features other than area.
    public Map( int xWidth, boolean creating){
        //this.name = name;
        GUI.tileWidth=xWidth;
<<<<<<< HEAD
        this.generateTiles();
//        generateLoaded();
=======
        if(creating)
        {
        this.generateLoaded();
        }
        else
        {
            this.generateBasic();
        }
>>>>>>> STuff
        Painter gen = new Painter(squareWidth,squareHeight);
        GUI gui = new GUI(gen); //initialzes mouselistener
        GUI.panel.setLayout(null);
        GUI.copy(Openingmenuscreen.tilePanel,GUI.panel);
        
//        GUI.placeUnitTester();
        GUI.gameFrame.add(gen);
    }
    public Map(int xWidth,String s)
    {
           //this.name = name;
        ScenarioGUI.tileWidth=xWidth;
        this.generateTilesS();
        ScenarioPainter gen = new ScenarioPainter(squareWidth,squareHeight);
        ScenarioGUI gui = new ScenarioGUI(gen); //initialzes mouselistener
        ScenarioGUI.panel.setLayout(null);
        ScenarioGUI.copy(Openingmenuscreen.tilePanel,ScenarioGUI.panel);
        
//        GUI.placeUnitTester();
        ScenarioGUI.gameFrame.add(gen);
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
               
                GUI.tileGameMap[j][i].setImage(grass);
               
            }
        } 
    }
  
    public final void generateLoaded()
    {
        this.loadMap();
        int width = Openingmenuscreen.tilePanel.getWidth();
        int height = Openingmenuscreen.tilePanel.getHeight();
        squareWidth = Math.floor(width/GUI.tileWidth);
        squareHeight = Math.floor(height/GUI.tileWidth);
        GUI.numberOfTilesWidth=squareWidth;
        GUI.numberOfTilesHeight=squareHeight;
        GUI.tileGameMap=new Tile[(int)squareWidth][(int)squareHeight];

      boolean b = false;
      boolean c = false;
      Tile adder = null;
         for(int i=0;i<squareHeight;i++)
        {
            for(Tile t: tilesToIncorporate)
            {
                if(t.yPosition==i)
                {
                    b = true;
                }
                    
            }
            for(int j=0; j<squareWidth; j++)
            {
                if(b)
                {
                    for(Tile t: tilesToIncorporate)
                     {
                        if(t.xPosition==j)
                            {
                             c = true;
                             adder = t;
                             }
                    
                      }
                }
                if(c)
                {
                GUI.tileGameMap[j][i] = adder;
                GUI.tileGameMap[j][i].setImage(adder.image);
                }
                else
                {
                 GUI.tileGameMap[j][i]= new Tile(j*GUI.tileWidth,i*GUI.tileWidth,GUI.tileWidth,GUI.tileWidth);
                 GUI.tileGameMap[j][i].setImage(grass);}
                }
            }
        } 
        
    
  
    public final void generateTilesS(){
        int width = Openingmenuscreen.tilePanel.getWidth();
        int height = Openingmenuscreen.tilePanel.getHeight();
        squareWidth = Math.floor(width/ScenarioGUI.tileWidth);
        squareHeight = Math.floor(height/ScenarioGUI.tileWidth);
        ScenarioGUI.numberOfTilesWidth=squareWidth;
        ScenarioGUI.numberOfTilesHeight=squareHeight;
        ScenarioGUI.tileGameMap=new Tile[(int)squareWidth][(int)squareHeight];

   
         for(int i=0;i<squareHeight;i++)
        {
            for(int j=0;j<squareWidth;j++)
            {
                ScenarioGUI.tileGameMap[j][i]= new Tile(j*ScenarioGUI.tileWidth,i*ScenarioGUI.tileWidth,ScenarioGUI.tileWidth,ScenarioGUI.tileWidth);
                if(j>i)
                {
                ScenarioGUI.tileGameMap[j][i].setImage(grass);}
                else
                {
                    ScenarioGUI.tileGameMap[j][i].setImage(dirt);
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
   
         for(int i=0;i<squareHeight;i++)
        {
            for(int j=0;j<squareWidth;j++)
            {
                createAgincourt(j, i, rng);
                
            }
        } 
    }
    
    public void createAgincourt(int j, int i, Random rng)
    {
        
        GUI.tileGameMap[j][i]= new Tile(j*GUI.tileWidth,i*GUI.tileWidth,GUI.tileWidth,GUI.tileWidth);
        //create the forest around
        if(i<(1*GUI.numberOfTilesHeight/5)||i>4*GUI.numberOfTilesHeight/5)
        {
            if(rng.nextInt(10)<8)
           {
           
             GUI.tileGameMap[j][i].setImage(tree2);
             //GUI.tileGameMap[j][i].setTileBlocked(); 
            }
           else
            {
                GUI.tileGameMap[j][i].setImage(grass);
          }
        }
        else if((j>(9.75*GUI.numberOfTilesWidth/20)&&j<10.25*GUI.numberOfTilesWidth/20))
        {
            GUI.tileGameMap[j][i].setImage(rock);
        }
        else 
        {
            
             GUI.tileGameMap[j][i].setImage(dirt);
             GUI.tileGameMap[j][i].setTerrainDifficultyLevel(2);
            // GUI.tileGameMap[j][i].setTileBlocked();
            
           
             
        }
    }
    
    public void loadMap()
    {
          
        final JFileChooser fc = new JFileChooser();
        File dir = new File("Maps"+File.separator+"mapAnchor.txt");
        
        System.out.println(dir);
        fc.setCurrentDirectory(dir);
        File  current = fc.getCurrentDirectory();
        
        System.out.println(current);
        fc.showOpenDialog(null);
        
  
       
        String name = fc.getSelectedFile().getName();
        System.out.println("You have selected to load "+name+ "map.");
        tilesToIncorporate = this.loadAllTiles(name);
      
        
        
    }
    public void createThermopayle(int j, int i, Random rng) {
        GUI.tileGameMap[j][i]= new Tile(j*GUI.tileWidth,i*GUI.tileWidth,GUI.tileWidth,GUI.tileWidth);
        
        //secret path
        
        if(i>4*GUI.numberOfTilesHeight/10&& j<GUI.numberOfTilesWidth/2 && j>GUI.numberOfTilesWidth/6&&
                j>5*GUI.numberOfTilesWidth/10 && i>4*GUI.numberOfTilesHeight/10&& j>=GUI.numberOfTilesWidth/2 &&
                j<=9*GUI.numberOfTilesWidth/10&&
                j<(6*GUI.numberOfTilesWidth/10)&&
                i>8.5*GUI.numberOfTilesHeight/10 &&
                i<9*GUI.numberOfTilesHeight/10&
                i>8.5*GUI.numberOfTilesHeight/10 &&
                i<9*GUI.numberOfTilesHeight/10)
        {
            
            GUI.tileGameMap[j][i].setImage(rock);
            GUI.tileGameMap[j][i].setHeight(2);
            
            
        }
        // mountains
        else if(i>4*GUI.numberOfTilesHeight/10&& j<GUI.numberOfTilesWidth/2 && j>GUI.numberOfTilesWidth/6)
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
                GUI.tileGameMap[j][i].setImage(grass);
                //switcher = true;
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

    private ArrayList<Tile> loadAllTiles(String name) {
        try {
            //Load all units
            //while
            String fileName=name;
            File file = new File("Maps"+ File.separator+fileName);
            Scanner reader = new Scanner(new FileInputStream(file));
         while(reader.hasNext())
         {
             if(reader.nextLine().equals("---"))
                 {
                     if(reader.hasNextLine())
                     {
                     String xPos = reader.nextLine();
                     System.out.println("xPos "+name);
                     String yPos = reader.nextLine();
                     String blocked = reader.nextLine();
                     String imageName = reader.nextLine();
                     Tile t = new Tile(10,10,Integer.parseInt(xPos),Integer.parseInt( yPos));
                     if(blocked.equals("true"))
                     {
                         t.setBlocked();
                     }
                     t.setImageFromString(imageName);
//                     nUnit.setUnitUnitID();
                     tilesToIncorporate.add(t);   
                     System.out.println("++++++++++++++++++++++ TILE "+t.image.name+" LOADED ");}
                 }
             else reader.nextLine();
         }
         
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UnitLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tilesToIncorporate;
    }
    
     
    
 }
   

    
    
   

