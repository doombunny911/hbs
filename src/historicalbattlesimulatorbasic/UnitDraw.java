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
    public UnitDraw(Unit getUnit){
        this.xDraw=getUnit.xPosition;
        this.yDraw=getUnit.yPosition;
        System.out.println("In UnitDraw, GUI.tileClicked is initailzed " + GUI.tileClicked);
        if(getUnit.currentFormation==null)
        {
            System.out.println("currentFormation is null");
            UnitFormations form = new UnitFormations(getUnit,GUI.tileClicked);
            form.rowFormation();
            getUnit.currentFormation=form;
        }
        else
        {
            System.out.println("currentFormation is not null in UnitDraw giving only unit in parameter");
            //this will later call which formation it is and draw according to that pattern
//            getUnit.currentFormation.paintFormation(GUI.panel.getGraphics());
        }
        
        this.tileLocationOfUnit=GUI.tileClicked;
        this.thisUnit=getUnit;
    }
    /*
    Returns the specific unit
    */
    public Unit getThisUnit(){
        return thisUnit;
    }

    
    //this constructor should be used for future updates to the unit location
    public UnitDraw(Unit getUnit,Tile tile){
        this.xDraw=getUnit.xPosition;
        this.yDraw=getUnit.yPosition;
        UnitFormations form = getUnit.currentFormation;
        if(getUnit.currentFormation==null)
        {
            this.tileLocationOfUnit=tile;
            this.thisUnit=getUnit;
            System.out.println("in unitDraw in a part that shouldn't happen, need fixing");
            
            form = new UnitFormations(getUnit,tile);
            form.rowFormation();
            getUnit.currentFormation=form;
        }
        else
        {
            form.updateTileAndUnit(getUnit, tile); //updates the current unit and tile
                if(form.whichFormation==UnitFormations.LINE)
                {
                    form.rowFormation();
                }
                else if(form.whichFormation==UnitFormations.SQUARE)
                {
                    form.setBoxFormation();
                }
                else if(form.whichFormation==UnitFormations.WEDGE)
                {
                    form.setWedgeFormation();
                }
                else if(form.whichFormation==UnitFormations.COLUMN)
                {
                    form.setColumnFormation();
                }
        }
        this.tileLocationOfUnit=tile;
        this.thisUnit=getUnit;
    }
}
