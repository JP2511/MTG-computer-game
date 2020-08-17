package mtg.mendonca;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
        cartas.add(sofiaslover);
        cartas.add(joao);
        cartas.add(gg);
        cartas.add(sofiaslover);
        cartas.add(joao);
        cartas.add(gg);
        cartas.add(sofiaslover);
        Deck bloodRush = new Deck(cartas);
        Hand miguel = new Hand();
        miguel.sendToHand(bloodRush.drawOrRemoveCards(7));
        miguel.showHand(7);
    }

}

