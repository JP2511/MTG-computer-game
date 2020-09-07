package mtg.mendonca;

import java.util.ArrayList;

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

    public void moveCardFromStackToHand() {
        this.hand.sendToHand(this.stack.removeFromStack());
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
                break;
            case "Enchantment":
                this.field.addEnchantmentToField((Enchantment) cardToBeAddedToField);
                break;
        }
    }

    public void moveCardFromStackToGarbage() {
        this.garbage.sendToGarbage(this.stack.removeFromStack());
    }

    public void moveCardFromFieldToHand(String cardType, int index) {
        switch (cardType) {
            case "Creature":
                ArrayList<Card> arrayOfCreaturesAndEnchantments = this.field.removeCreatureFromField(index);
                for(int i = 0; i < arrayOfCreaturesAndEnchantments.size(); i++) {
                    if(arrayOfCreaturesAndEnchantments.get(i).getType() == "Creature") {
                        this.hand.sendToHand(arrayOfCreaturesAndEnchantments.get(i));
                    } else {
                        this.hand.sendToHand(arrayOfCreaturesAndEnchantments.get(i));
                    }
                }
                break;
            case "Land":
                this.hand.sendToHand(this.field.removeBasicLandFromField(index));
                break;
            case "Artifact":
                this.hand.sendToHand(this.field.removeArtifactFromField(index));
                break;
            case "Planeswalker":
                this.hand.sendToHand(this.field.removePlaneswalkerFromField(index));
                break;
            default:
                System.out.println("Something wrong with the usage of player.moveCardFromFieldToHand");
        }
    }

    public void moveCardFromFieldToHand(String cardType, int indexOfCreature, int indexOfEnchantment) {
        switch (cardType) {
            case "Enchantment":
                this.hand.sendToHand(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
                break;
            default:
                System.out.println("Something wrong with the usage of player.moveCardFromFieldToHand (enchantment)");
        }
    }

    public void moveCardFromFieldToDeck(String cardType, int index) {
        switch (cardType) {
            case "Creature":
                ArrayList<Card> arrayOfCreaturesAndEnchantments = this.field.removeCreatureFromField(index);
                for(int i = 0; i < arrayOfCreaturesAndEnchantments.size(); i++) {
                    if(arrayOfCreaturesAndEnchantments.get(i).getType() == "Creature") {
                        this.deck.addCards(arrayOfCreaturesAndEnchantments.get(i));
                    } else {
                        this.deck.addCards(arrayOfCreaturesAndEnchantments.get(i));
                    }
                }
                break;
            case "Land":
                this.deck.addCards(this.field.removeBasicLandFromField(index));
                break;
            case "Artifact":
                this.deck.addCards(this.field.removeArtifactFromField(index));
                break;
            case "Planeswalker":
                this.deck.addCards(this.field.removePlaneswalkerFromField(index));
                break;
            default:
                System.out.println("Something wrong with the usage of player.moveCardFromFieldToDeck");
        }
    }

    public void moveCardFromFieldToDeck(String cardType, int indexOfCreature, int indexOfEnchantment) {
        switch (cardType) {
            case "Enchantment":
                this.deck.addCards(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
                break;
            default:
                System.out.println("Something wrong with the usage of player.moveCardFromFieldToDeck (enchantment)");
        }
    }

    public void moveCardFromFieldToGarbage(String cardType, int index) {
        switch (cardType) {
            case "Creature":
                ArrayList<Card> arrayOfCreaturesAndEnchantments = this.field.removeCreatureFromField(index);
                for(int i = 0; i < arrayOfCreaturesAndEnchantments.size(); i++) {
                    if(arrayOfCreaturesAndEnchantments.get(i).getType() == "Creature") {
                        this.garbage.sendToGarbage(arrayOfCreaturesAndEnchantments.get(i));
                    } else {
                        this.garbage.sendToGarbage(arrayOfCreaturesAndEnchantments.get(i));
                    }
                }
                break;
            case "Land":
                this.garbage.sendToGarbage(this.field.removeBasicLandFromField(index));
                break;
            case "Artifact":
                this.garbage.sendToGarbage(this.field.removeArtifactFromField(index));
                break;
            case "Planeswalker":
                this.garbage.sendToGarbage(this.field.removePlaneswalkerFromField(index));
                break;
            default:
                System.out.println("Something wrong with the usage of player.moveCardFromFieldToGarbage");
        }
    }

    public void moveCardFromFieldToGarbage(String cardType, int indexOfCreature, int indexOfEnchantment) {
        switch (cardType) {
            case "Enchantment":
                this.garbage.sendToGarbage(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
                break;
            default:
                System.out.println("Something wrong with the usage of player.moveCardFromFieldToGarbage (enchantment)");
        }
    }

    public void moveCardFromFieldToExile(String cardType, int index) {
        switch (cardType) {
            case "Creature":
                ArrayList<Card> arrayOfCreaturesAndEnchantments = this.field.removeCreatureFromField(index);
                for(int i = 0; i < arrayOfCreaturesAndEnchantments.size(); i++) {
                    if(arrayOfCreaturesAndEnchantments.get(i).getType() == "Creature") {
                        this.exile.exileCard(arrayOfCreaturesAndEnchantments.get(i));
                    } else {
                        this.garbage.sendToGarbage(arrayOfCreaturesAndEnchantments.get(i));
                    }
                }
                break;
            case "Land":
                this.exile.exileCard(this.field.removeBasicLandFromField(index));
                break;
            case "Artifact":
                this.exile.exileCard(this.field.removeArtifactFromField(index));
                break;
            case "Planeswalker":
                this.exile.exileCard(this.field.removePlaneswalkerFromField(index));
                break;
            default:
                System.out.println("Something wrong with the usage of player.moveCardFromFieldToExile");
        }
    }

    public void moveCardFromFieldToExile(String cardType, int indexOfCreature, int indexOfEnchantment) {
        switch (cardType) {
            case "Enchantment":
                this.exile.exileCard(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
                break;
            default:
                System.out.println("Something wrong with the usage of player.moveCardFromFieldToExile (enchantment)");
        }
    }

    public void moveCardFromHandToDeck(int index) {
        this.deck.addCards(this.hand.removeFromHand(index));
    }

    public void moveCardFromHandToGarbage(int index) {
        this.garbage.sendToGarbage(this.hand.removeFromHand(index));
    }

    public void moveCardFromHandToExile(int index) {
        this.exile.exileCard(this.hand.removeFromHand(index));
    }

    public void moveCardFromDeckToGarbage(int numberOfCardsToRemove) {
        ArrayList<Card> cardsToBeDiscarded = this.deck.drawOrRemoveCards(numberOfCardsToRemove);
        for(int i = 0; i < cardsToBeDiscarded.size(); i++) {
            this.garbage.sendToGarbage(cardsToBeDiscarded.get(i));
        }
    }

    public void moveCardFromDeckToExile(int numberOfCardsToRemove) {
        ArrayList<Card> cardsToBeDiscarded = this.deck.drawOrRemoveCards(numberOfCardsToRemove);
        for(int i = 0; i < cardsToBeDiscarded.size(); i++) {
            this.exile.exileCard(cardsToBeDiscarded.get(i));
        }
    }

    public void moveCardFromGarbageToHand(int index) {
        this.hand.sendToHand(this.garbage.removeFromGarbage(index));
    }

    public void moveCardFromGarbageToDeck(int index) {
        this.deck.addCards(this.garbage.removeFromGarbage(index));
    }

    public void moveCardFromGarbageToExile(int index) {
        this.exile.exileCard(this.garbage.removeFromGarbage(index));
    }
}