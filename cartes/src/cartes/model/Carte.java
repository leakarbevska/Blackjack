/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class representing a card 
 * @author 21711436
 */
public class Carte {
    
    private String valeur;
    private String couleur;

    public String getValeur() {
        return valeur;
    }

    public String getCouleur() {
        return couleur;
    }
    
    public Carte(String valeur, String couleur){
        this.valeur = valeur;
        this.couleur = couleur;
    }
    
    /**
     * Returns the value of the card with blackjack rules
     * @param currentSum the sum of all the cards in the hand
     * @return the value
     */
    public int getValue(int currentSum) {
        try {
            return Integer.parseInt(valeur);
        } catch(NumberFormatException e) {
            if(!valeur.equals("A")) {
                return 10;
            } else if(currentSum <= 10) {
                return 11;
            } else {
                return 1;
            }
        }
    }
    
    @Override
    public String toString(){
        return this.valeur + ", " + this.couleur;
    }
    
    /**
     * Creates an image of the card
     * @return the image
     * @throws IOException in case of invalid url of image
     */
    public BufferedImage getBufferedImage() throws IOException {
        return ImageIO.read(Carte.class.getResourceAsStream("/cartes/cardImage/"+getValeur()+"_of_"+getCouleur()+".png"));
        //ImageIO.read(Carte.class.getResourceAsStream("/cartes/Cards/card"+getCouleur()+getValeur()+".png"));
    }
    
    /**
     * Creates an image of a hidden card
     * @return the image
     * @throws IOException in case of invalid url of image
     */
    public static BufferedImage getBufferedHiddenCardImage() throws IOException {
        return ImageIO.read(Carte.class.getResourceAsStream("/cartes/cardImage/card_behind.jpg"));
    }
    
}
