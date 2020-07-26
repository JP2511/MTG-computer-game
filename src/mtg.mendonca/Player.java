package mtg.mendonca;

public class Player {
    private int life;

    public Player(int life) {
        this.life = life;
    }

    public Player() {
        this(20);
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