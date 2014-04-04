/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Edward
 */
public class CombatPanel extends JPanel
{
    Unit unitSelected;
    ArrayList<Unit> enemyUnits;
    
    //get all units in range
    public void initPanel()
    {
        unitSelected = GUI.unitSelected;
        enemyUnits = unitSelected
    }
    //Generate buttons with stats on them as well
    //When button is clicked, run 'attack' [subtract one point]
    //
    
}
