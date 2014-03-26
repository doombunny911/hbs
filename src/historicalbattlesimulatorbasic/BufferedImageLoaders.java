/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Edward
 * This class loads all different buffered images. All methods are static and can be called from any other class
 */
public class BufferedImageLoaders 
{
    
    private ArrayList<BufferedImageName> imageList = new ArrayList<>();
    BufferedImage grass = grassLoader();
    BufferedImage tree, dirt, rock;
    BufferedImage attackB, defendB, cancelB, setFormB, moveB, checkB, scroll;
    ArrayList<BufferedImage> buttons = new ArrayList();
    private BufferedImage background;
    private BufferedImage welcomePanelImage;
    public void loadAllButtons()
    {
      try {
           attackB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"Attack.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      
      try {
           defendB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"Defend.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      try {
           cancelB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"Cancel.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      try {
           checkB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"CheckStats.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      
      try {
           moveB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"Move.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      try {
           setFormB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"SetFormation.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
//      try {
//           setFormB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"SetFormation.png"));
//       } catch (IOException ex) {
//           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
//       }
      try {
           scroll = ImageIO.read(new File("Sprites"+File.separator+"Background"+File.separator+"ScrollBG.jpg"));

//           setFormB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"SetFormation.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
     
    }
    
    public void loadScroll()
    {
         try {
           scroll = ImageIO.read(new File("Sprites"+File.separator+"Background"+File.separator+"ScrollBG.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public ImageIcon getIconAttack()
    {
        
       ImageIcon attackImage = new ImageIcon(attackB);
            Image img = attackImage.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
            attackImage = new ImageIcon(newimg);
            return attackImage;
    }
    public ImageIcon getIconDefend()
    {
        ImageIcon defendImage = new ImageIcon(defendB);
            Image img = defendImage.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
            defendImage = new ImageIcon(newimg);
            return defendImage;
    }
    
    public ImageIcon getIconSetFormation()
    {
        ImageIcon setFormImage = new ImageIcon(setFormB);
            Image img = setFormImage.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
            setFormImage = new ImageIcon(newimg);
            return setFormImage;
    }
    public ImageIcon getIconCancel()
    {
        ImageIcon cancelImage = new ImageIcon(cancelB);
            Image img = cancelImage.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
            cancelImage = new ImageIcon(newimg);
            return cancelImage;
    }
    public ImageIcon getIconMove()
    {
        ImageIcon moveImage = new ImageIcon(moveB);
            Image img = moveImage.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
            moveImage = new ImageIcon(newimg);
            return moveImage;
    }
    public ImageIcon getCheckStatsIcon()
    {
        ImageIcon checkStatsImage = new ImageIcon(checkB);
            Image img = checkStatsImage.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
            checkStatsImage = new ImageIcon(newimg);
            return checkStatsImage;
    }
    public Image getScroll()
    {
      ImageIcon scrollBG = new ImageIcon(scroll);
            Image img = scrollBG.getImage();
            Image newimg = img.getScaledInstance(1000, 1000,  java.awt.Image.SCALE_SMOOTH); 
            
            return newimg;
    }
    public void loadAllImages()
    {
       imageList = BufferedImageMassImport.loadAllImages();
      
    }
    
    public ArrayList<BufferedImageName> getImages()
    {
        return imageList;
    }
    public void loadTopScreen()
    {
         try {
           welcomePanelImage = ImageIO.read(new File("Sprites"+File.separator+"Background"+File.separator+"WelcomePanel.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public BufferedImage getTopScreen()
    {
        return welcomePanelImage;
    }
    public void loadBackground()
    {
        try {
           background = ImageIO.read(new File("Sprites"+File.separator+"Background"+File.separator+"havok.jpg"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
 
    }
    public BufferedImage getBackground()
    {
        
        //BufferedImage background1 = new BufferedImage(this.backg
        return background;
    }
    public BufferedImage getImage(String imageName)
    {
        BufferedImage result = null;
        for(BufferedImageName bin: imageList)
        {
            if(bin.getName().equals(imageName))
            {
               result =bin.getImage();
            }
        }
        return result;
    }
    public BufferedImage getGrass()
    {
        return grass;
    }
       public static BufferedImage grassLoader() 
    {
       BufferedImage grass1=null;
       try {
           grass1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"greenGround.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
       return grass1;
    }
      public static BufferedImage rockLoader() 
    {
       BufferedImage rock1=null;
       try {
           rock1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"rocksGround.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
       return rock1;
    }
      
    
      public static BufferedImage dirtLoader() 
    {
       BufferedImage brown1=null;
       try {
           brown1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"brownGround.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
       return brown1;
    }
      public static BufferedImage blackKnightLoader() 
    {
       BufferedImage brown1=null;
       try {
           brown1 = ImageIO.read(new File("Sprites"+File.separator+"Unit Sprites"+File.separator+"blackKnight.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
       return brown1;
    }
       public static BufferedImage redKnightLoader() 
    {
       BufferedImage brown1=null;
       try {
           brown1 = ImageIO.read(new File("Sprites"+File.separator+"Unit Sprites"+File.separator+"redKnight.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
       return brown1;
    }
       public static BufferedImage unitPicLoader(Unit unit)
       {
       BufferedImage img = new BufferedImage(unit.xWidth, unit.yHeight, 4);
        try {
             img = ImageIO.read(new File(unit.spriteName));
        } catch (IOException ex) {
            Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return img;
       }
       
}
