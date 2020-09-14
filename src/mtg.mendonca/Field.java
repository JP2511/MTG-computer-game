package mtg.mendonca;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Field {
    private ArrayList<ArrayList<Card>> creaturesAndEnchantments = new ArrayList<>();                                              //List that contains list of creature + their enchantments
    private ArrayList<Artifact> artifactos = new ArrayList<>();
    private ArrayList<Planeswalker> planeswalkers = new ArrayList<>();
    private LinkedList<Land> basicLands = new LinkedList<>();
    private ArrayList<Card> instantAndSorcery = new ArrayList<>();
    private Card temporaryEnchantment = new Enchantment();

    public ArrayList<Card> removeCreatureFromField(int i) {
        ArrayList<Card> cardsToRemove = new ArrayList<>();
        for(int j = 0; j < this.creaturesAndEnchantments.get(i).size(); j++) {
            cardsToRemove.add((Card) this.creaturesAndEnchantments.get(i).get(j));
        }
        this.creaturesAndEnchantments.remove(i);
        return cardsToRemove;
    }

    public Card removeArtifactFromField(int i) {
        Card artifactToRemove = this.artifactos.get(i);
        this.artifactos.remove(i);
        return artifactToRemove;
    }

    public Card removeEnchantmentFromField(int i, int j) {
        Card enchantmentToRemove = (Card) this.creaturesAndEnchantments.get(i).get(j);
        this.creaturesAndEnchantments.get(i).remove(j);
        return enchantmentToRemove;
    }

    public Card removePlaneswalkerFromField(int i) {
        Card planeswalkerToRemove = this.planeswalkers.get(i);
        this.planeswalkers.remove(i);
        return planeswalkerToRemove;
    }

    public Card removeBasicLandFromField(String color) {
        Land cardToBeRemoved = new Land();
        Iterator<Land> landIterator = this.basicLands.iterator();
        while(landIterator.hasNext()) {
            Land land = landIterator.next();
            if(land.getColor().equals(color)) {
                cardToBeRemoved = land;
                landIterator.remove();
                break;
            }
        }
        return cardToBeRemoved;
    }

    public Card removeInstantOrSorceryFromField() {
        Card instantOrSorceryToRemove = this.instantAndSorcery.get(0);
        this.instantAndSorcery.remove(0);
        return instantOrSorceryToRemove;
    }

    public void addCreatureToField(Creature creature) {
        ArrayList<Card> creatureToAdd = new ArrayList<>();
        creatureToAdd.add(creature);
        this.creaturesAndEnchantments.add(creatureToAdd);
    }

    public void addArtifactToField(Artifact artifact) {
        this.artifactos.add(artifact);
    }
    
    public void addPlaneswalkerToField(Planeswalker planeswalker) {
        this.planeswalkers.add(planeswalker);
    }

    public void addBasicLandToField(Land land) {
        this.basicLands.add(land);
    }

    public void addInstantOrSorceryToField(Card card) {
        this.instantAndSorcery.add(card);
    }

    public void addEnchantmentToField(Enchantment enchantment) {
        this.temporaryEnchantment = enchantment;
    }
    
    public void enchantCreature(int index) {
        this.creaturesAndEnchantments.get(index).add(this.temporaryEnchantment);
        this.temporaryEnchantment = new Enchantment();
    }

    public int countNumberOfUntappedColoredLands(String color) {
        Iterator<Land> landIterator = this.basicLands.iterator();
        int numberOfUntappedLandsOfSpecifidColor = 0;
        while(landIterator.hasNext()) {
            Land landToBeCounted = landIterator.next();
            if(!landToBeCounted.isTapped() && landToBeCounted.getColor().equals(color)) {
                numberOfUntappedLandsOfSpecifidColor++;
            }
        }
        return numberOfUntappedLandsOfSpecifidColor;
    }

    public int countUntappedLand() {
        Iterator<Land> landIterator = this.basicLands.iterator();
        int numberOfUntappedLand = 0;
        while (landIterator.hasNext()) {
            if( !landIterator.next().isTapped()) {
                numberOfUntappedLand++;
            }
        }
        return numberOfUntappedLand;
    }

    public void tapLands(int numberOfRedLands, int numberOfGreenLands, int numberOfWhiteLands, int numberOfBlueLands, int numberOfBlackLands) {
        Iterator<Land> landIterator = this.basicLands.iterator();
        if(numberOfRedLands + numberOfGreenLands + numberOfWhiteLands + numberOfBlueLands + numberOfBlackLands > countUntappedLand()) {
            System.out.println("You don't have enough mana.");
        } else {
            while(landIterator.hasNext() && (numberOfRedLands > 0 || numberOfGreenLands > 0 || numberOfWhiteLands > 0 || numberOfBlueLands > 0 || numberOfBlackLands > 0)) {
                Land land = landIterator.next();
                if(numberOfRedLands > 0 && land.getColor().equals("Red")) {
                    land.tap();
                    numberOfRedLands--;
                } else if(numberOfGreenLands > 0 && land.getColor().equals("Green")) {
                    land.tap();
                    numberOfGreenLands--;
                } else if(numberOfWhiteLands > 0 && land.getColor().equals("White")) {
                    land.tap();
                    numberOfWhiteLands--;
                } else if(numberOfBlueLands > 0 && land.getColor().equals("Blue")) {
                    land.tap();
                    numberOfBlueLands--;
                } else if(numberOfBlackLands > 0 && land.getColor().equals("Black")) {
                    land.tap();
                    numberOfBlackLands--;
                }
            }
        }
    }

    public void untapLands() {
        Iterator<Land> landIterator = this.basicLands.iterator();
        while(landIterator.hasNext()) {
            landIterator.next().untap();
        }
    }

    public void untapAllCardsOnField() {
        Iterator<Land> landIterator = this.basicLands.iterator();
        while(landIterator.hasNext()) {
            landIterator.next().untap();
        }
        for(int i = 0; i < creaturesAndEnchantments.size(); i++) {
                creaturesAndEnchantments.get(i).get(0).untap();
        }
        for(int i = 0; i < artifactos.size(); i++) {
            artifactos.get(i).untap();
        }
    }

    public void showUntappedLandsPerColor() {
        int redUntappedLands = 0;
        int greenUntappedLands = 0;
        int whiteUntappedLands = 0;
        int blueUntappedLands = 0;
        int blackUntappedLands = 0;

        Iterator<Land> landIterator = this.basicLands.iterator();
        while(landIterator.hasNext()) {
            Land land = landIterator.next();
            if(!land.isTapped()) {
                switch (land.getColor()) {
                    case "Red":
                        redUntappedLands++;
                        break;
                    case "Green":
                        greenUntappedLands++;
                        break;
                    case "White":
                        whiteUntappedLands++;
                        break;
                    case "Blue":
                        blueUntappedLands++;
                        break;
                    case "Black":
                        blackUntappedLands++;
                        break;
                }
            }
        }

        System.out.println("You have " + redUntappedLands + " untapped mountain(s), " + greenUntappedLands + " untapped forest(s), " +
                whiteUntappedLands + " untapped plain(s), " + blueUntappedLands + " untapped island(s) and " + blackUntappedLands +
                " untapped swamp(s).");
    }

    public void showTappedLandsPerColor() {
        int redTappedLands = 0;
        int greenTappedLands = 0;
        int whiteTappedLands = 0;
        int blueTappedLands = 0;
        int blackTappedLands = 0;

        Iterator<Land> landIterator = this.basicLands.iterator();
        while(landIterator.hasNext()) {
            Land land = landIterator.next();
            if(land.isTapped()) {
                switch (land.getColor()) {
                    case "Red":
                        redTappedLands++;
                        break;
                    case "Green":
                        greenTappedLands++;
                        break;
                    case "White":
                        whiteTappedLands++;
                        break;
                    case "Blue":
                        blueTappedLands++;
                        break;
                    case "Black":
                        blackTappedLands++;
                        break;
                }
            }
        }

        System.out.println("You have " + redTappedLands + " tapped mountain(s), " + greenTappedLands + " tapped forest(s), " +
                whiteTappedLands + " tapped plain(s), " + blueTappedLands + " tapped island(s) and " + blackTappedLands +
                " tapped swamp(s).");
    }

    public void showField() {
        ArrayList<Card> cardsInField = new ArrayList<>();
        for(int i = 0; i < this.creaturesAndEnchantments.size(); i++) {
            cardsInField.addAll(this.creaturesAndEnchantments.get(i));
        }
        cardsInField.addAll(this.artifactos);
        cardsInField.addAll(this.planeswalkers);

        ArrayList<Card> stackedCardsLine = new ArrayList();
        for(int i = 0; i < cardsInField.size(); i++) {
            if((i+1) % 5 != 0 && i != cardsInField.size()-1) {
                stackedCardsLine.add(cardsInField.get(i));
            } else {
                stackedCardsLine.add(cardsInField.get(i));
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
        showUntappedLandsPerColor();
        showTappedLandsPerColor();
    }

    public boolean isThereAPlaneswalker() {
        return this.planeswalkers.size() > 0;
    }

    public ArrayList<Planeswalker> getPlaneswalkers() {
        return this.planeswalkers;
    }

    public String getPlaneswalkersNameAtIndex(int index) {
        return this.planeswalkers.get(index).getName();
    }

    public ArrayList<Creature> getCreatureByIndex(ArrayList<Integer> indexes) {
        ArrayList<Creature> creaturesToBeReturned = new ArrayList<>();
        for(int i = 0; i < indexes.size(); i++) {
            creaturesToBeReturned.add((Creature) this.creaturesAndEnchantments.get(indexes.get(i).intValue()).get(0));
        }
        return creaturesToBeReturned;
    }

    public ArrayList<Creature> getCreaturesAbleToAttack(int turn) {
        ArrayList<Creature> possibleCreaturesToAttack = new ArrayList<>();
        for(int i = 0; i < this.creaturesAndEnchantments.size(); i++) {
            if(((Creature) this.creaturesAndEnchantments.get(i).get(0)).canAttack(turn)) {
                possibleCreaturesToAttack.add((Creature) this.creaturesAndEnchantments.get(i).get(0));
            }
        }
        return possibleCreaturesToAttack;
    }

    public ArrayList<String> getAllCreaturesAbleToDefendNamesAndIndexs() {
        ArrayList<String> allCreaturesNames = new ArrayList<>();
        for(int i = 1; i <= this.creaturesAndEnchantments.size(); i++) {
            if(!this.creaturesAndEnchantments.get(i).get(0).isTapped()) {
                allCreaturesNames.add("\t" + i + " - " + this.creaturesAndEnchantments.get(i).get(0).getName());
            }
        }
        return allCreaturesNames;
    }

    public void defineTheTurnACreatureWasPlayedOfTheLastAddedCreature(int turn) {
        ((Creature) this.creaturesAndEnchantments.get(this.creaturesAndEnchantments.size() - 1).get(0)).setTurnInWhichItWasPlayed(turn);
    }

    public ArrayList<String> getCreaturesNameAndIndex(int turn) {
        ArrayList<String> creaturesToAttackNamesAndIndex = new ArrayList<>();
        for( int i = 0; i < this.creaturesAndEnchantments.size(); i++) {
            if(((Creature) this.creaturesAndEnchantments.get(i).get(0)).canAttack(turn)) {
                creaturesToAttackNamesAndIndex.add("\t" + (i+1) + " - " + this.creaturesAndEnchantments.get(i).get(0).getName());
            }
        }
        return creaturesToAttackNamesAndIndex;
    }
}