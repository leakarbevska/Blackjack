/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import cartes.model.Carte;
import cartes.model.Deck;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lea
 */
public class JUnitTest {
    
    public JUnitTest() {
    }

    @Test
    public void testAddTop() {
        Deck deck = Deck.getInstance();
        Cacrte card = new Carte("2", "diamonds");
        deck.addBottom(card);
        assertEquals(card,deck.pickCard(deck.getDeck().size()));
    }
    
    @Test
    public void testAddBottom() {
        Deck deck = Deck.getInstance();
        Carte card = new Carte("2", "diamonds");
        deck.addBottom(card);
        assertEquals(card,deck.pickCard(0));
    }
    
}
