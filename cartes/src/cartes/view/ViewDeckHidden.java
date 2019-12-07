/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.view;

import cartes.model.Carte;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Panel showing a deck of facing down cards
 * @author 21711436
 */
public class ViewDeckHidden extends JPanel{
    final static int MAX_CARDS_TO_DISPLAY = 7;
    private Graphics currentGraphics;
    private ArrayList<Carte> cards; 
    
    public ViewDeckHidden() {
        cards = new ArrayList<Carte>();
    }
    
    public void setCards(ArrayList<Carte> cards) {
        this.cards = cards;
        invalidate();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentGraphics = g;
        drawHand();
        this.setBackground(new Color(0,153,0));
        this.setBorder(new LineBorder(Color.GRAY, 1, false));
        this.setSize(getParent().getWidth()/2, 200);
        this.setVisible(true);
    }
    
    private Rectangle getCardRectangle(int cardIndex, float width, float height) {
        float ratio = (getWidth()/MAX_CARDS_TO_DISPLAY)/width;
        int line = cardIndex/MAX_CARDS_TO_DISPLAY;
        int xOffset = cardIndex%MAX_CARDS_TO_DISPLAY;
        return new Rectangle(Math.round((xOffset*width)*ratio), 
                line*Math.round(height*ratio), 
                Math.round(width*ratio), 
                Math.round(height*ratio));
    }
    
    private void drawHand() {
        for(int i = 0; i < cards.size(); i++) {
            BufferedImage bufferedImage;
            try {
                bufferedImage = Carte.getBufferedHiddenCardImage();
                drawBufferedImage(bufferedImage, getCardRectangle(i,bufferedImage.getWidth(), bufferedImage.getHeight()));
            } catch (IOException ex) {
                Logger.getLogger(ViewDeckVisible.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void drawBufferedImage(BufferedImage bufferedImage, Rectangle position) {
        currentGraphics.drawImage(bufferedImage, position.x, position.y, position.width, position.height, null);
    }
    
}
