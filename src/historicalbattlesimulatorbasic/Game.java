/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.util.Scanner;

/**
 *
 * @author Schmalz
 */
public class Game 
{
    
int numOfPlayers;
Player players[];
Map gameMap;
boolean hasWinner=false;
Player winner=null;
int gameSize=10; //determines how many units each side has. Variable. Should likely be removed and changed
      
Scanner scan = new Scanner(System.in);
public Game()
{
}

public void setUp(int num)
{

 this.setNumPlayers(num);
 for(int i=0; i<numOfPlayers; i++)
 {
     this.addPlayer(new Player(gameSize),i);
     System.out.println("Enter player 1's name: ");
     String name = scan.nextLine();
     players[i].setPlayerName(name);
 }
}
//sets how many people are playing
public void setNumPlayers(int numOfPlayers)
{
    this.numOfPlayers=numOfPlayers;
    
}
//adds a player and number
public void addPlayer(Player player, int num)
{
    players[num]=player;
}
public void playGame()
{
    while(hasWinner==false)
    {
        for(int i=0; i<numOfPlayers; i++)
        {
            this.takeTurn(players[i]);
        }
        
    }
    //implement later. Will
    
}

    private boolean takeTurn(Player player)
    {
        
       //Should be a loop, allows things to cycle through. Return true
        hasWinner=false;
        //player options.
        //Select Unit
        //Save
        //End Turn
        return hasWinner;
    }

    
    //need win conditions
}
