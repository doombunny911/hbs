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
class Scenario {
    
    //needs to create a map
    
    public Scenario(){
        Game game = new Game();
        
        Map map = new Map(10, true);
        GUI.scenario = this;
   
        String name = JOptionPane.showInputDialog(null, "Enter the name for player 1");
        JOptionPane.showMessageDialog(null," choose the army for: " + name);
        Player p1 = new Player(name);
        name = JOptionPane.showInputDialog(null, " Enter the name for player 2");
        JOptionPane.showMessageDialog(null,"  choose the army for : " +name );
        Player p2 = new Player(name);
        Game.playersForDemo.add(p1);
        Game.playersForDemo.add(p2);
        
        p1.up.setUpButtons();
        p2.up.setUpButtons();
        
       p1.up.setBounds(200, GUI.gameFrame.getHeight()-400, 200, 200);
       p2.up.setBounds(GUI.gameFrame.getWidth()-400, GUI.gameFrame.getHeight()-400, 200, 200);
       GUI.panel.add(p1.up);
       GUI.panel.add(p2.up);           
       GUI.scenario = this;
       game.gameMap=map;
      GUI.buttonLoader();
        
    }
    //use unitPlacers to allow prof to place units
    
    //need an arrayList of UnitDraws
    
    //need an arrayList of units for player 1 and player 2
    
    
    
}
