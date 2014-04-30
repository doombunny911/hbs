/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Edward
 */

public class MapCreator extends JPanel
{
   Map world;
   public static void main(String[] args)
   {
       MapCreator mp = new MapCreator();
       mp.createMap();
   }
         
    public Map createMap()
    {
     
        String mapParticulars[] = new String[1]; 
        for(int i=0;i<mapParticulars.length; i++)
        {
        mapParticulars[i]=JOptionPane.showInputDialog ( "Name For Map" );
        }
        
        world = GUI.gameMap;
        world.generateBasic();
        
        //
       return world;
        //Place Different Items on the map
    }
    
}
