package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import blackjack.controller.BlackjackController;
import blackjack.model.Human;
import cartes.model.Carte;

import blackjack.model.Player;
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
    private BlackjackController controller;
    public JUnitTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void calculateBetTest() {
        Human player   = new Human();
        this.controller.setBet(100);
        this.controller = new BlackjackController(player);
        // 200 + 100
        this.controller.distributeBet(player);
        assertEquals(300, player.getBudget());
        // (200+100) - 100
        this.controller.distributeBet(computer);
        assertEquals(200, player.getBudget());
    }
    
    @Test
    public void playerWithBiggerHandTest() {
        Human player   = new Human();
        this.controller = new BlackjackController(player);
        this.controller.getHumanPlayer().addCard(new Carte("2", "diamonds"));
        this.controller.getHumanPlayer().addCard(new Carte("5", "diamonds"));
        
        //player > pc
        this.controller.getComputerPlayer().addCard(new Carte("2", "diamons"));
        assertEquals(player, this.controller.getPlayerWithBiggerHand());
        //player = pc
        this.controller.getComputerPlayer().addCard(new Carte("5", "diamons"));
        assertEquals(this.controller.getComputerPlayer(), this.controller.getPlayerWithBiggerHand());
        //player < pc
        this.controller.getComputerPlayer().addCard(new Carte("5", "diamons"));
        assertEquals(this.controller.getComputerPlayer(), this.controller.getPlayerWithBiggerHand());        
    }

    @Test
    public void getOtherPlayerTest(){
        Human player    = new Human();
        this.controller = new BlackjackController(player);
        
        assertEquals(player ,this.controller.getOtherPlayer(this.controller.getComputerPlayer()));
        assertEquals(this.controller.getComputerPlayer() ,this.controller.getOtherPlayer(player));
        
    }
    
}
