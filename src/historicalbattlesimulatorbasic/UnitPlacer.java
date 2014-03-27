/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Edward
 */
public final class UnitPlacer extends JPanel
{ 
    ArrayList<JButton> unitImages = new ArrayList();
    int numOfUnitsToPlace = 0;
    ArrayList<Unit> units = new ArrayList<>();
    Unit unitToBeLoaded;
    ArrayList<Unit> unitArrayList;
    int index =0;
    boolean check = false;
    String player = "";
    public static void main(String[] args)
    {
        UnitLoader ul = new UnitLoader();
        ArrayList units = ul.runLoader();
        
        UnitPlacer up = new UnitPlacer(units); 
        
        
        JFrame newJ = new JFrame();
        
        newJ.setVisible(true);
        newJ.setSize(500,200);
        newJ.add(up);
        newJ.repaint();
        newJ.revalidate();
    }
   
   public void setPlayer(String playerName)
   {
       this.player = playerName;
   }
   public UnitPlacer()
   {
       UnitLoader ul = new UnitLoader();
       this.unitArrayList = ul.runLoader();
       this.numOfUnitsToPlace = this.unitArrayList.size();
         setOpaque(false);
        
   }
   public ArrayList<Unit> getUnitList()
   {
       return this.unitArrayList;
   }
   public UnitPlacer(ArrayList<Unit> unitArrayList)
   {
       this.numOfUnitsToPlace = unitArrayList.size();
//       setUpButtons(unitArrayList);
       setOpaque(false);
   }
   public String getTitle()
   {
       return this.player;
   }
    public void setUpButtons(ArrayList<Unit> unitArrayList)
    {
        
         final JButton title = new JButton(this.player+" 's Units");
          add(title);
         title.addActionListener(new ActionListener() 
                {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                      title.setVisible(false);
                      //nothing
                  
                }
                });
//       final Unit u = unitArrayList.get(index);
         add(title);
          for(final Unit u: unitArrayList)
           {      
//               System.out.println("the id of the unit in the unitPlacer "
//                        + u.getUnitID() + " "  + u.unitID);

           //  public UnitFormations unitFormer = new UnitFormations(u);
            ImageIcon unitImage = new ImageIcon(Unit.getUnitPic(u));
            Image img = unitImage.getImage();
            Image newimg = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon unitImage2 = new ImageIcon(newimg);
            final JButton button = new JButton(u.nameOfUnit,unitImage2) ;
//            unitImages.add(button);
            
            
            button.setOpaque(false);
            index++;
            numOfUnitsToPlace--;
            add(button);
                button.addActionListener(new ActionListener() 
                {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  if(check)
                  {
                      //nothing
                  }
                  else
                  {
                     check=true;
//                     System.out.println(u.nameOfUnit);
                     button.setVisible(false);
                    
                     unitToBeLoaded=u;
                     
                     System.out.println("in unitPlacer, the speed of this unit is " + u.speed);
                  }
                    
                    
                        
                    
                }});
            button.setBorderPainted(false);
         }
   
    }
  
    public void setUpButtons()
    {
        
         final JButton title = new JButton(this.player+" 's Units");
          add(title);
         title.addActionListener(new ActionListener() 
                {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                      title.setVisible(false);
                      //nothing
                  
                }
                });
//       final Unit u = unitArrayList.get(index);
         add(title);
          for(final Unit u: this.unitArrayList)
           {      
//               System.out.println("the id of the unit in the unitPlacer "
//                        + u.getUnitID() + " "  + u.unitID);

           //  public UnitFormations unitFormer = new UnitFormations(u);
            ImageIcon unitImage = new ImageIcon(Unit.getUnitPic(u));
            Image img = unitImage.getImage();
            Image newimg = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon unitImage2 = new ImageIcon(newimg);
            final JButton button = new JButton(u.nameOfUnit,unitImage2) ;
//            unitImages.add(button);
            
            
            button.setOpaque(false);
            index++;
            numOfUnitsToPlace--;
            add(button);
                button.addActionListener(new ActionListener() 
                {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  if(check)
                  {
                      //nothing
                  }
                  else
                  {
                     check=true;
                     System.out.println(u.nameOfUnit);
                     button.setVisible(false);
                    
                     unitToBeLoaded=u;
                     
                     System.out.println("in unitPlacer, the speed of this unit is " + u.speed);
                  }
                    
                    
                        
                    
                }});
            button.setBorderPainted(false);
         }
   
    }
  
}
