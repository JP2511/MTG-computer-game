package mtg.mendonca;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        Creature joao = new Creature("João", "Red", "2RR", "He is a strong man, and very very powerful.", 8, 4, "Human");
        Instant gg = new Instant("gg", "blue", "2BB", "123456789123456789123456789123456789123456789123456789");
        Enchantment sofiaslover = new Enchantment("Doug's bitch", "green", "3G", "Become attached to Doug's every move", "Love");
//        gg.getCard();

        List cartas = new ArrayList();
        cartas.add(joao);
        cartas.add(gg);
        cartas.add(sofiaslover);cartas.add(joao);
        cartas.add(gg);
        cartas.add(sofiaslover);cartas.add(joao);
        cartas.add(gg);
        cartas.add(sofiaslover);
        Deck bloodRush = new Deck(cartas);
        List numeros = new ArrayList();
        numeros.add(0);
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        numeros.add(5);
        numeros.add(6);
        bloodRush.showCards(numeros);
    }

}

