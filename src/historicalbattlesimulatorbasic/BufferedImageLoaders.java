/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import historicalbattlesimulatorbasic.Tile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
    
    
    public void loadAllButtons()
    {
        
    }
    public void loadAllImages()
    {
       imageList = BufferedImageMassImport.loadAllImages();
      
    }
    
    public ArrayList<BufferedImageName> getImages()
    {
        return imageList;
    }
    public static final BufferedImage loadBackground()
    {
        BufferedImage background = null;
        try {
           background = ImageIO.read(new File("Sprites"+File.separator+"Background"+File.separator+"havok.jpg"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
        
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
