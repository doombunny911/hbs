/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 *
 * @author Andrew
 */
public class Openingmenuscreen extends JFrame
{
    
    static JFrame gameFrame;
    static JPanel tilePanel;
    JPanel buttonPanel;
    
    JButton b1 = new JButton( new AbstractAction("Run Simulation") {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            Game game = new Game();
            game.setUp();
            Game.playersForDemo = Game.playerCreator(Game.numOfPlayers);
            for(Player p : Game.playersForDemo)
              {
                  System.out.println("Player name: "+ p.playerName);
              }
              //game begin
             System.out.println("The game between"+ Game.playersForDemo.get(0)+" and "+Game.playersForDemo.get(1)+" has begun");
             game.playGame(Game.playersForDemo);
  
        }
    });

    JButton b2 = new JButton( new AbstractAction("Create Unit") { 
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            try {
                UnitCreator.createUnit();
//            UnitLoader loader = new UnitLoader();
                
                //go to file and load in all units into a unit array to later be drawn
            } catch (IOException ex) {
                Logger.getLogger(Openingmenuscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    JButton b3 = new JButton( new AbstractAction("Create Map") { 
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
//needs a lot of fiddling
            
         Map  gameMap = MapCreator.createMap();
        }
    });
       JButton b4 = new JButton ( new AbstractAction("Create Scenario"){
       @Override
       public void actionPerformed ( ActionEvent e )
       {
           Scenario scenario = ScenarioCreator.createScenario();
       }});
       JButton b5 = new JButton( new AbstractAction("Load Game") { 
        @Override
        //This refers to when the loadGame button is clicked 
       public void actionPerformed( ActionEvent e ) 
        {
            //load the game, testButton for now
            tilePanel.remove(buttonPanel);
            gameFrame.remove(tilePanel);
            GUI.gameFrame.repaint();
         
           Map gameMap = new Map(10); //the width of the tiles


            GUI.gameMap=gameMap;
            GUI.buttonLoader();
            
            
            GUI.gameFrame.revalidate();
            UnitLoader loader = new UnitLoader();
            loader.runLoader();
            ArrayList<Unit> allUnits = loader.getAllUnits();
            //drawing unit, every unit is added via this, and thus drawn.
//            while(unitNum!=0)
            GUI.unitNum=allUnits.size();
            GUI.panel.repaint();
            GUI.gameFrame.repaint();
            GUI.gameFrame.revalidate();
        }
    });

   public Openingmenuscreen()
   {       
       initializeFrame();
       
       initializeButtonPanel();
        
       initializeTilePanel();
        
        gameFrame.repaint();
        gameFrame.add(tilePanel);
        gameFrame.setVisible(true);
   }
  
   //sets to fullscreen mode, more a hinderence atm but good for final product
  public void setFullScreen()
  {
      gameFrame.setUndecorated(true);
      gameFrame.setResizable(false);
  }

    private void initializeFrame()
    {
        this.gameFrame= new JFrame("Historical Battle Simulator");
        GUI.gameFrame=gameFrame;
       
       gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
       gameFrame.setSize(new Dimension((int)Toolkit.
                getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        System.out.println("gameFrame width = "+gameFrame.getPreferredSize()
                .getWidth()+" gameFrame Height = "+ gameFrame.
                        getPreferredSize().getHeight());
       System.out.println(gameFrame.getSize());

    }

    private void initializeButtonPanel() 
    {
        buttonPanel = new JPanel(new GridLayout(5,1));
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
    }    

    private void initializeTilePanel()
    {
        tilePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.BASELINE;
        gbc.weighty=0;
        tilePanel.add(buttonPanel,gbc);

    }
  
  
}
