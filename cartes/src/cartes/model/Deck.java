/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *A class representing the main deck of cards
 * @author 21711436
 */
public class Deck {
    private static Deck instance;
    private ArrayList<Carte> deck;

    private Deck(){
        this.resetDeck();
    }    
    
    public ArrayList<Carte> getDeck() {
        return deck;
    }
    
    
    /**
     * Singleton, checks if an instance of the class has been created
     * @return the instance already created, otherwise a new instance
     */
    public static Deck getInstance() {
        if(instance == null) {
            instance = new Deck();
        }
        return instance;
    }
    
    
    /**
     * Resets the deck by creating a new 52 deck
     */
    public final void resetDeck() {
        this.deck = Factory.createPaquet52();
        shuffleDeck();
    }    
    
    /**
     * Adds card to the top of the deck
     * @param card Card added 
     */
    public void addTop(Carte card){
        this.deck.add(this.deck.size(),card);
    }
    
    /**
     * Adds card to the bottom of the deck
     * @param card Card added 
     */
    public void addBottom(Carte card){
        this.deck.add(0,card);
    }
    
    /**
     * Picks out the first card from the deck
     * @return first card
     */
    public Carte pickCard(){
        Carte carte = deck.get(0);
        this.deck.remove(carte);
        return carte;
    }
    
    /**
     * Picks out a card from the specified index from the deck
     * @param i index of the card
     * @return card picked
     */
    public Carte pickCard(int i){
        Carte carte = deck.get(i);
        this.deck.remove(carte);
        return carte;
    }

    /**
     * Picks out a random card from the deck
     * @return card picked
     */
    public Carte pickRandomCard(){
        Random rand = new Random();
        return this.pickCard(rand.nextInt(this.deck.size()));
    }
    
    /**
     * Shuffles the order of the deck
     */
    public void shuffleDeck(){
        Collections.shuffle(this.deck);
    }
    
    /**
     * Cuts the deck in two sub-decks and swaps their order
     */
    public void cutDeck(){
        Random rand = new Random();
        int index = rand.nextInt(this.deck.size()-3);
        ArrayList<Carte> newPaquet = new ArrayList();
        
        for(int i=index; i<this.deck.size(); i++){ //upper part
            newPaquet.add(this.deck.get(i));
        }
        for(int i=0; i<index; i++){ //bottom part
            newPaquet.add(this.deck.get(i));
        }
        this.deck = newPaquet;
    }

    
    @Override
    public String toString(){
        String s="";
        for(int i=this.deck.size()-1; i>=0; i--){
            s += this.deck.get(i).toString() + "\n";
        }
        return s;
    }

}
