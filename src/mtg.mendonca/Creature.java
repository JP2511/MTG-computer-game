package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

public class Creature{
    private String name;
    private String color;
    private String manaCost;
    private String subType;
    private int attack;
    private int  life;
    private String effects;

    public Creature(String name, String color, String manaCost, String subType, int attack, int  life, String effects) {
        this.name = name;
        this.color = color;
        this.manaCost = manaCost;
        this.subType = subType;
        this.attack = attack;
        this. life =  life;
        this.effects = effects;
    }

    public List showCard() {  //Create the card

        List<String> card = new ArrayList<String>();

        String inicioFim = "---------------------------------";
        card.add(inicioFim);

        String segundoPenultimo = "|                               |";
        card.add(segundoPenultimo);

        String nameString = "";                  //linha do nome
        for(int i = 0; i<32; i++) {
            if(i == 0) {
                nameString += "| Name: " + name;
            } else if(i == 31) {
                nameString += "|";
            } else if (i== nameString.length()-1  ){
                nameString += " ";
            }
        }
        card.add(nameString);

        String colorString = "";                 //linha da cor da carta
        for(int i = 0; i<32; i++) {
            if(i == 0) {
                colorString += "| Color: " + color;
            } else if(i == 31) {
                colorString += "|";
            } else if (i== colorString.length()-1  ){
                colorString += " ";
            }
        }
        card.add(colorString);

        String manaCostString = "";              //linha da manaCost da carta
        for(int i = 0; i<32; i++) {
            if(i == 0) {
                manaCostString += "| ManaCost: " + manaCost;
            } else if(i == 31) {
                manaCostString += "|";
            } else if (i== manaCostString.length()-1  ){
                manaCostString += " ";
            }
        }
        card.add(manaCostString);

        String typeString = "";              //linha do tipo da carta
        for(int i = 0; i<32;i++) {
            if(i ==0 ) {
                String type = "Creature";
                typeString += "| Type: " + type;
            } else if(i == 31) {
                typeString += "|";
            } else if (i== typeString.length()-1  ){
                typeString += " ";
            }
        }
        card.add(typeString);

        String subTypeString = "";              //linha do subtipo da carta
        for(int i = 0; i<32; i++) {
            if(i == 0) {
                subTypeString += "| Subtype: " + subType;
            } else if(i == 31) {
                subTypeString += "|";
            } else if (i== subTypeString.length()-1  ){
                subTypeString += " ";
            }
        }
        card.add(subTypeString);

        String attackString = "";              //linha do ataque da carta
        for(int i = 0; i<32; i++) {
            if(i == 0) {
                attackString += "| Attack: " + attack;
            } else if(i == 31) {
                attackString += "|";
            } else if (i== attackString.length()-1  ){
                attackString += " ";
            }
        }
        card.add(attackString);

        String  lifeString = "";              //linha da defesa da carta
        for(int i = 0; i<32; i++) {
            if(i == 0) {
                 lifeString += "|  life: " +  life;
            } else if(i == 31) {
                 lifeString += "|";
            } else if (i==  lifeString.length()-1  ){
                 lifeString += " ";
            }
        }
        card.add( lifeString);

        String effectsString = "";              //linha dos efeitos da carta
        for(int i = 0; i<32; i++) {
            if(i == 0) {
                effectsString += "| Effects: " + effects;
            } else if(i == 31) {
                effectsString += "|";
            } else if (i== effectsString.length()-1  ){
                effectsString += " ";
            }
        }
        card.add(effectsString);
        card.add(segundoPenultimo);
        card.add(inicioFim);
        return card;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getLife() {
        return this.life;
    }

    public void doAttack(Player player) {
        player.loseLife(this.attack);
    }
}