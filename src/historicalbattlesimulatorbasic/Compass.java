package historicalbattlesimulatorbasic;

import static historicalbattlesimulatorbasic.Openingmenuscreen.gameFrame;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edward
 */
public final class Compass extends Applet
{
        public JButton[] buttonArray = new JButton[8];
        JButton north = new JButton("N");
        JButton northEast = new JButton("NE");
        JButton east = new JButton("E");
        JButton southEast = new JButton("SE");
        JButton south = new JButton("S");
        JButton southWest = new JButton("SW");
        JButton west = new JButton ("W");
        JButton northWest = new JButton("NW");
        JButton holder = new JButton(" Cancel ");
        JPanel compassPanel = new JPanel(new BorderLayout());
        Compass comp;
        
        
    public static void main(String[] args)
    {  
        
        JFrame newJ = new JFrame();
        newJ.setVisible(true);
        newJ.setSize(200,200);
        Compass c = new Compass();
        c.init();
        newJ.add(c);
       
    }
    
    public Compass()
    {   
       
      // comp.setLayout(new GridLayout(3,3));
      // this.setUpVisuals();
    }
    public void init() {
         setLayout(new GridLayout(3,3));
         add(northWest);
         add(north);
         add(northEast);
         add(west);
         add(holder);
         add(east);
         add(southWest);
         add(south);
         add(southEast);
     }
    
    
    
    
}