/*
 * This program is written by Pikulkaew Boonpeng for Advanced Java Programming class by
 * Professor Richmond, A at BunkerHill Community College.
 */
package pockerscoring;

import java.util.Arrays;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Poker Scoring. Checks only Straight, Flush, Pair of a Kind, Three of a Kind,
 * and Four of a Kind.
 *
 * @author Pikulkaew Boonpeng
 */
public class PokerScoring extends Application {

    final int SPACE = 10; // for easy spacing
    VBox all = new VBox(SPACE); // contains all the components
    HBox boxBtns = new HBox(SPACE); // contains Deal and Shuffle Deck buttons
    Button btnDeal = new Button("Deal Hand"), btnShuf = new Button("Shuffle Deck");
    TextArea showTa = new TextArea(); // shows the five dealed cards
    Label cardStatus = new Label(); // shows the scoring
    String cardSt; // String for cardStatus label

    Integer lines[] = new Integer[52]; // for the whole deck
    int count = 0; // counts the number of dealing
    // count the same value of cards
    int countTwo = 0, countThree = 0, countFour = 0, countFive = 0,
            countSix = 0, countSeven = 0, countEight = 0, countNine = 0,
            countTen = 0, countJack = 0, countQueen = 0, countKing = 0, countAce = 0;

    // Initialize the deck
    final Card[] cards = {
        new Card(2, "Two", " of Diamonds"), new Card(3, "Three", " of Diamonds"),
        new Card(4, "Four", " of Diamonds"), new Card(5, "Five", " of Diamonds"),
        new Card(6, "Six", " of Diamonds"), new Card(7, "Seven", " of Diamonds"),
        new Card(8, "Eight", " of Diamonds"), new Card(9, "Nine", " of Diamonds"),
        new Card(10, "Ten", " of Diamonds"), new Card(11, "Jack", " of Diamonds"),
        new Card(12, "Queen", " of Diamonds"), new Card(13, "King", " of Diamonds"),
        new Card(1, "Ace", " of Diamonds"), new Card(2, "Two", " of Hearts"),
        new Card(3, "Three", " of Hearts"), new Card(4, "Four", " of Hearts"),
        new Card(5, "Five", " of Hearts"), new Card(6, "Six", " of Hearts"),
        new Card(7, "Seven", " of Hearts"), new Card(8, "Eight", " of Hearts"),
        new Card(9, "Nine", " of Hearts"), new Card(10, "Ten", " of Hearts"),
        new Card(11, "Jack", " of Hearts"), new Card(12, "Queen", " of Hearts"),
        new Card(13, "King", " of Hearts"), new Card(1, "Ace", " of Hearts"),
        new Card(2, "Two", " of Clubs"), new Card(3, "Three", " of Clubs"),
        new Card(4, "Four", " of Clubs"), new Card(5, "Five", " of Clubs"),
        new Card(6, "Six", " of Clubs"), new Card(7, "Seven", " of Clubs"),
        new Card(8, "Eight", " of Clubs"), new Card(9, "Nine", " of Clubs"),
        new Card(10, "Ten", " of Clubs"), new Card(11, "Jack", " of Clubs"),
        new Card(12, "Queen", " of Clubs"), new Card(13, "King", " of Clubs"),
        new Card(1, "Ace", " of Clubs"), new Card(2, "Two", " of Spades"),
        new Card(3, "Three", " of Spades"), new Card(4, "Four", " of Spades"),
        new Card(5, "Five", " of Spades"), new Card(6, "Six", " of Spades"),
        new Card(7, "Seven", " of Spades"), new Card(8, "Eight", " of Spades"),
        new Card(9, "Nine", " of Spades"), new Card(10, "Ten", " of Spades"),
        new Card(11, "Jack", " of Spades"), new Card(12, "Queen", " of Spades"),
        new Card(13, "King", " of Spades"), new Card(1, "Ace", " of Spades")};

    /**
     * Sets card status to be empty.
     */
    public PokerScoring() {
        cardSt = "";
    }

    /**
     * Setup the program.
     *
     * @param primaryStage A window for the program
     */
    @Override
    public void start(Stage primaryStage) {

        actionShuffle();
        setup();

        btnDeal.setOnAction(e -> {
            actionDeal();
        });

        btnShuf.setOnAction(e -> {
            actionShuffle();
        });

        Scene scene = new Scene(all, 300, 250);

        primaryStage.setTitle("Pocker Scoring by PK");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Shuffles the deck.
     */
    private void actionShuffle() {
        count = 0; //resets counts of dealling
        btnDeal.setDisable(false); // enable Deal button

        // puts numbet [1,52] to this.lines array
        for (int i = 0; i < lines.length; i++) {
            lines[i] = i;
        }
        Collections.shuffle(Arrays.asList(lines)); // shuffles this.lines array

        // tells the user that the deck has been shuffled
        showTa.setText("SHUFFLED!");
        // set card status to be empty
        cardStatus.setText("");
    }

    /**
     * Deals 5 cards per time.
     */
    private void actionDeal() {
        // local lines array for 5 cards
        String lines[] = new String[5];
        // str for displaying cards
        String str = new String("");

        count++; //increments every time the Deal button is clicked

        if (count >= 1 && count <= 9) {
            // j is for local lines array
            // i is for global lines array
            for (int j = 0, i = (count - 1) * 5; (j < 5 && i < count * 5); j++, i++) {
                lines[j] = cards[this.lines[i]].getNumber() + cards[this.lines[i]].getKind();
            }

            // set str to have 5 cards
            for (int i = 0; i < 5; i++) {
                str += lines[i] + "\n";
            }
            // put str to showTa (Text Area)
            showTa.setText(str);

            // curr means the first card of the current deal.
            int curr = (count - 1) * 5;
            // puts 5 cards of the current deal to setStatus function
            setStatus(cards[this.lines[curr]], cards[this.lines[curr + 1]],
                    cards[this.lines[curr + 2]], cards[this.lines[curr + 3]],
                    cards[this.lines[curr + 4]]);
        } // last dealing (2 cards left)
        else {
            btnDeal.setDisable(true); // disable Deal button
            lines[0] = cards[this.lines[50]].getNumber() + cards[this.lines[50]].getKind();
            lines[1] = cards[this.lines[51]].getNumber() + cards[this.lines[51]].getKind();

            str += (lines[0] + "\n" + lines[1]);

            showTa.setText(str);

            // last 3 cards are empty cards
            setStatus(cards[this.lines[50]],
                    cards[this.lines[51]],
                    new Card(0, "", ""), new Card(0, "", ""), new Card(0, "", ""));

        }

        // resets countings and card status
        countTwo = 0;
        countThree = 0;
        countFour = 0;
        countFive = 0;
        countSix = 0;
        countSeven = 0;
        countEight = 0;
        countNine = 0;
        countTen = 0;
        countJack = 0;
        countQueen = 0;
        countKing = 0;
        countAce = 0;
        cardSt = "";
    }

    /**
     * Checks if the current deal has straight.
     *
     * @param arr Current deal's cards
     */
    private void checkStraight(Card[] arr) {
        // contains only value of the cards
        int valArr[] = new int[5];
        for (int i = 0; i < arr.length; i++) {
            valArr[i] = arr[i].getValue();
        }

        Arrays.sort(valArr); // sort the array

        // checks if the 2nd, 3rd, 4th, and 5th cards are
        // 1, 2, 3, 4 respectively bigger than the 1st card
        if (valArr[1] - valArr[0] == 1 && valArr[2] - valArr[0] == 2
                && valArr[3] - valArr[0] == 3 && valArr[4] - valArr[0] == 4) {
            // if they are, set straight to the card status
            cardSt += "Straight! ";
            cardStatus.setText(cardSt);
        }
    }

    /**
     * Checks if the current deal has flush.
     * 
     * @param arr Current deal's cards
     */
    private void checkFlush(Card[] arr) {
        // checks if every card in the current arr Card has the same kind
        if (arr[0].getKind().equals(arr[1].getKind())
                && arr[0].getKind().equals(arr[2].getKind())
                && arr[0].getKind().equals(arr[3].getKind())
                && arr[0].getKind().equals(arr[4].getKind())) {
            // if it is, set flush to the card status
            cardSt += "Flush! ";
            cardStatus.setText(cardSt);
        }
    }

    /**
     * Check amount of cards that have same value
     * @param num Number of card
     * @param count Counter of that number
     * @param arr Current deal's cards
     */
    private void checkNum(String num, int count, Card[] arr) {
        // For every card in arr
        for (Card card : arr) {
            // if the number of the card equals to num,
            if (card.getNumber().equals(num)) {
                // counter of that number increments
                count++;
            }
        }

        // Puts count value to switch-case to set card status
        switch (count) {
            case 2:
                cardSt += "Pair of " + num + "'s. ";
                break;
            case 3:
                cardSt += "Three of a kind of " + num + "'s. ";
                break;
            case 4:
                cardSt += "Four of " + num + "'s. ";
                break;
        }
        cardStatus.setText(cardSt); // set cardSt to cardStatus label
    }

    /**
     * Sets cardStatus label, if any.
     * @param s1 1st card of the deal
     * @param s2 2nd card of the deal
     * @param s3 3rd card of the deal
     * @param s4 4th card of the deal
     * @param s5 5th card of the deal
     */
    private void setStatus(Card s1, Card s2, Card s3, Card s4, Card s5) {
        Card[] check = {s1, s2, s3, s4, s5}; //put Cards into array
        // check scoring
        checkFlush(check);
        checkStraight(check);
        checkNum("Two", countTwo, check);
        checkNum("Three", countThree, check);
        checkNum("Four", countFour, check);
        checkNum("Five", countFive, check);
        checkNum("Six", countSix, check);
        checkNum("Seven", countSeven, check);
        checkNum("Eight", countEight, check);
        checkNum("Nine", countNine, check);
        checkNum("Ten", countTen, check);
        checkNum("Jack", countJack, check);
        checkNum("Queen", countQueen, check);
        checkNum("King", countKing, check);
        checkNum("Ace", countAce, check);
    }

    /**
     * Interface.
     */
    private void setup() {
        all.getChildren().addAll(boxBtns, showTa, cardStatus);
        all.setAlignment(Pos.CENTER);
        showTa.setMaxSize(200, 200);

        boxBtns.getChildren().addAll(btnDeal, btnShuf);
        boxBtns.setAlignment(Pos.CENTER);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
