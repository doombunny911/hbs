/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;


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
   //populates the gameMap with basic tiles with no features other than area.
    public Map(int xWidth)
    {
        GUI.tileWidth=xWidth;
        tileWidth=xWidth;
        Painter gen = new Painter(tileWidth);
        gameMap=gen.tiles;
        GUI gui = new GUI(gen); //initialzes mouselistener
        GUI.copy(Openingmenuscreen.tilePanel,GUI.panel);
        GUI.gameFrame.add(gen);
    }
 
    //inserts a specific tile into a specific location
    public void insertTile(Tile insertTile, int x,int y)
    {
        gameMap[x][y]=insertTile;
    }
    
    //Sets the tile to have north, east, south, west
    public void setTileAllDirections()
    {
         for(int x=0; x<gameMap.length ;x++)
         {   
            for(int y=0;y<gameMap[x].length;y++)
            {
                Tile north=null, northeast=null, east=null, southeast=null, south=null, southwest = null, west=null, northwest=null;
                if(gameMap[x][y].hasNorth())
                {
                    north=gameMap[x][y+1];
                }
                if(gameMap[x][y].hasNorthEast())
                {
                    northeast=gameMap[x+1][y+1];
                }
                if(gameMap[x][y].hasEast())
                {
                    east= gameMap[x+1][y];
                }
                if(gameMap[x][y].hasSouthEast())
                {
                    southeast=gameMap[x+1][y-1];
                }
                 if(gameMap[x][y].hasSouth())
                {
                    east= gameMap[x][y-1];
                }
                 if(gameMap[x][y].hasSouthWest())
                 {
                     southwest=gameMap[x-1][y-1];
                 }
                  if(gameMap[x][y].hasWest())
                {
                    east= gameMap[x-1][y];
                }
                  if(gameMap[x][y].hasNorthWest())
                  {
                     northwest= gameMap[x-1][y+1];
                  }

             
            gameMap[x][y].setDirections(north, east, south, west);
             //populate the gameMap with tiles. NESW. Might need 
            
        }
    }
    }

   
}
    
    
   

