package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

public class Creature extends Card {
    private int attack;
    private int defense;
    private String subType;
    private boolean dead = false;
    private boolean permanent = false;
    private boolean spell;
    private int turnInWhichItWasPlayed;

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isPermanent() {
        return this.permanent;
    }

    public void setSpell(boolean spell) {
        this.spell = spell;
    }

    public boolean isSpell() {
        return spell;
    }

    public void setTurnInWhichItWasPlayed(int turnNumber) {
        this.turnInWhichItWasPlayed = turnNumber;
    }

    public Creature(String name, String color, String manaCost, String type, String effect, int attack, int defense, String subType, boolean dead, boolean permanent, boolean spell) {
        super(name, color, manaCost, "Creature", effect);
        this.attack = attack;
        this.defense = defense;
        this.subType = subType;
        this.dead = dead;
        this.permanent = permanent;
        this.spell = spell;
    }

    public Creature(String name, String color, String manaCost, String effect, int attack, int defense, String subType) {
        this(name, color, manaCost, "Creature", effect, attack, defense, subType, false, false, false);
    }

    public Creature() {
        this("No Name", "No Color", "0", "No Effect", 0, 0, "No Subtype");
    }

    public void doDefense(int damageToTake) {
        if(super.isTapped()){
            System.out.println("You can not use this card to defend this turn.");
        } else{
            this.defense -= damageToTake;
            if(this.defense <= 0) {
                this.dead = true;
            }
        }
    }

    public void doAttack(Player nome){
        if(super.isTapped()){
            System.out.println("You can not use this card to attack this turn.");
        } else{
            nome.loseLife(this.attack);
            System.out.println(nome.getName() + " lost " + this.attack + " of life. " + nome.getName() + " has now " + nome.getLife() + " of life.");
            super.tap();
        }
    }

    public void doAttack(Planeswalker jake){
        if(super.isTapped()){
            System.out.println("You can not use this card to attack this turn.");
        } else{
            jake.setLife(jake.getLife() - this.attack);
            System.out.println(jake.getName() + " lost " + this.attack + " of life. And therefore it now has " + jake.getLife() + " of life.");
        }
    }

    public void doAttack(ArrayList<Creature> defendingCreatures, ArrayList<Integer> valueOfDamageToGive) {
        if(super.isTapped()){
            System.out.println("You can not use this card to attack this turn.");
        } else{
            int sumOfDefendingCreatureAttack = 0;
            for(int i = 0; i < valueOfDamageToGive.size(); i++) {
                sumOfDefendingCreatureAttack += defendingCreatures.get(i).getAttack();
                defendingCreatures.get(i).doDefense(valueOfDamageToGive.get(i));
            }
            if(sumOfDefendingCreatureAttack >= this.defense) {
                this.dead = true;
            }
        }
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
        caracteristicas.add("Attack: " + this.attack);
        caracteristicas.add("Defense: " + this.defense);
        String efeito = "Effect: " + super.getEffect();
        if(efeito.length()+4 > 33) {
            String a = "";
            for(int i = 0; i < efeito.length(); i++) {
                if( a.length() < 29 & i != efeito.length()-1) {
                    a += efeito.charAt(i);
                } else {
                    caracteristicas.add(a);
                    a = "" + efeito.charAt(i);
                }
            }
        } else {
            caracteristicas.add(efeito);
        }
        if(isTapped())  {
            caracteristicas.add("T A P P E D -- TAPPED");
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

    public boolean canAttack(int turn) {
        if(this.turnInWhichItWasPlayed != turn && !isTapped()) {
            return true;
        } else {
            return false;
        }
    }
}