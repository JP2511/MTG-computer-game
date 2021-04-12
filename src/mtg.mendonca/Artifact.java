package mtg.mendonca;

import java.util.ArrayList;

public class Artifact extends Card {
    private boolean permanent;

    private final boolean cycling = getEffect().contains("Cycling") && !getEffect().contains("Cyclicing abilities");

    {
        if(super.getEffect().contains(super.getName() + " enters the battlefield tapped")) {
            super.tap();
        }
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public Artifact(String name, String color, String manaCost, String effect, boolean permanent) {
        super(name, color, manaCost, "Artifact", effect);
        this.permanent = permanent;
    }

    public Artifact(String name, String color, String manaCost, String effect) {
        this(name, color, manaCost, effect, false);
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