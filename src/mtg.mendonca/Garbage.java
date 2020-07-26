package mtg.mendonca;
import java.util.List;

public class Garbage {
    private List lixo;

    public void sendToGarbage(Creature creature) {
        this.lixo.add(creature);
    }

    public void sendToGarbage(Artifact artifact) {
        this.lixo.add(artifact);
    }

    public void sendToGarbage(Plain plain) {
        this.lixo.add(plain);
    }

    public void sendToGarbage(Instant instant) {
        this.lixo.add(instant);
    }

    public void sendToGarbage(Plainswalker plainswalker) {
        this.lixo.add(plainswalker);
    }
}