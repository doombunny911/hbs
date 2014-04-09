/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JPanel;

/**
 *
 * @author Andrew
 */
//this class initalizes and draws the tiles for the game

public class Painter extends JPanel
{
    private int tileWidth;
  
    static double remainderHeight;
    static double remainderWidth;
    private double sHeight,sWidth;
    int x,y;
    Unit unit;
    public Painter(int widthf){
        this.tileWidth=widthf;
//        tiles=Map.gameMap;
    }
    public Painter(double sWidth,double sHeight){
        
        this.sWidth=sWidth;
        this.sHeight=sHeight;
    }
    
    //don't think this contructor is used
    public Painter(int locationX,int locationY){
        x =locationX;
        y=locationY;
    }
    //untested
    public Painter(Unit unit){
        this.unit=unit;
    }
    /**
     *
     * @param g
     */
   @Override
public void paintComponent(Graphics g) {
//       if(GUI.tileGameMap!=null) 
//             System.out.println(GUI.tileGameMap[0][0].getOccupier());
      Graphics2D g2=(Graphics2D)g;
      //tileMap
      for(int i=0;i<sHeight;i++)
      {
         for(int j=0;j<sWidth;j++)
         {
            
                if(tileHasASpriteFromUnitSelectedat(j, i))
                {
                    Stroke originalStroke = g2.getStroke();
                    g2.setStroke(new BasicStroke(4));
                    g2.draw(GUI.tileGameMap[j][i]);
                    g2.setStroke(originalStroke);
                }
                else if(isEnemyUnitThatIsBeingAttackedFoundAt(j, i))
                {
                    Color originalColor =g2.getColor();
                    Stroke originalStroke = g2.getStroke();
                    g2.setStroke(new BasicStroke(4));
                    g2.setColor(Color.RED);
                    g2.draw(GUI.tileGameMap[j][i]);
                    g2.setStroke(originalStroke);
                    g2.setColor(originalColor);
                }
                else
                {
                    g2.draw(GUI.tileGameMap[j][i]);
                }
                
                 GUI.tileGameMap[j][i].paintTile((g2));  
         
         }
      }
      //sprites
      if(!GUI.unitDraws.isEmpty())
      {
         for(int p=0;p<GUI.unitDraws.size();p++)
         {      
             
             GUI.unitDraws.get(p).thisUnit.currentFormation.paintFormation(g,p);
         }
      }
      //not currently used
//      if(GUI.unitSelected!=null&&GUI.tileClicked!=null&&!GUI.impendingAttack)
//      {
//          GUI.paintRange(GUI.unitSelected,g);
//      }
    }

    protected boolean isEnemyUnitThatIsBeingAttackedFoundAt(int j, int i) {
        return CombatPanel.enemyUnitFound!=null&&GUI.tileGameMap[j][i].isOccupied&& GUI.tileGameMap[j][i].getOccupier().getUnitID()==CombatPanel.enemyUnitFound.getUnitID();
    }

    protected boolean tileHasASpriteFromUnitSelectedat(int j, int i) {
             return GUI.unitSelected!=null&&GUI.tileGameMap[j][i].isOccupied&& GUI.tileGameMap[j][i].getOccupier().getUnitID()==GUI.unitSelected.getUnitID();
    }
 }
   


