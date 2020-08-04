package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

public class Creature extends Card {
    private int attack;
    private int defense;
    private String subType;
    private boolean dead = false;

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public boolean isDead() {
        return this.dead;
    }

    public Creature(String name, String color, String manaCost, String effect, int attack, int defense, String subType) {
        super(name, color, manaCost, "Creature", effect);
        this.attack = attack;
        this.defense = defense;
        this.subType = subType;
    }

    public void doDefense(Creature creatura) {
        if(super.isTapped()){
            System.out.println("You can not use this card to defend this turn.");
        } else{
            this.defense -= creatura.getAttack();
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
            System.out.println(nome + " lost " + this.attack + " of life.");
            super.tap();
        }
    }

    public void doAttack(Creature creatura) {
        if(super.isTapped()){
            System.out.println("You can not use this card to attack this turn.");
        } else{
            this.defense -= creatura.getAttack();
            if(this.defense <= 0) {
                this.dead = true;
            } else {
                super.tap();
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
                    a = "";
                }
            }
        } else {
            caracteristicas.add(efeito);
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