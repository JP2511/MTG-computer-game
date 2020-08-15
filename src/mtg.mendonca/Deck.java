package mtg.mendonca;

import java.util.Collections;
import java.util.List;

class Deck {
    private List<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = deck;
    }

    public void addCarta(List<Card> cartas) {
       for(int i = 0; i < cartas.size(); i++) {
           this.deck.add(cartas.get(i));
       }
    }

    public void drawOrRemoveCard(List<Card> cartas) {
        for(int i = 0; i < cartas.size(); i++) {
            this.deck.remove(cartas.get(i));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

}