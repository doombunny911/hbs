/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Andrew + Edward
 */
//class reads from a text file and loads the units into a unit array.
public class UnitLoader 
{
    
    static ArrayList<Unit> allUnits = new ArrayList();
     //Tester
      public static void main(String[] args) throws IOException 
    {
        UnitLoader tester = new UnitLoader();
           tester.runLoader();
           tester.unitPrepper();
    }
      public void runLoader()
      {
          
        final JFileChooser fc = new JFileChooser();
        File dir = new File("Units"+File.separator+"anchor.txt");
        
        System.out.println(dir);
        fc.setCurrentDirectory(dir);
        File  current = fc.getCurrentDirectory();
        
       System.out.println(current);
       fc.showOpenDialog(null);
        
  
       
        String name = fc.getSelectedFile().getName();
        System.out.println("You have selected to load "+name);
        this.loadAllUnits(name);
        
      }
    public void loadAllUnits(String fileToLoad) 
    {
        try {
            //Load all units
            //while
            String fileName=fileToLoad;
            File file = new File("Units"+File.separator+fileName);
            Scanner reader = new Scanner(new FileInputStream(file));
         while(reader.hasNext())
         {
             if(reader.nextLine().equals("---"))
                 {
                     
                     String name = reader.nextLine();
                     System.out.println("Name "+name);
                     Unit nUnit = loadUnit(reader, name); //puts the unit in a temporary palce
                     allUnits.add(nUnit);                     
                 }
             else reader.nextLine();
         }
            //Show all unit Names
            System.out.println("The following units have been added to the units list: ");
           for(Unit aUnit : allUnits)
            {
                 System.out.println(aUnit.unitName);
            }
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UnitLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        

       
    }
    //Loads a singular unit. 
    public static Unit loadUnit(Scanner reader, String unitName)
    {        
       
            //Scanner reader = new Scanner(new FileInputStream(file));
            //reader.findWithinHorizon(unitName, 0);
            String nUnitName= unitName; //0
            
                int nUnitType= Integer.parseInt(reader.nextLine()); //1
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
            System.out.println(unit.unitName + " created");
            return unit;
    } 
  
      public ArrayList unitPrepper()
      {
       ArrayList<Unit> newAllUnits = new ArrayList();
       ArrayList<Unit> allPrepUnit = new ArrayList();
          for(Unit prep: allUnits)
          {
            int someNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter amount of "+prep.unitName+" to create: ", "", JOptionPane.PLAIN_MESSAGE));
            String confirm = "Really create "+someNumber+" "+prep.unitName+" ?";
            JOptionPane.showConfirmDialog(null, confirm);
            for(int i=0; i<someNumber; i++)
            {
                allPrepUnit.add(prep);
            }
            newAllUnits.addAll(allPrepUnit);
          }
          //shouldn't you be adding newAllUnits to allUnits?  
          //what if I want to load more than one file?
         allUnits = newAllUnits;
         return allUnits;
      }
           
    
    
    
    
    public ArrayList getAllUnits()
    {
        return allUnits;
    }
    
}
