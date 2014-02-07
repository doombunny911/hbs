/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.io.File;
import java.io.FileNotFoundException;
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
//    static Unit 
    //Create a unit, with position, on the battlefield
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
       // System.out.println("The size of the unit is currently " + unitsAlive);
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
        //100
        int aSize = this.getUnitsAlive(); //gets the number of attacking units alive
        
        //100
        int dSize = defender.getUnitsAlive(); //gets the number of defending units alive
        
        if(this.unitDefeat==false)
        {
            //aSize ==100
            for(int i=0; i<aSize; i++) //this algorithm will run until each of the attacking units have attacked one of their opponents
            {
             int j = dSize-1; //the integer for the defenders unit size.
             if(j==0)
                {
                    //if all the defenders are dead, bring them all back again?
                    System.out.println("the attackers won, all defenders are dead");
                    break;
                    // j= defender.getUnitsAlive(); //resets j for the rest of the attackers to target.
                }
             defender.unitSoldiers[j]=this.unitSoldiers[i].attack(defender.unitSoldiers[j]); //attacks the unit and edits the value
             j--; //decrament j after every attack
                        
             
            }
            
        System.out.println("After this round of attacks by the" + this.unitname + " against " + defender.unitname+ " "+ defender.getUnitsAlive() + " units of "+ defender.unitname + "remain.");
        }
        
    }
    //used to move the entire unit in a direction. Sets the unit to be facing this direction.
    /*
    1 is north
    2 is east
    3 is west
    4 is south
    */
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
    public void placeUnit(int xPosition, int yPosition)
    {
        
    }

    public void resetMovementPoints() 
    {
        unitPoints=2;
    }

    //Saves the unit in the designated filename
    public void saveUnit(String fileName2) throws IOException {
        PrintWriter writer = null;
        String fileName=fileName2+".txt";
        File file = new File("Units\\"+fileName);
        File parent = file.getParentFile();

if(!parent.exists() && !parent.mkdirs()){
    throw new IllegalStateException("Couldn't create dir: " + parent);
} 
        try {                      
            writer = new PrintWriter(file, "UTF-8");
            writer.println("||");//
            writer.println(unitName);
            writer.println("Unit Type:"+soldierType.unitType); 
            writer.println("DMG Dice:"+soldierType.dmg); // the type of dice to be rolled for damage
            writer.println("AtkBonus:"+soldierType.attack); // the bonus to the attack
            writer.println("DMG Bonus:"+soldierType.dmgBonus); //bonus to damage
            writer.println("HP:"+soldierType.hp); // a soldiers health points
            writer.println("AC:"+soldierType.armorClass); //a soldiers basic armor class
            writer.println("Def:"+soldierType.defense); // a soldiers defense bonus
            writer.println("Speed:"+soldierType.speed); // the distance of which a soldier can travel
            writer.println("Range:"+soldierType.range); // the range of a soldiers weapons
            writer.println("Charge Bonus:"+soldierType.chargeBonus); //The bonus given to charging attack
            writer.println("Stamina:"+soldierType.stamina); // The amount of stamina a soldier has
            writer.println("Morale:"+soldierType.morale); // The amount of moral a soldier has
      
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
            
            
}
