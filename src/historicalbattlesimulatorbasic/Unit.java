/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is the unit class
 * @author schm1413
 */
public class Unit extends Soldier
{
    String unitName;
    Soldier unitSoldiers[];
    Soldier soldierType;
    int unitSize; 
    int unitWidth;
    int unitStartLocationX;
    int unitStartLocationY;
    boolean unitDefeat = false;
    int unitPoints=2;
    int xPosition, yPosition;
    int yHeight=10;
     int xWidth=10;
//    static Unit 
    //Create a unit, with position, on the battlefield
    public static void main(String[] args)
     {
         
     }
    public Unit(Soldier soldierType, int unitSize,int x,int y)
    {
        unitName = soldierType.unitname;
        this.unitSize = unitSize;
        this.unitStartLocationX=x;
        this.unitStartLocationY=y;
        unitSoldiers = new Soldier[unitSize];
        this.soldierType = soldierType;
        for(int i=0; i<unitSize; i++)
        {
            //make each unit a soldier
            unitSoldiers[i]= soldierType;
        }
    }
    //Create a unit with out position.
     public Unit(Soldier soldierType, int unitSize)
    {
        unitName = soldierType.unitname;
        this.unitSize = unitSize;
        unitSoldiers = new Soldier[unitSize];
        this.soldierType = soldierType;
        for(int i=0; i<unitSize; i++)
        {
            //make each unit a soldier
            unitSoldiers[i]= soldierType;
        }
    }
    //this should determine how many units are still alive
    public int getUnitsAlive()
    {
        int unitsAlive=0;
        for(int i=0; i<unitSize; i++)
        {
            if(unitSoldiers[i].isAlive())
            {
                unitsAlive = unitsAlive+1;
            }
            else
            {
                
            }//do nothing
            
        }
       if(unitsAlive==0)
        {
            unitDefeat = true; //if there are 0 or less units alive in this unit, set the value of unit defeat to true
        }
        return unitsAlive;
    }
    /*
     * Gets the boolean value if the unit is defeated
     * */
    public boolean isUnitDefeated()
    {
        return unitDefeat;
    }
    
    
    public void attack(Unit defender)
    {
       
        int aSize = this.getUnitsAlive(); //gets the number of attacking units alive
        
        //100
        int dSize = defender.getUnitsAlive(); //gets the number of defending units alive
        
        if(this.unitDefeat==false)
        {
            
            for(int i=0; i<aSize; i++) //this algorithm will run until each of the attacking units have attacked one of their opponents
            {
             int j = dSize-1; //the integer for the defenders unit size.
             if(j==0)
                {
                    //if all the defenders are dead, bring them all back again?
                    System.out.println("The attackers won, all defenders are dead");
                    break;
                    // j= defender.getUnitsAlive(); //resets j for the rest of the attackers to target.
                }
             defender.unitSoldiers[j]=this.unitSoldiers[i].attack(defender.unitSoldiers[j]); //attacks the unit and edits the value
             j--; //decrament j after every attack
                        
             
            }
            
        System.out.println("After this round of attacks by the" + this.unitname + " against " + defender.unitname+ " "+ defender.getUnitsAlive() + " units of "+ defender.unitname + "remain.");
        }
        
    }
    
      public void attackAtRange(Unit defender)
    {
       
        int aSize = this.getUnitsAlive(); //gets the number of attacking units alive
        
        //100
        int dSize = defender.getUnitsAlive(); //gets the number of defending units alive
        
        if(this.unitDefeat==false)
        {
            
            for(int i=0; i<aSize; i++) //this algorithm will run until each of the attacking units have attacked one of their opponents
            {
             int j = dSize-1; //the integer for the defenders unit size.
             if(j==0)
                {
                    //if all the defenders are dead, bring them all back again?
                    System.out.println("The attackers won, all defenders are dead");
                    break;
                    // j= defender.getUnitsAlive(); //resets j for the rest of the attackers to target.
                }
             defender.unitSoldiers[j]=this.unitSoldiers[i].rangeAttack(defender.unitSoldiers[j]); //attacks the unit and edits the value
             j--; //decrament j after every attack
                        
             
            }
            
        System.out.println("After this round of attacks by the" + this.unitname + " against " + defender.unitname+ " "+ defender.getUnitsAlive() + " units of "+ defender.unitname + "remain.");
        }
        
    }
    
    /*
    Used to move the entire unit in a direction. Sets the unit to be facing this direction.
    @dir - Direction:
    1 is north
    2 is northeast
    3 is east
    4 is southeast
    5 is south
    6 is southwest
    7 is west
    8 is northwest
    */
    @Override
    public void moveDirection(int dir)
    {
       boolean accessible=true; //the value for if every soldier can move
       
      //this algorithm will run until each 
      //of the attacking units have attacked one of their opponents
       for(int i=0; i<unitSize; i++) 
            {
            if(accessible)
            {
                 if(!this.unitSoldiers[i].canMove(dir))
                 {
                    accessible=false;
                 }
                 else
                    this.unitSoldiers[i].moveDirection(dir);
            }
       }
    }
    /*
    Places a unit on a specific part of the map. Technically sets the unit's xposition and yposition
    @xPosition - The x variable position of the unit
    @yPosition - The y variable position of the unit
    */
    public void placeUnit(int xPosition, int yPosition)
    {
        this.xPosition= xPosition;
        this.yPosition= yPosition;
    }
    /*
    Returns the x variable position
    */
    public int getXPosition()
    {
        return xPosition;
    }
    /*
    Returns the y variable position
    */
    public int getYPosition()
    {
        return yPosition;
    }
    
    public void resetUnitPoints() 
    {
        unitPoints=2;
    }

    //Saves the unit in the designated filename
    public void saveUnit(String fileName2) throws IOException {
        PrintWriter writer = null;
        String fileName=fileName2+".txt";
        File file = new File("Units"+File.separator+fileName);
        File parent = file.getParentFile();

if(!parent.exists() && !parent.mkdirs()){
    throw new IllegalStateException("Couldn't create dir: " + parent);
} 
        try {                      
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            writer.println("---"); //seperator 
            writer.println(unitName);
            writer.println(soldierType.unitType); 
            writer.println(soldierType.dmg); // the type of dice to be rolled for damage
            writer.println(soldierType.attack); // the bonus to the attack
            writer.println(soldierType.dmgBonus); //bonus to damage
            writer.println(soldierType.hp); // a soldiers health points
            writer.println(soldierType.armorClass); //a soldiers basic armor class
            writer.println(soldierType.defense); // a soldiers defense bonus
            writer.println(soldierType.speed); // the distance of which a soldier can travel
            writer.println(soldierType.range); // the range of a soldiers weapons
            writer.println(soldierType.chargeBonus); //The bonus given to charging attack
            writer.println(soldierType.stamina); // The amount of stamina a soldier has
            writer.println(soldierType.morale); // The amount of moral a soldier has
            writer.println(this.unitSize);
            System.out.println("Saved");
            writer.close();
             } 
        catch (FileNotFoundException ex)
             {
            System.out.println("Unable to save file");
        } 
        finally {
            writer.close();
        }
    }

    public void setPosition(int x, int y) 
    {
        this.xPosition = x;
        this.yPosition = y;
    }
            
            
}
