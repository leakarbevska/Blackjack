/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.model;

import java.util.ArrayList;

/**
 * A class that contains a static method which creates a deck of cards
 * @author 21711436
 */
public class Factory {
    
    /**
     * Creates a deck of cards
     * @return the deck
     */
    public static ArrayList<Carte> createPaquet52(){
        ArrayList<Carte> deck = new ArrayList<Carte>();
        String[] valeurs  = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] couleurs = {"hearts","diamonds","spades","clubs"}; 
        int i,j;
        for(i=0; i < 13; i++){
            for(j=0; j<4; j++){
                deck.add(new Carte(valeurs[i],couleurs[j]));
            }
        }
        return deck;
    }
    
}
