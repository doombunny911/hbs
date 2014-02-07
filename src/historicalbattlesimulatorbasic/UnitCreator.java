
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Andrew
 * @edit Ed
 */
public class UnitCreator 
{
    
    
    /**
     *
     */
    //Tester
      public static void main(String[] args) throws IOException 
    {
        UnitCreator tester = new UnitCreator();
        tester.createUnit();
    }
      //This shall be used to create a unit. Multiple of this unit may be loaded for a battle.
    public static void createUnit() throws IOException
    {
        //What the scanner will say so that user knows what stat they are entering
        String[] prompt = new String[15];
        
        prompt[0]="Enter the name of the Unit";
        prompt[1]="Enter the type of unit [1 is Melee" +
"     2 is Ranged" +
"     3 is Cavalry" +
"     4 is Spearmen" +
"     5 is 'other'";
        prompt[2]="Enter the damage coefficient";
        prompt[3]="Enter the attack coefficient";   
        prompt[4]="Enter the dmgBonus coefficient";        
        prompt[5]="Enter the hp coefficient";       
        prompt[6]="Enter the armorClass coefficient";        
        prompt[7]="Enter the defense coefficient"; 
        prompt[8]="Enter the speed coefficient";
        prompt[9]="Enter the range coefficient";
        prompt[10]="Enter the Chargebonus coefficient";
        prompt[11]="Enter the stamina coefficient";
        prompt[12]="Enter the morale coefficient";
        prompt[13]="Enter the size of the Unit, enter 1 and I will construct"
                + " an individual soldier instead";
        prompt[14]="Enter the name of the file to save this to";
        System.out.println("Enter in your character information now");
        System.out.println("At any time, type in exit to leave creation menu");
        Scanner in= new Scanner(System.in);
        
        //The stat array, holds the stat.  I entered all information in as strings
        //because it was easy working with only 1 type, but thinking about it I don't
        //think we need ints/doubles unless you plan on adding/subtracting the values
        //later on for some reason.  At the moment, i am just typecasting to integer/double
        //Regardless, This should work for the moment 
        String[] stats = new String[15];
        
        //loops through to get all the stat coefficient
        for(int i=0;i<stats.length;i++)
        {
            System.out.println(prompt[i]);
            stats[i]=in.next();
            //if user ever enters exit, it ends
                          
        }
        
       
        
        {
            
            Soldier soldier = new Soldier(
                stats[0],Integer.parseInt(stats[1]),Integer.parseInt(stats[2]),Double.parseDouble
                (stats[3]),Double.parseDouble(stats[4]),Double.parseDouble(stats[5]),
                Double.parseDouble(stats[6]),Double.parseDouble(stats[7]),
                Double.parseDouble(stats[8]),Double.parseDouble(stats[9]),
                Double.parseDouble(stats[10]),Double.parseDouble(stats[11]),
                Double.parseDouble(stats[12]));
            Unit unit = new Unit(soldier,Integer.parseInt(stats[13]));
            
            unit.saveUnit(stats[14]);
            
        }
     
    }   

}
