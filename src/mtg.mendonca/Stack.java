package mtg.mendonca;

import java.util.ArrayList;

public class Stack {
    private Card card;
    private ArrayList<Card> counterSpellCards = new ArrayList<>();

    public void addToStack(Card cardToBeAdded) {
        this.card = cardToBeAdded;
    }

    public Card removeFromStack() {
        Card cardToBeReturned = this.card;
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

    public Card playLastCounterSpell() {
        Card counterSpell = this.counterSpellCards.get(this.counterSpellCards.size() - 1);
        this.counterSpellCards.remove(this.counterSpellCards.size() - 1);
        return counterSpell;
    }

    public int countCounterSpellsUsed() {
        return this.counterSpellCards.size();
    }

    public ArrayList<Card> removeAllCounterSpells() {
        ArrayList<Card> cardsToBeRemoved = this.counterSpellCards;
        this.counterSpellCards = new ArrayList<>();
        return cardsToBeRemoved;
    }
}