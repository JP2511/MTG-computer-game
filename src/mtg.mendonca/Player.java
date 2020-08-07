package mtg.mendonca;

public class Player {
    private int life;
    private String name;

    public Player(int life, String name) {
        this.life = life;
        this.name = name;
    }

    public Player(String name) {
        this(20, name);
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return this.life;
    }

    public void loseLife(int extra) {
        this.life -= extra;
    }

    public void gainLife(int extra) {
        this.life += extra;
    }
}