package mtg.mendonca;

import java.util.ArrayList;

public class Planeswalker extends Card {
    private int life;
    private boolean permanent;
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

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Planeswalker(String name, String color, String manaCost, String effect, int life) {
        super(name, color, manaCost, "Planeswalker", effect);
        this.life = life;
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