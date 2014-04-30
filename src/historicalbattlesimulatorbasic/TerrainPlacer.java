package historicalbattlesimulatorbasic;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Edward
 */
public final class TerrainPlacer extends JPanel
{ 
    BufferedImageLoaders terLoader;
    Tile terrainToBeLoaded = null;
    int size =0;
    
    ArrayList<Tile> tilesToSave = new ArrayList();
    ArrayList<JButton> terrainImages = new ArrayList();
   // ArrayList<
    int numOfTerrainPlaces = 0;
    public static void main(String[] args){
        BufferedImageLoaders bil = new BufferedImageLoaders();
        bil.loadAllImages();
        ArrayList<BufferedImageName> image = bil.getImages();
        TerrainPlacer tp = new TerrainPlacer(image);
        
        JFrame newJ = new JFrame();
        newJ.setVisible(true);
        newJ.setSize(500,200);
        newJ.add(tp);
        newJ.repaint();
        newJ.revalidate();
    }
    public TerrainPlacer(ArrayList<BufferedImageName> images){
    
        terLoader = new BufferedImageLoaders();
        terLoader.loadAllImages();
        this.numOfTerrainPlaces = images.size();
        setUpButtons(images);
        this.setVisible(true);
        this.setSize(GUI.gameFrame.getWidth(),100);
       
        
   }
    public void setUpButtons(ArrayList<BufferedImageName> imageArrayList){
        JOptionPane.showMessageDialog(this, "Click on a terrain image to place it on the field of battle");
        for(final  BufferedImageName t: imageArrayList)
        {         
            ImageIcon terrainImage = new ImageIcon(t.getImage());
            Image img = terrainImage.getImage();
            Image newimg = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon terrainImage2 = new ImageIcon(newimg);
            System.out.println(t.getName());
            JButton button = new JButton(terrainImage2);
            button.setOpaque(false);
            
            add(button);
            
                button.addActionListener(new ActionListener() 
                {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
//                  Add tile to be loaded here
                    terrainToBeLoaded = new Tile(0, 0, 10, 10);
                    terrainToBeLoaded.setImage(t);
                    System.out.println(t.getName());
                }
            });
           // button.setContentAreaFilled(false);
            button.setBorderPainted(false);
           //button.setBackground(Color.black);
        }
        JButton increaseSize = new JButton(" + Size");
        increaseSize.addActionListener(new ActionListener() 
         {
      
                    @Override
                public void actionPerformed(ActionEvent e)
                {
//                  Add tile to be loaded here
                   size = size+1;
                }
            });
       JButton decreaseSize = new JButton(" - Size");
       decreaseSize.addActionListener(new ActionListener() 
     {
      
                    @Override
                public void actionPerformed(ActionEvent e)
                {
//                  Add tile to be loaded here
                   size = size-1;
                }
            });
      JButton exit = new JButton("Close Map Creator and Save");
      exit.addActionListener(new ActionListener() 
     {
      
                    @Override
                public void actionPerformed(ActionEvent e)
                {
//
                        String fileName = JOptionPane.showInputDialog("Enter name for the map:");
                        try {
                            GUI.tp.saveMap(fileName);
                        } catch (IOException ex) {
                            Logger.getLogger(TerrainPlacer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        GUI.terrainPlacerActive=false;
                        
                        Historicalbattlesimulatorbasic.Start = new Openingmenuscreen();
                }
            });
       add(increaseSize);
       add(decreaseSize);
       add(exit);
    }

   public void addToSaver(Tile t) 
    {
       tilesToSave.add(t);
    }
   
public void saveMap(String fileName2) throws IOException {
        PrintWriter writer = null;
        String fileName=fileName2+".txt";
        File file = new File("Maps"+File.separator+fileName);
        File parent = file.getParentFile();

if(!parent.exists() && !parent.mkdirs()){
    throw new IllegalStateException("Couldn't create dir: " + parent);
} 
        try {                      
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            writer.println("---"); //seperator 
           for(Tile t: tilesToSave)
           {
               writer.println(t.xPosition);
               writer.println(t.yPosition);
               writer.println(t.tileBlocked);
               writer.println(t.image.name);
           }
            System.out.println("Saved");
            writer.close();
             } 
        catch (FileNotFoundException ex)
             {
            System.out.println("Unable to save file");
        } 
        finally {
            writer.close();
        }
        GUI.terrainPlacerActive = false;
    }
}


