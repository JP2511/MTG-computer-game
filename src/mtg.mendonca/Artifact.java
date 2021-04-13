package mtg.mendonca;

import java.util.ArrayList;

public class Artifact extends Card {

    private final boolean cycling = getEffect().contains("Cycling") && !getEffect().contains("Cyclicing abilities");

    {
        if(super.getEffect().contains(super.getName() + " enters the battlefield tapped")) {
            super.tap();
        }
    }

    public Artifact(String name, String color, String manaCost, String effect) {
        super(name, color, manaCost, "Artifact", effect);
    }

    public Artifact() {
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