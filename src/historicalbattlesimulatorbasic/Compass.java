package historicalbattlesimulatorbasic;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Edward
 * edited: Andrew Phillips
 */
public final class Compass extends JPanel{

//The resizing here is a bit interesting
        ImageIcon northImage = new ImageIcon("Sprites/Compass/North.png");
            Image img = northImage.getImage();
            Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon northImage2 = new ImageIcon(newimg);
        JButton north = new JButton(northImage2) ;
            
        ImageIcon northEastImage = new ImageIcon("Sprites/Compass/NorthEast.png");
            Image img2 = northEastImage.getImage();
            Image newimg2 = img2.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon northEastImage2 = new ImageIcon(newimg2);
        JButton northEast = new JButton(northEastImage2);
        
        
        ImageIcon eastImage = new ImageIcon("Sprites/Compass/East.png");
            Image img3 = eastImage.getImage();
            Image newimg3 = img3.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon eastImage2 = new ImageIcon(newimg3);
        JButton east = new JButton(eastImage2);
        
        ImageIcon southEastImage = new ImageIcon("Sprites/Compass/SouthEast.png");
            Image img4 = southEastImage.getImage();
            Image newimg4 = img4.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon southEastImage2 = new ImageIcon(newimg4);
        JButton southEast = new JButton(southEastImage2);
        
        ImageIcon southImage = new ImageIcon("Sprites/Compass/South.png");
            Image img5 = southImage.getImage();
            Image newimg5 = img5.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon southImage2 = new ImageIcon(newimg5);
        JButton south = new JButton(southImage2);
        
        ImageIcon southWestImage = new ImageIcon("Sprites/Compass/SouthWest.png");
            Image img6 = southWestImage.getImage();
            Image newimg6 = img6.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon southWestImage2 = new ImageIcon(newimg6);
        JButton southWest = new JButton(southWestImage2);
        
        ImageIcon westImage = new ImageIcon("Sprites/Compass/West.png");
            Image img7 = westImage.getImage();
            Image newimg7 = img7.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon westImage2 = new ImageIcon(newimg7);
        JButton west = new JButton (westImage2);
        
        ImageIcon northWestImage = new ImageIcon("Sprites/Compass/NorthWest.png");
            Image img8 = northWestImage.getImage();
            Image newimg8 = img8.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon northWestImage2 = new ImageIcon(newimg8);
        JButton northWest = new JButton(northWestImage2);
        JButton toggleSprint = new JButton();
        int moveDirection;
        int previousMoveDirection;
    public static void main(String[] args) {  
        JFrame newJ = new JFrame();
        newJ.setVisible(true);
        newJ.setSize(200,200);
        Compass c = new Compass();
        c.init();
        newJ.add(c);
        
        //gotta repaint your JComponents son
        newJ.repaint();
        newJ.revalidate();
       
    }
    public Compass(){    
        removeJButtonDefaults();
    }     
    public void init() {
         setLayout(new GridLayout(3,3));
         add(northWest);
                    northWest.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  moveDirection=8;
                  moveLogic();
                }});
         add(north);
                north.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  moveDirection=1;
                  moveLogic();
                }});
         add(northEast);
                northEast.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  moveDirection=2;
                  moveLogic();
                }});
                  
         add(west);
                west.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  moveDirection=7;
                  moveLogic();
                }});
                
         add(toggleSprint);
         
              toggleSprint.addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e)
                  {
                      System.out.println("Toggle Sprint in Compass button");
                      if(GUI.unitSelected.isSprinting)
                      {
                          Modifier.removeSprint(GUI.unitSelected);
                      }
                      else
                          GUI.unitSelected.sprint();
                  }});
              
         add(east);
                east.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  moveDirection=3;
                  moveLogic();
                }});
         add(southWest);
                southWest.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  moveDirection=6;
                  moveLogic();
                }});       
         add(south);
                south.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e)
                {
                  moveDirection=5;
                  moveLogic();  
                }});
         add(southEast);
                 southEast.addActionListener(new ActionListener() {
                     @Override
                 public void actionPerformed(ActionEvent e)
                 {
                   moveDirection=4;
                   moveLogic();
                 }});
     }
    public void moveLogic(){
    
       int index = GUI.determineWhichUnitDrawContainsUnitIdEqaulToUnitSelectedAt();
       int tileMoveChange = GUI.tileWidth;
       
       if(GUI.unitSelected.isSprinting)
       {
            tileMoveChange*=2;

       }
       
    //which direction was unit moved in
       //not inherienting speed from soldiers, needs to be fixed. should be able to call units speed not a soldiers speed
      if(GUI.unitSelected.moveMentCounter>0&&determineTheNewLocationOfTheUnit(tileMoveChange))
      {
           GUI.unitSelected.hasMoved=true;
          if(GUI.unitSelected.isSprinting)
          {
              GUI.unitSelected.stamina--;
              GUI.unitSelected.hasSprinted=true;
          }
          //figure out where the new unit will be located
          determineTheNewLocationOfTheUnit(tileMoveChange);
//          if(GUI.tileGameMap[][])
          int dir = determineNewDirectionOfUnit();
          GUI.removeSoldiersFromPreviousTiles();
          
          
          
          Tile t = findTile(dir);
          //updates the draw to show new location of unit
            UnitDraw draw = new UnitDraw(GUI.unitSelected,new Tile(GUI.unitSelected.xPosition,GUI.unitSelected.yPosition,GUI.tileWidth,GUI.tileWidth));
            
            GUI.unitDraws.add(draw);//adds the new unit
            GUI.unitDraws.remove(index); //removes the previous unit
            GUI.repainter();
            previousMoveDirection= moveDirection ;
            GUI.unitSelected.moveMentCounter--; //removes one move counter from the unit 
            
      } 
   }
    protected boolean determineTheNewLocationOfTheUnit(int tileMoveChange) {
        
        boolean flag = false;
        switch(moveDirection)
        {
            case 1: //north
            {
                if(!GUI.tileGameMap[GUI.unitSelected.xPosition/GUI.tileWidth][(GUI.unitSelected.yPosition-tileMoveChange)/GUI.tileWidth].tileBlocked)
                {
                      GUI.unitSelected.yPosition-=tileMoveChange; //yPosition - 10 = 1 tile north
                      flag = true;
                }
                break;
            }
            
            case 2: //northEast
            {
                if(!GUI.tileGameMap[(GUI.unitSelected.xPosition+tileMoveChange)/GUI.tileWidth][(GUI.unitSelected.yPosition-tileMoveChange)/GUI.tileWidth].tileBlocked)
                {
                    GUI.unitSelected.xPosition+=tileMoveChange;//xPosition + 10 = 1 tile east
                    GUI.unitSelected.yPosition-=tileMoveChange; //yPosition - 10 = 1 tile north
                    flag = true;
                }
                
                break;
            }
            case 3: //East
            {
                if(!GUI.tileGameMap[(GUI.unitSelected.xPosition+tileMoveChange)/GUI.tileWidth][GUI.unitSelected.yPosition/GUI.tileWidth].tileBlocked)
                {
                    GUI.unitSelected.xPosition+=tileMoveChange;//xPosition + 10 = 1 tile east
                    flag = true;
                }          
                break;
            }
            case 4: //southEast
            {
             if(!GUI.tileGameMap[(GUI.unitSelected.xPosition+tileMoveChange)/GUI.tileWidth][(GUI.unitSelected.yPosition+tileMoveChange)/GUI.tileWidth].tileBlocked)
                {
                    GUI.unitSelected.xPosition+=tileMoveChange;//xPosition + 10 = 1 tile east
                    GUI.unitSelected.yPosition+=tileMoveChange;//yPosition + 10 = 1 tile South
                    flag = true;
                }
                break;
            }
            case 5: //South
            {
             if(!GUI.tileGameMap[GUI.unitSelected.xPosition/GUI.tileWidth][(GUI.unitSelected.yPosition+tileMoveChange)/GUI.tileWidth].tileBlocked)
             {   
                    GUI.unitSelected.yPosition+=tileMoveChange;//yPosition + 10 = 1 tile South
                    flag = true;
                    
              }
                break;
            }
            case 6://southWest
            {
                if(!GUI.tileGameMap[(GUI.unitSelected.xPosition-tileMoveChange)/GUI.tileWidth][(GUI.unitSelected.yPosition+tileMoveChange)/GUI.tileWidth].tileBlocked)
                {
                    GUI.unitSelected.xPosition-=tileMoveChange;//xPosition -10 = 1 tile West
                    GUI.unitSelected.yPosition+=tileMoveChange;//yPosition + 10 = 1 tile South
                    flag = true;
                }
                break;
            }
            case 7://West
            {
                if(!GUI.tileGameMap[(GUI.unitSelected.xPosition-tileMoveChange)/GUI.tileWidth][GUI.unitSelected.yPosition/GUI.tileWidth].tileBlocked)
                {
                    GUI.unitSelected.xPosition-=tileMoveChange;//xPosition -10 = 1 tile West
                    flag = true;
                }
                break;
            }
            case 8://NorthWest
            {
                if(!GUI.tileGameMap[(GUI.unitSelected.xPosition-tileMoveChange)/GUI.tileWidth][(GUI.unitSelected.yPosition-tileMoveChange)/GUI.tileWidth].tileBlocked)
                {
                    GUI.unitSelected.xPosition-=tileMoveChange;//xPosition -10 = 1 tile West
                    GUI.unitSelected.yPosition-=tileMoveChange;//yPosition -10 = 1 tile North
                    flag = true;
                }
                break;
            }
        }
        GUI.unitSelected.setPosition(GUI.unitSelected.xPosition,GUI.unitSelected.yPosition);
        return flag;

    }
    protected void removeJButtonDefaults() {
        this.north.setOpaque(false);
        this.north.setContentAreaFilled(false);
        this.north.setBorderPainted(false);
        this.northEast.setOpaque(false);
        this.northEast.setContentAreaFilled(false);
        this.northEast.setBorderPainted(false);
        this.northWest.setOpaque(false);
        this.northWest.setContentAreaFilled(false);
        this.northWest.setBorderPainted(false);
        this.east.setOpaque(false);
        this.east.setContentAreaFilled(false);
        this.east.setBorderPainted(false);
        this.west.setOpaque(false);
        this.west.setContentAreaFilled(false);
        this.west.setBorderPainted(false);
        this.south.setOpaque(false);
        this.south.setContentAreaFilled(false);
        this.south.setBorderPainted(false);
        this.southEast.setOpaque(false);
        this.southEast.setContentAreaFilled(false);
        this.southEast.setBorderPainted(false);
        this.southWest.setOpaque(false);
        this.southWest.setContentAreaFilled(false);
        this.southWest.setBorderPainted(false);
        this.toggleSprint.setOpaque(false);
        this.toggleSprint.setContentAreaFilled(false);
        this.toggleSprint.setBorderPainted(false);
        setOpaque(false);
    }
    private int  determineNewDirectionOfUnit() {
       if(previousMoveDirection!=moveDirection)
       {
           if(moveDirection==1)
           {
               GUI.unitSelected.unitFacing=1;
               
           }
           else if(moveDirection==3)
           {
               GUI.unitSelected.unitFacing=2;
           }
           else if(moveDirection==5)
           {
               GUI.unitSelected.unitFacing=3;
           }
           else if(moveDirection==7)
           {
               GUI.unitSelected.unitFacing=4;
           }
       }
       return GUI.unitSelected.unitFacing;
       
    }
    private Tile findTile(int direction){
        
        Tile t = new Tile(0,0,0,0);
        
       int index =GUI.determineWhichUnitDrawContainsUnitIdEqaulToUnitSelectedAt();
       
       for(int i=0;i<GUI.numberOfTilesHeight;i++)
       {
           for(int j=0;j<GUI.numberOfTilesWidth;j++)
           {
//               if(GUI.unitDraws.get(index).getThisUnit().getUnitID()==)
           }
       }
           
        
        return t;
        
        
        
        
        
    }
    
}


  
    
    
    
    
