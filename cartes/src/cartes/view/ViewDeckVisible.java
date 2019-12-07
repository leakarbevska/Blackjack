/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.view;

import cartes.model.Carte;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * JPanel representing a hand/deck of a player with one card showing or all of them
 * @author 21711436
 */
public class ViewDeckVisible extends JPanel {
    private static final int MAX_CARDS_TO_DISPLAY = 7;
    private Graphics2D currentGraphics;
    private ArrayList<Carte> cards; 
    private boolean showJustOneCard;
    
    public ViewDeckVisible(boolean showJustOneCard) {
        cards = new ArrayList<Carte>();
        this.showJustOneCard = showJustOneCard;
    }
    
    public void setCards(ArrayList<Carte> cards) {
        this.cards = cards;
        invalidate();
    }
    
    public void setShowJustOneCard(boolean b) {
        this.showJustOneCard = b;
    }
    
    /**
     * Shows the whole deck of the hidden cards
     */
    public void showOnceHiddenCards() {
        this.showJustOneCard = false;
        repaint();
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentGraphics = (Graphics2D)g;
        currentGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        drawHand();
        this.setBackground(new Color(0,153,0));
        this.setSize(getParent().getWidth(), getParent().getHeight()/3-20);
        this.setVisible(true);
    }
    
    /**
     * Calculates a size for the Card and returns a rectangle with the size
     * @param cardIndex position of the card in the deck
     * @param width width of the card
     * @param height height of the card
     * @return a rectangle with a specified size
     */    
    private Rectangle getCardRectangle(int cardIndex, float width, float height) {
        float ratio = (getWidth()/MAX_CARDS_TO_DISPLAY)/width;
        int line = cardIndex/MAX_CARDS_TO_DISPLAY;
        int xOffset = cardIndex%MAX_CARDS_TO_DISPLAY;
        return new Rectangle(Math.round((xOffset*width)*ratio), 
                line*Math.round(height*ratio), 
                Math.round(width*ratio), 
                Math.round(height*ratio));
    }
    
    
    /**
     * Draws the hand/deck of the computer
     */    
    private void drawHand() {
        BufferedImage bufferedImage;
        if(!this.showJustOneCard){
            for(int i = 0; i < cards.size(); i++) {
                try {
                    bufferedImage = cards.get(i).getBufferedImage();
                    drawBufferedImage(bufferedImage, getCardRectangle(i,bufferedImage.getWidth(), bufferedImage.getHeight()));
                } catch (IOException ex) {
                    Logger.getLogger(ViewDeckVisible.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            try {
                if(cards.size() > 0){
                    bufferedImage = cards.get(0).getBufferedImage();
                    drawBufferedImage(bufferedImage, getCardRectangle(0,bufferedImage.getWidth(), bufferedImage.getHeight()));

                    bufferedImage = Carte.getBufferedHiddenCardImage();
                    drawBufferedImage(bufferedImage, getCardRectangle(1,bufferedImage.getWidth(), bufferedImage.getHeight()));
                }
            } catch (IOException ex) {
                    Logger.getLogger(ViewDeckVisible.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Draws the image of the card
     * @param bufferedImage the card image
     * @param position position of the card
     */
    private void drawBufferedImage(BufferedImage bufferedImage, Rectangle position) {
        currentGraphics.drawImage(bufferedImage, position.x, position.y,position.width, position.height, null);
    }
   
}
