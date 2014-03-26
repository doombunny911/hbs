/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package historicalbattlesimulatorbasic;


/**
 *
 * @author Andrew
 */

public class UnitDraw
{
    
    //the position of the top left point of the unit
    int xDraw,yDraw; //x and y position
    Unit thisUnit; //the unit being drawn
    Tile tileLocationOfUnit; //tile location of where the unit is at
    
    //this constructor should be used when first loading in the units
    public UnitDraw(Unit getUnit)
    {
        this.xDraw=getUnit.xPosition;
        this.yDraw=getUnit.yPosition;
        System.out.println("in UnitDraw, GUI.tileClicked is initailzed " + GUI.tileClicked);
        if(getUnit.currentFormation==null)
        {
            
            UnitFormations form = new UnitFormations(getUnit,GUI.tileClicked);
            form.defaultFormation(GUI.tileClicked);
            getUnit.currentFormation=form;
        }
        else
        {
            //this will later call which formation it is and draw according to that pattern
            getUnit.currentFormation.paintFormation(GUI.panel.getGraphics());
        }
        
        this.tileLocationOfUnit=GUI.tileClicked;
        this.thisUnit=getUnit;
    }
    /*
    Returns the specific unit
    */
    public Unit getThisUnit()
    {
        return thisUnit;
    }

    
    //this constructor should be used for future updates to the unit location
    public UnitDraw(Unit getUnit,Tile tile)
    {
        this.xDraw=getUnit.xPosition;
        this.yDraw=getUnit.yPosition;
        
        if(getUnit.currentFormation==null)
        {
            
            UnitFormations form = new UnitFormations(getUnit,GUI.tileClicked);
            form.defaultFormation(GUI.tileClicked);
            getUnit.currentFormation=form;
        }
        else
        {
            //this will later call which formation it is and draw according to that pattern
            getUnit.currentFormation.paintFormation(GUI.panel.getGraphics());
        }
        
        this.tileLocationOfUnit=tile;
        this.thisUnit=getUnit;
    }
    //just add where they need to be painted and this method will do it


}
