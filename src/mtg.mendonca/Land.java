package mtg.mendonca;

public class Land extends Card {
    private String subType;
    private boolean permanent;

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public Land(String name, String color, String manaCost, String type, String effect, String subType, boolean permanent) {
        super(name, color, manaCost, type, effect);
        this.subType = subType;
        this.permanent = permanent;
    }

    public Land(String name, String color, String manaCost, String effect, String subType) {
        this(name, color, manaCost, "Land", effect, subType, false);
    }
}