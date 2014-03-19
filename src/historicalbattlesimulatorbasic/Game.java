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

<<<<<<< HEAD
=======
<<<<<<< HEAD
    public static ArrayList<Player> playersForDemo;
    static int numOfPlayers; //number of players playing

//    Scanner scan = new Scanner(System.in);
=======
>>>>>>> 3b08d9cb9cef79dd0c402418baa54c37ca98d3fb
    public static ArrayList<Player> playersForDemo = new ArrayList<Player>();
    static int numOfPlayers;
>>>>>>> b377efe660778ed50aa8790ca67c2c05b0270ee5

public static void main(String[] args)
{
<<<<<<< HEAD
=======
<<<<<<< HEAD
    Game game = new Game();
    game.setUp();
    game.playersForDemo = Game.playerCreator(numOfPlayers);
        for (Player p : playersForDemo)
        {
            System.out.println("Player name: "+ p.playerName);
        }
      //game begin
     System.out.println("The game between"+ playersForDemo.get(0)+" and "+playersForDemo.get(1)+" has begun");
     game.playGame(playersForDemo);
  
  
   }
=======
>>>>>>> 3b08d9cb9cef79dd0c402418baa54c37ca98d3fb
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
p1.allUnits = ul1.runLoader();

JOptionPane.showMessageDialog(null,"Player 2 choose your army:");
<<<<<<< HEAD
p2.allUnits = ul2.runLoader();

game.setUp(p1);
game.setUp(p2);
=======
p2.allUnits = ul1.runLoader();
>>>>>>> 3b08d9cb9cef79dd0c402418baa54c37ca98d3fb

  //game begin
 game.playGame(playersForDemo);
 }


<<<<<<< HEAD
=======
>>>>>>> b377efe660778ed50aa8790ca67c2c05b0270ee5
>>>>>>> 3b08d9cb9cef79dd0c402418baa54c37ca98d3fb

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
<<<<<<< HEAD

=======
<<<<<<< HEAD
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
=======

>>>>>>> b377efe660778ed50aa8790ca67c2c05b0270ee5
>>>>>>> 3b08d9cb9cef79dd0c402418baa54c37ca98d3fb


    
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
