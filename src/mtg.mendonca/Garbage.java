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
}