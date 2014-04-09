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
public final class Openingmenuscreen extends JFrame
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
           Scenario scenario = ScenarioCreator.createScenario();
       }});
       ImageIcon load = bil.getIconLoadGame();
       b5 = new JButton(load);
       b5.addActionListener(new ActionListener() { 
        @Override
        //This refers to when the loadGame button is clicked 
       public void actionPerformed( ActionEvent e ) 
        {
            thermopylae();

        }

            public void thermopylae() {
                //load the game, testButton for now
                
                
                Game game = new Game();
                Map map = new Map(10);
                
                
                ArrayList <Unit> greekUnits = new ArrayList();
               
                //Greek Units
                    //Spartan Hoplites
                    Soldier spartanHoplite = new Soldier("Spartan Hoplite 1",1,3,2,4,5,7,3,4,2,2,5,4);
                    Soldier spartanHoplite2 = new Soldier("Spartan Hoplite 2",1,3,2,4,5,7,3,4,2,2,5,4);
                    Soldier spartanHoplite3 = new Soldier("Spartan Hoplite 3",1,3,2,4,5,7,3,4,2,2,5,4);
                    Soldier averageGreekHoplite = new Soldier("Greek Militia Hoplite 1",1,1,2,2,2,4,1,2,1,1,2,4);
                    Soldier averageGreekHoplite2 = new Soldier("Greek Militia Hoplite 2",1,1,2,2,2,4,1,2,1,1,2,4);
                    Soldier leon = new Soldier("King Leonidas",100,300,200,400,500,700,300,400,1,1,500,400);
                    
                    
                    String name = "Greek Forces";
                    Unit spartanHopliteUnit = new Unit(spartanHoplite,100);
                   // spartanHopliteUnit.setPosition(GUI.panel.getWidth()/10, GUI.panel.getHeight()/10);
                    //Tile t = new Tile(GUI.panel.getWidth()/10, GUI.panel.getHeight()/10, GUI.tileWidth, GUI.tileWidth);
                    spartanHopliteUnit.setSprite("greekYellow.png");
                    //UnitDraw spartanDraw = new UnitDraw(spartanHopliteUnit, t);
                    //spartanDraw.
                  //  GUI.unitDraws.add(spartanDraw);
                    //GUI.repainter();
                        greekUnits.add(spartanHopliteUnit);
                    Unit spartanHopliteUnit2 = new Unit(spartanHoplite2,100);
                    spartanHopliteUnit2.setSprite("greekYellow.png");
                        greekUnits.add(spartanHopliteUnit2);
                    Unit spartanHopliteUnit3 = new Unit(spartanHoplite3,100);
                        greekUnits.add(spartanHopliteUnit3);
                    spartanHopliteUnit3.setSprite("greekYellow.png");
                    Unit greekMilitias = new Unit(averageGreekHoplite,150);
                      
                    greekMilitias.setSprite("greekGray.png");
                      greekUnits.add(greekMilitias);
                     
                    Unit greekMilitias2 = new Unit(averageGreekHoplite,150);
                    greekMilitias2.setSprite("greekGray.png");
                    greekUnits.add(greekMilitias2);
                    Unit leonidas = new Unit(leon,1);
                    leonidas.setSprite("greekRed.png");
                      greekUnits.add(leonidas);
                
                
                
                //persian units
                ArrayList <Unit> persianUnits = new ArrayList();
                String name2="Persian";
                
                //Soldiers
                Soldier immortals = new Soldier("Immortals",
                        1,
                        1,
                        2,
                        4,
                        3,
                        2,
                        1,
                        6,
                        3,
                        1,
                        6,
                        5);
               
                
                Soldier persianArcher= new Soldier("Archers",2,2,1,1,25,1,1,1,2,2,5,5);
                //immortals
                 Unit immortalsUnit = new Unit(immortals,500);
                 
               //bowmen
               Unit persianArcherUnit = new Unit(persianArcher, 50);
                 persianArcherUnit.setSprite("archerGray.png");
                  
                //xerxes
                
               persianUnits.add(immortalsUnit);
               persianUnits.add(persianArcherUnit);
                
                
             
                
                
                
                Player p1 = new Player(name,greekUnits);
               

                Player p2 = new Player(name2,persianUnits);
                
                
                p1.myTurn=true;
                p2.myTurn=false;
           //  p2.myTurn=false;
                Game.playersForDemo.add(p1);
                Game.playersForDemo.add(p2);
                
             // p1.up.setUpButtons();
             // p2.up.setUpButtons();
                
               p2.up.setBounds(200, GUI.gameFrame.getHeight()-400, 200, 200);
               p1.up.setBounds(GUI.gameFrame.getWidth()-400, GUI.gameFrame.getHeight()-400, 200, 200);
                GUI.panel.add(p2.up);
                GUI.panel.add(p1.up);
                
                game.gameMap=map;
                GUI.buttonLoader();
                
                
                
                //remove all panels
                removePanels();
            }
    });
    }
    public Openingmenuscreen(){       
       initFrame();
//     setFullScreen();
     
       
      
       initTilePanel();
    
      initButtonPanel();

       initWelcomePanel();
       gameFrame.add(tilePanel);
       gameFrame.setVisible(true);
//       welcomePanel.revalidate();

       GUI.gameFrame=gameFrame;
   }

   //sets to fullscreen mode, more a hinderence atm but good for final product

    public void setFullScreen(){
      gameFrame.setUndecorated(true);
      gameFrame.setResizable(false);
  }
    private void initFrame(){
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
    private void removePanels(){
       gameFrame.remove(welcomePanel);
       tilePanel.remove(buttonPanel);
       gameFrame.remove(tilePanel);
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
