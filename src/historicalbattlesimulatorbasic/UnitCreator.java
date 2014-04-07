
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrew
 * @edit Ed
 */
public class UnitCreator 
{
    
    
    /**
     *
     * @param args
     * @throws java.io.IOException
     */
    //Tester
    public static void main(String[] args) throws IOException {
        
      UnitCreator.createUnit();
    }
      //This shall be used to create a unit. Multiple of this unit may be loaded for a battle.
    public static void createUnit() throws IOException{
        //What the scanner will say so that user knows what stat they are entering
        String[] prompt = new String[16];
    
        prompt[0]="Enter the unit name:\n"
                + "This name should refer to its general name.\n"
                + " It can also include adjective such as country or region names to denote\n "
                + "what sort of unit it is.\n" +
"Examples: Cretan Archers, Longbowmen, Spartan Hoplites, General's Bodyguard";
        prompt[1]="Enter the type of unit \n 1 is Melee " +
"\n 2 is Ranged" +
"\n 3 is Cavalry" +
"\n 4 is Spearmen" +
"\n 5 is 'other'";
        prompt[2]="Select an image";
        prompt[3]="Enter the damage 'dice'. This is the random number from which the damage \n"
                + "shall be calculated. Some typical dice are 4,6,8,10,12";
        prompt[4]="Enter the attack bonus. This the number that will be added to the units \n"
                + "chance to hit. Typically between 1-5";   
        prompt[5]="Enter the Damage Bonus coefficient. This is the number that will be added \n "
                + "to the units damage";        
        prompt[6]="Enter the units HP. 10 is the average";       
        prompt[7]="Enter the units Armor Class. This is how hard they are to hit. \n "
                + "The average is 10, but it can go up to 20";        
        prompt[8]="Enter the defense value. This is the number added to a units AC when \n"
                + "defending. Between 1-5"; 
        prompt[9]="Enter the units speed. This is the amount of square it can travel in a turn. \n"
                + "The average is 5";
        prompt[10]="Enter the units range. If melee, this value is 1. If ranged, it can be up to 15\n"
                + "but will typically be around 8.";
        prompt[11]="Enter the units charge bonus. This is the damaged added when they charge. \n"
                + "Average is 2";
        prompt[12]="Enter the units stamina. This is how many times they can sprint.\n"
                + " Average is 2";
        prompt[13]="Enter the units morale";
        prompt[14]="Enter the average size of the Unit, enter 1 and I will construct"
                + " an individual soldier instead";
        prompt[15]="Enter the name of the file to save this to";
    
       
       
        
        //The stat array, holds the stat.  I entered all information in as strings
        //because it was easy working with only 1 type, but thinking about it I don't
        //think we need ints/doubles unless you plan on adding/subtracting the values
        //later on for some reason.  At the moment, i am just typecasting to integer/double
        //Regardless, This should work for the moment 
        String[] stats = new String[16];
        
        //loops through to get all the stat coefficient
        for(int i=0;i<stats.length;i++)
        {
            if(i!=2)
            {stats[i]=JOptionPane.showInputDialog ( prompt[i] );}
            else if(i==2)
            {
                 final JFileChooser fc = new JFileChooser();
                 File dir = new File("Sprites"+File.separator+"Unit Sprites"+File.separator+"anchorSprites.txt");
                 System.out.println(dir);
                 fc.setCurrentDirectory(dir);
                 File  current = fc.getCurrentDirectory();
        
                System.out.println(current);
                fc.showOpenDialog(null);
                String name = fc.getSelectedFile().getName();
                stats[2] = name;
        
            }
            //if user ever enters exit, it ends
                          
        }
                            
        {
            
            Soldier soldier = new Soldier(
                stats[0],Integer.parseInt(stats[1]),Integer.parseInt(stats[3]),Double.parseDouble
                (stats[4]),Double.parseDouble(stats[5]),Double.parseDouble(stats[6]),
                Double.parseDouble(stats[7]),Double.parseDouble(stats[8]),
                Double.parseDouble(stats[9]),Double.parseDouble(stats[10]),
                Double.parseDouble(stats[11]),Double.parseDouble(stats[12]),
                Double.parseDouble(stats[13]));
            Unit unit = new Unit(soldier,Integer.parseInt(stats[14]));
            unit.setSprite(stats[2]);
            unit.saveUnit(stats[15]);
          Frame frame = JOptionPane.getRootFrame();
            if(createMore(frame))
            {
                createUnit();
            }
     
    }   

}
    private static boolean createMore(Frame frame) throws HeadlessException {
      
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Would you like to create more units?"," ",
                JOptionPane.YES_NO_OPTION);
       
        if(n==0)
        {
            return true;
        }
        else if(n==1)
        {
            return false;
        }
          return false;
    }
}
