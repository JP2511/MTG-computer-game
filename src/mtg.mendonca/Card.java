package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private String name;
    private String color;
    private String manaCost;
    private String type;
    private String effect;
    private boolean tapped = false;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getManaCost() {
        return manaCost;
    }

    public String getType() {
        return type;
    }

    public String getEffect() {
        return effect;
    }

    public boolean isTapped() {
        return tapped;
    }

    public Card(String name, String color, String manaCost, String type, String effect) {
        this.name = name;
        this.color = color;
        this.manaCost = manaCost;
        this.type = type;
        this.effect = effect;
    }

    public void tap() {
        this.tapped = true;
    }
    public void untap() {
        this.tapped = false;
    }

    public String[] getCard () {  //creates a String array containing the contents of the card
        String[] carta = new String[15];
        List caracteristicas = new ArrayList();
        caracteristicas.add("Name: " + this.name);
        caracteristicas.add("Color: " + this.color);
        caracteristicas.add("ManaCost: " + this.manaCost);
        caracteristicas.add("Type: " + this.type);
        String efeito = "Effect: " + this.effect;
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

    public void showCard() {
        String[] carta = getCard();
        for(int i = 0; i < carta.length; i++) {
            System.out.println(carta[i]);
        }
    }
}