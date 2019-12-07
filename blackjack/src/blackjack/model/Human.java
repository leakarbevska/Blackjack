/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.model;

/**
 * Class representing the human player
 * @author lea
 */
public class Human extends Player {
    private int budget;
    final static int INITIAL_BUDGET = 200;
    
    public Human() {
        super();
        this.budget = INITIAL_BUDGET;
    }

    public int getBudget() {
        return budget;
    }
    
    public void resetBudget(){
        this.budget = INITIAL_BUDGET;
    }

    /**
     * Adds the bet to the budget 
     * @param bet the bet lost/won
     */
    public void addBetToBudget(int bet) {
        this.budget += bet;
    }
    
}
