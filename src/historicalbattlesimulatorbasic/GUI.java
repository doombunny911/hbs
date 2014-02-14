/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Andrew
 */
//the overall animation class of the project, every class regarding animation
//should go through this in some degree
//will be used for mouse listening too
public class GUI implements MouseListener
{
    static JFrame gameFrame;
    Map gameMap;
    static Tile[][] tileGameMap;
    static int tileWidth;
    static JPanel panel;
   public GUI(JFrame gameFrame,Map gameMap,Tile[][] tileGameMap,int tileWidth)
   {
       GUI.gameFrame=gameFrame;
       this.gameMap=gameMap;
       GUI.tileGameMap=tileGameMap;
       GUI.tileWidth = tileWidth;
       GUI.panel.addMouseListener(this);
       MouseEvent me = new MouseEvent(panel,0,0,0,0,0,0,true,0);
     
       
   }
   public void addToFrame(Component c)
   {
       gameFrame.add(c);
//       this.repainter();
   }
   public static void panelInitializer(JPanel panel)
   {
     
   }
   
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
    @Override
    public void mousePressed(MouseEvent me) 
    {
        double findTileX= Math.ceil(me.getX()/10);
        double findTileY=Math.ceil(me.getY()/10);
        
        //this is the tile that was clicked on
        Tile tile=GUI.tileGameMap[(int)findTileX][(int)findTileY];
    }
   
   
    @Override
    public void mouseClicked(MouseEvent me) 
    {
    }

   
    @Override
    public void mouseReleased(MouseEvent me) 
    {
    }

    @Override
    public void mouseEntered(MouseEvent me) 
    {
    }

    @Override
    public void mouseExited(MouseEvent me)
    {
    }
}
