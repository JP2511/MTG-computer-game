package mtg.mendonca;

import java.util.ArrayList;

public class Stack {
    private Card card;
    private ArrayList<Card> counterSpellCards;

    public void addToStack(Card cardToBeAdded) {
        this.card = cardToBeAdded;
    }

    public Card removeFromStack() {
        Card cardToBeReturned = this.card;
        this.card = new Card("NoName", "NoColor", "0", "NoType", "NoEffect");
        return cardToBeReturned;
    }

    public Card getCardFromStack() {
        return this.card;
    }

    public void addCounterSpellsToStack(Card cardToBeAdded) {
        this.counterSpellCards.add(cardToBeAdded);
    }

    public Card getLastCardFromCounterSpellStack() {
        return this.counterSpellCards.get(this.counterSpellCards.size() - 1);
    }

    public ArrayList<Card> removeAllCounterSpells() {
        ArrayList<Card> cardsToBeRemoved = this.counterSpellCards;
        this.counterSpellCards = new ArrayList<>();
        return cardsToBeRemoved;
    }
}