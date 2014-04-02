/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import static historicalbattlesimulatorbasic.BufferedImageMassImport.dir;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Edward
 */
public class MapLoader
{
 final JFileChooser fc = new JFileChooser();
        File dir = new File("Maps"+File.separator+"anchorM.txt");
//        
//        System.out.println(dir);
//        fc.setCurrentDirectory(dir);
//        File  current = fc.getCurrentDirectory();
//        
//        System.out.println(current);
//        fc.showOpenDialog(null);
        
  
       
        String name = fc.getSelectedFile().getName();   
}
