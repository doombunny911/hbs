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
    ArrayList<JButton> terrainImages = new ArrayList();
   // ArrayList<
    int numOfUnitsToPlace = 0;
    public static void main(String[] args)
    {
        UnitLoader ul = new UnitLoader();
        ArrayList units = ul.runLoader();
        UnitPlacer up = new UnitPlacer(units);
        up.setUpButtons(units);
        
        JFrame newJ = new JFrame();
        newJ.setVisible(true);
        newJ.setSize(500,200);
        newJ.add(up);
        newJ.repaint();
        newJ.revalidate();
    }
   public TerrainPlacer(ArrayList<Unit> unitArrayList)
   {
    BufferedImageLoaders terLoader = new BufferedImageLoaders();
    terLoader.loadAllImages();
    this.numOfUnitsToPlace = unitArrayList.size();
    setUpButtons(unitArrayList);
   }
    public void setUpButtons(ArrayList<Unit> unitArrayList)
    {
        JOptionPane.showMessageDialog(this, "Click on a unit to place it on the field of battle");
        for(final Unit u: unitArrayList)
        {         
            ImageIcon unitImage = new ImageIcon(Unit.getUnitPic(u));
            Image img = unitImage.getImage();
            Image newimg = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon unitImage2 = new ImageIcon(newimg);
            JButton button = new JButton(u.nameOfUnit,unitImage2) ;
            unitImages.add(button);
            button.setOpaque(false);
            add(button);
                button.addActionListener(new ActionListener() 
                {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
//                  
                    System.out.println(u.nameOfUnit);
                }

                    
            });
           // button.setContentAreaFilled(false);
            button.setBorderPainted(false);
           //button.setBackground(Color.black);
        }
    }
   
}


