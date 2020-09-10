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

    public void mulligan() {    // returns the cards from the hand to the deck, shuffles the deck and draws one less card than the previous number of cards in the hand
        int numberOfCards = this.hand.sizeOfHand();
        for(int i = 0; i < numberOfCards; i++) {
            moveCardFromHandToDeck(0);
        }
        this.deck.shuffleDeck();
        drawCards(numberOfCards - 1);
    }

    public void playCardFromHandToStack(int i, int numberOfRedLands, int numberOfGreenLands, int numberOfWhiteLands, int numberOfBlueLands, int numberOfBlackLands) {
        Card cardToBePlayed = this.hand.removeFromHand(i);
        String mana = cardToBePlayed.getManaCost();
        int redMana = 0;
        boolean redCorrect = true;
        int greenMana = 0;
        boolean greenCorrect = true;
        int whiteMana = 0;
        boolean whiteCorrect = true;
        int blueMana = 0;
        boolean blueCorrect = true;
        int blackMana = 0;
        boolean blackCorrect = true;
        boolean numberCorrect = true;
        if(mana.contains("R")) {
            redMana = mana.split("R").length - 1;
            if(redMana <= this.field.countNumberOfUntappedColoredLands("Red")) {
                redCorrect = true;
            } else {
                redCorrect = false;
            }
        } else if (mana.contains("G")) {
            greenMana = mana.split("G").length - 1;
            if(greenMana <= this.field.countNumberOfUntappedColoredLands("Green")) {
                greenCorrect = true;
            } else {
                greenCorrect = false;
            }
        } else if (mana.contains("W")) {
            whiteMana = mana.split("W").length - 1;
            if(whiteMana <= this.field.countNumberOfUntappedColoredLands("White")) {
                whiteCorrect = true;
            } else {
                whiteCorrect = false;
            }
        }  else if (mana.contains("U")) {
            blueMana = mana.split("U").length - 1;
            if(blueMana <= this.field.countNumberOfUntappedColoredLands("Blue")) {
                blueCorrect = true;
            } else {
                blueCorrect = false;
            }
        }  else if (mana.contains("B")) {
            blackMana = mana.split("B").length - 1;
            if(blackMana <= this.field.countNumberOfUntappedColoredLands("Black")) {
                blackCorrect = true;
            } else {
                blackCorrect = false;
            }
        } else if (mana.matches(".*\\d.*")){
            int number = Integer.parseInt(mana.replaceAll("[^0-9]", ""));
            if(number <= this.field.countUntappedLand() - (redMana + greenMana + whiteMana + blueMana + blackMana)) {
                numberCorrect = true;
            } else {
                numberCorrect = false;
            }
        }
        if(redCorrect && greenCorrect && whiteCorrect && blueCorrect && blackCorrect && numberCorrect) {
            this.stack.addToStack(this.hand.removeFromHand(i));
            this.field.tapLands(numberOfRedLands, numberOfGreenLands, numberOfWhiteLands, numberOfBlueLands, numberOfBlackLands);
        } else {
            System.out.println("You don't have enough mana to play that card.");
        }
    }

    public void moveCardFromHandToStack(int i) {
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
        if(cardType.equals("Enchantment")) {
            this.hand.sendToHand(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToHand (enchantment)");
        }
    }

    public void moveCardFromFieldToHand(String cardType, String color)  {
        if(cardType.equals("Land")) {
            this.hand.sendToHand(this.field.removeBasicLandFromField(color));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToHand (land)");
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
        if (cardType.equals("Enchantment")) {
            this.deck.addCards(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToDeck (enchantment)");
        }
    }

    public void moveCardFromFieldToDeck(String cardType, String color) {
        if(cardType.equals("Land")) {
            this.deck.addCards(this.field.removeBasicLandFromField(color));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToDeck (land)");
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
        if(cardType.equals("Enchantment")) {
            this.garbage.sendToGarbage(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToGarbage (enchantment)");
        }
    }

    public void moveCardFromFieldToGarbage(String cardType, String color) {
        if(cardType.equals("Land")) {
            this.garbage.sendToGarbage(this.field.removeBasicLandFromField(color));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToGarbage (land)");
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
        if(cardType.equals("Enchantment")) {
            this.exile.exileCard(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToExile (enchantment)");
        }
    }

    public void moveCardFromFieldToExile(String cardType, String color) {
        if(cardType.equals("Enchantment")) {
            this.exile.exileCard(this.field.removeBasicLandFromField(color));
        } else {
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