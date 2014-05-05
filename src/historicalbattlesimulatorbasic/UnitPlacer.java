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
public final class UnitPlacer extends JPanel
{ 
    ArrayList<JButton> unitImages = new ArrayList();
    int numOfUnitsToPlace;
//    ArrayList<Unit> units = new ArrayList<>();
    Unit unitToBeLoaded;
    ArrayList<Unit> unitArrayList;
    int index =0;
    static boolean check = false;
    String player = "";
    int playerNum =0;
    
   public static void main(String[] args){
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
   public void setPlayer(String playerName, int playerNum){
       this.player = playerName;
   }
   public void setObedience()
   {
       for(Unit u: unitArrayList)
       {
           u.setPlayerName(this.player);
           u.setPlayerNum(this.playerNum);
       }
   }
   public UnitPlacer(String name, int playerNum)
   {
       this.player=name;
       this.playerNum = playerNum;
       UnitLoader ul = new UnitLoader();
       this.unitArrayList = ul.runLoader();
       this.numOfUnitsToPlace = this.unitArrayList.size();
       //System.out.println("the number in unitPlacer = " + numOfUnitsToPlace);
       this.setObedience();
       this.setOpaque(false);
        
   }
   public UnitPlacer(String name, ArrayList <Unit> u)
   {
       this.player=name;
       
       this.unitArrayList=u;
       this.numOfUnitsToPlace=u.size();
       this.setOpaque(false);
       this.setUpButtons();
       this.setObedience();
   }
   public ArrayList<Unit> getUnitList(){
       return this.unitArrayList;
   }
   public UnitPlacer(ArrayList<Unit> unitArrayList){
       this.numOfUnitsToPlace = unitArrayList.size();
//       setUpButtons(unitArrayList);
       setOpaque(false);
   }
   public String getTitle(){
       return this.player;
   }
   boolean allDone = false;
   public void setUpButtons(){
        
         final JLabel title = new JLabel(this.player+" 's Units");
        
        
//       final Unit u = unitArrayList.get(index);
         add(title);
        
         int indx= 0;
          for(final Unit u: this.unitArrayList)
           {      
               indx++;
            ImageIcon unitImage = new ImageIcon(u.getUnitPic(u));
            Image img = unitImage.getImage();
            Image newimg = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon unitImage2 = new ImageIcon(newimg);
            final JButton button = new JButton(u.nameOfUnit,unitImage2) ;
//            unitImages.add(button);
            
            
            button.setOpaque(false);
            index++;
            add(button);
                button.addActionListener(new ActionListener() 
                {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  if(UnitPlacer.check)
                  {
                      //nothing
                  }
                  else
                  {
                     UnitPlacer.check=true;
                     System.out.println(u.nameOfUnit);
                     button.setVisible(false);
                     numOfUnitsToPlace--;
                     unitToBeLoaded=u;
                 
                     title.setVisible(false);
                   //  System.out.println("in unitPlacer, the speed of this unit is " + u.speed);
                   //  System.out.println("in unitPlacer, the range of this unit is "+ u.range);
                  }
                }});
//                if(indx ==this.unitArrayList.size())
//                {
//                    allDone = true;
//                }
            button.setBorderPainted(false);
           
         }
    }
  
}
