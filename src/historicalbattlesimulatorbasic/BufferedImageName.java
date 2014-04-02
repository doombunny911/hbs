/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.image.BufferedImage;

/**
 *
 * @author Edward
 * 
 */
public class BufferedImageName 
{
    public BufferedImage img;
    public  String name;
    public BufferedImageName(BufferedImage img, String name)
    {
        this.img = img;
        this.name = name;
    }
    public void setImage(BufferedImage img)
    {
        this.img = img;
    }
    public BufferedImage getImage()
    {
        return img;
    }
    public String getName()
    {
        return name;
    }
    
}
