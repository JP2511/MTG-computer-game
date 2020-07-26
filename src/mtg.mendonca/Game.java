package mtg.mendonca;

import java.util.List;

public class Game {
    public static void main(String[] args) {
        Creature JoJo =  new Creature("Jojo", "Green", "1G", "Anime Character", 2, 3, "None");
        List carta = JoJo.showCard();
        for(int i = 0; i < 12; i++) {
            System.out.println(carta.get(i));
        }
    }
}

