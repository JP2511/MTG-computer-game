package mtg.mendonca;

import java.util.ArrayList;

public class Sorcery extends Card {
    private boolean spell;
    private final boolean cycling = getEffect().contains("Cycling") && !getEffect().contains("Cyclicing abilities");

    public boolean isSpell() {
        return spell;
    }

    public void setSpell(boolean spell) {
        this.spell = spell;
    }

    public Sorcery(String name, String color, String manaCost, String type, String effect, boolean spell) {
        super(name, color, manaCost, type, effect);
        this.spell = spell;
    }

    public Sorcery(String name, String color, String manaCost, String effect) {
        this(name, color, manaCost, "Sorcery", effect, false);
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