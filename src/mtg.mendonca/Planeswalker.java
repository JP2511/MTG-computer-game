package mtg.mendonca;

import java.util.ArrayList;

public class Planeswalker extends Card {
    private int life;
    private boolean permanent;
    private boolean spell;
    private boolean dead;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

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

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Planeswalker(String name, String color, String manaCost, String type, String effect, int life, boolean permanent, boolean spell, boolean dead) {
        super(name, color, manaCost, type, effect);
        this.life = life;
        this.permanent = permanent;
        this.spell = spell;
        this.dead = dead;
    }

    public Planeswalker(String name, String color, String manaCost, String type, String effect, int life) {
        this(name, color, manaCost, "Planeswalker", effect, life, false, false, false);
    }

    public Planeswalker() {
        super("No Name","No Color", "0", "Planeswalker", "No effect");
        this.life = 0;
    }

    public void addLife(int number) {
        this.life += number;
        System.out.println(getName() + " has gained " + number + " of life. And therefore it has a total of " + this.life + " of life.");
    }

    public void looseLife(int number) {
        this.life -= number;
        System.out.println(getName() + " has lost " + number + " of life. And therefore it has a total of " + this.life + " of life.");
    }

    @Override
    public ArrayList<String> getCardsHandEffects() {
        ArrayList<String> effectsPossible = new ArrayList<>();
        return effectsPossible;
    }
}