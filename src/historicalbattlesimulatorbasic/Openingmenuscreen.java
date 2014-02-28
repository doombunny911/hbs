/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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
//           
            Game simulation = new Game(); 
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter number of players: ");
            int numPlayers = scan.nextInt();
//            simulation.setUp(numPlayers);
//            simulation.playGame();
        }
    });

    JButton b2 = new JButton( new AbstractAction("Create Unit") { 
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            System.out.println("test 2");
//            UnitCreator.createUnit();
//            UnitLoader loader = new UnitLoader();
            
            //go to file and load in all units into a unit array to later be drawn
        }
    });
    JButton b3 = new JButton( new AbstractAction("Create Map") { 
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
//needs a lot of fiddling
            Scanner scan = new Scanner(System.in);
            int width, height;
            System.out.println("Enter the width[East to West] of the map");
            width=scan.nextInt();
            System.out.println("Enter the height[North to South of the map");
            height=scan.nextInt();
            Map gameMap= new Map(width,height);
        }
    });
       JButton b4 = new JButton( new AbstractAction("Load Game") { 
        @Override
        //This refers to when the loadGame button is clicked 
       public void actionPerformed( ActionEvent e ) 
        {
            //load the game, testButton for now
            System.out.println("test");
            tilePanel.remove(buttonPanel);
            gameFrame.remove(tilePanel);
            GUI.gameFrame.repaint();
         
          System.out.println("here");
            Map gameMap=new Map(10);
            GUI.gameMap=gameMap;
            GUI.buttonLoader();
            
            
            GUI.gameFrame.revalidate();
            UnitLoader loader = new UnitLoader();
            loader.runLoader();
            ArrayList<Unit> allUnits = loader.getAllUnits();
            //drawing unit, every unit is added via this, and thus drawn.
//            while(unitNum!=0)
             GUI.unitNum=allUnits.size();
             System.out.println("this happens here");
            GUI.gameFrame.revalidate();
        }
    });

   public Openingmenuscreen()
   {
       
       gameFrame= new JFrame("Historical Battle Simulator");
       GUI.gameFrame=gameFrame;
       
       gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
       gameFrame.setSize(new Dimension((int)Toolkit.
                getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        System.out.println("gameFrame width = "+gameFrame.getPreferredSize()
                .getWidth()+" gameFrame Height = "+ gameFrame.
                        getPreferredSize().getHeight());
       System.out.println(gameFrame.getSize());

        buttonPanel = new JPanel(new GridLayout(4,1));
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.setBackground(Color.red); 
        tilePanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.BASELINE;
        gbc.weighty=0;
        gameFrame.validate();
        tilePanel.add(buttonPanel,gbc);
        gameFrame.add(tilePanel);
        gameFrame.setVisible(true);
//        Unit unit = new Unit(new Soldier(),4);
   }
  
   //sets to fullscreen mode, more a hinderence atm 
  public void setFullScreen()
  {
      gameFrame.setUndecorated(true);
      gameFrame.setResizable(false);
  }
  
  
}
