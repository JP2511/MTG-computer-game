package mtg.mendonca;

public class Stack {
    private Card card;

    public void addToStack(Card cardToBeAdded) {
        this.card = cardToBeAdded;
    }

    public Card removeFromStack() {
        Card cardToBeReturned = this.card;
        this.card = new Card("NoName", "NoColor", "0", "NoType", "NoEffect");
        return cardToBeReturned;
    }
}