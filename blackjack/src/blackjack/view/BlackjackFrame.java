/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.view;

import blackjack.controller.BlackjackController;
import blackjack.controller.BlackjackStateListener;
import cartes.model.Carte;
import cartes.view.ViewDeckVisible;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * Class representing the frame of the game
 * @author lea
 */
public class BlackjackFrame extends JFrame implements BlackjackStateListener {
    private final String TITRE = "Blackjack 52 cards";
    private final int[] TAILLE_FRAME = {650, 900};
    
    private JButton jbHit;
    private JButton jbStand;
    private JLabel  jlBudget;
    private JLabel  jlScore;
    private ViewDeckVisible humanPaquetView;
    private ViewDeckVisible computerPaquetView;
    
    private BlackjackController blackjackController;
    
    public BlackjackFrame(BlackjackController blackjackController){
        this.blackjackController = blackjackController;
        init();      
    }
    
    public final void init(){
        setupFrame();
        addComponents();
        beginPane();
    }
    
    /**
     * Sets up the general info about the frame
     */
    private void setupFrame() {
        this.setTitle(TITRE);
        this.setPreferredSize(new Dimension(TAILLE_FRAME[1], TAILLE_FRAME[0]));
        this.setResizable(false);
        
        this.getContentPane().setBackground(new Color(0,153,0));
        this.getContentPane().setLayout(new GridLayout(3,1));    
        
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    /**
     * Adds the Panels of the frame
     */
    private void addComponents() {
        //this.getContentPane().add(new ViewDeckComputer(), BorderLayout.NORTH);
        createTopPanel();
        createCentralPanel();
        createBottomPanel();
    }
    
    /**
     * Calls the controller to create a new game
     */
    private void startGame() {
        betOptionPane();
        this.computerPaquetView.setShowJustOneCard(true);
        blackjackController.startNewGame(this);
    }
    
    /**
     * OptionPane containing the name and the bet of the player
     */
    private void beginPane(){        
        JTextField textField = new JTextField();
        SpinnerModel sModel  = new SpinnerNumberModel(50,1,blackjackController.getHumanPlayer().getBudget(),5);  
        JSpinner spinner     = new JSpinner(sModel);

        Object[] inputFields = {"Welcome! \nYour initial budget is $200.", "Enter your name : ", textField, "Declare your bet : ", spinner, "Good luck!"};
        int option = JOptionPane.showConfirmDialog(this, inputFields, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            if(textField.getText().length() != 0){
                this.blackjackController.getHumanPlayer().setName(textField.getText());
                this.blackjackController.setBet((Integer) spinner.getValue());
                blackjackController.startNewGame(this);
            }else{
                beginPane();
            }
        }else{
            System.exit(0);
        }
    }
    
    /**
     * OptionPane containing the bet of the player
     */
    private void betOptionPane(){        
        SpinnerModel sModel  = new SpinnerNumberModel(50,1,blackjackController.getHumanPlayer().getBudget(),5);  
        JSpinner spinner     = new JSpinner(sModel);

        Object[] inputFields = {"Hey! \nYour current budget is $"+this.blackjackController.getHumanPlayer().getBudget()+".\n", "Declare your new bet : ", spinner, "Good luck!"};
        int option = JOptionPane.showConfirmDialog(this, inputFields, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            this.blackjackController.setBet((Integer) spinner.getValue());
        }else{
            this.dispose();
        }
    }    
    
    
    /**
     * Creates the panel of the dealer's deck
     */
    private void createTopPanel(){
        computerPaquetView = new ViewDeckVisible(true);
        this.getContentPane().add(computerPaquetView);
    }
    
    /**
     * Creates the central panel containing buttons and labels
     */
    private void createCentralPanel() {
        JPanel panelCenter = new JPanel(new BorderLayout());
        JPanel panelSouth = new JPanel();
        
        JPanel panelLabel = new JPanel();
        JLabel jlBlackjack = new JLabel("BLACKJACK");
        jlBlackjack.setFont(new Font("Serif", Font.BOLD, 25));
        jlBlackjack.setForeground(new Color(0xffffdd));
        panelLabel.add(jlBlackjack);
        
        panelLabel.setBackground(new Color(0,153,0));
        panelSouth.setBackground(new Color(0,153,0));
        panelSouth.add(createStandButton());
        panelSouth.add(createScoreLabel());
        panelSouth.add(createBudgetLabel());
        panelSouth.add(createHitMeButton());
        panelCenter.add(panelLabel, BorderLayout.CENTER);
        
        panelCenter.add(panelSouth, BorderLayout.SOUTH);
        this.getContentPane().add(panelCenter);
        panelCenter.setBackground(new Color(0,153,0));
    }
    
    /**
     * Creates the panel of the player's deck
     */
    private void createBottomPanel() {
        humanPaquetView = new ViewDeckVisible(false);
        this.getContentPane().add(humanPaquetView);
    }
    
    private JButton createHitMeButton() {
        jbHit = new JButton("HIT");
        jbHit.addActionListener(onHitMeClicked);
        return jbHit;
    }
    
    private JButton createStandButton() {
        jbStand = new JButton("STAND");
        jbStand.addActionListener(onStandClicked);
        return jbStand;
    }
    
    private JLabel createScoreLabel() {
        jlScore = new JLabel("Score: 0");
        return jlScore;
    }
    
    private JLabel createBudgetLabel() {
        jlBudget = new JLabel("Budget: 200$");
        return jlBudget;
    }
    
    private ActionListener onHitMeClicked = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            blackjackController.playerTurn(true);
        }
    };
    
    private ActionListener onStandClicked = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            blackjackController.playerTurn(false);
        }
    };
    
    /**
     * Updates the views of human's deck, score and budget
     * @param hand deck of cards
     * @param score sum of cards in hand
     * @param budget the budget of the player
     */
    public void updateHumanHand(ArrayList<Carte> hand, int score, int budget) {
        humanPaquetView.setCards(hand);
        jlScore.setText(String.format("Score: %d", score));
        jlBudget.setText(String.format("Budget: $%d", budget));
    }
    
    /**
     * Updates the view of the deck of the computer
     * @param hand deck of cards of the computer
     */
    public void updateComputerHand(ArrayList<Carte> hand) {
        computerPaquetView.setCards(hand);
    }
    
    
    public void onMatchOver(String winnerName) {
        showMatchOverDialog(winnerName);
    }
    
    public void onGameOver(){
        showGameOverDialog();
    }
    
    /**
     * Creates an OptionPane showing the winner of the match and asking if we want to continue the game.
     * @param winnerName 
     */
    private void showMatchOverDialog(String winnerName) {
        this.computerPaquetView.showOnceHiddenCards();
        int dialogResult = JOptionPane.showConfirmDialog (null, String.format("%s won!\nContinue?", winnerName),"", JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            this.startGame();
        } else {
            this.dispose();
        }
    }
    
    /**
     * Creates an OptionPane showing the winner and asking if we want to continue the game.
     * @param winnerName 
     */
    private void showGameOverDialog() {
        this.computerPaquetView.showOnceHiddenCards();
        int dialogResult = JOptionPane.showConfirmDialog (null, String.format("%s, you have lost!\nPlay again?", this.blackjackController.getHumanPlayer().getName()),"", JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            this.blackjackController.getHumanPlayer().resetBudget();
            this.startGame();
        } else {
            this.dispose();
        }
    }
}
