package mtg.mendonca;

public class Instant extends Card{
    private boolean spell;

    public boolean isSpell() {
        return spell;
    }

    public void setSpell(boolean spell) {
        this.spell = spell;
    }

    public Instant(String name, String color, String manaCost, String type, String effect, boolean spell) {
        super(name, color, manaCost, type, effect);
        this.spell = spell;
    }

    public Instant(String name, String color, String manaCost, String effect) {
        this(name, color, manaCost, "Instant", effect, false);
    }
}