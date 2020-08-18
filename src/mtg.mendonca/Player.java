package mtg.mendonca;

public class Player {
    private int life;
    private String name;
    private Deck deck;
    private Hand hand;

    public Player(int life, String name, Deck deck) {
        this.life = life;
        this.name = name;
        this.deck = deck;
    }

    public Player(String name, Deck deck) {
        this(20, name, deck);
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return this.life;
    }

    public void loseLife(int extra) {
        this.life -= extra;
    }

    public void gainLife(int extra) {
        this.life += extra;
    }

    public void drawCards(int n) {
        this.hand.sendToHand(this.deck.drawOrRemoveCards(n));
    }

    public void playCard(int i) {

    }

}