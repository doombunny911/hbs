/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
/**
 *
 * 
 * Credit to http://stackoverflow.com/questions/11300847/load-and-display-all-the-images-from-a-folder 
 * Edited by Edward Schmalz
 */
public class BufferedImageMassImport {
  

    // File representing the folder that you select using a FileChooser
    static final File dir = new File("Sprites"+File.separator+"Terrain");
    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
        "gif", "png", "bmp" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

  @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

public static ArrayList<BufferedImageName> loadAllImages(){
    ArrayList<BufferedImageName> bin = new ArrayList();
  if (dir.isDirectory()) { // make sure it's a directory
         for (final File f : dir.listFiles(IMAGE_FILTER)) {
             BufferedImage img = null;

              try {
                    img = ImageIO.read(f);
                    BufferedImageName ad = new BufferedImageName(img, f.getName());
                    bin.add(ad);
                    // you probably want something more involved here
                    // to display in your UI
                    System.out.println("image: " + f.getName());
                    System.out.println(" width : " + img.getWidth());
                    System.out.println(" height: " + img.getHeight());
                    System.out.println(" size  : " + f.length());
                } catch (final IOException e) {
                    // handle errors here
                }
            }
        }
        return bin;
    }
}

