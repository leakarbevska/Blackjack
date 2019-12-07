/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.controller;

import cartes.model.Carte;
import java.util.ArrayList;

/**
 * Interface representing a listener between the controller and the view
 * @author lea
 */
    public interface BlackjackStateListener {
        void updateHumanHand(ArrayList<Carte> hand, int score, int budget);
        void updateComputerHand(ArrayList<Carte> hand);
        void onMatchOver(String winnerName);
        void onGameOver();
    }
