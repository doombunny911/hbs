/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Edward
 */
public class PlayerTurnPanel extends JPanel
{
 int turnPlayer;
 ArrayList<Unit> player1Units;
 int p1UnitCount ;
 ArrayList<Unit> player2Units;
 int p2UnitCount ;
 public PlayerTurnPanel(){
   player1Units = GUI.player1AllUnits;
   player2Units = GUI.player2AllUnits;
   initTPanel();
//   p1UnitCount = GUI.unitTally(player1Units);
  // p2UnitCount = GUI.unitTally(player2Units);
 }
 
 public  void initTPanel() {

     this.setOpaque(false);
     
     JLabel text = new JLabel();
     text.setText("<html><h1>Player "+turnPlayer+" 's Turn</h1></html>");
     this.add(text);
     if(turnPlayer==1)
     {
           for(Unit u: player1Units)
            {
              JLabel unitPoints = new JLabel("<html><font face='Times New Roman'>"+u.nameOfUnit+" has <font color='red'><b>"+u.unitPoints+"</b></font> unit points</html>");
              this.add(unitPoints);
              
            }

     }
     else if(turnPlayer==2)
     {
         for(Unit u: player2Units)
         {
             JLabel unitPoints = new JLabel("<html><font face='Times New Roman'>"+u.nameOfUnit+" has <font color='red'><b>"+u.unitPoints+"</b></font> unit points</html>");
              this.add(unitPoints);
         }
     }
//     if(turnPlayer ==1)
//     {
//         this.add(new JLabel("Units Alive: "+p1UnitCount));
//     }
//     else if(turnPlayer ==2)
//     {
//         this.add(new JLabel("Units Alive: "+p2UnitCount));
//     }
//     else if(turnPlayer ==3 )
//     {
//         this.add(new JLabel("Player 1 Wins!"));
//         this.add(new JButton("Back to Home Screen"));    
//     }
//     else if(turnPlayer ==4 )
//     {
//         this.add(new JLabel("Player 2 Wins!"));
//         this.add(new JButton("Back to Home Screen"));    
//     }
     
 }
 public void switchTurn(){
     if( Game.playersForDemo.get(0).myTurn=true)
     {
         turnPlayer = 1;
     }
     if( Game.playersForDemo.get(1).myTurn=true)
     {
         turnPlayer =2;
     }
     if(GUI.player1HasNoUnits())
     {
         turnPlayer = 3; //3 means that player 1 won
     }
     if(GUI.player2HasNoUnits())
     {
         turnPlayer =4; //4 means player 2 won
     }
 }  
}
