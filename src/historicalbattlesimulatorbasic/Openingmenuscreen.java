/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;



/**
 *
 * @author Andrew
 */
public class Openingmenuscreen extends JFrame
{
    static JPanel tilePanel;//becomes obselete after GUI is initialized
    JFrame gameFrame; //become obsolete after GUI is initialized
    JPanel buttonPanel;
    JPanel welcomePanel;
    JButton b1 = new JButton( new AbstractAction("Run Simulation") {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            Game game = new Game();  
        }
    });

    JButton b2 = new JButton( new AbstractAction("Create Unit") { 
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            try {
                UnitCreator.createUnit();
//            UnitLoader loader = new UnitLoader();
                
                //go to file and load in all unitDraws into a unit array to later be drawn
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
            
            //remove all panels
            removePanels();
            
            
//            GUI.panel.setLayout(null);
            Map gameMap = new Map(10); //the width of the tiles

            GUI.gameMap=gameMap;
            GUI.buttonLoader();
            
            

            GUI.repainter();
        }
    });

   public Openingmenuscreen()
   {       
       initFrame();
     
     
       initButtonPanel();
        
       initTilePanel();
         
       
               
       initWelcomePanel();
       //Graphics g = this.getGraphics();
       
       
       gameFrame.add(tilePanel);
       gameFrame.setVisible(true);
       GUI.gameFrame=gameFrame;
   }
  
   //sets to fullscreen mode, more a hinderence atm but good for final product

   public void setFullScreen()
  {
      gameFrame.setUndecorated(true);
      gameFrame.setResizable(false);
  }

    private void initFrame()
    {
       this.gameFrame= new JFrame("Historical Battle Simulator");
       
       gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); //program stops if frame is close
       
       //sets size of gameFrame to that of your screen
       gameFrame.setSize(new Dimension((int)Toolkit.
                getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
       
//        System.out.println("gameFrame width = "+gameFrame.getPreferredSize()
//                .getWidth()+" gameFrame Height = "+ gameFrame.
//                        getPreferredSize().getHeight());
//       System.out.println(gameFrame.getSize());
    }

    private void initButtonPanel() 
    {
        buttonPanel = new JPanel(new GridLayout(5,1));
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
    }    

   
    private void initTilePanel()
    {
        tilePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.BASELINE;
        gbc.weighty=0;
        tilePanel.add(buttonPanel,gbc);
       
    }
   private void removePanels()
   {
       gameFrame.remove(welcomePanel);
       tilePanel.remove(buttonPanel);
       gameFrame.remove(tilePanel);
   }

    private void initWelcomePanel() 
    {
        welcomePanel = new JPanel();
        welcomePanel.setBounds(0,0,gameFrame.getWidth(),200);
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setHorizontalAlignment(JLabel.HEIGHT);
        welcomeLabel.setText("Welcome to the Historical Battle Simulator");
        welcomeLabel.setFont(new Font("Serif",Font.BOLD,65));
        welcomePanel.add(welcomeLabel);    
       gameFrame.add(welcomePanel);

    }
  
}
