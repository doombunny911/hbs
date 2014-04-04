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
    BufferedImageName grass = grassLoader();
    BufferedImageName tree, dirt, rock;
    BufferedImage attackB, defendB, cancelB, setFormB, moveB, checkB, scroll, squareB, wedgeB, lineB;
    ArrayList<BufferedImage> buttons = new ArrayList();
    private BufferedImage background;
    private BufferedImage welcomePanelImage;
    private BufferedImage runB;
    private BufferedImage createMapB;
    private BufferedImage createScenarioB;
    private BufferedImage loadB;
    private BufferedImage unitCreatorB;
    private BufferedImage endTurnB;
    public void loadAllButtons()
    {
      try {
           squareB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"squareFormation.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      try {
           wedgeB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"wedgeFormation.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      try {
           lineB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"lineFormation.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
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
      try {
           setFormB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"SetFormation.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      try {
           scroll = ImageIO.read(new File("Sprites"+File.separator+"Background"+File.separator+"ScrollBG.jpg"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      try {
           setFormB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"SetFormation.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
      try {
           endTurnB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"endTurn.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
     
    }
    
    
    
      public void loadMenuButtons()
    {
    
        try {
            runB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"runSimulation.png"));
            createMapB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"MapCreator.png"));
            createScenarioB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"ScenarioCreator.png"));
                   
            loadB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"LoadGame.png"));
            
            unitCreatorB = ImageIO.read(new File("Sprites"+File.separator+"Buttons"+File.separator+"UnitCreator.png"));
        } catch (IOException ex) {
            Logger.getLogger(BufferedImageLoaders.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
      
    }
    public ImageIcon getIconRunSimulation()
    {
          ImageIcon runBIcon = new ImageIcon(runB);
            Image img = runBIcon.getImage();
            Image newimg = img.getScaledInstance(100, 30,  java.awt.Image.SCALE_SMOOTH); 
            runBIcon = new ImageIcon(newimg);
            return runBIcon;
    }
    
     public ImageIcon getIconCreateMap()
    {
          ImageIcon createMapIcon = new ImageIcon(createMapB);
            Image img = createMapIcon.getImage();
            Image newimg = img.getScaledInstance(100, 30,  java.awt.Image.SCALE_SMOOTH); 
            createMapIcon = new ImageIcon(newimg);
            return createMapIcon;
    }
     public ImageIcon getIconEndTurn()
    {
          ImageIcon endTurn = new ImageIcon(endTurnB);
            Image img = endTurn.getImage();
            Image newimg = img.getScaledInstance(100, 30,  java.awt.Image.SCALE_SMOOTH); 
            endTurn = new ImageIcon(newimg);
            return endTurn;
    }
     public ImageIcon getIconCreateScenario()
    {
          ImageIcon createScenarioIcon = new ImageIcon(createScenarioB);
            Image img = createScenarioIcon.getImage();
            Image newimg = img.getScaledInstance(100, 30,  java.awt.Image.SCALE_SMOOTH); 
            createScenarioIcon = new ImageIcon(newimg);
            return createScenarioIcon;
    }
     public ImageIcon getIconCreateUnit()
    {
          ImageIcon createUnitIcon = new ImageIcon(unitCreatorB);
            Image img = createUnitIcon.getImage();
            Image newimg = img.getScaledInstance(100, 30,  java.awt.Image.SCALE_SMOOTH); 
            createUnitIcon = new ImageIcon(newimg);
            return createUnitIcon;
    }
      public ImageIcon getIconLoadGame()
    {
          ImageIcon loadGameIcon = new ImageIcon(loadB);
            Image img = loadGameIcon.getImage();
            Image newimg = img.getScaledInstance(100, 30,  java.awt.Image.SCALE_SMOOTH); 
            loadGameIcon = new ImageIcon(newimg);
            return loadGameIcon;
    }
     
     public ImageIcon getIconLoadGameMap()
    {
          ImageIcon createMapIcon = new ImageIcon(createMapB);
            Image img = createMapIcon.getImage();
            Image newimg = img.getScaledInstance(100, 30,  java.awt.Image.SCALE_SMOOTH); 
            createMapIcon = new ImageIcon(newimg);
            return createMapIcon;
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
    public ImageIcon getSquareFormIcon()
    {
        ImageIcon squareFormation = new ImageIcon(squareB);
            Image img = squareFormation.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
            squareFormation = new ImageIcon(newimg);
            return squareFormation;
    }
     public ImageIcon getWedgeFormIcon()
    {
        ImageIcon wedgeForm = new ImageIcon(wedgeB);
            Image img = wedgeForm.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
            wedgeForm = new ImageIcon(newimg);
            return wedgeForm;
    }
      public ImageIcon getLineFormIcon()
    {
        ImageIcon lineFormation = new ImageIcon(lineB);
            Image img = lineFormation.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); 
            lineFormation = new ImageIcon(newimg);
            return lineFormation;
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
    public BufferedImageName getGrass()
    {
        return grass;
    }
       public static BufferedImageName grassLoader() 
    {
       BufferedImage grass1=null;
       try {
           grass1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"greenGround.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
       BufferedImageName grass = new BufferedImageName(grass1, "greenGround.png");
       return grass;
    }
      public static BufferedImageName rockLoader() 
    {
       BufferedImage rock1=null;
       try {
           rock1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"rocksGround.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       }
       BufferedImageName rock = new BufferedImageName(rock1, "rocksGround.png");
       return rock;
    }
      
    
      public static BufferedImageName dirtLoader() 
    {
       BufferedImage brown1=null;
       try {
           brown1 = ImageIO.read(new File("Sprites"+File.separator+"Terrain"+File.separator+"brownGround.png"));
       } catch (IOException ex) {
           Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
       } 
       BufferedImageName dirt = new BufferedImageName(brown1, "brownGround.png");
       return dirt;
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
