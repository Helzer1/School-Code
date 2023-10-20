package com.example.midterm_;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BlackjackController {

    @FXML
    private TextField betBox;

    @FXML
    private TextField dealerCard1;

    @FXML
    private TextField dealerCard2;

    @FXML
    private TextField dealerCard3;

    @FXML
    private TextField dealerCard4;

    @FXML
    private Button hitButton;

    @FXML
    private Button playButton;

    @FXML
    private TextField playerCard1;

    @FXML
    private TextField playerCard2;

    @FXML
    private TextField playerCard3;

    @FXML
    private TextField playerCard4;

    @FXML
    private Button standButton;
    
    
    private int playerTotal = 0;
    private int dealerTotal = 0;

    @FXML
    void Hit(ActionEvent event) {
    	if (playerTotal < 21) {
            // Deal another card to the player
            String card = generateCardValue();
            playerTotal += getCardValue(card);

            if (playerCard1.getText().isEmpty()) {
                playerCard1.setText(card);
            } else if (playerCard2.getText().isEmpty()) {
                playerCard2.setText(card);
            } else if (playerCard3.getText().isEmpty()) {
                playerCard3.setText(card);
            } else if (playerCard4.getText().isEmpty()) {
                playerCard4.setText(card);
            }
        } else if (playerTotal > 21) {
            betBox.setText("Dealer wins!");
        }
    }

    @FXML
    void Play(ActionEvent event) {
    	// Start a new game
        playerTotal = 0;
        dealerTotal = 0;
        dealerCard1.setText(generateCardValue());
        dealerCard2.setText("");
        dealerCard3.setText("");
        dealerCard4.setText("");
        playerCard1.setText(generateCardValue());
        playerCard2.setText(generateCardValue());
        playerCard3.setText("");
        playerCard4.setText("");
        
    }

    @FXML
    void Stand(ActionEvent event) {
    	// Dealer's turn
        while (dealerTotal < 17) {
            String card = generateCardValue();
            dealerTotal += getCardValue(card);
            if (dealerCard2.getText().isEmpty()) {
                dealerCard2.setText(card);
            } else if( dealerCard3.getText().isEmpty()){
                dealerCard3.setText(card);
            } else {
                dealerCard4.setText(card);
            }
        }

        // Determine the winner
        if (dealerTotal > 21 || (playerTotal <= 21 && playerTotal > dealerTotal)) {
            betBox.setText("Player wins!");
        } else if (playerTotal == dealerTotal) {
            betBox.setText("It's a tie!");
        } else {
            betBox.setText("Dealer wins!");
        }

    }

    @FXML
    void placeBet(ActionEvent event) {

    }
    
    
    private String generateCardValue() {
        String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        Random rand = new Random();
        int index = rand.nextInt(cards.length);
        return cards[index];
    }

    private int getCardValue(String card) {
        if (card.equals("A")) {
            return 11;
        } else if (card.equals("K") || card.equals("Q") || card.equals("J")) {
            return 10;
        } else {
            return Integer.parseInt(card);
        }
    }

}
