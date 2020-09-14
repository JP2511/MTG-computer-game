package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

public class Enchantment extends Card {
    private String subType;
    private boolean permanent;
    private boolean spell;
    private Creature enchantedCreature;
    private boolean isEnchanted = false;

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

    public void enchantCreature(Creature creature) {
        this.enchantedCreature = creature;
        this.isEnchanted = true;
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
    public Enchantment() {
        this("No Name", "No Color", "0", "No Effect", "No Subtype");
    }

    @Override
    public String[] getCard () {  //creates a String array containing the contents of the card
        String[] carta = new String[15];
        List caracteristicas = new ArrayList();
        caracteristicas.add("Name: " + super.getName());
        caracteristicas.add("Color: " + super.getColor());
        caracteristicas.add("ManaCost: " + super.getManaCost());
        caracteristicas.add("Type: " + super.getType());
        caracteristicas.add("SubType: " + this.subType);
        String efeito = "Effect: " + super.getEffect();
        if(efeito.length()+4 > 33) {
            String a = "";
            for(int i = 0; i < efeito.length(); i++) {
                if( a.length() < 29 & i != efeito.length()-1) {
                    a += efeito.charAt(i);
                } else {
                    caracteristicas.add(a);
                    a = ""+efeito.charAt(i);
                }
            }
        } else {
            caracteristicas.add(efeito);
        }

        if(this.isEnchanted) {
            caracteristicas.add("Enchanted creature: " + enchantedCreature.getName());
        }

        for(int i = 0; i<15; i++){
            if(i==0 || i==14) {
                carta[i] = "---------------------------------";
            } else if (i > 1 & i <caracteristicas.size() + 2 ) {
                String valor = "";
                for(int j = 0; j < 33; j++){
                    if(j==0 || j == 32){
                        valor += "|";
                    } else if( j > 1 && j < ((String) caracteristicas.get(i-2)).length() + 2) {
                        valor += ((String) caracteristicas.get(i-2)).charAt(j-2);
                    } else {
                        valor += " ";
                    }
                }
                carta[i] = valor;
            } else {
                carta[i] = "|                               |";
            }
        }
        return carta;
    }
}