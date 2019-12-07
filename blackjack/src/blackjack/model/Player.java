/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.model;

import cartes.model.Carte;
import java.util.ArrayList;

/**
 * Class representing the computer and human player
 * @author 21711436
 */
public class Player {
    private String name;
    private ArrayList<Carte> hand;
    private boolean standing;
    
    public Player() {
        this.hand = new ArrayList<Carte>();
        this.standing = false;
    }
    
    public ArrayList<Carte> getHand() {
        return hand;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public boolean isStanding() {
        return standing;
    }
    
    
    public void stand() {
        standing = true;
    }
    
    /**
     * Adds card to the hand of the player
     * @param card the card added
     */
    public void addCard(Carte card) {
        hand.add(card);
    }
    
    /**
     * Resets the hand 
     */
    public void reset() {
        hand = new ArrayList<Carte>();
        standing = false;
    }
    
    /**
     * Calculates sum of all the cards in the hand
     * @return the sum
     */
    public int getSumOfHand() {
        int sum = 0;
        ArrayList<Carte> sortedHand = sortHandForCalculation();
        for(Carte card : sortedHand) {
            sum += card.getValue(sum);
        }
        
        return sum;
    }
    
    /**
     * Returns a sorted hand where the A cards are at the end
     * @return sorted hand/deck
     */
    private ArrayList<Carte> sortHandForCalculation() {
        ArrayList<Carte> sortedHand = new ArrayList<Carte>();
        for(Carte card : hand) {
            if(card.getValeur().equals("A")) {
                sortedHand.add(card);
            } else {
                sortedHand.add(0, card);
            }
        }
        return sortedHand;
    }
    
}
