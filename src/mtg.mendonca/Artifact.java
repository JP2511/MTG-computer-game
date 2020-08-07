package mtg.mendonca;

public class Artifact extends Card {
    private boolean permanent;
    private boolean spell;

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setSpell(boolean spell) {
        this.spell = spell;
    }

    public boolean isSpell() {
        return spell;
    }

    public Artifact(String name, String color, String manaCost, String type, String effect, boolean permanent, boolean spell) {
        super(name, color, manaCost, type, effect);
        this.permanent = permanent;
        this.spell = spell;
    }

    public Artifact(String name, String color, String manaCost, String effect) {
        this(name, color, manaCost, "artifact", effect, false, false);
    }
}