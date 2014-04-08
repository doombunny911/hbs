/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

/**
 *
 * @author Schmalz
 */
public class Modifier
{
    
//MODIFIERS
    //user initiated modifers
    /* Actions Taken by the user. The following methods are actions taken by the user that modify values.*/
public static void defend(Soldier soldier)
{
    soldier.armorClass = soldier.armorClass + soldier.defense;
}
public static void unDefend(Soldier soldier)
{
    soldier.armorClass =soldier.baseArmorClass;
}
public static void charge(Soldier soldier)
{
    soldier.dmgBonus=soldier.dmgBonus+soldier.dmgBonus;
}
public static void unCharge(Soldier soldier)
{
    soldier.dmgBonus= soldier.baseDmgBonus;
}

//Attack

    //Bonuses
public static boolean checkFlanking(Soldier selected, Soldier target)
{
    boolean flanking = false;
    System.out.println("TARGET -"+target.unitName +" ++++++++++++++/n CHECKING FLANKING");
    System.out.println("TTHIS -"+selected.unitName +" ++++++++++++++/n CHECKING FLANKING");
    //north
    if(target.facing==1&&selected.tileOccupied.yPosition>target.tileOccupied.yPosition)
    {//if target is behind, it would have to have a y value greater than it [ meaning if it is on top, it is below it]
        flanking = true;
    }
    
    //north east
    else if(target.facing==2&&selected.tileOccupied.yPosition>target.tileOccupied.yPosition&&selected.tileOccupied.xPosition<target.tileOccupied.xPosition)
    {//if target is behind, it would have to have a y value greater than it and a x less than 
        flanking = true;
    }
    
    //west if x is smaller, it is behind
     else if(target.facing==3&&selected.tileOccupied.xPosition<target.tileOccupied.xPosition)
    {
        flanking = true;
    }
    //south east if x is smaller and y is smaller it is behind
     else if(target.facing==4&&selected.tileOccupied.yPosition<target.tileOccupied.yPosition&&selected.tileOccupied.xPosition<target.tileOccupied.xPosition)
    {
        flanking = true;
    }
    //south is y is smaller it is behind
     else if(target.facing==5&&selected.tileOccupied.yPosition>target.tileOccupied.yPosition)
    {
        flanking = true;
    }
    //south west if x is larger and y is smaller it is behind
     else if(target.facing==6&&selected.tileOccupied.yPosition<target.tileOccupied.yPosition&&selected.tileOccupied.xPosition>target.tileOccupied.xPosition)
    {
        flanking = true;
    }
    //west  if x is larger it is behind
     else if(target.facing==7&&selected.tileOccupied.xPosition>target.tileOccupied.xPosition)
    {
        flanking = true;
    }
     
     //north west, if y>other x>other
     else if(target.facing==8&&selected.tileOccupied.yPosition>target.tileOccupied.yPosition&&selected.tileOccupied.xPosition>target.tileOccupied.xPosition)
    {
        flanking = true;
    }
    return flanking;
    
}
    /*Name: Flanking
    Type: Attack Bonus
    Bonus or Negative: Bonus
    Modifies Which Stat: Attack
    Modifier Amount: +x/2 Attack
    Occurs When: A unit is facing the opposite direction from the unit attacking it.*/
    public static void setFlanking(Soldier soldier)
    {
        soldier.attack = soldier.attack + soldier.baseAttack/2;
        soldier.isFlanking = true;
    }
    public static void removeFlanking(Soldier soldier)
    {
        soldier.attack =  soldier.attack -  soldier.baseAttack/2;
        soldier.isFlanking = false;
    }
    
   public static boolean checkHeightAdvantage(Soldier selected, Soldier opponent)
   {
       boolean hasAdvantage = false;
       if(opponent.tileOccupied!=null)
       {
       if(selected.tileOccupied.zPosition>opponent.tileOccupied.zPosition)
       {
           hasAdvantage = true;
       }
       }
       return hasAdvantage;
   }
   public static boolean checkHeightDisadvantage(Soldier selected, Soldier opponent)
   { boolean hasDisAdvantage = false;
      
   if(opponent.tileOccupied!=null)
       {if(selected.tileOccupied.zPosition<opponent.tileOccupied.zPosition)
       {
           hasDisAdvantage = true;
       }
       }
       return hasDisAdvantage;
   }
    /*Name: Height advantage
    Type: Attack Bonus
    Bonus or Negative: Bonus
    Modifies Which Stat: Attack
    Modifier Amount: +x/4 attack
    Occurs When: A unit is attacking is on a tile that is 1 or more units higher than.
     */
    public static void setHeightAdvantage(Soldier soldier) 
    {
         soldier.attack =  soldier.attack +  soldier.baseAttack/4;
    }
    public static void setHeightDisAdvantage(Soldier soldier)
    {
            
         soldier.attack =  soldier.attack -  soldier.baseAttack/4;
    
    }
    public static void removeHeightAdvantage(Soldier soldier)
    {
         soldier.attack =  soldier.attack -  soldier.baseAttack/4;
    }
    /*	Name: Balance Bonus 
	Type: Attack
	Bonus or Negative: Bonus
	Modifies Which Stat: Attack & DMG
	Modifier Amount: +1/2x Attack & +1/2 DMG
	Occurs When: Cavalry Attacks Ranged: Spearmen attack Cavalry, Ranged Attack Spearmen.
*/
    public static void setBalanceBonus(Soldier soldier)
    {
         soldier.attack =  soldier.attack +  soldier.baseAttack/2;
         soldier.dmgBonus =  soldier.dmgBonus +  soldier.baseDmgBonus/2;
    }
    public static void removeBalanceBonus(Soldier soldier)
    {
         soldier.attack =  soldier.attack -  soldier.baseAttack/2;
         soldier.dmgBonus =  soldier.dmgBonus -  soldier.baseDmgBonus/2;
    }
    //Negatives
    /*Name: Wounded Units
	Type: Attack
	Bonus or Negative: Negative
	Modifies Which Stat: Attack
	Modifier Amount: -1/2x Attack
	Occurs When: A unit has half health
    */
    public static void setWounded(Soldier soldier)
    {
         soldier.attack =  soldier.attack -  soldier.baseAttack/2;
    }
    public static void removeWounded(Soldier soldier)
    {
        soldier.attack =  soldier.attack +  soldier.baseAttack/2;
    }
    /*Name: Wavering
	Type: Attack
	Bonus or Negative: Negative
	Modifies Which Stat: Attack
	Modifier Amount: -1/2x Attack
	Occurs When: A unit's morale is below 50%.*/
    public static void setWavering(Soldier soldier)
    {
         soldier.attack =  soldier.attack -  soldier.baseAttack/2;
    }
    public static void removeWavering(Soldier soldier)
    {
        soldier.attack =  soldier.attack +  soldier.baseAttack/2;
    }
//DEFENSE
    /*Name: Bracing
	Type: Defense
	Bonus or Negative: Bonus
	Modifies Which Stat: Defense
	Modifier Amount: +1/2 Defense Bonus Against Melee
	Occurs When: An infantry unit with the ability to 'brace' itself activates this ability.
*/
//Defending Modifiers - as the following modifiers modify a units defense, they will only help if a unit is defending. 
    public static void setBracing(Soldier soldier)
    {
       soldier.defense =  soldier.defense +  soldier.baseDefense/2;   
    }
    public static void removeBracing(Soldier soldier)
    {
       soldier.defense =  soldier.defense -  soldier.baseDefense/2;
    }
 /*
    Name: In Partial Cover 
	Type: Defense
	Bonus or Negative: Bonus
	Modifies Which Stat: Defense
	Modifer Amount: +1/4 Defense Bonus[AGAINST RANGED]
	Occurs When : A unit is standing in a 'cover' square
        with the cover element facing toward the ranged attacking unit.
    */
    public static void setPartialCover(Soldier soldier)
{
     soldier.defense =  soldier.defense+ soldier.baseDefense/4;
    
}
    public static void removePartialCover(Soldier soldier)
    {
         soldier.defense =  soldier.defense- soldier.baseDefense/4;
    }
/*
	Name: In Full Cover 
	Type: Defense
	Bonus or Negative: Bonus
	Modifies Which Stat: Defense
	Modifer Amount: +1/2 Defense Bonus[AGAINST RANGED]
	Occurs When : A unit is standing in a 'cover' square with the cover element facing toward the 	ranged attacking unit.
*/
    public static void setFullCover(Soldier soldier)
    {
         soldier.defense =  soldier.defense +  soldier.baseDefense/2;
    }
    public static void removeFullCover(Soldier soldier)
    {
         soldier.defense =  soldier.defense -  soldier.baseDefense/2;
    }
//NEGATIVES
    /*Name: Moving
	Type: Defense
	Bonus or Negative: Negative
	Modifies Which Stat: Defense
	Modifier Amount: -1/5x Defense
	Occurs When: A unit is moving and is attacked.
    */
    public static void setMoving(Soldier soldier)
    {
       soldier.defense =  soldier.defense -  soldier.baseDefense/5;
    }
    public static void removeMoving(Soldier soldier)
    {
      soldier.defense =  soldier.defense +  soldier.baseDefense/5;
    }
//MOVEMENT
    //BONUSES
    /*Name: Sprint
	Type: Movement
	Bonus or Negative: Bonus
	Modifies Which Stat: Movement
	Modifier Amount: +Speed*2
	Occurs When: A unit with activates this ability.*/
    public static void sprint(Soldier soldier)
    {
         soldier.speed =  soldier.baseSpeed +  soldier.speed;
    }
    public static void removeSprint(Soldier soldier)
    {
         soldier.speed =  soldier.speed - soldier.baseSpeed;
    }
    //NEGATIVES
    /*Name: Rough Terrain LvL 1
	Type: Movement
	Bonus or Negative: Negative
	Modifies Which Stat: Speed
	Modifier Amount: -1 Speed
	Occurs When: A unit is attempting to move across terrain considered to be 'rough'. Light 	foliage, some rock, slight incline or slight mud.
*/
    /*
	Name: Rough Terrain LvL 2
	Type: Movement
	Bonus or Negative: Negative
	Modifies Which Stat: Speed
	Modifier Amount: -2 Speed
	Occurs When: A unit is attempting to move across terrain considered to be 'rough'. Medium	foliage, medium rocks or medium mud. 
    */
    /*
	Name: Rough Terrain LvL 3
	Type: Movement
	Bonus or Negative: Negative
	Modifies Which Stat: Speed
	Modifier Amount: -3 Speed
	Occurs When: A unit is attempting to move across terrain considered to be 'rough'. Harsh 	foliage, harsh rocks, harsh incline or heavy mud. 
    */
    /*
	Name: Rough Terrain LvL 4
	Type: Movement
	Bonus or Negative: Negative
	Modifies Which Stat: Speed
	Modifier Amount: -4 Speed
	Occurs When: A unit is attempting to move across terrain considered to be 'rough'. Extreme	foliage, extreme rocks, extreme incline or extreme mud.	
	*/
    
    public static void setTerrain(Soldier soldier, int tLevel)
    {
         soldier.speed =  soldier.speed - tLevel;
    }
    public static void removeTerrain(Soldier soldier, int tLevel)
    {
         soldier.speed =  soldier.speed + tLevel;
    }
    
    
    public static void heal(Soldier soldier, int value)
    {
        soldier.hp=soldier.hp+value;
    }
    // Reset Values
    //This shold be called every turn, and the bonuses than added on later.
    public static void resetValues(Soldier soldier)
    {
        
        soldier.attack =  soldier.baseAttack;
        soldier.dmg = (int)soldier.baseDmg;
        soldier.dmgBonus = soldier.baseDmgBonus;
        soldier.armorClass =  soldier.baseArmorClass;
        soldier.speed =  soldier.baseSpeed;
        soldier.range =  soldier.baseRange;
        soldier.chargeBonus =  soldier.baseChargeBonus;
        //modify to include regeneration instead 
        soldier.stamina =  soldier.stamina+1;
        soldier.morale =  soldier.baseMorale;
    }
}
