/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Schmalz
 */
public class Game 
{

  
    Frame frame = JOptionPane.getRootFrame();    

    Player players[];
    Map gameMap;
    boolean hasWinner=false;
    Player winner=null;

    public static ArrayList<Player> playersForDemo;
    static int numOfPlayers; //number of players playing

//    Scanner scan = new Scanner(System.in);

    

public static void main(String[] args)
{
   
Game game = new Game();
Player p1 = new Player();
p1.setPlayerName("Player One");
Player p2 = new Player();
p2.setPlayerName("Player Two");
playersForDemo.add(p1);
playersForDemo.add(p2);
JOptionPane.showMessageDialog(null,"Player 1 choose your army:");
UnitLoader ul1 = new UnitLoader();

UnitLoader ul2 = new UnitLoader();
GUI.player1UnitNum=ul1.getAllUnits().size();
GUI.player2UnitNum=ul2.getAllUnits().size();
GUI.player1AllUnits=ul1.getAllUnits();
GUI.player2AllUnits=ul2.getAllUnits();
p1.allUnits = ul1.runLoader();



p2.allUnits = ul2.runLoader();

game.setUp(p1);
JOptionPane.showMessageDialog(null,"Player 2 choose your army:");
game.setUp(p2);

p2.allUnits = ul1.runLoader();

p2.allUnits = ul1.runLoader();


  //game begin
 game.playGame(playersForDemo);
 }



 public void playGame(ArrayList<Player> playersForDemo)
{
   boolean hasWinner = false;
   while(!hasWinner)
   {
       for(Player p : playersForDemo)
       {
           JOptionPane.showMessageDialog(null,"Player "+p.playerName+"'s turn.");
           hasWinner = takeTurn(p);
       }
   }
}
//sets how many people are playing

public void setNumPlayers(int numOfPlayers)
{
    Game.numOfPlayers=numOfPlayers;
    
}
public static ArrayList playerCreator(int numPlay)
    {
     
       ArrayList<Player> players = new ArrayList();     
       
      
       for(int i=0; i<numPlay; i++)
           
        {           
            Player.singlePlayerDialog(players);   
        }
        return players;
    }


    
    //need win conditions
   //if player surrenders or out of units


    private boolean takeTurn(Player p)
    {
      hasWinner =  p.takeTurn();
      return hasWinner;
           }

    private void setUp(Player p1) 
    {
       JOptionPane.showMessageDialog(null, p1.playerName+" place your units!");
    }
}
