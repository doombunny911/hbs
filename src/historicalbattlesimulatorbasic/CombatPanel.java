/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import static historicalbattlesimulatorbasic.GUI.statPanel;
import java.awt.Color;
import java.awt.GridLayout;
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
        cp.initPanel();
        newJ.setVisible(true);
        newJ.setSize(500,200);
        newJ.add(cp);
        newJ.repaint();
        newJ.revalidate();
     }
     
    public void initPanel()
    {
        setOpaque(false);
        getAllUnitsOfEnemy();
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
            
        }
        enemyUnits = enemyPlayer.allUnits;
        enemyUnits = unitSelected.getAllInRange(enemyPlayer);
    }
    //Generate buttons with stats on them as well
     public void setUpButtons()
    {
        setLayout(new GridLayout(10,1));
        ImageIcon imageOfUnitSelected = new ImageIcon(GUI.unitSelected.getUnitPic(GUI.unitSelected));
         final JLabel title = new JLabel(unitSelected.nameOfUnit,imageOfUnitSelected, JLabel.CENTER);
        final JLabel title2 = new JLabel("Click on your target! If none are present, hit cancel");
     
          add(title);
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
            
            
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            // button.setBorderPainted(false);

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
                   // setVisible(false);
                    //subtract one point here as well.
                    if (!GUI.unitSelected.unitDefeat)
                        GUI.toggleButtons(GUI.buttonPanel,true);
                    
                } 
              
            
             });
   JButton close = new JButton("Close");
        
        close.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) //box Formation
           {
              setVisible(false);
             
           }
       });
        add(close);
     if (!GUI.unitSelected.unitDefeat)
                    {
                    GUI.toggleButtons(GUI.buttonPanel,true);
                    }
    }
    }
}
    //When button is clicked, run 'attack' [subtract one point]
    //
    

