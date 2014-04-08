/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * This is the unit class
 * @author schm1413
 * edit Andrew Phillips
 */
public class Unit extends Soldier 
{
    //THIS IS THE MOST IMPORTANT 
      Soldier soldierType;
      
    static int unitIDGen =0;
    int unitID;
    boolean clickable = true;
   
    double moveMentCounter; //for now, very basic
    String nameOfUnit;
    ArrayList<Soldier> unitSoldiers = new ArrayList<>();
    
    int unitSize; 
    int unitWidth;
    int unitStartLocationX;
    int unitStartLocationY;
    boolean unitDefeat = false;
    int unitPoints=2;
    int xPosition, yPosition;
    int yHeight=10;
    int xWidth=10;
    int unitFacing;
    UnitFormations currentFormation;
    String spriteName = "blackKnight.png"; 
    int playerNum;
    double speed;
    double range;
    
//    static Unit 
    //Create a unit, with position, on the battlefield
    public static void main(String[] args)
     {
         UnitLoader ul = new UnitLoader();
         ArrayList<Unit> uA = ul.runLoader();
         Unit u = uA.get(0);
         BufferedImage ic = u.getUnitPic(u);
         Graphics g = ic.createGraphics();
         ImageIcon icon = new ImageIcon();
         icon.setImage(ic);
         JOptionPane.showMessageDialog(null, icon);
     }
    
    public void setPlayerNum(int playerNum)
    {
        this.playerNum = playerNum;
    }
    public Unit(Soldier soldierType, int unitSize,int x,int y)
    {
        
        unitFacing = soldierType.facing;
        this.range = (soldierType.range)*4;
        this.speed = soldierType.speed;
        this.nameOfUnit = soldierType.unitName;
        this.unitSize = unitSize;
        this.unitStartLocationX=x;
        this.unitStartLocationY=y;
          
        
      
         //Creates a random Unit ID
        this.unitID=Unit.unitIDGen;
        this.moveMentCounter=soldierType.speed;
        Unit.unitIDGen++;
for(int i=0; i<unitSize;i++)
    {   
           
            //make each unit a soldier
            Soldier clone= soldierType.clone();
            clone.setUnitID(this.unitID);
            clone.placeOnTile(tileOccupied);
            unitSoldiers.add(clone);
        }


        this.moveMentCounter=unitSoldiers.get(0).speed;
        this.unitFacing=unitSoldiers.get(0).facing;

        
       System.out.println("unitID of " + this.nameOfUnit + " = " +this.unitID);
    }
   
    //Create a unit with out position.
     public Unit(Soldier soldierType, int unitSize)
    {

        unitFacing = soldierType.facing;
        range = soldierType.range;
        speed = soldierType.speed;
        nameOfUnit = soldierType.unitName;
        this.unitSize = unitSize;
      
        this.soldierType = soldierType;
        this.unitID=Unit.unitIDGen;
        
     
        Unit.unitIDGen++;

        for(int i=0; i<unitSize; i++)
        {
            //make each unit a soldier
            Soldier clone= soldierType.clone();
            clone.setUnitID(this.unitID);
            unitSoldiers.add(clone);
        }
        this.moveMentCounter=unitSoldiers.get(0).speed;
        this.unitFacing=unitSoldiers.get(0).facing;
        this.unitType = unitSoldiers.get(0).unitType;
        this.dmg=unitSoldiers.get(0).dmg; 
        this. attack=unitSoldiers.get(0).attack;
        this.dmgBonus=unitSoldiers.get(0).dmgBonus;
        this.hp=unitSoldiers.get(0).hp;
        this.armorClass=unitSoldiers.get(0).armorClass;
        this.speed=unitSoldiers.get(0).speed;
        this.range=unitSoldiers.get(0).range;
        this.chargeBonus=unitSoldiers.get(0).chargeBonus;
        this.stamina=unitSoldiers.get(0).stamina;
        this.morale=unitSoldiers.get(0).morale;
        this.defense = unitSoldiers.get(0).defense;

    }
    
    
    public void setSprite(String sprite){
        this.spriteName = sprite;
    }
    public boolean isClickable(){
        return clickable;
    }
    public void setClickable(){
        this.clickable = true;
    }
    public void setClickableFalse(){
        this.clickable = false;
    }
    public  BufferedImage getUnitPic(Unit unit){
        BufferedImage img = new BufferedImage(GUI.tileWidth, GUI.tileWidth, 4);
        try {
             img = ImageIO.read(new File("Sprites"+File.separator+"UnitSprites"+File.separator+unit.spriteName));
        } catch (IOException ex) {
            Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return img;
    }
    
    //this should determine how many units are still alive
    public int getUnitsAlive() {
        int unitsAlive=0;
        System.out.println("unitSoldiers size = " + unitSoldiers.size() );
        System.out.println("unitSize = " + unitSize );
        for(int i=0; i<unitSize-1; i++)
        {
//            System.out.println("i = " + i + " "+unitSoldiers.size());
            if(unitSoldiers.get(i).isAlive())
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
    public boolean isUnitDefeated(){
        return unitDefeat;
    }
    /*
    Attacks the defending units. 
    */
    public void attack(Unit defender,Player enemy){
       
        this.expendUnitPoint();
        int aSize = this.getUnitsAlive(); //gets the number of attacking units alive
        int dSize = defender.getUnitsAlive(); //gets the number of defending units alive
       
        
            int j=0;
            for(int i=0; i<aSize; i++) //this algorithm will run until each of the attacking units have attacked one of their opponents
            {    
                
             if(defender.unitDefeat)
             {
                 System.out.println("all defenders have been defeated");
                 enemy.allUnits.remove(defender);
             }
             else
             {
                 
                 
               dSize = defender.getUnitsAlive(); //gets the number of defending units alive
//               System.out.println("count  =  "+ count);
//               if(j<dSize)
//               {  
//                     j++;
////                   System.out.println("j is now eqaul to " + j);
//                 
//               }
//               else
//               {
//                   
//                   j=0;
//               }
//                if(!this.unitSoldiers.get(i).isAlive()&&j!=0)
//                {
//                    
//                    i--;
//                }                
                Soldier s = this.unitSoldiers.get(i).attack(defender.unitSoldiers.get(j)); //attacks the unit and edits the value, returns defender
                 
                 if(!s.isAlive()) //if the defender died
                 {
                    defender.unitSize--;
                    System.out.println(defender.unitSoldiers.get(j) + " IS KILLED");
                    defender.unitSoldiers.remove(j);
//                    System.out.println(defender.unitSoldiers.get(j) + "  --> SHOULD BE NULL"); //actually i believe arraylists compress, so the soldier above it would fall into j's place                                                                     //unless is the max j
                 }
                 else
                 {
                     j++;
                    System.out.println(s.unitName+" "+j+" has been attacked and survived");
                    defender.unitSoldiers.set(j, s);
                 }
             }
        }
        //Message Afterwards
        if(defender.getUnitsAlive()==0 && isUnitDefeated()) //saying the same thing twice
                {
                    //System.out.println("Remaining defender"+dSize);
                    JOptionPane.showMessageDialog(null, "All the"+ defender.nameOfUnit +"s are dead!");
                    defender.alive = false;
                   
                    
                    
                }
            JOptionPane.showMessageDialog(null, "After this round of attacks by the " 
                    + this.nameOfUnit + " against " + defender.nameOfUnit+ " "+
                    defender.unitSize + " units of "+ defender.nameOfUnit + " remain. ");
         //unitPoints = unitPoints -1;   
        
        
     int index=   GUI.determineWhichUnitDrawContainsUnitIdEqaulToUnitSelectedAt(defender);
     GUI.removeSoldiersFromPreviousTiles(defender);
     System.out.println("defender size = " +defender.unitSize + " " + defender.unitSoldiers.size()); 
     UnitDraw draw = new UnitDraw(defender,new Tile(defender.xPosition,defender.yPosition,GUI.tileWidth,GUI.tileWidth));
     GUI.unitDraws.remove(index);
     GUI.unitDraws.add(draw);
     GUI.repainter();
    }
    
  
    /*
  Calculates whether the opponent is in range or not, based on getting the central position. (The math may be off on this one)
    */
    private boolean isInRange(Unit opponent) {
        boolean inRange=false;
      
       for(Soldier s : this.unitSoldiers)
       {
           if(!inRange)
           {
               for(Soldier u: opponent.unitSoldiers)
               {
                   if(!inRange)
                   {
                        
                        if (s.getDistance(u)<= (this.soldierType.range+2)) 
                        {
                            System.out.println(s.getDistance(u));
                            inRange = true;
                        }
                   }
               }
           }
       }
       
        return inRange;
    }
    public ArrayList<Unit> getAllInRange(Player p2){
        ArrayList<Unit> allUnitsInRange = new ArrayList<>();
        ArrayList<Unit> p2AllUnits = p2.getUnitList();
        for(Unit u: p2AllUnits)
        {
           if(this.isInRange(u))
           {
               allUnitsInRange.add(u);
           }
        }
        
        return allUnitsInRange;
    }
    public void resetUnitPoints(){
        this.endTurn();
        this.unitPoints = 2;
        System.out.println(this.nameOfUnit+ " reset movement points");
    }
    public int returnUnitPoints(){
        return unitPoints;
    }
    public void expendUnitPoint(){
        this.unitPoints = this.unitPoints -1;
        GUI.refreshTurnPanel();
    }
    public boolean hasUnitPoints()
    {
        if(this.unitPoints>0)
        {
            return true;
        }
        else
        {
            return false;
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
  
    public void moveDirection(int dir){
       boolean accessible=true; //the value for if every soldier can move
       
      //this algorithm will run until each 
      //of the attacking units have attacked one of their opponents
       for(int i=0; i<unitSize; i++) 
            {
            if(accessible)
            {
                 if(!this.unitSoldiers.get(i).canMove(dir))
                 {
                    accessible=false;
                 }
                 else
                    this.unitSoldiers.get(i).moveDirection(dir);
            }
       }
    }
    /*
    Places a unit on a specific part of the map. Technically sets the unit's xposition and yposition
    @xPosition - The x variable position of the unit
    @yPosition - The y variable position of the unit
    */
    public void placeUnit(int xPosition, int yPosition){
        this.xPosition= xPosition;
        this.yPosition= yPosition;
    }
    /*
    Returns the x variable position
    */
    public int getXPosition(){
        return xPosition;
    }
    /*
    Returns the y variable position
    */
    public int getYPosition(){
        return yPosition;
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
            writer.println(nameOfUnit);
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
            writer.println(this.spriteName);
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

    public String getSpriteName(){
        System.out.println("getSpriteName called");
        return this.spriteName;
    }
    public void setPosition(int x, int y)  {
        this.xPosition = x;
        this.yPosition = y;
    }
    public int getUnitID() {
            return this.unitID;
        }
    public void setFormation(UnitFormations form) {
      this.currentFormation=form;
  }
    public void useSpecialAbility() {
       //use specialAbility
   }
    public void drawUnit(Unit unit) {
       int  index=GUI.determineWhichUnitDrawContainsUnitIdEqaulToUnitSelectedAt(GUI.unitSelected);

         unit.setPosition(GUI.tileClicked.xPosition,GUI.tileClicked.yPosition);

            UnitDraw draw = new UnitDraw(unit,new Tile(unit.xPosition,unit.yPosition,GUI.tileWidth,GUI.tileWidth));
            GUI.unitDraws.add(draw);//adds the new unit
       }
  public void endTurn() {
        
        //not sure how pervasive special ability is for this method
        //i feel like special ability could be anything and so it is hard to
        //determine what things are going to return to base and what doesn't
         System.out.println("Your turn has ended");
//         speed  =baseSpeed;
//         range    =baseRange;
         this.moveMentCounter=speed;
         chargeBonus  =baseChargeBonus;
         isCharging=false;
         isSprinting=false;
         hasSprinted=false;
         hasMoved=false;
           
            
    }

    void addUnitPoint() {
     unitPoints++;    
    }
}

