/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
        ArrayList<Unit> testUnits = tester.runLoader();
       
        for(Unit u: testUnits)
        {
            System.out.println("Loaded "+u.nameOfUnit);
        }
    }
      public ArrayList<Unit> runLoader()
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
        ArrayList<Unit> returnUnit = this.loadAllUnits(name);
        returnUnit = this.unitPrepper(returnUnit);
        return returnUnit;
        
        
        
      }
    public ArrayList<Unit> loadAllUnits(String fileToLoad) 
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
                     Unit nUnit = loadUnit(reader, name); //puts the unit in a temporary place
//                     nUnit.setUnitUnitID();
                     allUnits.add(nUnit);                     
                 }
             else reader.nextLine();
         }
         
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UnitLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allUnits;
       
    }
    //Loads a singular unit. 
    public static Unit loadUnit(Scanner reader, String unitName)
    {        
       
            //Scanner reader = new Scanner(new FileInputStream(file));
            //reader.findWithinHorizon(nameOfUnit, 0);
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
                String spriteName = reader.nextLine();
               
                
            Soldier soldier = new Soldier(nUnitName, nUnitType, nDMGDice, nAttackBonus,dmgBonus, hp,ac,def, speed, range, chargeBonus, stamina, morale);
            Unit unit = new Unit(soldier,unitSize);
            unit.setSprite(spriteName);
           
             BufferedImage ic = Unit.getUnitPic(unit);
            

//...
            ImageIcon icon = new ImageIcon();
            icon.setImage(ic);
           // JOptionPane.showMessageDialog(null, icon);
            return unit;
    } 
  
  public ArrayList<Unit> unitPrepper(ArrayList<Unit> playerUnits)
      {
//       ArrayList<Unit> newPlayerUnits = new ArrayList();
          System.out.println("the size please " + playerUnits.size());
       ArrayList<Unit> allPrepUnit = new ArrayList();
          for(Unit prep: playerUnits)
          {
            
            int someNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter amount of "+prep.nameOfUnit+" to create: ", "", JOptionPane.PLAIN_MESSAGE));
           // String confirm = "Really create "+someNumber+" "+prep.nameOfUnit+" ?";
            //JOptionPane.showConfirmDialog(null, confirm);
            
            //add the original unit
            allPrepUnit.add(prep);
            
            for(int i=1; i<someNumber; i++)
            {
                //create a new unit(very important, otherwise just have copies of the orignal units)
                Unit newUnit = new Unit(prep.soldierType,prep.unitSize);
                
                //add the new instance of the unit
                allPrepUnit.add(newUnit);
            }
//            allUnits = allPrepUnit;
          }
//         allUnits = newPlayerUnits;
          allUnits = allPrepUnit;
         return allUnits;
      }
  
  
  
 
  public ArrayList getAllUnits()
  {
    return allUnits;
  }
    
}
