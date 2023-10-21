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
    private TextField dealerTotalTextField;

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
    private TextField playerTotalTextField;

    @FXML
    private TextField result1TextField;

    @FXML
    private TextField result2TextField;

    @FXML
    private Button standButton;

    private int playerTotal = 0;
    private int dealerTotal = 0;

    @FXML
    void Hit(ActionEvent event) {
        if (playerTotal < 21) {
            // Deal another card to the player
            String card = generateCardValue();
            int cardValue = getCardValue(card);

            // Check if there are available player card slots
            if (playerCard1.getText().isEmpty()) {
                playerCard1.setText(card);
                playerTotal += cardValue;
            } else if (playerCard2.getText().isEmpty()) {
                playerCard2.setText(card);
                playerTotal += cardValue;
            } else if (playerCard3.getText().isEmpty()) {
                playerCard3.setText(card);
                playerTotal += cardValue;
            } else if (playerCard4.getText().isEmpty()) {
                playerCard4.setText(card);
                playerTotal += cardValue;
            }

            // Update the player's total in real-time
            playerTotalTextField.setText(String.valueOf(playerTotal));

            // Check if the player goes over 21
            if (playerTotal > 21) {
                result1TextField.setText("Dealer wins!");
                result2TextField.setText("You lose the bet amount.");
            } else if (playerTotal == 21) {
                // Stand if player reaches 21
                Stand(null);
            }
        }

    }

    @FXML
    void Play(ActionEvent event) {
        // Start a new game
        playerTotal = 0;
        dealerTotal = 0;
        betBox.setText("");

        // Deal cards for the dealer and the player
        String dealerCard1Value = generateCardValue();
        dealerTotal += getCardValue(dealerCard1Value);
        dealerCard1.setText(dealerCard1Value);
        dealerCard2.setText("");
        dealerCard3.setText("");
        dealerCard4.setText("");

        String playerCard1Value = generateCardValue();
        playerTotal += getCardValue(playerCard1Value);
        playerCard1.setText(playerCard1Value);

        String playerCard2Value = generateCardValue();
        playerTotal += getCardValue(playerCard2Value);
        playerCard2.setText(playerCard2Value);
        playerCard3.setText("");
        playerCard4.setText("");
        playerTotalTextField.setText("");
        dealerTotalTextField.setText("");
        result1TextField.setText("");
        result2TextField.setText("");
        // Update the initial player and dealer totals
        playerTotalTextField.setText(String.valueOf(playerTotal));
        dealerTotalTextField.setText(String.valueOf(dealerTotal));

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
        // Update the dealer's total in real-time
        dealerTotalTextField.setText(String.valueOf(dealerTotal));


        // Determine the winner and loser
        if (dealerTotal > 21 || (playerTotal <= 21 && playerTotal > dealerTotal)) {
            result1TextField.setText("Player wins!");
            result2TextField.setText("You win the bet amount.");

        } else if (playerTotal == dealerTotal) {
            result1TextField.setText("It's a tie!");
            result2TextField.setText("You get your bet amount back.");

        } else {
            result1TextField.setText("Dealer wins!");
            result2TextField.setText("You lose the bet amount.");

        }

    }
    private String generateCardValue() {
        String[] cards = {"Ace1","2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace11"};
        Random rand = new Random();
        int index = rand.nextInt(cards.length);
        return cards[index];
    }

    private int getCardValue(String card) {
        if (card.equals("Ace11")) {
            return 11;
        } else if (card.equals("King") || card.equals("Queen") || card.equals("Jack")) {
            return 10;
        }
        else if(card.equals("Ace1")) {
            return 1;
        } else {
            return Integer.parseInt(card);
        }
    }

}
