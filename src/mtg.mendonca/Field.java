package mtg.mendonca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

}