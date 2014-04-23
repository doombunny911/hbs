/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import static historicalbattlesimulatorbasic.GUI.statPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Edward
 */
public class CombatPanel extends JPanel
{
    static Unit enemyUnitFound;
    Unit unitSelected;
    Player enemyPlayer;
    ArrayList<Unit> enemyUnits;
    boolean visible = true;
    ArrayList<JButton> buttons = new ArrayList<>();
   
    //get all units in range
    public static void main(String[] args){
        JFrame newJ = new JFrame();
        CombatPanel cp = new CombatPanel();
        cp.initCPanel();
        newJ.setVisible(true);
        newJ.setSize(500,200);
        newJ.add(cp);
        newJ.repaint();
        newJ.revalidate();
     }
  
    public void initCPanel() {
        
        this.setBackground(Color.BLACK);
        javax.swing.border.Border borderUsed = BorderFactory.createLineBorder(Color.white);
        this.setBorder(borderUsed);
        getAllUnitsOfEnemy();
        System.out.println(enemyPlayer.nameOfArmy);
        enemyPlayer = getEnemy();
        if(GUI.unitSelected.anyInRange(enemyPlayer))
        {
        enemyUnits = GUI.unitSelected.getAllInRange(enemyPlayer);
        }
        else
        {
            JOptionPane.showMessageDialog(GUI.panel, "No units are in range. Select another option");
            GUI.toggleButtons(GUI.buttonPanel, visible);
            visible = false;
            GUI.unitSelected.addUnitPoint();
            
        }
        setUpButtons();
        
    }
    public void getAllUnitsOfEnemy() {
        unitSelected = GUI.unitSelected;
        for(Player p: Game.playersForDemo)
        {
          
            if(!p.myTurn)
            {
            
                enemyPlayer = p;
            }
            else
            {
                
            }
            
        }
        
        enemyUnits = enemyPlayer.allUnits;
//        enemyUnits = unitSelected.getAllInRange(enemyPlayer);
    }
    
    public Player getEnemy(){
        for(Player p: Game.playersForDemo)
        {
          
            if(!p.myTurn)
            {
            
                enemyPlayer = p;
            }
            else
            {
                
            }
            
        }
        return enemyPlayer;
    }
    //Generate buttons with stats on them as well
    public void setUpButtons(){
        setLayout(new GridLayout(5,1));
        Image imgx = GUI.unitSelected.getUnitPic(GUI.unitSelected);
        Image newimgx = imgx.getScaledInstance(10, 10,  java.awt.Image.SCALE_SMOOTH); 
        ImageIcon imageOfUnit = new ImageIcon(newimgx);

        final JLabel title0 = new JLabel("<html><h4><b><center><font color = 'red' face='Times New Roman'> Combat</b><br></font></center></h4></html>", JLabel.CENTER) ;
        final JLabel title1 = new JLabel(unitSelected.nameOfUnit,imageOfUnit, JLabel.CENTER);
      //   title.setBackground(Color.white);
        final JLabel title2 = new JLabel("<html> <font color = 'white' face='Times New Roman'"
                + "> Click on your target! If none are present, hit cancel</b></font></html>");
        add(title0);
        add(title1);
        add(title2);
         
        for(final Unit u: this.enemyUnits)
        {      
              ImageIcon unitImage = new ImageIcon(u.getUnitPic(u));
              System.out.println(u.nameOfUnit);
              Image img = unitImage.getImage();
              Image newimg = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
              ImageIcon unitImage2 = new ImageIcon(newimg);
              final JButton button = new JButton((u.nameOfUnit),unitImage2) ;
              buttons.add(button);
//            unitImages.add(button);
            
            
           // button.setOpaque(false);
           // button.setContentAreaFilled(false);
              button.setBorderPainted(true);
              button.setBackground(Color.black);
              button.setForeground(Color.white);
            // button.setBorderPainted(false);

              add(button);
              button.addActionListener(new ActionListener() 
              {
                 @Override
              public void actionPerformed(ActionEvent e)
              {
                    enemyUnitFound = u;
                    GUI.panel.paintImmediately(GUI.panel.getX(), GUI.panel.getY(), GUI.panel.getWidth(),GUI.panel.getHeight());
                    
                    unitSelected.attack(u,enemyPlayer);
                    
                    
                    
                    visible= false;
                    
                    for(JButton b: buttons)
                    {
                     b.setVisible(false);
                    }
                   // setVisible(false);
                    //subtract one point here as well.
                    if (!GUI.unitSelected.unitDefeat)
                        GUI.toggleButtons(GUI.buttonPanel,true);
                    
                } 
              
            
             });
  
       
       if (!GUI.unitSelected.unitDefeat)
       {
           GUI.toggleButtons(GUI.buttonPanel,true);
       }
      }
        JButton close = new JButton("Close");
        close.setForeground(Color.white);
        close.setBackground(Color.red);
        close.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) //box Formation
        {
           setVisible(false);
        }
       });
           add(close);
    }
}
    

