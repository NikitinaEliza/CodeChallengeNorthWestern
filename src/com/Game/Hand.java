package com.Game;

import java.util.Vector;

public class Hand {

    private Vector<Card> hand; // The cards in the hand.

    public Hand() {
        hand = new Vector<>();
    }

    public void clear() { // Discard all the cards from the hand.
        hand.removeAllElements();
    }

    public void addCard(Card c) {
        // Add the card c to the hand.  c should be non-null.  (If c is
        // null, nothing is added to the hand.)
        if (c != null) {
            hand.addElement(c);
        }
    }

    public void removeCard(int position) {
        // If the specified position is a valid position in the hand,
        // then the card in that position is removed.
        if (position >= 0 && position < hand.size())
            hand.removeElementAt(position);
    }

    public int getCardCount() {
        // Return the number of cards in the hand.
        return hand.size();
    }

    public Card getCard(int position) {
        // Get the card from the hand in given position, where positions
        // are numbered starting from 0.  If the specified position is
        // not the position number of a card in the hand, then null
        // is returned.
        if (position >= 0 && position < hand.size())
            return hand.elementAt(position);
        else
            return null;
    }



}
