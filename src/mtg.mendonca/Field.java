package mtg.mendonca;

import java.util.List;

public class Field {
    private List<Creature> creaturas;
    private List<Artifact> artifactos;
    private List<Enchantment> encantamentos;
    private List<Plainswalker> plainswalkers;

    public void removeFromField(Creature creature) {
        this.creaturas.remove(creature);
    }
    public void removeFromField(Artifact artifact) {
        this.artifactos.remove(artifact);
    }
    public void removeFromField(Enchantment enchantment) {
        this.encantamentos.remove(enchantment);
    }
    public void removeFromField(Plainswalker plainswalker) {
        this.plainswalkers.remove(plainswalker);
    }
}