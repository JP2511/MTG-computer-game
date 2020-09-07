package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private ArrayList<ArrayList<Card>> creaturesAndEnchantments = new ArrayList<>();                                              //List that contains list of creature + their enchantments
    private ArrayList<Artifact> artifactos = new ArrayList<>();
    private ArrayList<Planeswalker> planeswalkers = new ArrayList<>();
    private ArrayList<Land> lands= new ArrayList<>();
    private ArrayList<Land> basicLands = new ArrayList<>();
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

    public Card removeBasicLandFromField(int index) {
        Card basicLandToRemove = this.basicLands.get(index);
        this.basicLands.remove(index);
        return basicLandToRemove;
    }

    public Card removeLandFromField(int i ) {
        Card landToRemove = this.lands.get(i);
        this.lands.remove(i);
        return landToRemove;
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

    public void addLandToField(Land land) {
        this.lands.add(land);
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
}