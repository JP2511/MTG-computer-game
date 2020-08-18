package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private ArrayList<ArrayList<Card>> allCreatures = new ArrayList<>();                                              //List that contains list of creature + their enchantments
    private ArrayList<Artifact> artifactos = new ArrayList<>();
    private ArrayList<Plainswalker> plainswalkers = new ArrayList<>();
    private ArrayList<Land> lands= new ArrayList<>();
    private ArrayList<Land> basicLands = new ArrayList<>();

    public ArrayList<Card> removeCreatureFromField(int i) {
        ArrayList<Card> cardsToRemove = new ArrayList<>();
        for(int j = 0; j < this.allCreatures.get(i).size(); j++) {
            cardsToRemove.add((Card) this.allCreatures.get(i).get(j));
        }
        this.allCreatures.remove(i);
        return cardsToRemove;
    }

    public Card removeArtifactFromField(int i) {
        Card artifactToRemove = this.artifactos.get(i);
        this.artifactos.remove(i);
        return artifactToRemove;
    }

    public Card removeEnchantmentFromField(int i, int j) {
        Card enchantmentToRemove = (Card) this.allCreatures.get(i).get(j);
        this.allCreatures.get(i).remove(j);
        return enchantmentToRemove;
    }

    public Card removePlainswalkerFromField(int i) {
        Card plainswalkerToRemove = this.plainswalkers.get(i);
        this.plainswalkers.remove(i);
        return plainswalkerToRemove;
    }

    public Card removeBasicLandFromField(String color) {
        for(int i = 0; i < this.basicLands.size(); i++) {
            if(this.basicLands.get(i).getColor() == color) {
                Card basicLandToRemove = this.basicLands.get(i);
                this.basicLands.remove(i);
                return basicLandToRemove;
            }
        }
        return new Card("No Name", "No Color", "0", "No Type", "No Effect");
    }

    public Card removeLandFromField(int i ) {
        Card landToRemove = this.lands.get(i);
        this.lands.remove(i);
        return landToRemove;
    }

    public void addCreatureToField(Creature creature) {
        ArrayList<Card> creatureToAdd = new ArrayList<>();
        creatureToAdd.add(creature);
        this.allCreatures.add(creatureToAdd);
    }

    public void addArtifactToField(Artifact artifact) {
        this.artifactos.add(artifact);
    }

    public void addEnchantmentToField(Enchantment enchantment, int i) {
        this.allCreatures.get(i).add(enchantment);
    }

    public void addPlainswalkerToField(Plainswalker plainswalker) {
        this.plainswalkers.add(plainswalker);
    }

    public void addBasicLandToField(Land land) {
        this.basicLands.add(land);
    }

    public void addLandToField(Land land) {
        this.lands.add(land);
    }
}