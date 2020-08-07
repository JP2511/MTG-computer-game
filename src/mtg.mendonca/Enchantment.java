package mtg.mendonca;

public class Enchantment extends Card {
    private String subType;
    private boolean permanent;
    private boolean spell;

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public boolean isSpell() {
        return spell;
    }

    public void setSpell(boolean spell) {
        this.spell = spell;
    }

    public Enchantment(String name, String color, String manaCost, String type, String effect, String subType, boolean permanent, boolean spell) {
        super(name, color, manaCost, type, effect);
        this.subType = subType;
        this.permanent = permanent;
        this.spell = spell;
    }
    public Enchantment(String name, String color, String manaCost, String effect, String subType) {
        this(name, color, manaCost, "Enchantment", effect, subType, false, false);
    }
}