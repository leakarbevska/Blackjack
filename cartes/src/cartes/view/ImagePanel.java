/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * Class representing a Panel of an Image
 * @author lea
 */
public class ImagePanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private Image image = null;
    private int iWidth2;
    private int iHeight2;

    public ImagePanel(Image image){
        this.image = image;
        this.iWidth2 = image.getWidth(this)/2;
        this.iHeight2 = image.getHeight(this)/2;
    }
    
    /**
     * Drawing the image 
     * @param g instance of Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (image != null){
            int x = this.getParent().getWidth()/2 - iWidth2;
            int y = this.getParent().getHeight()/2 - iHeight2;
            g.drawImage(image,x,y,this);
        }
    }
}
