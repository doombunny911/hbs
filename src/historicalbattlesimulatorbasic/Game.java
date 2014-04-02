/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Schmalz
 */
public class Game 
{

  
//    Frame frame = JOptionPane.getRootFrame();    

//    Player players[];
    Map gameMap;
    boolean hasWinner=false;
    Player winner=null;

    public static ArrayList<Player> playersForDemo = new ArrayList<>();
    static int numOfPlayers; //number of players playing

//    Scanner scan = new Scanner(System.in);

    

public static void main(String[] args)
{
   
Game game = new Game();
Scanner in = new Scanner(System.in);
System.out.println("what is your name player one ?");
String name = in.next();
Player p1 = new Player(name);
System.out.println("what is your name player two ?");
name = in.next();
Player p2 = new Player(name);

playersForDemo.add(p1);
playersForDemo.add(p2);
JOptionPane.showMessageDialog(null,"Player 1 choose your army:");
UnitLoader unitLoader1 = new UnitLoader();
//p1.allUnits = ul1.runLoader();

unitLoader1.runLoader();


//p2.allUnits = ul2.runLoader();

//game.setUp(p1);
JOptionPane.showMessageDialog(null,"Player 2 choose your army:");
UnitLoader unitLoader2 = new UnitLoader();
GUI.player2UnitNum=unitLoader2.getAllUnits().size();
GUI.player2AllUnits=unitLoader2.getAllUnits();


////game.setUp(p2);

//p2.allUnits = ul1.runLoader();

//p2.allUnits = ul1.runLoader();


  //game begin
 game.playGame(playersForDemo);
 }



 public void playGame(ArrayList<Player> playersForDemo)
{
   boolean hasWinner = false;
//   while(!hasWinner)
//   {
       for(Player p : playersForDemo)
       {
           JOptionPane.showMessageDialog(null,"Player "+p.playerName+"'s turn.");
           hasWinner = takeTurn(p);
       }
//   }
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
   //         Player.singlePlayerDialog(players);   
        }
        return players;
    }


    
    //need win conditions
   //if player surrenders or out of unitDraws


    private boolean takeTurn(Player p)
    {
   //   hasWinner =  p.takeTurn();
      return hasWinner;
           }

//    private void setUp(Player p1) 
//    {
//       JOptionPane.showMessageDialog(null, p1.playerName+" place your units!");
//    }
}
