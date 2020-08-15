package mtg.mendonca;

public class Game {
    public static void main(String[] args) {
        Creature joao = new Creature("João", "Red", "2RR", "He is a strong man, and very very powerful.", 8, 4, "Human");
        Instant gg = new Instant("gg", "blue", "2BB", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Enchantment sofiaslover = new Enchantment("Doug's bitch", "green", "3G", "Become attached to Doug's every move", "Love");
        String[] crea = joao.getCard();
        String[] inst = gg.getCard();
        String[] sof = sofiaslover.getCard();
        for(int i= 0; i< 15; i++){
            System.out.println(crea[i]+"   "+inst[i]+"   "+sof[i]);
        }
    }
}

