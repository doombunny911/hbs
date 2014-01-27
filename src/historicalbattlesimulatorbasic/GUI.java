/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Component;
import javax.swing.JFrame;

/**
 *
 * @author Andrew
 */
//the overall animation class of the project, every class regarding animation
//should go through this in some degree
public class GUI extends JFrame
{
   static JFrame gameFrame;
    Map gameMap;
    Tile[][] tileGameMap;
    static int tileWidth;
   public GUI(JFrame gameFrame,Map gameMap,Tile[][] tileGameMap,int tileWidth)
   {
       this.gameFrame=gameFrame;
       this.gameMap=gameMap;
       this.tileGameMap=tileGameMap;
       this.tileWidth = tileWidth;
       
       
   }
   public GUI()
   {
       //used for future GUI's to set equal to a properally initialized GUI
   }
   public void addToFrame(Component c)
   {
       gameFrame.add(c);
//       this.repainter();
   }
   public void addToFrame(TileMapGenerator gen)
   {
       gameFrame.add(gen);
//       this.repainter();
   }
   public boolean frameNotInitialized()
   {
//       boolean flag;
//       if(gameFrame!=null)
//           flag=false;
//       else
//           flag=true;
//       System.out.println(flag);
       return false;
   }
//    //create frame
//    //call opening menu screen
//
//    private void startUp() 
//    {
//        if(frameNotInitialized())
//       {
//           gameFrame=new JFrame("Historical Battle Simulator");
//           gameFrame.setSize(1000,1000);
//           gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//           gameFrame.pack();
//           gameFrame.setVisible(true);
//       }    
//    }
//    
    public JFrame getFrame(JFrame frame)
    {
        return gameFrame;
    }
   
   public void repainter()
   {
       gameFrame.validate();
       gameFrame.repaint();
   }
   public boolean checkInitalization()
   {
       if(this.gameMap==null||this.tileGameMap==null||this.tileWidth==0)
       {
           return false;
       }
       return true;
   }
}
