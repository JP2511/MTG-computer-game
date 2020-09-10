package mtg.mendonca;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();

    public void sendToHand(ArrayList<Card> cards) {
        for(int i = 0; i < cards.size(); i++) {
            this.hand.add(cards.get(i));
        }
    }

    public void sendToHand(Card card) {
        this.hand.add(card);
    }

    public Card removeFromHand(int index) {
        Card cardToRemoveFromHand = this.hand.get(index);
        this.hand.remove(index);
        return cardToRemoveFromHand;
    }

    public void showHand(int n) {
        if(n > this.hand.size()) {
            n = this.hand.size();
        }
        ArrayList<Card> stackedCardsLine = new ArrayList();
        for(int i = 0; i < n; i++) {
            if((i+1) % 5 != 0 && i != n-1) {
                stackedCardsLine.add(this.hand.get(i));
            } else {
                stackedCardsLine.add(this.hand.get(i));
                for(int g = 0; g < 15; g++) {
                    for (int j = 0; j < stackedCardsLine.size(); j++) {
                        if(j != stackedCardsLine.size()-1) {
                            System.out.print(stackedCardsLine.get(j).getCard()[g] + "   ");
                        } else {
                            System.out.println(stackedCardsLine.get(j).getCard()[g]);
                        }
                    }
                }
                stackedCardsLine.clear();
            }
        }
    }

    public int sizeOfHand() {
        return this.hand.size();
    }
}