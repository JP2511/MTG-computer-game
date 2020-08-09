package mtg.mendonca;

import java.util.List;

public class Field {
    private List<List> allCreatures;                                              //List that contains list of creature + their enchantments
    private List<Artifact> artifactos;
    private List<Plainswalker> plainswalkers;

    public void removeFromField(Creature creature) {                              //goes through each element ( a List) of allCreatures
        for(int i = 0; i < this.allCreatures.size(); i++) {                       //determines if the element contains the specified creature
            if(this.allCreatures.get(i).contains(creature)) {                     //and eliminates the entire list (Creature + enchantments)
                this.allCreatures.remove(this.allCreatures.get(i));
            }
        }
    }

    public void removeFromField(Artifact artifact) {
        this.artifactos.remove(artifact);
    }

    public void removeEnchantmentFromField(Creature creature, Enchantment enchantment) {     //goes through each element ( a List) of allCreatures
        for(int i = 0; i < this.allCreatures.size(); i++) {                                  //determines if the element contains the specified creature
            if(this.allCreatures.get(i).contains(creature)) {                                //and eliminates the enchantment
                this.allCreatures.get(i).remove(enchantment);
            }
        }
    }
    public void removeFromField(Plainswalker plainswalker) {
        this.plainswalkers.remove(plainswalker);
    }
}