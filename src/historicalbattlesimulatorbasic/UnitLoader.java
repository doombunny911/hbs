/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew
 */
//class reads from a text file and loads the units into a unit array.
public class UnitLoader 
{
    static ArrayList<Unit> allUnits = new ArrayList();
     //Tester
      public static void main(String[] args) throws IOException 
    {
        UnitLoader tester = new UnitLoader();
        System.out.println("File to load: ");
        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();
        tester.loadUnits(name);
        
    }
    public void loadUnits(String fileToLoad) 
    {
        try {
            String fileName=fileToLoad+".txt";
            File file = new File("Units\\"+fileName);
            
            Scanner reader = new Scanner(new FileInputStream(file));
            System.out.println(reader.next());
            //Show all unit Names

            loadUnit(reader,"Hoplites");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UnitLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Loads a singular unit
    public static Unit loadUnit(Scanner reader, String unitName)
    {        
            reader.findWithinHorizon(unitName, 0);
            String nUnitName= unitName; //0
            System.out.println(nUnitName);
            int nUnitType= reader.nextInt(); //1
            System.out.println(nUnitType);
            double nAttackBonus = reader.nextDouble(); //2
            int nDMGDice = reader.nextInt(); //3
            double dmgBonus = reader.nextDouble();//4
            double hp = reader.nextDouble();
            double ac = reader.nextDouble();
            double def = reader.nextDouble();
            double speed = reader.nextDouble();
            double range = reader.nextDouble();
            double chargeBonus = reader.nextDouble();
            double stamina = reader.nextDouble();
            double morale = reader.nextDouble();
            int unitSize = reader.nextInt();
                   
         Soldier soldier = new Soldier(nUnitName, nUnitType, nDMGDice, nAttackBonus,dmgBonus, hp,ac,def, speed, range, chargeBonus, stamina, morale);
         Unit unit = new Unit(soldier,unitSize);
         System.out.println(unit.unitName);
        return unit;
           
    }
    
    
    public static ArrayList getAllUnits()
    {
        return allUnits;
    }
    
}
