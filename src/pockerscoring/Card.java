/*
 * This program is written by Pikulkaew Boonpeng for Advanced Java Programming class by
 * Professor Richmond, A at BunkerHill Community College.
 */
package pockerscoring;

/**
 * Contains value, String value, and kind of the card.
 *
 * @author Pikulkaew Boonpeng
 */
public class Card {

    /**
     * Stores int value of the card.
     */
    int value;

    /**
     * Store String value of the card.
     */
    String number;

    /**
     * Stores kind of the card.
     */
    String kind;

    /**
     * Sets nothing.
     */
    public Card() {

    }

    /**
     * Sets value to card.
     *
     * @param value Value of the card
     * @param number String value of the card
     * @param kind Kind of the card
     */
    public Card(int value, String number, String kind) {
        this.value = value;
        this.number = number;
        this.kind = kind;
    }

    /**
     * Gets value of the card.
     *
     * @return Value of the card
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Gets String value of the card.
     *
     * @return String value of the card
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Gets kind of the card.
     *
     * @return Kind of the card
     */
    public String getKind() {
        return this.kind;
    }

}
