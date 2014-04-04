/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Edward
 */
public class CombatPanel extends JPanel
{
    Unit unitSelected;
    Player enemyPlayer;
    ArrayList<Unit> enemyUnits;
     boolean visible = true;
     ArrayList<JButton> buttons = new ArrayList<>();
    //get all units in range
     public static void main(String[] args)
     {
         JFrame newJ = new JFrame();
        CombatPanel cp = new CombatPanel();
        newJ.setVisible(true);
        newJ.setSize(500,200);
        newJ.add(cp);
        newJ.repaint();
        newJ.revalidate();
     }
     
    public void initPanel()
    {
        getAllUnitsInRange();
        setUpButtons();
        
    }

    public void getAllUnitsInRange() {
        unitSelected = GUI.unitSelected;
        for(Player p: Game.playersForDemo)
        {
            if(p.myTurn)
            {
                enemyPlayer = p;
            }
            
        }
        enemyUnits = unitSelected.getAllInRange(enemyPlayer);
    }
    //Generate buttons with stats on them as well
     public void setUpButtons()
    {
        
         final JLabel title = new JLabel(unitSelected.nameOfUnit+" can attack:");
          add(title);
          
          for(final Unit u: this.enemyUnits)
           {      
            ImageIcon unitImage = new ImageIcon(Unit.getUnitPic(u));
            Image img = unitImage.getImage();
            Image newimg = img.getScaledInstance(20, 40,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon unitImage2 = new ImageIcon(newimg);
            final JButton button = new JButton((u.nameOfUnit+"Size of Unit"+u.unitSize),unitImage2) ;
            buttons.add(button);
//            unitImages.add(button);
            
            
            button.setOpaque(false);

            add(button);
                button.addActionListener(new ActionListener() 
                {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                    unitSelected.attack(u);
                    visible= false;
                    for(JButton b: buttons)
                    {
                        b.setVisible(false);
                    }
                    //subtract one point here as well.
                }
                });
            
         }
   
    }
  
    //When button is clicked, run 'attack' [subtract one point]
    //
    
}
