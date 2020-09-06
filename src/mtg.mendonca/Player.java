package mtg.mendonca;

public class Player {
    private int life;
    private String name;
    private Deck deck;
    private Hand hand = new Hand();
    private Stack stack = new Stack();
    private Field field = new Field();
    private Garbage garbage = new Garbage();
    private Exile exile = new Exile();

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

    public void playCardFromHandToStack(int i) {
        this.stack.addToStack(this.hand.removeFromHand(i));
    }

    public void playCardFromDeckToStack() {
        this.stack.addToStack(this.deck.drawOrRemoveCards(1).get(0));
    }

    public void playCardFromGarbageToStack(int index) {
        this.stack.addToStack(this.garbage.removeFromGarbage(index));
    }

    public void playCardFromStackToField() {
        Card cardToBeAddedToField = this.stack.removeFromStack();
        switch(cardToBeAddedToField.getType()) {
            case "Creature":
                this.field.addCreatureToField((Creature) cardToBeAddedToField);
                break;
            case "Instant":
                this.field.addInstantOrSorceryToField(cardToBeAddedToField);
                break;
            case "Sorcery":
                this.field.addInstantOrSorceryToField(cardToBeAddedToField);
                break;
            case "Land":
                this.field.addBasicLandToField((Land) cardToBeAddedToField);
                break;
            case "Artifact":
                this.field.addArtifactToField((Artifact) cardToBeAddedToField);
                break;
            case "Planeswalker":
                this.field.addPlaneswalkerToField((Planeswalker) cardToBeAddedToField);
        }
    }

    public void moveCardFromStackToGarbage() {
        this.garbage.sendToGarbage(this.stack.removeFromStack());
    }

    public void moveCardFromStackToHand() {
        this.hand.sendToHand(this.stack.removeFromStack());
    }

    public void moveCardFromHandToExile(int index) {
        this.exile.exileCard(this.hand.removeFromHand(index));
    }

}