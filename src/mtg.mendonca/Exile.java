package mtg.mendonca;

import java.util.ArrayList;

public class Exile {
    private ArrayList<Card> exiledCards = new ArrayList<>();

    public void exileCard(Card card) {
        System.out.println(card.getName() + " was exiled.");
        exiledCards.add(card);
    }
}