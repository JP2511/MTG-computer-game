package mtg.mendonca;

import java.util.ArrayList;

public class Garbage {
    private ArrayList<Card> garbage = new ArrayList<>();

    public void sendToGarbage(Card card) {
        this.garbage.add(card);
    }

    public Card removeFromGarbage(int index) {
        Card cardToBeReturned = this.garbage.get(index);
        this.garbage.remove(index);
        return cardToBeReturned;
    }

    public void showGraveyard() {
        ArrayList<Card> stackedCardsLine = new ArrayList();
        System.out.println("----------------------------------------------------------------------------------Graveyard----------------------------------------------------------------------------------");
        for(int i = 0; i < this.garbage.size(); i++) {
            if((i+1) % 5 != 0 && i != this.garbage.size()-1) {
                stackedCardsLine.add(this.garbage.get(i));
            } else {
                stackedCardsLine.add(this.garbage.get(i));
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
        System.out.println("------------------------------------------------------------------------------END Graveyard----------------------------------------------------------------------------------");
    }
}