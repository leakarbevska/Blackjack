/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes.view;

import cartes.model.Deck;
import java.awt.Color;
import javax.swing.JFrame;
/**
 * A Frame that is initially hidden from the players
 * @author lea
 */
public class HiddenOfPlayersFrame extends JFrame {
    private final String TITRE = "A ne pas montrer aux joueurs";
    private final int[] TAILLE_FRAME = {1000, 600};
    
    
    /**
     * Creates a frame 
     * @param paquet the deck visualized in the frame
     */
    public HiddenOfPlayersFrame(Deck paquet){
        this.setTitle(TITRE);
        this.setSize(TAILLE_FRAME[0], TAILLE_FRAME[1]);
        this.setBackground(Color.gray);
                
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public void showFrame(){
        this.setVisible(true);
    }
    
}
