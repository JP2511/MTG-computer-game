package mtg.mendonca;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Deck {
    private ArrayList<Card> deck;

    public int getSize() {
        return this.deck.size();
    }

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void addCards(ArrayList<Card> cartas) {
        for (int i = 0; i < cartas.size(); i++) {
            this.deck.add(cartas.get(i));
        }
    }

    public void addCards(Card card) {
        this.deck.add(card);
    }

    public ArrayList<Card> drawOrRemoveCards(int n) {
        n = this.deck.size() < n ? this.deck.size() : n;
        ArrayList<Card> cards = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            cards.add(this.deck.get(0));
            this.deck.remove(0);
        }
        return cards;
    }

    public ArrayList<Card> drawOrRemoveCards(ArrayList<String> nomesDasCartas) {
        ArrayList<Card> cartas = new ArrayList();
        for (int i = 0; i < nomesDasCartas.size(); i++) {
            for (int j = 0; j < this.deck.size(); j++) {
                if (nomesDasCartas.get(i) == this.deck.get(j).getName()) {
                    cartas.add(this.deck.get(j));
                    this.deck.remove(j);
                    break;
                }
            }
        }
        return cartas;
    }

    public ArrayList<Card> drawOrRemoveAllCards(ArrayList<String> nomesDasCartas) {
        ArrayList<Card> cartas = new ArrayList();
        for (int i = 0; i < nomesDasCartas.size(); i++) {
            for (int j = 0; j < this.deck.size(); j++) {
                if (nomesDasCartas.get(i) == this.deck.get(j).getName()) {
                    cartas.add(this.deck.get(j));
                    this.deck.remove(j);
                }
            }
        }
        return cartas;
    }

    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    public void showCards(List indexes) {
        int numeroDeListasNaLista = (int) Math.floor(indexes.size() / 5);
        numeroDeListasNaLista += indexes.size() % 5 != 0 ? 1 : 0;

        for (int i = 0; i < numeroDeListasNaLista; i++) {
            ArrayList<String> stackedCardsLine = new ArrayList<>();
            if (i != numeroDeListasNaLista - 1 || indexes.size() % 5 == 0) {
                for (int j = 0 + 5 * i; j < 5 + 5 * i; j++) {
                    for (int h = 0; h < 15; h++) {
                        stackedCardsLine.add(this.deck.get((int) indexes.get(j)).getCard()[h]);
                    }
                }
                int[] orderOfCards = createListOfIndexForOrderCards(5);
                for(int j = 0; j < orderOfCards.length; j++) {
                    if((j+1) % 5 != 0) {
                        System.out.print(stackedCardsLine.get(orderOfCards[j]) + "   ");
                    } else {
                        System.out.println(stackedCardsLine.get(orderOfCards[j]));
                    }
                }
            } else {
                for (int j = 0 + 5 * i; j < (indexes.size() % 5) + 5 * i; j++) {
                    for (int h = 0; h < 15; h++) {
                        stackedCardsLine.add(this.deck.get((int) indexes.get(j)).getCard()[h]);
                    }
                }
                int[] orderOfCards = createListOfIndexForOrderCards(indexes.size()%5);
                for(int j = 0; j < orderOfCards.length; j++) {
                    if((j+1) % (indexes.size() % 5) != 0) {
                        System.out.print(stackedCardsLine.get(orderOfCards[j]) + "   ");
                    } else {
                        System.out.println(stackedCardsLine.get(orderOfCards[j]));
                    }
                }
            }
        }
    }

    public int[] createListOfIndexForOrderCards ( int n){
        int[] orderOfCards = new int[15 * n];
        for (int i = 0; i < orderOfCards.length; i += n) {
            if (i == 0) {
                for (int j = 0; j < n; j++) {
                    orderOfCards[i + j] = i + j * 15;
                }
            } else {
                for (int j = 0; j < n; j++) {
                    orderOfCards[i + j] = orderOfCards[i + j - n] + 1;
                }
            }
        }
        return orderOfCards;
    }
}