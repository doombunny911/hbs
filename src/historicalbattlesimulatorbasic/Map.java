/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Graphics;

/**
 *
 * @author Schmalz
 * The overall gameMap
 */
public class Map 
{
    static  Tile[][] gameMap;
    static int tileWidth; 
    
    public Map(int xHeight, int yHeight)
    {
//        gameMap = new Tile[xHeight][yHeight]; Not sure what you were going for here
        //if gameMap = the grid of Tiles as it appears to be, setting it equal 
       
    }
//    public Map(int xWidth,GUI gui)
//    {
//        this.tileWidth=xWidth;
////        this.gui=gui;
//        
//    }
    public Map(int xWidth)
    {
        tileWidth=xWidth;

    }
    //populates the gameMap with basic tiles with no features other than area.
    public void populateMap()
    {
        //not sure if this works, including own implemenation after
//        for(int y=0; y<gameMap.length ;y++)
//        {   
//            for(int x=0;x<gameMap[y].length;y++)
//            {
//                gameMap[x][y]= new Tile(x,y, gameMap.length, gameMap[x].length); //populate the gameMap with tiles
//                System.out.print("Populated");  
//            }
//        }
//        setTileNESW();
        gameMap = new Tile[5000][5000]; //something that should never be reached
//        GUI.gameFrame.add(new TileMapGenerator());
        Openingmenuscreen.gameFrame.add(new TileMapGenerator(tileWidth));

//        TileMapGenerator gen = new TileMapGenerator(tileWidth);

//        Openingmenuscreen.gameFrame.add(gen);
//        GUI gui = new GUI();
//        gui.addToFrame(gen);
//        gui.tileGameMap=gameMap;
//         Openingmenuscreen.gameFrame.validate();
//         Openingmenuscreen.gameFrame.repaint();
       
//        GUI gui = Openingmenuscreen.gui;
        
    }
    //inserts a specific tile into a specific location
    public void insertTile(Tile insertTile, int x,int y)
    {
        gameMap[x][y]=insertTile;
    }
    
    public void setTileNESW()
    {
         for(int x=0; x<gameMap.length ;x++){   
        for(int y=0;y<gameMap[x].length;y++)
        {
            Tile north=null, east=null, south=null, west=null;
            if(gameMap[x][y].hasNorth())
            {
                north=gameMap[x][y+1];
            }
            if(gameMap[x][y].hasEast())
            {
                east= gameMap[x+1][y];
            }
             if(gameMap[x][y].hasSouth())
            {
                east= gameMap[x][y-1];
            }
              if(gameMap[x][y].hasWest())
            {
                east= gameMap[x-1][y];
            }
            
             
            gameMap[x][y].setDirections(north, east, south, west);
             //populate the gameMap with tiles. NESW. Might need 
            
        }
    }
    }

   
}
    
    
   

