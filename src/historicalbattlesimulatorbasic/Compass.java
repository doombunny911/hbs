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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
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
public final class Compass extends Applet implements ActionListener
{
     
//The resizing here is a bit interesting
        public JButton[] buttonArray = new JButton[8];
        ImageIcon northImage = new ImageIcon("Sprites/Compass/North.png");
            Image img = northImage.getImage();
            Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon northImage2 = new ImageIcon(newimg);
        JButton north = new JButton(northImage2) ;
            
        ImageIcon northEastImage = new ImageIcon("Sprites/Compass/NorthEast.png");
            Image img2 = northEastImage.getImage();
            Image newimg2 = img2.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon northEastImage2 = new ImageIcon(newimg2);
        JButton northEast = new JButton(northEastImage2);
        
        
        ImageIcon eastImage = new ImageIcon("Sprites/Compass/East.png");
            Image img3 = eastImage.getImage();
            Image newimg3 = img3.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon eastImage2 = new ImageIcon(newimg3);
        JButton east = new JButton(eastImage2);
        
        ImageIcon southEastImage = new ImageIcon("Sprites/Compass/SouthEast.png");
            Image img4 = southEastImage.getImage();
            Image newimg4 = img4.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon southEastImage2 = new ImageIcon(newimg4);
        JButton southEast = new JButton(southEastImage2);
        
        ImageIcon southImage = new ImageIcon("Sprites/Compass/South.png");
            Image img5 = southImage.getImage();
            Image newimg5 = img5.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon southImage2 = new ImageIcon(newimg5);
        JButton south = new JButton(southImage2);
        
        ImageIcon southWestImage = new ImageIcon("Sprites/Compass/SouthWest.png");
            Image img6 = southWestImage.getImage();
            Image newimg6 = img6.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon southWestImage2 = new ImageIcon(newimg6);
        JButton southWest = new JButton(southWestImage2);
        
        ImageIcon westImage = new ImageIcon("Sprites/Compass/West.png");
            Image img7 = westImage.getImage();
            Image newimg7 = img7.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon westImage2 = new ImageIcon(newimg7);
        JButton west = new JButton (westImage2);
        
        ImageIcon northWestImage = new ImageIcon("Sprites/Compass/NorthWest.png");
            Image img8 = northWestImage.getImage();
            Image newimg8 = img8.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon northWestImage2 = new ImageIcon(newimg8);
        JButton northWest = new JButton(northWestImage2);
        
        ImageIcon holderImage = new ImageIcon("Sprites/Compass/Circle.png");
            Image img9 = holderImage.getImage();
            Image newimg9 = img9.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon holderImage2 = new ImageIcon(newimg9);
        JButton holder = new JButton(holderImage2);
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
        
        //gotta repaint your JComponents son
        newJ.repaint();
        newJ.revalidate();
       
    }
   
    public Compass()
    {   
       this.north.setOpaque(false);
       this.north.setContentAreaFilled(false);
       this.north.setBorderPainted(false);
       this.northEast.setOpaque(false);
       this.northEast.setContentAreaFilled(false);
       this.northEast.setBorderPainted(false);
       this.northWest.setOpaque(false);
       this.northWest.setContentAreaFilled(false);
       this.northWest.setBorderPainted(false);
       this.east.setOpaque(false);
       this.east.setContentAreaFilled(false);
       this.east.setBorderPainted(false);
       this.west.setOpaque(false);
       this.west.setContentAreaFilled(false);
       this.west.setBorderPainted(false);
       this.south.setOpaque(false);
       this.south.setContentAreaFilled(false);
       this.south.setBorderPainted(false);
       this.southEast.setOpaque(false);
       this.southEast.setContentAreaFilled(false);
       this.southEast.setBorderPainted(false);
       this.southWest.setOpaque(false);
       this.southWest.setContentAreaFilled(false);
       this.southWest.setBorderPainted(false);
       this.holder.setOpaque(false);
       this.holder.setContentAreaFilled(false);
       this.holder.setBorderPainted(false);
      // comp.setLayout(new GridLayout(3,3));
      // this.setUpVisuals();
    }
        @Override
    public void init() {
         setLayout(new GridLayout(3,3));
         add(northWest);
                    northWest.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  GUI.unitSelected.moveNorthWest();
                }});
         add(north);
                north.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  GUI.unitSelected.moveNorth();
                }});
         add(northEast);
                northEast.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  GUI.unitSelected.moveNorthEast();
                }});
                  
         add(west);
                west.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  GUI.unitSelected.moveWest();
                }});
         add(holder);
                holder.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  //DISPLAY SOMETHING ELSE HERE
                }});
         add(east);
                east.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  GUI.unitSelected.moveEast();
                }});
         add(southWest);
                southWest.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  GUI.unitSelected.moveSouthWest();
                }});       
         add(south);
                south.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  GUI.unitSelected.moveSouth();
                }});
         add(southEast);
                 southEast.addActionListener(new ActionListener() {
                     @Override
                 public void actionPerformed(ActionEvent e)
                 {
                   GUI.unitSelected.moveSouthEast();
                 }});
     }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
    
    
}