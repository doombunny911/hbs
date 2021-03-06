
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;
/**
 * This is part of the basic prototype of the battle simulator
 * Specifically, this is the individual soldier unit
 * @author schm1413
 * edit Andrew Phillips
 */
import java.util.Random;
//This is the basic constructor class of the soldier
public class Soldier 
{
    //basic parts of the soldier
    boolean alive = true;
    String unitName;
    int unitType; /*
    / 1 is Melee
    / 2 is Ranged
    / 3 is Cavalry
    / 4 is Spearmen
    / 5 is 'other'
    */
    
    int facing;//1 is north, 2 is east, 3 is south, 4 is west
    double attack; // the bonus to the attack
    int dmg; // the type of dice to be rolled for damage
    double dmgBonus; //bonus to damage
    double hp; // a soldiers health points
    double armorClass; //a soldiers basic armor class
    double defense; // a soldiers defense bonus
    double speed; // the distance of which a soldier can travel
    double range; // the range of a soldiers weapons
    double chargeBonus; //The bonus given to charging attack
    double stamina; // The amount of stamina a soldier has
    double morale; // The amount of moral a soldier has
   
    //these won't be modified at all and are constants left to remain as the basis when modifiers are implemented
    double baseAttack;
    double baseHP;
    int baseDmg;
    double baseDmgBonus;
    double baseDefense;
    double baseArmorClass;
    double baseSpeed;
    double baseRange;
    double baseChargeBonus;
    double baseStamina;
    double baseMorale;
     //modifier booleans
    boolean hasBalanceBonus = false;
    boolean hasFlankingBonus = false;
    boolean hasHeightBonus = false;
    boolean hasTerrainModifier = false;
    boolean hasMovementDefenseModifier = false;
    boolean inCover = false;
    boolean isWounded = false;
    boolean isWavering = false;
   
    boolean isCharging;
    boolean isDefending;
    boolean isSprinting;
//   protected boolean isBracing;
   
   //make sure to set to false after each turn
    boolean hasSprinted; //used at turn end.  If true, no stamina is increased
    boolean hasMoved;//used at turn end, if true, and has sprinted is not true, gets a portion of stamina back.  if false, get more stamina back 
    
   int idOfUnit;
//Tile being occupied
    Tile tileOccupied;
//basic dice to be used in combat
    Random dice = new Random();
  
 
public static void main(String[] args)
{
 Soldier s1 = new Soldier("Unit1",2,10,10,10,5,10,3,4,12,3,4,4);
 Tile t1 = new Tile(10, 10,10,10);
 s1.occupyTile(t1);
 t1.occupyBy(s1);
 Soldier s2 = new Soldier("Unit1",2,3,2,1,5,10,3,4,4,3,4,4);
 Tile t2 = new Tile(20, 12,10,10);
 s2.occupyTile(t2);
 t2.occupyBy(s2);
 s1.rangeAttack(s2);
}
    boolean isFlanking;
public Soldier(String unitName,
                int unitType,
                int dmg,
                double attack,
                double dmgBonus,
                double hp,
                double armorClass,
                double defense,
                double speed,
                double rangeV,
                double chargeBonus,
                double stamina,
                double morale)
{
    
     this.unitName = unitName;
     facing=1;
     this.unitType = unitType;
     this.attack = attack;
     this.dmg = dmg;
     this.dmgBonus = dmgBonus;
     this.hp = hp;
     this.armorClass = armorClass;
     this.speed = speed;
     if(unitType==1)
     {
         this.range=20;
     }
     else if(unitType==4)
     {
         this.range = 25;
     }
     else if(unitType==3)
     {
     this.range = 25;
     }
     else if(unitType==2)
     {
         this.range = 150;
     }
     else if(unitType==5)
     {
         this.range = 1000;
     }
     else
    {
        this.range = 25;
    }
     this.chargeBonus = chargeBonus;
     this.stamina = stamina;
     this.morale = morale;
     this.defense=defense;
     //these won't be modified at all
     baseAttack = attack;
     baseHP = hp;
     baseDmg = dmg;
     baseDmgBonus = dmgBonus;
     baseDefense = defense;
     baseArmorClass = armorClass;
     baseSpeed = speed;
     baseRange = this.range;
     baseChargeBonus = chargeBonus;
     baseStamina = stamina;
     baseMorale = morale;
     
    
}

/*
@int id - the identification number of the unit
*/
  public void setUnitID(int id){
    this.idOfUnit = id;
}
  public int getUnitID(){
    return this.idOfUnit;
}
//MOVEMENT
  public void placeOnTile(Tile tile){
    tileOccupied = tile;
    
    tileOccupied.occupyBy(this); //hopefully this works... Don't know if it will though.
}
/**The number based system. 
 *  1 is north
    2 is northeast
    3 is east
    4 is southeast
    5 is south
    6 is southwest
    7 is west
    8 is northwest
    */
  public void moveDirection(int dir){
    if(dir==1)
    {
        this.moveNorth();
    }
    if(dir==2)
    {
        this.moveNorthEast();
    }
    if(dir==3)
    {
        this.moveEast();
    }
    if(dir==4)
    {
        this.moveSouthEast();
    }
    if(dir==5)
    {
        this.moveSouth();
    }
    if(dir==6)
    {
        this.moveSouthWest();
    }
    if(dir==7)
    {
        this.moveWest();
    }
    if(dir==8)
    {
        this.moveNorthWest();
    }
}
/* 1 is north
    2 is northeast
    3 is east
    4 is southeast
    5 is south
    6 is southwest
    7 is west
    8 is northwest
    */
  public boolean canMove(int dir){
    if(dir==1)
    {
          return this.tileOccupied.hasNorth();
    }
    if(dir==2)
    {
        return this.tileOccupied.hasNorthEast();
    }
    if(dir==3)
    {
        return this.tileOccupied.hasEast();
    }
    if(dir==4)
    {
       return this.tileOccupied.hasSouthEast();
    }
    if(dir==5)
    {
        return this.tileOccupied.hasSouth();
    }
    if(dir==6)
    {
        return this.tileOccupied.hasSouthWest();
    }
    if(dir==7)
    {
        return this.tileOccupied.hasWest();
    }
    if(dir==8)
    {
        return this.tileOccupied.hasNorthWest();
    }
    else 
        return false;
}
  public void moveNorth(){
if(tileOccupied.hasNorth()&&!tileOccupied.tileNorth.isOccupied)
    {
        tileOccupied = tileOccupied.tileNorth();
        this.placeOnTile(tileOccupied);
    }
}
  public void moveNorthEast(){
    if(tileOccupied.hasNorthEast()&&!tileOccupied.tileNorthEast.isOccupied)
    {
    tileOccupied = tileOccupied.tileNorthEast();
     this.placeOnTile(tileOccupied);
    }
}
  public void moveEast(){
    if(tileOccupied.hasEast()&&!tileOccupied.tileEast.isOccupied)
    {
    tileOccupied = tileOccupied.tileEast();
     this.placeOnTile(tileOccupied);
    }
}
  public void moveSouthEast(){
    if(tileOccupied.hasSouthEast()&&!tileOccupied.tileSouthEast.isOccupied)
    {
    tileOccupied = tileOccupied.tileSouthEast();
     this.placeOnTile(tileOccupied);
    }
}
  public void moveSouth(){
    if(tileOccupied.hasSouth()&&!tileOccupied.tileSouth.isOccupied)
    {
    tileOccupied = tileOccupied.tileSouth();
     this.placeOnTile(tileOccupied);
    }
}
  public void moveSouthWest(){
    if(tileOccupied.hasSouthWest()&&!tileOccupied.tileSouthWest.isOccupied)
    {
    tileOccupied = tileOccupied.tileSouthWest();
     this.placeOnTile(tileOccupied);
    }
}
  public void moveWest(){
    System.out.println(this);
    if(tileOccupied.hasWest()&&!tileOccupied.tileWest.isOccupied)
    {
    tileOccupied = tileOccupied.tileWest();
     this.placeOnTile(tileOccupied);
    }
}
  public void moveNorthWest(){
    if(tileOccupied.hasNorthWest()&&!tileOccupied.tileNorthWest.isOccupied)
    {
       tileOccupied = tileOccupied.tileNorthWest();
         this.placeOnTile(tileOccupied);
    }
}
  public Soldier(){
    
}

    @Override
    public Soldier clone(){
    Soldier clone = new Soldier(this.unitName,
                this.unitType,
                this.dmg,
                this.attack,
                this.dmgBonus,
                this.hp,
                this.armorClass,
                this.defense,
                this.speed,
                this.range,
                this.chargeBonus,
                this.stamina,
                this.morale);
    return clone;
}
/* Actions Taken by the user. The following methods are actions taken by the user that modify values.*/
  public void defend(){
    Modifier.defend(this);
}
  public void undefend(){
      Modifier.unDefend(this);
  }
  public void charge(){
    Modifier.charge(this);
}
  public void brace(){
    Modifier.setBracing(this);
}
  public void sprint(){
     this.isSprinting=true;
    Modifier.sprint(this);
}
  

/*Die- the following method, when activated 'kills' the soldier*/
  public void die()        
  {
    alive = false;
}
//the method for attacking another soldier(individually)
  public Soldier attack(Soldier defender){
    //calculate the dice roll for the attack
    int diceRoll = dice.nextInt(20);
    if(this.dmg<0)
    {
        this.dmg=0;
    }
    double dmgDice = dice.nextInt(this.dmg)+ this.dmgBonus;
   
    this.calculateModifiers(defender);
        //System.out.println("Defender HP before the hit: "+defender.hp);
 
    
        if((diceRoll + this.attack) > (defender.armorClass))
        {
            defender.hp= defender.hp - dmgDice;
             //   JOptionPane.showMessageDialog(null,"The attack hits and "+defender.unitName +" takes " + dmgDice + " points of damage");
           System.out.println("Defender HP after the hit: "+defender.hp);
        }
        else
        {
          // JOptionPane.showMessageDialog(null,"The attack misses!");
        }
    
   
     defender.update();
//     JOptionPane.showMessageDialog(null,defender.unitName+ "'s remaining HP: "+defender.hp);
     return defender;
}
  public Soldier rangeAttack(Soldier opponent){
    if(lineOfSight(opponent)&&inRange(opponent))
    {
         int diceRoll = dice.nextInt(20);
    double dmgDice = dice.nextInt(this.dmg)+ this.dmgBonus;
    
    {
        if((diceRoll + this.attack) > (opponent.armorClass))
        {
            opponent.hp= opponent.hp - dmgDice;
            System.out.println("The attack hits and "+opponent.unitName +" takes " + dmgDice + " points of damage");
            
        }
        else
        {
            System.out.println("The attack misses!");
        }
    }
   
     opponent.update();
     System.out.println(opponent.hp);
     
    }
   return opponent;
    
}
  public void update(){
    //System.out.println("Update called");
        if (this.hp<=0)
        {
            this.die();
            System.out.println(this.unitName + " died");
        }
        else
        {
            System.out.println(this.unitName + " lives to fight another day");
            Modifier.resetValues(this);
        }
     
}
  public boolean isAlive(){
    return alive;
}
  public double getHealth(){
    return this.hp;
}
//this is the method to calculate which modifiers get implemented. It is a series of if cases for the variety of settings.
  public void calculateModifiers(Soldier opponent){
      //Flanking Bonus
        setBalanceBonus(opponent);
        //Flanking Bonus
        boolean checkFlanking = Modifier.checkFlanking(this,opponent);
        if(checkFlanking)
        {
            Modifier.setFlanking(this);
            System.out.println("FLANKING");
        }
        else
        {
            Modifier.removeFlanking(this);
            System.out.println("REMOVED FLANKING");
        }
    
        
        //Height Bonus
       boolean checkHeightAdvantage = Modifier.checkHeightAdvantage(this, opponent);
       boolean checkHeightDisAdvantage = Modifier.checkHeightDisadvantage(this, opponent);
       if(checkHeightAdvantage)
       {
           Modifier.setHeightAdvantage(this);
       }
       else
       {
           Modifier.removeHeightAdvantage(this);
       }
       if(checkHeightDisAdvantage)
       {
           Modifier.setHeightDisAdvantage(this);
       }
       else
       {
           Modifier.removeHeightAdvantage(this);
       }
        //Rough Terrain Movement
        //Moving Bonus
        //inCover
        //Wounded Bonus
        if(this.hp<(this.hp/2))
        {
            Modifier.setWounded(this);
        }
        //Wavering Bonus
        if(this.morale<(this.morale/2))
        {
            Modifier.setWavering(this);
        }
}
  private void setBalanceBonus(Soldier opponent) {
        //Balance Bonus
        /*
        / 1 is Melee
        / 2 is Ranged
        / 3 is Cavalry
        / 4 is Spearmen
        / 5 is 'other'
        */
        if(this.unitType==4 && opponent.unitType==1) //pikeman vs cavalry
        {
            Modifier.setBalanceBonus(this);
        }
       
    }
  private boolean lineOfSight(Soldier opponent){
        //loops over tiles between the unit to make sure no obstruction is there
        return true;
    }
  public double getDistance(Soldier opponent){
       double x1 = this.tileOccupied.xPosition;
    
       double y1 = this.tileOccupied.yPosition;
     if(opponent.tileOccupied==null)
     {
         System.out.println("+++++++++++++++++++++++++++++++++++++++++ \n TILE OCCUPIED NULL \n++++++++++");
     }
       double x2 = opponent.tileOccupied.xPosition;
       // System.out.println(x2);
       double y2 = opponent.tileOccupied.yPosition;
      //  System.out.println(y2);
       double distance = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
//       System.out.println("DISTANCE- "+distance);
       return distance;
       
    }
  public double getDistance(double avgX, double avgY){
        double x1 = this.tileOccupied.xPosition;
     
        double y1 = this.tileOccupied.yPosition;
     
        double x2 = avgX;
       // System.out.println(x2);
        double y2 = avgY;
      //  System.out.println(y2);
        double distance = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
       return distance;
    }
  public boolean inRange(Soldier opponent){
       if(this.range >= this.getDistance(opponent))
       {
           return true;
       }
       else
       {
         //  System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                   //System.out.println("OUT OF RANGE");
           return false;
       }
       
       
    }
  private void occupyTile(Tile t){
         t.occupyBy(this);
        this.tileOccupied=t;
    }
 
}