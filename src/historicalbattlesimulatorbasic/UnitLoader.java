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
        System.out.println("Unit to load: ");
        String unitToLoad = scn.nextLine();
        Unit loadedUnit = tester.loadUnit(name, unitToLoad);
    }
    public void loadAllUnits(String fileToLoad) 
    {
        try {
            //Load all units
            //while
            String fileName=fileToLoad+".txt";
            File file = new File("Units"+File.separator+fileName);
            Scanner reader = new Scanner(new FileInputStream(file));
         //   while(reader.ha)
            //Show all unit Names
            
            Unit unit = loadUnit(null, null);
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UnitLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        

       
    }
    //Loads a singular unit. 
    public static Unit loadUnit(String fileToLoad, String unitName)
    {        
        try {
            String fileName=fileToLoad+".txt";
            File file = new File("Units"+File.separator+fileName);
            Scanner reader = new Scanner(new FileInputStream(file));
            reader.findWithinHorizon(unitName, 0);
            String nUnitName= reader.nextLine(); //0
            System.out.println(nUnitName);
            int nUnitType= Integer.parseInt(reader.nextLine()); //1
            System.out.println("Unit Type " + nUnitType);
            int nDMGDice = Integer.parseInt(reader.nextLine()); //3
            double nAttackBonus = Double.parseDouble(reader.nextLine()); //2
            double dmgBonus = Double.parseDouble(reader.nextLine());//4
            double hp = Double.parseDouble(reader.nextLine()); //5
            double ac = Double.parseDouble(reader.nextLine()); //6
            double def =Double.parseDouble(reader.nextLine()); //7
            double speed = Double.parseDouble(reader.nextLine()); //8
            double range = Double.parseDouble(reader.nextLine()); //9
            double chargeBonus = Double.parseDouble(reader.nextLine()); //10
            double stamina = Double.parseDouble(reader.nextLine()); //11
            double morale = Double.parseDouble(reader.nextLine()); //12
            int unitSize = Integer.parseInt(reader.nextLine()); //13
       
            Soldier soldier = new Soldier(nUnitName, nUnitType, nDMGDice, nAttackBonus,dmgBonus, hp,ac,def, speed, range, chargeBonus, stamina, morale);
            Unit unit = new Unit(soldier,unitSize);
            System.out.println(unit.unitName + "created");
            return unit;
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(UnitLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
           
    }
    
    
    public static ArrayList getAllUnits()
    {
        return allUnits;
    }
    
}
