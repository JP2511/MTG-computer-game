package mtg.mendonca;

import java.util.ArrayList;
import java.util.List;

public class Land extends Card {
    private String subType;
    private final boolean cycling = getEffect().contains("Cycling") && !getEffect().contains("Cyclicing abilities");

    {
        if(super.getEffect().contains(super.getName() + " enters the battlefield tapped")) {
            super.tap();
        }
    }

    public Land(String name, String color, String effect, String subType) {
        super(name, color, "0", "Land", effect);
        this.subType = subType;
    }

    public Land() {
        this("No Name", "No Color", "No Effect", "No Subtype");
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

    @Override
    public ArrayList<String> getCardsHandEffects() {
        ArrayList<String> effectsPossible = new ArrayList<>();
        if(this.cycling) {
            effectsPossible.add("Cycling");
        }
        return effectsPossible;
    }
}