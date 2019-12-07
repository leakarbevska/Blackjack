/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.controller;

import blackjack.model.Human;
import blackjack.model.Player;
import cartes.model.Deck;

/**
 * Main controller of the blackjack game, makes changes in the model and calls the view.
 * @author lea
 */
public class BlackjackController {
    private static final int COMPUTER_BRAVERY = 18;
    private int bet;
    
    private Player computerPlayer;
    private Human humanPlayer;
    private BlackjackStateListener listener;
    
    public BlackjackController(Human humanPlayer) {
        this.humanPlayer = humanPlayer;
        this.computerPlayer = new Player();
        this.computerPlayer.setName("Computer");
    }

    public Player getComputerPlayer() {
        return computerPlayer;
    }

    public Human getHumanPlayer() {
        return humanPlayer;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
    
    /**
     * Creates a new game
     * @param listener implemented by the frame
     */
    public void startNewGame(BlackjackStateListener listener) {
        this.listener = listener;
        Deck.getInstance().resetDeck();
        humanPlayer.reset();
        computerPlayer.reset();
        dealHandTo(humanPlayer);
        dealHandTo(humanPlayer);
        dealHandTo(computerPlayer);
        dealHandTo(computerPlayer);
        listener.updateHumanHand(humanPlayer.getHand(), humanPlayer.getSumOfHand(), humanPlayer.getBudget());
        listener.updateComputerHand(computerPlayer.getHand());
    }
    
    /**
     * Plays the turn of the player
     * @param deal boolean which checks if the player has hit on the deal button
     */
    public void playerTurn(boolean deal) {
        if(deal) {
            dealHandTo(humanPlayer);
            listener.updateHumanHand(humanPlayer.getHand(), humanPlayer.getSumOfHand(), humanPlayer.getBudget());
        } else {
            humanPlayer.stand();
        }
        if(!proclaimWinner(humanPlayer) && !computerPlayer.isStanding()) {
            computerTurn();
        }
    }
    
    /**
     * Plays the turn of the computer
     */
    private void computerTurn() {
        if(computerPlayer.getSumOfHand() >=  COMPUTER_BRAVERY) {
            computerPlayer.stand();
        } else {
            dealHandTo(computerPlayer);
            listener.updateComputerHand(computerPlayer.getHand());
        }
        if(!proclaimWinner(computerPlayer) && humanPlayer.isStanding()) {
            computerTurn();
        }
    }
    
    /**
     * Returns the other player than the one send as an argument
     * @param player the Player that we are not looking for
     * @return the other player
     */
    public Player getOtherPlayer(Player player){
        if(player == this.computerPlayer){
            return this.humanPlayer;
        }
        return this.computerPlayer;
    }
    
    /**
     * Adds or subtracts the bet to Human's budget depending of who wins
     * @param winner the player that won
     */
    public void distributeBet(Player winner){
        if(winner == this.humanPlayer){
            this.humanPlayer.addBetToBudget(bet);
        }else{
            this.humanPlayer.addBetToBudget(-bet);
            if(this.humanPlayer.getBudget() <= 0){
                this.listener.onGameOver();
            }
        }
    }
    
    /**
     * Checks if the match is over and if so calls the listener with the winner's name
     * @param player the last player that has done a move 
     * @return true if it has finished, false otherwise
     */
    private boolean proclaimWinner(Player player) {
        if(player.getSumOfHand() == 21){
            distributeBet(player);
            listener.onMatchOver(player.getName());
            return true;
        }else if(player.getSumOfHand() > 21){
            Player winner = getOtherPlayer(player);
            distributeBet(winner);
            listener.onMatchOver(winner.getName());
            return true;
        }else if(player.isStanding() && getOtherPlayer(player).isStanding()){
            Player winner = getPlayerWithBiggerHand();
            distributeBet(winner);
            listener.onMatchOver(winner.getName());
            return true;
        }
        return false;
    }
    
    /**
     * Checks which player has bigger score
     * @return player with bigger score or computer if equal
     */
    public Player getPlayerWithBiggerHand(){
        if(this.humanPlayer.getSumOfHand() > this.computerPlayer.getSumOfHand()){
            return this.humanPlayer;
        }else{//if equal or bigger pc wins
            return this.computerPlayer;
        }
    }
    
    /**
     * Deals a card to a specified player
     * @param player the player who gets a card
     */
    private void dealHandTo(Player player) {
        player.addCard(Deck.getInstance().pickCard());
    }
    
}
