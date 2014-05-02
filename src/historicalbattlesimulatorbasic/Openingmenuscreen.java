/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author Andrew
 */
public final class Openingmenuscreen
{
    static JPanel tilePanel;//becomes obselete after GUI is initialized
    JFrame gameFrame; //become obsolete after GUI is initialized
    JPanel buttonPanel;
    JPanel welcomePanel;
    JButton b1, b2 ,b3, b4, b5;
   BufferedImageLoaders bil = new BufferedImageLoaders();
    public void initButtonImages(){
        bil.loadMenuButtons();
        ImageIcon run = bil.getIconRunSimulation();
        b1 = new JButton(run) ;
        b1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            
            Game game = new Game();
            
            removePanels();
            
            String name = JOptionPane.showInputDialog(null, "Enter your name player 1");
            JOptionPane.showMessageDialog(null, name   +" choose your army:");
            Player p1 = new Player(name);
            name = JOptionPane.showInputDialog(null, " Enter your name player 2");
            JOptionPane.showMessageDialog(null,name +"  choose your army:");
            Player p2 = new Player(name);
            p1.myTurn=true;
            Game.playersForDemo.add(p1);
            Game.playersForDemo.add(p2);

            Map map = new Map(10);
            //map.loadMap();
            p1.up.setUpButtons();
            p2.up.setUpButtons();
            
            p1.up.setBounds(200, GUI.gameFrame.getHeight()-400, 200, 200);
            p2.up.setBounds(GUI.gameFrame.getWidth()-400, GUI.gameFrame.getHeight()-400, 200, 200);
            GUI.panel.add(p1.up);
            GUI.panel.add(p2.up);           
            game.gameMap=map;
            GUI.buttonLoader();
//            
        }
    }); 
    ImageIcon create = bil.getIconCreateUnit();
        b2 = new JButton(create) ;
        b2.addActionListener(new ActionListener() {
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
        ImageIcon map = bil.getIconCreateMap();
        b3 = new JButton(map);
        b3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            removePanels();
           
            
            GUI.terrainPlayerActive= true;
            Map map = new Map(10);
          //  map.generateBasic();
            GUI.terrainPlacer();
           
            
                     
           
           // GUI.buttonLoader();
//needs a lot of fiddling
            
//         Map  gameMap = MapCreator.createMap();
        }
    });
      ImageIcon scenario = bil.getIconCreateScenario();
       b4 = new JButton (scenario);
       b4.addActionListener(new ActionListener() {
       
       @Override
       public void actionPerformed ( ActionEvent e )
       {
           removePanels();
           Scenario scenario = new Scenario();
       }});
       ImageIcon load = bil.getIconLoadGame();
       b5 = new JButton(load);
       b5.addActionListener(new ActionListener() { 
        @Override
        //This refers to when the loadGame button is clicked 
       public void actionPerformed( ActionEvent e ) 
        {
            demo();

        }

            public void demo() {
                //load the game, testButton for now
                
                
                Game game = new Game();
//                Map map = new Map(10, "agincourt");
                
                Map m = new Map(10);
                m.generateLoaded();
                GUI.gameMap = m;
                
            // p1.up.setUpButtons();
           //  p2.up.setUpButtons();
                
//               p2.up.setBounds(200, GUI.gameFrame.getHeight()-400, 200, 200);
//               p1.up.setBounds(GUI.gameFrame.getWidth()-400, GUI.gameFrame.getHeight()-400, 200, 200);
//                GUI.panel.add(p2.up);
                //GUI.panel.add(p1.up);
                
//                game.gameMap=map;
                GUI.buttonLoader();
                
                
                
                //remove all panels
                removePanels();
            }
    });
    }
    public Openingmenuscreen(){       
       initFrame();
     setFullScreen();
     
       
      
       initTilePanel();
    
      initButtonPanel();

       initWelcomePanel();
       gameFrame.add(tilePanel);
       gameFrame.setVisible(true);
//       welcomePanel.revalidate();

       GUI.gameFrame=gameFrame;
//       ScenarioGUI.gameFrame=gameFrame;
   }

   //sets to fullscreen mode, more a hinderence atm but good for final product

    public void setFullScreen(){
      gameFrame.setUndecorated(true);
      gameFrame.setResizable(false);
  }
    private void initFrame(){
       this.gameFrame= new JFrame("Historical Battle Simulator");
       
       gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE); //program stops if frame is close
       
       //sets size of gameFrame to that of your screen
       gameFrame.setSize(new Dimension((int)Toolkit.
                getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
       
//        System.out.println("gameFrame width = "+gameFrame.getPreferredSize()
//                .getWidth()+" gameFrame Height = "+ gameFrame.
//                        getPreferredSize().getHeight());
//       System.out.println(gameFrame.getSize());
    }
    public static void setTransparent(JButton b){
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        
    }
    private void initButtonPanel() {
         initButtonImages();
         b1.setVisible(true);
         setTransparent(b1);
         b2.setVisible(true);
         setTransparent(b2);
         b3.setVisible(true);
         setTransparent(b3);
         b4.setVisible(true);
         setTransparent(b4);
         b5.setVisible(true);
         setTransparent(b5);
        buttonPanel = new JPanel(new GridLayout(5,1));
        buttonPanel.setOpaque(false);
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
    }    
    private void initTilePanel(){
        tilePanel = new JPanel(new GridBagLayout());
       // BufferedImageLoaders bil = new BufferedImageLoaders();
        bil.loadBackground();
        Image background = bil.getBackground();
        tilePanel = new IPanel(background);
        tilePanel.setBounds(0,0,gameFrame.getWidth(),200);
        
        
       
    }
    public  void removePanels(){
       gameFrame.remove(welcomePanel);
       tilePanel.remove(buttonPanel);
       gameFrame.remove(tilePanel);
   }
     public void replacePanels(){
       gameFrame.add(welcomePanel);
       tilePanel.add(buttonPanel);
       gameFrame.add(tilePanel);
   }
    private void initWelcomePanel() {
       bil.loadTopScreen();
       Image topImage = bil.getTopScreen();
       welcomePanel = new IPanel(topImage);
       welcomePanel.setBounds(0,0,gameFrame.getWidth(),200);
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.anchor = GridBagConstraints.BASELINE;
       gbc.weighty=0;
       welcomePanel.add(buttonPanel,gbc);
       gameFrame.add(welcomePanel);

    }
  
}
