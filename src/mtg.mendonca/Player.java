package mtg.mendonca;

import java.util.ArrayList;

public class Player {
    private int life;
    private String name;
    private Deck deck;
    private Hand hand = new Hand();
    private Stack stack = new Stack();
    private Field field = new Field();
    private Graveyard graveyard = new Graveyard();
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
        for (int i = 0; i < numberOfCards; i++) {
            moveCardFromHandToDeck(0);
        }
        this.deck.shuffleDeck();
        drawCards(numberOfCards - 1);
    }

    public void shuffleDeck() {
        this.deck.shuffleDeck();
    }

    public boolean playCardFromHandToStack(int i, int numberOfRedLands, int numberOfGreenLands, int numberOfWhiteLands, int numberOfBlueLands, int numberOfBlackLands, boolean isCardACounter) {
        boolean isCardPlayed;
        Card cardToBePlayed = this.hand.getCardFromHand(i);
        String mana = cardToBePlayed.getManaCost();

        if (canPayCard(mana, numberOfRedLands, numberOfGreenLands, numberOfWhiteLands, numberOfBlueLands, numberOfBlackLands)) {
            if (isCardACounter) {
                this.stack.addCounterSpellsToStack(cardToBePlayed);
            } else {
                this.stack.addToStack(cardToBePlayed);
            }
            this.hand.removeFromHand(i);
            this.field.tapLands(numberOfRedLands, numberOfGreenLands, numberOfWhiteLands, numberOfBlueLands, numberOfBlackLands);
            isCardPlayed = true;

            // shows card
            System.out.println(" ");
            for (int j = 0; j < 15; j++) {
                System.out.println(cardToBePlayed.getCard()[j]);
            }

        } else {
            System.out.println("You don't have enough mana to play that card.");
            isCardPlayed = false;
        }

        return isCardPlayed;
    }

    public void moveCardFromHandToStack(int i) {
        for (int j = 0; j < 15; j++) {
            System.out.println(this.hand.getCardFromHand(i).getCard()[j]);
        }
        this.stack.addToStack(this.hand.removeFromHand(i));
    }

    public void playCardFromDeckToStack() {
        this.stack.addToStack(this.deck.drawOrRemoveCards(1).get(0));
    }

    public void playCardFromGarbageToStack(int index) {
        this.stack.addToStack(this.graveyard.removeFromGarbage(index));
    }

    public void moveCardFromStackToHand() {
        this.hand.sendToHand(this.stack.removeFromStack());
    }

    public void playCardFromStackToField(int turn) {
        Card cardToBeAddedToField = this.stack.removeFromStack();
        switch (cardToBeAddedToField.getType()) {
            case "Creature":
                this.field.addCreatureToField((Creature) cardToBeAddedToField);
                defineTheTurnACreatureWasPlayedOfTheLastAddedCreature(turn);
                break;
            case "Instant":
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

    public boolean checkForCounterSpell() {
        return this.stack.checkForCounterSpell();
    }

    public void playCounterSpellFromStackToField() {
        this.field.addInstantOrSorceryToField(this.stack.playLastCounterSpell());
    }

    public void moveCardFromStackToGarbage() {
        this.graveyard.sendToGarbage(this.stack.removeFromStack());
    }

    public void moveCardFromFieldToHand(String cardType, int index) {
        switch (cardType) {
            case "Creature":
                ArrayList<Card> arrayOfCreaturesAndEnchantments = this.field.removeCreatureFromField(index);
                for (int i = 0; i < arrayOfCreaturesAndEnchantments.size(); i++) {
                    if (arrayOfCreaturesAndEnchantments.get(i).getType() == "Creature") {
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
        if (cardType.equals("Enchantment")) {
            this.hand.sendToHand(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToHand (enchantment)");
        }
    }

    public void moveCardFromFieldToHand(String cardType, String color) {
        if (cardType.equals("Land")) {
            this.hand.sendToHand(this.field.removeBasicLandFromField(color));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToHand (land)");
        }
    }

    public void moveCardFromFieldToDeck(String cardType, int index) {
        switch (cardType) {
            case "Creature":
                ArrayList<Card> arrayOfCreaturesAndEnchantments = this.field.removeCreatureFromField(index);
                for (int i = 0; i < arrayOfCreaturesAndEnchantments.size(); i++) {
                    this.deck.addCards(arrayOfCreaturesAndEnchantments.get(i));
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
        if (cardType.equals("Land")) {
            this.deck.addCards(this.field.removeBasicLandFromField(color));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToDeck (land)");
        }
    }

    public void moveCardFromFieldToGarbage(String cardType, int index) {
        switch (cardType) {
            case "Creature":
                ArrayList<Card> arrayOfCreaturesAndEnchantments = this.field.removeCreatureFromField(index);
                for (int i = 0; i < arrayOfCreaturesAndEnchantments.size(); i++) {
                    this.graveyard.sendToGarbage(arrayOfCreaturesAndEnchantments.get(i));
                }
                break;
            case "Artifact":
                this.graveyard.sendToGarbage(this.field.removeArtifactFromField(index));
                break;
            case "Planeswalker":
                this.graveyard.sendToGarbage(this.field.removePlaneswalkerFromField(index));
                break;
            case "Instant":
            case "Sorcery":
                this.graveyard.sendToGarbage(this.field.removeInstantOrSorceryFromField());
                break;
            default:
                System.out.println("Something wrong with the usage of player.moveCardFromFieldToGarbage");
        }
    }

    public void moveCardFromFieldToGarbage(String cardType, int indexOfCreature, int indexOfEnchantment) {
        if (cardType.equals("Enchantment")) {
            this.graveyard.sendToGarbage(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToGarbage (enchantment)");
        }
    }

    public void moveCardFromFieldToGarbage(String cardType, String color) {
        if (cardType.equals("Land")) {
            this.graveyard.sendToGarbage(this.field.removeBasicLandFromField(color));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToGarbage (land)");
        }
    }

    public void moveCardFromFieldToExile(String cardType, int index) {
        switch (cardType) {
            case "Creature":
                ArrayList<Card> arrayOfCreaturesAndEnchantments = this.field.removeCreatureFromField(index);
                for (int i = 0; i < arrayOfCreaturesAndEnchantments.size(); i++) {
                    if (arrayOfCreaturesAndEnchantments.get(i).getType() == "Creature") {
                        this.exile.exileCard(arrayOfCreaturesAndEnchantments.get(i));
                    } else {
                        this.graveyard.sendToGarbage(arrayOfCreaturesAndEnchantments.get(i));
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
        if (cardType.equals("Enchantment")) {
            this.exile.exileCard(this.field.removeEnchantmentFromField(indexOfCreature, indexOfEnchantment));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToExile (enchantment)");
        }
    }

    public void moveCardFromFieldToExile(String cardType, String color) {
        if (cardType.equals("Enchantment")) {
            this.exile.exileCard(this.field.removeBasicLandFromField(color));
        } else {
            System.out.println("Something wrong with the usage of player.moveCardFromFieldToExile (enchantment)");
        }
    }

    public void moveCardFromHandToDeck(int index) {
        this.deck.addCards(this.hand.removeFromHand(index));
    }

    public void moveCardFromHandToGarbage(int index) {
        this.graveyard.sendToGarbage(this.hand.removeFromHand(index));
    }

    public void moveCardFromHandToExile(int index) {
        this.exile.exileCard(this.hand.removeFromHand(index));
    }

    public void moveCardFromDeckToGarbage(int numberOfCardsToRemove) {
        ArrayList<Card> cardsToBeDiscarded = this.deck.drawOrRemoveCards(numberOfCardsToRemove);
        for (int i = 0; i < cardsToBeDiscarded.size(); i++) {
            this.graveyard.sendToGarbage(cardsToBeDiscarded.get(i));
        }
    }

    public void moveCardFromDeckToExile(int numberOfCardsToRemove) {
        ArrayList<Card> cardsToBeDiscarded = this.deck.drawOrRemoveCards(numberOfCardsToRemove);
        for (int i = 0; i < cardsToBeDiscarded.size(); i++) {
            this.exile.exileCard(cardsToBeDiscarded.get(i));
        }
    }

    public void moveCardFromGarbageToHand(int index) {
        this.hand.sendToHand(this.graveyard.removeFromGarbage(index));
    }

    public void moveCardFromGarbageToDeck(int index) {
        this.deck.addCards(this.graveyard.removeFromGarbage(index));
    }

    public void moveCardFromGarbageToExile(int index) {
        this.exile.exileCard(this.graveyard.removeFromGarbage(index));
    }

    public void showHand() {
        this.hand.showHand(this.hand.sizeOfHand());
    }

    public int handSize() {
        return this.hand.sizeOfHand();
    }

    public ArrayList<String> namesOfCardsInHand() {
        return this.hand.namesOfCardsInHand();
    }

    public ArrayList<Integer> namesOfLandsInHand() {
        return this.hand.namesOfLandsInHand();
    }

    public Card getCardFromHand(int index) {
        return this.hand.getCardFromHand(index);
    }

    public boolean canPayCard(String manaCost, int numberOfRedLands, int numberOfGreenLands, int numberOfWhiteLands, int numberOfBlueLands, int numberOfBlackLands) {
        int possibleManaCosts = 1;
        if(manaCost.contains("/")) {
            possibleManaCosts += countCharInString(manaCost, "/".charAt(0));
        } else {
            manaCost += "/";
        }
        for(int i = 0; i < possibleManaCosts; i++) {
            String mana = manaCost.split("/")[i];

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

            if (mana.contains("R")) {
                redMana = countCharInString(mana, 'R');
                if (redMana <= this.field.countNumberOfUntappedColoredLands("Red") && numberOfRedLands >= redMana && numberOfRedLands <= this.field.countNumberOfUntappedColoredLands("Red")) {
                    redCorrect = true;
                } else {
                    redCorrect = false;
                }
            }

            if (mana.contains("G")) {
                greenMana = countCharInString(mana, 'G');
                if (greenMana <= this.field.countNumberOfUntappedColoredLands("Green") && numberOfGreenLands >= greenMana && numberOfGreenLands <= this.field.countNumberOfUntappedColoredLands("Green")) {
                    greenCorrect = true;
                } else {
                    greenCorrect = false;
                }
            }

            if (mana.contains("W")) {
                whiteMana = countCharInString(mana, 'W');
                whiteCorrect = whiteMana <= this.field.countNumberOfUntappedColoredLands("White") && numberOfWhiteLands >= whiteMana && numberOfWhiteLands <= this.field.countNumberOfUntappedColoredLands("White");
            }

            if (mana.contains("U")) {
                blueMana = countCharInString(mana, 'U');
                if (blueMana <= this.field.countNumberOfUntappedColoredLands("Blue") && numberOfBlueLands >= blueMana && numberOfBlueLands <= this.field.countNumberOfUntappedColoredLands("Blue")) {
                    blueCorrect = true;
                } else {
                    blueCorrect = false;
                }
            }

            if (mana.contains("B")) {
                blackMana = countCharInString(mana, 'B');
                if (blackMana <= this.field.countNumberOfUntappedColoredLands("Black") && numberOfBlackLands >= blackMana && numberOfBlackLands <= this.field.countNumberOfUntappedColoredLands("Black")) {
                    blackCorrect = true;
                } else {
                    blackCorrect = false;
                }
            }

            if (mana.matches(".*\\d.*")) {
                int number = Integer.parseInt(mana.replaceAll("[^0-9]", ""));
                if (number <= this.field.countUntappedLand() - (redMana + greenMana + whiteMana + blueMana + blackMana)) {
                    numberCorrect = true;
                } else {
                    numberCorrect = false;
                }
            }

            // isManaEnough checks if the numberOfLands asked to tap is smaller or equal to the number of untapped lands for each color
            boolean isManaEnough = numberOfRedLands <= this.field.countNumberOfUntappedColoredLands("Red") && numberOfGreenLands <= this.field.countNumberOfUntappedColoredLands("Green") &&
                    numberOfWhiteLands <= this.field.countNumberOfUntappedColoredLands("White") && numberOfBlueLands <= this.field.countNumberOfUntappedColoredLands("Blue") &&
                    numberOfBlackLands <= this.field.countNumberOfUntappedColoredLands("Black");

            if(redCorrect && greenCorrect && whiteCorrect && blueCorrect && blackCorrect && numberCorrect && isManaEnough) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> canPayForCards(ArrayList<Integer> availableCounterSpells) {
        ArrayList<Integer> counterSpellsThatCanBePayed = new ArrayList<>();
        for (int i = 0; i < availableCounterSpells.size(); i++) {

            String mana = this.hand.getCardFromHand(availableCounterSpells.get(i)).getManaCost();
            int redManaValue = 0;
            boolean redBoolean = true;
            int greenManaValue = 0;
            boolean greenBoolean = true;
            int whiteManaValue = 0;
            boolean whiteBoolean = true;
            int blueManaValue = 0;
            boolean blueBoolean = true;
            int blackManaValue = 0;
            boolean blackBoolean = true;
            int anyManaValue = 0;
            boolean anyManaBoolean = true;

            if (mana.contains("R")) {
                redManaValue = countCharInString(mana, 'R');
                if (redManaValue > this.field.countNumberOfUntappedColoredLands("Red")) {
                    redBoolean = false;
                }
            }

            if (mana.contains("G")) {
                greenManaValue = countCharInString(mana, 'G');
                if (greenManaValue > this.field.countNumberOfUntappedColoredLands("Green")) {
                    greenBoolean = false;
                }
            }

            if (mana.contains("W")) {
                whiteManaValue = countCharInString(mana, 'W');
                if (whiteManaValue > this.field.countNumberOfUntappedColoredLands("White")) {
                    whiteBoolean = false;
                }
            }

            if (mana.contains("U")) {
                blueManaValue = countCharInString(mana, 'U');
                if (blueManaValue > this.field.countNumberOfUntappedColoredLands("Blue")) {
                    blueBoolean = false;
                }
            }

            if (mana.contains("B")) {
                blackManaValue = countCharInString(mana, 'B');
                if (blackManaValue > this.field.countNumberOfUntappedColoredLands("Black")) {
                    blackBoolean = false;
                }
            }

            if (mana.matches(".*\\d.*")) {
                anyManaValue = Integer.parseInt(mana.replaceAll("[^0-9]", ""));
                if (anyManaValue > (this.field.countUntappedLand() - redManaValue - greenManaValue - whiteManaValue - blueManaValue - blackManaValue)) {
                    anyManaBoolean = false;
                }
            }

            if (redBoolean && greenBoolean && whiteBoolean && blueBoolean && blackBoolean && anyManaBoolean) {
                counterSpellsThatCanBePayed.add(availableCounterSpells.get(i));
            }
        }
        return counterSpellsThatCanBePayed;
    }

    public boolean isAbleToPay(String manaCost) {
        int possibleManaCosts = 1;
        if(manaCost.contains("/")) {
            possibleManaCosts += countCharInString(manaCost, "/".charAt(0));
        } else {
            manaCost += "/";
        }
        for(int i = 0; i < possibleManaCosts; i++) {
            String mana = manaCost.split("/")[i];
            int redManaValue = 0;
            boolean redBoolean = true;
            int greenManaValue = 0;
            boolean greenBoolean = true;
            int whiteManaValue = 0;
            boolean whiteBoolean = true;
            int blueManaValue = 0;
            boolean blueBoolean = true;
            int blackManaValue = 0;
            boolean blackBoolean = true;
            int anyManaValue = 0;
            boolean anyManaBoolean = true;

            if (mana.contains("R")) {
                redManaValue = countCharInString(mana, 'R');
                if (redManaValue > this.field.countNumberOfUntappedColoredLands("Red")) {
                    redBoolean = false;
                }
            }

            if (mana.contains("G")) {
                greenManaValue = countCharInString(mana, 'G');
                if (greenManaValue > this.field.countNumberOfUntappedColoredLands("Green")) {
                    greenBoolean = false;
                }
            }

            if (mana.contains("W")) {
                whiteManaValue = countCharInString(mana, 'W');
                if (whiteManaValue > this.field.countNumberOfUntappedColoredLands("White")) {
                    whiteBoolean = false;
                }
            }

            if (mana.contains("U")) {
                blueManaValue = countCharInString(mana, 'U');
                if (blueManaValue > this.field.countNumberOfUntappedColoredLands("Blue")) {
                    blueBoolean = false;
                }
            }

            if (mana.contains("B")) {
                blackManaValue = countCharInString(mana, 'B');
                if (blackManaValue > this.field.countNumberOfUntappedColoredLands("Black")) {
                    blackBoolean = false;
                }
            }

            if (mana.matches(".*\\d.*")) {
                anyManaValue = Integer.parseInt(mana.replaceAll("[^0-9]", ""));
                if (anyManaValue > (this.field.countUntappedLand() - redManaValue - greenManaValue - whiteManaValue - blueManaValue - blackManaValue)) {
                    anyManaBoolean = false;
                }
            }
            if(redBoolean && greenBoolean && whiteBoolean && blueBoolean && blackBoolean && anyManaBoolean) {
                return true;
            }
        }
        return false;
    }

    public void howToPayManaOfCard(String manaCost) {
        int possibleManaCosts = 1;
        if(manaCost.contains("/")) {
            possibleManaCosts += countCharInString(manaCost, "/".charAt(0));
        } else {
            manaCost += "/";
        }
        for(int i = 0; i < possibleManaCosts; i++) {
            String mana = manaCost.split("/")[i];
            int redManaValue = 0;
            int greenManaValue = 0;
            int whiteManaValue = 0;
            int blueManaValue = 0;
            int blackManaValue = 0;
            int anyManaValue = 0;

            if (mana.contains("R")) {
                redManaValue = countCharInString(mana, 'R');
            }

            if (mana.contains("G")) {
                greenManaValue = countCharInString(mana, 'G');
            }

            if (mana.contains("W")) {
                whiteManaValue = countCharInString(mana, 'W');
            }

            if (mana.contains("U")) {
                blueManaValue = countCharInString(mana, 'U');
            }

            if (mana.contains("B")) {
                blackManaValue = countCharInString(mana, 'B');
            }

            if (mana.matches(".*\\d.*")) {
                anyManaValue = Integer.parseInt(mana.replaceAll("[^0-9]", ""));
            }

            String stringToPrint = "ou need to tap " + redManaValue + " mountain(s), " + greenManaValue + " forest(s), " + whiteManaValue +
                    " plain(s), " + blueManaValue + " island(s), " + blackManaValue + " swamp(s) and " + anyManaValue + " of whatever " +
                    "land(s) you choose.";
            if(i == 0 && possibleManaCosts > 1) {
                System.out.print("\nY" + stringToPrint);
            } else if(i == 0 && possibleManaCosts <= 1) {
                System.out.println("\nY" + stringToPrint);
            } else if(possibleManaCosts - 1 > i) {
                System.out.print("Or y" + stringToPrint);
            } else {
                System.out.println("Or y" + stringToPrint);
            }
        }
    }

    public void tapLands(int numberOfRedLands, int numberOfGreenLands, int numberOfWhiteLands, int numberOfBlueLands, int numberOfBlackLands) {
        this.field.tapLands(numberOfRedLands, numberOfGreenLands, numberOfWhiteLands, numberOfBlueLands, numberOfBlackLands);
    }

    public void untapAllCardsOnField() {
        this.field.untapAllCardsOnField();
    }

    public void showUntappedLandsPerColor() {
        this.field.showUntappedLandsPerColor();
    }

    public int countCharInString(String string, char charToCount) {
        int numberOfTimesItAppears = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == charToCount) {
                numberOfTimesItAppears++;
            }
        }
        return numberOfTimesItAppears;
    }

    public void showField() {
        this.field.showField();
    }

    public boolean isThereAPlaneswalker() {
        return this.field.isThereAPlaneswalker();
    }

    public ArrayList<Planeswalker> getPlaneswalker() {
        return this.field.getPlaneswalkers();
    }

    public String getPlaneswalkersNameAtIndex(int index) {
        return this.field.getPlaneswalkersNameAtIndex(index);
    }

    public ArrayList<Creature> getCreaturesByIndex(ArrayList<Integer> indexes) {
        return this.field.getCreatureByIndex(indexes);
    }

    public ArrayList<Creature> getCreaturesAbleToAttack(int turn) {
        return this.field.getCreaturesAbleToAttack(turn);
    }

    public ArrayList<String> getCreaturesNameAndIndex(int turn) {
        return this.field.getCreaturesNameAndIndex(turn);
    }

    public ArrayList<String> getAllCreaturesAbleToDefendNamesAndIndexs() {
        return this.field.getAllCreaturesAbleToDefendNamesAndIndexs();
    }

    public ArrayList<String> getAllCreaturesAbleToDefendNamesAndIndexs(Creature creatureThatIsAttacking) {
        return this.field.getAllCreaturesAbleToDefendNamesAndIndexs(creatureThatIsAttacking);
    }

    public void defineTheTurnACreatureWasPlayedOfTheLastAddedCreature(int turn) {
        this.field.defineTheTurnACreatureWasPlayedOfTheLastAddedCreature(turn);
    }

    public boolean areThereLandsInHand() {
        return this.hand.areThereLandsInHand();
    }

    public boolean areThereNonLandsInHand() {
        return this.hand.areThereNonLandsCardsInHand();
    }

    public ArrayList<Integer> checkForCounterSpell(Card cardBeingPlayed) {
        return this.hand.checkForCounterSpell(cardBeingPlayed);
    }

    public void attack(Player player, int index) {
        this.field.attack(player, index);
    }

    public void attack(int indexOfPlaneswalker, int index) {
        this.field.attack(indexOfPlaneswalker, index);
    }

    public void tapCreatures(ArrayList<Integer> indexes) {
        this.field.tapCreatures(indexes);
    }

    public void showGraveyard() {
        this.graveyard.showGraveyard();
    }

    public Card getCardFromStack() {
        return this.stack.getCardFromStack();
    }

    public Card getLastCardFromCounterSpellStack() {
        return this.stack.getLastCardFromCounterSpellStack();
    }

    public int countCounterSpellsUsed() {
        return this.stack.countCounterSpellsUsed();
    }

    public void removeAllCounterSpells() {
        ArrayList<Card> counterSpellsToGraveyard = this.stack.removeAllCounterSpells();
        for (int i = 0; i < counterSpellsToGraveyard.size(); i++) {
            this.graveyard.sendToGarbage(counterSpellsToGraveyard.get(i));
        }
    }

    public ArrayList<String> getIndexAndNameOfAllCardsInHand() {
        return this.hand.getIndexAndNameOfAllCardsInHand();
    }

    public String getCyclingPrice(Card cardWithCycling) {
        String effectAfterCycling = cardWithCycling.getEffect().split("Cycling")[1];
        if(effectAfterCycling.contains("<i>")) {
            effectAfterCycling = effectAfterCycling.split("<i>")[0];
        } else if(effectAfterCycling.contains(".")) {
            effectAfterCycling = effectAfterCycling.split("\\.")[0];
        }
        return effectAfterCycling.trim();
    }

    public void useCycling(int indexOfCardWithCycling) {
        Card cardWithCycling = this.hand.removeFromHand(indexOfCardWithCycling);
        this.graveyard.sendToGarbage(cardWithCycling);
        drawCards(1);
    }
}