package historicalbattlesimulatorbasic;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    ArrayList<JButton> terrainImages = new ArrayList();
   // ArrayList<
    int numOfUnitsToPlace = 0;
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
    this.numOfUnitsToPlace = images.size();
    setUpButtons(images);
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
//                  
                    System.out.println(t.getName());
                }
            });
           // button.setContentAreaFilled(false);
            button.setBorderPainted(false);
           //button.setBackground(Color.black);
        }
    }
   
}


