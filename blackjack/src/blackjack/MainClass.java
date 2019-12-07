package blackjack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import blackjack.controller.BlackjackController;
import blackjack.model.Human;
import blackjack.view.BlackjackFrame;

/**
 * Containing the main, calls a new blackjack game
 * @author 21711436
 */
public class MainClass {
    
    public static void main(String[] args){
        BlackjackController blackjack = new BlackjackController(new Human());
        BlackjackFrame jeu = new BlackjackFrame(blackjack);
    }
}
