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
public void setUp()
{
    //gets the number of players
    JOptionPane.showMessageDialog(frame, "Welcome to the Historical Battle Simulator! ");
    int pcount = Integer.parseInt(JOptionPane.showInputDialog
        (frame, "How many players shall be participating?","0"));
    this.setNumPlayers(pcount);
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
      boolean hasWinner =  p.takeTurn();
      return hasWinner;
           }
}
