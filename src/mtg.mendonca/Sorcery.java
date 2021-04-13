package mtg.mendonca;

import java.util.ArrayList;

public class Sorcery extends Card {
    private final boolean cycling = getEffect().contains("Cycling") && !getEffect().contains("Cyclicing abilities");

    public Sorcery(String name, String color, String manaCost, String effect) {
        super(name, color, manaCost, "Sorcery", effect);
    }

    public Sorcery() {
        this("No Name", "No Color", "0", "No Effect");
    }

    @Override
    public ArrayList<String> getCardsHandEffects() {
        ArrayList<String> effectsPossible = new ArrayList<>();
        if(this.cycling) {
            effectsPossible.add("Cycling");
        }
        return effectsPossible;
    }
}