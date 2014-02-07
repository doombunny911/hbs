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
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;



/**
 *
 * @author Andrew
 */
public class Openingmenuscreen extends JFrame implements ActionListener
{
    static JFrame gameFrame;
    static JPanel tilePanel;
    JPanel buttonPanel;
    static GUI gui;
    JButton b1 = new JButton( new AbstractAction("Run Simulation") {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
//           
            Game simulation = new Game(); 
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter number of players: ");
            int numPlayers = scan.nextInt();
            simulation.setUp(numPlayers);
            simulation.playGame();
        }
    });

    JButton b2 = new JButton( new AbstractAction("Create Unit") { 
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            System.out.println("test 2");
//            UnitCreator.createUnit();
//           
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
        public void actionPerformed( ActionEvent e ) 
        {
            //load the game, testButton for now
            tilePanel.remove(buttonPanel);
            Map gameMap=new Map(10);
            //gameMap.populateMap();
            gui = new GUI(gameFrame,gameMap,gameMap.gameMap,gameMap.tileWidth);
            gui.checkInitalization();
            gui.repainter();
            //only for now
            gameFrame.remove(tilePanel);
            //an array of units is loaded into memory via textfile and are stored
            //than drawn from the array to be drawn
//            int x,y,height,width;
//            x=0;y=0;height=0;width=0;
            GUI.gameFrame.add(new UnitDraw());
            gui.repainter();
            
            

        }
    });

   public Openingmenuscreen()
   {
//       b1.addActionListener(this);
//       GUI gui = new GUI();
       gameFrame= new JFrame("Historical Battle Simulator");
       
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setSize(new Dimension((int)Toolkit.
                getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        System.out.println("gameFrame width = "+gameFrame.getPreferredSize()
                .getWidth()+" gameFrame Height = "+ gameFrame.
                        getPreferredSize().getHeight());
       System.out.println(gameFrame.getSize());

//        gameFrame.setBackground(Color.yellow);
//        GridLayout gLayout= new GridLayout();
        buttonPanel = new JPanel(new GridLayout(4,1));
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.setBackground(Color.red);
//        JPanel 
        tilePanel = new JPanel(new GridBagLayout());
        tilePanel.setBackground(Color.orange);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.BASELINE;
        gbc.weighty=0;
        
       
        tilePanel.add(buttonPanel,gbc);
//         frame.add(overallPanel);
        gameFrame.add(tilePanel);


        
        gameFrame.setVisible(true);
//        gui.gameFrame=this.gameFrame;
   }
  
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
    public static GUI getGUI()
    {
        return gui;
    }
    
    
    
}
