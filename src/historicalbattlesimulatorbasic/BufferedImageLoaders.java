/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Edward
 */
public class BufferedImageLoaders {
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
}
