/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import javax.swing.JOptionPane;

/**
 *
 * @author Edward
 */

public class MapCreator
{
   
    public static Map createMap()
    {
        String prompt[] = new String[1];
     //   prompt[0]="Enter the name of your map:";
        prompt[0]="Enter the width of your map: ";
        String mapParticulars[] = new String[1]; 
        for(int i=0;i<mapParticulars.length; i++)
        {
        mapParticulars[i]=JOptionPane.showInputDialog ( prompt[i] );
        }
        Map map = new Map(Integer.parseInt(mapParticulars[0]));
        return map;
        //Place Different Items on the map
    }
}
