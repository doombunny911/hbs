/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Edward
 */
public class BackgroundImage extends JPanel
{
    Image img;
      public BackgroundImage()
    {
         BufferedImage bg = BufferedImageLoaders.loadBackground();
        // Loads the background image and stores in img object.
        img = bg;
    }
      public static void initBackground(Graphics g)
      {
          BackgroundImage bits = new BackgroundImage();
          bits.paint(g);
      }
    public void paint(Graphics g)
    {
        // Draws the img to the BackgroundPanel.
        g.drawImage(img, 0, 0, Color.black, this);
    }
}
