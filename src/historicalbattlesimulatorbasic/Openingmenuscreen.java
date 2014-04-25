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
            GUI.scenario=null;
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
            removePanels();
           
            

            Map map = new Map(10);
            map.generateBasic();
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
                
                GUI.scenario=null;
                Game game = new Game();
                Map map = new Map(10);
                
                
                ArrayList <Unit> frenchUnits = new ArrayList();
               
                //Greek Units
//                String name = "French Forces";
//                    //Spartan Hoplites
//                UnitLoader ul = new UnitLoader();
//                ArrayList<Unit> frenchUnitGeter = ul.loadAllUnits("FrenchAgincourt.txt");
//                
////                Unit leonidas = new Unit(greekUnitGetter.get(0).soldierType.clone(),greekUnitGetter.get(0).unitSize);
////                leonidas.setSprite("orangeGeneral.png");
//                //greekUnits.add(leonidas);
////                    UnitDraw leo = new UnitDraw(leonidas, new Tile(1120, 260,10,10));
////                                                             GUI.unitDraws.add(leo);
////                                                             greekUnits.add(leonidas);
//                Unit frenchKnights1 =  new Unit(frenchUnitGeter.get(1).soldierType.clone(),frenchUnitGeter.get(1).unitSize);
//                frenchKnights1.setSprite("blueCavalry.png");
//                frenchKnights1.setPosition(210, 1120);
//                                                             frenchUnits.add(frenchKnights1);
//                                                             UnitDraw h5 = new UnitDraw(frenchKnights1, new Tile(210,1120,10,10));
//                                                             GUI.unitDraws.add(h5);
//                
//                Unit frenchKnights2 =  new Unit(frenchUnitGeter.get(1).soldierType.clone(),frenchUnitGeter.get(1).unitSize);
//                frenchKnights2.setSprite("blueCavalry.png");
//                frenchKnights2.setPosition(1090, 270);
//                UnitDraw h2 = new UnitDraw(frenchKnights2, new Tile(1090, 270,10,10));
//                                                             GUI.unitDraws.add(h2);
//                                                             frenchUnits.add(frenchKnights2);
//                Unit frenchKnight3 =  new Unit(frenchUnitGeter.get(1).soldierType.clone(),frenchUnitGeter.get(1).unitSize);
//                
//                frenchKnight3.setSprite("orangeSpear.png");
//                frenchKnight3.setPosition(1200, 340);   
//                UnitDraw h3 = new UnitDraw(frenchKnight3, new Tile(1200, 340,10,10));
//                                                             GUI.unitDraws.add(h3);
//             frenchUnits.add(frenchKnight3);
//                   
//                Unit thebanSlinger = new Unit(frenchUnitGeter.get(2).soldierType.clone(), frenchUnitGeter.get(2).unitSize);
//                thebanSlinger.setSprite("redRange.png");
//                thebanSlinger.setPosition(1480,310);
//                UnitDraw t1 = new UnitDraw(thebanSlinger, new Tile(1480, 310,10,10));
//                frenchUnits.add(thebanSlinger);
//                                                             GUI.unitDraws.add(t1);
//             //   thebanSlinger.setFormation(COLUMN);
//              
//                
//                Unit greekMilitias1 = new Unit(frenchUnitGeter.get(3).soldierType.clone(), frenchUnitGeter.get(3).unitSize);
//                Unit greekMilitias2 =new Unit(frenchUnitGeter.get(3).soldierType.clone(), frenchUnitGeter.get(3).unitSize);      
//                    greekMilitias1.setSprite("redSpear.png");
//                    greekMilitias1.setPosition(1390, 450);
//                    greekMilitias2.setSprite("redSpear.png");
//                    greekMilitias2.setPosition(1550, 200);
//                     UnitDraw g1 = new UnitDraw(greekMilitias1, new Tile(1390, 450,10,10));
//                                                             GUI.unitDraws.add(g1);
//                                                             frenchUnits.add(greekMilitias1);
//                     UnitDraw g2 = new UnitDraw(greekMilitias2, new Tile(1550, 200,10,10));
//                                                             GUI.unitDraws.add(g2);       frenchUnits.add(greekMilitias2);                                 
//                   
                
                //persian units
                ArrayList <Unit> persianUnits = new ArrayList();
                String name2="Persian";
                UnitLoader ul2 = new UnitLoader();
                ArrayList<Unit> persianUnitList = ul2.loadAllUnits("PersianEmpire.txt");
        
                Unit infantry1 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry2 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry3 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry4 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry5 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry6 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry7 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry8 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry9 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry10 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry11 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                Unit infantry12 = new Unit(persianUnitList.get(0).soldierType.clone(),persianUnitList.get(0).unitSize*2);
                                                            
                                                             infantry1.setSprite("greyMelee.png");
                                                             infantry2.setSprite("greyMelee.png");
                                                             infantry3.setSprite("greyMelee.png");
                                                             infantry4.setSprite("greyMelee.png");
                                                             infantry5.setSprite("greyMelee.png");
                                                             infantry6.setSprite("greyMelee.png");
                                                             infantry7.setSprite("greyMelee.png");
                                                             infantry8.setSprite("greyMelee.png");
                                                             infantry9.setSprite("greyMelee.png");
                                                             infantry10.setSprite("greyMelee.png");
                                                             infantry11.setSprite("greyMelee.png");
                                                             infantry12.setSprite("greyMelee.png");
                                                             
                                                             infantry1.setPosition(400,200);
                                                             infantry2.setPosition(400,250);
                                                             infantry3.setPosition(400,300);
                                                             infantry4.setPosition(450, 370);
                                                             infantry5.setPosition(440, 430);
                                                             infantry6.setPosition(440, 480);
                                                             infantry7.setPosition(150, 210);
                                                             infantry8.setPosition(180, 490);
                                                             
                                                             UnitDraw in1 = new UnitDraw(infantry1, new Tile(400,200,10,10));
                                                             GUI.unitDraws.add(in1);
                                                             UnitDraw in2 = new UnitDraw(infantry2,new Tile(400,250,10,10));
                                                             GUI.unitDraws.add(in2);
                                                             UnitDraw in3 = new UnitDraw(infantry3,new Tile(400,300,10,10));
                                                             GUI.unitDraws.add(in3);
                                                             UnitDraw in4 = new UnitDraw(infantry4, new Tile(450,370,10,10));
                                                             GUI.unitDraws.add(in4);
                                                             UnitDraw in5 = new UnitDraw(infantry5, new Tile(440,430,10,10));
                                                             GUI.unitDraws.add(in5);
                                                             UnitDraw in6 = new UnitDraw(infantry6, new Tile(440,480,10,10));
                                                             GUI.unitDraws.add(in6);
                                                             UnitDraw in7 = new UnitDraw(infantry7, new Tile(150,210,10,10));
                                                             GUI.unitDraws.add(in7);
                                                             UnitDraw in8 = new UnitDraw(infantry8, new Tile(180,490,10,10));
                                                             GUI.unitDraws.add(in8);
                                persianUnits.add(infantry1);
                                persianUnits.add(infantry2);
                                persianUnits.add(infantry3);
                                persianUnits.add(infantry4);
                                persianUnits.add(infantry5);
                                persianUnits.add(infantry6);
                                persianUnits.add(infantry7);
                                persianUnits.add(infantry8);
                                                             
                Unit immortals1 = new Unit(persianUnitList.get(1).soldierType.clone(),persianUnitList.get(1).unitSize*2);
                Unit immortals2 = new Unit(persianUnitList.get(1).soldierType.clone(),persianUnitList.get(1).unitSize*2);
                Unit immortals3 = new Unit(persianUnitList.get(1).soldierType.clone(),persianUnitList.get(1).unitSize*2);
                Unit immortals4 = new Unit(persianUnitList.get(1).soldierType.clone(),persianUnitList.get(1).unitSize*2);
               // Unit immortals1 = new Unit(persianUnitList.get(1).clone(),persianUnitList.get(1).unitSize);
                                
                immortals1.setSprite("greyGeneral.png");
                immortals2.setSprite("greyGeneral.png");
                immortals3.setSprite("greyGeneral.png");
                immortals4.setSprite("greyGeneral.png");
                                persianUnits.add(immortals1);
                                persianUnits.add(immortals2);
                                persianUnits.add(immortals3);
                                persianUnits.add(immortals4);
                                
                                
                    
              Unit archer1 = new Unit(persianUnitList.get(2).soldierType.clone(),persianUnitList.get(2).unitSize*2);
              Unit archer2 = new Unit(persianUnitList.get(2).soldierType.clone(),persianUnitList.get(2).unitSize*2);
              Unit archer3 = new Unit(persianUnitList.get(2).soldierType.clone(),persianUnitList.get(2).unitSize*2);
              archer1.setSprite("greyRange.png");
              archer2.setSprite("greyRange.png");
              archer3.setSprite("greyRange.png");
                            persianUnits.add(archer1);
                            persianUnits.add(archer2);
                            persianUnits.add(archer3);
             
                
                
                
                Player p1 = new Player("ed",frenchUnits);
               

                Player p2 = new Player(name2,persianUnits);
                
                
                p1.myTurn=true;
                p2.myTurn=false;
           //  p2.myTurn=false;
                Game.playersForDemo.add(p1);
                Game.playersForDemo.add(p2);
                
            // p1.up.setUpButtons();
           //  p2.up.setUpButtons();
                
//               p2.up.setBounds(200, GUI.gameFrame.getHeight()-400, 200, 200);
//               p1.up.setBounds(GUI.gameFrame.getWidth()-400, GUI.gameFrame.getHeight()-400, 200, 200);
//                GUI.panel.add(p2.up);
                //GUI.panel.add(p1.up);
                
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
       ScenarioGUI.gameFrame=gameFrame;
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
